package com.entity.system;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class SystemRole {

	private String rolecode;
	private String rolename;
    private String rolemode;
    private String ValidFlag;
    private String newrolecode;
    public String getNewrolecode() {
		return newrolecode;
	}
	public void setNewrolecode(String newrolecode) {
		this.newrolecode = newrolecode;
	}
    public String getRolecode() {
		return rolecode;
	}
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRolemode() {
		return rolemode;
	}
	public void setRolemode(String rolemode) {
		this.rolemode = rolemode;
	}
	public String getValidFlag() {
		return ValidFlag;
	}
	public void setValidFlag(String validFlag) {
		ValidFlag = validFlag;
	}

    
    
	public SystemRole(){
  	  
    }
    public SystemRole(String rolecode){
  	  try
		{
  		  
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_ROLE where ROLECODE=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(rolecode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.rolecode = rolecode;
				this.rolename = r.getString("ROLENAME");
				this.rolemode=r.getString("ROLEMODE");
				this.ValidFlag=r.getString("VALIDFLAG");
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
    

	/**
	 * ����
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into SYSTEM_ROLE(ROLECODE,ROLENAME,rolemode,ValidFlag) values(?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.rolecode), new Parameter.String(this.rolename),new Parameter.String(this.rolemode),new Parameter.Int(this.ValidFlag) };// new Parameter.Int(this.roleid)
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
	
	/**
	 * ����
	 * 
	 * @return
	 */
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update SYSTEM_ROLE set ROLECODE=?,ROLENAME=?,rolemode=?,ValidFlag=? where ROLECODE=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.newrolecode), new Parameter.String(this.rolename),new Parameter.String(this.rolemode),new Parameter.Int(this.ValidFlag),new Parameter.String(this.rolecode) };
		
			if (db.run(sql,pp))
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

	/**
	 * ɾ��
	 * 
	 * @param BmString
	 * @return
	 */
	public boolean Delete(String BmString)
	{
		try
		{
			Vector v = new Vector();
			DBObject db = new DBObject();
			if (BmString.indexOf(",") == -1)
			{
				v.add("delete from SYSTEM_ROLE where rolecode like '" +BmString
						+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from SYSTEM_ROLE where rolecode like '" +bm[i]
							+ "%'");
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
	public DataTable getRoleList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||rolecode||'\">' as ѡ��,rolecode as ��ɫ����,rolename as ��ɫ����,rolemode as ��ɫģʽ,ValidFlag as ��Ч��־,'<a href=\"#\" onclick=F1(\"'||rolecode||'\") class=\"button4\" >�� ��</a> <a href=\"systemrole_position_manage.jsp?bm='||rolecode||'\" class=\"button4\" target=\"temp\">��ɫ��λ</a> <a href=\"systemrole_member_manage.jsp?bm='||rolecode||'\" class=\"button4\" target=\"temp\">��ɫ��Ա</a> <a href=\"#\" onclick=F6(\"'||rolecode||'\") class=\"button4\">ɾ��</a>' as ����  from SYSTEM_ROLE order by rolecode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/*public DataTable getRoleList(){
		try
		{
		DBObject db = new DBObject();
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}}*/
		
	
	public DataTable getAllRoleList()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from SYSTEM_ROLE");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	  
}
