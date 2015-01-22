package com.dao.system;

import java.util.List;
import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class LoadCode {

	public String table_name="";
	public String code_id="";
	public String code_name="";
	public String level_id="";
	public String pcode_id="";
	public String pcode_name="";
	protected static List tb = null;// 编码表内容
	
	/*
	 * 新增一行编码
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
	
			String sql = "insert into system_tablecodemeta_col (table_name,code_id,code_name,level_id,pcode_id,pcode_name) values (?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{       new Parameter.String(this.table_name), 
					new Parameter.String(this.code_id),
					new Parameter.String(this.code_name),
					new Parameter.String(this.level_id),
					new Parameter.String(this.pcode_id),
					new Parameter.String(this.pcode_name)};
					
					
			if (db.run(sql, pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static DataTable getSelectItem()
	{
		
		DBObject db = new DBObject();
		try {
			
			DataTable dt = db
					.runSelectQuery("select table_name from system_tablecodemeta");
			
			
			return dt;
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
		
	}

	public static DataTable getSelectItem(String item)
	{
		
		tb=Bm.tb;
		int sm=Bm.bm.indexOf(item.toUpperCase());
		try {
			DataTable dt=(DataTable) tb.get(sm);
			return dt;
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
		
	}

	public String getTable_name() {
		return table_name;
	}


	public void setTable_name(String tableName) {
		table_name = tableName;
	}


	public String getCode_id() {
		return code_id;
	}

	public void setCode_id(String codeId) {
		code_id = codeId;
	}

	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String codeName) {
		code_name = codeName;
	}


	public String getLevel_id() {
		return level_id;
	}


	public void setLevel_id(String levelId) {
		level_id = levelId;
	}


	public String getPcode_id() {
		return pcode_id;
	}


	public void setPcode_id(String pcodeId) {
		pcode_id = pcodeId;
	}


	public String getPcode_name() {
		return pcode_name;
	}


	public void setPcode_name(String pcodeName) {
		pcode_name = pcodeName;
	}
	
}
