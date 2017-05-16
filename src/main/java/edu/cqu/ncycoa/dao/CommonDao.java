package edu.cqu.ncycoa.dao;

import java.util.List;

import edu.cqu.ncycoa.common.dto.DataGridReturn;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;

public interface CommonDao extends AbstractBaseDao {
	public <T> DataGridReturn getDataGridReturn(final QueryDescriptor<T> cq, final boolean isOffset);
	public <T> List<T> getQueryRes(TypedQueryBuilder<T> tqBuilder);
}