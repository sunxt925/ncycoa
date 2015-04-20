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
				opr.setPOSITIONRELATION("上级");
			if(level.equals("lower"))
				opr.setPOSITIONRELATION("下级");
			if (opr.Insert(orgposition))
			{
				res += "MessageBox.Show(null,'添加成功！',null,'LogOK',null,1,'添加成功');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close()";
				/*res +="var ccm=\""+orgcode+"\";";
				res +="var positioncode=\""+positioncode+"\";";
				res +="var positionname=\""+positionname+"\";";
				res += "window.open('../xtwh/orgposition/relationposition_list.jsp?sid='+rand+'&bm='+ccm+'&positioncode='+positioncode+'&positionname='+positionname,'unitpositionlist');";*/
			}
			else
			{
				res += "MessageBox.Show(null,'添加失败！',null,'LogOK','Error',1,'添加失败，可能是由于角色ID重复，请检查！');";
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
				res += "MessageBox.Show(null,'修改成功！',null,'LogOK',null,1,'修改成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("OrgCode")+"\";";
				res += "window.open('../xtwh/system_unit/unit_positionlist.jsp?sid='+rand+'&bm='+ccm,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'修改失败！',null,'LogOK','Error',1,'修改失败，可能是由于角色ID重复，请检查！');";
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
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+orgcode+"\";";
				res +="var positioncode=\""+positioncode+"\";";
				res +="var positionname=\""+positionname+"\";";
				res += "window.open('../xtwh/orgposition/relationposition_list.jsp?sid='+rand+'&bm='+ccm+'&positioncode='+positioncode+'&positionname='+positionname,'temp');";
				//System.out.println(res);
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
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
