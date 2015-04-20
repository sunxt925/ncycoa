package com.action.system;
import javax.servlet.http.HttpServletRequest;

import com.common.Format;
import com.db.*;
import com.action.*;
import com.entity.system.*;

public class SystemUnitPositionAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		OrgPosition orgposition = null;
		//Position position=null;
		if (action != null && action.equals("add"))
		{
			String[] ids = request.getParameterValues("items");
			Position[] positions=new Position[ids.length];
			
			for (int i = 0; i < ids.length; i++)
				positions[i]=new Position(ids[i]);
			orgposition = new OrgPosition();
			orgposition.setOrgcode(request.getParameter("orgcode"));
			orgposition.setOrgname(request.getParameter("orgname"));
			orgposition.setMemo(null);
			orgposition.setPositionconfigcount("0");
			if (orgposition.Insert(positions))
			{
				res += "show('��ӳɹ�');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res+="window.close();";
				//res+="window.dialogArguments.location='../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm";
				
				//res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
			}
			else
			{
				res += "show('���ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
				res+="window.close();";
				/*res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";*/
			}
		}
		else if (action != null && action.equals("modify"))
		{
			orgposition = new OrgPosition();
			orgposition.setMemo(Format.NullToBlank(request
					.getParameter("Memo")));
			orgposition.setOrgcode(Format.NullToBlank(request
					.getParameter("OrgCode")));
			orgposition.setOrgname(Format.NullToBlank(request
					.getParameter("OrgName")));
			orgposition.setPositioncode(Format.NullToBlank(request
					.getParameter("PositionCode")));
			orgposition.setPositionconfigcount(Format.NullToBlank(request
					.getParameter("positionconfigcount")));
			orgposition.setPositionname(Format.NullToBlank(request
					.getParameter("PositionName")));
			orgposition.setOrgpositionid(Integer.parseInt(Format.NullToZero(request
					.getParameter("orgpositionid"))));
			
			if (orgposition.Update())
			{
				res += "show('�޸ĳɹ�');";
				res+="window.close();";
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("OrgCode")+"\";";
				//res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
			}
			else
			{
				res += "show('�޸�ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
				res+="window.close();";
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("OrgCode")+"\";";
				//res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
			}
		}
		else if (action != null && action.equals("del"))
		{
			//String[] ids = request.getParameterValues("items");
			String[] ids=request.getParameterValues("org");//
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
			orgposition = new OrgPosition();
			if (orgposition.Delete(para))
			{
				res += "show('ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
				//System.out.println(res);
			}
			else
			{
				res += "show('ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
			}
		}
		return res;
	}
}