/*
 * 创建日期 2006-7-18
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.entity.system;

import java.util.ArrayList;
import java.util.List;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

/**
 * @author admin
 * 
 *         TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class UserInfo
{

	String username;// 用户名

	boolean superuser = false;// 是否超级用户

	String ip = "";// 登录IP
	String defaultid="";//默认角色
	
	private List<SystemRole> roles = new ArrayList<SystemRole>();
	public List<SystemRole> getRoles(){
		return roles;
	}
	public void setRoles(List<SystemRole> roles){
		this.roles = roles;
	}
	
	public String getRoleJson(){
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		sb.append("[");
		for(SystemRole role : roles){
			sb.append("{rolecode:'").append(role.getRolecode()).append("'");
			sb.append(",rolename:'").append(role.getRolename()).append("'");
			if((defaultid.isEmpty() && flag) || defaultid.equals(role.getRolecode())){
				sb.append(",selected:true");
				flag = false;
			}
			sb.append("},");
		}
		if(sb.length() > 1) sb.delete(sb.length() - 1, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	public String getDefaultid() {
		return defaultid;
	}

	public void setDefaultid(String defaultid) {
		this.defaultid = defaultid;
	}

	String staffcode = "";// 职工编码

	String availwidth = "1366"; //当前屏幕可用宽度
	String avalilheight = "728";//当前屏幕可用高度
	int perpage_full = 20;//整页时每页可显示行数
	int perpage_half = 10;//分两栏时每页可显示行数
	int perpage_third = 7;//分三栏时每页可显示行数

	public UserInfo()
	{

	}

	public UserInfo(String in_uid)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from system_user where user_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(in_uid) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.username = in_uid;
				this.staffcode = r.getString("staffcode");
				this.defaultid=r.getString("defaultroleid");
				if (r.getString("issuperuser").equals("1"))
				{
					this.setSuperuser(true);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getStaffcode()
	{
		return staffcode;
	}

	public void setStaffcode(String staffcode)
	{
		this.staffcode = staffcode;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public boolean isSuperuser()
	{
		return superuser;
	}

	public void setSuperuser(boolean superuser)
	{
		this.superuser = superuser;
	}

	public String getAvailwidth()
	{
		return availwidth;
	}

	public void setAvailwidth(String availwidth)
	{
		if (!availwidth.equals(""))
		{
			this.availwidth = availwidth;
		}
	}

	public String getAvalilheight()
	{
		return avalilheight;
	}

	public void setAvalilheight(String avalilheight)
	{
		if (!avalilheight.equals(""))
		{
			this.avalilheight = avalilheight;
			this.perpage_full = (Integer.parseInt(this.avalilheight) - 100) / 47;
			this.perpage_half = (Integer.parseInt(this.avalilheight) - 100) / 86;
			this.perpage_third = (Integer.parseInt(this.avalilheight) - 100) / 188;
			// System.out.print("perpage_full="+this.perpage_full);
			
			System.out.println(Integer.parseInt(this.avalilheight));
		}
	}

	public int getPerpage_full()
	{
		return perpage_full;
	}

	public void setPerpage_full(int perpageFull)
	{
		perpage_full = perpageFull;
	}

	public int getPerpage_half()
	{
		return perpage_half;
	}

	public void setPerpage_half(int perpageHalf)
	{
		perpage_half = perpageHalf;
	}

	public int getPerpage_third()
	{
		return perpage_third;
	}

	public void setPerpage_third(int perpageThird)
	{
		perpage_third = perpageThird;
	}
}