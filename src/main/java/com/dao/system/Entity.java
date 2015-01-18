package com.dao.system;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class Entity {

	
	public static DataTable getSelectItem()
	{
		
		DBObject db = new DBObject();
		try {
			DataTable dt = db
					.runSelectQuery("select table_name from system_tablecodemeta");
			return dt;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
	}

	public static DataTable getSelectItem(String item)
	{
		
		DBObject db = new DBObject();
		try {
			DataTable dt = db
					.runSelectQuery("select code_name from system_tablecodemeta_col where table_name='"+item+"'");
			return dt;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
	}
}
