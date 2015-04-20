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
				res += "show('添加成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "show('添加失败，可能是由于编码重复，请检查！');";
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
				res += "show('修改成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "show('修改失败，可能是由于编码重复，请检查！');";
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
				res += "show('删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "show('删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/system_menuManagement/modulemanage.jsp?sid='+rand,'_self');";
			}
		}
		return res;
	}
}
