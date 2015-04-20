package com.dao.system;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class IndexTree {
	public static String getTreeNew(String indexccm, String pageurl,
			String pagetarget)
	{
		try
		{
			//if (indexccm.equals(""))
			//	indexccm = "NC";
			String res = "";
			int pre_level = 0;
			DBObject db = new DBObject();
			String sql = "";
			sql = "select * from tbm_indexitem where indexcode like '" + indexccm
					+ "%' order by indexcode";
			// System.out.print(sql);
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					String ccm = r.getString("indexcode");
					//String orgclass = r.getString("orgclass");
					
					String parent_ccm = r.getString("parentindexcode");
					String index_name = r.getString("indexname");// ccm.substring(0,
					// ccm.length()
					// - 3);
					String p_isparent = "";
					String p_url = "";
					if (parent_ccm.equals(""))
					{
						parent_ccm = "0";
						p_isparent = ",isParent:true";
					}
					else{
						p_url = ",url:\"" + pageurl + "?indexccm=" + ccm +"&indexname="+index_name
								+ "\",target:\"" + pagetarget + "\"";
					}
					res = res + "{id:'" + ccm + "',pId:'" + parent_ccm
							+ "',name:\"" + index_name + "\"" + p_url
							+ p_isparent + "}\r";
					if (i != dt.getRowsCount() - 1)
					{
						res = res + ",";
					}
				}

			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
