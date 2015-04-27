package edu.cqu.ncycoa.dao;

import org.springframework.web.servlet.tags.form.OptionTag;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.Staff;

import edu.cqu.ncycoa.util.SystemUtils;

public class SupplierDao {

	public static String getGoodsType(){
		DBObject db = new DBObject();
		String result="";
		String sql = "";
		sql = "select goodsname from COM_GOODSCLASS where parentgoodscode='WF.01'";
		DataTable dt = null;
		dt = db.runSelectQuery(sql);
		if (dt != null && dt.getRowsCount() >= 1)
		{
			for (int i = 0; i < dt.getRowsCount(); i++)
			{
				//DataRow r = dt.get(i);
				result=result+"<option value='"+i+"'>";
				try {
					result=result+dt.get(i).get(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				result=result+"</option>";
				System.out.println(result);
			}
		}
		return result;
	}

	public static String getDepart() {
		//Staff staff=new Staff(SystemUtils.getSessionUser().getStaffcode());
		String result="";
		
		try {
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGMEMBER where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<option value='"+r.getString("orgname")+"'>";
					try {
						result=result+r.getString("orgname");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</option>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getEvaluContent(String year){
		String result="";
		DBObject db = new DBObject();
		String sql = "";
		sql = "select INDEX_NAME,INDEX_OPTION from NCYCOA_EVALU_DEFINE where EVALU_YEAR=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(year) };
		DataTable dt = null;
		int count=0;
		try {
			dt = db.runSelectQuery(sql,pp);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (dt != null && dt.getRowsCount() >= 1)
		{
			for (int i = 0; i < dt.getRowsCount(); i++)
			{
				DataRow r = dt.get(i);
				try {
					if(r.get(1).toString().equals("()=()")){
						result=result+"<tr><td colspan=2 style='background-color: rgb(250, 204, 57);text-align: center;'>类别:"+r.get(0).toString()+"</td></tr>";
					}else{
						result=result+"<tr><td style='background-color:rgba(121, 154, 241, 0.94)' width: 50px;>评价项"+(++count)+":"+"</td><td style='background-color:rgba(152, 201, 255, 0.94)'>";
						result=result+r.get(0)+"</td>";
						String option=r.get(1).toString();
						String[] optionText=splitOption(option,0);
						String[] optionScore=splitOption(option,1);
						for(int j=0;j<optionScore.length;j++)
						{
							result=result+"<tr><td colspan=2>&nbsp;&nbsp;&nbsp;<input type='radio' name='option"+count+"' value='"+optionScore[j]+"' />";
							result=result+optionText[j]+"("+optionScore[j]+"分)";
							result=result+"</td></tr>";
						}
				
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(result);
			}
		}
		return result;
	}

	private static String[] splitOption(String option, int i) {
		String[] temp=option.split("=");
		if(i==0){
			String text=temp[0].substring(1, temp[0].length()-1);
			String[] textStrings=text.split(",");
			return textStrings;
		}else if(i==1){
			String score=temp[1].substring(1, temp[1].length()-1);
			String[] scoreStrings=score.split(",");
			return scoreStrings;
		}
		return null;
	}

	public static String getSupplier() {
		String temp=getDepartName();
		String[] departs=temp.substring(0, temp.length()-1).split(",");

		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select supplier_name from NCYCOA_SUPPLIER t where manage_depart='"+departs[0]+"'";
			for(int j=1;j<departs.length;j++){
				sql=sql+" or manage_depart='"+departs[j]+"'";
			}
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<option value='"+r.getString("supplier_name")+"'>";
					try {
						result=result+r.getString("supplier_name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</option>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String getDepartName() {
		String result="";
		
		try {
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGMEMBER where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+r.getString("orgname")+",";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int getContentCount() {
		DBObject db = new DBObject();
		String sql = "";
		sql = "select INDEX_NAME,INDEX_OPTION from NCYCOA_EVALU_DEFINE where EVALU_YEAR='2015' and index_option<>'()=()'";
		DataTable dt = null;
		int count=0;
		dt = db.runSelectQuery(sql);
		count=dt.getRowsCount();
		return count;
	}

	public static void removeByName(String name) {
		DBObject db = new DBObject();
		String sql = "";
		sql = "delete from NCYCOA_SUPPLIER where supplier_name=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(name) };
		 
		try {
			db.run(sql, pp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getAllSupplier() {
		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select supplier_name from NCYCOA_SUPPLIER t ";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<option value='"+r.getString("supplier_name")+"'>";
					try {
						result=result+r.getString("supplier_name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</option>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean isEvalued(String depart, String supplier, String year) {
		DBObject db = new DBObject();
		String sql = "select * from NCYCOA_EVALU_RESULT where evalu_depart=? and evalu_supplier=? and evalu_year=? ";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(depart),new Parameter.String(supplier),new Parameter.String(year) };
		try {
			DataTable dt = db.runSelectQuery(sql,pp);
			if(dt != null&& dt.getRowsCount() >= 1)
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void startEvalu() {
		DBObject db = new DBObject();
		String sql = "update NCYCOA_SUPPLIER_FLAG set FLAG_VALUE=? where FLAG_PARA=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String("T") ,new Parameter.String("isStartEvalu") };
        
		try {
			db.run(sql,pp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeEvalu() {
		DBObject db = new DBObject();
		String sql = "update NCYCOA_SUPPLIER_FLAG set FLAG_VALUE=? where FLAG_PARA=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String("F") ,new Parameter.String("isStartEvalu") };
        
		try {
			db.run(sql,pp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
