package com.workflow.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.system.StaffInfo;
import com.workflow.orm.InstanceInfo;
import com.workflow.service.InstanceService;

@Service("flowTool")
@Transactional
public class flowTool {
	@Autowired
	private ProcessEngine processEngine;
	public List<String> getFlownames(){
		return null;
	}
	public List<String> getInstancesByFlowKey(String key) throws Exception{
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		RuntimeService runtimeService = processEngine
				.getRuntimeService();
		HistoryService historyService=processEngine.getHistoryService();
		ArrayList<String> instances=new ArrayList<String>();
		for (ProcessInstance processInstance : runtimeService.createProcessInstanceQuery().list()) {
			if(processInstance.getProcessDefinitionKey().equals(key)){
				HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
				StaffInfo user=new StaffInfo(historicProcessInstance.getStartUserId());
				instances.add(user.getName()+":"+sdf.format(historicProcessInstance.getStartTime()));
			}		
		}
		return instances;
	}
}
