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
	private String code;  	 			// ��ͬ��ţ������ű�ţ�
	
	@Column(name="CONTRACT_NAME")
	private String name;  	 			// ��ͬ����
	
	@Column(name="CONTRACT_TYPE")
	private String type;     			// ��ͬ���
	
	@Column(name="RELEVANT_DEPART")
	private String relevantDepartment;  // ʵʩ����
	
	@Column(name="PARTY_A")
	private String partyA;	 			// ��ͬ�׷�
	
	@Column(name="PARTY_B")
	private String partyB;   			// ��ͬ�ҷ�
	
	@Column(name="CONTRACT_OBJECT")
	private String contractObject; 		// ��ͬ���
	
	@Column(name="CONTRACT_VALUE")
	private BigDecimal contractValue;   // ��ͬ���
	
	@Temporal(TemporalType.DATE)
	@Column(name="SIGNING_DATE")
	private Date signingDate;			// ��ͬǩ��ʱ��
	
	@Column(name="IMPLEMET_STAGE")
	private String implementationStage; // ��ִͬ�����
	
	@Temporal(TemporalType.DATE)
	@Column(name="FINISHING_DATE")
	private Date finishingDate;		    // ��ͬ���ʱ��
	
	@Column(name="RENEWAL")
	private String renewal; 		    // ��ͬ��ǩ���
	
	@Column(name="CONTRACT_FILE_PATH")
	private String contractFilePath;    // ��ͬ�ı��洢·��
	
	@Column(name="CONTRACT_AUDITFILE_PATH")//��˱��ļ�·��
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
