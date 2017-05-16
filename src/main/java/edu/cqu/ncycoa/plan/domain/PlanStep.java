package edu.cqu.ncycoa.plan.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="NCYCOA_PLAN_STEP")
public class PlanStep implements Comparable<PlanStep>{
	
	@Transient
	public final static Short READY = 0;
	
	@Transient
	public final static Short EXECUTING = 1;
	
	@Transient
	public final static Short FINISHED = 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PLAN_STEP_ID")
	private Long id;   		 	// 步骤id
	
	@Column(name="PLAN_STEP_ORDER")
	private Long order;
	
	@Column(name="CONTENT")
	@Lob
	private String content;   // 步骤描述
	
	@Column(name="SUMMARY")
	private String summary;
	
	@Temporal(TemporalType.DATE)
	private Date ending;
	
	public Date getEnding() {
		return ending;
	}

	public void setEnding(Date ending) {
		this.ending = ending;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(Integer timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	@Column(name="TIME_CONSUMING")
	private Integer timeConsuming;
	
	@ManyToOne
	@JoinColumn(name = "PLAN_ID")
	private Plan plan;
	
	@ElementCollection
	@CollectionTable(name="NCYCOA_PLAN_STEP_PART", joinColumns=@JoinColumn(name="PLAN_STEP_ID"))
	@MapKeyColumn(name="PARTICIPANT_CODE")
	@Column(name="PARTICIPANT_NAME")
	private Map<String, String> participants; // 参与人

	@Column(name="STATUS")
	private Short status = (short)0;      // 0等待执行、1正在执行、2执行完成

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
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

	@Override
	public int compareTo(PlanStep o) {
		return getOrder().compareTo(o.getOrder());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((participants == null) ? 0 : participants.hashCode());
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((timeConsuming == null) ? 0 : timeConsuming.hashCode());
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
		PlanStep other = (PlanStep) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (participants == null) {
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
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
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (timeConsuming == null) {
			if (other.timeConsuming != null)
				return false;
		} else if (!timeConsuming.equals(other.timeConsuming))
			return false;
		return true;
	}

}
