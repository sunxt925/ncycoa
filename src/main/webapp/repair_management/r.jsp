<%@page import="oracle.net.aso.a"%>
<%@ page language="java" pageEncoding="gb2312"%>
<%@page import="java.io.*"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="java.util.*,com.entity.system.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.task.*"%>
<%@page import="org.activiti.engine.runtime.*,org.activiti.engine.impl.persistence.entity.*"%>
<%@page import="org.activiti.engine.impl.RepositoryServiceImpl"%>
<%@page import="org.activiti.engine.impl.pvm.process.ActivityImpl"%>
<%@page import="org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>计划管理</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_Datatype.js"></script>

<script type="text/javascript" src="js/MyDatePicker/WdatePicker.js"></script></HEAD>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
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
	
	Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
	String processDefId = task.getProcessDefinitionId();
	ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)processEngine.getRepositoryService().getProcessDefinition(processDefId);
	
	String processInsID = task.getProcessInstanceId();
	
	ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInsID).singleResult();
	
	String activitid = pi.getActivityId();
	
	ActivityImpl activityImpl = processDefinitionEntity.findActivity(activitid);
	
/* 	if (taskId != null) {
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
          } */
%>

<body >
<img  src="pic.jsp?processDefid=<%=processInstanceId %>" style="position:absolute;left:66px;top:193px;">

<div  style="position:absolute; border:1px solid red;left:<%=activityImpl.getX()%>px;top:<%=activityImpl.getY()%>px;width:<%=activityImpl.getWidth()%>px;height:<%=activityImpl.getHeight()%>px;" ></div>

</body>