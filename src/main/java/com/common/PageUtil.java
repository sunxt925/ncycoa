package com.common;

import com.db.DataRow;
import com.db.DataTable;

public class PageUtil
{
	
	
	public static String DividePage(int page_no,int pagecount,String url,String parameter)
	{
		String res = "";
		int prepage=page_no+1;
		try
		{
			res="<script language=\"javascript\">function changepage(){var op=document.getElementById(\"pageselect\").options[document.getElementById(\"pageselect\").selectedIndex].value;";
			res=res+"window.location.href=\""+url+"?page_no=\"+op+\"&"+parameter+"\";";
			res=res+"}</script>";
			//String first_page="<font color=cccccc >��ҳ</font>&nbsp;&nbsp;";
			String first_page="<span style='color:#cccccc;font-size:12px'>��ҳ</span>&nbsp;&nbsp;";
			if (page_no!=0)
			{
				first_page="<a  href=\""+url+"?page_no=0&"+parameter+"\"><span style='font-size:12px'>��ҳ</span></a>&nbsp;&nbsp;";
			}
			//String previous_page="<font color=cccccc>��һҳ</font>&nbsp;&nbsp";
			String previous_page="<span style='color:#cccccc;font-size:12px'>��һҳ</span>&nbsp;&nbsp";
			if (page_no>0)
			{
				previous_page="<a href=\""+url+"?page_no="+(page_no-1)+"&"+parameter+"\"><span style='font-size:12px'>��һҳ</span></a>&nbsp;&nbsp;";
			}
			//String next_page="<font color=cccccc>��һҳ</font>&nbsp;&nbsp";
			String next_page="<span style='color:#cccccc;font-size:12px'>��һҳ</span>&nbsp;&nbsp";
			if (page_no<pagecount-1)
			{
				next_page="<a href=\""+url+"?page_no="+(page_no+1)+"&"+parameter+"\"><span style='font-size:12px'>��һҳ</span></a>&nbsp;&nbsp;";
			}
			//String finial_page="<font color=cccccc>βҳ</font>&nbsp;&nbsp";
			String finial_page="<span style='color:#cccccc;font-size:12px'>βҳ</span>&nbsp;&nbsp";
			if (page_no<pagecount-1)
			{
				finial_page="<a href=\""+url+"?page_no="+(pagecount-1)+"&"+parameter+"\"><span style='color:#cccccc;font-size:12px'>βҳ</span></a>&nbsp;&nbsp;";
			}
			//res=res+first_page+previous_page+next_page+finial_page+"��ת���ڣ�";
			res=res+first_page+previous_page+next_page+finial_page+"<span style='font-size:12px'>��ת���ڣ�</span>";
			res=res+"<select id=\"pageselect\" name=\"pageselect\" onchange=\"changepage()\">";
			for (int i=1;i<=pagecount;i++)
			{
				String selected="";
				if (i==page_no+1)
				{
					selected="selected";
				}
				res=res+"<option value='"+String.valueOf(i-1)+"' "+selected+"><span style='font-size:12px'>��"+i+"ҳ</span></option>";
			}
			res=res+"</select><span style='font-size:12px'>ҳ&nbsp;&nbsp;��"+pagecount+"ҳ</span>";
			return res;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
		
	}
	
}
