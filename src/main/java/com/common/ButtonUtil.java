package com.common;

import com.entity.system.*;

public class ButtonUtil
{
	public static String AddButton(UserInfo u, String cdccm, String type,
			String action)
	{
		return AddButton(u, cdccm, type, action, "");
	}

	public static String AddButton(UserInfo u, String cdccm, String type,
			String action, String name)
	{
		return AddButton(u, cdccm, type, action, name, "");
	}

	public static String AddButton(UserInfo u, String cdccm, String type,
			String action, String name, String parameter)
	{
		try
		{
			String retstr = "<a href=\"#\" onclick=\"" + action + "("
					+ parameter + ")\">";
			if (type.equals("add"))
			{
				retstr += "<img src='../images/icon/add.gif' border='0' width='20' height='20' align='absmiddle'>";
				if (name.equals(""))
					name = "Ìí¼Ó";
			}
			if (type.equals("delete"))
			{
				retstr += "<img src='../images/icon/delete.gif' border='0' width='20' height='20' align='absmiddle'>";
				if (name.equals(""))
					name = "É¾³ý";
			}
			if (type.equals("refresh"))
			{
				retstr += "<img src='../images/icon/refresh.gif' border='0' width='20' height='20' align='absmiddle'>";
				if (name.equals(""))
					name = "Ë¢ÐÂ";
			}
			retstr = retstr + name + "</a>";
			return retstr;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
