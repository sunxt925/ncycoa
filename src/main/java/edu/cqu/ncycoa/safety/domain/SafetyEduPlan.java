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
@Table(name="SAFE_SAFETYEDU_PLAN")
public class SafetyEduPlan {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SAFETYEDU_ID")
	private Long id;//��ˮ��
	
	@Column(name="CLASS_NAME")
	private String className;//�γ�����
	
	@Column(name="STUDENT")
	private String student;//��ѵ����
	
	@Column(name="STUDENTPOS")
	private String studentPos;//��ѵ�߸�λ
	
	@Column(name="SAFETYEDU_TIME")
	private Date trainTime;//��ѵʱ��
	
	@Column(name="SAFETYEDU_PLACE")
	private String place;//�ص�
	
	@Column(name="PEOPLE_NUM")
	private String peopleNum;//����
	
	@Column(name="STUDY_TIME")
	private String studyTime;//ѧʱ ��
	
	@Column(name="MANAGE_DEPART")
	private String manageDepart;//�а쵥λ ����

	@Column(name="MONEY_SOURCE")
	private String moneySource;//�ʽ���Դ	
	
	@Column(name="MEMO")
	private String memo;//��ע

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getStudentPos() {
		return studentPos;
	}

	public void setStudentPos(String studentPos) {
		this.studentPos = studentPos;
	}

	public Date getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(Date trainTime) {
		this.trainTime = trainTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}

	public String getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public String getManageDepart() {
		return manageDepart;
	}

	public void setManageDepart(String manageDepart) {
		this.manageDepart = manageDepart;
	}

	public String getMoneySource() {
		return moneySource;
	}

	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
