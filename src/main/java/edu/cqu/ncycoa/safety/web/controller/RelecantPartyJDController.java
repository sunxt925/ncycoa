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
import edu.cqu.ncycoa.dao.SafetyDao;
import edu.cqu.ncycoa.safety.domain.RelevantPartyJD;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/jd_management")
public class RelecantPartyJDController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safeproduction_management/addjd");
		mav.addObject("parties",SafetyDao.getAllRelevantParties());
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
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		systemService.removeEntities(ids, RelevantPartyJD.class);
		message = "会议室删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		RelevantPartyJD motorCar = systemService.findEntityById(Long.parseLong(id), RelevantPartyJD.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safeproduction_management/addjd");
		mav.addObject("jd",motorCar);
		mav.addObject("parties",SafetyDao.getAllRelevantParties());
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(RelevantPartyJD motorCar, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String partyName=motorCar.getPartyName();
		motorCar.setPartyContent(SafetyDao.getPartyContentByName(partyName));
		motorCar.setManager(SafetyDao.getManagerByName(partyName));
		if (motorCar.getId() != null) {
			message = "会议室更新成功";
			RelevantPartyJD t = systemService.findEntityById(motorCar.getId(), RelevantPartyJD.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(motorCar, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "会议室更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "会议室添加成功";
			
			systemService.addEntity(motorCar);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "safeproduction_management/jdlist";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(RelevantPartyJD motorCar, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<RelevantPartyJD> cq = new QueryDescriptor<RelevantPartyJD>(RelevantPartyJD.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<RelevantPartyJD> tqBuilder = QueryUtils.getTQBuilder(motorCar, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
