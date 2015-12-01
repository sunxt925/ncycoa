package com.common;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import com.db.DBObject;

public class Util {
	
	public static  String getSequence(String code)
	{
		try{
			DBObject db = new DBObject();
			return db.executeOneValue("select get_sequence('"+code+"') as seq from dual");
			
		}catch(Exception e)
		{
			return null;
		}
	}
	
	public static String getSeqcode(String name)
	{
		try{
			DBObject db = new DBObject();
			return db.executeOneValue("select seq_code from system_sequence where seq_name='"+name+"'");
			
		}catch(Exception e)
		{
			return null;
		}
	}
/**
 * ���������ļ�
 * @return
 */
	public static Properties getfileCfg(){
    	Properties prop = new Properties();
    	try {
    		String path = Util.class.getClassLoader().getResource("").toURI().getPath();
    		InputStream in = new BufferedInputStream (new FileInputStream(path+"/ftp.properties"));
        	prop.load(in);     ///���������б�
        	return prop;
		} catch (Exception e) {
			return null;
		}
    	
    }
	/**
	 * ��ȡ�ļ�·��
	 * @param filename
	 * @return
	 * @throws URISyntaxException
	 */
	public static String getFilepath(String filename){
		try {
			String path = Util.class.getClassLoader().getResource("").toURI().getPath();
			return path +"/"+ filename;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	/**
	 * ��ʱ������һ��������ļ���
	 * @return
	 */
	public static  String getName() {
		Random random = new Random();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"/" + random.nextInt(10000)
				+ System.currentTimeMillis();
	}

}
