package edu.cqu.ncycoa.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.CodeDictionary;
import com.common.Format;
import com.common.Util;
import com.common.WordUtils;
import com.dao.system.UnitDao;
import com.entity.system.UserInfo;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TQRestriction;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.ContractInfo;
import edu.cqu.ncycoa.plan.domain.Plan;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/contract-management")
public class ContractManagementController {
	
	@Resource(name="systemService")
	SystemService systemService;
	
	@Resource(name="processEngine")
	ProcessEngine processEngine;
	
	@RequestMapping(params="add")
	public ModelAndView add(HttpServletRequest request) {
		String type = request.getParameter("type");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contract_management/contract");
		mav.addObject("type",type);
		return mav;
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
		
		systemService.removeEntities(ids, ContractInfo.class);
		message = "合同删除成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(ContractInfo contract, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (contract.getId() != null) {
			message = "合同更新成功";
			ContractInfo t = systemService.findEntityById(contract.getId(), ContractInfo.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(contract, t);
				systemService.saveEntity(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				message = "合同更新失败";
			}
		} else {
			message = "合同添加成功";
			contract.setStatus((short)0);
			contract.setAppDate(new Date());
			systemService.addEntity(contract);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params = "commit")
	@ResponseBody
	public void commit(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		
		ContractInfo contractInfo = systemService.findEntityById(Long.parseLong(id), ContractInfo.class);
		
		
		
		
		
		String processID = ContractInfo.class.getSimpleName();
		String objId = processID + ":" + id;
		Map<String, Object> paras =new HashMap<String, Object>();
		paras.put("inputUser", ((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
		
		if(contractInfo.getType().equals("0")){
			paras.put("chief", UnitDao.getComAdminChief());
		}else if(contractInfo.getType().equals("1")){
			paras.put("chief", UnitDao.getOfficeAudit());
		}else if(contractInfo.getType().equals("2")){
			paras.put("chief", UnitDao.getOfficeAudit());
		}
		
		
		paras.put("finallyaudit", UnitDao.getCityAudit());
		
		processEngine.getRuntimeService().startProcessInstanceByKey(processID, objId, paras);
		String processinstanceid = processEngine.getRuntimeService().
				createProcessInstanceQuery().processInstanceBusinessKey(objId,processID).list().get(0).getProcessInstanceId();
		contractInfo.setStatus((short)1);//修改提交状态
		contractInfo.setProcessInstanceId(processinstanceid);//存入流程实例号
		systemService.saveEntity(contractInfo);
		IdentityService identityService=processEngine.getIdentityService();
		identityService.setAuthenticatedUserId(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
       
        message = "审核完成";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	@RequestMapping(params="submitTask")
	public ModelAndView submitTask(HttpServletRequest request, HttpServletResponse response) {
		List<String> list = new ArrayList<String>();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contract_management/contractaudit");
		String taskId = request.getParameter("taskId");
		

		Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		String processDefId = task.getProcessDefinitionId();
		String pi = task.getProcessInstanceId();
		
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)processEngine.getRepositoryService().getProcessDefinition(processDefId);
		
		ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(pi)
	              .singleResult();
	   
		
		String activityId = processInstance.getActivityId();
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		
		/**
		 * 获取下一个当前活动后面的Sequence line
		 */
		List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
		
		if(pvmTransitions != null && pvmTransitions.size() > 0){
			for(PvmTransition pvmTransition : pvmTransitions){
				String name = (String)pvmTransition.getProperty("name");
				if(StringUtils.isNotBlank(name)){
					list.add(name);
				}else{
					list.add("默认提交");
				}
			}
		}
		
		
		String contractId = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(pi)
		              .singleResult().getBusinessKey().split(":")[1];
		ContractInfo contractInfo = systemService.findEntityById(Long.parseLong(contractId), ContractInfo.class);
		
		
		/**
		 * 获取历史批注
		 */
		List<Comment> comments = new ArrayList<Comment>();
		List<HistoricTaskInstance> historicTaskInstances = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
				                                                         .processInstanceId(pi)
				                                                         .list();
		if(historicTaskInstances !=null && historicTaskInstances.size() > 0){
			for(HistoricTaskInstance historicTaskInstance : historicTaskInstances){
				String htaskId = historicTaskInstance.getId();
				List<Comment> taskList = processEngine.getTaskService().getTaskComments(htaskId);
				comments.addAll(taskList);
			}
		}
		mav.addObject("contract",contractInfo);
		mav.addObject("outcomelist", list);
		mav.addObject("taskId",taskId);
		mav.addObject("comments", comments);
		return mav;
	}
	
	@RequestMapping(params = "exetask")
	@ResponseBody
	public void exetask(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		AjaxResultJson j = new AjaxResultJson();
		String message = "";
		String taskId = request.getParameter("taskId");
		String outcome = request.getParameter("outcome"); 
		
		String comment = request.getParameter("comment");
		  
		TaskService taskService = processEngine.getTaskService();
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String pi = task.getProcessInstanceId();
		//添加审核人
		Authentication.setAuthenticatedUserId(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
		taskService.addComment(taskId, pi, comment);//增加批准信息
		
		Map<String, Object> paras =new HashMap<String, Object>();
		paras.put("outcome", outcome);
		
		taskService.complete(taskId,paras);
		try {
			message = "合同审批提交成功";
			
		} catch (Exception e) {
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	@RequestMapping(params = "produceContract")
	@ResponseBody
	public void produceContract(HttpServletRequest request, HttpServletResponse response) throws IOException{
		AjaxResultJson j = new AjaxResultJson();
		boolean flag = true;
		String message;
		String id = request.getParameter("id");
		
		ContractInfo contractInfo = systemService.findEntityById(Long.parseLong(id), ContractInfo.class);
		if(null !=contractInfo.getAudittable()&&!contractInfo.getAudittable().equals("")){
			message = "审核表已经存在!!!";
		}else{
			List<ProcessInstance> processInstances = processEngine.getRuntimeService().createProcessInstanceQuery().list();

			for(ProcessInstance processInstance : processInstances){
				if(processInstance.getProcessInstanceId().equals(contractInfo.getProcessInstanceId())){
					flag = false;
				}
			}
			
			if(flag){
				/**
				 * 获取历史批注
				 */
				List<Comment> comments = new ArrayList<Comment>();
				List<HistoricTaskInstance> historicTaskInstances = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
						                                                         .processInstanceId(contractInfo.getProcessInstanceId())
						                                                         .list();
				if(historicTaskInstances !=null && historicTaskInstances.size() > 0){
					for(HistoricTaskInstance historicTaskInstance : historicTaskInstances){
						String htaskId = historicTaskInstance.getId();
						List<Comment> taskList = processEngine.getTaskService().getTaskComments(htaskId);
						comments.addAll(taskList);
					}
				}
				String tempfilename = Util.getName();
				WordUtils.produceWord(Util.getfileCfg().get("uploadfilepath")+tempfilename+".doc", Util.getFilepath("wordtemplate/contractaudit_template.doc"), getCommentMap(contractInfo,comments));
				contractInfo.setAudittable(tempfilename+".doc");
				systemService.saveEntity(contractInfo);
			}
			if(flag){
				message = "合同生成成功";
			}else{
				message = "合同还在审核中，无法生成";
			}
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
		
		
		
	}
	
	public Map<String, String> getCommentMap(ContractInfo contractInfo,List<Comment> comments){
		Map<String, String> map = new HashMap<String, String>();
		Calendar c = Calendar.getInstance();
		c.setTime(contractInfo.getAppDate());
		map.put("y0", c.get(Calendar.YEAR)+"");
		map.put("m0", c.get(Calendar.MONTH)+"");
		map.put("d0", c.get(Calendar.DAY_OF_MONTH)+"");
		map.put("projectname", Format.NullToBlank(contractInfo.getName()));
		map.put("relevantDepartment", CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname", Format.NullToBlank(contractInfo.getRelevantDepartment())));
		map.put("contractValue", contractInfo.getContractValue()+"");
		map.put("partyB", Format.NullToBlank(contractInfo.getPartyB()));
		map.put("content", "");
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(comments.get(0).getTime());
		Calendar c2 = Calendar.getInstance();
		c2.setTime(comments.get(0).getTime());
		Calendar c3 = Calendar.getInstance();
		c3.setTime(comments.get(0).getTime());
		
		if(contractInfo.getType().equals("0")){
			map.put("comment1", " ");
			map.put("chief1", " ");
			map.put("y1", " ");
			map.put("m1", " ");
			map.put("d1", " ");
			
			
			map.put("comment2", comments.get(0).getFullMessage());
			map.put("chief2", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comments.get(0).getUserId()));
			map.put("y2", c1.get(Calendar.YEAR)+"");
			map.put("m2", c1.get(Calendar.MONTH)+"");
			map.put("d2", c1.get(Calendar.DAY_OF_MONTH)+"");
		}else{
			map.put("comment1", comments.get(0).getFullMessage());
			map.put("chief1", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comments.get(0).getUserId()));
			map.put("y1", c1.get(Calendar.YEAR)+"");
			map.put("m1", c1.get(Calendar.MONTH)+"");
			map.put("d1", c1.get(Calendar.DAY_OF_MONTH)+"");
			
			
			map.put("comment2", " ");
			map.put("chief2", " ");
			map.put("y2", " ");
			map.put("m2", " ");
			map.put("d2", " ");
		}
		
		
		
		map.put("comment3", " ");
		map.put("chief3", " ");
		map.put("y3", " ");
		map.put("m3", " ");
		map.put("d3", " ");
		
		
		map.put("comment4", " ");
		map.put("chief4", " ");
		map.put("y4", " ");
		map.put("m4", " ");
		map.put("d4", " ");
		
		
		map.put("comment5", " ");
		map.put("chief5", " ");
		map.put("y5", " ");
		map.put("m5", " ");
		map.put("d5", " ");
		
		
		map.put("comment6", comments.get(1).getFullMessage());
		map.put("chief6", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comments.get(1).getUserId()));
		map.put("y6", c2.get(Calendar.YEAR)+"");
		map.put("m6", c2.get(Calendar.MONTH)+"");
		map.put("d6", c2.get(Calendar.DAY_OF_MONTH)+"");
		
		
		map.put("comment7", comments.get(2).getFullMessage());
		map.put("chief7", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comments.get(2).getUserId()));
		map.put("y7", c3.get(Calendar.YEAR)+"");
		map.put("m7", c3.get(Calendar.MONTH)+"");
		map.put("d7", c3.get(Calendar.DAY_OF_MONTH)+"");
		return map;
	}
	
	@RequestMapping(params="dgview")
	public ModelAndView dgView(HttpServletRequest request) {
		String type = request.getParameter("type");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contract_management/contractlist");
		mav.addObject("type",type);
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(ContractInfo contract, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<ContractInfo> cq = new QueryDescriptor<ContractInfo>(ContractInfo.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		String type = request.getParameter("type");
		//查询条件组装器
		TypedQueryBuilder<ContractInfo> tqBuilder = QueryUtils.getTQBuilder(contract, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		tqBuilder.addRestriction(new TQRestriction("type", "in",type));
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="dgview_audit")
	public String dgViewAudit(HttpServletRequest request) {
		return "plan/plan_audit_list";
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
		tqBuilder.addRestriction(tqBuilder.getRootAlias() + ".status", "=", (short)0);
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
		
		Plan plan;
		for(Long tmp : ids) {
			plan = systemService.findEntityById(tmp, Plan.class);
			plan.setStatus((short)1);
			systemService.saveEntity(plan);
			systemService.addLog("计划审核", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		
		message = "审核完成";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
}
