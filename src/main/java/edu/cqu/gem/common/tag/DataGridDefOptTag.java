package edu.cqu.gem.common.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cqu.gem.common.dto.DataGridOperation;
import edu.cqu.gem.common.dto.DataGridOperationType;

/**
 * �б�Ĭ�ϲ������ǩ
 */
@SuppressWarnings("serial")
public class DataGridDefOptTag extends TagSupport {
	
	protected String url;
	protected String title;
	protected String exp;				//�ж������Ƿ���ʾ�ı��ʽ
	protected String operationCode;		//��ť�Ĳ���Code
	
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
