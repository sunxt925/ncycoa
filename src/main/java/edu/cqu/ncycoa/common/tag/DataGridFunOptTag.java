package edu.cqu.ncycoa.common.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cqu.ncycoa.common.dto.DataGridOperation;
import edu.cqu.ncycoa.common.dto.DataGridOperationType;

/**
 * 列表自定义函数操作项处理标签
 */
@SuppressWarnings("serial")
public class DataGridFunOptTag extends TagSupport {
	
	protected String title;
	protected String exp;				//判断链接是否显示的表达式
	protected String funname;			//自定义函数名称
	protected String operationCode;	    //按钮的操作Code

	@Override
	public int doEndTag() throws JspTagException {
		DataGridOperation dataGridUrl = new DataGridOperation();
		dataGridUrl.setTitle(title);
		dataGridUrl.setType(DataGridOperationType.Fun);
		dataGridUrl.setExp(exp);
		dataGridUrl.setFunname(funname);
		
		DataGridTag parent = (DataGridTag) findAncestorWithClass(this, DataGridTag.class);
		parent.setOperationUrl(dataGridUrl, operationCode);
		return EVAL_PAGE;
	}
	
	public void setFunname(String funname) {
		this.funname = funname;
	}
	
	public void setExp(String exp) {
		this.exp = exp;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

}
