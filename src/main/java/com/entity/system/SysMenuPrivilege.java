package com.entity.system;
import java.util.Vector;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
public class SysMenuPrivilege {
	
	
	   public String role_id="";
       public String level_code="";
       
       
       public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String levelCode) {
		level_code = levelCode;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String roleId) {
		role_id = roleId;
	}
	
	
		public SysMenuPrivilege(){
	  	  
	    }
		
		
	    public SysMenuPrivilege(String id){
	  	  try
			{
	  		  
				DBObject db = new DBObject();
				String sql = "select * from SYSTEM_MENU_PRIVILLEGE where role_id=?";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(id) };
				DataTable dt = db.runSelectQuery(sql, pp);
				if (dt != null && dt.getRowsCount() == 1)
				{
					DataRow r = dt.get(0);
					this.role_id = id;
					this.level_code = r.getString("level_code");	
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
				String sql = "insert into SYSTEM_MENU_PRIVILLEGE(role_id,level_code) values(?,?)";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(this.role_id), new Parameter.String(this.level_code) };// new Parameter.Int(this.roleid)
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
		
		public boolean Insert(SysMenuPrivilege[] u)//批量插入
		{
			try
			{
				//System.out.println(u.length);
				for(int i=0;i<u.length;i++)
				{
					this.role_id=u[i].getRole_id();
					//System.out.println(this.role_id);
					this.level_code=u[i].getLevel_code();
					if(!this.Insert())
						return false;
				}
				return true;
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
		public boolean Update()
		{
			try
			{
				DBObject db = new DBObject();
				String sql = "update SYSTEM_ROLE set,role_name=? where role_id=?";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(this.level_code),new Parameter.Int(Integer.parseInt(this.role_id))};
			
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
		 * 删除
		 * 
		 * @param BmString
		 * @return
		 */
		public boolean Delete(String role_id)
		{
			try
			{
				DBObject db = new DBObject();
				String sql = "delete from SYSTEM_MENU_PRIVILLEGE where role_id="+role_id;
			
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

      
