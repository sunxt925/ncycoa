package edu.cqu.ncycoa.plan.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.ncycoa.common.service.CommonServiceImpl;
import edu.cqu.ncycoa.plan.PlanStatus;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.Plan;
import edu.cqu.ncycoa.plan.domain.PlanStep;

@Service("planService")
@Transactional
public class PlanServiceImpl extends CommonServiceImpl implements PlanService {

	@Override
	public void runPlan(Long planId){
		Plan plan = commonDao.readEntityById(planId, Plan.class);
		//设置计划为执行态
		plan.setStatus(PlanStatus.EXECUTTING);
		
		List<PlanStep> steps = plan.getSteps();
		if(steps != null && !steps.isEmpty()){
			//找到该项计划的第一个流程节点，设置该节点为运行状态
			PlanStep step = steps.get(0);
			step.setStatus((short)1);
			
			//给流程节点的负责人，创建一个待办事项
			PendingTask task = new PendingTask();
			switch(step.getTypeValue()){
			case 0:
				task.setFormKey("pending-task.htm?handle&type=normal");
				break;
			case 1:
				task.setFormKey("pending-task.htm?handle&type=audit");
				break;
			case 2:
				task.setFormKey("pending-task.htm?handle&type=upload");
				break;
			}
			task.setPlan(step);
			task.setParticipant(step.getParticipant());
			task.setParticipantValue(step.getParticipantValue());
			task.setContent("来源于计划[ " + step.getPlan().getName() + " ]的待处理任务");
			task.setStatus((short)0);
			task.setGenDate(new Date());
			commonDao.saveEntity(task);
		}
	}
	
	@Override
	public void handlePendingTask(Long taskId){
		PendingTask task = commonDao.readEntityById(taskId, PendingTask.class);
		//设置已处理该待办事项
		task.setStatus((short)1);
		task.setHandleDate(new Date());
		
		//设置该事项对应的流程节点的状态为，已完成
		PlanStep step = task.getPlan();
		step.setStatus((short)2);
		
		List<PlanStep> steps = step.getPlan().getSteps();
		int i=0;
		for(;i<steps.size(); i++){
			if(steps.get(i).getId().equals(step.getId())){
				break;
			}
		}
		
		if(i == steps.size() - 1) { //如果流程已走完，修改计划的状态
			step.getPlan().setStatus((short)5);
		} else { //流程还未走完，取下一个流程节点，并推送新的待办事项
			step = steps.get(i+1);
			step.setStatus((short)1);
			
			task = new PendingTask();
			switch(step.getTypeValue()){
			case 0:
				task.setFormKey("pending-task.htm?handle&type=normal");
				break;
			case 1:
				task.setFormKey("pending-task.htm?handle&type=audit");
				break;
			case 2:
				task.setFormKey("pending-task.htm?handle&type=upload");
				break;
			}
			task.setPlan(step);
			task.setParticipant(step.getParticipant());
			task.setParticipantValue(step.getParticipantValue());
			task.setContent("来源于计划[ " + step.getPlan().getName() + " ]的待处理任务");
			task.setGenDate(new Date());
			task.setStatus((short)0);
			commonDao.saveEntity(task);
		}
	}
	
	@Override
	public List<PlanStep> findPlanStepsByPlanId(Long planId){
		Plan plan = commonDao.readEntityById(planId, Plan.class);
		return plan.getSteps();
	}
	
}
