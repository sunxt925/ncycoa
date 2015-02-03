package com.workflow.std.jbpm;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class HandlerDecision implements DecisionHandler{

	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		String toWhere =execution.getVariable("toWhere").toString();
		String result=null;
		if("yingxiao".equals(toWhere)){
			result="Ӫ���ּ���ίԱ��";
		}else if("zhuanmai".equals(toWhere)){
			result="ר���ּ���ίԱ��";
		}else if("anquan".equals(toWhere)){
			result="��ȫ�ּ���ίԱ��";
		}else if("wuliu".equals(toWhere)){
			result="�����ּ���ίԱ��";
		}else if("jichu".equals(toWhere)){
			result="��������ּ���ίԱ��";
		}
		return result;
	}

}
