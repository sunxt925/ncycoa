package com.performance.poi.excel.entity;

/**
 * ģ�嵼����������
 */
public class TemplateExportParams {

	public TemplateExportParams() {

	}

	public TemplateExportParams(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public TemplateExportParams(String templateUrl, int sheetNum) {
		this.templateUrl = templateUrl;
		this.sheetNum = sheetNum;
	}

	public TemplateExportParams(String templateUrl, String sheetName) {
		this.templateUrl = templateUrl;
		this.sheetName = sheetName;
	}

	public TemplateExportParams(String templateUrl, String sheetName, int sheetNum) {
		this.templateUrl = templateUrl;
		this.sheetName = sheetName;
		this.sheetNum = sheetNum;
	}

	
	private String templateUrl;
	private int sheetNum = 0;
	private String sheetName;

	/**
	 * ģ������·��
	 */
	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	/**
	 * �����ڼ���������Ĭ�ϵ�0��
	 */
	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	/**
	 * �����Ĺ���������֣�������趨��ʹ��ģ���е�����
	 */
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
