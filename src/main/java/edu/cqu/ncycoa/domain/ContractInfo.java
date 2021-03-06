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
	
	@Column(name="PARTY_NAME")
	private String partyName;           //合同对方名称
	
	@Column(name="PARTY_ADDRESS")
	private String partyaddress;        //合同对方住所
	
	@Column(name="PARTY_TYPE")
	private String partyType;           //合同对方性质
	
	@Column(name="PARTY_REGVALUE")
	private String partyRegValue;       //合同对方注册资本
	
	@Column(name="CONTRACT_OBJECT")
	private String contractObject; 		// 合同标的
	
	@Column(name="BUDGET_VALUE")
	private String budgetValue;   // 预算资金
	
	@Column(name="CONTRACT_VALUE")
	private String contractValue;   // 合同金额
	
	@Temporal(TemporalType.DATE)
	@Column(name="SIGNING_DATE")
	private Date signingDate;			// 合同签订时间
	
	@Column(name="IMPLEMET_STAGE")
	private String implementationStage; // 合同执行情况
	
	@Column(name="IMPLEMET_FREE")
	private BigDecimal implementationFree; // 合同执行金额
	
	@Temporal(TemporalType.DATE)
	@Column(name="FINISHING_DATE")
	private Date finishingDate;		    // 合同完成时间
	
	@Column(name="RENEWAL")
	private String renewal; 		    // 合同续签情况
	
	@Column(name="CONTRACT_FILE_PATH")
	private String contractFilePath;    // 合同文本存储路径
	
	@Column(name="CONTRACT_AUDITFILE_PATH")//审核表文件路径
	private String audittable;
	
	@Column(name="CONTRACT_AUDITCTX")//审批事项
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
	private Date effectiveDate;   //有效期
	
	@Column(name="CHENGBANLEADER")
	private String chengbanleader;//承办部门分管领导
	
	@Column(name="CAIGOULEADER")
	private String caigouleader;//采购部门分管领导
	
	@Column(name="OTHERFILE")
	private String otherfile;//其他文件
	
	@Column(name="FINALLYAUDITTIME")
	private Date finallyAuditTime;//最终审核时间
	
	@Column(name="INPUTDATE")
	private Date inputDate;//合同录入日期
	
	@Column(name="APPLYUSERCODE")
	private String applyUserCode;//提交用户编码
	
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

	

	public String getBudgetValue() {
		return budgetValue;
	}

	public void setBudgetValue(String budgetValue) {
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

	public String getOtherfile() {
		return otherfile;
	}

	public void setOtherfile(String otherfile) {
		this.otherfile = otherfile;
	}

	public Date getFinallyAuditTime() {
		return finallyAuditTime;
	}

	public void setFinallyAuditTime(Date finallyAuditTime) {
		this.finallyAuditTime = finallyAuditTime;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getApplyUserCode() {
		return applyUserCode;
	}

	public void setApplyUserCode(String applyUserCode) {
		this.applyUserCode = applyUserCode;
	}

	
	
}
