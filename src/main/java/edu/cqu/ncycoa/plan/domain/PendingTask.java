package edu.cqu.ncycoa.plan.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="NCYCOA_PENDING_TASK")
public class PendingTask {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PENDING_TASK_ID")
	private Long id;   		 	// 步骤id

	@Column(name="CONTENT")
	private String content;   // 步骤描述
	
	@Column(name="FORM_KEY")
	private String formKey;

	@OneToOne
	@JoinColumn(name = "PLAN_STEP_ID")
	private PlanStep plan;
	
	@Column(name="PARTICIPANT")
	private String participant; // 参与人
	
	@Column(name="PARTICIPANT_VALUE")
	private String participantValue;
	
	@Column(name="STATUS")
	private Short status = (short)0;      // 0等待处理、1处理完毕
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GEN_TIME")
	private Date genDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HANDLE_TIME")
	private Date handleDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public PlanStep getPlan() {
		return plan;
	}

	public void setPlan(PlanStep plan) {
		this.plan = plan;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getParticipantValue() {
		return participantValue;
	}

	public void setParticipantValue(String participantValue) {
		this.participantValue = participantValue;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
	public Date getGenDate() {
		return genDate;
	}

	public void setGenDate(Date genDate) {
		this.genDate = genDate;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

}
