/*
 * 创建日期 2006-7-18
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.dao.system;

import com.db.*;
import com.entity.system.*;
import com.common.*;

/**
 * @author 林茂
 * 
 *         TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class Tree
{

	// 用户树
	public static String getTreeNew(String username)
	{
		try
		{
			UserInfo u = new UserInfo(username);
			// System.out.println("运行到这里");
			String res = "";
			// int pre_level = 0;
			// DBObject db = new DBObject();
			// String sql = "";
			if (u.getDefaultid().isEmpty())
			{
				UseridRoleid ur = new UseridRoleid();
				// System.out.println(username);
				// DataTable dt=ur.getUseridRoleid(username);
				DataTable dt = ur.getUseridDepartmentidRoleid(username);
				if (dt != null && dt.getRowsCount() > 0)
				{

					DataRow r = dt.get(0);
					DBObject db = new DBObject();
					String sql = "update system_user set defaultroleid='"
							+ r.getString("rolecode") + "' where user_code='"
							+ username + "'";
					db.run(sql);
					// u.setDefaultid(r.getString("rolecode"));
					res = getTreeNewByroid(r.getString("rolecode"));

				}
				// System.out.println(res);
			}
			/*
			 * if (u.isSuperuser()) { sql =
			 * "select * from system_menu order by level_code"; DataTable dt =
			 * null; dt = db.runSelectQuery(sql); // String
			 * url_t="home.jsp?url="; if (dt != null && dt.getRowsCount() > 0) {
			 * for (int i = 0; i < dt.getRowsCount(); i++) { DataRow r =
			 * dt.get(i); String cdmc = r.getString("menu_name"); String url =
			 * r.getString("menu_url"); String ccm = r.getString("level_code");
			 * String ccm_p = ccm.substring(0, ccm.length() - 3); if
			 * (ccm_p.equals("")) ccm_p = "0"; String p_url = ""; String
			 * p_isparent = ""; if (!url.equals("")) { p_url =
			 * ",url:\"home_jump.jsp?url=" + url + "@cdmc=" + cdmc +
			 * "\",target:\"mainframe\""; } else { p_isparent =
			 * ",isParent:true"; } res = res + "{id:" + Integer.parseInt(ccm) +
			 * ",pId:" + Integer.parseInt(ccm_p) + ",name:\"" + cdmc + "\"" +
			 * p_url + p_isparent + ",open:true}\r"; if (i != dt.getRowsCount()
			 * - 1) { res = res + ","; } } } }
			 */
			else
			{
				// sql = "";
				// System.out.println(u.getStaffcode());
				// System.out.println(u.getDefaultid());
				res = getTreeNewByroid(u.getDefaultid());

			}

			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public static String getTreeNewByroid(String roid)
	{
		try
		{
			// System.out.println("运行到这里");
			// System.out.println(roid);
			String sql = "select * from SYSTEM_MENU_PRIVILLEGE where role_id='"
					+ roid + "'";
			// UserInfo u = new UserInfo(username);
			String res = "";
			int pre_level = 0;
			DBObject db = new DBObject();
			// String sql = "";
			/*
			 * if (u.isSuperuser()) { sql =
			 * "select * from system_menu order by level_code"; } else { sql =
			 * ""; }
			 */
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			// String url_t="home.jsp?url=";
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);

					String ccm = r.getString("level_code");
					SystemModule systemm = new SystemModule(ccm);
					String cdmc = systemm.getMenu_name();
					String url = systemm.getMenu_url();
					String ccm_p = ccm.substring(0, ccm.length() - 3);
					if (ccm_p.equals(""))
						ccm_p = "0";
					String p_url = "";
					String p_isparent = "";
					if (!url.equals(""))
					{
						p_url = ",url:\"home_jump.jsp?url=" + url + "@cdccm="
								+ ccm + "@cdmc=" + cdmc
								+ "\",target:\"mainframe\"";
					}
					else
					{
						p_isparent = ",isParent:true";
					}
					res = res + "{id:" + ccm + ",pId:" + ccm_p + ",name:\""
							+ cdmc + "\"" + p_url + p_isparent
							+ ",open:true}\r";
					if (i != dt.getRowsCount() - 1)
					{
						res = res + ",";
					}
				}
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

}