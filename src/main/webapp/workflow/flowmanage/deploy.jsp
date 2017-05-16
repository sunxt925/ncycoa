<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.activiti.engine.*"%>
<%
// 	String action = request.getParameter("action");
// 	if ("deploy".equals(action)) {
		String filename = "D:\\JbpmExample\\activititest1\\src\\main\\resources\\stdflow.bpmn";
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
		// Get Activiti services
		RepositoryService repositoryService = processEngine
			.getRepositoryService();
		// Deploy the process definition
		repositoryService.createDeployment()
						 .addInputStream("stdflow.bpmn20.xml",new FileInputStream(filename))
						 .deploy();
		response.sendRedirect("index.jsp");
		return;
// 	}
%>