package edu.cqu.gem.common.dto;

import java.util.List;
import edu.cqu.gem.ncycoa.util.SystemUtils;


/**
 * easyui��datagrid���̨���ݲ���ʹ�õ�dto
 */
public class DataGrid {

	private int page = 1;// ��ǰҳ
	private int rows = 10;// ÿҳ��ʾ��¼��
	private String sort = null;// �����ֶ���
	private SortDirection order = SortDirection.asc;// ��ʲô����(asc,desc)
	private String field;// �ֶ�
	private String treefield;// �������ݱ��ı��ֶ�
	@SuppressWarnings("rawtypes")
	private List results;// �����
	private int total;// �ܼ�¼��
	private String footer;// �ϼ���

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getField() {
		return field;
	}

	@SuppressWarnings("rawtypes")
	public List getResults() {
		return results;
	}

	public void setResults(@SuppressWarnings("rawtypes") List results) {
		this.results = results;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		if (SystemUtils.getRequest() != null && SystemUtils.getRequest().getParameter("rows") != null) {
			return rows;
		}
		return 10000;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public SortDirection getOrder() {
		return order;
	}

	public void setOrder(SortDirection order) {
		this.order = order;
	}

	public String getTreefield() {
		return treefield;
	}

	public void setTreefield(String treefield) {
		this.treefield = treefield;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

}
