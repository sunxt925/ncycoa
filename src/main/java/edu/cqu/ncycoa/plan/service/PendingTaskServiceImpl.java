package edu.cqu.ncycoa.plan.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.system.Org;

import edu.cqu.ncycoa.common.service.CommonServiceImpl;
import edu.cqu.ncycoa.plan.PlanStatus;
import edu.cqu.ncycoa.plan.domain.Asset;
import edu.cqu.ncycoa.plan.domain.DptReview;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;
import edu.cqu.ncycoa.plan.domain.UserReview;

@Service("pendingTaskService")
@Transactional
public class PendingTaskServiceImpl extends CommonServiceImpl implements PendingTaskService {
	
	
	private static Logger LOG = LoggerFactory.getLogger(PendingTaskServiceImpl.class);
	
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
			task.getPlanInstance().getPlan().setStatus(PlanStatus.EXEC_FINISHING);
			task.getPlanInstance().setStatus(PlanInstance.FINISHED);
			task.getPlanInstance().setEndingDate(task.getHandleDate());
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
			
			prepareUserReviewData(task.getPlanInstance());
			prepareDepartReviewData(task.getPlanInstance());
			
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
	
	protected void prepareDepartReviewData(PlanInstance instance){
		
		String departId = instance.getPlan().getDepartId();
		Org depart = new Org(departId);
		
		Calendar now = Calendar.getInstance();
		List<DptReview> dptReviews = commonDao.readEntitiesByJPQL("select e from DptReview e where orgCode=?1 and year=?2 and month=?3", DptReview.class,
				departId, (short)now.get(Calendar.YEAR), (short)now.get(Calendar.MONTH));
		
		if(dptReviews == null || dptReviews.size() == 0) {
			
			DptReview tmpReivew = new DptReview();
			
			tmpReivew.setMonth((short)now.get(Calendar.MONTH));
			tmpReivew.setYear((short)now.get(Calendar.YEAR));
			tmpReivew.setOrgCode(departId);
			tmpReivew.setOrgName(depart.getName());
			tmpReivew.setResult("");
			
			commonDao.saveEntity(tmpReivew);
		} else {
			
			if(dptReviews.size() > 1) {
				LOG.warn("部门评价有重复数据 [ " + departId + "," + now.get(Calendar.YEAR) + "," + now.get(Calendar.MONTH) + "] ");
			}
			
			DptReview tmpReivew = dptReviews.get(0);
			if(!"".equals(tmpReivew.getResult())) {
				LOG.info("已经评价 [ " + departId + "," + now.get(Calendar.YEAR) + "," + now.get(Calendar.MONTH) + "] ");
			}
		}
	}
	
	protected void prepareUserReviewData(PlanInstance planInstance){
		List<PlanTask> allTasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where planInstance=?1", PlanTask.class, planInstance);
		Map<String, List<PlanTask>> p2Task = new HashMap<String, List<PlanTask>>();
		Map<String, String> p2Name = new HashMap<String, String>();
		Map<String, Integer> p2Over = new HashMap<String, Integer>();
		for(PlanTask tmpTask : allTasks) {
			if(!p2Name.containsKey(tmpTask.getParticipantCode())) {
				p2Name.put(tmpTask.getParticipantCode(), tmpTask.getParticipantName());
			}
			
			if(!p2Task.containsKey(tmpTask.getParticipantCode())) {
				p2Task.put(tmpTask.getParticipantCode(), new ArrayList<PlanTask>());
			}
			p2Task.get(tmpTask.getParticipantCode()).add(tmpTask);
			
			if(!p2Over.containsKey(tmpTask.getParticipantCode())) {
				p2Over.put(tmpTask.getParticipantCode(), 0);
			}
			
			if(tmpTask.getHandleDate().after(tmpTask.getStep().getEnding())) {
				p2Over.put(tmpTask.getParticipantCode(), p2Over.get(tmpTask.getParticipantCode()) + 1);
			}
		}
		
		for(String userid : p2Name.keySet()) {
			Calendar now = Calendar.getInstance();
			List<UserReview> usrReviews = commonDao.readEntitiesByJPQL("select e from UserReview e where participantCode=?1 and year=?2 and month=?3", UserReview.class,
					userid, (short)now.get(Calendar.YEAR), (short)now.get(Calendar.MONTH));
			if(usrReviews == null || usrReviews.size() == 0) {
				
				UserReview tmpReivew = new UserReview();
				
				tmpReivew.setMonth((short)now.get(Calendar.MONTH));
				tmpReivew.setYear((short)now.get(Calendar.YEAR));
				tmpReivew.setParticipantCode(userid);
				tmpReivew.setParticipantName(p2Name.get(userid));
				tmpReivew.setDepartId(planInstance.getPlan().getDepartId());
				tmpReivew.setResult("");
				tmpReivew.setStatistics(p2Over.get(userid)+ "/" + p2Task.get(userid).size());
				tmpReivew.setOverDeadTimeCounts(p2Over.get(userid));
				tmpReivew.setNoOverDeadTimeCounts(p2Task.get(userid).size() - p2Over.get(userid));
				
				commonDao.saveEntity(tmpReivew);
			} else {
				
				if(usrReviews.size() > 1) {
					LOG.warn("人员评价有重复数据 [ " + userid + "," + now.get(Calendar.YEAR) + "," + now.get(Calendar.MONTH) + "] ");
				}
				
				UserReview tmpReivew = usrReviews.get(0);
				
				if(!"".equals(tmpReivew.getResult())) {
					LOG.info("已经评价 [ " + userid + "," + now.get(Calendar.YEAR) + "," + now.get(Calendar.MONTH) + "] ");
				}
				
				int old = tmpReivew.getOverDeadTimeCounts() + tmpReivew.getNoOverDeadTimeCounts();
				tmpReivew.setOverDeadTimeCounts(p2Over.get(userid) + tmpReivew.getOverDeadTimeCounts());
				tmpReivew.setNoOverDeadTimeCounts(old + p2Task.get(userid).size() - tmpReivew.getOverDeadTimeCounts());
				tmpReivew.setStatistics(tmpReivew.getOverDeadTimeCounts()+ "/" + (old + p2Task.get(userid).size()) );
				commonDao.saveEntity(tmpReivew);
			}
		}
	}
	
	@Override
	public Map<PlanStep, List<PlanTask>> findPreTasksByTaskId(Long taskId){
		PlanTask task = commonDao.readEntityById(taskId, PlanTask.class);
		List<PlanStep> steps = task.getPlanInstance().getPlan().getSteps();
		int i=0;
		TreeMap<PlanStep, List<PlanTask>> ret = new TreeMap<PlanStep, List<PlanTask>>();
		for(;i<steps.size(); i++){
			if(steps.get(i).getId().equals(task.getStep().getId())){
				break;
			}
			List<PlanTask> preTasks = commonDao.readEntitiesByJPQL("select e from PlanTask e where planInstance=?1 and step=?2", PlanTask.class, task.getPlanInstance(), steps.get(i));
			ret.put(steps.get(i), preTasks);
		}
		return ret.descendingMap();
	}
	
}
