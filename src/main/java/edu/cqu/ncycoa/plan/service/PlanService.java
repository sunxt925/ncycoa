package edu.cqu.ncycoa.plan.service;

import java.util.List;
import java.util.Map;

import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.plan.domain.DptReview;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;

public interface PlanService extends CommonService{

	public void runPlan(Long planId);

	public List<PlanStep> findPlanStepsByPlanId(Long planId);

	public Map<PlanStep, List<PlanTask>> findPlanTasks(PlanInstance planInstance);

	public PlanInstance findPlanInstanceByPlanId(Long planId);
	
	public void auditAndRunPlan(Long planId, Boolean isPassed);

	public abstract Map<PlanStep, List<PlanTask>> findAllTasksByPlanId(Long planId);

	public abstract void userReview(Long id, String result);

	public abstract PlanInstance findPlanInstanceByTaskId(Long taskId);

	public abstract void dptReview(Long id, String result);

}