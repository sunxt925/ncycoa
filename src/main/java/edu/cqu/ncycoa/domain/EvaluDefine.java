package edu.cqu.ncycoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="NCYCOA_EVALU_DEFINE")
@IdClass(EvaluDefinePK.class)
public class EvaluDefine {
	@Id
	@Column(name="EVALU_YEAR")
	private String evaluYear;
	
	@Id
	@Column(name="INDEX_CODE")
	private String indexCode;
	
	@Column(name="INDEX_PARENTCODE")
	private String indexParentCode; //父指标编码
	
	@Column(name="INDEX_NAME")
	private String indexName; //指标名（题目）
	
	@Column(name="INSTRUCTION")
	private String instruction; //说明
	
	@Column(name="INDEX_OPTION")
	private String indexOption; //指标选项 （,,）=（,,）

	public String getEvaluYear() {
		return evaluYear;
	}

	public void setEvaluYear(String evaluYear) {
		this.evaluYear = evaluYear;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getIndexOption() {
		return indexOption;
	}

	public void setIndexOption(String indexOption) {
		this.indexOption = indexOption;
	}

	public String getIndexParentCode() {
		return indexParentCode;
	}

	public void setIndexParentCode(String indexParentCode) {
		this.indexParentCode = indexParentCode;
	}
	
	

}
