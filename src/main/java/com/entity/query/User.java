package com.entity.query;

import java.util.*;

import com.common.Format;
import com.db.*;

/**
 * @author 林茂
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class User
{
	public String username = "";// 用户名

	public String chinesename = "";// 中文名

	public String password = "";// 密码

	

	public User()
	{

	}

	public User(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_USER where user_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.username = PK;
				this.username = r.getString("user_code");
				this.chinesename = r.getString("user_name");
				this.password = r.getString("user_password");
			
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
			String pwd_md5 = Format.getMD5(this.password);
			DBObject db = new DBObject();
			String sql = "insert into SYSTEM_USER(user_code,user_name,user_password) values (?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.username), new Parameter.String(this.chinesename),
					new Parameter.String(pwd_md5),
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
	public boolean Update()
	{
		try
		{
			String pwd_md5 = Format.getMD5(this.password);
			setPassword(pwd_md5);
			DBObject db = new DBObject();
			String sql = "update SYSTEM_USER set user_name=?,user_password=? where user_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ 
					new Parameter.String(this.chinesename),
					new Parameter.String(this.password),
					new Parameter.String(this.username)
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
				v.add("delete from SYSTEM_USER where user_code like '" + BmString
						+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from SYSTEM_USER where user_code like '" + bm[i]
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

	/**
	 * @return 返回 bz。
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param bz
	 *            要设置的 bz。
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return 返回 ccm。
	 */
	public String getChinesename()
	{
		return chinesename;
	}

	/**
	 * @param ccm
	 *            要设置的 ccm。
	 */
	public void setChinesename(String chinesename)
	{
		this.chinesename = chinesename;
	}

	/**
	 * @return 返回 cdmc。
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param cdmc
	 *            要设置的 cdmc。
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return 返回 ckmc。
	 */
	
}