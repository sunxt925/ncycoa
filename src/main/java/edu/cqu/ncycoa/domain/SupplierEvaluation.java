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
	//������ ����+������ Ϊ����
	//�����ֶλ��У�����
	@Id
	@Column(name="EVALU_DEPART")
	private String evaluDepart;
	
	@Id
	@Column(name="EVALU_SUPPLIER")
	private String evaluSupplier;
	
	@Column(name="EVALU_SCORE")
	private int score; //�÷�
	
	@Column(name="EVALU_LEVEL")
	private Short level; //���۵ȼ� 0�� 1�� 2һ�� 3������
	
	@Column(name="EVALU_CONTENT")
	private String content; //��������
	

    //����
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EVALU_TIME")
	private Date evaluTime; //����ʱ��

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
