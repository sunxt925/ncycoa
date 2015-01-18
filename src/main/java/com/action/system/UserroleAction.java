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

				// System.out.println("ִ�е�����");
				/*
				 * System.out.println(userRole.getRole_id());
				 * System.out.println(userRole.getUser_code());
				 * System.out.println(userRole.getUnit_ccm());
				 */
				if (userRole.Insert())
				{
					res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
				}
				else
				{
					res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
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
				res += "MessageBox.Show(null,'�޸ĳɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'�޸�ʧ�ܣ�',null,'LogOK','Error',1,'�޸�ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
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
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_self');";
			}
		}
		return res;
	}
}
