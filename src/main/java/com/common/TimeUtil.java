package com.common;

import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtil 
{
	public static String getSelectDateMonth(String f_name)
	{
		try
		{
			String res="<select name='"+f_name+"' id='"+f_name+"'>";
			Calendar cal=Calendar.getInstance();			
			int this_year=cal.get(Calendar.YEAR);
			int this_month=cal.get(Calendar.MONTH);
			for (int i=this_year;i>this_year-10;i--)
			{
				for (int j=12;j>0;j--)
				{
					if (i==this_year && j==this_month+1)
					{
						res=res+"<option value='"+i+"-"+j+"' selected>"+i+"-"+j+"</option>";
					}
					else
					{
						res=res+"<option value='"+i+"-"+j+"'>"+i+"-"+j+"</option>";
					}
				}
			}
			res=res+"</select>";
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getNowDate(int flag)
	{
		
		String date = "";
		switch(flag)
		{
		case 0:
		{
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间精确到分秒
			date = dateFormat.format(now);
			
			break;
			
		}
		case 1:
		{
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//时间精确到年月日
			date = dateFormat.format(now);
			
			break;
		}
		
		}
		return date;
	}
}
