package com.common;

import java.util.Date;

import com.db.DBObject;
import com.db.DataTable;

public class IndexCode {

	public static String getIndexRootcode(String indexcode,String pIndexcode){
		
		try {
			
			if(!pIndexcode.equals("-1")){
				indexcode=pIndexcode;
			}
			String sql="select indexcode from tbm_indexitem where indexcode like '"+indexcode+"%'   and parentindexcode='"+pIndexcode+"' order by indexcode";
			DBObject  db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			int maxcount = dt.getRowsCount();
			long num =0;
			if(maxcount == 0){
				num = 1;
			}else{
				String[] maxnum = dt.get(dt.getRowsCount()-1).getString("indexcode").split("\\.");
				if(pIndexcode.equals("-1")){
					num = Long.parseLong(maxnum[maxnum.length-1].replace(indexcode, "")) + 1;
				}else{
					num = Long.parseLong(maxnum[maxnum.length-1]) + 1;
				}
			}
			
			String newcode="";
			if(pIndexcode.equals("-1")){
			
			   if(num<10){
				  newcode=indexcode+"0"+num;
			   }
			   else {
				  newcode=indexcode+num;
			   }
			}
			else{
				
				
				if(num<10){
					newcode=pIndexcode+".00"+num;
				}else if(num<100){
					newcode=pIndexcode+".0"+num;
				}else {
					newcode=pIndexcode+"."+num;
				}
				
			}
			return newcode;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public static String getRecno(String flag){
		
		try {
			 Date nowTime=new Date(); 
		     
		     //SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss"); 
		    return (flag + nowTime.getTime()) + (int)(Math.random() * 100000);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	
	}
}
