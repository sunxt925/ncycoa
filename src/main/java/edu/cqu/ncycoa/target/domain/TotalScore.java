package edu.cqu.ncycoa.target.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBM_OBJTOTALSCORE")
public class TotalScore {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RESULT_ID")
	private Long ID;
	
	@Column(name="ARCH_CODE")
	private String ArchCode = "";
	@Column(name="OBJ_CODE")  //对象编码
	private String objectCode = "";
	
	@Column(name="TOTALSCORE")  //实际值
	private Long totalScore;
	
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
	public Long getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
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
