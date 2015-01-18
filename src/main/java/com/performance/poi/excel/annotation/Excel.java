package com.performance.poi.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

	/**
	 * ����ʱ����Ӧ���ݿ���ֶ� ��Ҫ���û�����ÿ���ֶΣ� ������annocation������ ����ʱ������
	 * ���������������annotation���ֶε�˳���й� ����ʹ��a_id,b_id��ȷʵ�Ƿ�ʹ��
	 */
	public String exportName();

	/**
	 * ����ʱ��excel��ÿ���еĿ� ��λΪ�ַ���һ������=2���ַ� �� �������������нϺ��ʵĳ��� ����������6 ������һ�������֡�
	 * �Ա���4����Ůռ1�������б����������֡� ����1-255
	 */
	public int exportFieldWidth() default 10;

	/**
	 * ����ʱ��excel��ÿ���еĸ߶� ��λΪ�ַ���һ������=2���ַ�
	 */
	public int exportFieldHeight() default 10;

	/**
	 * ����signΪ1,����Ҫ��pojo�м���һ������ convertGet�ֶ���() ���磬�ֶ�sex ����Ҫ���� public String
	 * convertGet�ֶ���() ����ֵΪstring ����signΪ0,��ת�� ����ʹ�� @Transient
	 * ���ע����hibernate�����������
	 */
	public int exportConvertSign() default 0;

	/**
	 * ���������Ƿ���Ҫת�� �� �����е�excel���Ƿ���Ҫ���ֶ�תΪ��Ӧ������ ����signΪ1,����Ҫ��pojo�м��� void
	 * convertSet�ֶ���(String text)
	 */
	public int importConvertSign() default 0;

	/**
	 * ���뵼���Ƿ���Ҫת��,ͬ������exportConvertSign��importConvertSign
	 */
	public int imExConvert() default 0;

	/**
	 * �������� 1 ���ı� 2 ��ͼƬ,3�Ǻ��� Ĭ�����ı�
	 */
	public int exportType() default 1;

	/**
	 * �������� 1 ��file��ȡ 2 �Ǵ����ݿ��ж�ȡ Ĭ�����ļ� ͬ������Ҳ��һ����
	 */
	public int imageType() default 1;

	/**
	 * ����·��,�����ͼƬ������д,Ĭ����upload/className/ IconEntity������Ӧ�ľ���upload/Icon/
	 * 
	 */
	public String savePath() default "upload";

	/**
	 * չʾ���ڼ���
	 */
	public String orderNum() default "0";

	/**
	 * �Ƿ��� ��֧��\n
	 */
	public boolean isWrap() default true;

	/**
	 * �Ƿ���Ҫ����ϲ���Ԫ��(���ں���list��,�����ĵ�Ԫ��,�ϲ�list�����Ķ��row)
	 */
	public boolean needMerge() default false;

	/**
	 * ����ʱ������,����ֶ���Date��������Ҫ���� ���ݿ������string ����,�����Ҫ����������ݿ��ʽ
	 */
	public String databaseFormat() default "yyyyMMddHHmmss";

	/**
	 * ������ʱ���ʽ,������Ƿ�Ϊ�����ж��Ƿ���Ҫ��ʽ������
	 */
	public String exportFormat() default "";

	/**
	 * �����ʱ���ʽ,������Ƿ�Ϊ�����ж��Ƿ���Ҫ��ʽ������
	 */
	public String importFormat() default "";

	/**
	 * ʱ���ʽ,�൱��ͬʱ������exportFormat �� importFormat
	 */
	public String imExFormat() default "";

	/**
	 * cell д������õĺ���
	 */
	public String cellFormula() default "";
}
