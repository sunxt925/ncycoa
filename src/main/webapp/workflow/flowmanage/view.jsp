<%@page import="java.io.*"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="java.util.*,com.entity.system.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.task.*"%>
<%@page import="org.activiti.engine.runtime.*,org.activiti.engine.impl.persistence.entity.*"%>
<%@page import="org.activiti.engine.impl.RepositoryServiceImpl"%>
<%@page import="org.activiti.engine.impl.pvm.process.ActivityImpl"%>
<%@page import="org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程图</title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/js/jtip/jquery.cluetip.css" type="text/css" />
 <link rel="stylesheet" href="<%=request.getContextPath()%>/js/jtip/demo.css" type="text/css" />
 <script src="<%=request.getContextPath()%>/js/jtip/jquery-1.7.1.js"></script>
 <script src="<%=request.getContextPath()%>/js/jtip/jquery.cluetip.js"></script>
 <script type="text/javascript">
 	$(function(){
 		$('#flow').cluetip({
 							splitTitle: '|',
 							//hoverClass: 'highlight',
 							width: '150px',
							cursor: 'pointer',
							//tracking: true,
							cluetipClass: 'rounded'//jtip
 							});
 	});
 </script>
</head>

<%
String processInstanceId = request.getParameter("processInstanceId");
String taskId = request.getParameter("taskId");

ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
TaskService taskService = processEngine
.getTaskService();
	RepositoryService repositoryService = processEngine.getRepositoryService();
	if (taskId != null) {
		Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		processInstanceId=task.getProcessInstanceId();
	}
 	RuntimeService runtimeService = processEngine.getRuntimeService();
	
	List<Execution> executionlist =  runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
	ExecutionEntity executionEntity=(ExecutionEntity) executionlist.get(0);
	String procDefId = executionEntity.getProcessDefinitionId();
	List<String> activitiIds=new ArrayList<String>();
	for(int i=0;i<executionlist.size();i++){
		ExecutionEntity executionentity=(ExecutionEntity) executionlist.get(i);
		String activitiid=executionentity.getActivityId();
		if(activitiid!=null){
			Boolean flag=true;
			for(String activiti :activitiIds){
				if(activitiid.equals(activiti)){
					flag=false;
				}
			}
			if(flag){
				activitiIds.add(activitiid);
			}
		}
	}
        
	
	
	
ProcessDefinition processDefinition = repositoryService  
                    .createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();  
  
            ProcessDefinitionImpl pdImpl = (ProcessDefinitionImpl) processDefinition;  
            String processDefinitionId = pdImpl.getId();// 流程标识  
  
            ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)  
                    .getDeployedProcessDefinition(processDefinitionId);  
            List<ActivityImpl> actImpls =new ArrayList<ActivityImpl>();  
              
  
            List<ActivityImpl> activitiList = def.getActivities();// 获得当前任务的所有节点  
          for(String activitiId : activitiIds){  
            for (ActivityImpl activityImpl : activitiList) {  
                String id = activityImpl.getId();  
                if (id.equals(activitiId)) {// 获得执行到那个节点  
                    actImpls.add(activityImpl);  
                    break;  
                }  
            }
          }
        
 
	
	// 获取当前任务
	List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
	String message="待办人:";
	for(Task task :tasks){
		String assignee=task.getAssignee();
		StaffInfo staffinfo=new StaffInfo(assignee);
		String staffname=staffinfo.getName();
		message=message+"|"+staffname;
	}
	
%>
<body>
<div>
<img alt="" src="pic.jsp?id=<%=procDefId %>" style="text-align: center;position:absolute;left:0.4%;top:0.5%;">
<%for(ActivityImpl actImpl : actImpls){ %>
<div id="flow" style="position:absolute; border:2px solid red;left:<%=actImpl.getX() %>px;top:<%=actImpl.getY()%>px;width:<%=actImpl.getWidth()+3 %>px;height:<%=actImpl.getHeight() %>px;" title="<%=message%>"></div>
<%} %>
</div>
</body>
</html>