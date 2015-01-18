package com.common;

import com.db.DBObject;

public class SequenceUtil {
	
	public static int getSequence(String entity)
	{
		try{
			DBObject db = new DBObject();
			int newsequence = Integer.parseInt(db.executeOneValue("select getsequence('"+entity+"') as seq from dual"));
			return newsequence;
			
		}catch(Exception e)
		{
			return -1;
		}
	}

}
