package com.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;
import com.entity.system.meetingattendee;

public class meetingattendAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		EntityOperation eo = new EntityOperation();
	
		if (action != null && action.equals("add"))
		{
			meetingattendee meetingattendee=new meetingattendee();
			
			//String staffcode=request.getParameter("staffcode");
			String meetingno=request.getParameter("meetingno");
			String orgcode=request.getParameter("orgcode");
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
		
			if (meetingattendee.Insert(para,meetingno,orgcode))
			{
				res += "show('添加成功');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("meetingno")+"\";";
				//res += "window.open('../xtwh/systemrolemanage/systemrole_member_manage.jsp?sid='+rand+'&bm='+ccm,'_parent');";
				res +="window.close();";
			}
			else
			{
				res += "show('添加失败，可能是由于员工编码重复或超过指定人数，请检查！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("meetingno")+"\";";
				//res += "window.open('../xtwh/systemrolemanage/systemrole_member_manage.jsp?sid='+rand+'&bm='+ccm,'_parent');";
				res +="window.close();";
			}
		}
		else if (action != null && action.equals("modify"))
			
		{
			
			  String entity=request.getParameter("entity");
				eo.setEntity(entity);
				res0 = eo.Update(request);	
				res += "show('"+res0+"');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.close();";
				res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
				

		}
		else if (action != null && action.equals("del"))
		{
			String meetingno=request.getParameter("meetingno");
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("item");
			String[] itemsStrings1=request.getParameterValues("items");
			for(int i=0;i<itemsStrings.length;i++)
			{
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("ATTENDEECODE", itemsStrings1[i]);
				deletemap.put("MEETINGNO", itemsStrings[i]);
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
		}
			
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../ConferenceManage/attend.jsp?bm="+meetingno+"'"+",'_self');";
			
		}
		return res;
		
		
	
	}
}

