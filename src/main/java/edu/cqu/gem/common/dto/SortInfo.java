package edu.cqu.gem.common.dto;

/**
 * ����ֵ
 */
public class SortInfo {

	/**
	 * ��������
	 */
	private Integer columnId;
	/**
	 * desc or asc
	 */
	private SortDirection sortOrder;

	/**
	 * @return ��������
	 */
	public Integer getColumnId() {
		return columnId;
	}

	/**
	 * @param columnId
	 *            ��������
	 */
	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	/**
	 * @return desc or asc
	 */
	public SortDirection getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            desc or asc
	 */
	public void setSortOrder(SortDirection sortOrder) {
		this.sortOrder = sortOrder;
	}

}
