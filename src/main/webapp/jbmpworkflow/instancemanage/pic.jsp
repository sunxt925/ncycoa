<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.workflow.service.*"%>
<%@page import="com.workflow.serviceimpl.*"%>
<%@page import="com.workflow.orm.*"%>
<%@page import="java.util.*,org.jbpm.api.*,java.util.zip.ZipInputStream" %>

<%
    ProcessEngine pe = Configuration.getProcessEngine();
	RepositoryService rs = pe.getRepositoryService();
	ExecutionService es = pe.getExecutionService();
	
	String id = request.getParameter("id");
	InstanceService instanceimpl=new InstanceServiceImpl();
	InstanceInfo instance=instanceimpl.loadInstanceById(id);
	String png=instance.getPng();
	ProcessInstance pi = es.findProcessInstanceById(id);
	String pdId = pi.getProcessDefinitionId();
	ProcessDefinition pd = rs.createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult();
	
	//OutputStream os = response.getOutputStream();
	InputStream inputStream = rs.getResourceAsStream(pd.getDeploymentId(), png);
	
	/* byte[] b=new byte[1024];
	int len=-1;
	while((len=inputStream.read(b,0,1024)) > 0)
	{
	        os.write(b, 0, len);
	}
	os.flush();
	out = pageContext.pushBody(); */
	
	BufferedImage bi = ImageIO.read(inputStream);
	out.clear(); 
	out = pageContext.pushBody(); 
	ImageIO.write(bi,"PNG",response.getOutputStream());
	
%>