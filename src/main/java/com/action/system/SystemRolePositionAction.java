
package com.action.system;

import javax.servlet.http.HttpServletRequest;

import com.common.Format;
import com.action.*;
import com.entity.system.Org;
import com.entity.system.RolePosition;
import com.entity.system.SystemRole;


	public class SystemRolePositionAction extends ActionInterface
	{
		public String getResult(HttpServletRequest request)
		{
			String res = "";
			String action = request.getParameter("act");
			//String rolecode=request.getParameter("rolecode");
			RolePosition  roleposition = null;
			//System.out.println(rolecode+"yfgujygfjy");
			if (action != null && action.equals("add"))
			{
				roleposition=new RolePosition();
				String orgcode=request.getParameter("orgcode");
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
			
				if (roleposition.Insert(para,orgcode,rolecode))
				{
					res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("rolecode")+"\";";
					//res += "window.open('../xtwh/systemrolemanage/systemrole_position_manage.jsp?sid='+rand+'&bm='+ccm,'_parent');";
					res +="window.close()";
				}
				else
				{
					res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ�������������ӵ���ͬһ��λ�����飡');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("rolecode")+"\";";
					//res += "window.open('../xtwh/systemrolemanage/systemrole_position_manage.jsp?sid='+rand+'&bm='+ccm,'_parent');";
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
				roleposition = new RolePosition();
				if (roleposition.Delete(para,rolecode))
				{
					res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("rolecode")+"\";";
					res += "window.open('../xtwh/systemrolemanage/systemrole_position_manage.jsp?sid='+rand+'&bm='+ccm,'_self');";
				}
				else
				{
					res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ��������Ա��ϵ��');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("rolecode")+"\";";
					res += "window.open('../xtwh/systemrolemanage/systemrole_position_manage.jsp?sid='+rand+'&bm='+ccm,'_self');";
				}
			}
			return res;
		}
	}
