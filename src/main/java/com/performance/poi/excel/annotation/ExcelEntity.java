package com.performance.poi.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��ǵ�������ʵ����
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelEntity {

	/**
	 *����ʱ����Ӧ���ݿ���ֶ� ��Ҫ���û�����ÿ���ֶΣ�
	 *������annocation������
	 *����ʱ������ ���������������annotation���ֶε�˳���й�
	 *����ʹ��a_id,b_id��ȷʵ�Ƿ�ʹ��
	 */
	public String exportName()  default "";
}
