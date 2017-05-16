package edu.cqu.ncycoa.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.CodeDictionary;

public class ContractQuery {
	
	private String code;  	 			// ��ͬ��ţ������ű�ţ�
	
	private String name;  	 			// ��ͬ����
	
	private String type;     			// ��ͬ���
	
	private String relevantDepartment;  // ʵʩ����
	
	private String contactMethod;       //ʵʩ��ʽ
	
	private String partyA;	 			// ��ͬ�׷�
	
	private String partyB;   			// ��ͬ�ҷ�
	
	private String contractObject; 		// ��ͬ���
	
	private String contractValue;   // ��ͬ���
	
	private Date signingDate;			// ��ͬǩ��ʱ��
	
	private String implementationStage; // ��ִͬ�����
	
	private BigDecimal implementationFree; // ��ִͬ�н��
	
	private Date finishingDate;		    // ��ͬ���ʱ��
	
	private String renewal; 		    // ��ͬ��ǩ���

	public static List<ContractQuery> getContractQuery(List<ContractInfo> contractInfos){
	    Map<String, String> type_map = new HashMap<String, String>();
	    Map<String, String> method_map = new HashMap<String, String>();
		{
			type_map.put("0", "������ͬ");
			type_map.put("1", "������ͬ");
			type_map.put("2", "���޺�ͬ");
			type_map.put("3", "�ִ���ͬ");
			type_map.put("4", "������ͬ");
			type_map.put("5", "����ʩ��(ά��)��ͬ");
			type_map.put("6", "������ͬ");
			type_map.put("7", "ί�к�ͬ");
			type_map.put("8", "�����ͬ");
			type_map.put("9", "�����ͬ");
			method_map.put("0","�����б�");
			method_map.put("1","�����б�");
			method_map.put("2","������̸��");
			method_map.put("3","ѯ��");
			method_map.put("4","��һ��Դ");
			
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
