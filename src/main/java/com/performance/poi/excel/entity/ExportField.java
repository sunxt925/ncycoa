package com.performance.poi.excel.entity;

import java.lang.reflect.Method;
import java.util.List;

/**
 * �����˵�����Excel�б���ֶε���Ϣ
 */
public class ExportField {
	
	/**
	 * ��Ԫ����
	 */
	private int width;

	/**
	 * ��Ԫ��߶�
	 */
	private int height;
	
	/**
	 * ��ӦexportName
	 */
	private String name;
	
	/**
	 * ��ӦexportType
	 */
	private int type;
	
	/**
	 * ͼƬ������1���ļ�2�����ݿ�
	 */
	private int exportImageType;
	
	/**
	 * ����˳��
	 */
	private int orderNum;
	
	/**
	 * �Ƿ�֧�ֻ���
	 */
	private boolean isWrap;
	
	/**
	 * �Ƿ���Ҫ�ϲ�
	 */
	private boolean needMerge;
	
	/**
	 * ���ݿ��ʽ
	 */
	private String databaseFormat;
	
	/**
	 * �������ڸ�ʽ
	 */
	private String exportFormat;
	
	/**
	 * cell ����
	 */
	private String cellFormula;
	/**
	 * get��convert �ϲ�
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
