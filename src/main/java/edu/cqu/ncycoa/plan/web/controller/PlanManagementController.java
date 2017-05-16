package edu.cqu.ncycoa.plan.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.UserInfo;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.plan.PlanStatus;
import edu.cqu.ncycoa.plan.StepType;
import edu.cqu.ncycoa.plan.domain.DptReview;
import edu.cqu.ncycoa.plan.domain.Plan;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;
import edu.cqu.ncycoa.plan.domain.UserReview;
import edu.cqu.ncycoa.plan.service.PlanService;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/plan-management")
public class PlanManagementController {
	
	@Resource(name="planService")
	PlanService planService;
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request, Model model) {
		model.addAttribute("isAdmin", true);
		return "plan_management/planlist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(Plan plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Plan> cq = new QueryDescriptor<Plan>(Plan.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<Plan> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request, Model model) {
		model.addAttribute("isAdmin", true);
		
		ProcessEngine processEngine = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext())
				.getBean("processEngine", ProcessEngine.class);
		
		Map<String, ProcessDefinition> tmp = new HashMap<String, ProcessDefinition>();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		for (ProcessDefinition def : repositoryService.createProcessDefinitionQuery().list()) {
			if(tmp.get(def.getKey()) != null){
				ProcessDefinition anotherDef = tmp.get(def.getKey());
				if(anotherDef.getVersion() < def.getVersion()){
					tmp.put(def.getKey(), def);
				}
			} else {
				tmp.put(def.getKey(), def);
			}
		}
		
		model.addAttribute("fixFlows", tmp.values());
		
		return "plan_management/plan";
	}
	
	@RequestMapping(params="update")
	public String update(Long id, HttpServletRequest request, Model model) {
		Plan plan = planService.findEntityById(id, Plan.class);
		List<PlanStep> steps = planService.findEntitiesByProperty("plan", plan, PlanStep.class);
		model.addAttribute("taskList", steps);
		model.addAttribute("plan", plan);
		
		Map<String, ProcessDefinition> tmp = new HashMap<String, ProcessDefinition>();
		ProcessEngine processEngine = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext())
				.getBean("processEngine", ProcessEngine.class);
		RepositoryService repositoryService = processEngine.getRepositoryService();
		for (ProcessDefinition def : repositoryService.createProcessDefinitionQuery().list()) {
			if(tmp.get(def.getKey()) != null){
				ProcessDefinition anotherDef = tmp.get(def.getKey());
				if(anotherDef.getVersion() < def.getVersion()){
					tmp.put(def.getKey(), def);
				}
			} else {
				tmp.put(def.getKey(), def);
			}
		}
		
		model.addAttribute("fixFlows", tmp.values());
		
		model.addAttribute("isAdmin", true);
		return "plan_management/plan";
	}
	
	@RequestMapping(params="planstep")
	public String planstep(HttpServletRequest request, Model model) {
		return "plan_management/planstep";
	}
	
	@RequestMapping(params="save")
	@ResponseBody
	public void save(Plan plan, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		
		List<PlanStep> tasks = new ArrayList<PlanStep>();
		if(plan.getStepType().intValue() == StepType.CUSTOM_FLOW.intValue()){
			String[] taskid = request.getParameter("taskid").split(":;;:");
			String[] taskorder = request.getParameter("taskorder").split(":;;:");
			String[] taskparticipant = request.getParameter("taskparticipant").split(":;;:");
			String[] taskParticipantValue = request.getParameter("taskParticipantValue").split(":;;:");
			String[] taskTimeConsuming = request.getParameter("taskTimeConsuming").split(":;;:");
			String[] taskcontent = request.getParameter("taskcontent").split(":;;:");
			String[] tasksummary = request.getParameter("tasksummary").split(":;;:");
			
			for(int i=0; i<taskid.length; i++){
				PlanStep task = new PlanStep();
				task.setId(StringUtils.isBlank(taskid[i]) ? null : Long.parseLong(taskid[i]));
				task.setOrder(Long.parseLong(taskorder[i]));
				
				String[] taskParticipantList_ids = taskParticipantValue[i].split(",");
				String[] taskParticipantLists = taskparticipant[i].split(",");
				Map<String, String> taskParticipants = new HashMap<String, String>();
				for(int k = 0; k < taskParticipantList_ids.length; k++){
					taskParticipants.put(taskParticipantList_ids[k], taskParticipantLists[k]);
				}
				task.setParticipants( taskParticipants );
				task.setSummary(tasksummary[i]);
				task.setContent(taskcontent[i]);
				task.setTimeConsuming(Integer.parseInt(taskTimeConsuming[i]));
				task.setStatus((short)0);
				tasks.add(task);
			}
			
			Collections.sort(tasks);
			Calendar ending = Calendar.getInstance();
			ending.setTime(plan.getPlanBeginDate());
			for(int i=0; i<tasks.size(); i++){
				PlanStep task = tasks.get(i);
				ending.add(Calendar.DAY_OF_YEAR, task.getTimeConsuming());
				task.setEnding(ending.getTime());
			}
		}
		
		String message;
		if (plan.getId() != null) {
			message = "计划更新成功";
			
			Plan t = planService.findEntityById(plan.getId(), Plan.class);
			t.setStatus(PlanStatus.EDIT);
			saveTasks(t, tasks);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(plan, t);
				t.setPlanEndDate(tasks.get(tasks.size() - 1).getEnding());
				planService.saveEntity(t);
			} catch (Exception e) {
				message = "计划更新失败";
			}
		} else {
			message = "计划添加成功";
			plan.setStatus(PlanStatus.EDIT);
			plan.setInputUser(SystemUtils.getSessionUser().getStaffcode());
			plan.setDepartId(SystemUtils.getSessionUser().getOrgCode());
			plan.setInputDate(new Date());
			plan.setSteps(tasks);
			plan.setPlanEndDate(tasks.get(tasks.size() - 1).getEnding());
			for(PlanStep step : tasks){
				step.setPlan(plan);
			}
			planService.addEntity(plan);
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="del")
	@ResponseBody
	public void del(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			message = "数据不合法";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		planService.removeEntities(ids, Plan.class);
		message = "计划删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="foraudit")
	@ResponseBody
	public void foraudit(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		
		Plan t = planService.findEntityById(Long.parseLong(id), Plan.class);
		t.setStatus(PlanStatus.WAITTING_FOR_AUDIT);
		planService.saveEntity(t);
		message = "已提交审核";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	private void saveTasks(Plan plan, List<PlanStep> tasks) {
		for(PlanStep task : tasks){
			if(task.getId() != null){
				PlanStep step = planService.findEntityById(task.getId(), PlanStep.class);
				MyBeanUtils.copyBeanNotNull2Bean(task, step);
				planService.saveEntity(step);
			} else {
				task.setPlan(plan);
				planService.addEntity(task);
			}
		}
	}
	
	@RequestMapping(params="dgview_dpt_audit")
	public String dgViewDptAudit(HttpServletRequest request) {
		return "plan_management/plan_dpt_audit_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_dpt_audit")
	@ResponseBody
	public void dgDataDptAudit(Plan plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Plan> cq = new QueryDescriptor<Plan>(Plan.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<Plan> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction("status", "=", PlanStatus.WAITTING_FOR_AUDIT);
		
		boolean noperm = true;
		// 科长审本部门的岗位计划，办公室负责人（主任）审所有部门的
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		try {
			String sql = "select p.positionname from base_orgmember m join base_orgposition p USING(positioncode) where m.orgcode=? and m.staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(user.getOrgCode()), new Parameter.String(user.getStaffcode()) };
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql, pp);
			List<String> tmps = new ArrayList<String>();
			if (dt != null && dt.getRowsCount() > 0) {
				for(int i=0; i<dt.getRowsCount(); i++){
					tmps.add(dt.get(i).getString("positionname"));
				}
			}
			
			if(tmps.contains("主任")) {
				tqBuilder.addRestriction("type", "=", (short)1);
				noperm = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(noperm) {
			try {
				response.getWriter().write("没有权限");
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			cq.setTqBuilder(tqBuilder);
			commonService.getDataGridReturn(cq, true);
			TagUtil.datagrid(response, dg);
		}
	}
	
	
	@RequestMapping(params="dgview_user_audit")
	public String dgViewUserAudit(HttpServletRequest request) {
		return "plan_management/plan_user_audit_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_user_audit")
	@ResponseBody
	public void dgDataUserAudit(Plan plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Plan> cq = new QueryDescriptor<Plan>(Plan.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<Plan> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction("status", "=", PlanStatus.WAITTING_FOR_AUDIT);
		
		boolean noperm = true;
		// 科长审本部门的岗位计划，办公室负责人（主任）审所有部门的
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		try {
			String sql = "select p.positionname from base_orgmember m join base_orgposition p USING(positioncode) where m.orgcode=? and m.staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(user.getOrgCode()), new Parameter.String(user.getStaffcode()) };
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql, pp);
			List<String> tmps = new ArrayList<String>();
			if (dt != null && dt.getRowsCount() > 0) {
				for(int i=0; i<dt.getRowsCount(); i++){
					tmps.add(dt.get(i).getString("positionname"));
				}
			}
			
			if((tmps.contains("科长") || tmps.contains("主任")) && !"副科长".equals(tmps)) {
				tqBuilder.addRestriction("type", "=", (short)0);
				tqBuilder.addRestriction("departId", "=", user.getOrgCode());
				noperm = false;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(noperm) {
			try {
				response.getWriter().write("没有权限");
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			cq.setTqBuilder(tqBuilder);
			commonService.getDataGridReturn(cq, true);
			TagUtil.datagrid(response, dg);
		}
	}
	
	@RequestMapping(params="audit")
	@ResponseBody
	public void audit(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			message = "数据不合法";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		boolean isPassed = request.getParameter("pass").equals("true") ? true : false;
		for(Long tmp : ids) {
			planService.auditAndRunPlan(tmp, isPassed);
		}
		
		message = "计划已审核";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="dgview_exec")
	public String dgViewExec(HttpServletRequest request) {
		return "plan_management/plan_exec_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_exec")
	@ResponseBody
	public void dgDataExec(Plan plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Plan> cq = new QueryDescriptor<Plan>(Plan.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<Plan> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction("status", "in", Arrays.asList(new Short[]{PlanStatus.AUDIT_PASS, PlanStatus.EXECUTTING, PlanStatus.EXEC_FINISHING , PlanStatus.EXEC_EXCEPTION}));
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="exec")
	@ResponseBody
	public void exec(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			message = "数据不合法";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		for(Long tmp : ids) {
			planService.runPlan(tmp);
		}
		
		message = "计划开始执行";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="exec_view")
	public String exec_view(Long id, HttpServletRequest request, Model model) {
		
		PlanInstance instance = null;
		if(StringUtils.isNotEmpty(request.getParameter("type")) && "taskid".equals(request.getParameter("type")) ){
			instance = planService.findPlanInstanceByTaskId(id);
		} else {
			instance = planService.findPlanInstanceByPlanId(id);
		}
		Map<PlanStep, List<PlanTask>> tasks = planService.findPlanTasks(instance);
		model.addAttribute("taskList", tasks);
		
		return "plan_management/plan_exec_view";
	}
	
	@RequestMapping(params="dgview_supervise")
	public String dgViewSupervise(HttpServletRequest request) {
		return "plan_management/plan_supervise_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_supervise")
	@ResponseBody
	public void dgDataSupervise(Plan plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Plan> cq = new QueryDescriptor<Plan>(Plan.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<Plan> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction("status", "in", Arrays.asList(new Short[]{PlanStatus.EXECUTTING, PlanStatus.EXEC_FINISHING}));
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="sel_flow_view")
	public String selFlowView(Long id, String fixFlowKey, HttpServletRequest request, Model model) {
		model.addAttribute("planid", id);
		ProcessEngine processEngine = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext())
				.getBean("processEngine", ProcessEngine.class);
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().processDefinitionKey(fixFlowKey).list();
		model.addAttribute("instances", instances);
		
		return "plan_management/sel_flow_instance";
	}
	
	@RequestMapping(params="plan_review")
	public String plan_review(Long id, HttpServletRequest request, Model model) {
		return "plan_management/plan_review_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_review")
	@ResponseBody
	public void dgDataReview(PlanInstance plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<PlanInstance> cq = new QueryDescriptor<PlanInstance>(PlanInstance.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<PlanInstance> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction("status", "in", Arrays.asList(new Short[]{PlanInstance.FINISHED}));
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="user_review_post")
	@ResponseBody
	public void user_review_post(Long id, String result, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		
		if(!"10".equals(result) && !"0".equals(result) && !"-10".equals(result)){
			message = "参数不正确";
		} else {
			planService.userReview(id, result);
			message = "评价结果已更新";
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="dpt_review_post")
	@ResponseBody
	public void dpt_review_post(Long id, String result, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		
		if(!"10".equals(result) && !"0".equals(result) && !"-10".equals(result)){
			message = "参数不正确";
		} else {
			planService.dptReview(id, result);
			message = "评价结果已更新";
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="review")
	public String review(Long id, HttpServletRequest request, Model model) {
		
		Map<PlanStep, List<PlanTask>> tasks = planService.findAllTasksByPlanId(id);
		model.addAttribute("tasks", tasks);
		
		return "plan_management/plan_review";
	}
	
	@RequestMapping(params="user_review")
	public String user_review(Long id, HttpServletRequest request, Model model) {
		return "plan_management/user_review";
	}
	
	@RequestMapping(params="users")
	public String user_review(String year, String month, HttpServletRequest request, Model model) {
		return "plan_management/user_review_list";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_user_review")
	@ResponseBody
	public void dgdata_user_review(UserReview plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		
		String month = request.getParameter("m");
		int y = Integer.parseInt(request.getParameter("y"));
		int m = Integer.parseInt(month.charAt(1) == '0' ? month.substring(2) : month.substring(1)) - 1;
		
		QueryDescriptor<UserReview> cq = new QueryDescriptor<UserReview>(UserReview.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<UserReview> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction("month", "=", (short)m);
		tqBuilder.addRestriction("year", "=", (short)y);
		
		boolean noperm = true;
		// 科长审本部门的岗位计划，办公室负责人（主任）审所有部门的
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		try {
			String sql = "select p.positionname from base_orgmember m join base_orgposition p USING(positioncode) where m.orgcode=? and m.staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(user.getOrgCode()), new Parameter.String(user.getStaffcode()) };
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql, pp);
			List<String> tmps = new ArrayList<String>();
			if (dt != null && dt.getRowsCount() > 0) {
				for(int i=0; i<dt.getRowsCount(); i++){
					tmps.add(dt.get(i).getString("positionname"));
				}
			}
			
			if((tmps.contains("科长") || tmps.contains("主任")) && !"副科长".equals(tmps)) {
				tqBuilder.addRestriction("departId", "=", user.getOrgCode());
				noperm = false;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(noperm) {
			try {
				response.getWriter().write("没有权限");
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			cq.setTqBuilder(tqBuilder);
			commonService.getDataGridReturn(cq, true);
			TagUtil.datagrid(response, dg);
		}
	}
	
	
	@RequestMapping(params="dpt_review")
	public String dpt_review(HttpServletRequest request, Model model) {
		return "plan_management/dpt_review";
	}
	
	@RequestMapping(params="dpts")
	public String dpts(String year, String month, HttpServletRequest request, Model model) {
		return "plan_management/dpt_review_list";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_dpt_review")
	@ResponseBody
	public void dgdata_dpt_review(DptReview plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		String month = request.getParameter("m");
		int y = Integer.parseInt(request.getParameter("y"));
		int m = Integer.parseInt(month.charAt(1) == '0' ? month.substring(2) : month.substring(1)) - 1;
		
		QueryDescriptor<DptReview> cq = new QueryDescriptor<DptReview>(DptReview.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<DptReview> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction("month", "=", (short)m);
		tqBuilder.addRestriction("year", "=", (short)y);
		
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		try {
			String sql = "select orgcode from tbm_admindpt where staffcode=? and adminmode='工作'";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(user.getStaffcode()) };
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql, pp);
			List<String> tmps = new ArrayList<String>();
			if (dt != null && dt.getRowsCount() > 0) {
				for(int i=0; i<dt.getRowsCount(); i++){
					tmps.add(dt.get(i).getString("orgcode"));
				}
			}
			
			tqBuilder.addRestriction("orgCode", "in", tmps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
}
