package com.performance.poi.excel.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel ���������ڱ��id��
 */
@Retention(RetentionPolicy.RUNTIME)   
@Target({ java.lang.annotation.ElementType.TYPE })
public @interface ExcelTarget {
	/**
	 *����excel����ID ���޶������ֶ� 
	 */
	public String id();
}
