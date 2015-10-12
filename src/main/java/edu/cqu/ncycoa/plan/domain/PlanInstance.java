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
	public final static Short INTERCEPTED = 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PLAN_INSTANCE_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "PLAN_ID")
	private Plan plan;
	
	@ManyToOne
	@JoinColumn(name = "PLAN_STEP_ID")
	private PlanStep currentStep;
	
	@Column(name="EXECUTOR")
	private String executor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXEC_DATE")
	private Date execDate;
	
	@Column(name="STATUS")
	private Short status = (short)0;

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
