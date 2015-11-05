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
	
	@Column(name="PARTY_NAME")
	private String partyName;           //��ͬ�Է�����
	
	@Column(name="PARTY_ADDRESS")
	private String partyaddress;        //��ͬ�Է�ס��
	
	@Column(name="PARTY_TYPE")
	private String partyType;           //��ͬ�Է�����
	
	@Column(name="PARTY_REGVALUE")
	private String partyRegValue;       //��ͬ�Է�ע���ʱ�
	
	@Column(name="CONTRACT_OBJECT")
	private String contractObject; 		// ��ͬ���
	
	@Column(name="BUDGET_VALUE")
	private BigDecimal budgetValue;   // Ԥ���ʽ�
	
	@Column(name="CONTRACT_VALUE")
	private BigDecimal contractValue;   // ��ͬ���
	
	@Temporal(TemporalType.DATE)
	@Column(name="SIGNING_DATE")
	private Date signingDate;			// ��ͬǩ��ʱ��
	
	@Column(name="IMPLEMET_STAGE")
	private String implementationStage; // ��ִͬ�����
	
	@Column(name="IMPLEMET_FREE")
	private BigDecimal implementationFree; // ��ִͬ�н��
	
	@Temporal(TemporalType.DATE)
	@Column(name="FINISHING_DATE")
	private Date finishingDate;		    // ��ͬ���ʱ��
	
	@Column(name="RENEWAL")
	private String renewal; 		    // ��ͬ��ǩ���
	
	@Column(name="CONTRACT_FILE_PATH")
	private String contractFilePath;    // ��ͬ�ı��洢·��
	
	@Column(name="CONTRACT_AUDITFILE_PATH")//��˱��ļ�·��
	private String audittable;
	
	@Column(name="CONTRACT_AUDITCTX")//��������
	private String auditctx;
	
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
	
	@Column(name="EFFECTIVEDATE")
	private Date effectiveDate;   //��Ч��
	
	@Column(name="CHENGBANLEADER")
	private String chengbanleader;//�а첿�ŷֹ��쵼
	
	@Column(name="CAIGOULEADER")
	private String caigouleader;//�ɹ����ŷֹ��쵼
	
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

	public BigDecimal getImplementationFree() {
		return implementationFree;
	}

	public void setImplementationFree(BigDecimal implementationFree) {
		this.implementationFree = implementationFree;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getAuditctx() {
		return auditctx;
	}

	public void setAuditctx(String auditctx) {
		this.auditctx = auditctx;
	}

	public String getChengbanleader() {
		return chengbanleader;
	}

	public void setChengbanleader(String chengbanleader) {
		this.chengbanleader = chengbanleader;
	}

	public String getCaigouleader() {
		return caigouleader;
	}

	public void setCaigouleader(String caigouleader) {
		this.caigouleader = caigouleader;
	}

	public BigDecimal getBudgetValue() {
		return budgetValue;
	}

	public void setBudgetValue(BigDecimal budgetValue) {
		this.budgetValue = budgetValue;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyaddress() {
		return partyaddress;
	}

	public void setPartyaddress(String partyaddress) {
		this.partyaddress = partyaddress;
	}

	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	public String getPartyRegValue() {
		return partyRegValue;
	}

	public void setPartyRegValue(String partyRegValue) {
		this.partyRegValue = partyRegValue;
	}

	
	
}
