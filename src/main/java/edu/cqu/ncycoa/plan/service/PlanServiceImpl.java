package edu.cqu.ncycoa.plan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.ncycoa.common.service.CommonServiceImpl;
import edu.cqu.ncycoa.plan.PlanStatus;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.Plan;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;
import edu.cqu.ncycoa.util.SystemUtils;

@Service("planService")
@Transactional
public class PlanServiceImpl extends CommonServiceImpl implements PlanService {

	@Override
	public void auditAndRunPlan(Long planId, Boolean isPassed) {
		Plan plan = commonDao.readEntityById(planId, Plan.class);
		if(isPassed) {
			plan.setStatus(PlanStatus.AUDIT_PASS);
			runPlan(planId);
		} else {
			plan.setStatus(PlanStatus.AUDIT_NO_PASS);
		}
	}
	
	@Override
	public void runPlan(Long planId){
		Plan plan = commonDao.readEntityById(planId, Plan.class);
		//设置计划为执行态
		plan.setStatus(PlanStatus.EXECUTTING);
		
		PlanInstance planInstance = new PlanInstance();
		planInstance.setPlan(plan);
		planInstance.setExecutor(SystemUtils.getSessionUser().getUsername());
		planInstance.setExecDate(new Date());
		planInstance.setStatus(PlanInstance.EXECUTING);
		
		planInstance.setPlanId(plan.getId());
		planInstance.setPlanBeginDate(plan.getPlanBeginDate());
		planInstance.setPlanEndDate(plan.getPlanEndDate());
		planInstance.setName(plan.getName());
		planInstance.setSummary(plan.getSummary());
		
		String participants = "";
		String participantIds = "";
		List<PlanStep> steps = plan.getSteps();
		for(int i = 0; i < steps.size(); i++){
			PlanStep step = steps.get(i);
			
			for(String id : step.getParticipants().keySet()){
				if(!participantIds.contains(id)) {
					participantIds += id + ",";
					participants += step.getParticipants().get(id) + ",";
				}
			}
			
			if(i == 0){
				step.setStatus(PlanStep.EXECUTING);
				planInstance.setCurrentStep(step);
				commonDao.saveEntity(planInstance);
				
				List<PlanTask> planTasks =  PendingTaskServiceUtil.createPlanTasksByPlanStep(planInstance, step);
				for(PlanTask task : planTasks){
					task.setStatus(PlanTask.EXECUTING);
					commonDao.saveEntity(task);
					
					PendingTask aPendingTask = PendingTaskServiceUtil.createPendingTaskByPlanTask(task);
					commonDao.saveEntity(aPendingTask);
				}
			} else {
				List<PlanTask> planTasks =  PendingTaskServiceUtil.createPlanTasksByPlanStep(planInstance, step);
				for(PlanTask task : planTasks){
					task.setStatus(PlanTask.READY);
					commonDao.saveEntity(task);
				}
			}
		}
		
		if(participantIds.length() > 0) {
			participantIds = participantIds.substring(0,  participantIds.length() - 1);
			participants = participants.substring(0,  participants.length() - 1);
		}
		planInstance.setParticipantIds(participantIds);
		planInstance.setParticipantNames(participants);
		commonDao.saveEntity(planInstance);
		
	}
	
	@Override
	public List<PlanStep> findPlanStepsByPlanId(Long planId){
		Plan plan = commonDao.readEntityById(planId, Plan.class);
		return plan.getSteps();
	}
	
	@Override
	public PlanInstance findPlanInstanceByPlanId(Long planId){
		Plan plan = commonDao.readEntityById(planId, Plan.class);
		PlanInstance planInstance = commonDao.readEntityByProperty("plan", plan, PlanInstance.class);
		return planInstance;
	}
	
	@Override
	public Map<PlanStep, List<PlanTask>> findPlanTasks(PlanInstance planInstance){
		List<PlanTask> tasks = commonDao.readEntitiesByProperty("planInstance", planInstance, PlanTask.class);
		Map<PlanStep, List<PlanTask>> ret = new TreeMap<PlanStep, List<PlanTask>>();
		for(PlanTask task : tasks){
			if(!ret.containsKey(task.getStep())){
				ret.put(task.getStep(), new ArrayList<PlanTask>());
			}
			ret.get(task.getStep()).add(task);
		}
		return ret;
	}
	
	
	@Override
	public void planReview(Long id, String result){
		PlanInstance plan = commonDao.readEntityById(id, PlanInstance.class);
		plan.setReview(result);
		plan.setStatus(PlanInstance.REVIEWED);
	}
	
	@Override
	public Map<PlanStep, List<PlanTask>> findAllTasksByPlanId(Long planId){
		Plan plan = commonDao.readEntityById(planId, Plan.class);
		List<PlanStep> steps = plan.getSteps();
		int i=0;
		TreeMap<PlanStep, List<PlanTask>> ret = new TreeMap<PlanStep, List<PlanTask>>();
		for(;i<steps.size(); i++){
			List<PlanTask> preTasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where step=?1", PlanTask.class, steps.get(i));
			ret.put(steps.get(i), preTasks);
		}
		return ret;
	}
	
}
