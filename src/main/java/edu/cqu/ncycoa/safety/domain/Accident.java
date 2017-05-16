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
@Table(name="SAFE_ACCIDENT")
public class Accident {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCIDENT_ID")
	private Long id;//编号
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACCIDENT_TIME")
	private Date time;  // 时间
	
	@Column(name="ACCIDENT_place")
	private String place;//地点
	
	@Column(name="ACCIDENT_DETIAL")
	private String detial;//事故概述
	
	@Column(name="ACCIDENT_ANALYZE")
	private String analyze;//事故分析
	
	@Column(name="ACCIDENT_PUNISH")
	private String punish;//事故责任
	
	@Column(name="ACCIDENT_CUREINFO")
	private String cureInfo;//伤亡人员救治信息
	
	@Column(name="ACCIDENT_MEMO")
	private String memo;//备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetial() {
		return detial;
	}

	public void setDetial(String detial) {
		this.detial = detial;
	}

	public String getAnalyze() {
		return analyze;
	}

	public void setAnalyze(String analyze) {
		this.analyze = analyze;
	}



	public String getPunish() {
		return punish;
	}

	public void setPunish(String punish) {
		this.punish = punish;
	}

	public String getCureInfo() {
		return cureInfo;
	}

	public void setCureInfo(String cureInfo) {
		this.cureInfo = cureInfo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	
}
