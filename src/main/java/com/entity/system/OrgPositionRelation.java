package com.entity.system;

import java.util.Vector;
import com.common.*;
import com.db.*;
public class OrgPositionRelation {
	private String positioncode;
	
	private String orgcode;
	private String relationorgcode;
	private String relationpositioncode;
	private String POSITIONRELATION;
	private int ORGPOSITIONRELATIONID;
	
	
	public OrgPositionRelation()
	{
		
	}
	public DataTable getOrgPositionRelationList(String orgcode,String positioncode)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||ORGPOSITIONRELATIONID||'\">' as 选择,relationorgcode as 机构编码,relationorgname as 机构名称,relationpositioncode as 岗位编码,relationpositionname as 岗位名称,POSITIONRELATION as 关系 ,'<a href=\"#\" onClick=dele(\"'||ORGPOSITIONRELATIONID||'\") class=\"button4\">删除</a>' as 操作  from orgpositionview where OrgCode ='"+orgcode+"' and positioncode='"+positioncode+"'";

			
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 添入
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into BASE_ORGPOSITIONRELATION(ORGPOSITIONRELATIONID,PositionCode,orgcode,relationorgcode,relationpositioncode,POSITIONRELATION) values(ORGPOSITIONRELATIONID.nextVal,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.positioncode), new Parameter.String(this.orgcode), new Parameter.String(this.relationorgcode),new Parameter.String(this.relationpositioncode),new Parameter.String(this.POSITIONRELATION)};// new Parameter.Int(this.roleid)
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
	public boolean Insert(OrgPosition[] orgposition)//批量插入
	{
		try
		{
			for(int i=0;i<orgposition.length;i++)
			{
				this.relationorgcode=orgposition[i].getOrgcode();
				this.relationpositioncode=orgposition[i].getPositioncode();
				if(!this.Insert())
					return false;
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean Delete(String BmString)
	{
		try
		{
			Vector v = new Vector();
			DBObject db = new DBObject();
			if (BmString.indexOf(",") == -1)
			{
				v.add("delete from BASE_OrgPositionRelation where ORGPOSITIONRELATIONID like '" + BmString
						+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from BASE_OrgPositionRelation where ORGPOSITIONRELATIONID like '" +bm[i]
							+ "%'");
				}
			}
			if (db.executeBatch(v))
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
	public int getORGPOSITIONRELATIONID() {
		return ORGPOSITIONRELATIONID;
	}
	public void setORGPOSITIONRELATIONID(int oRGPOSITIONRELATIONID) {
		ORGPOSITIONRELATIONID = oRGPOSITIONRELATIONID;
	}
	public String getPositioncode() {
		return positioncode;
	}
	public void setPositioncode(String positioncode) {
		this.positioncode = positioncode;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getRelationorgcode() {
		return relationorgcode;
	}
	public void setRelationorgcode(String relationorgcode) {
		this.relationorgcode = relationorgcode;
	}
	public String getRelationpositioncode() {
		return relationpositioncode;
	}
	public void setRelationpositioncode(String relationpositioncode) {
		this.relationpositioncode = relationpositioncode;
	}
	public String getPOSITIONRELATION() {
		return POSITIONRELATION;
	}
	public void setPOSITIONRELATION(String pOSITIONRELATION) {
		POSITIONRELATION = pOSITIONRELATION;
	}
}
