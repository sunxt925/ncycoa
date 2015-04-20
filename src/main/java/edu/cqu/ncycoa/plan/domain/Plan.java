package edu.cqu.ncycoa.plan.domain;

import java.util.Date;
import java.util.List;

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
	private Long id;   		 	// �ƻ����
	
	@Column(name="PLAN_NAME")
	private String name;  	 	// �ƻ�����
	
	@Column(name="DESCRIPTION")
	private String description;   // �ƻ�����
	
	@ElementCollection
	@CollectionTable(name="NCYCOA_PLAN_PART", joinColumns=@JoinColumn(name="PLAN_ID"))
	private List<String> participants; // ������
	
	@Column(name="STATUS")
	private Short status;      // �Ѵ�������ˡ�����˴���ɡ������
	
	@Column(name="DEPART")
	private String departId;   // �ƻ���������
	
	@Column(name="TYPE")
	private Short type;        // ��λ�ƻ������żƻ�
	
	@OneToMany(mappedBy = "plan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("order")
	private List<PlanStep> steps;
	
	@Column(name="INPUT_USER")
	private String inputUser;  // ¼����
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INPUT_DATE")
	private Date inputDate;    // ¼��ʱ��
	
	@Column(name="AUTITOR")
	private String auditor;    // �����
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AUDIT_DATE")
	private Date auditDate;    // ���ʱ��

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
