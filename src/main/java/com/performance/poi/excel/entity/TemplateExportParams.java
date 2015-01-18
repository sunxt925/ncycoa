package com.performance.poi.excel.entity;

/**
 * 模板导出参数设置
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
	 * 模板所在路径
	 */
	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	/**
	 * 导出第几个工作表，默认第0个
	 */
	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	/**
	 * 导出的工作表的名字，如果不设定就使用模板中的名字
	 */
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
