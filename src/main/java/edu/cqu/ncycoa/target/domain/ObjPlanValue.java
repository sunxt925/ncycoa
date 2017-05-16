package edu.cqu.ncycoa.target.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBM_OBJPLANVALUE")
public class ObjPlanValue {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PV_ID")
	private Long ID;
	
	@Column(name="INDEX_CODE")
	private String IndexCode = "";	
	@Column(name="ARCH_CODE")
	private String ArchCode = "";
	@Column(name="OBJ_CODE")  //对象编码
	private String objectCode = "";
	@Column(name="PLAN_VALUE")  //计划值
	private String planValue = "";
	
	@Column(name="YEAR")  //年份
	private String year = "";
	@Column(name="SEASON")  //季度
	private String season = "";
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
	
}
