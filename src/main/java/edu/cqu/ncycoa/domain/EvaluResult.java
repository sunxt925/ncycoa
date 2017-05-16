package edu.cqu.ncycoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NCYCOA_EVALU_RESULT")
public class EvaluResult {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EVALU_ID")
	private Long evaluID;
	
	@Column(name="EVALU_YEAR")
	private String evaluYear;
	
	@Column(name="EVALU_SUPPLIER")
	private String evaluSupplier;//被评供应商
	
	@Column(name="EVALU_DEPART")
	private String evaluDepart;//评价部门
	
	//是否针对每个题目的得分情况进行记录，建立指标字段与定义表相关联
	
	@Column(name="EVALU_SCORE")
	private int score; //得分
	
	@Column(name="EVALU_LEVEL")
	private Short level; //评价等级 0优 1良 2一般 3不及格
	
	@Column(name="EVALU_DETAIL")
	private String detail;//详细分值 格式：5,10,5...

	public Long getEvaluID() {
		return evaluID;
	}

	public void setEvaluID(Long evaluID) {
		this.evaluID = evaluID;
	}

	public String getEvaluYear() {
		return evaluYear;
	}

	public void setEvaluYear(String evaluYear) {
		this.evaluYear = evaluYear;
	}

	public String getEvaluSupplier() {
		return evaluSupplier;
	}

	public void setEvaluSupplier(String evaluSupplier) {
		this.evaluSupplier = evaluSupplier;
	}

	public String getEvaluDepart() {
		return evaluDepart;
	}

	public void setEvaluDepart(String evaluDepart) {
		this.evaluDepart = evaluDepart;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	
}
