package edu.cqu.ncycoa.plan.service;

import java.util.Date;
import java.util.List;

import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.plan.domain.Asset;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.plan.domain.PlanInstance;
import edu.cqu.ncycoa.plan.domain.PlanStep;
import edu.cqu.ncycoa.plan.domain.PlanTask;

public interface PendingTaskService extends CommonService{

	public void handleTask(Long pendingTaskId, Long planTaskId, String result, Short type);

	public void handleAuditTask(Long pendingTaskId, Long planTaskId, int audit, Short type);

	public abstract List<PlanTask> findPreTasksByTaskId(Long taskId);

	public abstract void handleUploadTask(Long pendingTaskId, Long planTaskId, List<Asset> assets, Short type);

}