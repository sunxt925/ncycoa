package com.dao.system;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class GroupTree {

	// ÓÃ»§Ê÷
	public static String getTreeNew(String unitccm, String pageurl,
			String pagetarget)
	{
		try
		{
			if (unitccm.equals(""))
				unitccm = "1000";
			String res = "";
			int pre_level = 0;
			DBObject db = new DBObject();
			String sql = "";
			//sql = "select * from query_group where group_code like '" + unitccm
			//		+ "%' and group_code!='" + unitccm + "' order by group_code";
			sql = "select * from query_group where group_code like '" + unitccm
			+ "%'" +" order by group_code";
			// System.out.print(sql);
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					String ccm = r.getString("group_code");
					//String orgclass = r.getString("orgclass");
					String parent_ccm =r.getString("pgroup_code");
					//String parent_ccm = ccm.substring(0, ccm.length() - 4);
					String unit_name = r.getString("group_name");// ccm.substring(0,
					// ccm.length()
					// - 3);
					String p_isparent = "";
					String p_url = "";
					if (parent_ccm.equals(""))
					{
						parent_ccm = "1000";
						p_isparent = ",isParent:true";
					}
					else{
						p_url = ",url:\"" + pageurl + "?group_code=" + ccm +"&group_name="+unit_name +
								 "\",target:\"" + pagetarget + "\"";
					}
					res = res + "{id:" + ccm + ",pId:" + parent_ccm
							+ ",name:\"" + unit_name + "\"" + p_url
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
