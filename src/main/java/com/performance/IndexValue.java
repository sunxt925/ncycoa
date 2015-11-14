package com.performance;

import java.math.BigDecimal;

/**
 * 指标项根据提供的参数得出的计算值，注意跟指标得分的区别
 * 
 * @author hui
 *
 */
public class IndexValue {
	
	/** 
	 * 无效的指标值，用来表示计算过程中发生异常或错误 
	 */
	public static IndexValue INVALID = new IndexValue();
	/** 
	 * 未定义的指标值，表示未提供参数和默认值 
	 */
	public static IndexValue UNDEFINED = new IndexValue();
	
	public IndexValue() {
		this(0);
	}
	
	/**
	 * 如果value是infinite或NaN的话抛出NumberFormatException
	 */
	public IndexValue(double value) {
		this.value = new BigDecimal(value);
	}
	
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	private BigDecimal value;
}
