package edu.cqu.gem.common.dto;

import java.io.Serializable;
import java.util.List;

import edu.cqu.gem.common.util.dao.TypedQueryBuilder;

@SuppressWarnings("serial")
public class QueryDescriptor<T> implements Serializable {

	private int curPage = 1;		// 当前页
	private int pageSize = 10;		// 默认一页条数
	private String myAction;		// 请求的action 地址
	private String myForm;			// form 名字
	private int isUseimage = 0;		// 翻页工具条样式
	private TypedQueryBuilder<T> tqBuilder;

	private boolean flag = true;				// 对同一字段进行第二次重命名查询时值设置FASLE不保存重命名查询条件
	private String field = "";					// 查询需要显示的字段
	private Class<T> entityClass;				// POJO
	private List<T> results;					// 结果集
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
	 * 设置分页显示数
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
	 * 设置工具条样式（0:不带图片：1带图片）
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
	 * 对同一字段进行第二次重命名查询时值设置FASLE不保存重命名查询条件
	 * 
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
