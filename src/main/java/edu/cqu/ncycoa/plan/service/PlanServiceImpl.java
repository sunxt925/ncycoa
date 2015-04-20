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
		plan.setStatus(PlanStatus.EXECUTTING);
		List<PlanStep> steps = plan.getSteps();
		if(steps != null && !steps.isEmpty()){
			PlanStep step = steps.get(0);
			step.setStatus((short)1);
			
			PendingTask task = new PendingTask();
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
		task.setStatus((short)1);
		task.setHandleDate(new Date());
		
		PlanStep step = task.getPlan();
		step.setStatus((short)2);
		
		List<PlanStep> steps = step.getPlan().getSteps();
		int i=0;
		for(;i<steps.size(); i++){
			if(steps.get(i).getId().equals(step.getId())){
				break;
			}
		}
		
		if(i == steps.size() - 1) {
			step.getPlan().setStatus((short)5);
		} else {
			step = steps.get(i+1);
			step.setStatus((short)1);
			task = new PendingTask();
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
