package com.workflow.serviceimpl;

import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;


public class ProcessInstanceDiagramCmd implements Command<InputStream> {
    protected String processInstanceId;
    protected ProcessEngine processEngine;

    public ProcessInstanceDiagramCmd(String processInstanceId,ProcessEngine processEngine) {
        this.processInstanceId = processInstanceId;
        this.processEngine=processEngine;
    }

    public InputStream execute(CommandContext commandContext) {
        ExecutionEntityManager executionEntityManager = commandContext
            .getExecutionEntityManager();
        ExecutionEntity executionEntity = executionEntityManager
            .findExecutionById(processInstanceId);
        List<String> activiityIds = executionEntity.findActiveActivityIds();
        String processDefinitionId = executionEntity.getProcessDefinitionId();

         GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(
                processDefinitionId);
        BpmnModel bpmnModel = getBpmnModelCmd.execute(commandContext);

        InputStream is = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(bpmnModel,
                "png",activiityIds, activiityIds, processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(), processEngine.getProcessEngineConfiguration().getClassLoader(),1.0);

        return is;
    }
}