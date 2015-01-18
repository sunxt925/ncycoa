package com.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.common.EntityOperation;
import com.common.Format;
import com.action.*;
import com.entity.system.SystemRole;

public class SystemRoleAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		SystemRole systemRole = null;
		EntityOperation eo = new EntityOperation();
		//System.out.println(action+"yfgujygfjy");
		if (action != null && action.equals("add"))
		{
			String entity=request.getParameter("entity");
			String sequence=request.getParameter("sequence");
		    eo.setSequence(sequence);
			
			eo.setEntity(entity);
			res0 = eo.Add(request);
			
			/*systemRole = new SystemRole();
			systemRole.setRolecode(Format.NullToBlank(request
					.getParameter("Rolecode")));
			systemRole.setRolename(Format.NullToBlank(request
					.getParameter("Rolename")));
			systemRole.setRolemode(Format.NullToBlank(request
					.getParameter("Rolemode")));
			systemRole.setValidFlag(Format.NullToBlank(request
					.getParameter("ValidFlag")));
			System.out.println(Format.NullToBlank(request
					.getParameter("ValidFlag"))+"��ϿƼ������Ƽ��Ƕ������");

			if (systemRole.Insert())
			{
				res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				//res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_parent');";
				res+="window.close()";
			}
			else
			{
				res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				//res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_parent');";
				res+="window.close()";
			}*/
		}
		else if (action != null && action.equals("modify"))
			
		{
			  String entity=request.getParameter("entity");
				
				eo.setEntity(entity);
				res0 = eo.Update(request);	
				
//			systemRole = new SystemRole();
//		
//			systemRole.setNewrolecode(Format.NullToBlank(request
//					.getParameter("Rolecode")));
//			systemRole.setRolename(Format.NullToBlank(request
//					.getParameter("Rolename")));
//			systemRole.setRolemode(Format.NullToBlank(request
//					.getParameter("Rolemode")));
//			systemRole.setValidFlag(Format.NullToBlank(request
//					.getParameter("ValidFlag")));
//			systemRole.setRolecode(Format.NullToBlank(request
//					.getParameter("role_id")));
//			if (systemRole.Update())
//			{
//				res += "MessageBox.Show(null,'�޸ĳɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
//				res += "var rand=Math.floor(Math.random()*10000);";
//				//res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_parent');";
//				res+="window.close()";
//			}
//			else
//			{
//				res += "MessageBox.Show(null,'�޸�ʧ�ܣ�',null,'LogOK','Error',1,'�޸�ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
//				res += "var rand=Math.floor(Math.random()*10000);";
//				//res += "window.open('../xtwh/systemrolemanage/systemrole_manage.jsp?sid='+rand,'_parent');";
//				res+="window.close()";
//			}
		}
		else if (action != null && action.equals("del"))
		{
			/*//String[] ids = request.getParameterValues("items");
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
			systemRole = new SystemRole();
			if (systemRole.Delete(para))
			{
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/empty.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ�����ɾ����ɫ�µĸ�λ���Ա');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/systemrolemanage/empty.jsp?sid='+rand,'_self');";
			}
		}
		return res;*/
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("item");
		//System.out.println(itemsStrings);
			for(int i=0;i<itemsStrings.length;i++)
			{
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("ROLECODE", itemsStrings[i]);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
		}
		}
		
		res += "show('"+res0+"');";
		res+="window.close()";
		return res;
	
	}
}

