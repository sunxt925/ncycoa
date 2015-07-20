package edu.cqu.ncycoa.plan.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.entity.system.StaffInfo;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.plan.domain.Asset;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.PlanTask;
import edu.cqu.ncycoa.plan.service.PendingTaskService;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/pending-task")
public class PendingTaskController {
	
	@Resource(name="pendingTaskService")
	PendingTaskService pendingTaskService;
	
	@Resource(name="processEngine")
	ProcessEngine processEngine;
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request, Model model) {
		return "plan_management/pendingtask_list";
	}
	
	@RequestMapping(params="view")
	public String view(Long id, HttpServletRequest request, Model model) {
		PlanTask task = pendingTaskService.findEntityById(id, PlanTask.class);
		model.addAttribute("task", task);
		return "plan_management/plan_task_detail";
	}
	
	@RequestMapping(params="handle")
	public String handle(HttpServletRequest request, Model model) {
		Long id = Long.parseLong(request.getParameter("id"));
		Long taskId = Long.parseLong(request.getParameter("taskId"));
		String type = request.getParameter("type");
		model.addAttribute("id", id);
		model.addAttribute("taskId", taskId);
		if("normal".equals(type)){
			return "plan_management/plan_step_normal";
		} else if("audit".equals(type)){
			
			List<PlanTask> tasks = pendingTaskService.findPreTasksByTaskId(taskId);
			model.addAttribute("preTasks", tasks);
			
			return "plan_management/plan_step_audit";
		} else {
			return "plan_management/plan_step_upload";
		}
	}
	
	@RequestMapping(params="h_normal")
	@ResponseBody
	public void handleNormal(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			Long taskId = Long.parseLong(request.getParameter("taskId"));
			String description = request.getParameter("description");
			pendingTaskService.handleTask(id, taskId, description, (short)0);
		} catch (Exception e) {
			message = "执行失败";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		message = "执行成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="h_audit")
	@ResponseBody
	public void handleAudit(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			Long taskId = Long.parseLong(request.getParameter("taskId"));
			int audit = Integer.parseInt(request.getParameter("audit"));
			pendingTaskService.handleAuditTask(id, taskId, audit, (short)0);
		} catch (Exception e) {
			message = "执行失败";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		message = "执行成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	private File saveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
		File file = new File(path + "/" + filename);
		FileOutputStream fs = new FileOutputStream(file);
		byte[] buffer = new byte[1024 * 1024];
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
		return file;
	}
	
	@RequestMapping(params="h_upload")
	@ResponseBody
	public void handleUpload(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			Long taskId = Long.parseLong(request.getParameter("taskId"));
			List<Asset> assets = new ArrayList<Asset>();
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			String path = multipartRequest.getSession().getServletContext().getRealPath("doc/plan_task");
			for (Iterator<MultipartFile> it = multipartRequest.getFiles("files").iterator(); it.hasNext();) {
				MultipartFile file = it.next();
				String newFileName = UUID.randomUUID().toString();
				String originalName = file.getOriginalFilename();
				String extName = originalName.substring(originalName.lastIndexOf("."), originalName.length());
				
				Asset asset = new Asset();
				asset.setDescription("");
				asset.setExtName(extName);
				asset.setFriendlyName(originalName);
				asset.setRealName(path + newFileName + extName);
				asset.setId(newFileName);
				assets.add(asset);
				
				saveFileFromInputStream(file.getInputStream(), path, newFileName + extName);
			}
			
			pendingTaskService.handleUploadTask(id, taskId, assets, (short)0);
		} catch (Exception e) {
			message = "执行失败";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		message = "执行成功";
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
		
		Collections.sort(dg.getResults(), new Comparator<PendingTask>(){
			@Override
			public int compare(PendingTask o1, PendingTask o2) {
				return -o1.getGenDate().compareTo(o2.getGenDate());
			}
		});
		
		TagUtil.datagrid(response, dg);
	}
	
}
