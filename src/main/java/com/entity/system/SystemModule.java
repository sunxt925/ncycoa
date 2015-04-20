package com.entity.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class SystemModule {
	public String level_code;
	
	public String menu_name;
	public String menu_url;
	public String menu_type;
	public String newlevel_code;
	public int queryId;
	
	private List<SystemModule> children = new ArrayList<SystemModule>();
	public List<SystemModule> getChildren(){
		return children;
	}
	public void setChildren(List<SystemModule> children){
		this.children = children;
	}

	public SystemModule()
	{

	}

	
	public SystemModule(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from system_menu where level_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.level_code= PK;
				this.menu_name = r.getString("menu_name");
				this.menu_url = r.getString("menu_url");
				this.menu_type = r.getString("menu_type");
				
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
			String sql = "insert into system_menu(level_code,menu_name,menu_url,menu_type) values (?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.level_code),
					new Parameter.String(this.menu_name),
					new Parameter.String(this.menu_url),
					new Parameter.String(this.menu_type),
					//new Parameter.Int(this.queryId),
					 };
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

	public DataTable getMenuList(int pageno, int perpage,String level_code)
	{
		try
		{
			//System.out.println(level_code);
			DBObject db = new DBObject();
			String base_sql = "select '选择' as 选择,level_code,level_code as 层次码,menu_name as 菜单名称,menu_url as 菜单URL,menu_type as 菜单类型,'操作' as 操作  from system_menu where level_code like'"+level_code+"___'order by level_code";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			// System.out.print(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTable getMenuList(String level_code)
	{
		try
		{
			//System.out.println(level_code);
			DBObject db = new DBObject();
			String base_sql = "select '选择' as 选择,level_code,level_code as 层次码,menu_name as 菜单名称,menu_url as 菜单URL,menu_type as 菜单类型,'操作' as 操作  from system_menu where level_code like'"+level_code+"___'order by level_code";
			
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllOrgList(String level_code)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from system_menu where level_code like'"+level_code+"___'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update system_menu set level_code=?,menu_name=?,menu_url=?,menu_type=?where level_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.newlevel_code),
					new Parameter.String(this.menu_name),
					new Parameter.String(this.menu_url),
					new Parameter.String(this.menu_type),
					//new Parameter.String(this.queryId),
					new Parameter.String(this.level_code),
				 };
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
	 * 删除
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
				v.add("delete from system_menu where level_code like '" + BmString
						+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from system_menu where level_code like '" + bm[i]
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
	
	
	
	
	public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String levelCode) {
		level_code = levelCode;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menuName) {
		menu_name = menuName;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menuUrl) {
		menu_url = menuUrl;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menuType) {
		menu_type = menuType;
	}

	public String getNewlevel_code() {
		return newlevel_code;
	}


	public void setNewlevel_code(String newlevelCode) {
		newlevel_code = newlevelCode;
	}
//	public String getQueryId() {
//		return queryId;
//	}
//
//
//	public void setQueryId(String queryId) {
//		this.queryId = queryId;
//	}
}
