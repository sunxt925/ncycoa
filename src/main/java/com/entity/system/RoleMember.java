package com.entity.system;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class RoleMember {
public String rolecode;
public String getRolecode() {
	return rolecode;
}

public void setRolecode(String rolecode) {
	this.rolecode = rolecode;
}

public String getStaffcode() {
	return staffcode;
}

public void setStaffcode(String staffcode) {
	this.staffcode = staffcode;
}
public String staffcode;

public RoleMember(){
}

public RoleMember(String rolecode){
	  try
		{
		  
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_ROLE_MEMBER where ROLECODE=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(rolecode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.staffcode = r.getString("staffcode");
				}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
  }

public DataTable getRoleMemberList(int pageno, int perpage,String rolecode)
{
	try
	{
		DBObject db = new DBObject();
		String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||a.staffcode||'\">' as 选择,a.rolecode as 角色代码,c.ROLENAME as 角色名称 ,a.staffcode as 职工编码 ,b.STAFFNAME as 职工名称,'<a href=\"#\" onclick=F6(\"'||a.staffcode||'\") class=\"button4\">删除</a>' as 操作 from SYSTEM_ROLE_MEMBER a ,base_staff b,SYSTEM_ROLE c where a.rolecode  like '"+rolecode+"' and a.rolecode=c.rolecode and a.staffcode=b.staffcode";

		String sql_run = Format.getFySql(base_sql, pageno, perpage);
		return db.runSelectQuery(sql_run);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public DataTable getAllRolePositionList(String rolecode)
{
	try
	{
		DBObject db = new DBObject();
		DataTable dt = db.runSelectQuery("select * from SYSTEM_ROLE_MEMBER where rolecode like'"+rolecode+"'");
		return dt;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public boolean Insert(String BmString ,String rolecode)
{
	
	try
	{
		
		Vector v = new Vector();
		DBObject db = new DBObject();
		// SystemRole sr=new SystemRole(rolecode);
		if (BmString.indexOf(",") == -1)
		{  
			//OrgPosition op=new OrgPosition(BmString,orgcode);
			v.add("insert into SYSTEM_ROLE_MEMBER(staffCODE,roleCODE) values('"+BmString+"','"+rolecode+"')");
		}
		else
		{
			String[] bm = BmString.split(",");
			for (int i = 0; i < bm.length; i++)
			{
				//OrgPosition op=new OrgPosition(bm[i],orgcode);
				v.add("insert into SYSTEM_ROLE_MEMBER(staffCODE,roleCODE) values('"+bm[i]+"','"+rolecode+"')");			}
		}
		if (db.executeBatch(v))
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
public boolean Delete(String BmString,String rolecode)
{
	try
	{
		Vector v = new Vector();
		DBObject db = new DBObject();
		if (BmString.indexOf(",") == -1)
		{
			
			//String[] op = BmString.split("\\.");
			v.add("delete from SYSTEM_ROLE_MEMBER where staffcode like '" +BmString
					+ "%'and rolecode like'"+rolecode+ "%'");
		}
		else
		{
			String[] bm = BmString.split(",");
			for (int i = 0; i < bm.length; i++)
			{
				//String[] op = bm[i].split("\\.");
				v.add("delete from SYSTEM_ROLE_MEMBER where staffcode like '" +bm[i]
						+ "%'and rolecode like'"+rolecode+ "%'");
			}
		}
		if (db.executeBatch(v))
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
