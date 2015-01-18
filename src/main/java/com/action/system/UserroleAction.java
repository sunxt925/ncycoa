package com.action.system;

import javax.servlet.http.HttpServletRequest;

import com.common.Format;
import com.action.*;
import com.entity.system.UserRole;

public class UserroleAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		UserRole userRole = null;

		if (action != null && action.equals("add"))
		{
			userRole = new UserRole();
			String users = Format
					.NullToBlank(request.getParameter("user_code"));
			String[] aa = users.split("\\|");

			for (int i = 0; i < aa.length; i++)
			{

				userRole.setRole_id(Format.NullToBlank(request
						.getParameter("role_id")));
				userRole.setUser_code(Format.NullToBlank(aa[i]));
				userRole.setUnit_ccm(Format.NullToBlank(request
						.getParameter("unit_ccm")));

				// System.out.println("执行到这里");
				/*
				 * System.out.println(userRole.getRole_id());
				 * System.out.println(userRole.getUser_code());
				 * System.out.println(userRole.getUnit_ccm());
				 */
				if (userRole.Insert())
				{
					res += "MessageBox.Show(null,'添加成功！',null,'LogOK',null,1,'添加成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
				}
				else
				{
					res += "MessageBox.Show(null,'添加失败！',null,'LogOK','Error',1,'添加失败，可能是由于角色ID重复，请检查！');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
				}
			}
		}
		else if (action != null && action.equals("modify"))
		{
			userRole = new UserRole();
			userRole.setRole_id(Format.NullToBlank(request
					.getParameter("role_id")));
			userRole.setUser_code(Format.NullToBlank(request
					.getParameter("user_code")));
			userRole.setUnit_ccm(Format.NullToBlank(request
					.getParameter("unit_ccm")));
			System.out.println("juese" + userRole.getRole_id());
			System.out.println(userRole.getRole_id());
			if (userRole.Update())
			{
				res += "MessageBox.Show(null,'修改成功！',null,'LogOK',null,1,'修改成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'修改失败！',null,'LogOK','Error',1,'修改失败，可能是由于角色ID重复，请检查！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
			}
		}
		else if (action != null && action.equals("del"))
		{
			String[] ids = request.getParameterValues("items");
			String para = "";
			for (int i = 0; i < ids.length; i++)
			{
				if (i == ids.length - 1)
				{
					para = para + ids[i];
				}
				else
				{
					para = para + ids[i] + ",";
				}
			}
			userRole = new UserRole();
			if (userRole.Delete(para))
			{
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
			}
		}
		return res;
	}
}
