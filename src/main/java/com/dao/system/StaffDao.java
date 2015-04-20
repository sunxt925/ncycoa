package com.dao.system;

import java.util.ArrayList;
import java.util.List;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class StaffDao {

	public static String getMemberJson(String orgcode){
		try
		{
			
			StringBuilder sbBuilder=new StringBuilder();
			DBObject db = new DBObject();
			String sql = "";
			sql = "select * from base_orgmember where  orgcode like '"+orgcode+"%' order by positioncode,staffcode";
			
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			sbBuilder.append("[");
			if (dt != null && dt.getRowsCount() >= 1)
			{
				
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					sbBuilder.append("{");
					sbBuilder.append("\"positioncode\":").append("\""+r.getString("positioncode")+"\"").append(",");
					sbBuilder.append("\"positionname\":").append("\""+r.getString("positionname")+"\"").append(",");
					sbBuilder.append("\"orgcode\":").append("\""+r.getString("orgcode")+"\"").append(",");
					sbBuilder.append("\"orgname\":").append("\""+r.getString("orgname")+"\"").append(",");
					sbBuilder.append("\"staffcode\":").append("\""+r.getString("staffcode")+"\"").append(",");
					sbBuilder.append("\"staffname\":").append("\""+r.getString("staffname")+"\"").append("");
					sbBuilder.append("}").append(",");
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
	public static List<String> getstafflistByname(String staffname){
		try {
			
			String sql="select staffcode from base_orgmember where staffcode in (select a.staffcode from base_staff a where a.staffname like '%"+staffname+"%') order by orgcode,positioncode,staffcode";
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			List<String> stafflist=new ArrayList<String>();
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow row=null;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					stafflist.add(row.getString("staffcode"));
				}
			}
			return stafflist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static List<String> getstafflistByorg(String orgcode){
		try{
			
			String sql="select staffcode from base_orgmember where orgcode like '"+orgcode+"%' order by orgcode,positioncode,staffcode";
			if(orgcode.equals("NC.00")){
				   sql="select orgcode,staffcode from base_orgmember where substr(orgcode,7,2)<'20'  order by orgcode,positioncode,staffcode";
			}
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			List<String> stafflist=new ArrayList<String>();
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow row=null;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					stafflist.add(row.getString("staffcode"));
				}
			}
			return stafflist;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
}
