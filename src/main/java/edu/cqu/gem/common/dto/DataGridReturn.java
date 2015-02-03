package edu.cqu.gem.common.dto;

import java.util.List;

/**
 * 后台向前台返回JSON，用于easyui的datagrid
 */
@SuppressWarnings("rawtypes")
public class DataGridReturn {

	private Integer total;	// 总记录数
	private List rows;		// 每行记录
	
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
