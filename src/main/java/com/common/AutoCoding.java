package com.common;
import com.db.*;
public class AutoCoding {
	public AutoCoding()
	{
		
	}
	public static String line(int num)
	{
		String temp="";
		for(int i=0;i<num;i++)
		{
			temp+="_";
		}
		return temp;
	}
	public static String initcoid(int num)
	{
		String temp="";
		for(int i=0;i<num-1;i++)
		{
			temp+="0";
		}
		temp+="1";
		return temp;
	}
	public static String Codingnolevel(String sheetname,String fieldname,String str,int codelength)//含有字母，没有分层的编码自动生成,最后一个参数是数字的位数
	{
		
		String newcoding="";
		try
			{
				
				
				
					DBObject db = new DBObject();
					
					DataTable dt = db.runSelectQuery("select * from "+sheetname+" where "+fieldname+" like '"+str+"%' order by "+fieldname+" desc");
					
					 if(dt!=null && dt.getRowsCount()>0){
					 String code =dt.get(0).getString(fieldname);
					 //System.out.println(code);
					 String temp=code.substring(code.length()-codelength,code.length());
					 //System.out.println(temp);
					 int newcode=Integer.parseInt(temp)+1; 
					  String s = String.valueOf(newcode);
					  String res="";
					  for(int i=s.length();i<codelength;i++)
					  {
					  	res+="0";
					  }
					 res+=s;
					 newcoding=str+res;
					
					 }
					 else{
					 newcoding=str+initcoid(codelength);
					 
					 
					 }
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
			return newcoding;
	}
	public static String Coding(String parentcode,String sheetname,String fieldname,int codelength)
	{
		
		String newcoding="";
		try
			{
				
				
				
					DBObject db = new DBObject();
					
					DataTable dt = db.runSelectQuery("select * from "+sheetname+" where "+fieldname+" like '"+parentcode+""+line(codelength)+"' order by "+fieldname+"");
					
					 if(dt!=null && dt.getRowsCount()>0){
					 String code =dt.get(dt.getRowsCount()-1).getString(fieldname);
					 //System.out.println(dt.getRowsCount());
					 String temp=code.substring(code.length()-codelength,code.length());
					 
					 int newcode=Integer.parseInt(temp)+1; 
					  String s = String.valueOf(newcode);
					  String res="";
					  for(int i=s.length();i<codelength;i++)
					  {
					  	res+="0";
					  }
					 res+=s;
					 newcoding=code.substring(0,code.length()-codelength)+res;
					
					 }
					 else{
					 newcoding=parentcode+initcoid(codelength);
					 
					 
					 }
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
			return newcoding;
	}
	public static String Coding(String parentcode,String sheetname,String fieldname,int codelength,String str)
	{
		
		String newcoding="";
		try
			{
				if(parentcode.equals("0"))
				{
					DBObject db = new DBObject();
					
					DataTable dt = db.runSelectQuery("select * from "+sheetname+" where "+fieldname+" like '"+parentcode+""+line(codelength-1)+"' order by "+fieldname+"");
					if(dt!=null && dt.getRowsCount()>0){
					 String code =dt.get(dt.getRowsCount()-1).getString(fieldname);
					 //System.out.println(dt.getRowsCount());
					 //String temp=code.substring(code.length()-codelength,code.length());
					 
					 int newcode=Integer.parseInt(code)+1; 
					  String s = String.valueOf(newcode);
					  String res="";
					  for(int i=s.length();i<codelength;i++)
					  {
					  	res+="0";
					  }
					 res+=s;
					 newcoding=code.substring(0,code.length()-codelength)+res;
					
					 }
					 else{
					 newcoding=initcoid(codelength);
					 
					 
					 }
					
				}
				else
				{
					
				
					DBObject db = new DBObject();
					//System.out.println("select * from "+sheetname+" where "+fieldname+" like '"+parentcode+"."+line(codelength)+"' order by "+fieldname+"");
					DataTable dt = db.runSelectQuery("select * from "+sheetname+" where "+fieldname+" like '"+parentcode+""+str+""+line(codelength)+"' order by "+fieldname+"");
					
					 if(dt!=null && dt.getRowsCount()>0){
					 String code =dt.get(dt.getRowsCount()-1).getString(fieldname);
					 //System.out.println(dt.getRowsCount());
					 String temp=code.substring(code.length()-codelength,code.length());
					 
					 int newcode=Integer.parseInt(temp)+1; 
					  String s = String.valueOf(newcode);
					  String res="";
					  for(int i=s.length();i<codelength;i++)
					  {
					  	res+="0";
					  }
					 res+=s;
					 newcoding=code.substring(0,code.length()-codelength)+res;
					
					 }
					 else{
					 newcoding=parentcode+"."+initcoid(codelength);
					 
					 
					 }
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
			return newcoding;
	}	 
		 
	
	
}
