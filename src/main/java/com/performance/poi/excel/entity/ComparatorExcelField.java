package com.performance.poi.excel.entity;

import java.util.Comparator;

/**
 * ������������
 */
public class ComparatorExcelField implements Comparator<ExportField> {
	public int compare(ExportField prev, ExportField next) {
		return prev.getOrderNum() - next.getOrderNum();
	}
}
