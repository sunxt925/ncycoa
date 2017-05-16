package com.entity.system;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class RolePosition {
public String positioncode;
public String orgcode;
public String rolecode;
public String positionname;
public String orgname;
public String rolename;


public RolePosition(){
}

public RolePosition(String rolecode){
	  try
		{
		  
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_ROLE_POSITION where ROLECODE=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(rolecode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.positioncode = r.getString("POSITIONCODE");
				this.orgcode = r.getString("ORGCODE");
				this.rolecode=rolecode;
				this.positionname=r.getString("POSITIONNAME");
				this.orgname=r.getString("ORGNAME");
				this.rolename=r.getString("ROLENAME");
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
  }

public DataTable getRolePositionList(int pageno, int perpage,String rolecode)
{
	try
	{
		DBObject db = new DBObject();

		String base_sql = "select '<input type=\"checkbox\" id=\"'||positioncode||'.'||orgcode||'\" name=\"items\" value=\"'||positioncode||'.'||orgcode||'\">' as 选择,rolecode as 角色代码,ROLENAME 角色名称,positioncode as 岗位代码,positionname as 岗位名称, orgcode as 单位编码,ORGNAME as 单位名称  ,'<a href=\"#\" onclick=F6(\"'||positioncode||'.'||orgcode||'\") class=\"button4\">删除</a>' as 操作 from SYSTEM_ROLE_POSITION where rolecode like '"+rolecode+"'";

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
		DataTable dt = db.runSelectQuery("select * from SYSTEM_ROLE_POSITION where rolecode like'"+rolecode+"'");
		return dt;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public boolean Insert(String BmString ,String orgcode ,String rolecode)
{
	
	try
	{
		
		Vector v = new Vector();
		DBObject db = new DBObject();
		 SystemRole sr=new SystemRole(rolecode);
		if (BmString.indexOf(",") == -1)
		{  
			OrgPosition op=new OrgPosition(BmString,orgcode);
			v.add("insert into SYSTEM_ROLE_POSITION(POSITIONCODE,ORGCODE,ROLECODE,POSITIONNAME,ORGNAME,ROLENAME) values('"+BmString+"','"+orgcode+"','"+rolecode+"','"+op.getPositionname()+"','"+op.getOrgname()+"','"+sr.getRolename()+"')");
		
		
		}
		else
		{
			String[] bm = BmString.split(",");
			for (int i = 0; i < bm.length; i++)
			{
				OrgPosition op=new OrgPosition(bm[i],orgcode);
				v.add("insert into SYSTEM_ROLE_POSITION(POSITIONCODE,ORGCODE,ROLECODE,POSITIONNAME,ORGNAME,ROLENAME) values('"+bm[i]+"','"+orgcode+"','"+rolecode+"','"+op.getPositionname()+"','"+op.getOrgname()+"','"+sr.getRolename()+"')");
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
public boolean Delete(String BmString,String rolecode)
{
	try
	{
		Vector v = new Vector();
		DBObject db = new DBObject();
		if (BmString.indexOf(",") == -1)
		{
			
			String[] op = BmString.split("\\.");
			v.add("delete from SYSTEM_ROLE_POSITION where positioncode like '" +op[0]
					+ "%'and orgcode like'"+op[1]+ "%'and rolecode like'"+rolecode+ "%'");
		}
		else
		{
			String[] bm = BmString.split(",");
			for (int i = 0; i < bm.length; i++)
			{
				String[] op = bm[i].split("\\.");
				v.add("delete from SYSTEM_ROLE_POSITION where positioncode like '" +op[0]
				     + "%'and orgcode like'"+op[1]+ "%'and rolecode like'"+rolecode+ "%'");
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
public String getPositoncode() {
	return positioncode;
}
public void setPositoncode(String positoncode) {
	this.positioncode = positoncode;
}
public String getOrgcode() {
	return orgcode;
}
public void setOrgcode(String orgcode) {
	this.orgcode = orgcode;
}
public String getRolecode() {
	return rolecode;
}
public void setRolecode(String rolecode) {
	this.rolecode = rolecode;
}
public String getPositonname() {
	return positionname;
}
public void setPositonname(String positonname) {
	this.positionname = positonname;
}
public String getOrgname() {
	return orgname;
}
public void setOrgname(String orgname) {
	this.orgname = orgname;
}
public String getRolename() {
	return rolename;
}
public void setRolename(String rolename) {
	this.rolename = rolename;
}



}
