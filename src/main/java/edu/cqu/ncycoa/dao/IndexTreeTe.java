package edu.cqu.ncycoa.dao;

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
			sql = "select * from NCYCOA_EVALU_DEFINE where index_code like '" + indexccm
					+ "%' order by index_code";
			
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			
//			DBObject db = new DBObject();
//			String sql = "";
//			sql = "select * from NCYCOA_EVALU_DEFINE where index_parentcode= '" + indexccm
//					+ "' order by index_code";
//			
//			DataTable dt = null;
//			dt = db.runSelectQuery(sql);
//			
//			DBObject db2 = new DBObject();
//			String sql2 = "";
//			sql2 = "select * from NCYCOA_EVALU_DEFINE where index_parentcode like '" + indexccm
//					+ ".%' order by index_code";
//			
//			DataTable dt2 = null;
//			dt2 = db2.runSelectQuery(sql2);
			
			sbBuilder.append("[");
			if (dt != null && dt.getRowsCount() > 0)
			{
				System.out.println(dt.get(4).getString("index_name"));
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					count++;
					//System.out.println("i="+i+"count="+count);
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
					i--;
					//System.out.println(sbBuilder.toString());
				}
				sbBuilder.delete(sbBuilder.length()-1, sbBuilder.length());
				

			}
			sbBuilder.append("]");
			//System.out.println(sbBuilder.toString());
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
					
					if(dt.get(i).getString("index_parentcode").equals(pccm)){
						count++;
						//System.out.println("sub:i="+i+"count="+count);
						DataRow r = dt.get(i);
						String ccm = r.getString("index_code");
						String index_name = r.getString("index_name");
						sbBuilder.append("{");
						sbBuilder.append("\"id\":").append("\""+ccm+"\"").append(",");
						sbBuilder.append("\"text\":").append("\""+index_name+"\"");
						
						sbBuilder.append("}").append(",");
						//System.out.println(sbBuilder.toString());
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
