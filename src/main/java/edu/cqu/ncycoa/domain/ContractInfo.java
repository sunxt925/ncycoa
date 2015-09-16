package edu.cqu.ncycoa.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="NCYCOA_CONTRACT_INFO")
public class ContractInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CONTRACT_INFO_ID")
	private Long id;	
	
	@Column(name="CONTRACT_CODE")
	private String code;  	 			// 合同编号（按部门编号）
	
	@Column(name="CONTRACT_NAME")
	private String name;  	 			// 合同名称
	
	@Column(name="CONTRACT_TYPE")
	private String type;     			// 合同类别
	
	@Column(name="RELEVANT_DEPART")
	private String relevantDepartment;  // 实施部门
	
	@Column(name="PARTY_A")
	private String partyA;	 			// 合同甲方
	
	@Column(name="PARTY_B")
	private String partyB;   			// 合同乙方
	
	@Column(name="CONTRACT_OBJECT")
	private String contractObject; 		// 合同标的
	
	@Column(name="CONTRACT_VALUE")
	private BigDecimal contractValue;   // 合同金额
	
	@Temporal(TemporalType.DATE)
	@Column(name="SIGNING_DATE")
	private Date signingDate;			// 合同签订时间
	
	@Column(name="IMPLEMET_STAGE")
	private String implementationStage; // 合同执行情况
	
	@Temporal(TemporalType.DATE)
	@Column(name="FINISHING_DATE")
	private Date finishingDate;		    // 合同完成时间
	
	@Column(name="RENEWAL")
	private String renewal; 		    // 合同续签情况
	
	@Column(name="CONTRACT_FILE_PATH")
	private String contractFilePath;    // 合同文本存储路径
	
	@Column(name="CONTRACT_AUDITFILE_PATH")//审核表文件路径
	private String audittable;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="CONTACTER")
	private String contacter;
	
	@Column(name="CONTACT_METHOD")
	private String contactMethod;
	
	@Column(name="STATUS")
	private Short status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="APP_DATE")
	private Date appDate;
	
	@Column(name="APP_DEPART")
	private String appDepart;
	
	@Column(name="IMPL_METHOD")
	private String implMethod;
	
	@Column(name="PROCESS_INS_ID")
	private String processInstanceId;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getAppDepart() {
		return appDepart;
	}

	public void setAppDepart(String appDepart) {
		this.appDepart = appDepart;
	}

	public String getImplMethod() {
		return implMethod;
	}

	public void setImplMethod(String implMethod) {
		this.implMethod = implMethod;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	private String memo;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getContractValue() {
		return contractValue;
	}

	public void setContractValue(BigDecimal contractValue) {
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

	public String getContractFilePath() {
		return contractFilePath;
	}

	public void setContractFilePath(String contractFilePath) {
		this.contractFilePath = contractFilePath;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getAudittable() {
		return audittable;
	}

	public void setAudittable(String audittable) {
		this.audittable = audittable;
	}
	
}
