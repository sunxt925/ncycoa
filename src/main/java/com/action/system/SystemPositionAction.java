package com.action.system;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.Format;
import com.entity.system.Position;

public class SystemPositionAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		Position position = null;

		if (action != null && action.equals("add"))
		{
			position = new Position();
			position.setPositionCode(Format.NullToBlank(request
					.getParameter("PositionCode")));
			position.setPositionName(Format.NullToBlank(request
					.getParameter("PositionName")));
			position.setPositionDesc(Format.NullToBlank(request
					.getParameter("positionDesc")));
			
			if (position.Insert())
			{
				
				
				res += "show('��ӳɹ�');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				res+="window.close();";
				//res += "window.open('../xtwh/position/position_manage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "show('���ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				res+="window.close();";
				//res += "window.open('../xtwh/position/position_manage.jsp?sid='+rand,'_self');";
			}
		}
		else if (action != null && action.equals("modify"))
		{
			position = new Position();
			position.setPositionCode(Format.NullToBlank(request
					.getParameter("PositionCode")));
			position.setPositionName(Format.NullToBlank(request
					.getParameter("PositionName")));
			position.setPositionDesc(Format.NullToBlank(request
					.getParameter("PositionDesc")));
			if (position.Update())
			{
				res += "show('�޸ĳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/position/position_manage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "show('�޸�ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/position/position_manage.jsp?sid='+rand,'_self');";
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
			position = new Position();
			if (position.Delete(para))
			{
				res += "show('ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/position/position_manage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "show('ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/position/position_manage.jsp?sid='+rand,'_self');";
			}
		}
		return res;
	}
}
