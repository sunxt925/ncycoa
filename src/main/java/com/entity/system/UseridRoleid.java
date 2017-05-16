package com.entity.system;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
public class UseridRoleid {
	private String user_code;
	
	private String staffcode;
	private String issupperuser;
	private String rolecode;
	private String level_code;
	public UseridRoleid()
	{
		
	}
	
	
	public static boolean isSuperAdmin(String staffcode){
		
		try
		{
			//System.out.println(usercode);
			DBObject db = new DBObject();
			boolean issuperadmin = false;
			String base_sql="select user_code,s.staffcode,issuperuser,rolecode from system_user s inner JOIN system_role_member srm on srm.staffcode=s.staffcode where user_code='"+staffcode.substring(staffcode.length()-6, staffcode.length())+"' order by user_code";
			//System.out.println(base_sql);
			//String base_sql = "select user_code,s.staffcode,issuperuser,rolecode,level_code from system_user s inner JOIN system_role_member srm  inner JOIN system_menu_privillege smp on smp.role_id=srm.rolecode  on srm.staffcode=s.staffcode where user_code='"+usercode+"'";

			//String sql_run = Format.getFySql(base_sql, pageno, perpage);
			DataTable dataTable = db.runSelectQuery(base_sql);
			for(int i=0;i<dataTable.getRowsCount();i++){
				DataRow row = dataTable.get(i);
				if(row.getString("rolecode").equals("007")||row.getString("rolecode").equals("004")){
					issuperadmin = true;
					break;
				}
				
			}
			
			return issuperadmin;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public DataTable getUseridRoleid(String usercode)
	{
		try
		{
			//System.out.println(usercode);
			DBObject db = new DBObject();
			
			String base_sql="select user_code,s.staffcode,issuperuser,rolecode from system_user s inner JOIN system_role_member srm on srm.staffcode=s.staffcode where user_code='"+usercode+"' order by user_code";
			//System.out.println(base_sql);
			//String base_sql = "select user_code,s.staffcode,issuperuser,rolecode,level_code from system_user s inner JOIN system_role_member srm  inner JOIN system_menu_privillege smp on smp.role_id=srm.rolecode  on srm.staffcode=s.staffcode where user_code='"+usercode+"'";

			//String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getUseridDepartmentidRoleid(String usercode)
	{
		try
		{
			//System.out.println(usercode);
			DBObject db = new DBObject();
			
			String base_sql="select user_code,s.staffcode,issuperuser,rolecode from system_user s  inner JOIN base_orgmember bo on bo.staffcode=s.staffcode inner JOIN System_Role_Position srp on srp.positioncode=bo.positioncode where user_code='"+usercode+"' union select user_code,s.staffcode,issuperuser,rolecode from system_user s inner JOIN system_role_member srm on srm.staffcode=s.staffcode where user_code='"+usercode+"' order by user_code";
			//System.out.println(base_sql);
			//String base_sql = "select user_code,s.staffcode,issuperuser,rolecode,level_code from system_user s inner JOIN system_role_member srm  inner JOIN system_menu_privillege smp on smp.role_id=srm.rolecode  on srm.staffcode=s.staffcode where user_code='"+usercode+"'";

			//String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getUserpositionRole(String usercode)
	{
		try
		{
			//System.out.println(usercode);
			DBObject db = new DBObject();
			
			String base_sql="select user_code,s.staffcode,issuperuser,rolecode from system_user s inner JOIN base_orgmember bo on bo.staffcode=s.staffcode inner JOIN System_Role_Position srp on srp.positioncode=bo.positioncode where user_code='"+usercode+"' order by user_code";
			//System.out.println(base_sql);
			//String base_sql = "select user_code,s.staffcode,issuperuser,rolecode,level_code from system_user s inner JOIN system_role_member srm  inner JOIN system_menu_privillege smp on smp.role_id=srm.rolecode  on srm.staffcode=s.staffcode where user_code='"+usercode+"'";

			//String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String userCode) {
		user_code = userCode;
	}
	public String getStaffcode() {
		return staffcode;
	}
	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}
	public String getIssupperuser() {
		return issupperuser;
	}
	public void setIssupperuser(String issupperuser) {
		this.issupperuser = issupperuser;
	}
	public String getRolecode() {
		return rolecode;
	}
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String levelCode) {
		level_code = levelCode;
	}
}
