package edu.cqu.ncycoa.target.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQRestriction;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.dao.AbstractBaseDaoImpl;
import edu.cqu.ncycoa.safety.domain.Accident;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;

public class ResultService extends AbstractBaseDaoImpl{

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<String> getObjCodesByArch(String archcode) {
		List<String> resultList=new ArrayList<String>();
		try {
			DBObject db = new DBObject();

			String sql = "select object_code from TBM_OBJINDEXARCHUSER where  indexarch_code = '" + archcode + "'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() >= 0) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					resultList.add(r.getString(0));
				}
			}
			return resultList;
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
