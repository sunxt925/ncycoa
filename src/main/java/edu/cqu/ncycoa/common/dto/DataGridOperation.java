package edu.cqu.ncycoa.common.dto;

public class DataGridOperation {
	
	private String title;			// ��ť����
	private String icon;			// ��ťͼ��
	private String value;			// �������
	private String url;				// �������ӵ�ַ
	private String funname;			// �Զ���JS������
	private String isbtn;			// �Ƿ��ǲ���ѡ�����������
	private String message;			// �������ʱ����ʾ��
	private String exp;				// �ж������Ƿ���ʾ�ı��ʽ
	private String width;			// ���������
	private String height;			// �������߶�
	private boolean isRadio;		// �Ƿ��ǵ�ѡ��
	private String onclick;			// ѡ����¼�
	private DataGridOperationType type;	// ��ť����

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setRadio(boolean isRadio) {
		this.isRadio = isRadio;
	}

	public String getFunname() {
		return funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public String getMessage() {
		return message;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIsbtn() {
		return isbtn;
	}

	public void setIsbtn(String isbtn) {
		this.isbtn = isbtn;
	}

	public void setType(DataGridOperationType type) {
		this.type = type;
	}

	public DataGridOperationType getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRadio() {
		return isRadio;
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

	public String getIcon() {
		return icon;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
