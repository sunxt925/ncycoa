package edu.cqu.ncycoa.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.Format;
import com.entity.system.UserInfo;


import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.Reform;
import edu.cqu.ncycoa.domain.ReformBack;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;


@Controller
@RequestMapping("/reformback_management")
public class ReformBackController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("reformid"); 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reformback_management/reformback");
		mav.addObject("reformid",id);
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
		
		systemService.removeEntities(ids, ReformBack.class);
		message = "整改反馈删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(ReformBack reformBack, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (reformBack.getId() != null) {
			message = "整改反馈更新成功";
			ReformBack t = systemService.findEntityById(reformBack.getId(), ReformBack.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(reformBack, t);
				
				
				systemService.saveEntity(t);
				
				
			} catch (Exception e) {
				message = "整改反馈更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "整改反馈上传成功";
			reformBack.setSubDate(Format.strToDate(Format.getNowtime()));
			reformBack.setSubUser(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
			//更新整改记录状态
			Reform reform= systemService.findEntityById(reformBack.getReformId(), Reform.class);
			reform.setFlag("1");
			systemService.saveEntity(reform);
			systemService.addEntity(reformBack);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "reformback_management/reformbacklist";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(ReformBack reformBack, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<ReformBack> cq = new QueryDescriptor<ReformBack>(ReformBack.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<ReformBack> tqBuilder = QueryUtils.getTQBuilder(reformBack, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
