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
				res += "MessageBox.Show(null,'添加成功！',null,'LogOK',null,1,'添加成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("rolecode")+"\";";
				res += "window.close();";
				//res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
			else
			{
				res += "MessageBox.Show(null,'添加失败！',null,'LogOK','Error',1,'添加失败，可能是由于数据问题，请检查！');";
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
				res += "MessageBox.Show(null,'修改成功！',null,'LogOK',null,1,'修改成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
			//	res += "parent.menutree.location.reload();";
				res += "window.close();";
				//res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			}
			else
			{
				res += "MessageBox.Show(null,'修改失败！',null,'LogOK','Error',1,'修改失败，可能是由于编码重复，请检查！');";
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
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
				res += "parent.menutree.location.reload();";
				res += "window.open('../xtwh/system_menuManagement/menu_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
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
