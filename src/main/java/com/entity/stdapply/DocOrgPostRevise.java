package com.entity.stdapply;

import java.util.Vector;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class DocOrgPostRevise {
    private int recid=0;
	private String PositionCode="";
    private String DocCode="";
    private String OrgCode="";
    private String Relation="";
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public String getPositionCode() {
		return PositionCode;
	}
	public void setPositionCode(String positionCode) {
		PositionCode = positionCode;
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
	public String getRelation() {
		return Relation;
	}
	public void setRelation(String relation) {
		Relation = relation;
	}
	
	/*增加
	 * 不知recid.nextVal正确否
	 * */
	public boolean insert(){
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into std_org_postrevise(recid,positioncode,doccode,orgcode,relation) values(recid.nextVal,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.PositionCode) ,new Parameter.String(this.DocCode), new Parameter.String(this.OrgCode),new Parameter.String(this.Relation) };// new Parameter.Int(this.roleid)
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
	
	public boolean InsertDt(DataTable dt){
		try
		{
			for (int i = 0; i < dt.getRowsCount(); i++) {
				DBObject db = new DBObject();
				String sql = "insert into std_org_postrevise(recid,positioncode,doccode,orgcode,relation) values(?,?,?,?,?)";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] {
						new Parameter.Int(dt.get(i).get(0)),
						new Parameter.String(dt.get(i).get(1).toString()),
						new Parameter.String(dt.get(i).get(2).toString()),
						new Parameter.String(dt.get(i).get(3).toString()),
						new Parameter.String(dt.get(i).get(4).toString()) };// new
																// Parameter.Int(this.roleid)
				db.run(sql, pp) ;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
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
			String sql = "update std_org_postrevise set positioncode=?,doccode=?,orgcode=?,relation=? where recid=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.PositionCode), new Parameter.String(this.DocCode),new Parameter.String(this.OrgCode),new Parameter.String(this.Relation),new Parameter.Int(this.recid)};
		
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
	public boolean Delete(String recid,String myorgcode)
	{
		try
		{	boolean flag=false;
			Vector v = new Vector();
			DBObject db = new DBObject();
			if (recid.indexOf(",") == -1)
			{
				v.add("delete from std_org_postrevise where recid like '" + recid
						+ "%'");
			}
			else
			{
				String[] bm = recid.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from std_org_postrevise where recid like '" +bm[i]
							+ "%'");
				}
			}
			if (db.executeBatch(v))
			{
				flag=true;
			}
			return flag;
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
			String sql="delete from std_org_postrevise where doccode like '" +docCode+"'";
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
	 * 根据orgCode删除std_docorg中的数据
	 * 
	 * @param 
	 * @return
	 */
	public boolean DeleteByOrgCode(String orgCode)
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql="delete from std_org_postrevise where orgcode like " +orgCode;
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
	
	//根据positionCode 查询标准编码
	public DataTable getMetaCodeByPosCode(String positionCode)
	{
		try {
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select doccode from std_org_postrevise where positioncode ='"+positionCode+"'");
			return dt;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getpostByOrgdocCode(String orgcode , String doccode)
	{
		try {
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select positioncode from std_org_postrevise where doccode = '"+ doccode +"' and orgcode = '" + orgcode + "'");
			return dt;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
