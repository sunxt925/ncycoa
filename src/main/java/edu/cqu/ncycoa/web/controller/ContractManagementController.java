package edu.cqu.ncycoa.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
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
import com.entity.index.AllMeritCollection;
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
import edu.cqu.ncycoa.domain.ActivityComment;
import edu.cqu.ncycoa.domain.ContractInfo;
import edu.cqu.ncycoa.domain.ContractQuery;
import edu.cqu.ncycoa.plan.domain.Plan;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.ExportExcel;
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
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		String type = request.getParameter("type"); 
		ContractInfo contractInfo = systemService.findEntityById(Long.parseLong(id), ContractInfo.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contract_management/contract");
		mav.addObject("contract",contractInfo);
		mav.addObject("relevantDepartment_disp",CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname", contractInfo.getRelevantDepartment()));
		if(null==type || type.equals("")){
			type = contractInfo.getType();
		}
		mav.addObject("type",type);
		return mav;
	}
	
	@RequestMapping(params = "commit")
	@ResponseBody
	public void commit(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		String type=request.getParameter("type");
		
		ContractInfo contractInfo = systemService.findEntityById(Long.parseLong(id), ContractInfo.class);
		
		
		
		
		
		String processID = ContractInfo.class.getSimpleName();
		String objId = processID + ":" + id;
		Map<String, Object> paras =new HashMap<String, Object>();
		UserInfo userinfo = (UserInfo)request.getSession().getAttribute("UserInfo");
		
		paras.put("inputUser", userinfo.getStaffcode());
		
		String companycode = AllMeritCollection.getcompanyByobject(userinfo.getOrgCode());
		
		
		if(Integer.parseInt((companycode.split("\\.")[2])) >= 20){
			//区县
			paras.put("chief", UnitDao.getComanyAudit(companycode));
		}else{
			//市局
			paras.put("chief", UnitDao.getCityComanyAudit(userinfo.getOrgCode()));
		}
        if(type.equals("0")){//固定流程
        	if(contractInfo.getType().equals("0")){
    			//企管、法规、审计、内管、财务、安管科
    			//02,04,05,06,10,11
    			List<String> auditorg = new ArrayList<String>();
    			auditorg.add("NC.01.02");
    			auditorg.add("NC.01.05");
    			auditorg.add("NC.01.06");
    			auditorg.add("NC.01.10");
    			auditorg.add("NC.01.11");
    			auditorg.remove(userinfo.getOrgCode());
    			paras.put("finallyauditGroup", UnitDao.getManyComanyAudit(auditorg));
    		}else if(contractInfo.getType().equals("1")||contractInfo.getType().equals("3")){
    			//企管、法规、审计、内管、财务部
    			//02,04,05,06,11
    			List<String> auditorg = new ArrayList<String>();
    			auditorg.add("NC.01.02");
    			auditorg.add("NC.01.05");
    			auditorg.add("NC.01.06");
    			auditorg.add("NC.01.11");
    			auditorg.remove(userinfo.getOrgCode());
    			paras.put("finallyauditGroup", UnitDao.getManyComanyAudit(auditorg));
    		}else if(contractInfo.getType().equals("2")){
    			//办公室、法规、财务、审计、企管、内管
    			//01,02,04,05,06,11
    			List<String> auditorg = new ArrayList<String>();
    			auditorg.add("NC.01.01");
    			auditorg.add("NC.01.02");
    			auditorg.add("NC.01.05");
    			auditorg.add("NC.01.06");
    			auditorg.add("NC.01.11");
    			auditorg.remove(userinfo.getOrgCode());
    			paras.put("finallyauditGroup", UnitDao.getManyComanyAudit(auditorg));
    		}
		}else{//自定义
			String[] participants = request.getParameter("participants").split(",");
			List<String> auditor = new ArrayList<String>();
			for(String s: participants){
				if(!s.equals("")){
					auditor.add(s);
				}
			}
			paras.put("finallyauditGroup", auditor);
		}
        paras.put("fgchief", UnitDao.getCityComanyAudit("NC.01.04"));
		
		
		
		//1151130100140446,1151130100140040,1151130100140051,1151130100140070,1151130100140005
		
		

		
		processEngine.getRuntimeService().startProcessInstanceByKey(processID, objId, paras);
		String processinstanceid = processEngine.getRuntimeService().
				createProcessInstanceQuery().processInstanceBusinessKey(objId,processID).list().get(0).getProcessInstanceId();
		if(Integer.parseInt((companycode.split("\\.")[2])) >= 20){
			//区县
			contractInfo.setAppDepart(companycode);
		}else{
			//市局
			contractInfo.setAppDepart(userinfo.getOrgCode());
		}
		
		contractInfo.setStatus((short)1);//修改提交状态
		contractInfo.setProcessInstanceId(processinstanceid);//存入流程实例号
		systemService.saveEntity(contractInfo);
		IdentityService identityService=processEngine.getIdentityService();
		identityService.setAuthenticatedUserId(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().taskAssignee(userinfo.getStaffcode()).processInstanceId(processinstanceid).orderByDueDate().desc().list();
		processEngine.getTaskService().complete(tasks.get(0).getId());
		
		if(type.equals("1")){
			List<Task> task_tmp = processEngine.getTaskService().createTaskQuery().taskAssignee(userinfo.getStaffcode()).processInstanceId(processinstanceid).orderByDueDate().desc().list();
			Map<String, Object> paras_tmp =new HashMap<String, Object>();
			paras_tmp.put("outcome", true);
			processEngine.getTaskService().complete(task_tmp.get(0).getId(),paras_tmp);
		}
		
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
		UserInfo userinfo = (UserInfo)request.getSession().getAttribute("UserInfo");
		List<Task> groupTaskList=processEngine.getTaskService().createTaskQuery().taskAssignee(userinfo.getStaffcode()).orderByTaskCreateTime().desc().list();
		Task task = groupTaskList.get(0);
		//Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		String processDefId = task.getProcessDefinitionId();
		String pi = task.getProcessInstanceId();
		
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)processEngine.getRepositoryService().getProcessDefinition(processDefId);
		
		ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(pi)
	              .singleResult();
	   
		
	  /*  String activityId = processInstance.getActivityId();
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		
		*//**
		 * 获取下一个当前活动后面的Sequence line
		 *//*
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
		}*/
		
		
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
		List<ActivityComment> accomment = new ArrayList<ActivityComment>();
		
		for(Comment comment : comments){
			ActivityComment activityComment = new ActivityComment();
			activityComment.setTime(Format.dateToStr(comment.getTime()));
			activityComment.setUsername(CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comment.getUserId()));
			activityComment.setMsg(comment.getFullMessage());
			accomment.add(activityComment);
			
		}
		mav.addObject("contract",contractInfo);
		mav.addObject("outcomelist", list);
		mav.addObject("taskId",taskId);
		mav.addObject("comments", accomment);
		return mav;
	}
	
	@RequestMapping(params = "exetask")
	@ResponseBody
	public void exetask(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		AjaxResultJson j = new AjaxResultJson();
		String message = "";
		String id = request.getParameter("id");
		ContractInfo contractInfo = systemService.findEntityById(Long.parseLong(id), ContractInfo.class);
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
		
		boolean flag = true;
		List<ProcessInstance> processInstances = processEngine.getRuntimeService().createProcessInstanceQuery().list();

		for(ProcessInstance processInstance : processInstances){
			if(processInstance.getProcessInstanceId().equals(contractInfo.getProcessInstanceId())){
				flag = false;
			}
		}
		
		if(flag){
			contractInfo.setStatus((short)2);
			systemService.saveEntity(contractInfo);
		}
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
				File f = new File(Util.getfileCfg().get("uploadfilepath")+tempfilename+".doc");
				if (!f.getParentFile().exists())
					f.getParentFile().mkdirs();

				if(contractInfo.getType().equals("0")){
					WordUtils.produceWord(Util.getfileCfg().get("uploadfilepath")+tempfilename+".doc", Util.getFilepath("wordtemplate/contractaudit_template_0.doc"), getCommentMap(contractInfo,comments));
				}else if(contractInfo.getType().equals("1")||contractInfo.getType().equals("3")){
					WordUtils.produceWord(Util.getfileCfg().get("uploadfilepath")+tempfilename+".doc", Util.getFilepath("wordtemplate/contractaudit_template_1.doc"), getCommentMap(contractInfo,comments));
				}else if(contractInfo.getType().equals("2")){
					WordUtils.produceWord(Util.getfileCfg().get("uploadfilepath")+tempfilename+".doc", Util.getFilepath("wordtemplate/contractaudit_template_2.doc"), getCommentMap(contractInfo,comments));
				}
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
		Map<String, String> methods = new HashMap<String, String>();
		methods.put("0", "公开招标");
		methods.put("1", "邀请招标");
		methods.put("2", "竞争性谈判");
		methods.put("3", "询价");
		methods.put("4", "单一来源");
		Calendar c = Calendar.getInstance();
		c.setTime(contractInfo.getAppDate());
		map.put("y0", c.get(Calendar.YEAR)+"");
		map.put("m0", (c.get(Calendar.MONTH)+1)+"");
		map.put("d0", c.get(Calendar.DAY_OF_MONTH)+"");
		map.put("projectname", Format.NullToBlank(contractInfo.getName()));
		map.put("relevantDepartment", CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname", Format.NullToBlank(contractInfo.getRelevantDepartment())));
		if(null!=contractInfo.getContactMethod()&&!contractInfo.getContactMethod().equals("")){
			map.put("contactMethod", methods.get(contractInfo.getContactMethod()));
		}else{
			map.put("contactMethod", "");
		}
		
		map.put("contractValue", contractInfo.getContractValue()+"");
		map.put("partyB", Format.NullToBlank(contractInfo.getPartyB()));
		map.put("content", contractInfo.getContent());
		
	
		String comm6 = "";
		
		if(contractInfo.getType().equals("0")){
			comm6 = "NC.01.10";
		}else if(contractInfo.getType().equals("1")||contractInfo.getType().equals("3")){
			comm6 = "NC.01.02";
		}else if(contractInfo.getType().equals("2")){
			comm6 = "NC.01.02";
		}
		for(Comment comment : comments){

			if(comment.getUserId().equals(UnitDao.getCityComanyAudit(contractInfo.getAppDepart()))){
				Calendar c_f = Calendar.getInstance();
				c_f.setTime(comment.getTime());
				map.put("comment1", comment.getFullMessage());
				map.put("chief1", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comment.getUserId()) );
				map.put("y1", c_f.get(Calendar.YEAR)+"");
				map.put("m1", (c_f.get(Calendar.MONTH)+1)+"");
				map.put("d1", c_f.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(comment.getUserId().equals(UnitDao.getCityComanyAudit(contractInfo.getRelevantDepartment()))){
				Calendar c_f = Calendar.getInstance();
				c_f.setTime(comment.getTime());
				map.put("comment2", comment.getFullMessage());
				map.put("chief2", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comment.getUserId()) );
				map.put("y2", c_f.get(Calendar.YEAR)+"");
				map.put("m2", (c_f.get(Calendar.MONTH)+1)+"");
				map.put("d2", c_f.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(comment.getUserId().equals(UnitDao.getCityComanyAudit("NC.01.11"))){
				Calendar c_f = Calendar.getInstance();
				c_f.setTime(comment.getTime());
				map.put("comment3", comment.getFullMessage());
				map.put("chief3", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comment.getUserId()) );
				map.put("y3", c_f.get(Calendar.YEAR)+"");
				map.put("m3", (c_f.get(Calendar.MONTH)+1)+"");
				map.put("d3", c_f.get(Calendar.DAY_OF_MONTH)+"");
			}
			
			if(comment.getUserId().equals(UnitDao.getCityComanyAudit("NC.01.05"))){
				Calendar c_f = Calendar.getInstance();
				c_f.setTime(comment.getTime());
				map.put("comment4", comment.getFullMessage());
				map.put("chief4", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comment.getUserId()) );
				map.put("y4", c_f.get(Calendar.YEAR)+"");
				map.put("m4", (c_f.get(Calendar.MONTH)+1)+"");
				map.put("d4", c_f.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(comment.getUserId().equals(UnitDao.getCityComanyAudit("NC.01.06"))){
				Calendar c_f = Calendar.getInstance();
				c_f.setTime(comment.getTime());
				map.put("comment5", comment.getFullMessage());
				map.put("chief5", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comment.getUserId()) );
				map.put("y5", c_f.get(Calendar.YEAR)+"");
				map.put("m5", (c_f.get(Calendar.MONTH)+1)+"");
				map.put("d5", c_f.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(comment.getUserId().equals(UnitDao.getCityComanyAudit(comm6))){
				Calendar c_f = Calendar.getInstance();
				c_f.setTime(comment.getTime());
				map.put("comment6", comment.getFullMessage());
				map.put("chief6", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comment.getUserId()) );
				map.put("y6", c_f.get(Calendar.YEAR)+"");
				map.put("m6", (c_f.get(Calendar.MONTH)+1)+"");
				map.put("d6", c_f.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(comment.getUserId().equals(UnitDao.getCityComanyAudit("NC.01.04"))){
				Calendar c_f = Calendar.getInstance();
				c_f.setTime(comment.getTime());
				map.put("comment7", comment.getFullMessage());
				map.put("chief7", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", comment.getUserId()) );
				map.put("y7", c_f.get(Calendar.YEAR)+"");
				map.put("m7", (c_f.get(Calendar.MONTH)+1)+"");
				map.put("d7", c_f.get(Calendar.DAY_OF_MONTH)+"");
			}
		}
		
	
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
	@RequestMapping(params="dgallview")
	public ModelAndView dgAllView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contract_management/allcontractlist");
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
		if(null!=type&&!type.equals("")){
			tqBuilder.addRestriction(new TQRestriction("type", "in",type));
		}
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
	@RequestMapping(params="exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		response.setCharacterEncoding("utf-8");  
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
		
		//查询条件组装器
		TypedQueryBuilder<ContractInfo> tqBuilder = new TypedQueryBuilder<ContractInfo>(ContractInfo.class,"e");
		if(null!=sDate && !sDate.equals("")){
			tqBuilder.addRestriction(new TQRestriction("signingDate", ">=",DATE.parse(sDate)));
		}
		if(null!=eDate && !eDate.equals("")){
		tqBuilder.addRestriction(new TQRestriction("signingDate", "<=",DATE.parse(eDate)));
		}
		List<ContractInfo> contractInfos = systemService.getQueryRes(tqBuilder);
	    response.setContentType("multipart/form-data"); 
	    response.setHeader("Content-Disposition", "attachment;fileName="+Util.getName()+".xls");  
	    OutputStream os=response.getOutputStream();  
		try {
			
			String[] headers = {"合同编码","合同名称","合同类别","实施部门","实施方式","甲方","乙方","合同标的","合同金额","签订日期","执行情况","执行金额","完成日期","续签情况"}; 
			ExportExcel<ContractQuery> excel = new ExportExcel<ContractQuery>();
			excel.exportExcel(headers, ContractQuery.getContractQuery(contractInfos), os);
		} finally{
			if(os!=null){
				os.close();
			}
		}
		
	}
}
