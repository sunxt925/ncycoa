package com.entity.std;


import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class DocMetaInfo {
     private String DocCode="";
     private String DocName="";
     
     public String getDocCode() {
		return DocCode;
	}
	public void setDocCode(String docCode) {
		DocCode = docCode;
	}
	public String getDocName() {
		return DocName;
	}
	public void setDocName(String docName) {
		DocName = docName;
	}
	public DocMetaInfo(){
		
	}
	public DataTable getMetaList(String docCode) {
		
		try {
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select * from std_docmetainfo where doccode = '"+docCode+"'");
			return dt;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
				
	}
	public String getMetaNameByDocCode(String docCode) {
		try {
			String name = "";
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select * from std_docmetainfo where doccode = '"+docCode+"'");
			name = dt.get(0).get(1).toString();
			return name;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
    
		
	
	//将标准编码，标准名称插入std_docmetainfo
	public boolean insert(){
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into std_docmetainfo(doccode,docname) values(?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.DocCode), new Parameter.String(this.DocName) };// new Parameter.Int(this.roleid)
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
	
	/**
	 * 更新std_docmetainfo
	 * 
	 * @return
	 */
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update std_docmetainfo set doccode=?,docname=? where doccode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.DocCode),new Parameter.String(this.DocName),new Parameter.String(this.DocCode)};
		
			if (db.run(sql,pp))
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
	
	
	/**
	 * 根据docCode删除std_docmetainfo中的数据
	 * 
	 * @param 
	 * @return
	 */
	public boolean Delete(String docCode)
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql="delete from std_docmetainfo where doccode like '" +docCode+"'";
			if (db.run(sql))
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
