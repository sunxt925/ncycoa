package com.dao.system;
import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class IndexDao {

	public DataTable getIndex(String pindexcode){
		try {
			DBObject db = new DBObject();
			String base_sql = "select  indexcode,indexname,scoreperiod,parentindexcode,standardscore from tbm_indexitem"
					+" where indexcode like '"+pindexcode+"%' order by indexcode";

			return db.runSelectQuery(base_sql);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private  int count=0;
	public  String getTreeNew(String indexccm)
	{
		try
		{
			
			StringBuilder sbBuilder=new StringBuilder();
			DBObject db = new DBObject();
			String sql = "";
			DataTable dt = getIndex(indexccm);
		    sbBuilder.append("[");
			if (dt != null && dt.getRowsCount() > 0)
			{
				
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					count++;
					DataRow r = dt.get(i);
					String ccm = r.getString("indexcode");
					String index_name = r.getString("indexname");
					String scoreperiod = r.getString("scoreperiod");
					double standardscore = Double.parseDouble(r.getString("standardscore"));
					sbBuilder.append("{");
					sbBuilder.append("\"id\":").append("\""+Format.replacePointtoOth(ccm,"n")+"\"").append(",");
					sbBuilder.append("\"indexname\":").append("\""+index_name+"\"").append(",");
					sbBuilder.append("\"period\":").append("\""+scoreperiod+"\"").append(",");
					sbBuilder.append("\"standardscore\":").append("\""+standardscore+"\"").append(",");
					
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
					String string = dt.get(i).getString("parentindexcode");
					if(dt.get(i).getString("parentindexcode").equals(pccm)){
						count++;
						DataRow r = dt.get(i);
						String ccm = r.getString("indexcode");
						String index_name = r.getString("indexname");
						String scoreperiod = r.getString("scoreperiod");
						double standardscore = Double.parseDouble(r.getString("standardscore"));
						
						sbBuilder.append("{");
						sbBuilder.append("\"id\":").append("\""+Format.replacePointtoOth(ccm,"n")+"\"").append(",");
						sbBuilder.append("\"indexname\":").append("\""+index_name+"\"").append(",");
						sbBuilder.append("\"period\":").append("\""+scoreperiod+"\"").append(",");
						sbBuilder.append("\"standardscore\":").append("\""+standardscore+"\"").append(",");
						
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
