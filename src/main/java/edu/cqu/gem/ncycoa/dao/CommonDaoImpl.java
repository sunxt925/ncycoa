package edu.cqu.gem.ncycoa.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.cqu.gem.common.dto.DataGridReturn;
import edu.cqu.gem.common.dto.QueryDescriptor;
import edu.cqu.gem.common.util.dao.TypedQueryBuilder;
import edu.cqu.gem.ncycoa.util.PagerUtil;

@Repository("commonDao")
public class CommonDaoImpl extends AbstractBaseDaoImpl implements CommonDao {

	@PersistenceContext(unitName = "ncycoaPU")
	protected EntityManager em;

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	/**
	 * 返回easyui datagrid DataGridReturn模型对象
	 */
	public <T> DataGridReturn getDataGridReturn(final QueryDescriptor<T> cq, final boolean isOffset) {
		TypedQueryBuilder<T> tqBuilder = cq.getTqBuilder();
		TypedQuery<Long> countTQ = tqBuilder.toCountQuery(em);
		final long allCounts = countTQ.getSingleResult();
		
		TypedQuery<T> tq = tqBuilder.toQuery(em);
		int pageSize = cq.getPageSize();// 每页显示数
		int curPageNO = PagerUtil.validatePageNO((int)allCounts, cq.getCurPage(), pageSize);// 当前页
		int offset = PagerUtil.getOffset((int)allCounts, curPageNO, pageSize);
		if (isOffset) {// 是否分页
			tq.setFirstResult(offset);
			tq.setMaxResults(cq.getPageSize());
		} else {
			pageSize = (int)allCounts;
		}
		List<?> list = tq.getResultList();
		cq.getDataGrid().setResults(list);
		cq.getDataGrid().setTotal((int)allCounts);
		return new DataGridReturn((int)allCounts, list);
	}

}
