package edu.cqu.ncycoa.safety.domain;

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
@Table(name="SAFE_RELEVANTPARTY_JD")
public class RelevantPartyJD {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="PARTY_NAME")
	private String partyName;   //相关方名称
	
	@Column(name="PARTY_CONTENT")
	private String partyContent;   //相关方从事业务
	
	@Column(name="MANAGER")
	private String manager;  	 //归口管理部门交底人
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="JD_TIME")
	private Date jdTime;  // 交底时间
	
	@Column(name="CONTENT")
	private String content;  	 //交底内容

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyContent() {
		return partyContent;
	}

	public void setPartyContent(String partyContent) {
		this.partyContent = partyContent;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Date getJdTime() {
		return jdTime;
	}

	public void setJdTime(Date jdTime) {
		this.jdTime = jdTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
