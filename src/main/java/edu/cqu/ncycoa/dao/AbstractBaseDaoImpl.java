package edu.cqu.ncycoa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;

public abstract class AbstractBaseDaoImpl implements AbstractBaseDao {
	
	protected abstract EntityManager getEntityManager();
	protected abstract JdbcTemplate getJdbcTemplate();
	protected abstract NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(); 
	
	@Override
	public <T> T readEntityById(Object id, Class<T> clazz) {
		EntityManager em = getEntityManager();
		return em.find(clazz, id);
	}
	
	@Override
	public <PK, T> List<T> readEntitiesByIds(Set<PK> ids, Class<T> clazz) {
		EntityManager em = getEntityManager();
		List<T> entities = new ArrayList<T>();
		for(PK id : ids){
			entities.add(em.find(clazz, id));
		}
		return entities;
	}
	
	@Override
	public <T> T readEntityByProperty(String propertyName, Object value, Class<T> clazz) {
		EntityManager em = getEntityManager();
		TypedQuery<T> query = new TypedQueryBuilder<T>(clazz, "e").addRestriction("e." + propertyName, "=", value).toQuery(em);
		return query.getSingleResult();
	}
	
	@Override
	public <T> List<T> readEntitiesByProperty(String propertyName, Object value, Class<T> clazz) {
		EntityManager em = getEntityManager();
		TypedQuery<T> query = new TypedQueryBuilder<T>(clazz, "e").addRestriction("e." + propertyName, "=", value).toQuery(em);
		return query.getResultList();
	}
	
	@Override
	public <T> List<T> readEntitiesByProperties(String propertyName, Set<Object> values, Class<T> clazz) {
		TypedQueryBuilder<T> tqb = new TypedQueryBuilder<T>(clazz, "e");
		if(values != null && !values.isEmpty()) {
			tqb.addRestriction("e." + propertyName, "in", values);
		}
		EntityManager em = getEntityManager();
		TypedQuery<T> query = tqb.toQuery(em);
		return query.getResultList();
	}
	
	@Override
	public <T> List<T> readAllEntities(Class<T> clazz) {
		EntityManager em = getEntityManager();
		TypedQuery<T> query = new TypedQueryBuilder<T>(clazz, "e").toQuery(em);
		return query.getResultList();
	}
	
	@Override
	public void deleteEntityById(Object id, Class<?> clazz) {
		Object entity = getEntityManager().find(clazz, id);
		if(entity != null) {
			getEntityManager().remove(entity);
		}
	}
	
	@Override
	public <T> T saveEntity(T entity) {
		EntityManager em = getEntityManager();
		if(em.contains(entity)) {
			return em.merge(entity);
		} else {
			em.persist(entity);
			return entity;
		}
	}
	
	@Override
	public <T> T updateEntity(T entity) {
		EntityManager em = getEntityManager();
		return em.merge(entity);
	}
	
	@Override
	public <T> T readEntityByJPQL(String jpql, Class<T> clazz) {
		TypedQuery<T> query = getEntityManager().createQuery(jpql, clazz);
		return query.getSingleResult();
	}
	
	@Override
	public <T> List<T> readEntitiesByJPQL(String jpql, Class<T> clazz, Object... params) {
		TypedQuery<T> query = getEntityManager().createQuery(jpql, clazz);
		return query.getResultList();
	}
	
	@Override
	public int executeJPQL(String jpql) {
		return getEntityManager().createQuery(jpql).executeUpdate();
	}
	
	@Override
	public List<Map<String, Object>> readSetBySql(String sql, Object... objs) {
		return getJdbcTemplate().queryForList(sql, objs);
	}
	
	@Override
	public Map<String, Object> readSingleBySql(String sql, Object... objs) {
		return getJdbcTemplate().queryForMap(sql, objs);
	}
	
	@Override
	public int executeSql(String sql, List<Object> params) {
		return getJdbcTemplate().update(sql, params);
	}
	
	@Override
	public int executeSql(String sql, Object... params) {
		return getJdbcTemplate().update(sql, params);
	}
	
	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		return getNamedParameterJdbcTemplate().update(sql, params);
	}
	
	@Override
	public Object executeSqlReturnKey(String sql, Map<String, Object> params) {
		Object keyValue = null;
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource sqlp = new MapSqlParameterSource(params);
			getNamedParameterJdbcTemplate().update(sql, sqlp, keyHolder);
			keyValue = keyHolder.getKey().longValue();
		} catch (Exception e) {
			keyValue = null;
		}
		
		return keyValue;
	}
	
}
