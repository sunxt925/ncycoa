package edu.cqu.ncycoa.safety.domain;

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

@Entity
@Table(name="SAFE_SAFETYEDU")
public class SafetyEdu {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SAFETYEDU_ID")
	private Long id;//���
	
	@Column(name="SAFETYEDU_TIME")
	private Date trainTime;//��ѵʱ��
	
	@Column(name="SAFETYEDU_TEACHER")
	private String teacher;//��ʦ���
	
	
	@ElementCollection
	@CollectionTable(name="SAFE_SAFETYEDU_ATTENDEE", joinColumns=@JoinColumn(name="SAFETYEDU_ID"))
	private List<String> participants; // �μ���Ա
	
	@Column(name="SAFETYEDU_FILEPATH")
	private String filePath;//�ļ�·������
	
	@Column(name="SAFETYEDU_TRAINRES")
	private String trainRes;//���˽��

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(Date trainTime) {
		this.trainTime = trainTime;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public String getTrainRes() {
		return trainRes;
	}

	public void setTrainRes(String trainRes) {
		this.trainRes = trainRes;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
