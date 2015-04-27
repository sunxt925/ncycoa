<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.ProcessDefinition"%>

<%@page import="org.activiti.engine.task.Task"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="java.util.*,java.io.File"%>


<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	
	RepositoryService repositoryService = processEngine.getRepositoryService();
	TaskService taskService = processEngine.getTaskService();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	String taskid=request.getParameter("id");
	
	Object reportpathobject = taskService.getVariable(taskid, "reportpath");
	if(reportpathobject!=null){
		String report=reportpathobject.toString();
		if(!(report.equals(""))){
			String[] reports=report.split(";");
			for(int m=0;m<reports.length;m++){
				File deleteroport=new File((request.getSession()
						.getServletContext().getRealPath("doc")
						+ "/checkproject/" + reports[m]));
				deleteroport.delete();
			}
		}
	}
	Object allreportpathobject = taskService.getVariable(taskid, "allreportpath");
	if(allreportpathobject!=null){
		String allreport=allreportpathobject.toString();
		if(!(allreport.equals(""))){
			String[] allreports=allreport.split(";");
			for(int m=0;m<allreports.length;m++){
				File deleteroport=new File((request.getSession()
						.getServletContext().getRealPath("doc")
						+ "/checkproject/" + allreports[m]));
				deleteroport.delete();
			}
		}
	}
	Object filepath = taskService.getVariable(taskid, "filepath");
	if (filepath != null){
		String[] filenames=filepath.toString().split(";");
		for (int m = 0; m < filenames.length; m++) {
		File deletefile = new File((request.getSession()
				.getServletContext().getRealPath("doc")
				+ "/checkproject/" + filenames[m]));
		deletefile.delete();
		}
		
	}
	
	Task task = processEngine.getTaskService().createTaskQuery().taskId(taskid).singleResult();
	String instanceid=task.getProcessInstanceId();
	runtimeService.deleteProcessInstance(instanceid,"f");

	//runtimeService.suspendProcessInstanceById(instanceid);//挂起
	
%>