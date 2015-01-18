package com.performance.dao;

import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;

public class TBM_InputTableIndexDao
{
	public DataTable getIndices(String tableNo)
	{
		if(tableNo == null || tableNo.equals("")) return null;
		
		DataTable dt = null;
		try
		{
			DBObject db = new DBObject();
			String sql = "select indexcode as ָ�������, indexorder as ��ʾ˳��, enabled as ��Ч���, 'XXX' as ���� from tbm_inputtableindex where tableno='" + tableNo + "' order by indexorder";
			dt = db.runSelectQuery(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return dt;
	}
	
	public DataTable getIndices(String tableNo, int pageNo, int pageSize)
	{
		if(tableNo == null || tableNo.equals("")) return null;
		
		DataTable dt = null;
		try
		{
			DBObject db = new DBObject();
			String sql = "select indexcode as ָ�������, indexorder as ��ʾ˳��, enabled as ��Ч���, 'XXX' as ���� from tbm_inputtableindex where tableno='" + tableNo + "' order by indexorder";
			sql = Format.getFySql(sql, pageNo, pageSize);
			dt = db.runSelectQuery(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return dt;
	}
}
