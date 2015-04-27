package edu.cqu.ncycoa.plan.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.Plan;
import edu.cqu.ncycoa.plan.service.PlanService;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/pending-task")
public class PendingTaskController {
	
	@Resource(name="planService")
	PlanService planService;
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request, Model model) {
		return "plan_management/pendingtask_list";
	}
	
	@RequestMapping(params="view")
	public String view(Long id, HttpServletRequest request, Model model) {
		PendingTask task = planService.findEntityById(id, PendingTask.class);
		model.addAttribute("task", task);
		return "plan_management/pendingtask";
	}
	
	@RequestMapping(params="handle")
	@ResponseBody
	public void handle(Long id, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		planService.handlePendingTask(id);
		message = "任务处理完毕";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(PendingTask task, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<PendingTask> cq = new QueryDescriptor<PendingTask>(PendingTask.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<PendingTask> tqBuilder = QueryUtils.getTQBuilder(task, request.getParameterMap());
		String staffcode = SystemUtils.getSessionUser().getStaffcode();
		tqBuilder.addRestriction("participantValue", "=", staffcode);
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
}
