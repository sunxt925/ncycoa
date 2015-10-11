package edu.cqu.ncycoa.plan.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.ncycoa.common.service.CommonServiceImpl;
import edu.cqu.ncycoa.plan.domain.Asset;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;

@Service("pendingTaskService")
@Transactional
public class PendingTaskServiceImpl extends CommonServiceImpl implements PendingTaskService {
	
	@Override
	public void handleNotAdmission(Long pendingTaskId, Long planTaskId) {
		PlanTask task = commonDao.readEntityById(planTaskId, PlanTask.class);
		task.setStatus(PlanTask.FINISHED);
		task.setHandleDate(new Date());
		task.setResult("驳回");
		
		PendingTask pendingTask = commonDao.readEntityById(pendingTaskId, PendingTask.class);
		pendingTask.setStatus((short)1);
		pendingTask.setHandleDate(new Date());
		
		List<PlanTask> tasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where e.planInstance=?1 and e.step=?2 and e.status!=?3", 
				PlanTask.class, task.getPlanInstance(), task.getStep(), PlanTask.FINISHED);
		if(tasks != null && tasks.size() > 0) {
			return;
		}
		task.getStep().setStatus(PlanStep.FINISHED);
		PlanStep nextStep = null;
		List<PlanStep> steps = task.getPlanInstance().getPlan().getSteps();
		int i=0;
		for(;i<steps.size(); i++){
			if(steps.get(i).getId().equals(task.getStep().getId())){
				break;
			}
		}
		
		boolean cannotpass = false;
		tasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where e.planInstance=?1 and e.step=?2", PlanTask.class, task.getPlanInstance(), task.getStep());
		for(PlanTask tmp : tasks){
			if("驳回".equals(tmp.getResult())){
				cannotpass = true;
				break;
			}
		}
		
		if(cannotpass && i == 0){
			nextStep = steps.get(0);
		} else if(cannotpass && i > 0){
			task.getStep().setStatus(PlanStep.READY);
			for(PlanTask tmp : tasks){
				tmp.setStatus(PlanTask.READY);
				tmp.setResult("");
			}
			
			nextStep = steps.get(i - 1);
		} else if(!cannotpass && i == steps.size() - 1){ //
			task.getPlanInstance().getPlan().setStatus((short)5);
			task.getPlanInstance().setStatus(PlanInstance.FINISHED);
			nextStep = null;
		} else {
			nextStep = steps.get(i + 1);
		}
		
		if(nextStep != null){
			nextStep.setStatus(PlanStep.EXECUTING);
			task.getPlanInstance().setCurrentStep(nextStep);
			
			List<PlanTask> nextTasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where planInstance=?1 and step=?2", 
					PlanTask.class, task.getPlanInstance(), nextStep);
			
			for(PlanTask tmp : nextTasks){
				tmp.setStatus(PlanTask.EXECUTING);
				tmp.setResult("");
				
				PendingTask aPendingTask = PendingTaskServiceUtil.createPendingTaskByPlanTask(tmp);
				commonDao.saveEntity(aPendingTask);
			}
			
		}
		
		
	}
	
	@Override
	public void handleTask(Long pendingTaskId, Long planTaskId, String description, List<Asset> assets){
		PlanTask task = commonDao.readEntityById(planTaskId, PlanTask.class);
		task.setStatus(PlanTask.FINISHED);
		task.setHandleDate(new Date());
		task.setResult("通过");
		task.setUploadedFiles(assets);
		task.setDescription(description);
		
		for(Asset asset : assets) {
			asset.setTask(task);
		}
		
		PendingTask pendingTask = commonDao.readEntityById(pendingTaskId, PendingTask.class);
		pendingTask.setStatus((short)1);
		pendingTask.setHandleDate(new Date());
		
		//设置该事项对应的流程节点的状态为，已完成
		List<PlanTask> tasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where e.planInstance=?1 and e.step=?2 and e.status!=?3", 
				PlanTask.class, task.getPlanInstance(), task.getStep(), PlanTask.FINISHED);
		if(tasks != null && tasks.size() > 0){
			return;
		}
		task.getStep().setStatus(PlanStep.FINISHED);
		
		List<PlanStep> steps = task.getPlanInstance().getPlan().getSteps();
		int i=0;
		for(;i<steps.size(); i++){
			if(steps.get(i).getId().equals(task.getStep().getId())){
				break;
			}
		}
		if(i == steps.size() - 1) { //如果流程已走完，修改计划的状态
			task.getPlanInstance().getPlan().setStatus((short)5);
			task.getPlanInstance().setStatus(PlanInstance.FINISHED);
		} else { //流程还未走完，取下一个流程节点，并推送新的待办事项
			PlanStep step = steps.get(i+1);
			step.setStatus(PlanStep.EXECUTING);
			task.getPlanInstance().setCurrentStep(step);
			
			List<PlanTask> nextTasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where planInstance=?1 and step=?2", 
					PlanTask.class, task.getPlanInstance(), step);
			
			for(PlanTask tmp : nextTasks){
				tmp.setStatus(PlanTask.EXECUTING);
				
				PendingTask aPendingTask = PendingTaskServiceUtil.createPendingTaskByPlanTask(tmp);
				commonDao.saveEntity(aPendingTask);
			}
		}
	}
	
	@Override
	public Map<PlanStep, List<PlanTask>> findPreTasksByTaskId(Long taskId){
		PlanTask task = commonDao.readEntityById(taskId, PlanTask.class);
		List<PlanStep> steps = task.getPlanInstance().getPlan().getSteps();
		int i=0;
		Map<PlanStep, List<PlanTask>> ret = new HashMap<PlanStep, List<PlanTask>>();
		for(;i<steps.size(); i++){
			if(steps.get(i).getId().equals(task.getStep().getId())){
				break;
			}
			List<PlanTask> preTasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where planInstance=?1 and step=?2", PlanTask.class, task.getPlanInstance(), steps.get(i));
			ret.put(steps.get(i), preTasks);
		}
		return ret;
	}
}
