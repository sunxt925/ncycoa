package edu.cqu.ncycoa.safety.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.CodeDictionary;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.safety.domain.SafetyEdu;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/safetyedu_management")
public class SafetyEduController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "safetyedu_management/safetyedu";
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
		
		systemService.removeEntities(ids, SafetyEdu.class);
		message = "培训记录删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		SafetyEdu safetyEdu = systemService.findEntityById(Long.parseLong(id), SafetyEdu.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safetyedu_management/safetyedu");
		mav.addObject("safetyEdu",safetyEdu);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(SafetyEdu safetyEdu, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (safetyEdu.getId() != null) {
			message = "培训记录更新成功";
			SafetyEdu t = systemService.findEntityById(safetyEdu.getId(), SafetyEdu.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(safetyEdu, t);
				String[] staffcodes = request.getParameter("participants").replace("[","").replace("]", "").split(",");
				List<String> stafflist= new ArrayList<String>();
				for(String staffcode : staffcodes){
					stafflist.add(staffcode.trim());
				}
				t.setParticipants(stafflist);
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "培训记录更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "培训记录添加成功";
			//添加参与人员
			String[] staffcodes = request.getParameter("participants").replace("[","").replace("]", "").split(",");
			List<String> stafflist= new ArrayList<String>();
			for(String staffcode : staffcodes){
				stafflist.add(staffcode.trim());
			}
			safetyEdu.setParticipants(stafflist);
			systemService.addEntity(safetyEdu);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "safetyedu_management/safetyedulist";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(SafetyEdu safetyEdu, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<SafetyEdu> cq = new QueryDescriptor<SafetyEdu>(SafetyEdu.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<SafetyEdu> tqBuilder = QueryUtils.getTQBuilder(safetyEdu, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	@RequestMapping(params="queryAttender")
	public ModelAndView queryAttender(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mov =new ModelAndView();
		String id = request.getParameter("id");
		SafetyEdu safetyEdu = systemService.findEntityById(Long.parseLong(id), SafetyEdu.class);
		Map<String,String> map = new HashMap<String, String>();
		for(String s : safetyEdu.getParticipants()){
			map.put(s, CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",s));
		}
		mov.setViewName("safetyedu_management/memberlist");
		mov.addObject("maps", map);
		return mov;
	}
}
