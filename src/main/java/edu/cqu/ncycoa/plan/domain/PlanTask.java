package edu.cqu.ncycoa.plan.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="NCYCOA_PLAN_TASK")
public class PlanTask {
	
	@Transient
	public final static Short READY = 0;
	
	@Transient
	public final static Short EXECUTING = 1;
	
	@Transient
	public final static Short FINISHED = 2;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PLAN_TASK_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "PLAN_INSTANCE_ID")
	private PlanInstance planInstance;
	
	@ManyToOne
	@JoinColumn(name = "PLAN_STEP_ID")
	private PlanStep step;
	
	@Column(name="PARTICIPANT_NAME")
	private String participantName; 
	
	@Column(name="PARTICIPANT_CODE")
	private String participantCode;
	
	@Column(name="TASK_TYPE")
	private Short taskType;
	
	@Column(name="STATUS")
	private Short status = (short)0;      // 0等待处理、1处理完毕
	
	@Column(name="RESULT")
	private String result;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HANDLE_TIME")
	private Date handleDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlanInstance getPlanInstance() {
		return planInstance;
	}

	public void setPlanInstance(PlanInstance planInstance) {
		this.planInstance = planInstance;
	}

	public PlanStep getStep() {
		return step;
	}

	public void setStep(PlanStep step) {
		this.step = step;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getParticipantCode() {
		return participantCode;
	}

	public void setParticipantCode(String participantCode) {
		this.participantCode = participantCode;
	}

	public Short getTaskType() {
		return taskType;
	}

	public void setTaskType(Short taskType) {
		this.taskType = taskType;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}
	
}
