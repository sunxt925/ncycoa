package com.entity.query;

import java.util.*;

import com.common.Format;
import com.db.*;

/**
 * @author ��ï
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class User
{
	public String username = "";// �û���

	public String chinesename = "";// ������

	public String password = "";// ����

	

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
	 * ����
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
	 * ����
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
	 * @return ���� bz��
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param bz
	 *            Ҫ���õ� bz��
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return ���� ccm��
	 */
	public String getChinesename()
	{
		return chinesename;
	}

	/**
	 * @param ccm
	 *            Ҫ���õ� ccm��
	 */
	public void setChinesename(String chinesename)
	{
		this.chinesename = chinesename;
	}

	/**
	 * @return ���� cdmc��
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param cdmc
	 *            Ҫ���õ� cdmc��
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return ���� ckmc��
	 */
	
}