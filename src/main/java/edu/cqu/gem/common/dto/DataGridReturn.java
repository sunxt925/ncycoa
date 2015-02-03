package edu.cqu.gem.common.dto;

import java.util.List;

/**
 * ��̨��ǰ̨����JSON������easyui��datagrid
 */
@SuppressWarnings("rawtypes")
public class DataGridReturn {

	private Integer total;	// �ܼ�¼��
	private List rows;		// ÿ�м�¼
	
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

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
