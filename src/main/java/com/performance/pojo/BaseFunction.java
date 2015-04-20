package com.performance.pojo;

import java.util.ArrayList;
import java.util.List;

public class BaseFunction {
	private String id;
	private BaseFunction parent;											// ���˵�
	private String name;													// �˵�����
	private Short level;													// �˵��ȼ�
	private String url;														// �˵���ַ
	private Short isIframe;													// �˵���ַ�򿪷�ʽ
	private String order;													// �˵�����
	private BaseIcon icon = new BaseIcon();									// �˵�ͼ��
	private List<BaseFunction> children = new ArrayList<BaseFunction>();    // �Ӳ˵�
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public BaseIcon getIcon() {
		return icon;
	}

	public void setIcon(BaseIcon icon) {
		this.icon = icon;
	}

	public BaseFunction getParent() {
		return this.parent;
	}

	public void setParent(BaseFunction parent) {
		this.parent = parent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getLevel() {
		return this.level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<BaseFunction> getChildren() {
		return children;
	}

	public void setChildren(List<BaseFunction> children) {
		this.children = children;
	}

	public Short getIsIframe() {
		return isIframe;
	}

	public void setIsIframe(Short isIframe) {
		this.isIframe = isIframe;
	}
}