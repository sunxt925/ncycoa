<%@page contentType="text/html;charset=gb2312"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.ProcessDefinition"%>

<%@page import="org.activiti.engine.task.Task"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="java.util.*"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	TaskService taskService = processEngine
			.getTaskService();
	String taskId = request.getParameter("id");
%>
	<form action="approvesubmit.jsp">
		<input type="hidden" value="${param.id}" name="taskId"><br>
		�����ˣ�<%=taskService.getVariable(taskId, "owner") %><br>
		����������<%=taskService.getVariable(taskId, "day") %><br>
		����ԭ��<%=taskService.getVariable(taskId, "reason") %><br>
		<input type="submit" value="������׼" name="result">
	</form>
</body>
</html>