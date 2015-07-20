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
import edu.cqu.ncycoa.safety.domain.PressureEquipment;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/pressureequipment_management")
public class PressureEquipmentController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "equipment_management/addpressureequipment";
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
		
		systemService.removeEntities(ids, PressureEquipment.class);
		message = "会议室删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		PressureEquipment pressureEquipment = systemService.findEntityById(Long.parseLong(id), PressureEquipment.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("equipment_management/addpressureequipment");
		mav.addObject("pressureEquipment",pressureEquipment);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(PressureEquipment pressureEquipment, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (pressureEquipment.getId() != null) {
			message = "会议室更新成功";
			PressureEquipment t = systemService.findEntityById(pressureEquipment.getId(), PressureEquipment.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(pressureEquipment, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "会议室更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "会议室添加成功";
			
			systemService.addEntity(pressureEquipment);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "equipment_management/pressureequipmentlist";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(PressureEquipment pressureEquipment, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<PressureEquipment> cq = new QueryDescriptor<PressureEquipment>(PressureEquipment.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<PressureEquipment> tqBuilder = QueryUtils.getTQBuilder(pressureEquipment, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}


}
