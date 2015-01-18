package com.entity.std;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class DocStorePath {
	private String DocClass=""; //资源类
	private String StoreDir="";  //存储路径
	private String Memo="";
	public String getDocClass() {
		return DocClass;
	}
	public void setDocClass(String docClass) {
		DocClass = docClass;
	}
	public String getStoreDir() {
		return StoreDir;
	}
	public void setStoreDir(String storeDir) {
		StoreDir = storeDir;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	
	//根据资源类查找存储路径
	public String getStoreDirByClass(String docClass){
		
		try {
			String storedir="";
			DBObject db = new DBObject();
			DataTable dt= db.runSelectQuery("select storedir from System_DocStorePath where docclass= '"+docClass+"'");
		    storedir = dt.get(0).get(1).toString();
		    return storedir;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	    
	}
	public DataTable getAllDocClass() {
		try {
			
			DBObject db = new DBObject();
			DataTable dt= db.runSelectQuery("select * from System_DocStorePath ");   
		    return dt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*增加*/
	public boolean insert(){
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into System_DocStorePath(docclass,storedir,memo) values(?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.DocClass), new Parameter.String(this.StoreDir),new Parameter.String(this.Memo) };// new Parameter.Int(this.roleid)
			if (db.run(sql, pp))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	
}
