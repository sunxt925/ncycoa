package edu.cqu.ncycoa.plan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NCYCOA_DPT_REVIEW")
public class DptReview {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DPT_REVIEW_ID")
	private Long id;
	
	@Column
	private Short year;
	
	@Column
	private Short month;
	
	@Column(name="NAME")
	private String orgName; 
	
	@Column(name="CODE")
	private String orgCode;
	
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


	public String getStatistics() {
		return statistics;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
