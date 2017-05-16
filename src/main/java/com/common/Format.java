/*
 * �������� 2006-7-17
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.common;

import java.text.SimpleDateFormat;
import java.util.*;
import java.security.*;
import java.io.*;

/**
 * @author admin
 * 
 *         TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class Format
{
	public static final String Empty = "";

	public static final String True = "1";

	public static final String False = "0";

	public static final String CONTENT_HTML = "text/html; charset=GB2312";

	public static final String CONTENT_XML = "text/html; charset=GB2312";

	public static final String Space = "&nbsp;";

	public static String toUtf8String(String s)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if ((c >= 0) && (c <= 255))
			{
				sb.append(c);
			}
			else
			{
				byte[] b;
				try
				{
					b = String.valueOf(c).getBytes("utf-8");
				}
				catch (Exception ex)
				{
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++)
				{
					int k = b[j];
					if (k < 0)
					{
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * <b>���ܣ�����ַ���Ϊnull���򷵻�null�����򷵻��ַ�����trimֵ </b>
	 * </p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String trim(String str)
	{
		return str == null ? null : str.trim();
	}

	public static String NullToBlank(String str)
	{
		if (str == null)
		{
			return "";
		}
		else
		{
			return str.trim();
		}
	}

	public static String NullToString(String str, String rep)
	{
		if (str == null)
		{
			return NullToBlank(rep);
		}
		else
		{
			return str.trim();
		}
	}

	public static String NullToZero(String str)
	{
		if (str == null)
		{
			return "0";
		}
		else if (str != null && str.equals(""))
		{
			return "0";
		}
		else
		{
			return str.trim();
		}
	}

	public static String NullToNum(String str, int num)
	{
		if (str == null)
		{
			return String.valueOf(num);
		}
		else
		{
			return str.trim();
		}
	}

	/**
	 * <p>
	 * <b>���ܣ��������Ϊ�գ������ַ����գ����򷵻ض����ַ�����trimֵ </b>
	 * </p>
	 * 
	 * @param o
	 * @return String
	 */
	public static String getString(Object o)
	{
		if (o == null)
		{
			return Format.Empty;
		}
		else
		{
			return o.toString().trim();
		}
	}

	/**
	 * <p>
	 * <b>���ܣ��������Ϊ�գ�����html�Ŀո񣬷��򷵻ض����ַ�����trimֵ </b>
	 * </p>
	 * 
	 * @param o
	 * @return String
	 */
	public static String formatToHtml(Object o)
	{
		if (o == null)
		{
			return Space;
		}
		else
		{
			String ret = o.toString().trim();
			if (ret.equals(Format.Empty))
			{
				return Space;
			}
			else
			{
				return ret;
			}
		}
	}

	/**
	 * <p>
	 * <b>���ܣ� ������������ַ���������hashMap </b>
	 * </p>
	 * 
	 * @param str
	 *            ��ʽΪa=1;b=2
	 * @return HashMap
	 */
	public static HashMap getKeyMap(String str)
	{
		if (str == null || str.equals(""))
		{
			return null;
		}
		HashMap hm = new HashMap();
		StringTokenizer st = new StringTokenizer(str, ";");
		int count = st.countTokens();
		String[] keyList = new String[count];
		for (int i = 0; i < count; i++)
		{
			keyList[i] = st.nextToken().toString().trim();
			StringTokenizer stoken = new StringTokenizer(keyList[i], "=");
			String key = stoken.nextToken();
			String value = null;
			try
			{
				value = stoken.nextToken();
			}
			catch (Exception e)
			{
				value = Format.Empty;
			}
			hm.put(key, value);
		}
		return hm;
	}

	/**
	 * <p>
	 * <b>���ܣ� �����������hashMapֵת���ַ��� </b>
	 * </p>
	 * 
	 * @param hm
	 *            HashMap
	 * @return String ��ʽΪa=1;b=2
	 */
	public static String getKeyString(HashMap hm)
	{
		return getKeyString(hm, false);
	}

	/**
	 * <p>
	 * <b>���ܣ� �����������hashMapֵת���ַ��� </b>
	 * </p>
	 * 
	 * @param hm
	 *            HashMap
	 * @param mode
	 *            true��ֻ���²�Ϊ�յ��ַ�����ַ��� false�������ַ������ºۼ����ַ�Ϊ�յĻ�ʹ��""��������ַ���
	 * @return String ��ʽΪa=1;b=2
	 */
	public static String getKeyString(HashMap hm, boolean mode)
	{
		if (hm == null)
		{
			return null;
		}
		StringBuffer s = new StringBuffer();
		Set alist = hm.keySet();
		Iterator it = alist.iterator();
		Object value = null;
		String tmp = null;
		while (it.hasNext())
		{
			String str01 = it.next().toString().trim();

			value = hm.get(str01);
			tmp = (value == null ? Empty : value.toString().trim());
			if (tmp.equals(Empty))
			{
				if (mode)
				{
					continue;
				}
				else
				{
					s.append(str01).append("=").append(tmp).append(";");
				}
			}
			else
			{
				s.append(str01).append("=").append(tmp).append(";");
			}
		}
		return s.toString();
	}

	/**
	 * <p>
	 * <b>���ܣ���msg�ַ�����javascript��� </b>
	 * </p>
	 */
	public static void alert(javax.servlet.jsp.JspWriter out, String msg)
	{
		if (msg != null && msg.indexOf("\"") >= 0)
		{
			msg = msg.replaceAll("\"", "\\\\\"");
		}
		if (msg != null && msg.indexOf("\n") >= 0)
		{
			msg = msg.replaceAll("\n", "\\\\n");
		}
		StringBuffer buf = new StringBuffer(
				"<script language='javascript'>\nalert(\"");
		buf.append(msg).append("\")\n</script>");
		try
		{
			out.println(buf.toString());
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * <p>
	 * <b>���ܣ���msg�ַ�����javascript��� </b>
	 * </p>
	 */
	public static void alert(java.io.PrintWriter out, String msg)
	{
		// System.out.println(msg);
		if (msg != null && msg.indexOf("\"") >= 0)
		{
			msg = msg.replaceAll("\"", "\\\\\"");
		}
		if (msg != null && msg.indexOf("\n") >= 0)
		{
			msg = msg.replaceAll("\n", "\\\\n");
		}
		// System.out.println(msg);

		StringBuffer buf = new StringBuffer(
				"<script language='javascript'>\nalert(\"");
		buf.append(msg).append("\")\n</script>");

		out.println(buf.toString());
	}

	public static String rootPath = null;

	public static void setWebRootPath(String path)
	{
		rootPath = path;
	}

	public static String getWebRootPath()
	{
		return rootPath;
	}

	public static String handleQueryString(String str)
	{
		if (str == null)
		{
			return Format.Empty;
		}
		else
		{
			return str.replaceAll("'", "''");
		}
	}

	public static StringBuffer replaceAll(StringBuffer buf, String pattern,
			String str)
	{
		if (buf == null || pattern == null || pattern.equals(str))
		{
			return buf;
		}
		int index = buf.indexOf(pattern);
		int len = pattern.length();
		while (index >= 0)
		{
			buf.replace(index, index + len, str);
			index = buf.indexOf(pattern, index + str.length());
		}
		return buf;
	}

	public static String handleHtmlString(String str)
	{
		if (str == null)
		{
			return Format.Empty;
		}
		else
		{
			return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		}
	}

	public static String NumberFormat(String str, String regular)
	{
		try
		{
			String res = "";
			if (regular.indexOf(".") > -1)
			{
				int xsws = regular.substring(regular.indexOf(".")).length() - 1;
				if (str == null)
				{
					res = "";
				}
				// ���û��С����
				if (str.indexOf(".") == -1 && !str.equals(""))
				{
					res = str + ".";
					for (int i = 0; i < xsws; i++)
					{
						res = res + "0";
					}
				}
				// �����С����
				if (str.indexOf(".") >= 0 && !str.equals(""))
				{
					// ���ж��е�С��λ��
					int yyxsws = str.length() - str.indexOf(".") - 1;
					if (yyxsws == xsws)
					{
						res = str;
					}
					else if (yyxsws < xsws)
					{
						res = str;
						for (int j = 0; j < xsws - yyxsws; j++)
						{
							res = res + "0";
						}
					}
					else
					{
						res = str.substring(0, str.length() + xsws - yyxsws);
					}
				}
				// �����ǧ�ַ�
				if (regular.indexOf(",") > -1)
				{
					if (res.indexOf(".") > -1)
					{
						String zs = res.substring(0, res.indexOf("."));
						String xs = res.substring(res.indexOf("."));
						zs = getQff(zs);
						res = zs + xs;
						res = res.replace(",.", ".");
						res = res.replace("-,", "-");
					}
					else
					{
						res = getQff(res);
					}
				}
			}
			else if (regular.indexOf(".") == -1 && regular.indexOf(",") > 0)
			{
				res = getQff(str);
				if (res.indexOf(",") > 0)
				{
					res = res.substring(0, res.length() - 1);
				}
				else
				{
					res = res.substring(0, res.length());
				}
			}
			else
			{
				res = str;
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return str;
		}
	}

	public static String getQff(String instr)
	{
		try
		{
			String res = "";
			String begin = "";
			if (instr.indexOf("-") > 0)
			{
				begin = "-";
				instr = instr.substring(1);
			}
			int zs_len = instr.length();
			int xhcs = (int) zs_len / 3;
			if (zs_len % 3 > 0)
			{

				for (int i = 0; i < zs_len % 3; i++)
				{
					res = res + instr.charAt(i);
				}
				res = res + ",";

			}
			// else
			// {
			// xhcs = xhcs - 1;
			// }
			for (int i = 0; i < xhcs; i++)
			{
				int ksqs = zs_len % 3 + 3 * i;
				res = res + instr.charAt(ksqs);
				res = res + instr.charAt(ksqs + 1);
				res = res + instr.charAt(ksqs + 2);
				res = res + ",";
			}
			res = begin + res;
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public static String getMD5(String text)
	{
		String key = "1234567890";
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			md.update(key.getBytes());
			ByteArrayInputStream bs = new ByteArrayInputStream(md.digest());
			StringWriter sw = new StringWriter();
			for (int i = 0; i < 16; i++)
			{
				String bt = Integer.toHexString(bs.read());
				if (bt.length() < 2)
				{
					sw.write("0" + bt);
				}
				else
				{
					sw.write(bt);
				}
			}
			String r = sw.toString();
			bs.close();
			sw.close();
			return r;
		}
		catch (Exception e)
		{
			return "";
		}
	}

	public static String getFySql(String sql, int pageno, int perpage)
	{
		try
		{
			int page_start = perpage * pageno + 1;
			int page_end = (pageno + 1) * perpage;
			return "select * from (select rownum as ���, xx.* from (" + sql
					+ ") xx ) where ��� >="
					+ page_start+" and ��� <="+page_end;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	//���ڸ�ʽת��
	public static Date strToDate(String str){
		try {
			
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//Сд��mm��ʾ���Ƿ���  
		 
		Date date=sdf.parse(str); 
		return date;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}
	public static String dateToStr(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		if(date!=null)
		return sdf.format(date);
		else {
			return null;
		}
	}
	//�õ�������λ��
	public static int getIntlength(int n){
		int v=0;
		while(n/10>=1){
			n=n/10;
			v++;
		}
		v++;
		return v;
	}
	//��ȡ��ǰʱ��  yyyy-MM-dd HH��mm��ss
	public static String getNowtime()
	{
		 Date nowTime=new Date(); 
	      
	      SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH��mm��ss"); 
	      return time.format(nowTime); 
	}
	//��ȡ��ǰʱ��  yyyy-MM-dd
	public static String getNowtime2()
	{
		 Date nowTime=new Date(); 
	      
	      SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
	      return time.format(nowTime); 
	}
	public static String getFuturetime(int year){
		Date nowTime=new Date();
		nowTime.setYear(nowTime.getYear()+year);
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
	    return time.format(nowTime); 
	}
	//�滻�ַ����е�.Ϊ�����ַ�
	public static String replacePointtoOth(String string,String ch){
		String[] strings=string.split("\\.");
	      String ss="";
	      for(String s:strings){
	    	  ss+=s+ch;
	      }
	      return (String)ss.subSequence(0, ss.length()-1);
	}
	//�滻�ַ����е�.Ϊ�����ַ�
		public static String replaceOthtoPoint(String string,String ch){
			String[] strings=string.split(ch);
		      String ss="";
		      for(String s:strings){
		    	  ss+=s+".";
		      }
		      return (String)ss.subSequence(0, ss.length()-1);
		}
}