package com.entity.system;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class UnitRole {
	public String rolecode;
	public String orgcode;
	public String getRolecode() {
		return rolecode;
	}
	
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public UnitRole(){
	  	  
    }
    public UnitRole(String ORGCODE){
  	  try
		{
  		  
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_USER_ROLE where ORGCODE=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(ORGCODE) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.orgcode = ORGCODE;
				this.rolecode= r.getString("ROLECODE");
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
    

	/**
	 * 添入
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into SYSTEM_USER_ROLE(ORGCODE,ROLECODE) values(?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.orgcode), new Parameter.String(this.rolecode)};// new Parameter.Int(this.roleid)
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
	 * 更新
	 * 
	 * @return
	 */
	/*public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update SYSTEM_ROLE set ROLECODE=?,ROLENAME=?,rolemode=?,ValidFlag=? where ROLECODE=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.newrolecode), new Parameter.String(this.rolename),new Parameter.String(this.rolemode),new Parameter.Int(Integer.parseInt(this.ValidFlag)),new Parameter.String(this.rolecode) };
		
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
*/
	/**
	 * 删除
	 * 
	 * @param BmString
	 * @return
	 */
	public boolean Delete(String BmString,String orgcode)
	{
		try
		{
			Vector v = new Vector();
			DBObject db = new DBObject();
			if (BmString.indexOf(",") == -1)
			{
				v.add("delete from SYSTEM_USER_ROLE where rolecode like '" +BmString
						+ "%'and orgcode like'"+orgcode+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from SYSTEM_USER_ROLE where rolecode like '" +bm[i]
							+ "%'and orgcode like'"+orgcode+ "%'");
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
	/*public DataTable getUnitRoleList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||rolecode||'\">' as 选择,rolecode as 角色代码,rolename as 角色名称,rolemode as 角色模式,ValidFlag as 有效标志,'<a href=\"systemrole_mod.jsp?bm='||rolecode||'\" class=\"button4\">修 改</a> ' as 操作  from SYSTEM_USER_ROLE";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}*/
	public DataTable getUnitRoleList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||rolecode||'\">' as 选择,rolecode as 角色编码,rolename as 角色名称,rolemode as 角色模式,ValidFlag as 有效标志 from unit_role where ORGCODE like'"+orgcode+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllUnitRoleList()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from SYSTEM_USER_ROLE");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	  
}
