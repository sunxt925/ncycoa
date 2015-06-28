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
@Table(name="SAFE_FIREEQUIPMENT")
public class FireEquipment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="NAME")
	private String name;   //设备名称
	
	@Column(name="SERIAL_NUM")
	private String serialNum;   //编号
	
	@Column(name="AMOUNT")
	private String amount;   //数量
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SET_TIME")
	private Date setTime;  // 配置时间
	
	@Column(name="LOCATION")
	private String location;   //位置
	
	@Column(name="CHECKER")
	private String checker;  	 // 责任人
	
	@Column(name="USE_DEPART")
	private String useDepart;  // 使用部门
	
	@Column(name="KEY_CONTENT")
	private String validTime;     //有效期
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="NEXT_CHECKDATE")
	private Date nextCheckDate;     // 下次检修日期
	
	@Column(name="MEMO")
	private String memo;   // 备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getSetTime() {
		return setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getUseDepart() {
		return useDepart;
	}

	public void setUseDepart(String useDepart) {
		this.useDepart = useDepart;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public Date getNextCheckDate() {
		return nextCheckDate;
	}

	public void setNextCheckDate(Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
