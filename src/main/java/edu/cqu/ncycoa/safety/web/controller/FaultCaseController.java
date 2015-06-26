package edu.cqu.ncycoa.safety.web.controller;

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
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.safety.domain.FaultCase;
import edu.cqu.ncycoa.safety.domain.SafeConductMaterial;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;
@Controller
@RequestMapping("/faultcase_management")
public class FaultCaseController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "safetyedu_management/faultcase";
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
		
		systemService.removeEntities(ids, FaultCase.class);
		message = "记录删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		FaultCase faultCase = systemService.findEntityById(Long.parseLong(id), FaultCase.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safetyedu_management/faultcase");
		mav.addObject("faultCase",faultCase);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(FaultCase faultCase, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (faultCase.getId() != null) {
			message = "更新成功";
			FaultCase t = systemService.findEntityById(faultCase.getId(), FaultCase.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(faultCase, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "添加成功";
			
			systemService.addEntity(faultCase);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "safetyedu_management/faultcaselist";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(FaultCase faultCase, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<FaultCase> cq = new QueryDescriptor<FaultCase>(FaultCase.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<FaultCase> tqBuilder = QueryUtils.getTQBuilder(faultCase, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
