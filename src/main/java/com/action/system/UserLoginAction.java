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
				res += "MessageBox.Show(null,'添加成功！',null,'LogOK',null,1,'添加成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
			else
			{
				res += "MessageBox.Show(null,'添加失败！',null,'LogOK','Error',1,'添加失败，可能是由于编码重复，请检查！');";
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
					res += "MessageBox.Show(null,'修改成功！',null,'LogOK',null,1,'修改成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";				}
				else
				{
					res += "MessageBox.Show(null,'修改失败！',null,'LogOK','Error',1,'修改失败，可能是由于编码重复，请检查！');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";				}
			}
			else
			{
				if (u.Insert())
				{
					res += "MessageBox.Show(null,'添加成功！',null,'LogOK',null,1,'添加成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";				}
				else
				{
					res += "MessageBox.Show(null,'添加失败！',null,'LogOK','Error',1,'添加失败，可能是由于编码重复，请检查！');";
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
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
		}
		
		

		
		return res;
	}
}
