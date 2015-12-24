package edu.cqu.ncycoa.plan.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dao.system.StaffDao;

import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;
import edu.cqu.ncycoa.util.SystemUtils;

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
	
	@SuppressWarnings("static-access")
	public static PendingTask createPendingTaskByPlanTask(PlanTask task){
		PendingTask ret = new PendingTask();
		String formKey = "pending-task.htm?handle&taskId=" + task.getId();
		
		ret.setCeilingEntityId(task.getId().toString());
		ret.setFormKey(formKey);
		ret.setParticipant(task.getParticipantName());
		ret.setParticipantValue(task.getParticipantCode());
		ret.setContent("��Դ�ڼƻ�[ " + task.getStep().getPlan().getName() + ":" + task.getStep().getSummary() + " ]�Ĵ���������");
		ret.setStatus((short)0);
		ret.setGenDate(new Date());
		
		// ������
		List<String> phonesList = StaffDao.getPhonesByStaffcode(Arrays.asList(task.getParticipantCode()));
		if(phonesList != null && phonesList.size() > 0) {
			StringBuilder content = new StringBuilder();
			content.append("������֪ͨ����");
			content.append("�����µ������������Դ�ڼƻ� [")
				.append(task.getStep().getPlan().getName())
				.append(":")
				.append(task.getStep().getSummary())
				.append("], �뾡�촦�� ");
			SystemUtils.getWebService().SendMessage(phonesList.get(0), content.toString());
		}
		
		return ret;
	}
}
