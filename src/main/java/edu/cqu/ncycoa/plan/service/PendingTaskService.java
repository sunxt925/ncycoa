package edu.cqu.ncycoa.plan.service;

import java.util.List;

import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.plan.domain.PlanStep;

public interface PendingTaskService extends CommonService{

	public void runPlan(Long planId);

	public void handlePendingTask(Long taskId);

	public List<PlanStep> findPlanStepsByPlanId(Long planId);

}