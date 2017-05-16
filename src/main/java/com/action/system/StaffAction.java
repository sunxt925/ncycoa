package com.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.action.ActionInterface;
import com.common.Format;
import com.entity.query.User;
import com.entity.system.Code;
import com.entity.system.Staff;
import com.entity.system.StaffInfo;
import com.entity.system.UserLogin;

public class StaffAction extends ActionInterface{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		Staff staff = null;
		StaffInfo staffinfo = null;
		
		HttpSession session  = request.getSession();
		
		if (action != null && action.equals("add"))
		{
			
			System.out.println(request.getParameter("isexist"));
			

			

			staff = new Staff();
			staff.setStaffcode(Format.NullToBlank(request.getParameter("staffcode")));
			staff.setStaffname(Format.NullToBlank(request.getParameter("staffname")));
			staff.setOrgcode(Format.NullToBlank(request.getParameter("orgcode")));
			staff.setOrgname(Format.NullToBlank(request.getParameter("orgname")));
			staff.setPositioncode(Format.NullToBlank(request.getParameter("positioncode")));
			staff.setPositionname(Format.NullToBlank(request.getParameter("positionname")));
			staff.setGender(Format.NullToBlank(request.getParameter("gender")));
			staff.setIdcard(Format.NullToBlank(request.getParameter("idcard")));
			
			if(request.getParameter("isexist").equals("false")){
				
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


			if (staffinfo.Insert()&&staff.Insert())
			{
				res += "show('添加成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
				}

			else
			{
				res += "show('添加失败，可能是由于编码重复，请检查！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
			}
			
			else if(request.getParameter("isexist").equals("true")){
				if (staff.Insert())
				{
					res += "show('添加成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
					}

				else
				{
					res += "show('添加失败，可能是由于编码重复，请检查！');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.close();";
					res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
				}
			}
			


		}
		else if (action != null && action.equals("modify"))
		{
			staff = new Staff();
			staff.setStaffcode(Format.NullToBlank(request.getParameter("staffcode")));
			staff.setStaffname(Format.NullToBlank(request.getParameter("staffname")));
			staff.setOrgcode(Format.NullToBlank(request.getParameter("orgcode")));
			staff.setOrgname(Format.NullToBlank(request.getParameter("orgname")));
			staff.setPositioncode(Format.NullToBlank(request.getParameter("positioncode")));
			staff.setPositionname(Format.NullToBlank(request.getParameter("positionname")));
			staff.setGender(Format.NullToBlank(request.getParameter("gender")));
			staff.setIdcard(Format.NullToBlank(request.getParameter("idcard")));
			staff.setMemberid(Format.NullToBlank(request.getParameter("memberid")));
			staff.setPositiontime(Format.NullToBlank(request.getParameter("positiontime")));

			if (staff.Update())
			{
				res += "show('修改成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
			else
			{
				res += "show('修改失败，可能是由于编码重复，请检查！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
		}
		else if (action != null && action.equals("del"))
		{
			String staffcode = request.getParameter("staffcode");
			
			
			staff = new Staff();
			String positioncode = request.getParameter("positioncode");
			String positionname = request.getParameter("positionname");
			String orgcode = request.getParameter("orgcode");
			String orgclass = request.getParameter("orgclass");
			String orgname = request.getParameter("orgname");
			if (staff.Delete(staffcode,positioncode,orgcode))
			{
				res += "show('删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/user/member_list.jsp?sid='+rand+'&positioncode="+positioncode+"&positionname="+positionname+"&orgcode="+orgcode+"&orgname="+orgname+"&orgclass="+orgclass+"'"+",'_self');";
				String a = "window.open('../xtwh/user/member_list.jsp?sid='+rand+'&positioncode="+positioncode+"&positionname="+positionname+"&orgcode="+orgcode+"&orgname="+orgname+"&orgclass="+orgclass+"'"+",'_self');";
				System.out.println(a);
			}
			else
			{
				res += "show('删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/user/member_list.jsp?sid='+rand+'&positioncode="+positioncode+"&positionname="+positionname+"'"+"&orgcode="+orgcode+"&orgname="+orgname+"&orgclass="+orgclass+",'_self');";
			}
		}
		
		else if(action != null && action.equals("get")) //将员工信息
		{
			
			staffinfo = new StaffInfo();
			staffinfo.setByIdcard(request.getParameter("idcard0"));
			staff = new Staff();
			staff.setIdcard(staffinfo.getIdcard());
			staff.setGender(staffinfo.getGender());
			staff.setOrgname(request.getParameter("orgname"));
			staff.setOrgcode(request.getParameter("orgcode"));
			staff.setPositionname(request.getParameter("positionname"));
			staff.setPositioncode(request.getParameter("positioncode"));
			staff.setStaffcode(staffinfo.getCode());
			staff.setStaffname(staffinfo.getName());
			
			if (staff.Insert())
			{
				res += "show('添加成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
			else
			{
				res += "show('添加失败，可能是由于编码重复，请检查！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
		}
		
		else if(action != null && action.equals("choose"))
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
			staff = new Staff();
			if (staff.Choose(para,(String)session.getAttribute("orgcode"),(String)session.getAttribute("orgname"),(String)session.getAttribute("positioncode"),(String)session.getAttribute("positionname")))
			{
				res += "show('添加成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
			else
			{
				res += "show('添加失败，可能是由于编码重复，请检查！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";

			}
		}

		return res;
	}
	


}
