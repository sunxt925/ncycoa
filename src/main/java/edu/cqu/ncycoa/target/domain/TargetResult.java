package edu.cqu.ncycoa.target.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBM_ObjRESULT")
public class TargetResult {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RESULT_ID")
	private String ID = "";
	
	@Column(name="INDEX_CODE")
	private String IndexCode = "";	
	@Column(name="ARCH_CODE")
	private String ArchCode = "";
	@Column(name="OBJ_CODE")  //对象编码
	private String objectCode = "";
	@Column(name="PLAN_VALUE")  //计划值
	private String planValue = "";
	@Column(name="REAL_VALUE")  //实际值
	private String realValue = "";
	
	@Column(name="SCORE")  //实际值
	private String score = "";
	
	@Column(name="YEAR")  //年份
	private String year = "";
	@Column(name="SEASON")  //季度
	private String season = "";
	
	//备注
	@Column(name="MEMO")
	private String Memo = "";

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getIndexCode() {
		return IndexCode;
	}

	public void setIndexCode(String indexCode) {
		IndexCode = indexCode;
	}

	public String getArchCode() {
		return ArchCode;
	}

	public void setArchCode(String archCode) {
		ArchCode = archCode;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getPlanValue() {
		return planValue;
	}

	public void setPlanValue(String planValue) {
		this.planValue = planValue;
	}

	public String getRealValue() {
		return realValue;
	}

	public void setRealValue(String realValue) {
		this.realValue = realValue;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
}
