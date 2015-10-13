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
	private String projectName; //��Ŀ����
	
	@Column(name="REPAIRAUDIT_APPORGCODE")
	private String apporgCode; //���루��λ������
	
	@Column(name="REPAIRAUDIT_REPAIRFREE")
    private float repairFree; //ά����ĿԤ��
	
	@Column(name="REPAIRAUDIT_APPDATE")
    private Date appDate; //��������
	
	@Column(name="REPAIRAUDIT_REPAIRCONTENT")
    private String repairContent; //ά����Ҫ����
    
	@Column(name="REPAIRAUDIT_APPORGOPINION")
    private String apporgOpinion; //���벿�����
	
	@Column(name="REPAIRAUDIT_ZONGHEOPINION")
    private String zongheOpinion; //�ۺϰ����
	
	@Column(name="REPAIRAUDIT_COUNTYOPINION")
    private String countyOpinion; //�ؾ־ֳ����������
	
	@Column(name="REPAIRAUDIT_GUIKOUORGOPINION")
    private String guikouorgOpinion; //��ڹ��������
	
	@Column(name="REPAIRAUDIT_SANXIANGOPINION")
    private String sanxiangOpinion; //���������ίԱ���������

	@Column(name="REPAIRAUDIT_AUDITFLAG")
	private String auditFlag;
	
	@Column(name="REPAIRAUDIT_AUDITDATE")
    private Date auditDate; //��������
	
	
	@Column(name="REPAIRAUDIT_PROJECTTYPE")
	private String projectType;
	
	@Column(name="REPAIRAUDIT_SERVICEPROVIDOR")
	private String serviceProvider;//ά����
	
	@Column(name="REPAIRAUDIT_HANDLEPERSON")
	private String handlePerson;//������

	@Column(name="REPAIRAUDIT_TRUEFREE")
	private double trueFree;//ʵ�ʽ��
	
	@Column(name="REPAIRAUDIT_HANDLEDATE")
	private Date handleDate;//����ʱ��
	
	@Column(name="REPAIRAUDIT_ENDTIME")
	private Date endTime;//���ʱ��
	
	@Column(name="REPAIRAUDIT_ACCEPTOR")
	private String acceptor;//������
	
	@Column(name="PROCESS_INS_ID")
	private String processInstanceId;//����ʵ��ID
	
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
