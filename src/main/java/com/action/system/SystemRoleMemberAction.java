
package com.action.system;

import javax.servlet.http.HttpServletRequest;

import com.common.Format;
import com.action.*;
import com.entity.system.Org;
import com.entity.system.RoleMember;
import com.entity.system.RolePosition;
import com.entity.system.SystemRole;


	public class SystemRoleMemberAction extends ActionInterface
	{
		public String getResult(HttpServletRequest request)
		{
			String res = "";
			String action = request.getParameter("act");
			//String rolecode=request.getParameter("rolecode");
			RoleMember  rolemember = null;
			//System.out.println(rolecode+"yfgujygfjy");
			if (action != null && action.equals("add"))
			{
				rolemember=new RoleMember();
				
				//String staffcode=request.getParameter("staffcode");
				String rolecode=request.getParameter("rolecode");
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
			
				if (rolemember.Insert(para,rolecode))
				{
					res += "show('添加成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("rolecode")+"\";";
					//res += "window.open('../xtwh/systemrolemanage/systemrole_member_manage.jsp?sid='+rand+'&bm='+ccm,'_parent');";
					res +="window.close()";
				}
				else
				{
					res += "show('添加失败，可能是由于员工编码重复，请检查！');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("rolecode")+"\";";
					//res += "window.open('../xtwh/systemrolemanage/systemrole_member_manage.jsp?sid='+rand+'&bm='+ccm,'_parent');";
					res +="window.close()";
				}
			}
			/*else if (action != null && action.equals("modify"))
				
			{
				systemRole = new SystemRole();
				systemRole.setNewrolecode(Format.NullToBlank(request
						.getParameter("Rolecode")));
				systemRole.setRolename(Format.NullToBlank(request
						.getParameter("Rolename")));
				systemRole.setRolemode(Format.NullToBlank(request
						.getParameter("Rolemode")));
				systemRole.setValidFlag(Format.NullToBlank(request
						.getParameter("ValidFlag")));
				systemRole.setRolecode(Format.NullToBlank(request
						.getParameter("role_id")));
				if (systemRole.Update())
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
			}*/
			else if (action != null && action.equals("del"))
			{
				String rolecode=request.getParameter("rolecode");
				//String[] ids = request.getParameterValues("items");
				String[] ids = request.getParameterValues("item");
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
				String[] op = para.split("\\.");
				//System.out.println(op[1]);
				rolemember=new RoleMember();
				if (rolemember.Delete(para,rolecode))
				{
					res += "show('删除成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("rolecode")+"\";";
					res += "window.open('../xtwh/systemrolemanage/systemrole_member_manage.jsp?sid='+rand+'&bm='+ccm,'_self');";
				}
				else
				{
					res += "show('删除失败，请与管理员联系！');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("rolecode")+"\";";
					res += "window.open('../xtwh/systemrolemanage/systemrole_member_manage.jsp?sid='+rand+'&bm='+ccm,'_self');";
				}
			}
			return res;
		}
	}
