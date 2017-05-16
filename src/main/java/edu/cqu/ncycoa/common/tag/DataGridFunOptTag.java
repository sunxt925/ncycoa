package edu.cqu.ncycoa.common.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cqu.ncycoa.common.dto.DataGridOperation;
import edu.cqu.ncycoa.common.dto.DataGridOperationType;

/**
 * �б��Զ��庯����������ǩ
 */
@SuppressWarnings("serial")
public class DataGridFunOptTag extends TagSupport {
	
	protected String title;
	protected String exp;				//�ж������Ƿ���ʾ�ı��ʽ
	protected String funname;			//�Զ��庯������
	protected String operationCode;	    //��ť�Ĳ���Code

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
