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
// 	List<ProcessDefinition> procDefList = repositoryService.createProcessDefinitionQuery()
// 			.processDefinitionKey("stdflow")//获得指定流程的工作流
// 			.orderByProcessDefinitionVersion()
// 			.desc()
// 			.list();
// 			for(ProcessDefinition task : procDefList){
// 				System.out.println("deploymentid:::::"+task.getDeploymentId());
// 				repositoryService.deleteDeployment(task.getDeploymentId(),true);
// 			}
String defid=request.getParameter("id");
ProcessDefinition task=repositoryService.getProcessDefinition(defid);
				repositoryService.deleteDeployment(task.getDeploymentId(),true);
	
	response.sendRedirect("index.jsp");
	
%>