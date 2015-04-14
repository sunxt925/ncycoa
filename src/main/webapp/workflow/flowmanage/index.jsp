<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.util.*"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.runtime.*"%>
<%@page import="org.activiti.engine.task.*"%>
<%@page import="org.activiti.engine.form.*"%>

<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	// Get Activiti services
	RepositoryService repositoryService = processEngine
		.getRepositoryService();
	FormService formService=processEngine.getFormService();
	RuntimeService runtimeService = processEngine
		.getRuntimeService();
	TaskService taskService = processEngine
		.getTaskService();
%>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
<a href="deploy.jsp">deploy</a>
<a href="h2database">h2database</a>
<hr>

<table border="1" width="100%">
  <legend>process definition</legend>
  <thead>
    <tr>
    <th>id</th>
	  <th>key</th>
	  <th>name</th>
	  <th>&nbsp;</th>
	</tr>
  </thead>
  <tbody>
<%
	for (ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().list()) {
		pageContext.setAttribute("processDefinition", processDefinition);
%>
    <tr>
    <td>${processDefinition.id}</td>
	  <td>${processDefinition.key}</td>
	  <td>${processDefinition.name}</td>
	  <td>
	    <a href="start.jsp?id=${processDefinition.id}">发起流程</a>
	    <a href="deleteflow.jsp?id=${processDefinition.id}">删除流程</a>
	    <a href="pic.jsp?id=${processDefinition.id}">流程图</a>
	  </td>
	</tr>
<%
	}
%>
  </tbody>
</table>

<hr>

<table border="1" width="100%">
  <legend>process instance</legend>
  <thead>
    <tr>
	  <th>id</th>
	  <th>process definition</th>
	  <th>&nbsp;</th>
	</tr>
  </thead>
  <tbody>
<%
	for (ProcessInstance processInstance : runtimeService.createProcessInstanceQuery().list()) {
		pageContext.setAttribute("processInstance", processInstance);
%>
    <tr>
	  <td>${processInstance.id}</td>
	  <td>${processInstance.processDefinitionId}</td>
	  <td>
	  <a href="deleteinstance.jsp?processInstanceId=${processInstance.id}">删除实例</a>
	    <a href="view.jsp?processInstanceId=${processInstance.id}">流程实例图</a>
	  </td>
	</tr>
<%
	}
%>
  </tbody>
</table>

<hr>

  </body>
</html>