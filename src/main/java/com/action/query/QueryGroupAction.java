package com.action.query;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;

public class QueryGroupAction extends ActionInterface
{
	
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		if (action != null && action.equals("add"))
		{
			
			String entity=request.getParameter("entity");
				eo.setEntity(entity);
			res0 = eo.Add(request);
			res += "show('"+res0+"');";
			res += "window.close();";
		}
		else if (action != null && action.equals("modify"))
		{
            String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Update(request);	
			res += "show('"+res0+"');";
			res += "window.close();";
		}
		
		if(action != null && action.equals("del"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("items");
			
			for(int i=0;i<itemsStrings.length;i++)
			{
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("GROUP_CODE", itemsStrings[i]);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
				
				
			}
			
			res += "show('"+res0+"');";
			
		}
	
		
		return res;
	}
	
	/*
	public String getResult(HttpServletRequest request)
	{
		
		
		
		
		
		String res = "";
		String action = request.getParameter("act");
		QueryGroup g = null;
		if (action != null && action.equals("add"))
		{
			g = new QueryGroup();
			g.setCcm(Format.NullToBlank(request.getParameter("ccm")));
			g.setFlmc(Format.NullToBlank(request.getParameter("flmc")));
			g.setFlms(Format.NullToBlank(request.getParameter("flms")));
			if (g.Insert())
			{
				res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../search/groupmanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ����������ڱ����ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../search/groupmanage.jsp?sid='+rand,'_self');";
			}
		}
		else if (action != null && action.equals("modify"))
		{
			g = new QueryGroup();
			g.setCcm(Format.NullToBlank(request.getParameter("ccm")));
			g.setFlmc(Format.NullToBlank(request.getParameter("flmc")));
			g.setFlms(Format.NullToBlank(request.getParameter("flms")));
			g.setNewccm(Format.NullToBlank(request.getParameter("newccm")));

			if (g.Update())
			{
				res += "MessageBox.Show(null,'�޸ĳɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../search/groupmanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'�޸�ʧ�ܣ�',null,'LogOK','Error',1,'�޸�ʧ�ܣ����������ڱ����ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../search/groupmanage.jsp?sid='+rand,'_self');";
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
			g = new QueryGroup();
			if (g.Delete(para))
			{
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../search/groupmanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../search/groupmanage.jsp?sid='+rand,'_self');";
			}
		}
		return res;
	}*/
}
