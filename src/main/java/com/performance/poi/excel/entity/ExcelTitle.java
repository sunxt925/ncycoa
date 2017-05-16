package com.performance.poi.excel.entity;

import org.apache.poi.hssf.util.HSSFColor;

/**
 * �������
 */
public class ExcelTitle {
	
	public ExcelTitle(){
		
	}
	
	public ExcelTitle(String title,String secondTitle,String sheetName){
		this.title = title;
		this.subTitle = secondTitle;
		this.sheetName = sheetName;
	}

	private String title;
	private String subTitle;
	private String sheetName;
	
	/**
	 * ��ͷ��ɫ
	 */
	private short color = HSSFColor.WHITE.index;
	
	/**
	 * ����˵���е���ɫ
	 * ����:HSSFColor.SKY_BLUE.index  Ĭ��
	 */
	private short headerColor = HSSFColor.SKY_BLUE.index;

	/**
	 * ������
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ��������
	 */
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public short getColor() {
		return color;
	}

	public void setColor(short color) {
		this.color = color;
	}

	/**
	 * ��񸱱���
	 */
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String secondTitle) {
		this.subTitle = secondTitle;
	}

	public short getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(short headerColor) {
		this.headerColor = headerColor;
	}
}
