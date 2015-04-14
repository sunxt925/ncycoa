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
	
	RepositoryService repositoryService = processEngine.getRepositoryService();
	TaskService taskService = processEngine.getTaskService();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	String instanceid=request.getParameter("processInstanceId");
	runtimeService.deleteProcessInstance(instanceid,"f");
	//runtimeService.suspendProcessInstanceById(instanceid);//挂起
	response.sendRedirect("index.jsp");
	
%>