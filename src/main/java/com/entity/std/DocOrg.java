package com.entity.std;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class DocOrg {
	private String Relation="";
	private String DocCode="";
	private String OrgCode="";
	public String getRelation() {
		return Relation;
	}
	public void setRelation(String relation) {
		Relation = relation;
	}
	public String getDocCode() {
		return DocCode;
	}
	public void setDocCode(String docCode) {
		DocCode = docCode;
	}
	public String getOrgCode() {
		return OrgCode;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	
	/*增加*/
	public boolean insert(){
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into std_docorg(doccode,orgcode,relation) values(?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.DocCode), new Parameter.String(this.OrgCode),new Parameter.String(this.Relation) };// new Parameter.Int(this.roleid)
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
	public DataTable Have() throws Exception{
	     DBObject db = new DBObject();
	     String sql = "select doccode from std_docorg where doccode = ? and orgcode=?";
	     Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	                                                          { new Parameter.String(this.DocCode)	,
	    		 												new Parameter.String(this.OrgCode)	
	                                                          };
	     DataTable dt;
			dt = db.runSelectQuery(sql, pp);
	     return dt;
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
			String sql = "update std_docorg set doccode=?,orgcode=?,relation=? where orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.DocCode),new Parameter.String(this.OrgCode),new Parameter.String(this.Relation),new Parameter.String(this.DocCode)};
		
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
	 * 根据docCode删除std_docorg中的数据
	 * 
	 * @param 
	 * @return
	 */
	public boolean DeleteByDocCode(String docCode)
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql="delete from std_docorg where doccode like '" +docCode+"'";
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
	

	/**
	 * 根据docCode删除std_docorg中的数据
	 * 
	 * @param 
	 * @return
	 */
	public boolean DeleteByOrgCode(String orgCode)
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql="delete from std_docorg where orgcode like " +orgCode;
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
	
	/*根据orgCode查找docCode*/
	public DataTable getMetaCodeByOrgCode(String orgCode)
	{
		try {
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select doccode from std_DocOrg where orgcode ="+orgCode);
			return dt;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*根据orgCode查找表的全部内容*/
	public DataTable getMetaByOrgCode(String orgCode)
	{
		try {
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select * from std_DocOrg where orgcode ="+orgCode);
			return dt;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
