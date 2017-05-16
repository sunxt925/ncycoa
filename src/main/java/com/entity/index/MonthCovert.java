package com.entity.index;

import java.util.HashMap;
import java.util.Map;


public class MonthCovert {

	private static Map<String, String> map=new HashMap<String, String>();
	static {
		map.put("M01", "1");
		map.put("M02", "2");
		map.put("M03", "3");
		map.put("M04", "4");
		map.put("M05", "5");
		map.put("M06", "6");
		map.put("M07", "7");
		map.put("M08", "8");
		map.put("M09", "9");
		map.put("M10", "10");
		map.put("M11", "11");
		map.put("M12", "12");
		
	}
	public static String getMonth(String mon){
		return map.get(mon);
	}
}
