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
		archcode=archcode.substring(0, 7);
		List<String> resultList=new ArrayList<String>();
		try {
			DBObject db = new DBObject();

			String sql = "select object_code from TBM_OBJINDEXARCHUSER where  indexarch_code = '" + archcode + "'";
			System.out.println(sql);
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
	
	public static String getDataByYears(String archcode,String objcode,String startyear,String endyear,String season){
		archcode=archcode.substring(0, 7);
		objcode=objcode.substring(0,objcode.length()-1);
		String objs="";
		String scores="";
		String years="";
		try {
			DBObject db = new DBObject();

			String sql = "select * from TBM_OBJTOTALSCORE where arch_code like '" + archcode + "%' and obj_code='"+objcode+"' and season='"+season+"'";
			System.out.println(sql);
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() >= 0) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					//objs=objs+r.getString("OBJ_CODE")+",";
					years=years+r.getString("YEAR")+",";
					scores=scores+r.getString("TOTALSCORE")+",";
				}
			}
			//objs=objs.substring(0, objs.length()-1);
			years=years.substring(0,years.length()-1);
			scores=scores.substring(0,scores.length()-1);
			return years+";"+scores;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getDataBySeasons(String archcode,String objcode,String startyear,String endyear){
		archcode=archcode.substring(0, 7);
		objcode=objcode.substring(0,objcode.length()-1);
		String objs="";
		String scores="";
		String seasons="";
		try {
			DBObject db = new DBObject();

			String sql = "select * from TBM_OBJTOTALSCORE where arch_code like '" + archcode + "%' and obj_code='"+objcode+"'";
			System.out.println(sql);
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() >= 0) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					//objs=objs+r.getString("OBJ_CODE")+",";
					seasons=seasons+r.getString("SEASON")+",";
					scores=scores+r.getString("TOTALSCORE")+",";
				}
			}
			//objs=objs.substring(0, objs.length()-1);
			seasons=seasons.substring(0,seasons.length()-1);
			scores=scores.substring(0,scores.length()-1);
			return seasons+";"+scores;
		} catch (Exception e) {
			return null;
		}
	}
	

}
