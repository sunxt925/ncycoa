package com.workflow.service;

import com.workflow.orm.InstanceInfo;

public interface InstanceService {
	public boolean saveInstance(InstanceInfo instanceinfo);
	public InstanceInfo loadInstanceById(String instanceid) throws Exception;
	public boolean delete();
	public boolean deleteById(String pid);
}
