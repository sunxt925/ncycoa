package com.action.system;

import javax.servlet.http.*;

import com.entity.query.User;
import com.entity.system.UserLogin;
import com.action.*;
import com.common.*;

public class UserLoginAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		UserLogin u = null;
		HttpSession session  = request.getSession();
		
		
		if (action != null && action.equals("add"))
		{
			u = new UserLogin();
			u.setUsercode(Format.NullToBlank(request.getParameter("usercode")));
			u.setStaffcode(Format.NullToBlank(request
					.getParameter("staffcode")));
			u.setPassword(Format.NullToBlank(request.getParameter("password")));

			if (u.Insert())
			{
				res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
			else
			{
				res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ����������ڱ����ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
		}
		else if (action != null && action.equals("modify"))
		{
		
			
			u = new UserLogin();
			
			u.setUsercode(Format.NullToBlank(request.getParameter("usercode")));
			u.setStaffcode(Format.NullToBlank(request.getParameter("staffcode")));
			u.setPassword(Format.NullToBlank(request.getParameter("password")));
			String staffcode = Format.NullToBlank(request.getParameter("staffcode"));
			//System.out.println(staffcode);
			boolean exist = u.isexist(staffcode);
			if(exist)
			{
				if (u.Update())
				{
					res += "MessageBox.Show(null,'�޸ĳɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";				}
				else
				{
					res += "MessageBox.Show(null,'�޸�ʧ�ܣ�',null,'LogOK','Error',1,'�޸�ʧ�ܣ����������ڱ����ظ������飡');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";				}
			}
			else
			{
				if (u.Insert())
				{
					res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";				}
				else
				{
					res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ����������ڱ����ظ������飡');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";				}
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
			u = new UserLogin();
			if (u.Delete(para))
			{
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
		}
		
		

		
		return res;
	}
}
