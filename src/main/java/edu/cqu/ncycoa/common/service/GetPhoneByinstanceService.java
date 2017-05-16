package edu.cqu.ncycoa.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.system.StaffInfo;

@Service("GetPhoneByinstanceService")
@Transactional
public class GetPhoneByinstanceService {
	public static boolean isStartEvalu=false;
	
	
	@Resource(name="processEngine")
	ProcessEngine processEngine;


	public String getPhoneByInstanceid(String instanceid){
		String processInstanceId = instanceid;
//		String taskId = request.getParameter("taskId");

//		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
//		ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
		TaskService taskService = processEngine
		.getTaskService();
			RepositoryService repositoryService = processEngine.getRepositoryService();
//			if (taskId != null) {
//				Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
//				processInstanceId=task.getProcessInstanceId();
//			}
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
			String message="";
			for(Task task :tasks){
				String assignee=task.getAssignee();
				StaffInfo staffinfo=new StaffInfo(assignee);
				String phonenumber=staffinfo.getMobilephone();
				if(message.equals(""))
					message=phonenumber;
				else
					message=message+","+phonenumber;
			}
			return message;
	}
	public String getInstanceidByTaskId(String taskid){
		if (taskid != null) {
		Task task = processEngine.getTaskService().createTaskQuery().taskId(taskid).singleResult();
		return task.getProcessInstanceId();
		}
		return null;
	}
}
