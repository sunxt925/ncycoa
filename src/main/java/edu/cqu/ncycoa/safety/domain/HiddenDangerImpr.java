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
@Table(name="SAFE_DANGERIMPROVEMENT")
public class HiddenDangerImpr {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="NUMBER")
	private String number;   //���
	
	@Column(name="PLACE")
	private String place;   //�ص�
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE")
	private Date date;  // ����
	
	@Column(name="DANGER_CONTENT")
	private String dangerContent;  	 // ��������
	
	@Column(name="DANGER_TYPE")
	private String dangerType;  // ��������
	
	@Column(name="DANGER_LEVEL")
	private Short dangerLevel;     //�����ּ� 0һ�� 1�ص�
	
	@Column(name="IMPROVE_STATUS")
	private String improveStatus;     // ���Ĵ�ʩ��������
	
	@Column(name="IMPROVE_DEPART")
	private String improveDepart;   // �������ε�λ/����
	
	@Column(name="IMPROVE_CHECKER")
	private String improveChecker;   // ������֤��
	
	@Column(name="MEMO")
	private String memo;   // ��ע

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDangerContent() {
		return dangerContent;
	}

	public void setDangerContent(String dangerContent) {
		this.dangerContent = dangerContent;
	}

	public String getDangerType() {
		return dangerType;
	}

	public void setDangerType(String dangerType) {
		this.dangerType = dangerType;
	}

	public Short getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(Short dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public String getImproveStatus() {
		return improveStatus;
	}

	public void setImproveStatus(String improveStatus) {
		this.improveStatus = improveStatus;
	}

	public String getImproveDepart() {
		return improveDepart;
	}

	public void setImproveDepart(String improveDepart) {
		this.improveDepart = improveDepart;
	}

	public String getImproveChecker() {
		return improveChecker;
	}

	public void setImproveChecker(String improveChecker) {
		this.improveChecker = improveChecker;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
