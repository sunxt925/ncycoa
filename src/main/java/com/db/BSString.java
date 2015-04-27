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
	 * �ڱ������ݿ�֮ǰ���������ַ�ת������ֹ��������
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
     * ��������ַ�������MD5����
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
	// �����NULL�Զ�ת��ֵ ����
	public static String blanknull(String s) { 
		if (s == null) s=""; 
		s=s.trim();
		s=replaceAll(s,"��");
		return s;
	} 

	// �滻�ַ��� ����
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
	
		
	// ����ַ���Ϊָ����ȵĶ�������ʾ���ӡ. ����
	public static String manyrow(String src,int rows,int cols){
		String result="";
		String teststr="";
		int srclen=src.length();
		int row=0;
		int i;
		while (srclen>0){
			//���ʵ���ֽ�����������2,��ת��Ϊ���ֽڣ����󳤶ȡ���������ȡ���ٲ��Եķ�ʽ�����ܶ�һ���ַ���ȡ�
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
     *  FLOAT����2λС�����ַ��� 
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