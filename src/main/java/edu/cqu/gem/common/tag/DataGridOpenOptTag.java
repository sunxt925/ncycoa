package edu.cqu.gem.common.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cqu.gem.common.dto.DataGridOperation;
import edu.cqu.gem.common.dto.DataGridOperationType;

/**
 * �б�������������ǩ
 */
@SuppressWarnings("serial")
public class DataGridOpenOptTag extends TagSupport {
	
	protected String url;				// ����ҳ���ַ
	protected String width = "100%";	// �������ڿ��
	protected String height = "100%";	// �������ڸ߶�
	protected String title;				// ���ӱ���
	protected String exp;				// �ж������Ƿ���ʾ�ı��ʽ
	protected String operationCode;		// ��ť�Ĳ���Code
	protected String openModel = "OpenWin"; // ������ʽ

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
