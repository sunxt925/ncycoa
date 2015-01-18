package com.common;
import com.db.*;

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

}
