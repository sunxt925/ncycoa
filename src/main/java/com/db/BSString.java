package com.db;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Vector;

public class BSString {

	/**
	@param Src
	@param breakStr
	@return java.util.Vector
	@roseuid 3F9E71990197
	 */
	public static Vector splite(String Src, String breakStr) {
		Vector v = new java.util.Vector();
		int pos;
		String s;
		String tmp;
		pos = Src.indexOf(breakStr);
		s = Src;
		while (pos > 0) {
			tmp = s.substring(0, pos);
			if (tmp.length() > 0)
				v.add(tmp);
			if(pos>=s.length()-1) break;
			s =	s.substring(
					pos + breakStr.length(),
					s.length() );
			pos = s.indexOf(breakStr);
		}
		return v;
	}

	public static String fillStrWithChar(
		String src,
		int strlength,
		char theChar,
		int direct) {
		String result = src;
		int dest = strlength - src.length();
		for (int i = 0; i < dest; i++) {
			if (direct == 0)
				result = theChar + result;
			else
				result += theChar;
		}
		return result;
	}
	
	/**
	 * 在保存数据库之前进行中文字符转换，防止出现乱码
	 * @param s
	 * @return
	 */
	public static String toGb(String s) {
		String str = null;
		if (s == null) {
			s = "";
		}
		try {
			str = new String(s.getBytes("ISO8859_1"), "GBK");
			return str;
		} catch (Exception e) {
			return null;
		}
	}
    /**
     * 对输入的字符串进行MD5加密
     * @param text
     * @return
     */
	public static String getMD5(String text) {
		String key = "1234567890";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			md.update(key.getBytes());
			ByteArrayInputStream bs = new ByteArrayInputStream(md.digest());
			StringWriter sw = new StringWriter();
			for (int i = 0; i < 16; i++) {
				String bt = Integer.toHexString(bs.read());
				if (bt.length() < 2) {
					sw.write("0" + bt);
				} else {
					sw.write(bt);
				}
			}
			String r = sw.toString();
			bs.close();
			sw.close();
			return r;
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String replaceAll(String src,String str){
		StringBuffer s=new StringBuffer(src);
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='\''){
				s.replace(i,i+1,str);
				i+=str.length()-1;
			}
		}
		return s.toString();
	}
	// 如果是NULL自动转空值 王泽建
	public static String blanknull(String s) { 
		if (s == null) s=""; 
		s=s.trim();
		s=replaceAll(s,"’");
		return s;
	} 

	// 替换字符串 王泽建
	public static String replacestr(String src,String oldstr,String newstr){
		int oldlen=oldstr.length();
		int newlen=newstr.length();
		int i=src.indexOf(oldstr);
		while(i>0){
			int srclen=src.length();
			src=src.substring(0,i)+newstr+src.substring(i+oldlen,srclen);
			i=src.indexOf(oldstr);		
		}
		return src;
	}
	
		
	// 拆分字符串为指定宽度的多行以显示或打印. 王泽建
	public static String manyrow(String src,int rows,int cols){
		String result="";
		String teststr="";
		int srclen=src.length();
		int row=0;
		int i;
		while (srclen>0){
			//算出实际字节数，汉字算2,先转换为了字节，再求长度。采用先试取，再测试的方式，可能多一个字符宽度。
			for (i=0;i<srclen;i++){
				teststr=src.substring(0,i);
				if (teststr.getBytes().length>=cols) break;
			}
			result=result+src.substring(0,i)+"<br>";
			src=src.substring(i,srclen);
			srclen=src.length();	
			row=row+1;
			if (row==rows) break;			
		}
		if (result.length()>4)	result=result.substring(0,result.length()-4);
		if (result.length()==0) result="&nbsp;";
		return result;
	}	
	
	
    /**
     *  FLOAT换成2位小数的字符串 
     * @param float
     * @return string
     */	
	public static String tomoney(float flt) { 
		String tom = ""+flt ;
		int wz=tom.indexOf('.');
		if (wz!=-1 ){
			tom=tom+"00";
			tom=tom.substring(0,wz+3);
		}else
			tom=tom+".00";
		return tom.trim();
	} 

}