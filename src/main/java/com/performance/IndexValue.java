package com.performance;

import java.math.BigDecimal;

/**
 * ָ��������ṩ�Ĳ����ó��ļ���ֵ��ע���ָ��÷ֵ�����
 * 
 * @author hui
 *
 */
public class IndexValue {
	
	/** 
	 * ��Ч��ָ��ֵ��������ʾ��������з����쳣����� 
	 */
	public static IndexValue INVALID = new IndexValue();
	/** 
	 * δ�����ָ��ֵ����ʾδ�ṩ������Ĭ��ֵ 
	 */
	public static IndexValue UNDEFINED = new IndexValue();
	
	public IndexValue() {
		this(0);
	}
	
	/**
	 * ���value��infinite��NaN�Ļ��׳�NumberFormatException
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
