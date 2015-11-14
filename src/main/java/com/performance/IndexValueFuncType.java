package com.performance;

import java.util.HashMap;
import java.util.Map;

/**
 * ָ��ֵ���㣨��ʽ������ 
 * 
 * @author hui
 * 
 */
public class IndexValueFuncType {
	
	public static IndexValueFuncType INVALID = new IndexValueFuncType("invalid", "XXXX");
	public static IndexValueFuncType FORMULAR = new IndexValueFuncType("formular", "���㺯��");
	public static IndexValueFuncType ENUM_SINGLE = new IndexValueFuncType("enum-sigle", "ö�ٵ�ѡ");
	public static IndexValueFuncType ENUM_MULTIPLE = new IndexValueFuncType("enum-multiple", "ö�ٶ�ѡ");
	public static IndexValueFuncType SUM = new IndexValueFuncType("sum", "�������");
	
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
