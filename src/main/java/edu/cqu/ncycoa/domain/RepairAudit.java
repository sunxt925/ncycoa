package edu.cqu.ncycoa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NCYCOA_REPAIRAUDIT")
public class RepairAudit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REPAIRAUDIT_ID")
	private Long id;
	
	@Column(name="REPAIRAUDIT_PROJECTNAME")
	private String projectName; //项目名称
	
	@Column(name="REPAIRAUDIT_APPORGCODE")
	private String apporgCode; //申请（单位）部门
	
	@Column(name="REPAIRAUDIT_REPAIRFREE")
    private float repairFree; //维修项目预算
	
	@Column(name="REPAIRAUDIT_APPDATE")
    private Date appDate; //申请日期
	
	@Column(name="REPAIRAUDIT_REPAIRCONTENT")
    private String repairContent; //维修主要内容
    
	@Column(name="REPAIRAUDIT_APPORGOPINION")
    private String apporgOpinion; //申请部门意见
	
	@Column(name="REPAIRAUDIT_ZONGHEOPINION")
    private String zongheOpinion; //综合办意见
	
	@Column(name="REPAIRAUDIT_COUNTYOPINION")
    private String countyOpinion; //县局局长（经理）意见
	
	@Column(name="REPAIRAUDIT_GUIKOUORGOPINION")
    private String guikouorgOpinion; //归口管理部门意见
	
	@Column(name="REPAIRAUDIT_SANXIANGOPINION")
    private String sanxiangOpinion; //三项工作管理委员会主任意见

	@Column(name="REPAIRAUDIT_AUDITFLAG")
	private String auditFlag;
	
	@Column(name="REPAIRAUDIT_AUDITDATE")
    private Date auditDate; //申请日期
	
	
	@Column(name="REPAIRAUDIT_PROJECTTYPE")
	private String projectType;
	
	@Column(name="REPAIRAUDIT_SERVICEPROVIDOR")
	private String serviceProvider;//维修商
	
	@Column(name="REPAIRAUDIT_HANDLEPERSON")
	private String handlePerson;//经办人

	@Column(name="REPAIRAUDIT_TRUEFREE")
	private double trueFree;//实际金额
	
	@Column(name="REPAIRAUDIT_HANDLEDATE")
	private Date handleDate;//审批时间
	
	@Column(name="REPAIRAUDIT_ENDTIME")
	private Date endTime;//完成时间
	
	@Column(name="REPAIRAUDIT_ACCEPTOR")
	private String acceptor;//验收人
	
	@Column(name="PROCESS_INS_ID")
	private String processInstanceId;//流程实例ID
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getApporgCode() {
		return apporgCode;
	}

	public void setApporgCode(String apporgCode) {
		this.apporgCode = apporgCode;
	}

	public float getRepairFree() {
		return repairFree;
	}

	public void setRepairFree(float repairFree) {
		this.repairFree = repairFree;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getRepairContent() {
		return repairContent;
	}

	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}

	public String getApporgOpinion() {
		return apporgOpinion;
	}

	public void setApporgOpinion(String apporgOpinion) {
		this.apporgOpinion = apporgOpinion;
	}

	public String getZongheOpinion() {
		return zongheOpinion;
	}

	public void setZongheOpinion(String zongheOpinion) {
		this.zongheOpinion = zongheOpinion;
	}

	public String getCountyOpinion() {
		return countyOpinion;
	}

	public void setCountyOpinion(String countyOpinion) {
		this.countyOpinion = countyOpinion;
	}

	public String getGuikouorgOpinion() {
		return guikouorgOpinion;
	}

	public void setGuikouorgOpinion(String guikouorgOpinion) {
		this.guikouorgOpinion = guikouorgOpinion;
	}

	public String getSanxiangOpinion() {
		return sanxiangOpinion;
	}

	public void setSanxiangOpinion(String sanxiangOpinion) {
		this.sanxiangOpinion = sanxiangOpinion;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	
	
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public double getTrueFree() {
		return trueFree;
	}

	public void setTrueFree(double trueFree) {
		this.trueFree = trueFree;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
    
    
    
}
