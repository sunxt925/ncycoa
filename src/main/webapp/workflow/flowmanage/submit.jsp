<%@page contentType="text/html;charset=UTF-8"%>
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
	String owner = request.getParameter("owner");
	int day = Integer.parseInt(request.getParameter("day"));
	String reason = request.getParameter("reason");
	
	Map map = new HashMap();
	map.put("day", day);
	map.put("reason", reason);
	map.put("toWhere", "zhuanmai");

	taskService.complete(taskId,map);
	
	response.sendRedirect("index.jsp");
%>