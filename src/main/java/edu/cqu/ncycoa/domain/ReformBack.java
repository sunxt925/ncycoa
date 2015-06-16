package edu.cqu.ncycoa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="NCYCOA_REFORMBACK")
public class ReformBack {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFORMBACK_ID")
	private Long id;
	
	@Column(name="REFORMBACK_NAME")
	private String backname;
	
	@Column(name="REFORMBACK_REFROMID")
	private Long reformId;
	
	@Column(name="REFORMBACK_FILENAME")
	private String fileName;
	
	@Column(name="REFORMBACK_MEMO")
	private String memo;
	
	@Column(name="REFORMBACK_SUBDATE")
	private Date subDate;
	
	@Column(name="REFORMBACK_SUBUSER")
	private String subUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBackname() {
		return backname;
	}
	public void setBackname(String backname) {
		this.backname = backname;
	}
	public Long getReformId() {
		return reformId;
	}
	public void setReformId(Long reformId) {
		this.reformId = reformId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getSubDate() {
		return subDate;
	}
	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}
	public String getSubUser() {
		return subUser;
	}
	public void setSubUser(String subUser) {
		this.subUser = subUser;
	}
	
	
}
