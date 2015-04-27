package com.entity.system;
import java.util.Vector;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
public class UserRole {
	
    private String role_id;
    private String user_code;
    private String unit_ccm;
    
	public UserRole(){
    }
    public UserRole(String id){
    	  try
  		{
    		  
  			DBObject db = new DBObject();
  			String sql = "select * from SYSTEM_USER_ROLE where role_id=?";
  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
  			{ new Parameter.String(id) };
  			DataTable dt = db.runSelectQuery(sql, pp);
  			if (dt != null && dt.getRowsCount() == 1)
  			{
  				DataRow r = dt.get(0);
  				this.role_id = id;
  				this.user_code = r.getString("user_code");	
  				this.unit_ccm = r.getString("unit_ccm");
  			}
  			
  		}
  		catch (Exception e)
  		{
  			e.printStackTrace();
  		}
      }
    /**
	 * ÌíÈë
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into SYSTEM_USER_ROLE(role_id,user_code,unit_ccm) values(?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(Integer.parseInt(this.role_id)), new Parameter.String(this.user_code),new Parameter.String(this.unit_ccm) };// new Parameter.Int(this.roleid)
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
	 * ¸üÐÂ
	 * 
	 * @return
	 */
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update SYSTEM_USER_ROLE set role_id=?,user_code=?,unit_ccm=? where role_id=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(Integer.parseInt(this.role_id)),new Parameter.String(this.user_code),new Parameter.String(this.unit_ccm),new Parameter.Int(Integer.parseInt(this.role_id))};
		
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
	 * É¾³ý
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
				v.add("delete from SYSTEM_USER_ROLE where role_id like '" + Integer.parseInt(BmString)
						+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from SYSTEM_USER_ROLE where role_id like '" +Integer.parseInt(bm[i])
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
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String roleId) {
		role_id = roleId;
	}

	
    public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String userCode) {
		user_code = userCode;
	}

	
    
    public String getUnit_ccm() {
		return unit_ccm;
	}
	public void setUnit_ccm(String unitCcm) {
		unit_ccm = unitCcm;
	}
	
	
}
