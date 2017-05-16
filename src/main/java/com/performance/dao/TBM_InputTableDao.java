package com.performance.dao;

import com.db.DBObject;
import com.db.DataTable;

public class TBM_InputTableDao
{
	public String getTableNo(String archCode)
	{
		if(archCode == null || archCode.equals("")) return "";
		
		String res = "";
		try
		{
			DBObject db = new DBObject();
			String sql = "select tableno from tbm_inputtable where indexarchcode='" + archCode + "'";
			DataTable dt = db.runSelectQuery(sql);
			
			if (dt != null && dt.getRowsCount() > 0)
			{
				res = dt.get(0).getString(0);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return res;
	}
}
