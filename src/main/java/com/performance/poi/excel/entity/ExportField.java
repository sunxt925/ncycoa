package com.performance.poi.excel.entity;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 描述了导出的Excel中表格字段的信息
 */
public class ExportField {
	
	/**
	 * 单元格宽度
	 */
	private int width;

	/**
	 * 单元格高度
	 */
	private int height;
	
	/**
	 * 对应exportName
	 */
	private String name;
	
	/**
	 * 对应exportType
	 */
	private int type;
	
	/**
	 * 图片的类型1是文件2是数据库
	 */
	private int exportImageType;
	
	/**
	 * 排序顺序
	 */
	private int orderNum;
	
	/**
	 * 是否支持换行
	 */
	private boolean isWrap;
	
	/**
	 * 是否需要合并
	 */
	private boolean needMerge;
	
	/**
	 * 数据库格式
	 */
	private String databaseFormat;
	
	/**
	 * 导出日期格式
	 */
	private String exportFormat;
	
	/**
	 * cell 函数
	 */
	private String cellFormula;
	/**
	 * get和convert 合并
	 */
	private Method getMethod;
	
	private List<Method> getMethods;
	
	private List<ExportField> list;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Method getGetMethod() {
		return getMethod;
	}

	public void setGetMethod(Method getMethod) {
		this.getMethod = getMethod;
	}

	public List<ExportField> getList() {
		return list;
	}

	public void setList(List<ExportField> list) {
		this.list = list;
	}

	public List<Method> getGetMethods() {
		return getMethods;
	}

	public void setGetMethods(List<Method> getMethods) {
		this.getMethods = getMethods;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public boolean isWrap() {
		return isWrap;
	}

	public void setWrap(boolean isWrap) {
		this.isWrap = isWrap;
	}

	public boolean isNeedMerge() {
		return needMerge;
	}

	public void setNeedMerge(boolean needMerge) {
		this.needMerge = needMerge;
	}

	public int getExportImageType() {
		return exportImageType;
	}

	public void setExportImageType(int exportImageType) {
		this.exportImageType = exportImageType;
	}

	public String getDatabaseFormat() {
		return databaseFormat;
	}

	public void setDatabaseFormat(String databaseFormat) {
		this.databaseFormat = databaseFormat;
	}

	public String getExportFormat() {
		return exportFormat;
	}

	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}

	public String getCellFormula() {
		return cellFormula;
	}

	public void setCellFormula(String cellFormula) {
		this.cellFormula = cellFormula;
	}
}
