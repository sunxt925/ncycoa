package edu.cqu.ncycoa.domain;

import java.io.Serializable;

public class SupplierEvaluationPK implements Serializable{
	//可能必须要无参构造函数+hashCode和equals方法。
	private String evaluDepart;
	private String evaluSupplier;
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
}
