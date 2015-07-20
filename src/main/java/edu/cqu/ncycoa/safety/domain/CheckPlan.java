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
@Table(name="SAFE_CHECKPLAN")
public class CheckPlan {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="TYPE")
	private String type;   //������
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CHECK_TIME")
	private Date checkTime;  // ���ʱ��
	
	@Column(name="CHECKER")
	private String checker;  	 // �����
	
	@Column(name="CHECKED_DEPART")
	private String checkedDepart;  // �ܼ쵥λ/����
	
	@Column(name="KEY_CONTENT")
	private String keyContent;     //����ص�����
	
	@Column(name="CHECK_METHOD")
	private String checkMethod;     // ��鷽��
	
	@Column(name="MEMO")
	private String memo;   // ��ע

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getCheckedDepart() {
		return checkedDepart;
	}

	public void setCheckedDepart(String checkedDepart) {
		this.checkedDepart = checkedDepart;
	}

	public String getKeyContent() {
		return keyContent;
	}

	public void setKeyContent(String keyContent) {
		this.keyContent = keyContent;
	}

	public String getCheckMethod() {
		return checkMethod;
	}

	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	 
}
