package com.action.task;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;
import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;
import com.entity.system.Staff;
import com.entity.system.UserInfo;

public class ParticipantAction extends ActionInterface{
	
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		if (action != null && action.equals("choose"))
		{
			String[] ids = request.getParameterValues("items");
			int number=0;
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String taskno = (String)request.getSession().getAttribute("taskno");
			String date = (String)request.getSession().getAttribute("date");
			UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
			String orgcode = new Staff(user.getStaffcode()).getOrgcode();
	
			Map<String,String> map = new HashMap<String,String>();
			map.put("TASKNO", taskno);
			map.put("ORGCODE", orgcode);
			map.put("FINISHEDFLAG", "0");
			map.put("TASKAUDITFLAG", "0");
			
			for(int i=0,j=ids.length;i<j;i++)
			{

			map.put("PARTICIPANTCODE", ids[i]);

			eo.setMap(map);
			
			res0 = eo.Add(request);
			
			}
			
			try
			{
				DBObject db = new DBObject();
				String sql1="select * from monthtaskparticipant t where t.TASKNO='"+taskno+"'";
			
  	        	
  	        	
  	        	DataTable dt =db.runSelectQuery(sql1);
  	        	number=dt.getRowsCount();
  	        	
  	        	String sql = "update MONTHTASK set NUMPARTICIPANT="+number+" where TASKNO='"+taskno+"'";
  	        	db.run(sql);
				
			}catch(Exception e)
			{
				
			}
			
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			
			
			res += "window.close();";
			res += "window.dialogArguments.reloadPage();";
			res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			res += "window.open('../task/ParticipantList.jsp?taskno="+taskno+"'"+",'_self');";
			//res += "window.parent.open('../task/TaskListDate.jsp?date="+date+"','tasklist');";
			//res += "window.parent.tasklist.reload();";
			
			//System.out.println(date);
			
			return res;
		}
		else if (action != null && action.equals("modify"))
		{
            String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Update(request);	

		}
		


		
		else if(action != null && action.equals("del"))
		{
			String date = Format.NullToBlank(request.getParameter("date"));
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			int number=0;
			String taskno = request.getParameter("TASKNO");
			String participantcode = request.getParameter("participantcode");
			Map<String,String> deletemap = new HashMap<String, String>();
			
			deletemap.put("TASKNO", taskno);
			deletemap.put("PARTICIPANTCODE", participantcode);
			eo.setDeletemap(deletemap);
			res0 = eo.Delete();
			//System.out.println("运行到这里");
			try
			{
				DBObject db = new DBObject();
				String sql1="select * from monthtaskparticipant t where t.TASKNO='"+taskno+"'";
			
  	        	
  	        	
  	        	DataTable dt =db.runSelectQuery(sql1);
  	        	number=dt.getRowsCount();
  	        	
  	        	String sql = "update MONTHTASK set NUMPARTICIPANT="+number+" where TASKNO='"+taskno+"'";
  	        	db.run(sql);
				
			}catch(Exception e)
			{
				
			}
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../task/ParticipantList.jsp?taskno="+taskno+"'"+",'_self');";
			res += "window.open('../task/TaskListDate.jsp?date="+date+"','tasklist');";
			
			return res;
			
		}
		
		else if (action != null && action.equals("report"))
		{
            String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Update(request);	
			
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../task/TaskReport.jsp?','_self');";
			
			return res;

		}
		
		else if (action != null && action.equals("audit"))
		{
            String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Update(request);
			res += "show('"+res0+"审核成功"+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.close();";
			res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
			
			return res;

		}
	
		res += "show('"+res0+"');";
		res += "var rand=Math.floor(Math.random()*10000);";
		res += "window.close();";
		res += "window.dialogArguments.window.location = window.dialogArguments.window.location;";
		
		return res;
	}

}
