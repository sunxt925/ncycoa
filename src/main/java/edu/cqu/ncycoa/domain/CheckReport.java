package edu.cqu.ncycoa.domain;
// 采标实体
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="STD_CHECKREPORT")
public class CheckReport {
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAboutCommit() {
		return aboutCommit;
	}

	public void setAboutCommit(String aboutCommit) {
		this.aboutCommit = aboutCommit;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REPORTID")
	private Long id;   
	
	@Column(name="ABOUTCOMMIT")
	private String aboutCommit; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENDTIME")
	private Date endTime; 

	@Column(name="REPORTPATH")
	private String reportPath; 
}
