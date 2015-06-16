package edu.cqu.ncycoa.plan.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.plan.PlanStatus;
import edu.cqu.ncycoa.plan.domain.Plan;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;
import edu.cqu.ncycoa.plan.service.PlanService;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/plan-management")
public class PlanManagementController {
	
	@Resource(name="planService")
	PlanService planService;
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request, Model model) {
		model.addAttribute("isAdmin", true);
		return "plan_management/plan";
	}
	
	@RequestMapping(params="update")
	public String update(Long id, HttpServletRequest request, Model model) {
		Plan plan = planService.findEntityById(id, Plan.class);
		List<PlanStep> steps = planService.findEntitiesByProperty("plan", plan, PlanStep.class);
		model.addAttribute("taskList", steps);
		model.addAttribute("plan", plan);
		model.addAttribute("isAdmin", true);
		return "plan_management/plan";
	}
	
	
	@RequestMapping(params="planstep")
	public String planstep(HttpServletRequest request, Model model) {
		return "plan_management/planstep";
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
		t.setStatus((short)1);
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
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(Plan plan, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		
		String participantList_id = request.getParameter("participantList_id");
		String participantList = request.getParameter("participantList");
		String[] participantList_ids = participantList_id.split(",");
		String[] participantLists = participantList.split(",");
		
		Map<String, String> participants = new HashMap<String, String>();
		for(int i = 0; i < participantList_ids.length; i++){
			participants.put(participantList_ids[i], participantLists[i]);
		}
		plan.setParticipants( participants );
		
		
		String[] taskid = request.getParameter("taskid").split("&");
		String[] taskorder = request.getParameter("taskorder").split("&");
		String[] taskparticipant = request.getParameter("taskparticipant").split("&");
		String[] taskParticipantValue = request.getParameter("taskParticipantValue").split("&");
		String[] tasktype = request.getParameter("tasktype").split("&");
		String[] taskTypeValue = request.getParameter("taskTypeValue").split("&");
		String[] taskcontent = request.getParameter("taskcontent").split("&");
		
		List<PlanStep> tasks = new ArrayList<PlanStep>();
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
			
			task.setType(tasktype[i]);
			task.setTypeValue(Short.parseShort(taskTypeValue[i]));
			task.setContent(taskcontent[i]);
			task.setStatus((short)0);
			tasks.add(task);
		}
		
		
		String message;
		if (plan.getId() != null) {
			message = "计划更新成功";
			
			Plan t = planService.findEntityById(plan.getId(), Plan.class);
			t.setStatus((short)0);
			saveTasks(t,tasks);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(plan, t);
				planService.saveEntity(t);
			} catch (Exception e) {
				message = "计划更新失败";
			}
		} else {
			message = "计划添加成功";
			plan.setStatus((short)0);
			plan.setInputUser(SystemUtils.getSessionUser().getStaffcode());
			plan.setInputDate(new Date());
			plan.setSteps(tasks);
			for(PlanStep step : tasks){
				step.setPlan(plan);
			}
			planService.addEntity(plan);
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
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
	
	@RequestMapping(params="dgview_audit")
	public String dgViewAudit(HttpServletRequest request) {
		return "plan_management/plan_audit_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_audit")
	@ResponseBody
	public void dgDataAudit(Plan plan, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Plan> cq = new QueryDescriptor<Plan>(Plan.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<Plan> tqBuilder = QueryUtils.getTQBuilder(plan, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction("status", "=", (short)1);
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
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
		
		Plan plan;
		for(Long tmp : ids) {
			plan = planService.findEntityById(tmp, Plan.class);
			plan.setStatus(isPassed ? (short)2 : (short)3);
			planService.saveEntity(plan);
		}
		
		message = "审核完成";
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
		PlanInstance instance = planService.findPlanInstanceByPlanId(id);
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
		tqBuilder.addRestriction("status", "in", Arrays.asList(new Short[]{PlanStatus.EXECUTTING}));
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
