package com.performance;

import java.util.HashMap;
import java.util.Map;

/**
 * ָ��÷ּ��㣨��ʽ������
 * 
 * @author hui
 *
 */
public class IndexScoreFuncType {
	
	public static IndexScoreFuncType INVALID = new IndexScoreFuncType("invalid", "XXXX");
	public static IndexScoreFuncType NORMAL	= new IndexScoreFuncType("normal", "һ�㺯��");
	public static IndexScoreFuncType SEGMENT = new IndexScoreFuncType("segment", "�ֶκ���");
	
	private static Map<String, IndexScoreFuncType> maps = new HashMap<String, IndexScoreFuncType>();
	
	protected IndexScoreFuncType(String type, String friendlyType) {
		this.friendlyName = friendlyType;
		setName(type);
	}
	
	public IndexScoreFuncType get(String type) {
		if(!maps.containsKey(type)) {
			return IndexScoreFuncType.INVALID;
		}
		return maps.get(type);
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
