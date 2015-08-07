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
	
	@Column(name="PLAN_NUM")
	private String planNum;   //计划编号
	
	@Column(name="TYPE")
	private Short type;   //检查类别  综合检查_0,专项检查_1,节假日检查_2,季节性检查_3
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CHECK_TIME")
	private Date checkTime;  // 检查时间
	
	@Column(name="CHECKER")
	private String checker;  	 // 检查人
	
	@Column(name="CHECKED_DEPART")
	private String checkedDepart;  // 受检单位/部门   可选择
	
	@Column(name="KEY_CONTENT")
	private String keyContent;     //检查重点内容
	
	@Column(name="CHECK_METHOD")
	private String checkMethod;     // 检查方法
	
	@Column(name="MEMO")
	private String memo;   // 备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
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

	public String getPlanNum() {
		return planNum;
	}

	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}
	 
	
}
