package edu.cqu.ncycoa.plan.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String content;   // 描述
	
	@Column(name="FORM_KEY")
	private String formKey;
	
	@Column(name = "CEIlLING_ENTITY_ID")
	private String ceilingEntityId;
	
	@Column(name="PARTICIPANT")
	private String participant; 		 // 参与人
	
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

	public String getCeilingEntityId() {
		return ceilingEntityId;
	}

	public void setCeilingEntityId(String ceilingEntityId) {
		this.ceilingEntityId = ceilingEntityId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ceilingEntityId == null) ? 0 : ceilingEntityId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((formKey == null) ? 0 : formKey.hashCode());
		result = prime * result + ((genDate == null) ? 0 : genDate.hashCode());
		result = prime * result + ((handleDate == null) ? 0 : handleDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
		result = prime * result + ((participantValue == null) ? 0 : participantValue.hashCode());
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
		PendingTask other = (PendingTask) obj;
		if (ceilingEntityId == null) {
			if (other.ceilingEntityId != null)
				return false;
		} else if (!ceilingEntityId.equals(other.ceilingEntityId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (formKey == null) {
			if (other.formKey != null)
				return false;
		} else if (!formKey.equals(other.formKey))
			return false;
		if (genDate == null) {
			if (other.genDate != null)
				return false;
		} else if (!genDate.equals(other.genDate))
			return false;
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
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		if (participantValue == null) {
			if (other.participantValue != null)
				return false;
		} else if (!participantValue.equals(other.participantValue))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
}
