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

public class TargetService extends AbstractBaseDaoImpl{
	
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
private int count=0;
	public static String getNextArchCode(String classT){
		String result = null;
			
			try {
				DBObject db = new DBObject();
				String sql = "select INDEX_CODE from TBM_OBJINDEX where index_code like '"+classT+"%' order by index_code desc";
				//Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

				DataTable dt = db.runSelectQuery(sql);
				if (dt != null&& dt.getRowsCount() >= 1) {
					
						DataRow r = dt.get(0);
						result=r.getString("index_code");
				}else{
					result=classT+"01";
					return result;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String s = null;
			s=result.substring(0, 3);
			String temp=s.substring(s.length()-2,s.length());
			System.out.println("temp:"+temp);
			DecimalFormat df = new DecimalFormat("00"); 
			String temp2 = df.format(Integer.parseInt(temp)+1);
			result=result.charAt(0)+temp2;
			System.out.println("result:"+result);
			return result;
	}
	
	public  String getTreeNew(String indexccm)
	{
		
		try
		{
			StringBuilder sbBuilder=new StringBuilder();
			DBObject db = new DBObject();
			String sql = "";
			sql = "select * from tbm_objindex where index_code like '" + indexccm
					+ "%' order by index_code";
			System.out.println(sql);
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			sbBuilder.append("[");
			if (dt != null && dt.getRowsCount() > 0)
			{
				
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					count++;
					DataRow r = dt.get(i);
					String ccm = r.getString("index_code");
					String index_name = r.getString("index_name");
					sbBuilder.append("{");
					sbBuilder.append("\"id\":").append("\""+ccm+"\"").append(",");
					sbBuilder.append("\"text\":").append("\""+index_name+"\"").append(",");
					sbBuilder.append("\"children\":").append("[");
					sbBuilder.append(getChild(dt,ccm));
					sbBuilder.append("]");
					sbBuilder.append("}").append(",");
					i=count;
				}
				sbBuilder.delete(sbBuilder.length()-1, sbBuilder.length());
				

			}
			sbBuilder.append("]");
			System.out.println(sbBuilder.toString());
			return sbBuilder.toString();
		}
		catch (Exception e)
		{
			
			return "";
		}
	}
	
	public   String getChild(DataTable dt,String pccm){
		try {
			
			StringBuilder sbBuilder=new StringBuilder();
		
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = count; i < dt.getRowsCount(); i++)
				{
					
					System.out.println(dt.get(i).getString("PARENT_INDEX_CODE"));
					System.out.println(pccm);
					if(dt.get(i).getString("PARENT_INDEX_CODE").equals(pccm)){
						System.out.println("this");
						count++;
						DataRow r = dt.get(i);
						String ccm = r.getString("INDEX_CODE");
						String index_name = r.getString("INDEX_NAME");
						sbBuilder.append("{");
						sbBuilder.append("\"id\":").append("\""+ccm+"\"").append(",");
						sbBuilder.append("\"text\":").append("\""+index_name+"\"").append(",");
						sbBuilder.append("\"children\":").append("[");
						sbBuilder.append(getChild(dt,ccm));
						sbBuilder.append("]");
						sbBuilder.append("}").append(",");
					}
					
					
				}
				sbBuilder.delete(sbBuilder.length()-1, sbBuilder.length());
				

			}
			System.out.println("child:"+sbBuilder.toString());
			return sbBuilder.toString();
		} catch (Exception e) {
			System.out.println("catchle");
			return "";
		}
	}

	public static String getNextIndexCode(String pcode) {
		String result = null;
		
		try {
			DBObject db = new DBObject();
			String sql = "select INDEX_CODE from TBM_OBJINDEX where parent_index_code='"+pcode+"' order by index_code desc";
			//Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				
					DataRow r = dt.get(0);
					result=r.getString("index_code");
			}else{
				result=pcode+".001";
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String s = null;
		s=result.substring(8, result.length());
		DecimalFormat df = new DecimalFormat("000"); 
		String temp2 = df.format(Integer.parseInt(s)+1);
		result=pcode+"."+temp2;
		System.out.println(result);
		return result;
	}

	public static List<ObjIndexItem> getArchByClass(String string) {
		List<ObjIndexItem> result = new ArrayList<ObjIndexItem>();
		
		try {
			DBObject db = new DBObject();
			String sql = "select * from TBM_OBJINDEX where PARENT_INDEX_CODE='-1' and index_code like '"+string+"%'";
			//Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					ObjIndexItem item=new ObjIndexItem();
					DataRow r = dt.get(i);
					try {
						item.setIndexCode(r.getString("index_code"));
						item.setIndexName(r.getString("INDEX_NAME"));
						item.setIndexDesc(r.getString("index_desc"));
						item.setVersion(r.getString("version"));
						item.setScoreSumLow(Double.parseDouble(r.getString("SCORE_SUMLOW")));
						item.setScoreSumMax(Double.parseDouble(r.getString("SCORE_SUMMAX")));
//						item.setValidBeginDate(new Date(r.getString("VALID_BEGINDATE")));
						System.out.println(r.getString("VALID_BEGINDATE"));
//						item.setValidEndDate(new Date(r.getString("VALID_ENDDATE")));
						item.setIsParent(r.getString("ISPARENT"));
						item.setParentIndexCode(r.getString("PARENT_INDEX_CODE"));
						result.add(item);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<ObjIndexItem> getArchEntidies(String string){
		TypedQueryBuilder<ObjIndexItem> tqBuilder=new TypedQueryBuilder<ObjIndexItem>(ObjIndexItem.class, "e");
		tqBuilder.addRestriction(new TQRestriction("index_code", "like", string+"%"));
		tqBuilder.addRestriction(new TQRestriction("PARENT_INDEX_CODE", "=", "-1"));
		TypedQuery<ObjIndexItem> query = tqBuilder.toQuery(em);
		return query.getResultList();
	}


}
