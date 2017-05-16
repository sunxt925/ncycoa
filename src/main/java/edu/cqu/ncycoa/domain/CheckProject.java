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
@Table(name="STD_CHECKPROJECT")
public class CheckProject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CHECKID")
	private Long id;   
	
	@Column(name="CHECKNAME")
	private String checkName; 
	
	@Column(name="STAFFCODE")
	private String staffCode;  
	
	@Column(name="STAFFNAME")
	private String staffName;  	
	
	@Column(name="FILEPATH")
	private String filePath;  	
	
	@Column(name="CHECKCODE")
	private String checkCode;  
	
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkcode) {
		this.checkCode = checkcode;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name="STATUS")
	private String status;  	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="STARTTIME")
	private Date startTime;

	
}
