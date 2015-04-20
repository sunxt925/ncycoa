package com.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.action.ActionInterface;
import com.common.Format;

import com.entity.system.StaffInfo;


public class StaffInfoAction extends ActionInterface{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		StaffInfo staffinfo = null;
		
		HttpSession session  = request.getSession();
		
		if (action != null && action.equals("add"))
		{

			
			
			staffinfo = new StaffInfo();
			staffinfo.setCode(Format.NullToBlank(request.getParameter("staffcode")));
			staffinfo.setName(Format.NullToBlank(request.getParameter("staffname")));
			staffinfo.setIdcard(Format.NullToBlank(request.getParameter("idcard")));
			staffinfo.setGender(Format.NullToBlank(request.getParameter("gender")));
			staffinfo.setBirthday(Format.NullToBlank(request.getParameter("birthday")));
			staffinfo.setNationalitycode(Format.NullToBlank(request.getParameter("nationalitycode")));
			staffinfo.setNationality(Format.NullToBlank(request.getParameter("nationality")));
			staffinfo.setNativeplace(Format.NullToBlank(request.getParameter("nativeplace")));
			staffinfo.setMarriage(Format.NullToBlank(request.getParameter("marriage")));
			staffinfo.setSalarylevel(Format.NullToBlank(request.getParameter("salarylevel")));
			staffinfo.setBegincareerdate(Format.NullToBlank(request.getParameter("begincareerdate")));
			staffinfo.setEmail(Format.NullToBlank(request.getParameter("email")));
			staffinfo.setQq(Format.NullToBlank(request.getParameter("qq")));
			staffinfo.setMobilephone(Format.NullToBlank(request.getParameter("mebilephone")));
			staffinfo.setOfficephone(Format.NullToBlank(request.getParameter("officephone")));
			staffinfo.setHomephone(Format.NullToBlank(request.getParameter("homephone")));
			staffinfo.setHomeaddress(Format.NullToBlank(request.getParameter("homeaddress")));
			

			if (staffinfo.Insert())
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
			staffinfo = new StaffInfo();
			staffinfo.setCode(Format.NullToBlank(request.getParameter("staffcode")));
			staffinfo.setName(Format.NullToBlank(request.getParameter("staffname")));
			staffinfo.setIdcard(Format.NullToBlank(request.getParameter("idcard")));
			staffinfo.setGender(Format.NullToBlank(request.getParameter("gender")));
			staffinfo.setBirthday(Format.NullToBlank(request.getParameter("birthday")));
			staffinfo.setNationalitycode(Format.NullToBlank(request.getParameter("nationalitycode")));
			staffinfo.setNationality(Format.NullToBlank(request.getParameter("nationality")));
			staffinfo.setNativeplace(Format.NullToBlank(request.getParameter("nativeplace")));
			staffinfo.setMarriage(Format.NullToBlank(request.getParameter("marriage")));
			staffinfo.setSalarylevel(Format.NullToBlank(request.getParameter("salarylevel")));
			staffinfo.setBegincareerdate(Format.NullToBlank(request.getParameter("begincareerdate")));
			staffinfo.setEmail(Format.NullToBlank(request.getParameter("email")));
			staffinfo.setQq(Format.NullToBlank(request.getParameter("qq")));
			staffinfo.setMobilephone(Format.NullToBlank(request.getParameter("mobilephone")));
			staffinfo.setOfficephone(Format.NullToBlank(request.getParameter("officephone")));
			staffinfo.setHomephone(Format.NullToBlank(request.getParameter("homephone")));
			staffinfo.setHomeaddress(Format.NullToBlank(request.getParameter("homeaddress")));

			if (staffinfo.Update())
			{
				res += "MessageBox.Show(null,'修改成功！',null,'LogOK',null,1,'修改成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
			else
			{
				res += "MessageBox.Show(null,'修改失败！',null,'LogOK','Error',1,'修改失败，可能是由于编码重复，请检查！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";			}
		}
		else if (action != null && action.equals("del"))
		{
			String staffcode = request.getParameter("staffcode");
			
			
			staffinfo = new StaffInfo();
			if (staffinfo.Delete(staffcode))
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
