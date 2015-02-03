<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.workflow.std.jbpm.*" %>
<%@ page import="org.jbpm.api.*,org.jbpm.api.task.Task" %>
<%
	UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
	ProcessHandler processhandler=new ProcessHandler();
	Boolean flag=processhandler.startProcessinstance(staffcode);
	String url="";
	if(flag){
		ProcessEngine processEngine=Configuration.getProcessEngine();
		TaskService taskService=processEngine.getTaskService();  
		List<Task> groupTaskList=taskService.findPersonalTasks(staffcode);
		int length=groupTaskList.size();
		Task task=groupTaskList.get(length-1);
		url="std_applymanage.jsp?id="+task.getId();
	}else{
		url="sorry.jsp";
	}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>南充市烟草公司</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                  <td width="100%" >
               <iframe src="<%=url %>" name="list" id="list" height="100%"  width="100%" frameborder="0"></iframe>
                 </td>
                   </tr>
            </table> 
  </body>
</html>