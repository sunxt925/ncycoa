package edu.cqu.ncycoa.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
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
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "contract_management/contract";
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
			systemService.addEntity(contract);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		return "contract_management/contractlist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(ContractInfo contract, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<ContractInfo> cq = new QueryDescriptor<ContractInfo>(ContractInfo.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<ContractInfo> tqBuilder = QueryUtils.getTQBuilder(contract, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
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
	
}
