package com.action.system;

import javax.servlet.http.HttpServletRequest;

import com.common.Format;
import com.action.*;
import com.entity.system.SystemRole;
import com.entity.system.UnitRole;

public class SystemUnitRoleAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		UnitRole  unitrole = null;
		//System.out.println(action+"yfgujygfjy");
		if (action != null && action.equals("add"))
		{
			/*String ccm=request.getParameter("orgcode");
			System.out.println(ccm);*/
			 unitrole = new UnitRole();
			 unitrole.setOrgcode(request.getParameter("orgcode"));
			 unitrole.setRolecode(request.getParameter("pageselect"));
			if (unitrole.Insert())
			{
				res += "show('��ӳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/system_unit/UnitRolemanage.jsp?sid='+rand+'&bm='+ccm,'_self');";
			}
			else
			{
				res += "show('���ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/system_unit/UnitRolemanage.jsp?sid='+rand+'&bm='+ccm,'_self');";
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
			String orgcode=request.getParameter("orgcode");
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
			unitrole = new UnitRole();
			if (unitrole.Delete(para,orgcode))
			{
				res += "show('ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/system_unit/UnitRolemanage.jsp?sid='+rand+'&bm='+ccm,'_self');";
			}
			else
			{
				res += "show('ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/system_unit/UnitRolemanage.jsp?sid='+rand+'&bm='+ccm,'_self');";
			}
		}
		return res;
	}
}
