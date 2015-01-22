package edu.cqu.gem.common.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cqu.gem.common.dto.DataGridOperation;
import edu.cqu.gem.common.dto.DataGridOperationType;

/**
 * 列表默认操作项标签
 */
@SuppressWarnings("serial")
public class DataGridDefOptTag extends TagSupport {
	
	protected String url;
	protected String title;
	protected String exp;				//判断链接是否显示的表达式
	protected String operationCode;		//按钮的操作Code
	
	@Override
	public int doEndTag() throws JspTagException {
		DataGridOperation dataGridUrl = new DataGridOperation();
		dataGridUrl.setTitle(title);
		dataGridUrl.setUrl(url);
		dataGridUrl.setType(DataGridOperationType.Deff);
		dataGridUrl.setExp(exp);
		
		DataGridTag parent = (DataGridTag) findAncestorWithClass(this, DataGridTag.class);
		parent.setOperationUrl(dataGridUrl, operationCode);
		return EVAL_PAGE;
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
	
}
