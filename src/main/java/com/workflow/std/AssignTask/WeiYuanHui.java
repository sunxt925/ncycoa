package com.workflow.std.AssignTask;

import java.util.List;  

import org.jbpm.api.Configuration;  
import org.jbpm.api.ProcessEngine;  
import org.jbpm.api.TaskService;  
import org.jbpm.api.model.OpenExecution;  
import org.jbpm.api.task.Assignable;  
import org.jbpm.api.task.AssignmentHandler;  
import org.jbpm.api.task.Participation;  
import org.jbpm.api.task.Task;  
import org.jbpm.pvm.internal.task.OpenTask;  
  
public class WeiYuanHui implements AssignmentHandler{  
  
    private static final long serialVersionUID = 1L;  
    List<String> WeiYuanHuiGroup;  
    ProcessEngine processEngine=Configuration.getProcessEngine();  
    TaskService taskService=processEngine.getTaskService();  
      
    @SuppressWarnings("unchecked")  
    public void assign(Assignable assignable, OpenExecution execution) throws Exception {   
        String pid=execution.getProcessInstance().getId();  
        //System.out.println("pid :"+pid);  
        Task task=taskService.createTaskQuery().processInstanceId(pid).activityName(execution.getName()).uniqueResult();  
        //System.out.println(task.getName());  
        WeiYuanHuiGroup=(List<String>)taskService.getVariable(task.getId(), "WeiYuanHuiGroup");  
        if(WeiYuanHuiGroup!=null){  
            for(String participant:WeiYuanHuiGroup){  
                //System.out.println(participant);  
                Task subTask=((OpenTask)task).createSubTask();  
                subTask.setName(task.getId());//
                subTask.setDescription("stdapply/std_applyapp.jsp");//
                subTask.setAssignee(participant);  
                taskService.addTaskParticipatingUser(task.getId(), participant, Participation.CLIENT);  
            }  
        }  
    }  
} 