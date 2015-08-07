package edu.cqu.ncycoa.web.controller;

import java.util.HashMap;
import java.util.Map;

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
import edu.cqu.ncycoa.domain.MeetingRoom;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/meetingroom_management")
public class MeetingRoomController {

	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "meetingroom_management/room";
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
		
		systemService.removeEntities(ids, MeetingRoom.class);
		message = "会议室删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		initmaps();
		String id = request.getParameter("id"); 
		MeetingRoom meetingRoom = systemService.findEntityById(Long.parseLong(id), MeetingRoom.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("meetingroom_management/room");
		mav.addObject("meetingRoom",meetingRoom);
		mav.addObject("orgname",orgcode2nameMaps.get(meetingRoom.getBelongOrg()));
		
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(MeetingRoom meetingRoom, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (meetingRoom.getId() != null) {
			message = "会议室更新成功";
			MeetingRoom t = systemService.findEntityById(meetingRoom.getId(), MeetingRoom.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(meetingRoom, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "会议室更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "会议室添加成功";
			
			systemService.addEntity(meetingRoom);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "meetingroom_management/roomlist";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(MeetingRoom meetingRoom, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<MeetingRoom> cq = new QueryDescriptor<MeetingRoom>(MeetingRoom.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<MeetingRoom> tqBuilder = QueryUtils.getTQBuilder(meetingRoom, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	static Map<String, String> orgcode2nameMaps = new HashMap<String,String>();
    public static void initmaps(){
    	orgcode2nameMaps.put("NC.00", "市局公司部门");
    	orgcode2nameMaps.put("NC.01.20", "顺庆区局");
    	orgcode2nameMaps.put("NC.01.21", "高坪区局");
    	orgcode2nameMaps.put("NC.01.22", "嘉陵区局");
    	orgcode2nameMaps.put("NC.01.24", "西充县局");
    	orgcode2nameMaps.put("NC.01.25", "南部县局");
    	orgcode2nameMaps.put("NC.01.26", "阎中市局");
    	orgcode2nameMaps.put("NC.01.27", "仪陇县局");
    	orgcode2nameMaps.put("NC.01.28", "营山县局");
    	orgcode2nameMaps.put("NC.01.29", "蓬安县局");
    	orgcode2nameMaps.put("NC.01.30", "物流中心");
    	
    }
	
}
