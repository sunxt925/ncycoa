package edu.cqu.ncycoa.plan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NCYCOA_USER_REVIEW")
public class UserReview {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_REVIEW_ID")
	private Long id;
	
	@Column
	private Short year;
	
	@Column
	private Short month;
	
	@Column(name="PARTICIPANT_NAME")
	private String participantName; 
	
	@Column(name="PARTICIPANT_CODE")
	private String participantCode;
	
	@Column(name="DEPART_ID")
	private String departId;
	
	@Column(name="RESULT")
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	@Column
	private String statistics;
	
	@Column
	private Integer overDeadTimeCounts;
	
	@Column
	private Integer noOverDeadTimeCounts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public Short getMonth() {
		return month;
	}

	public void setMonth(Short month) {
		this.month = month;
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

	public String getStatistics() {
		return statistics;
	}

	public void setStatistics(String statistics) {
		this.statistics = statistics;
	}

	public Integer getOverDeadTimeCounts() {
		return overDeadTimeCounts;
	}

	public void setOverDeadTimeCounts(Integer overDeadTimeCounts) {
		this.overDeadTimeCounts = overDeadTimeCounts;
	}

	public Integer getNoOverDeadTimeCounts() {
		return noOverDeadTimeCounts;
	}

	public void setNoOverDeadTimeCounts(Integer noOverDeadTimeCounts) {
		this.noOverDeadTimeCounts = noOverDeadTimeCounts;
	}
	
}
