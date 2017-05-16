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
	private Long ID;
	
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
	
	
	@Column(name="EXAMFLAG")  //1 考核 0 不考核
	private String examFlag = ""; 
	
	@Column(name="ALARMFLAG")  //1 报警 0 不报警
	private String alarmFlag = "";
	
	@Column(name="ISALARM")  //是否报警 1 报警 0 不报警
	private String isAlarm = "";
	
	@Column(name="INDEXNAME")  //
	private String indexName = "";
	
	@Column(name="OBJNAME")  //
	private String objName = "";
	
	//备注
	@Column(name="MEMO")
	private String Memo = "";

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
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

	public String getExamFlag() {
		return examFlag;
	}

	public void setExamFlag(String examFlag) {
		this.examFlag = examFlag;
	}

	public String getAlarmFlag() {
		return alarmFlag;
	}

	public void setAlarmFlag(String alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	public String getIsAlarm() {
		return isAlarm;
	}

	public void setIsAlarm(String isAlarm) {
		this.isAlarm = isAlarm;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}
	
	
}
