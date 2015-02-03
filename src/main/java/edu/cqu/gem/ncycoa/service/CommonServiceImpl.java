package edu.cqu.gem.ncycoa.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.gem.common.dto.DataGridReturn;
import edu.cqu.gem.common.dto.QueryDescriptor;
import edu.cqu.gem.ncycoa.dao.CommonDao;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	
	@Resource(name="commonDao")
	private CommonDao commonDao;
	
	@Override
	public <T> T findEntityById(Object id, Class<T> clazz) {
		return commonDao.readEntityById(id, clazz);
	}

	@Override
	public <PK, T> List<T> findEntitiesByIds(Set<PK> ids, Class<T> clazz) {
		return commonDao.readEntitiesByIds(ids, clazz);
	}

	@Override
	public <T> T findEntityByProperty(String propertyName, Object value, Class<T> clazz) {
		return commonDao.readEntityByProperty(propertyName, value, clazz);
	}

	@Override
	public <T> List<T> findEntitiesByProperty(String propertyName, Object value, Class<T> clazz) {
		return commonDao.readEntitiesByProperty(propertyName, value, clazz);
	}

	@Override
	public <T> List<T> findEntitiesByProperties(String propertyName, Set<Object> values, Class<T> clazz) {
		return commonDao.readEntitiesByProperties(propertyName, values, clazz);
	}

	@Override
	public <T> List<T> findAllEntities(Class<T> clazz) {
		return commonDao.readAllEntities(clazz);
	}

	@Override
	public void removeEntityById(Object id, Class<?> clazz) {
		commonDao.deleteEntityById(id, clazz);
	}

	@Override
	public <T> T addEntity(T entity) {
		return commonDao.saveEntity(entity);
	}
	
	@Override
	public <T> T findEntityByJPQL(String jpql, Class<T> clazz) {
		return commonDao.readEntityByJPQL(jpql, clazz);
	}
	
	@Override
	public <T> List<T> readEntitiesByJPQL(String jpql, Class<T> clazz, Object... params) {
		return commonDao.readEntitiesByJPQL(jpql, clazz, params);
	}
	
	@Override
	public int executeJPQL(String jpql) {
		return commonDao.executeJPQL(jpql);
	}
	
	@Override
	public List<Map<String, Object>> findSetBySql(String sql, Object... objs) {
		return commonDao.readSetBySql(sql, objs);
	}
	
	@Override
	public Map<String, Object> findSingleBySql(String sql, Object... objs) {
		return commonDao.readSingleBySql(sql, objs);
	}
	
	@Override
	public int executeSql(String sql, List<Object> params) {
		return commonDao.executeSql(sql, params);
	}
	
	@Override
	public int executeSql(String sql, Object... params) {
		return commonDao.executeSql(sql, params);
	}
	
	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		return commonDao.executeSql(sql, params);
	}
	
	@Override
	public Object executeSqlReturnKey(String sql, Map<String, Object> params) {
		return commonDao.executeSql(sql, params);
	}
	
	public DataGridReturn getDataGridReturn(final QueryDescriptor cq, final boolean isOffset) {
		return commonDao.getDataGridReturn(cq, isOffset);
	}
	
}
