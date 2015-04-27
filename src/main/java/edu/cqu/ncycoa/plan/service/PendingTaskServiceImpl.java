package edu.cqu.ncycoa.plan.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.CommonServiceImpl;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.plan.domain.PendingTask;
import edu.cqu.ncycoa.util.SystemUtils;

@Service("pendingTaskService")
@Transactional
public class PendingTaskServiceImpl extends CommonServiceImpl {

	
}
