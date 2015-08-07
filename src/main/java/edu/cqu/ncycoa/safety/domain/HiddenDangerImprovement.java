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
@Table(name="SAFE_HIDDENDANGERIMPR")
public class HiddenDangerImprovement { //�¹���������
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   //��ˮ�� 
	
	@Column(name="DPLACE")
	private String place;   //������λ
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DANGER_DATE")
	private Date date;  // ����ʱ��
	
	@Column(name="DANGER_CONTENT")
	private String dangerContent;  	 // ��������
	
	@Column(name="DANGER_TYPE")
	private String dangerType;  // ��������
	
	@Column(name="IMPROVE_METHOD")
	private String improveMethod;     // ���Ĵ�ʩ
	
	@Column(name="IMPROVE_STATUS")
	private String improveStatus;     // ������
	
	@Column(name="IMPROVE_DEPART")
	private String improveDepart;   // �������ε�λ/����
	
	@Column(name="IMPROVE_CHECKER")
	private String improveChecker;   // ������֤��
	
	@Column(name="MEMO")
	private String memo;   // ��ע
	
	@Column(name="FILEPATH")
	private String filePath; //�ļ�·������  ������֤����

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

	public String getImproveMethod() {
		return improveMethod;
	}

	public void setImproveMethod(String improveMethod) {
		this.improveMethod = improveMethod;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
