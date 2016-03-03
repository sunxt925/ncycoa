package com.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.db.DBObject;
import com.db.DataTable;

public class IndexCode {

	private final static Map<String, Integer> map_26 = new HashMap<String,Integer>();
	private final static Map<Integer, String> _map_26 = new HashMap<Integer, String>();
	static {
		map_26.put("A", 0);
		map_26.put("B", 1);
		map_26.put("C", 2);
		map_26.put("D", 3);
		map_26.put("E", 4);
		map_26.put("F", 5);
		map_26.put("G", 6);
		map_26.put("H", 7);
		map_26.put("I", 8);
		map_26.put("J", 9);
		map_26.put("K", 10);
		map_26.put("L", 11);
		map_26.put("M", 12);
		map_26.put("N", 13);
		map_26.put("O", 14);
		map_26.put("P", 15);
		map_26.put("Q", 16);
		map_26.put("R", 17);
		map_26.put("S", 18);
		map_26.put("T", 19);
		map_26.put("U", 20);
		map_26.put("V", 21);
		map_26.put("W", 22);
		map_26.put("X", 23);
		map_26.put("Y", 24);
		map_26.put("Z", 25);
		
		_map_26.put(0,"A");
		_map_26.put(1,"B");
		_map_26.put(2,"C");
		_map_26.put(3,"D");
		_map_26.put(4,"E");
		_map_26.put(5,"F");
		_map_26.put(6,"G");
		_map_26.put(7,"H");
		_map_26.put(8,"I");
		_map_26.put(9,"J");
		_map_26.put(10,"K");
		_map_26.put(11,"L");
		_map_26.put(12,"M");
		_map_26.put(13,"N");
		_map_26.put(14,"O");
		_map_26.put(15,"P");
		_map_26.put(16,"Q");
		_map_26.put(17,"R");
		_map_26.put(18,"S");
		_map_26.put(19,"T");
		_map_26.put(20,"U");
		_map_26.put(21,"V");
		_map_26.put(22,"W");
		_map_26.put(23,"X");
		_map_26.put(24,"Y");
		_map_26.put(25,"Z");
	}
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
			boolean flag = false;
			if(maxcount == 0){
				num = 1;
			}else{
				String[] maxnum = dt.get(dt.getRowsCount()-1).getString("indexcode").split("\\.");
				if(pIndexcode.equals("-1")){
					if(isDigit(maxnum[maxnum.length-1].substring(1, 3))&&Long.parseLong(maxnum[maxnum.length-1].substring(1, 3))<99){

						num = Long.parseLong(maxnum[maxnum.length-1].replace(indexcode, "")) + 1;
					}else if(isDigit(maxnum[maxnum.length-1].substring(1, 3))&&Long.parseLong(maxnum[maxnum.length-1].substring(1, 3))==99){
                         flag = true;
						
						num =  Long.parseLong(maxnum[maxnum.length-1].substring(1, 3)) + 1;
					}else{
						flag = true;
						
						num = covert10(maxnum[maxnum.length-1].substring(1, 3))+1;
					}
					
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
				   if(!flag){
					   newcode=indexcode+num;
				   }else{
					   newcode=indexcode+covert26((int)num);
				   }
				  
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
	
	public static boolean isDigit(String strNum) {  
	    return strNum.matches("[0-9]{1,}");  
	} 
	public static String covert26(int num){
		String result = "";
		if(num > 99){
			
				int tmp_d = num%26;
				int tmp_h = num/26;
				
				result = String.valueOf(_map_26.get(tmp_h%26)) + String.valueOf(_map_26.get(tmp_d%26));
			
		}else{
			result = num+"";
		}
		
		return result;
	}
	
	public static int covert10(String str){
		int s = map_26.get(str.substring(0, 1));
		int d = map_26.get(str.substring(1, 2));
		return s*26+d;
	}
}
