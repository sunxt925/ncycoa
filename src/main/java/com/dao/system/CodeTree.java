package com.dao.system;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class CodeTree {

	//±àÂëÊ÷
	public static String getTreeNew(String table_name, String pageurl,
			String pagetarget)
	{
		try
		{
			if (table_name.equals(""))
				table_name = "0";
			String res = "";
			int pre_level = 0;
			DBObject db = new DBObject();
			String sql = "";
			sql="select * from system_tablecodemeta_col where table_name='"+table_name+"' order by code_id";
			
			//sql="select * from system_tablecodemeta_col where table_name='"+table_name+"' order by level_id";
			
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					//String ccm = r.getString("level_id");
					String ccm = r.getString("code_id");
					String parent_ccm = r.getString("pcode_id");
					//String parent_ccm = ccm.substring(0, ccm.length() - 4);
					String code_name = r.getString("code_name");// ccm.substring(0,
					// ccm.length()
					// - 3);
					String p_isparent = "";
					String p_url = "";
					if (parent_ccm.equals(""))
					{
						parent_ccm = "0";
						p_isparent = ",isParent:true";
					}
					else
					{
						//p_url = ",url:\"" + pageurl + "?table_name=" + table_name +"&level_id="+ccm+"&code_name="+code_name
						//		+ "\",target:\"" + pagetarget + "\"";
						p_url = ",url:\"" + pageurl + "?table_name=" + table_name +"&code_id="+ccm+"&code_name="+code_name
						+ "\",target:\"" + pagetarget + "\"";
					}
					res = res + "{id:" + ccm + ",pId:" + parent_ccm
							+ ",name:\"" + code_name + "\"" + p_url
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
