package edu.cqu.ncycoa.plan.service;

import java.util.List;
import java.util.Map;

import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.plan.domain.Asset;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;

public interface PendingTaskService extends CommonService{

	public Map<PlanStep, List<PlanTask>> findPreTasksByTaskId(Long taskId);

	public void handleTask(Long pendingTaskId, Long planTaskId, String description, List<Asset> assets);

	public void handleNotAdmission(Long pendingTaskId, Long planTaskId);

}