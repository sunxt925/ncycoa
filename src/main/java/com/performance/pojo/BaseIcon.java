package com.performance.pojo;

public class BaseIcon {
	private String id;
	private String iconName;
	private Short iconType;
	private String iconPath;
	private byte[] iconContent;
	private String iconClas;
	private String extend;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIconName() {
		return this.iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Short getIconType() {
		return this.iconType;
	}

	public void setIconType(Short iconType) {
		this.iconType = iconType;
	}

	public String getIconPath() {
		return this.iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getIconClas() {
		return iconClas;
	}

	public void setIconClas(String iconClas) {
		this.iconClas = iconClas;
	}

	public void setIconContent(byte[] iconContent) {
		this.iconContent = iconContent;
	}

	public byte[] getIconContent() {
		return iconContent;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}
}
