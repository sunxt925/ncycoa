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

/**
 * 计划运行实例
 * 
 * @author hui
 */
@Entity
@Table(name="NCYCOA_PLAN_INSTANCE")
public class PlanInstance {
	
	@Transient
	public final static Short EXECUTING = 0;
	
	@Transient
	public final static Short FINISHED = 1;
	
	@Transient
	public final static Short REVIEWED = 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PLAN_INSTANCE_ID")
	private Long id;
	
	@Column(name="PLAN_ID")
	private Long planId;
	
	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	@Column(name="PLAN_NAME")
	private String name;  	 	 // 计划名称
	
	@Column(name="SUMMARY")
	private String summary;
	
	@Column(name="PARTICIPANT_ID")
	private String participantIds;
	
	public String getParticipantIds() {
		return participantIds;
	}

	public void setParticipantIds(String participantIds) {
		this.participantIds = participantIds;
	}

	@Column(name="PARTICIPANT_NAME")
	private String participantNames;
	
	@Column(name="REVIEW")
	private String review;
	
	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getParticipantNames() {
		return participantNames;
	}

	public void setParticipantNames(String participants) {
		this.participantNames = participants;
	}

	@Temporal(TemporalType.DATE)
	private Date planBeginDate;
	
	@Temporal(TemporalType.DATE)
	private Date planEndDate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPlanBeginDate() {
		return planBeginDate;
	}

	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	@ManyToOne
	@JoinColumn(name = "PLAN")
	private Plan plan;
	
	@ManyToOne
	@JoinColumn(name = "PLAN_STEP_ID")
	private PlanStep currentStep;
	
	@Column(name="EXECUTOR")
	private String executor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXEC_DATE")
	private Date execDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DATE")
	private Date endingDate;
	
	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	@Column(name="STATUS")
	private Short status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public PlanStep getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(PlanStep currentStep) {
		this.currentStep = currentStep;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public Date getExecDate() {
		return execDate;
	}

	public void setExecDate(Date execDate) {
		this.execDate = execDate;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentStep == null) ? 0 : currentStep.hashCode());
		result = prime * result + ((execDate == null) ? 0 : execDate.hashCode());
		result = prime * result + ((executor == null) ? 0 : executor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanInstance other = (PlanInstance) obj;
		if (currentStep == null) {
			if (other.currentStep != null)
				return false;
		} else if (!currentStep.equals(other.currentStep))
			return false;
		if (execDate == null) {
			if (other.execDate != null)
				return false;
		} else if (!execDate.equals(other.execDate))
			return false;
		if (executor == null) {
			if (other.executor != null)
				return false;
		} else if (!executor.equals(other.executor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
}
