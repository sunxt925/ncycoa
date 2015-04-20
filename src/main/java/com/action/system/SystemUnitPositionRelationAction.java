package com.action.system;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.*;
import com.entity.system.*;

public class SystemUnitPositionRelationAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		String orgcode=request.getParameter("orgcode");
		String positioncode=request.getParameter("positioncode");
		String positionname=request.getParameter("positionname");
		//System.out.println(positionname);
		//OrgPosition orgposition = null;
		OrgPositionRelation opr=null;
		//Position position=null;
		if (action != null && action.equals("add"))
		{
			
			String[] ids = request.getParameterValues("items");
			String level=request.getParameter("level");
			OrgPosition[] orgposition=new OrgPosition[ids.length];;
			
			for (int i = 0; i < ids.length; i++)
				orgposition[i]=new OrgPosition(Integer.parseInt(ids[i]));
			opr = new OrgPositionRelation();
			opr.setOrgcode(orgcode);
			opr.setPositioncode(positioncode);
			if(level.equals("higher"))
				opr.setPOSITIONRELATION("�ϼ�");
			if(level.equals("lower"))
				opr.setPOSITIONRELATION("�¼�");
			if (opr.Insert(orgposition))
			{
				res += "MessageBox.Show(null,'��ӳɹ���',null,'LogOK',null,1,'��ӳɹ�');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close()";
				/*res +="var ccm=\""+orgcode+"\";";
				res +="var positioncode=\""+positioncode+"\";";
				res +="var positionname=\""+positionname+"\";";
				res += "window.open('../xtwh/orgposition/relationposition_list.jsp?sid='+rand+'&bm='+ccm+'&positioncode='+positioncode+'&positionname='+positionname,'unitpositionlist');";*/
			}
			else
			{
				res += "MessageBox.Show(null,'���ʧ�ܣ�',null,'LogOK','Error',1,'���ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
				res += "window.close()";
				/*res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+orgcode+"\";";
				res +="var positioncode=\""+positioncode+"\";";
				res +="var positionname=\""+positionname+"\";";
				res += "window.open('../xtwh/orgposition/relationposition_list.jsp?sid='+rand+'&bm='+ccm+'&positioncode='+positioncode+'&positionname='+positionname,'unitpositionlist');";*/
			}
		}
		else if (action != null && action.equals("modify"))
		{
			/*orgposition = new OrgPosition();
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
				res += "MessageBox.Show(null,'�޸ĳɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("OrgCode")+"\";";
				res += "window.open('../xtwh/system_unit/unit_positionlist.jsp?sid='+rand+'&bm='+ccm,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'�޸�ʧ�ܣ�',null,'LogOK','Error',1,'�޸�ʧ�ܣ����������ڽ�ɫID�ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("OrgCode")+"\";";
				res += "window.open('../xtwh/system_unit/unit_positionlist.jsp?sid='+rand+'&bm='+ccm,'_self');";
			}*/
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
			opr = new OrgPositionRelation();
			if (opr.Delete(para))
			{
				//System.out.println(positionname);
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+orgcode+"\";";
				res +="var positioncode=\""+positioncode+"\";";
				res +="var positionname=\""+positionname+"\";";
				res += "window.open('../xtwh/orgposition/relationposition_list.jsp?sid='+rand+'&bm='+ccm+'&positioncode='+positioncode+'&positionname='+positionname,'temp');";
				//System.out.println(res);
			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+orgcode+"\";";
				res +="var positioncode=\""+positioncode+"\";";
				res +="var positionname=\""+positionname+"\";";
				res += "window.open('../xtwh/orgposition/relationposition_list.jsp?sid='+rand+'&bm='+ccm+'&positioncode='+positioncode+'&positionname='+positionname,'unitpositionlist');";
			}
		}
		return res;
	}
	

}
