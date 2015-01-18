package com.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;

import com.entity.system.SystemModule;
import com.action.*;
import com.common.*;

public class SystemModuleAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		EntityOperation eo = new EntityOperation();
		SystemModule m = null;
		if (action != null && action.equals("add"))
		{
			String entity=request.getParameter("entity");
			String sequence=request.getParameter("sequence");
		    eo.setSequence(sequence);
			
			eo.setEntity(entity);
			res0 = eo.Add(request);
			/*m = new SystemModule();
			m.setLevel_code(Format.NullToBlank(request
					.getParameter("level_code")));
			m.setMenu_name(Format
					.NullToBlank(request.getParameter("menu_name")));
			m.setMenu_url(Format.NullToBlank(request.getParameter("menu_url")));
			m.setMenu_type(Format
					.NullToBlank(request.getParameter("menu_type")));
			if (m.Insert())
			{
				res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("rolecode")+"\";";
				res += "window.close();";
				//res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
			else
			{
				res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ������������������⣬���飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("rolecode")+"\";";
				res += "window.close();";
				//res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
				
				}*/
		}
		else if (action != null && action.equals("modify"))
			
		{
			String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Update(request);	
			/*m = new SystemModule();
			m.setNewlevel_code(Format.NullToBlank(request
					.getParameter("newlevel_code")));
			m.setLevel_code(Format.NullToBlank(request
					.getParameter("level_code")));
			m.setMenu_name(Format
					.NullToBlank(request.getParameter("menu_name")));
			m.setMenu_url(Format.NullToBlank(request.getParameter("menu_url")));
			m.setMenu_type(Format
					.NullToBlank(request.getParameter("menu_type")));

			if (m.Update())
			{
				res += "MessageBox.Show(null,'�޸ĳɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
			//	res += "parent.menutree.location.reload();";
				res += "window.close();";
				//res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
			else
			{
				res += "MessageBox.Show(null,'�޸�ʧ�ܣ�',null,'LogOK','Error',1,'�޸�ʧ�ܣ����������ڱ����ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
				//res += "parent.menutree.location.reload();";
				res += "window.close();";
			}*/
		}
		else if (action != null && action.equals("del"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("item");
	
			for(int i=0;i<itemsStrings.length;i++)
			{
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("LEVEL_CODE", itemsStrings[i]);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
			}
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
			m = new SystemModule();
			if (m.Delete(para))
			{
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
				res += "parent.menutree.location.reload();";
				res += "window.open('../xtwh/system_menuManagement/menu_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
				res += "parent.menutree.location.reload();";
				res += "window.open('../xtwh/system_menuManagement/menu_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
			}*/
		}
		res += "show('"+res0+"');";
		res+="window.close()";
		return res;
	}
}
