package edu.cqu.gem.common.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cqu.gem.common.dto.DataGridOperation;
import edu.cqu.gem.common.dto.DataGridOperationType;

/**
 * 列表弹出窗操作项处理标签
 */
@SuppressWarnings("serial")
public class DataGridOpenOptTag extends TagSupport {
	
	protected String url;				// 弹出页面地址
	protected String width = "100%";	// 弹出窗口宽度
	protected String height = "100%";	// 弹出窗口高度
	protected String title;				// 链接标题
	protected String exp;				// 判断链接是否显示的表达式
	protected String operationCode;		// 按钮的操作Code
	protected String openModel = "OpenWin"; // 弹出方式

	@Override
	public int doEndTag() throws JspTagException {
		DataGridOperation dataGridUrl = new DataGridOperation();
		dataGridUrl.setTitle(title);
		dataGridUrl.setUrl(url);
		dataGridUrl.setWidth(width);
		dataGridUrl.setHeight(height);
		dataGridUrl.setType(DataGridOperationType.valueOf(openModel));
		dataGridUrl.setExp(exp);
		
		DataGridTag parent = (DataGridTag) findAncestorWithClass(this, DataGridTag.class);
		parent.setOperationUrl(dataGridUrl, operationCode);
		return EVAL_PAGE;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public void setOpenModel(String openModel) {
		this.openModel = openModel;
	}

}
