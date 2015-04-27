<%@page import="com.workflow.serviceimpl.ProcessInstanceDiagramCmd"%>
<%@page import="org.activiti.engine.impl.interceptor.Command"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.task.*"%>

<%
	String procDefId="RepairAudit:1:27504";
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
TaskService taskService = processEngine
.getTaskService();
	RepositoryService repositoryService = processEngine.getRepositoryService();
//	ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
//	String diagramResourceName = procDef.getDiagramResourceName();  
// InputStream imageStream = repositoryService.getResourceAsStream(  
//         procDef.getDeploymentId(), diagramResourceName);  
ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();

String diagramResourceName = processDefinition.getDiagramResourceName();
InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
byte[] b = new byte[1024];  
int len = -1;  
while((len = imageStream.read(b, 0, 1024)) != -1) {  
    response.getOutputStream().write(b, 0, len);  
    // 防止异常：getOutputStream() has already been called for this response  
    out.clear();  
    out = pageContext.pushBody();  
} 
%>