package com.performance;

import java.util.HashMap;
import java.util.Map;

/**
 * 指标得分计算（公式）类型
 * 
 * @author hui
 *
 */
public class IndexScoreFuncType {
	
	public static IndexScoreFuncType INVALID = new IndexScoreFuncType("invalid", "XXXX");
	public static IndexScoreFuncType NORMAL	= new IndexScoreFuncType("normal", "一般函数");
	public static IndexScoreFuncType SEGMENT = new IndexScoreFuncType("segment", "分段函数");
	
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
