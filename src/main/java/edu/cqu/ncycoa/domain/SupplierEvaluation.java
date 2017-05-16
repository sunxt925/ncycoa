package edu.cqu.ncycoa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="NCYCOA_SUPPLIER_EVALU")
@IdClass(SupplierEvaluationPK.class)
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SupplierEvaluation {
	//表中有 部门+供货商 为主键
	//其他字段还有：评分
	@Id
	@Column(name="EVALU_DEPART")
	private String evaluDepart;
	
	@Id
	@Column(name="EVALU_SUPPLIER")
	private String evaluSupplier;
	
	@Column(name="EVALU_SCORE")
	private int score; //得分
	
	@Column(name="EVALU_LEVEL")
	private Short level; //评价等级 0优 1良 2一般 3不及格
	
	@Column(name="EVALU_CONTENT")
	private String content; //评价内容
	

    //附件
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EVALU_TIME")
	private Date evaluTime; //评价时间

	public String getEvaluDepart() {
		return evaluDepart;
	}

	public void setEvaluDepart(String evaluDepart) {
		this.evaluDepart = evaluDepart;
	}

	public String getEvaluSupplier() {
		return evaluSupplier;
	}

	public void setEvaluSupplier(String evaluSupplier) {
		this.evaluSupplier = evaluSupplier;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getEvaluTime() {
		return evaluTime;
	}

	public void setEvaluTime(Date evaluTime) {
		this.evaluTime = evaluTime;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
