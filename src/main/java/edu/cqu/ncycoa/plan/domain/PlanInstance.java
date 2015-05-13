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
	
}
