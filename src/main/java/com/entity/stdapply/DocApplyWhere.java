package com.entity.stdapply;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class DocApplyWhere {
	public DocApplyWhere(){}
	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}
	public String getStaffcode() {
		return staffcode;
	}
	public void setApplywhere(String applywhere) {
		this.applywhere = applywhere;
	}
	public String getApplywhere() {
		return applywhere;
	}
	int id=0;
	private String staffcode="";
	private String applywhere="";
	public boolean insert(){
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into std_applywhere(id,staffcode,applywhere) values(recid.nextVal,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.staffcode) ,new Parameter.String(this.applywhere) };// new Parameter.Int(this.roleid)
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
	public DataTable getTowhereByStaffcode(String staffcode)
	{
		try {
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select applywhere from std_applywhere where staffcode ="+staffcode);
			return dt;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public boolean DeleteByStaffcode(String staffcode)
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql="delete from std_applywhere where staffcode like '" +staffcode+"'";
			if (db.run(sql))
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
	
}
