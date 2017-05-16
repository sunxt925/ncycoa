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
<%@page import="org.activiti.engine.impl.cmd.*"%>
<%@page import="org.activiti.engine.impl.interceptor.Command,com.workflow.serviceimpl.ProcessInstanceDiagramCmd"%>

<%
	String processInstanceId=request.getParameter("id");
	//OutputStream os = response.getOutputStream();

	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	Command<InputStream> cmd  = new ProcessInstanceDiagramCmd(processInstanceId,processEngine);
	InputStream is = processEngine.getManagementService()
			.executeCommand(cmd);
    byte[] b = new byte[1024];  
    int len = -1;  
    while((len = is.read(b, 0, 1024)) != -1) {  
        response.getOutputStream().write(b, 0, len);  
        // 防止异常：getOutputStream() has already been called for this response  
        out.clear();  
        out = pageContext.pushBody();  
    } 
	
%>