package com.performance.poi.excel.entity;

import java.lang.reflect.Method;
import java.util.List;

/**
 * excel ���빤��??��cell������ӳ??
 */
public class ImportField {
	
	/**
	 * ��ӦexportName
	 */
	private String name;
	
	/**
	 * ��ӦexportType
	 */
	private int type;
	
	/**
	 * ��Ӧ Collection NAME
	 */
	private String collectionName;
	
	/**
	 * ����ͼƬ�ĵ�??
	 */
	private String saveUrl;
	
	/**
	 * ����ͼƬ����??1����??2�����ݿ�
	 */
	private int saveType;
	
	/**
	 * ��ӦexportType
	 */
	private String classType;
	
	/**
	 * �������ڸ�ʽ
	 */
	private String importFormat;
	
	/**
	 * set��convert �ϲ�
	 */
	private Method setMethod;

	private List<Method> setMethods;

	private List<ImportField> list;

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

	public List<ImportField> getList() {
		return list;
	}

	public void setList(List<ImportField> list) {
		this.list = list;
	}

	public Method getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(Method setMethod) {
		this.setMethod = setMethod;
	}

	public List<Method> getSetMethods() {
		return setMethods;
	}

	public void setSetMethods(List<Method> setMethods) {
		this.setMethods = setMethods;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public int getSaveType() {
		return saveType;
	}

	public void setSaveType(int saveType) {
		this.saveType = saveType;
	}

	public String getImportFormat() {
		return importFormat;
	}

	public void setImportFormat(String importFormat) {
		this.importFormat = importFormat;
	}
}
