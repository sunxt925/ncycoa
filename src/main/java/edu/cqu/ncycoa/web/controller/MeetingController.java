package edu.cqu.ncycoa.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.MeetingService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.MeetingInfo;
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
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		return "meetingmanage/meetinglist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(MeetingInfo meetingInfo, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<MeetingInfo> cq = new QueryDescriptor<MeetingInfo>(MeetingInfo.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<MeetingInfo> tqBuilder = QueryUtils.getTQBuilder(meetingInfo, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
