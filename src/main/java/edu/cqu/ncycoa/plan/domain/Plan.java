package edu.cqu.ncycoa.plan.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="NCYCOA_PLAN")
public class Plan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PLAN_ID")
	private Long id;   		 	 // 计划编号
	
	@Column(name="PLAN_NAME")
	private String name;  	 	 // 计划名称
	
	@Column(name="DESCRIPTION")
	private String description;  // 计划描述
	
	@ElementCollection
	@CollectionTable(name="NCYCOA_PLAN_PART", joinColumns=@JoinColumn(name="PLAN_ID"))
	@MapKeyColumn(name="PARTICIPANT_CODE")
	@Column(name="PARTICIPANT_NAME")
	private Map<String, String> participants; // 参与人
	
	@Column(name="STATUS")
	private Short status;      // 已创建待审核、已审核待完成、已完成
	
	@Column(name="DEPART")
	private String departId;   // 计划所属部门
	
	@Column(name="TYPE")
	private Short type;        // 岗位计划，部门计划
	
	@Column(name="STEP_TYPE")
	private Short stepType;        // 自定义流程，固定流程
	
	@Column(name="FIX_Flow_Key")
	private String fixFlowKey;
	
	public String getFixFlowKey() {
		return fixFlowKey;
	}

	public void setFixFlowKey(String fixFlowKey) {
		this.fixFlowKey = fixFlowKey;
	}

	@OneToMany(mappedBy = "plan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("order")
	private List<PlanStep> steps;
	
	@Column(name="INPUT_USER")
	private String inputUser;  // 录入人
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INPUT_DATE")
	private Date inputDate;    // 录入时间
	
	@Column(name="AUTITOR")
	private String auditor;    // 审计人
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AUDIT_DATE")
	private Date auditDate;    // 审计时间

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Short getStepType() {
		return stepType;
	}

	public void setStepType(Short stepType) {
		this.stepType = stepType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getParticipants() {
		return participants;
	}

	public void setParticipants(Map<String, String> participants) {
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

	public List<PlanStep> getSteps() {
		return steps;
	}

	public void setSteps(List<PlanStep> steps) {
		this.steps = steps;
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
