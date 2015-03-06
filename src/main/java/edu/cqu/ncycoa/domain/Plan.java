package edu.cqu.ncycoa.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="NCYCOA_PLAN")
public class Plan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PLAN_ID")
	private Long id;   // 计划编号
	
	@Column(name="PLAN_NAME")
	private String name;  	 // 计划名称
	
	@Column(name="CONTENT")
	private String content;  // 计划内容
	
	@Column(name="MEMO")
	private String memo;     // 备注
	
	@ElementCollection
	@CollectionTable(name="NCYCOA_PLAN_PART", joinColumns=@JoinColumn(name="PLAN_ID"))
	private List<String> participants; // 参与人
	
	@Column(name="STATUS")
	private Short status;     // 已创建待审核、已审核待完成、已完成
	
	@Column(name="TYPE")
	private Short type;        // 岗位计划，部门计划
	
	@Column(name="INPUT_USER")
	private String inputUser;  // 录入人
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INPUT_DATE")
	private Date inputDate;    // 录入时间
	
	@Column(name="AUTITOR")
	private String auditor;    // 审计人
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AUDIT_DATE")
	private Date auditDate;  // 审计时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getInputUser() {
		return inputUser;
	}

	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}
