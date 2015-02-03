package com.workflow.std.jbpm;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class HandlerDecision implements DecisionHandler{

	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		String toWhere =execution.getVariable("toWhere").toString();
		String result=null;
		if("yingxiao".equals(toWhere)){
			result="营销分技术委员会";
		}else if("zhuanmai".equals(toWhere)){
			result="专卖分技术委员会";
		}else if("anquan".equals(toWhere)){
			result="安全分技术委员会";
		}else if("wuliu".equals(toWhere)){
			result="物流分技术委员会";
		}else if("jichu".equals(toWhere)){
			result="基础管理分技术委员会";
		}
		return result;
	}

}
