package edu.cqu.gem.common.dto;

import java.util.List;

/**
 * ��̨��ǰ̨����JSON������easyui��datagrid
 */
public class DataGridReturn {

	private Integer total;	// �ܼ�¼��
	@SuppressWarnings("rawtypes") private List rows;		// ÿ�м�¼
	@SuppressWarnings("rawtypes") private List footer;
	
	@SuppressWarnings("rawtypes")
	public DataGridReturn(Integer total, List rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}

	@SuppressWarnings("rawtypes")
	public List getFooter() {
		return footer;
	}

	@SuppressWarnings("rawtypes")
	public void setFooter(List footer) {
		this.footer = footer;
	}

}
