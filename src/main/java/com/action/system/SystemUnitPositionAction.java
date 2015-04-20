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
				res += "MessageBox.Show(null,'添加成功！',null,'LogOK',null,1,'添加成功');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res+="window.close();";
				//res+="window.dialogArguments.location='../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm";
				
				//res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
			}
			else
			{
				res += "MessageBox.Show(null,'添加失败！',null,'LogOK','Error',1,'添加失败，可能是由于角色ID重复，请检查！');";
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
				res += "MessageBox.Show(null,'修改成功！',null,'LogOK',null,1,'修改成功');";
				res+="window.close();";
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("OrgCode")+"\";";
				//res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
			}
			else
			{
				res += "MessageBox.Show(null,'修改失败！',null,'LogOK','Error',1,'修改失败，可能是由于角色ID重复，请检查！');";
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
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
				//System.out.println(res);
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../xtwh/orgposition/unit_positionlist.jsp?sid='+rand+'&unitccm='+ccm,'unitpositionlist');";
			}
		}
		return res;
	}
}