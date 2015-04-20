package edu.cqu.ncycoa.plan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="NCYCOA_PLAN_STEP")
public class PlanStep {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PLAN_STEP_ID")
	private Long id;   		 	// 步骤id
	
	@Column(name="PLAN_STEP_ORDER")
	private Long order;
	
	@Column(name="CONTENT")
	private String content;   // 步骤描述
	
	@ManyToOne
	@JoinColumn(name = "PLAN_ID")
	private Plan plan;
	
	@Column(name="PARTICIPANT")
	private String participant; // 参与人
	
	@Column(name="PARTICIPANT_VALUE")
	private String participantValue;
	
	@Column(name="STATUS")
	private Short status = (short)0;      // 0等待执行、1正在执行、2执行完成
	
	@Column(name="TYPE_VALUE")
	private Short typeValue;        // 步骤类型
	
	@Column(name="TYPE")
	private String type;

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

	public Short getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Short typeValue) {
		this.typeValue = typeValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
