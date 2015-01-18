package com.common;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Clob;



public class ClobTransfer {  
     /** 
     * 将String转成Clob ,静态方法 
     *  
     * @param str 
     *            字段 
     * @return clob对象，如果出现错误，返回 null 
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
     * Description:将string对象转换为Clob对象,Blob处理方式与此相同
     *
     * @param str
     * @param lob
     * @return
     * @throws Exception
     * @mail sunyujia@yahoo.cn
     * @blog blog.csdn.ne t/sunyujia/
     * @since：Oct 1, 2008 7:20:31 PM
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
     * 将Clob转成String ,静态方法 
     *  
     * @param clob 
     *            字段 
     * @return 内容字串，如果出现错误，返回 null 
     */  
    public static String clobToString(Clob clob) {  
        if (clob == null)  
            return null;  
        StringBuffer sb = new StringBuffer();  
        Reader clobStream = null;  
        try {  
            clobStream = clob.getCharacterStream();  
            char[] b = new char[60000];// 每次获取60K  
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