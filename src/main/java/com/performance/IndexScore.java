package com.performance;

import java.math.BigDecimal;

public class IndexScore {
	/** 
	 * ��Ч��ָ��ֵ��������ʾ��������з����쳣����� 
	 */
	public static IndexScore INVALID = new IndexScore();
	/** 
	 * δ�����ָ��ֵ����ʾδ�ṩ������Ĭ��ֵ 
	 */
	public static IndexScore UNDEFINED = new IndexScore();
	
	public IndexScore() {
		this(0);
	}
	
	/**
	 * ���value��infinite��NaN�Ļ��׳�NumberFormatException
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



