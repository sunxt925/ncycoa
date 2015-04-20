package com.performance.poi.excel.entity;

/**
 * �����������
 */
public class ImportParams {
	/**
	 * ����������,Ĭ��0
	 */
	private int titleRows = 0;
	/**
	 * ��񸱱�������,Ĭ��1
	 */
	private int secondTitleRows = 1;
	/**
	 * �ֶ�����ֵ���б���֮��ľ��� Ĭ��0
	 */
	private int startRows = 0;
	/**
	 * ��������,������cellû��??����??
	 * ��??��Ϊ�����list�������??
	 */
	private int keyIndex = 0;
	/**
	 * �ϴ����????��ȡ��sheet ����,Ĭ��??
	 */
	private int sheetNum = 1;
	/**
	 * �Ƿ���Ҫ�����ϴ���Excel,Ĭ��Ϊfalse
	 */
	private boolean needSave = false;
	/**
	 * �����ϴ���ExcelĿ¼,Ĭ�ϣ�
	 * TestEntity����ౣ��·������
	 * upload/excelUpload/Test/yyyyMMddHHmss_*****
	 * ���������ϴ�ʱ��_��λ�����
	 */
	private String saveUrl = "upload/excelUpload";

	public int getTitleRows() {
		return titleRows;
	}

	public void setTitleRows(int titleRows) {
		this.titleRows = titleRows;
	}

	public int getSecondTitleRows() {
		return secondTitleRows;
	}

	public void setSecondTitleRows(int secondTitleRows) {
		this.secondTitleRows = secondTitleRows;
	}

	public int getStartRows() {
		return startRows;
	}

	public void setStartRows(int startRows) {
		this.startRows = startRows;
	}

	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public int getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public boolean isNeedSave() {
		return needSave;
	}

	public void setNeedSave(boolean needSave) {
		this.needSave = needSave;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

}
