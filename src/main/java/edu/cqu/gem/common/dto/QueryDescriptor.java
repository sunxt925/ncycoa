package edu.cqu.gem.common.dto;

import java.io.Serializable;
import java.util.List;

import edu.cqu.gem.common.util.dao.TypedQueryBuilder;

@SuppressWarnings("serial")
public class QueryDescriptor<T> implements Serializable {

	private int curPage = 1;		// ��ǰҳ
	private int pageSize = 10;		// Ĭ��һҳ����
	private String myAction;		// �����action ��ַ
	private String myForm;			// form ����
	private int isUseimage = 0;		// ��ҳ��������ʽ
	private TypedQueryBuilder<T> tqBuilder;

	private boolean flag = true;				// ��ͬһ�ֶν��еڶ�����������ѯʱֵ����FASLE��������������ѯ����
	private String field = "";					// ��ѯ��Ҫ��ʾ���ֶ�
	private Class<T> entityClass;				// POJO
	private List<T> results;					// �����
	private int total;

	public TypedQueryBuilder<T> getTqBuilder() {
		return tqBuilder;
	}

	public void setTqBuilder(TypedQueryBuilder<T> tqBuilder) {
		this.tqBuilder = tqBuilder;
	}
	
	public QueryDescriptor() {
	}

	public QueryDescriptor(Class<T> entityClass, DataGrid dg) {
		this.curPage = dg.getPage();

		this.field = dg.getField();
		this.entityClass = entityClass;
		this.dataGrid = dg;
		this.pageSize = dg.getRows();
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	private DataGrid dataGrid;

	public DataGrid getDataGrid() {
		return dataGrid;
	}

	public void setDataGrid(DataGrid dataGrid) {
		this.dataGrid = dataGrid;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ���÷�ҳ��ʾ��
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getMyAction() {
		return myAction;
	}

	public void setMyAction(String myAction) {
		this.myAction = myAction;
	}

	public String getMyForm() {
		return myForm;
	}

	public void setMyForm(String myForm) {
		this.myForm = myForm;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getIsUseimage() {
		return isUseimage;
	}

	/**
	 * ���ù�������ʽ��0:����ͼƬ��1��ͼƬ��
	 * 
	 * @param isUseimage
	 */
	public void setIsUseimage(int isUseimage) {
		this.isUseimage = isUseimage;
	}

	public boolean isFlag() {
		return flag;
	}

	/**
	 * ��ͬһ�ֶν��еڶ�����������ѯʱֵ����FASLE��������������ѯ����
	 * 
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
