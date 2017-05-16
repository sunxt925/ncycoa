<%@page contentType="text/html;charset=gb2312"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.ProcessDefinition"%>

<%@page import="org.activiti.engine.task.Task"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="java.util.*"%>


<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	TaskService taskService = processEngine
			.getTaskService();
	String taskId = request.getParameter("taskId");
	request.setCharacterEncoding("gb2312");
	
	Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("public", true);
	taskService.complete(taskId,variables);//Ö´ĞĞ  ÓĞ²Î
	
	
	
// 	ts.setVariables(taskId,map);
// 	ts.completeTask(taskId,result);
	
	response.sendRedirect("index.jsp");
%>