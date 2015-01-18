package com.common;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Clob;



public class ClobTransfer {  
     /** 
     * ��Stringת��Clob ,��̬���� 
     *  
     * @param str 
     *            �ֶ� 
     * @return clob����������ִ��󣬷��� null 
     */  
    public static Clob stringToClob(String str) {  
        if (null == str)  
            return null;  
        else {  
            try {  
                java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str  
                        .toCharArray());  
                return c;  
            } catch (Exception e) {  
                return null;  
            }  
        }  
    }  
    public static String oracleClob2Str(Clob clob) throws Exception {
        return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
    }
    /**
     *
     * Description:��string����ת��ΪClob����,Blob����ʽ�����ͬ
     *
     * @param str
     * @param lob
     * @return
     * @throws Exception
     * @mail sunyujia@yahoo.cn
     * @blog blog.csdn.ne t/sunyujia/
     * @since��Oct 1, 2008 7:20:31 PM
     */
    public static Clob oracleStr2Clob(String str, Clob lob) throws Exception {
        Method methodToInvoke = lob.getClass().getMethod(
                "getCharacterOutputStream", (Class[]) null);
        Writer writer = (Writer) methodToInvoke.invoke(lob, (Object[]) null);
        writer.write(str);
        writer.close();
        return lob;
    }
    /** 
     * ��Clobת��String ,��̬���� 
     *  
     * @param clob 
     *            �ֶ� 
     * @return �����ִ���������ִ��󣬷��� null 
     */  
    public static String clobToString(Clob clob) {  
        if (clob == null)  
            return null;  
        StringBuffer sb = new StringBuffer();  
        Reader clobStream = null;  
        try {  
            clobStream = clob.getCharacterStream();  
            char[] b = new char[60000];// ÿ�λ�ȡ60K  
            int i = 0;  
            while ((i = clobStream.read(b)) != -1) {  
                sb.append(b, 0, i);  
            }  
        } catch (Exception ex) {  
            sb = null;  
        } finally {  
            try {  
                if (clobStream != null) {  
                    clobStream.close();  
                }  
            } catch (Exception e) {  
            }  
        }  
        if (sb == null)  
            return null;  
        else  
            return sb.toString();  
    }  
      
    public static String clobToString(oracle.sql.CLOB clob){  
        try{  
            Reader inStream = clob.getCharacterStream();  
            char[] c = new char[(int) clob.length()];  
            inStream.read(c);  
            String data = new String(c);  
            inStream.close();  
            return data;  
        }catch(Exception e){  
            e.printStackTrace();  
            return "";  
        }  
    }  
}  