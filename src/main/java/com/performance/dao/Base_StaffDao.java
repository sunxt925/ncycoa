package com.performance.dao;

import com.db.DBObject;
import com.db.DataTable;

public class Base_StaffDao
{
	public DataTable getStaffByOrg(String orgCode)
	{
		DataTable dt = null;
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select staffcode,staffname,idcard,gender from base_orgmember where orgcode='"+orgCode+"'"; 
			dt=db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return dt;
	}
}
