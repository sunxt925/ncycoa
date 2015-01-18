package com.action.system;

import javax.servlet.http.*;

import com.action.*;
import com.common.*;
import com.entity.system.SystemEntity;

public class SystemEntityAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		ExecuteResult er = null;
		try
		{
			String res = "";
			String action = Format.NullToBlank(request.getParameter("act"));
			if (action.equals("init"))
			{
				SystemEntity e = new SystemEntity();
				er = e.initEntity(request);
				if (er.isRes())
				{
					res += "MessageBox.Show(null,'��ʼ���ɹ���',null,'LogOK',null,1,'��ʼ���ɹ�');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.open('../xtwh/entitymanage.jsp?sid='+rand,'_self');";
				}
				else
				{
					res += "MessageBox.Show(null,'��ʼ��ʧ�ܣ�',null,'LogOK','Error',1,'"
							+ er.getRes_str() + "');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.open('../xtwh/entitymanage.jsp?sid='+rand,'_self');";
				}
			}
			if (action.equals("modifyentity"))
			{
				SystemEntity e = new SystemEntity();
				e.setEntity_code(Format.NullToBlank(request
						.getParameter("entity_code")));
				e.setEntity_name(Format.NullToBlank(request
						.getParameter("entity_name")));
				e.setEntity_comment(Format.NullToBlank(request
						.getParameter("entity_comment")));
				er = e.Update();
				if (er.isRes())
				{
					res += "MessageBox.Show(null,'�޸Ļ��ɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
					res +="window.close();";
				//	res += "var rand=Math.floor(Math.random()*10000);";
				//	res += "window.open('../xtwh/entitymanage.jsp?sid='+rand,'_self');";
				}
				else
				{
					res += "MessageBox.Show(null,'�޸Ļ�ʧ�ܣ�',null,'LogOK','Error',1,'"
							+ er.getRes_str() + "');";
					res +="window.close();";
				//	res += "var rand=Math.floor(Math.random()*10000);";
				//	res += "window.open('../xtwh/entitymanage.jsp?sid='+rand,'_self');";
				}
			}
			if (action.equals("modifycolumn"))
			{
				SystemEntity e = new SystemEntity();
				String entity_code = Format.NullToBlank(request
						.getParameter("entity_code"));
				e.setEntity_code(entity_code);
				er = e.UpdateColumn(request);
				if (er.isRes())
				{
					res += "MessageBox.Show(null,'�޸Ļ��ɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.open('../xtwh/entity_column.jsp?bm="
							+ entity_code + "&sid='+rand,'_self');";
					res +="window.close();";
				}
				else
				{
					res += "MessageBox.Show(null,'�޸Ļ�ʧ�ܣ�',null,'LogOK','Error',1,'"
							+ er.getRes_str() + "');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res += "window.open('../xtwh/entity_column.jsp?bm="
							+ entity_code + "&sid='+rand,'_self');";
					res +="window.close();";
				}
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

}
