<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.workflow.serviceimpl.*" %>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.runtime.*"%>
<%@page import="org.activiti.engine.task.*"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%@page import="org.activiti.engine.form.*"%>
<%
	UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
	ProcessHandler processhandler=new ProcessHandler();
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	Boolean flag=processhandler.startProcessinstance(staffcode,processEngine);
	String url="";
	if(flag){
		
		TaskService taskService = processEngine
				.getTaskService();
		List<Task> groupTaskList=taskService.createTaskQuery().taskAssignee(staffcode).orderByTaskCreateTime().desc().list();
		Task task=groupTaskList.get(0);
		url="pro_apply.jsp?id="+task.getId();
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