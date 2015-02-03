<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.workflow.serviceimpl.InstanceServiceImpl"%>
<%@page import="java.util.* " %>
<%@ page import="org.jbpm.api.* " %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
String pid=request.getParameter("pid");
InstanceServiceImpl instance=new InstanceServiceImpl();
boolean flag=instance.deleteById(pid);

ProcessEngine processEngine=Configuration.getProcessEngine();
ExecutionService executionService = processEngine.getExecutionService();
executionService.deleteProcessInstanceCascade(pid); 
if(flag){
response.getWriter().write("删除成功");
}else{
response.getWriter().write("删除失败");
}
response.getWriter().flush();
	response.getWriter().close();
%>
