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
		//���üƻ�Ϊִ��̬
		plan.setStatus(PlanStatus.EXECUTTING);
		
		List<PlanStep> steps = plan.getSteps();
		if(steps != null && !steps.isEmpty()){
			//�ҵ�����ƻ��ĵ�һ�����̽ڵ㣬���øýڵ�Ϊ����״̬
			PlanStep step = steps.get(0);
			step.setStatus((short)1);
			
			//�����̽ڵ�ĸ����ˣ�����һ����������
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
			task.setContent("��Դ�ڼƻ�[ " + step.getPlan().getName() + " ]�Ĵ���������");
			task.setStatus((short)0);
			task.setGenDate(new Date());
			commonDao.saveEntity(task);
		}
	}
	
	@Override
	public void handlePendingTask(Long taskId){
		PendingTask task = commonDao.readEntityById(taskId, PendingTask.class);
		//�����Ѵ���ô�������
		task.setStatus((short)1);
		task.setHandleDate(new Date());
		
		//���ø������Ӧ�����̽ڵ��״̬Ϊ�������
		PlanStep step = task.getPlan();
		step.setStatus((short)2);
		
		List<PlanStep> steps = step.getPlan().getSteps();
		int i=0;
		for(;i<steps.size(); i++){
			if(steps.get(i).getId().equals(step.getId())){
				break;
			}
		}
		
		if(i == steps.size() - 1) { //������������꣬�޸ļƻ���״̬
			step.getPlan().setStatus((short)5);
		} else { //���̻�δ���꣬ȡ��һ�����̽ڵ㣬�������µĴ�������
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
			task.setContent("��Դ�ڼƻ�[ " + step.getPlan().getName() + " ]�Ĵ���������");
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
