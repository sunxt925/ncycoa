package com.action.system;

import javax.servlet.http.HttpServletRequest;

import com.common.Format;
import com.action.*;
import com.entity.query.SysUserPost;

public class SysUserPostAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		SysUserPost m = null;
		if (action != null && action.equals("add"))
		{
			m = new SysUserPost();
			m.setPost_id(Format.NullToBlank(request.getParameter("postId")));
			m.setUser_code(Format
					.NullToBlank(request.getParameter("user_code")));
			m.setUnit_ccm(Format.NullToBlank(request.getParameter("unitCcm")));
			if (m.Insert())
			{
				res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ����������ڱ����ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
		}
		else if (action != null && action.equals("modify"))
		{
			m = new SysUserPost();
			m.setNewpost_id(Format.NullToBlank(request
					.getParameter("newpostId")));
			m.setPost_id(Format.NullToBlank(request.getParameter("postId")));
			m
					.setUser_code(Format.NullToBlank(request
							.getParameter("userCode")));
			m.setUnit_ccm(Format.NullToBlank(request.getParameter("unitccm")));

			if (m.Update())
			{
				res += "MessageBox.Show(null,'�޸ĳɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'�޸�ʧ�ܣ�',null,'LogOK','Error',1,'�޸�ʧ�ܣ����������ڱ����ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
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
			m = new SysUserPost();
			if (m.Delete(para))
			{
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
		}
		return res;
	}
}
