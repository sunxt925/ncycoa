package edu.cqu.ncycoa.plan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;

public class PendingTaskServiceUtil {

	public static List<PlanTask> createPlanTasksByPlanStep(PlanInstance instance, PlanStep step){
		List<PlanTask> ret = new ArrayList<PlanTask>();
		Map<String, String> participants = step.getParticipants();
		for(String key : participants.keySet()){
			PlanTask task = new PlanTask();
			
			task.setPlanInstance(instance);
			task.setStep(step);
			task.setParticipantCode(key);
			task.setParticipantName(participants.get(key));
			task.setStatus((short)0);
			
			ret.add(task);
		}
		
		return ret;
	}
	
	public static PendingTask createPendingTaskByPlanTask(PlanTask task){
		PendingTask ret = new PendingTask();
		String formKey = "pending-task.htm?handle&taskId=" + task.getId();
		
		ret.setCeilingEntityId(task.getId().toString());
		ret.setFormKey(formKey);
		ret.setParticipant(task.getParticipantName());
		ret.setParticipantValue(task.getParticipantCode());
		ret.setContent("来源于计划[ " + task.getStep().getPlan().getName() + ":" + task.getStep().getSummary() + " ]的待处理任务");
		ret.setStatus((short)0);
		ret.setGenDate(new Date());
		
		return ret;
	}
}
