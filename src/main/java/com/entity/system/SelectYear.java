package com.entity.system;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectYear {
	public static String returnYear(){
		String year=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String[] dfs=df.format(new Date()).split("-");
		if(dfs[1].equals("01")||dfs[1].equals("02"))
			year=""+Integer.parseInt(dfs[0])+"/"+(Integer.parseInt(dfs[0])-1);
		else
			year=""+Integer.parseInt(dfs[0]);
		return year;
	}
}
