package edu.cqu.gem.common.dto;

public class SortColumn {
	/**
	 * ��������
	 */
	protected String index;
	/**
	 * �Զ�����������
	 */
	protected String order;
	/**
	 * ��������
	 */
	protected String type;
	/**
	 * �����ֶ�����
	 */
	protected String name;
	/**
	 * �Զ���ȡֵ
	 */
	protected String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
