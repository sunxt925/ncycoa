package com.performance;

import java.math.BigDecimal;

public class IndexScore {
	/** 
	 * 无效的指标值，用来表示计算过程中发生异常或错误 
	 */
	public static IndexScore INVALID = new IndexScore();
	/** 
	 * 未定义的指标值，表示未提供参数和默认值 
	 */
	public static IndexScore UNDEFINED = new IndexScore();
	
	public IndexScore() {
		this(0);
	}
	
	/**
	 * 如果value是infinite或NaN的话抛出NumberFormatException
	 */
	public IndexScore(double value) {
		this.value = new BigDecimal(value);
	}
	
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	private BigDecimal value;
	private boolean isMax;
	private boolean isMin;
	private boolean isTruncated;
	
}



