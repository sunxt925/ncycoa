package edu.cqu.ncycoa.dao;

import edu.cqu.ncycoa.common.dto.DataGridReturn;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;

public interface CommonDao extends AbstractBaseDao {
	public <T> DataGridReturn getDataGridReturn(final QueryDescriptor<T> cq, final boolean isOffset);
}