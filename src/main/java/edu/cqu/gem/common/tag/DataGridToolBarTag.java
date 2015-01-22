package edu.cqu.gem.common.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cqu.gem.common.dto.DataGridOperation;
import edu.cqu.gem.common.dto.DataGridOperationType;

/**
 * �б�������ǩ
 */
@SuppressWarnings("serial")
public class DataGridToolBarTag extends TagSupport {
	
	protected String url;
	protected String title;
	protected String exp;				// �ж������Ƿ���ʾ�ı��ʽ
	protected String funname;			// �Զ��庯������
	protected String icon;				// ͼ��
	protected String onclick;
	protected String width;
	protected String height;
	protected String operationCode;		// ��ť�Ĳ���Code

	@Override
	public int doEndTag() throws JspTagException {
		DataGridOperation dataGridUrl = new DataGridOperation();
		dataGridUrl.setTitle(title);
		dataGridUrl.setUrl(url);
		dataGridUrl.setType(DataGridOperationType.ToolBar);
		dataGridUrl.setIcon(icon);
		dataGridUrl.setOnclick(onclick);
		dataGridUrl.setExp(exp);
		dataGridUrl.setFunname(funname);
		dataGridUrl.setWidth(String.valueOf(width));
		dataGridUrl.setHeight(String.valueOf(height));
		
		DataGridTag parent = (DataGridTag) findAncestorWithClass(this, DataGridTag.class);
		parent.setToolbar(dataGridUrl, operationCode);
		return EVAL_PAGE;
	}

	public void setFunname(String funname) {
		this.funname = funname;
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

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
