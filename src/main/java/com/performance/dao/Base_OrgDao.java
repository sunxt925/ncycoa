package com.performance.dao;

import com.db.DBObject;
import com.db.DataTable;

public class Base_OrgDao
{
	public DataTable getOrgs(String orgCode)
	{
		DataTable dt = null;
		try
		{
			DBObject db = new DBObject();
			String sql =  "select * from base_org where orgcode like '" + orgCode + "%' order by orgcode";
			
			dt = db.runSelectQuery(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return dt;
	}
}
