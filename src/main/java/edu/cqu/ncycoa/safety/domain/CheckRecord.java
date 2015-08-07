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
@Table(name="SAFE_CHECKRECORD")
public class CheckRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   //��ˮ��
	
	@Column(name="PLACE")
	private String place;   //���ص�
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CHECK_TIME")
	private Date checkTime;  // ���ʱ��
	
	@Column(name="HOST")
	private String host;  	 // ������
	
	@Column(name="PARTICIPANTS")
	private String participants;  // �μ���Ա
	
	@Column(name="CHECK_CONTENT")
	private String checkContent;     //�������
	
	@Column(name="CHECK_RESULT")
	private String checkResult;     // �����
	
	@Column(name="CHANGE_REQUIRE")
	private String changeRequire;     // ����Ҫ��
	
	@Column(name="RESULT_FILEPATH")
	private String filePath; //�ļ�·������
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getChangeRequire() {
		return changeRequire;
	}

	public void setChangeRequire(String changeRequire) {
		this.changeRequire = changeRequire;
	}

	
}
