package com.dao.system;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class IndexTreeTe {
	private  int count=0;
	public  String getTreeNew(String indexccm)
	{
		try
		{
			
			StringBuilder sbBuilder=new StringBuilder();
			DBObject db = new DBObject();
			String sql = "";
			sql = "select * from tbm_indexitem where indexcode like '" + indexccm
					+ "%' order by indexcode";
			
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			sbBuilder.append("[");
			if (dt != null && dt.getRowsCount() > 0)
			{
				
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					count++;
					DataRow r = dt.get(i);
					String ccm = r.getString("indexcode");
					String index_name = r.getString("indexname");
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
					
					if(dt.get(i).getString("parentindexcode").equals(pccm)){
						count++;
						DataRow r = dt.get(i);
						String ccm = r.getString("indexcode");
						String index_name = r.getString("indexname");
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
}
