package edu.cqu.gem.common.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cqu.gem.common.dto.DataGridColumn;

/**
 * 列表字段处理项目
 */
@SuppressWarnings("serial")
public class DataGridColumnTag extends TagSupport {
	
	protected String title;
	protected String field;
	protected Integer width;
	protected String rowspan;
	protected String colspan;
	protected String align;
	protected boolean sortable = true;
	protected boolean checkbox;
	protected String formatter;
	protected boolean hidden = true;
	protected String replace;
	protected String treefield;
	protected boolean image;
	protected boolean query = false;
	private String queryMode = "single";		// 字段查询模式：single单字段查询；scope范围查询
	// protected boolean autoLoadData = true; 	// 列表是否自动加载数据
	private boolean frozenColumn = false; 		// 是否是冰冻列 默认不是
	protected boolean bSearchable = true;
	protected String url;						// 自定义链接
	protected String funname = "openwindow";	// 自定义函数名称
	protected String arg;						// 自定义链接传入参数字段
	protected String dictionary; 				// 数据字典组编码
	protected String extend; 					// 扩展属性
	protected String style; 					// Td的CSS
	protected String imageSize;					// 自定义图片显示大小
	protected String downloadName;				// 附件下载
	private boolean autocomplete = false;		// 自动完成
	private String extendParams;				// 扩展参数

	@Override
	public int doEndTag() throws JspTagException {
		DataGridColumn dataGridColumn = new DataGridColumn();
		dataGridColumn.setTitle(title);
		dataGridColumn.setField(field);
		dataGridColumn.setWidth(width);
		dataGridColumn.setRowspan(rowspan);
		dataGridColumn.setColspan(colspan);
		dataGridColumn.setAlign(align);
		dataGridColumn.setSortable(sortable);
		dataGridColumn.setCheckbox(checkbox);
		dataGridColumn.setDateFormatter(formatter);
		dataGridColumn.setHidden(hidden);
		dataGridColumn.setReplace(replace);
		dataGridColumn.setTreefield(treefield);
		dataGridColumn.setImage(image);
		dataGridColumn.setImageSize(imageSize);
		dataGridColumn.setQuery(query);
		dataGridColumn.setUrl(url);
		dataGridColumn.setFunname(funname);
		dataGridColumn.setArg(arg);
		dataGridColumn.setQueryMode(queryMode);
		dataGridColumn.setDictionary(dictionary);
		dataGridColumn.setFrozenColumn(frozenColumn);
		dataGridColumn.setExtend(extend);
		dataGridColumn.setStyle(style);
		dataGridColumn.setDownloadName(downloadName);
		dataGridColumn.setAutocomplete(autocomplete);
		dataGridColumn.setExtendParams(extendParams);
		
		DataGridTag parent = (DataGridTag) findAncestorWithClass(this, DataGridTag.class);
		parent.setColumn(dataGridColumn);
		return EVAL_PAGE;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public void setbSearchable(boolean bSearchable) {
		this.bSearchable = bSearchable;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public void setTreefield(String treefield) {
		this.treefield = treefield;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}

	public String getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}

	public boolean isFrozenColumn() {
		return frozenColumn;
	}

	public void setFrozenColumn(boolean frozenColumn) {
		this.frozenColumn = frozenColumn;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setAutocomplete(boolean autocomplete) {
		this.autocomplete = autocomplete;
	}

	public void setExtendParams(String extendParams) {
		this.extendParams = extendParams;
	}

}
