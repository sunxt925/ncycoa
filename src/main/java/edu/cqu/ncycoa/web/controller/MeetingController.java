package edu.cqu.ncycoa.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.CodeDictionary;
import com.common.Format;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.MeetingService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TQRestriction;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.MeetingInfo;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/meeting_management")
public class MeetingController {

	@Resource(name="meetingService")
	MeetingService meetingService;
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "meetingmanage/meeting";
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
			meetingService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		meetingService.removeEntities(ids, MeetingInfo.class);
		message = "会议删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		MeetingInfo meetingInfo = meetingService.findEntityById(Long.parseLong(id), MeetingInfo.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("meetingmanage/meeting");
		mav.addObject("meetingInfo",meetingInfo);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(MeetingInfo meetingInfo, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (meetingInfo.getId() != null) {
			message = "计划更新成功";
			MeetingInfo t = meetingService.findEntityById(meetingInfo.getId(), MeetingInfo.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(meetingInfo, t);
				String[] staffcodes = request.getParameter("participants").replace("[","").replace("]", "").split(",");
				List<String> stafflist= new ArrayList<String>();
				for(String staffcode : staffcodes){
					stafflist.add(staffcode.trim());
				}
				t.setParticipants(stafflist);
				meetingService.saveEntity(t);
				
			} catch (Exception e) {
				message = "计划更新失败";
				meetingService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "计划添加成功";
			//添加参与人员
			String[] staffcodes = request.getParameter("participants").replace("[","").replace("]", "").split(",");
			List<String> stafflist= new ArrayList<String>();
			for(String staffcode : staffcodes){
				stafflist.add(staffcode.trim());
			}
			meetingInfo.setParticipants(stafflist);
			meetingInfo.setAuditFlag("0");
			meetingInfo.setMeetingFlag("0");
			meetingService.addEntity(meetingInfo);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		String flag = request.getParameter("flag");
		String url =  "meetingmanage/meetinglist";;
		if(flag.equals("audited_y")){
			url = "meetingmanage/noticelist";;
		}
		
		if(flag.equals("audited")){
			url = "meetingmanage/auditedlist";;
		}
		if(flag.equals("uaudited")){
			url = "meetingmanage/meeting_auditlist";;
		}
		if(flag.equals("query")){
			url = "meetingmanage/meetinglquery";;
		}
		if(flag.equals("depart")){
			url = "meetingmanage/departmeetinglist";;
		}
		return url;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(MeetingInfo meetingInfo, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<MeetingInfo> cq = new QueryDescriptor<MeetingInfo>(MeetingInfo.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<MeetingInfo> tqBuilder = QueryUtils.getTQBuilder(meetingInfo, request.getParameterMap());
		String flag = request.getParameter("flag");
		
		if(flag.equals("audited_y")||flag.equals("depart")){
			tqBuilder.addRestriction(new TQRestriction( "auditFlag", "in", Arrays.asList("11")));
			tqBuilder.addRestriction(new TQRestriction( "meetingFlag", "in", Arrays.asList("0")));
		}
		
		if(flag.equals("audited")){
			tqBuilder.addRestriction(new TQRestriction( "auditFlag", "in", Arrays.asList("11","10")));
		}
		if(flag.equals("uaudited")){
			tqBuilder.addRestriction(new TQRestriction( "auditFlag", "in", Arrays.asList("0")));
		}
			
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
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
		String res = request.getParameter("res"); 
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
		
		MeetingInfo meetingInfo;
		for(Long tmp : ids) {
			meetingInfo = meetingService.findEntityById(tmp, MeetingInfo.class);
			if(res.equals("yes")){
				meetingInfo.setAuditFlag("11");
			}else{
				meetingInfo.setAuditFlag("10");
			}
			meetingInfo.setAuditDate(Format.strToDate(Format.getNowtime()));
			meetingService.saveEntity(meetingInfo);
			meetingService.addLog("会议审核", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		
		message = "审核完成";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="execute")
	@ResponseBody
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		String res = request.getParameter("res"); 
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
		
		MeetingInfo meetingInfo;
		for(Long tmp : ids) {
			meetingInfo = meetingService.findEntityById(tmp, MeetingInfo.class);
			
			if(res.equals("yes")){
				meetingInfo.setMeetingFlag("11");
			}else{
				meetingInfo.setMeetingFlag("10");
			}
			
			meetingInfo.setAuditDate(Format.strToDate(Format.getNowtime()));
			meetingService.saveEntity(meetingInfo);
			meetingService.addLog("会议审核", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		
		message = "处理完成";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="queryAttender")
	public ModelAndView queryAttender(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mov =new ModelAndView();
		String id = request.getParameter("id");
		MeetingInfo meetingInfo = meetingService.findEntityById(Long.parseLong(id), MeetingInfo.class);
		Map<String,String> map = new HashMap<String, String>();
		for(String s : meetingInfo.getParticipants()){
			map.put(s, CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",s));
		}
		mov.setViewName("meetingmanage/memberlist");
		mov.addObject("maps", map);
		return mov;
	}
	
}
