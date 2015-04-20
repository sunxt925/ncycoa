package com.performance.poi.excel.entity;

import java.util.Map;

public class ExcelCollectionParams {

	/**
	 * ���϶�Ӧ������
	 */
	private String name;
	
	/**
	 * ʵ�����
	 */
	private Class<?> type;
	
	/**
	 * ���list����Ĳ�������ʵ�����
	 */
	private Map<String, ImportField> excelParams;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Map<String, ImportField> getExcelParams() {
		return excelParams;
	}

	public void setExcelParams(Map<String, ImportField> excelParams) {
		this.excelParams = excelParams;
	}
}
