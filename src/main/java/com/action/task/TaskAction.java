package com.action.task;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;
import com.common.Util;
import com.db.DBObject;
import com.entity.system.Staff;
import com.entity.system.UserInfo;
import com.entity.task.Task;

public class TaskAction extends ActionInterface{
	
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
			String taskno = Util.getSequence("º∆ªÆ–Ú∫≈");
			UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
			String orgcode = new Staff(user.getStaffcode()).getOrgcode();
			String operator = user.getUsername();
			eo.setGetcurrentdate(true);
			Map<String,String> map = new HashMap<String,String>();
			map.put("TASKNO", taskno);
			map.put("TASKORGCODE", orgcode);
			map.put("OPERATOR", operator);
			map.put("TASKAUDITFLAG", "0");
			map.put("FINISHEDFLAG", "0");
			map.put("NUMPARTICIPANT", "0");
			eo.setMap(map);
			
			res0 = eo.Add(request);
		}
		else if (action != null && action.equals("modify"))
		{
            String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Update(request);
			DBObject db = new DBObject();
			String taskno = request.getParameter("old_TASKNO");
			String sql="update MONTHTASK set TaskAuditFlag='0' where TASKNO='"+taskno+"'";
			try {
				db.run(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.close();";
			//res += "window.open('../task/TaskMod.jsp'"+",'_self');";
			
			return res;

		}
		
		else if (action != null && action.equals("audit"))
		{
			
			String taskno = request.getParameter("taskno");
			String auditflag = request.getParameter("auditflag");
			
			if(Task.audit(taskno, auditflag))
				res0 = "…Û∫À≥…π¶";
			
			else
				res0 = "…Û∫À ß∞‹";
 

		}
		

		
		else if(action != null && action.equals("del"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String taskno = request.getParameter("TASKNO");
			Map<String,String> deletemap = new HashMap<String, String>();
			
			deletemap.put("TASKNO", taskno);
			eo.setDeletemap(deletemap);
			res0 = eo.Delete();
	/*		String[] itemsStrings=request.getParameterValues("items");
			
			for(int i=0;i<itemsStrings.length;i++)
			{
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("TASKNO", itemsStrings[i]);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
			}
			*/
			
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../task/TaskMod.jsp'"+",'_self');";
			
			return res;
			
		}
	
		res += "show('"+res0+"');";
		res += "var rand=Math.floor(Math.random()*10000);";
		res += "window.close();";
		res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
		
		return res;
	}

}
