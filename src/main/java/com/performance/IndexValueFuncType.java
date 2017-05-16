package com.performance;

import java.util.HashMap;
import java.util.Map;

/**
 * 指标值计算（公式）类型 
 * 
 * @author hui
 * 
 */
public class IndexValueFuncType {
	
	public static IndexValueFuncType INVALID = new IndexValueFuncType("invalid", "XXXX");
	public static IndexValueFuncType FORMULAR = new IndexValueFuncType("formular", "计算函数");
	public static IndexValueFuncType ENUM_SINGLE = new IndexValueFuncType("enum-sigle", "枚举单选");
	public static IndexValueFuncType ENUM_MULTIPLE = new IndexValueFuncType("enum-multiple", "枚举多选");
	public static IndexValueFuncType SUM = new IndexValueFuncType("sum", "子项汇总");
	
	private static Map<String, IndexValueFuncType> maps = new HashMap<String, IndexValueFuncType>();
	
	public static IndexValueFuncType get(String type) {
		if(!maps.containsKey(type)) {
			return IndexValueFuncType.INVALID;
		}
		return maps.get(type);
	}
	
	protected IndexValueFuncType(String type, String friendlyType) {
		this.friendlyName = friendlyType;
		setName(type);
	}
	
	public String getName() {
		return name;
	}

	protected void setName(String type) {
		if(!maps.containsKey(type)) {
			this.name = type;
			maps.put(type, this);
		}
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	private String name;
	private String friendlyName;

}
