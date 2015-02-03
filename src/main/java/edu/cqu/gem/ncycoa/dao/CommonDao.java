package edu.cqu.gem.ncycoa.dao;

import edu.cqu.gem.common.dto.DataGridReturn;
import edu.cqu.gem.common.dto.QueryDescriptor;

public interface CommonDao extends AbstractBaseDao {
	public <T> DataGridReturn getDataGridReturn(final QueryDescriptor<T> cq, final boolean isOffset);
}