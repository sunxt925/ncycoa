package com.performance.poi.excel.entity;

import org.apache.poi.hssf.util.HSSFColor;

/**
 * 表格属性
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
	 * 表头颜色
	 */
	private short color = HSSFColor.WHITE.index;
	
	/**
	 * 属性说明行的颜色
	 * 例如:HSSFColor.SKY_BLUE.index  默认
	 */
	private short headerColor = HSSFColor.SKY_BLUE.index;

	/**
	 * 表格标题
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 工作表名
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
	 * 表格副标题
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
