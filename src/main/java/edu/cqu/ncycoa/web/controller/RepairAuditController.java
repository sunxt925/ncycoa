package edu.cqu.ncycoa.web.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.Format;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.RepairAudit;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/repair_management")
public class RepairAuditController {

	@Resource(name="systemService")
	SystemService systemService;
	
	@Resource(name="wfRtService")
	RuntimeService runtimeService;
	
	@Resource(name="wfRepoService")
	RepositoryService repositoryService;
	
	@Resource(name="wfTaskService")
	TaskService taskService;
	
	@Resource(name="wfHistoryService")
	HistoryService historyService;
	
	@Resource(name="wfManagementService")
	ManagementService managementService;
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "repair_management/repair";
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
		
		systemService.removeEntities(ids, RepairAudit.class);
		message = "维修申请删除成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(RepairAudit repairAudit, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (repairAudit.getId() != null) {
			message = "维修申请更新成功";
			RepairAudit t = systemService.findEntityById(repairAudit.getId(), RepairAudit.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(repairAudit, t);
				systemService.saveEntity(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				message = "维修申请更新失败";
			}
		} else {
			message = "维修申请添加成功";
		    repairAudit.setAppDate(Format.strToDate(Format.getNowtime()));
		    repairAudit.setAuditFlag("0");
			systemService.addEntity(repairAudit);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		RepairAudit repairAudit = systemService.findEntityById(Long.parseLong(id), RepairAudit.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("repair_management/repair");
		mav.addObject("repairAudit",repairAudit);
		return mav;
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		return "repair_management/repairlist";
	}
	
	@RequestMapping(params="officedgview")
	public String officedgView(HttpServletRequest request) {
		return "repair_management/repair_office_audit_list";
	}
	
	@RequestMapping(params="directordgview")
	public String directordgView(HttpServletRequest request) {
		return "repair_management/repair_director_audit_list";
	}
	
	@RequestMapping(params="auditresdgview")
	public String auditresdgView(HttpServletRequest request) {
		return "repair_management/repairreslist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(RepairAudit repairAudit, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<RepairAudit> cq = new QueryDescriptor<RepairAudit>(RepairAudit.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<RepairAudit> tqBuilder = QueryUtils.getTQBuilder(repairAudit, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="dgview_audit")
	public String dgViewAudit(HttpServletRequest request) {
		return "repair_management/repair_audit_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_audit")
	@ResponseBody
	public void dgDataAudit(RepairAudit repairAudit, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<RepairAudit> cq = new QueryDescriptor<RepairAudit>(RepairAudit.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<RepairAudit> tqBuilder = QueryUtils.getTQBuilder(repairAudit, request.getParameterMap());
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
		
		RepairAudit repairAudit;
		for(Long tmp : ids) {
			repairAudit = systemService.findEntityById(tmp, RepairAudit.class);
			
			//办公室审核
			if(request.getParameter("auditor").equals("office")){
				if(request.getParameter("res").equals("yes")){
					repairAudit.setAuditFlag("11");
				}else{
					repairAudit.setAuditFlag("10");
				}
			}
			//局长审核
			if(request.getParameter("auditor").equals("director")){
				if(request.getParameter("res").equals("yes")){
					repairAudit.setAuditFlag("21");
				}else{
					repairAudit.setAuditFlag("20");
				}
			}
			systemService.saveEntity(repairAudit);
			systemService.addLog("维修申请审核", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		
		message = "审核完成";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params = "repairAudit")
	@ResponseBody
	public void repairAudit(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message = "";
		String id = request.getParameter("id");
		try {
			String processID = RepairAudit.class.getSimpleName();
			Map<String, Object> paras =new HashMap<String, Object>();
			paras.put("inputUser", "001");
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processID, paras);
			
			message = "维修申请提交成功";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
}
