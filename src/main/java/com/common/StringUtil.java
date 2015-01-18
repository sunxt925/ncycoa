package com.common;

import java.util.*;

public class StringUtil
{
	public static String[] getRepPara(String inStr)
	{
		try
		{
			String retstr = "";
			while (inStr.indexOf("@") > -1)
			{
				inStr = inStr.substring(inStr.indexOf("@") + 1);
				retstr = retstr + inStr.substring(0, inStr.indexOf("@"))+",";
				inStr = inStr.substring(inStr.indexOf("@") + 1);
			}
			if (retstr.equals(""))
			{
				return null;
			}
			else
			{
				return retstr.split(",");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
