/*
 * 创建日期 2006-7-18
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.dao.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.common.Format;
import com.common.UserException;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.OrgPosition;
import com.entity.system.SystemRole;
import com.entity.system.UserInfo;
import com.entity.system.UseridRoleid;

/**
 * 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class User
{
	public static void loginOut(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.removeAttribute("UserInfo"); // 在当前会话中清除当前用户
	}

	public static void login(String name, String pwd, HttpServletRequest request) throws Exception
	{
		DBObject db = new DBObject();
		HttpSession session = request.getSession();
		session.removeAttribute("UserInfo"); // 在当前会话中清除当前用户
		if (name == null) { throw new Exception("用户名为空！"); }
		name = name.trim();
		// 将密码进行MD5加密
		String pwd_md5 = Format.getMD5(pwd);
		// System.out.print(pwd_md5);
		// 根据name从数据库取得用户的基础信息信息
		String sqlStr = "select * from system_user where user_code=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(name) };
		// String pwd = "";
		UserInfo UserInfo = null;
		DataTable dt = db.runSelectQuery(sqlStr, pp);
		if (dt != null && dt.getRowsCount() == 1)
		{
			if (!dt.get(0).getString("user_password").equals(pwd_md5))
			{
				throw new UserException("密码错误！");
			}
			else
			{
				UserInfo = new UserInfo(name);
				UserInfo.setIp(request.getRemoteAddr());
				UserInfo.setAvailwidth(Format.NullToBlank(request.getParameter("availwidth")));
				UserInfo.setAvalilheight(Format.NullToBlank(request.getParameter("availheight")));
				
				UseridRoleid ur = new UseridRoleid();
				DataTable dtUseridRoleid = ur.getUseridDepartmentidRoleid(UserInfo.getUsername());
				if (dtUseridRoleid != null)
				{
					for (int i = 0; i < dtUseridRoleid.getRowsCount(); i++)
					{
						DataRow r1 = dtUseridRoleid.get(i);
						SystemRole role = new SystemRole(r1.getString("rolecode"));
						UserInfo.getRoles().add(role);
						
						if(UserInfo.getDefaultid() == null || UserInfo.getDefaultid().equals("")){
							UserInfo.setDefaultid(role.getRolecode());
						}
					}
				}
				
				String staffcode = UserInfo.getStaffcode();
				OrgPosition orgPosition = new OrgPosition();
				// DataTable dt2=orgPosition.getOrgposition(staffcode);
				DataTable dt2 = orgPosition.getOrgposition(staffcode);

				String orgcode = dt2.get(0).getString("orgcode");
				String staffname = dt2.get(0).getString("staffname");
				String positioncode = "";

				if (dt2.getRowsCount() == 1)
				{
					positioncode += dt2.get(0).getString("positioncode");
				}
				else
				{
					for (int i = 0; i < dt2.getRowsCount() - 1; i++)
					{

						positioncode += dt2.get(i).getString("positioncode") + ",";
					}
					positioncode += dt2.get(dt2.getRowsCount() - 1).getString("positioncode");
				}

//				com.cms.webflow.server.Util.InitServerPath(request);// 初试化webpath
//				com.cms.model.sysmng.login.User user = new com.cms.model.sysmng.login.User();
//				user.setZgdm(staffcode);
//				user.setZgxm(staffname);
//				user.setYybdm(orgcode);
//				user.setRolcod(positioncode);// 如果一人多岗位，positioncode用，分开，如：0111011,011012.
//				// user.setEmail(“market@emsflow.com”);
//				request.getSession().setAttribute("USER", user);
			}
		}
		else
		{
			throw new UserException("该用户不存在！");
		}
		// */
		// 将登陆成功的用户设置为当前用户设置
		session.setMaxInactiveInterval(3600 * 100);
		session.setAttribute("UserInfo", UserInfo);
	}

	// 获取员工岗位编码
	public static String getOrgCode(String staffcode)
	{
		try
		{
			OrgPosition orgPosition = new OrgPosition();
			// DataTable dt2=orgPosition.getOrgposition(staffcode);
			DataTable dt2 = orgPosition.getOrgposition(staffcode);

			return dt2.get(0).getString("orgcode");
			// String staffname=dt2.get(0).getString("staffname");
		}
		catch (Exception e)
		{
			return null;
			// TODO: handle exception
		}

	}
}