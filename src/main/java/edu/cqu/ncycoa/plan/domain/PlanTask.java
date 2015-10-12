package edu.cqu.ncycoa.plan.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column(name="STATUS")
	private Short status = (short)0;      // 0等待处理、1处理完毕
	
	@Column(name="RESULT")
	private String result;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HANDLE_TIME")
	private Date handleDate;
	
	@Column(name="DESCRIPTION")
	@Lob
	private String description;
	
	@OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Asset> uploadedFiles;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Asset> getUploadedFiles() {
		return uploadedFiles;
	}

	public void setUploadedFiles(List<Asset> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handleDate == null) ? 0 : handleDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((participantCode == null) ? 0 : participantCode.hashCode());
		result = prime * result + ((participantName == null) ? 0 : participantName.hashCode());
		result = prime * result + ((planInstance == null) ? 0 : planInstance.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((step == null) ? 0 : step.hashCode());
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
		PlanTask other = (PlanTask) obj;
		if (handleDate == null) {
			if (other.handleDate != null)
				return false;
		} else if (!handleDate.equals(other.handleDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (participantCode == null) {
			if (other.participantCode != null)
				return false;
		} else if (!participantCode.equals(other.participantCode))
			return false;
		if (participantName == null) {
			if (other.participantName != null)
				return false;
		} else if (!participantName.equals(other.participantName))
			return false;
		if (planInstance == null) {
			if (other.planInstance != null)
				return false;
		} else if (!planInstance.equals(other.planInstance))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (step == null) {
			if (other.step != null)
				return false;
		} else if (!step.equals(other.step))
			return false;
		return true;
	}
	
}
