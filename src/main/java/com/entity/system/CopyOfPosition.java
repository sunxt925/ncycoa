package com.entity.system;
import java.util.Vector;

import com.common.*;
import com.db.*;
public class CopyOfPosition {
	
	private String PositionCode;
	private String PositionName;
	private String PositionDesc;
	private String PROCESSINSTANCEID;
	public String getPROCESSINSTANCEID() {
		return PROCESSINSTANCEID;
	}
	public void setPROCESSINSTANCEID(String pROCESSINSTANCEID) {
		PROCESSINSTANCEID = pROCESSINSTANCEID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	private String status;
	public CopyOfPosition()
	{
		
	}
	public CopyOfPosition(String id){
	  	  try
			{
	  		  
				DBObject db = new DBObject();
				String sql = "select * from Base_Position where PositionCode=?";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(id) };
				DataTable dt = db.runSelectQuery(sql, pp);
				if (dt != null && dt.getRowsCount() == 1)
				{
					DataRow r = dt.get(0);
					this.PositionCode = id;
					this.PositionName = r.getString("PositionName");	
					this.PositionDesc = r.getString("PositionDesc");
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
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
				String sql = "insert into Base_Position(PositionCode,PositionName,PositionDesc,PROCESSINSTANCEID,status) values(?,?,?,?,?)";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(this.PositionCode), new Parameter.String(this.PositionName), new Parameter.String(this.PositionDesc), new Parameter.String(this.PROCESSINSTANCEID), new Parameter.String(this.status) };// new Parameter.Int(this.roleid)
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
		 * 更新
		 * 
		 * @return
		 */
		public boolean Update()
		{
			try
			{
				DBObject db = new DBObject();
				String sql = "update Base_Position set PositionName=?,PositionDesc=? where PositionCode=?";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{new Parameter.String(this.PositionName),new Parameter.String(this.PositionDesc),new Parameter.String(this.PositionCode)};
			
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
		 * 删除
		 * 
		 * @param BmString
		 * @return
		 */
		public boolean Delete(String BmString)
		{
			try
			{
				Vector v = new Vector();
				DBObject db = new DBObject();
				if (BmString.indexOf(",") == -1)
				{
					v.add("delete from Base_Position where PositionCode like '" + BmString
							+ "%'");
				}
				else
				{
					String[] bm = BmString.split(",");
					for (int i = 0; i < bm.length; i++)
					{
						v.add("delete from Base_Position where PositionCode like '" +bm[i]
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
	public DataTable getPositionList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||PositionCode||'\">' as 选择,PositionCode as 岗位编码,PositionName as 岗位名称,PositionDesc as 岗位说明,'<a href=\"position_mod.jsp?bm='||PositionCode||'\" class=\"button4\">修 改</a> ' as 操作   from Base_Position order by PositionCode";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllPositionListDrawTable(String name)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt;
			if(name==null)
				 dt= db.runSelectQuery("select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||PositionCode||'\">' as 选择,PositionCode as 岗位编码,PositionName as 岗位名称,PositionDesc as 岗位说明  from Base_Position order by PositionCode");
			else
				 dt=db.runSelectQuery("select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||PositionCode||'\">' as 选择,PositionCode as 岗位编码,PositionName as 岗位名称,PositionDesc as 岗位说明  from Base_Position where PositionCode like '%"+name+"%' order by PositionCode" );
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllPositionList()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from Base_Position");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public String getPositionCode() {
		return PositionCode;
	}

	public void setPositionCode(String positionCode) {
		PositionCode = positionCode;
	}

	public String getPositionName() {
		return PositionName;
	}

	public void setPositionName(String positionName) {
		PositionName = positionName;
	}

	public String getPositionDesc() {
		return PositionDesc;
	}

	public void setPositionDesc(String positionDesc) {
		PositionDesc = positionDesc;
	}
}
