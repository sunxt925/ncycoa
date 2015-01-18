package com.entity.system;

import java.util.*;

import com.common.Format;
import com.db.*;

/**
 * @author ��ï
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class UserLogin
{
	public String usercode = "";// �û���

	public String staffcode = "";// Ա�����

	public String password = "";// ����

	

	public UserLogin()
	{

	}

	public UserLogin(String staffcode)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_USER where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(staffcode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.usercode = r.getString("user_code");
				this.staffcode = r.getString("staffcode");
				this.password = r.getString("user_password");
			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean isexist(String staffcode)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_USER where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(staffcode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
			  // System.out.println("true");
			   return true;
			
			}
			else
			{
				// System.out.println("false");
				 return false;
			}
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
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
			String sql = "insert into SYSTEM_USER(user_code,staffcode,user_password) values (?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.usercode), new Parameter.String(this.staffcode),
					new Parameter.String(pwd_md5)
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
			String sql = "update SYSTEM_USER set user_password=? where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ 
					new Parameter.String(this.password),
					new Parameter.String(this.staffcode)
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

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getStaffcode() {
		return staffcode;
	}

	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


	
	
}