package com.entity.system;

import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;

public class WebFlowCode {

	//public String prmcod="";
	//public String prmcdd="";
	//public String code_class="";
	public WebFlowCode(){
		
	}
	
	public DataTable getWebFlowCode(){
		
		try {
			DBObject db=new DBObject();
			String sql="select prmcod,prmcdd from codctctp";
			DataTable dt=db.runSelectQuery(sql);
		
			return dt;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
}
	public boolean Syn(){
		try {
			
			DataTable dt=getWebFlowCode();
			for(int i=0;i<dt.getRowsCount();i++){
				Code code=new  Code();
				code.setTable_name(dt.get(i).getString("prmcod"));
				code.setCode_class("00020003");
				code.setTable_title(dt.get(i).getString("prmcdd"));
				code.Insert();
			}
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	public DataTable getAllWebflowCodeColList(String table_name,int pageno,int perpage){
		try {
			DBObject db=new DBObject();
			
			String base_sql = "select  prmcon as ±àÂë,prmcnd as ±àÂëËµÃ÷   from codctdep where prmcod='"+table_name+"' order by prmcon";
			
			
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
		 	return db.runSelectQuery(sql_run);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
   public DataTable getAllWebFlowCodeList(String table_name){
		
		
		try {
			DBObject db=new DBObject();
			String sql = "select  prmcon ,prmcnd    from codctdep where prmcod='"+table_name+"' order by prmcon";
			DataTable dt=new DataTable();
			return db.runSelectQuery(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	
		
		
	}
}
