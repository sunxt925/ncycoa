package com.performance.pojo;

import java.util.ArrayList;
import java.util.List;

public class BaseFunction {
	private String id;
	private BaseFunction parent;											// 父菜单
	private String name;													// 菜单名称
	private Short level;													// 菜单等级
	private String url;														// 菜单地址
	private Short isIframe;													// 菜单地址打开方式
	private String order;													// 菜单排序
	private BaseIcon icon = new BaseIcon();									// 菜单图标
	private List<BaseFunction> children = new ArrayList<BaseFunction>();    // 子菜单
	
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