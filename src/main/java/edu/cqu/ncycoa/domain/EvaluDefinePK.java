package edu.cqu.ncycoa.domain;

import java.io.Serializable;

public class EvaluDefinePK implements Serializable{
	private String evaluYear;
	private String indexCode;
	public String getEvaluYear() {
		return evaluYear;
	}
	public void setEvaluYear(String evaluYear) {
		this.evaluYear = evaluYear;
	}
	public String getEvaluCode() {
		return indexCode;
	}
	public void setEvaluCode(String evaluCode) {
		this.indexCode = evaluCode;
	}

	
}
