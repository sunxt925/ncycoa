package edu.cqu.ncycoa.plan.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.system.StaffInfo;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.service.PlanService;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/pending-task")
public class PendingTaskController {
	
	@Resource(name="planService")
	PlanService planService;
	
	@Resource(name="processEngine")
	ProcessEngine processEngine;
	
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
		tqBuilder.addRestriction("status", "=", (short)0);
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		
		List<PendingTask> pendingTasks = new ArrayList<PendingTask>();
		TaskService taskService = processEngine.getTaskService();
		FormService formService=processEngine.getFormService();
		for (org.activiti.engine.task.Task activityTask : taskService.createTaskQuery().taskAssignee(staffcode).orderByTaskCreateTime().desc().list()) {
			TaskFormData formData = formService.getTaskFormData(activityTask.getId());
			String formKey = formData.getFormKey();
			StaffInfo staffinfo=new StaffInfo(staffcode);
			String staffname=staffinfo.getName();
			
			PendingTask pendingTask = new PendingTask();
			pendingTask.setContent(activityTask.getName());
			
			Timestamp gendate = new Timestamp(activityTask.getCreateTime().getTime());
			pendingTask.setGenDate(gendate);
			pendingTask.setParticipant(activityTask.getAssignee());
			pendingTask.setParticipantValue(staffname);
			pendingTask.setFormKey(formKey + "?id=" + activityTask.getId());
			pendingTasks.add(pendingTask);
		}
		dg.getResults().addAll(pendingTasks);
		
		Collections.sort(dg.getResults(), new Comparator<Date>(){
			@Override
			public int compare(Date o1, Date o2) {
				return -o1.compareTo(o2);
			}
		});
		
		TagUtil.datagrid(response, dg);
	}
	
}
