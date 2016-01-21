package edu.cqu.ncycoa.target.service;

import java.text.DecimalFormat;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class TargetService {
	private int count=0;

	public static String getNextArchCode(){
		String result = null;
			
			try {
				DBObject db = new DBObject();
				String sql = "select INDEX_CODE from TBM_OBJINDEX order by index_code desc";
				//Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

				DataTable dt = db.runSelectQuery(sql);
				if (dt != null&& dt.getRowsCount() >= 1) {
					
						DataRow r = dt.get(0);
						result=r.getString("index_code");
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
					
					if(dt.get(i).getString("parent��index��code").equals(pccm)){
						count++;
						DataRow r = dt.get(i);
						String ccm = r.getString("index_code");
						String index_name = r.getString("index��name");
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
			return sbBuilder.toString();
		} catch (Exception e) {
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
}
