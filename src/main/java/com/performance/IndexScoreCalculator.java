package com.performance;

public abstract class IndexScoreCalculator {
	
	public abstract IndexValue getValue(IndexParameterProvider para);
	
	public abstract IndexScore getScore(IndexValue value);
	
	public IndexScore getScore(IndexParameterProvider para) {
		IndexValue value = getValue(para);
		return getScore(value);
	}
	
}
