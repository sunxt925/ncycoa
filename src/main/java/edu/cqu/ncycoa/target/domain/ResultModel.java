package edu.cqu.ncycoa.target.domain;

import java.util.List;

public class ResultModel {

	private List<TargetResult> result;

	public List<TargetResult> getResult() {
		return result;
	}

	public void setResult(List<TargetResult> result) {
		this.result = result;
	}

	public ResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultModel(List<TargetResult> result) {
		super();
		this.result = result;
	}
	
	
}
