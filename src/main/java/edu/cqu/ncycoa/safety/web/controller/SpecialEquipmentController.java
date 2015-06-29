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
import edu.cqu.ncycoa.domain.MeetingRoom;
import edu.cqu.ncycoa.safety.domain.SpecialEquipment;
import edu.cqu.ncycoa.safety.domain.SubSpecialEquipment;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/specialequipment_management")
public class SpecialEquipmentController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="addsub")
	public ModelAndView addSub(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id"); 
		//SubSpecialEquipment subSpecialEquipment = systemService.findEntityById(Long.parseLong(id), SubSpecialEquipment.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("equipment_management/addsubspecialequipment");
		mav.addObject("subID",id);
		//mav.addObject("subSpecialEquipment",subSpecialEquipment);
		return mav;
	}
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "equipment_management/addspecialequipment";
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
		
		systemService.removeEntities(ids, SpecialEquipment.class);
		message = "会议室删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		SpecialEquipment specialEquipment = systemService.findEntityById(Long.parseLong(id), SpecialEquipment.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("equipment_management/addspecialequipment");
		mav.addObject("specialEquipment",specialEquipment);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(SpecialEquipment specialEquipment, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (specialEquipment.getId() != null) {
			message = "会议室更新成功";
			SpecialEquipment t = systemService.findEntityById(specialEquipment.getId(), SpecialEquipment.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(specialEquipment, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "会议室更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "会议室添加成功";
			
			systemService.addEntity(specialEquipment);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params = "savesub")
	@ResponseBody
	public void saveSub(SubSpecialEquipment subSpecialEquipment, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("subID");
		System.out.println("@@"+subSpecialEquipment.getEquipmentName());
		SpecialEquipment t = systemService.findEntityById(Long.parseLong(id), SpecialEquipment.class);
		t.addSub(subSpecialEquipment);
		message = "添加成功";
		MyBeanUtils.copyBeanNotNull2Bean(t, t);
		systemService.saveEntity(t);	
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "equipment_management/specialequipmentlist";
		
	}
	@RequestMapping(params="sub")
	public ModelAndView dgViewSub(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("^"+id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("equipment_management/subspecialequipmentlist");
		mav.addObject("id",id);
		return mav;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(SpecialEquipment specialEquipment, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<SpecialEquipment> cq = new QueryDescriptor<SpecialEquipment>(SpecialEquipment.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<SpecialEquipment> tqBuilder = QueryUtils.getTQBuilder(specialEquipment, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdatasub")
	@ResponseBody
	public void dgDataSub(SubSpecialEquipment subSpecialEquipment, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<SubSpecialEquipment> cq = new QueryDescriptor<SubSpecialEquipment>(SubSpecialEquipment.class, dg);
		String id = request.getParameter("id");
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<SubSpecialEquipment> tqBuilder = QueryUtils.getTQBuilder(subSpecialEquipment, request.getParameterMap());
		System.out.println("^^^^"+id);
		tqBuilder.addRestriction(new TQRestriction( "ID", "in", Arrays.asList(id)));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	
}
