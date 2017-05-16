package edu.cqu.ncycoa.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.CodeDictionary;

public class ContractQuery {
	
	private String code;  	 			// 合同编号（按部门编号）
	
	private String name;  	 			// 合同名称
	
	private String type;     			// 合同类别
	
	private String relevantDepartment;  // 实施部门
	
	private String contactMethod;       //实施方式
	
	private String partyA;	 			// 合同甲方
	
	private String partyB;   			// 合同乙方
	
	private String contractObject; 		// 合同标的
	
	private String contractValue;   // 合同金额
	
	private Date signingDate;			// 合同签订时间
	
	private String implementationStage; // 合同执行情况
	
	private BigDecimal implementationFree; // 合同执行金额
	
	private Date finishingDate;		    // 合同完成时间
	
	private String renewal; 		    // 合同续签情况

	public static List<ContractQuery> getContractQuery(List<ContractInfo> contractInfos){
	    Map<String, String> type_map = new HashMap<String, String>();
	    Map<String, String> method_map = new HashMap<String, String>();
		{
			type_map.put("0", "其他合同");
			type_map.put("1", "买卖合同");
			type_map.put("2", "租赁合同");
			type_map.put("3", "仓储合同");
			type_map.put("4", "技术合同");
			type_map.put("5", "建设施工(维修)合同");
			type_map.put("6", "承揽合同");
			type_map.put("7", "委托合同");
			type_map.put("8", "赠与合同");
			type_map.put("9", "运输合同");
			method_map.put("0","公开招标");
			method_map.put("1","邀请招标");
			method_map.put("2","竞争性谈判");
			method_map.put("3","询价");
			method_map.put("4","单一来源");
			
		}
		List<ContractQuery> contractQueries = new ArrayList<ContractQuery>();
		for(ContractInfo contractInfo : contractInfos){
			ContractQuery contractQuery = new ContractQuery();
			contractQuery.setCode(contractInfo.getCode());
			contractQuery.setName(contractInfo.getName());
			contractQuery.setType(type_map.get(contractInfo.getType()));
			contractQuery.setRelevantDepartment(CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",contractInfo.getRelevantDepartment()));
			if(null!=contractQuery.getContactMethod()&&!contractQuery.getContactMethod().equals("")){
				contractQuery.setContactMethod(method_map.get(contractInfo.getContactMethod()));
			}else{
				contractQuery.setContactMethod(method_map.get(contractInfo.getContactMethod()));
			}
			contractQuery.setPartyA(contractInfo.getPartyA());
			contractQuery.setPartyB(contractInfo.getPartyB());
			contractQuery.setContractObject(contractInfo.getContractObject());
			contractQuery.setContractValue(contractInfo.getContractValue());
			contractQuery.setSigningDate(contractInfo.getSigningDate());
			contractQuery.setImplementationStage(contractInfo.getImplementationStage());
			contractQuery.setImplementationFree(contractInfo.getImplementationFree());
			contractQuery.setFinishingDate(contractInfo.getFinishingDate());
			contractQuery.setRenewal(contractInfo.getRenewal());
			contractQueries.add(contractQuery);
		}
		return contractQueries;
	}


	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRelevantDepartment() {
		return relevantDepartment;
	}

	public void setRelevantDepartment(String relevantDepartment) {
		this.relevantDepartment = relevantDepartment;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getContractObject() {
		return contractObject;
	}

	public void setContractObject(String contractObject) {
		this.contractObject = contractObject;
	}

	

	public String getContractValue() {
		return contractValue;
	}




	public void setContractValue(String contractValue) {
		this.contractValue = contractValue;
	}




	public Date getSigningDate() {
		return signingDate;
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public String getImplementationStage() {
		return implementationStage;
	}

	public void setImplementationStage(String implementationStage) {
		this.implementationStage = implementationStage;
	}

	public BigDecimal getImplementationFree() {
		return implementationFree;
	}

	public void setImplementationFree(BigDecimal implementationFree) {
		this.implementationFree = implementationFree;
	}

	public Date getFinishingDate() {
		return finishingDate;
	}

	public void setFinishingDate(Date finishingDate) {
		this.finishingDate = finishingDate;
	}

	public String getRenewal() {
		return renewal;
	}

	public void setRenewal(String renewal) {
		this.renewal = renewal;
	}
	
	
	
}
