package com.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;
import com.entity.system.MeetingInfo;
import com.entity.system.Staff;
import com.entity.system.UserInfo;

public class meetingAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		EntityOperation eo = new EntityOperation();
		
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		 
		 
        String name=user.getUsername();
		//System.out.println(action+"yfgujygfjy");
		if (action != null && action.equals("add"))
		{
			String orgcode= new Staff(user.getStaffcode()).getOrgcode();
			String entity=request.getParameter("entity");
			//String sequence=request.getParameter("sequence");
		    //eo.setSequence(sequence);
			
			String meetingno=MeetingInfo.getMeetingno();
			Map<String,String> map = new HashMap<String,String>();
			map.put("MEETINGNO", meetingno);
			map.put("AUDITFLAG", "0");
			map.put("HANDLER", name);
			map.put("MEETINGFLAG", "0");
			map.put("APPLYORGCODE", orgcode);
			eo.setMap(map);
			eo.setEntity(entity);
			res0 = eo.Add(request);
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.close();";
			res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			
			
	
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
		
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("item");
	
			for(int i=0;i<itemsStrings.length;i++)
			{
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("MEETINGNO", itemsStrings[i]);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
		}
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../ConferenceManage/empty1.jsp','_self');";
			
			
		}
		else if (action != null && action.equals("del1"))
		{
		
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("item");
	
			for(int i=0;i<itemsStrings.length;i++)
			{
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("MEETINGNO", itemsStrings[i]);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
		}
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../ConferenceManage/orgmeeting.jsp','_self');";
			
			
		}
		else if (action != null && action.equals("audit"))
		{
		
			
            String entity=request.getParameter("entity");
        
			eo.setEntity(entity);
			res0 = eo.Update(request);
			res += "MessageBox.Show(null,'"+res0+"审核成功"+"',null,'LogOK',null,1,'"+res0+"审核成功"+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.close();";
			res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			
			
		}
		else if (action != null && action.equals("handle"))
		{
		
			
            String entity=request.getParameter("entity");
        
			eo.setEntity(entity);
			res0 = eo.Update(request);
			res += "MessageBox.Show(null,'"+res0+"操作成功"+"',null,'LogOK',null,1,'"+res0+"操作成功"+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.close();";
			res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			
			
		}
		return res;
	
	}
}

