package com.workflow.std.jbpm;

import org.jbpm.api.TaskService;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.api.task.Task;
  
public class Sign implements Command<Boolean>{  
  
    private static final long serialVersionUID = 1L;  
    public  String VAR_SIGN;  
    private String PASS="审核通过";  
    private String NOPASS="驳回";  
    private String parentTaskId;  
    private Task parentTask;  
    private Task joinTask;  
    String pid;  
    public Sign(String parentTaskId,Task joinTask,String var_sign){  
        this.parentTaskId=parentTaskId;   
        this.joinTask=joinTask;  
        this.VAR_SIGN=var_sign;
    }  
      
    public String getPid(){  
        return pid;  
    }  
      
    public Boolean execute(Environment environment) throws Exception {  
        TaskService taskService=environment.get(TaskService.class);  
        parentTask=taskService.getTask(parentTaskId);  
        pid=parentTask.getExecutionId();  
//        String sign=(String)taskService.getVariable(joinTask.getId(), VAR_SIGN);  
        if(VAR_SIGN!=null&&VAR_SIGN.equals("不同意")){  
//        	TaskImpl test  =(TaskImpl)taskService.getTask(parentTaskId);  
//            test.removeSubTask(joinTask); 
            taskService.completeTask(joinTask.getId());  
            taskService.completeTask(parentTask.getId(), NOPASS);  
            return true;  
        }  
//        TaskImpl test  =(TaskImpl)taskService.getTask(parentTaskId);  
//        test.removeSubTask(joinTask); 
        taskService.completeTask(joinTask.getId());  
        if(taskService.getSubTasks(parentTaskId).size()==0){  
            taskService.completeTask(parentTaskId,PASS);  
            return true;  
        }  
        return false;  
    }  
  
}  