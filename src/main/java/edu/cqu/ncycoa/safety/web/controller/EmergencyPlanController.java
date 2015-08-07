package edu.cqu.ncycoa.safety.web.controller;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import edu.cqu.ncycoa.safety.domain.EmergencyPlan;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;
@Controller
@RequestMapping("/emergencyplan_management")
public class EmergencyPlanController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "safetyedu_management/emergencyplan";
	}
	
	@RequestMapping(params="add_b")
	public String addb(HttpServletRequest request) {
		return "safetyedu_management/emergencyplanb";
	}
	
	@RequestMapping(params="add_c")
	public String addc(HttpServletRequest request) {
		return "safetyedu_management/emergencyplanc";
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
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		systemService.removeEntities(ids, EmergencyPlan.class);
		message = "记录删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		EmergencyPlan safeConductMaterial = systemService.findEntityById(Long.parseLong(id), EmergencyPlan.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safetyedu_management/emergencyplan");
		mav.addObject("ep",safeConductMaterial);
		return mav;
	}
	@RequestMapping(params="update_b")
    public ModelAndView updateb(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		EmergencyPlan safeConductMaterial = systemService.findEntityById(Long.parseLong(id), EmergencyPlan.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safetyedu_management/emergencyplanb");
		mav.addObject("ep",safeConductMaterial);
		return mav;
	}
	@RequestMapping(params="update_c")
    public ModelAndView updatec(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		EmergencyPlan safeConductMaterial = systemService.findEntityById(Long.parseLong(id), EmergencyPlan.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safetyedu_management/emergencyplanc");
		mav.addObject("ep",safeConductMaterial);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(EmergencyPlan safeConductMaterial, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (safeConductMaterial.getId() != null) {
			message = "更新成功";
			EmergencyPlan t = systemService.findEntityById(safeConductMaterial.getId(), EmergencyPlan.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(safeConductMaterial, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "添加成功";
			
			systemService.addEntity(safeConductMaterial);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		return "safetyedu_management/emergencyplanlist";		
	}
	
	@RequestMapping(params="dgviewb")
	public String dgViewb(HttpServletRequest request) {
		return "safetyedu_management/emergencyplanblist";		
	}
	
	@RequestMapping(params="dgviewc")
	public String dgViewc(HttpServletRequest request) {
		return "safetyedu_management/emergencyplanclist";		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(EmergencyPlan safeConductMaterial, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<EmergencyPlan> cq = new QueryDescriptor<EmergencyPlan>(EmergencyPlan.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<EmergencyPlan> tqBuilder = QueryUtils.getTQBuilder(safeConductMaterial, request.getParameterMap());
		tqBuilder.addRestriction(new TQRestriction( "type", "in", Arrays.asList(new Short((short)0))));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_b")
	@ResponseBody
	public void dgDatab(EmergencyPlan safeConductMaterial, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<EmergencyPlan> cq = new QueryDescriptor<EmergencyPlan>(EmergencyPlan.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<EmergencyPlan> tqBuilder = QueryUtils.getTQBuilder(safeConductMaterial, request.getParameterMap());
		tqBuilder.addRestriction(new TQRestriction( "type", "in", Arrays.asList(new Short((short)1))));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_c")
	@ResponseBody
	public void dgDatac(EmergencyPlan safeConductMaterial, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<EmergencyPlan> cq = new QueryDescriptor<EmergencyPlan>(EmergencyPlan.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<EmergencyPlan> tqBuilder = QueryUtils.getTQBuilder(safeConductMaterial, request.getParameterMap());
		tqBuilder.addRestriction(new TQRestriction( "type", "in", Arrays.asList(new Short((short)2))));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
