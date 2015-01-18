package com.entity.system;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class OrgPosition {
	private String positioncode;
	private String orgcode;
	private String positionname;
	private String orgname;
	private String positionconfigcount;
	private String memo;
	private int orgpositionid;
	public int getOrgpositionid() {
		return orgpositionid;
	}
	public void setOrgpositionid(int orgpositionid) {
		this.orgpositionid = orgpositionid;
	}
	public OrgPosition()
	{
		
	}
	public OrgPosition(String PK,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGPOSITION where positioncode=? and orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK),new Parameter.String(orgcode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.positioncode = PK;
				this.orgcode = orgcode;
				this.positionname = r.getString("positionname");
				this.orgname = r.getString("orgname");
				this.positionconfigcount = r.getString("positionconfigcount");
				this.memo = r.getString("memo");
				this.orgpositionid=Integer.parseInt(r.getString("orgpositionid"));
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public OrgPosition(int orgpositionid)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGPOSITION where orgpositionid=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(orgpositionid)};
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.orgpositionid=orgpositionid;
				this.positioncode = r.getString("positioncode");
				this.orgcode = r.getString("orgcode");
				this.positionname = r.getString("positionname");
				this.orgname = r.getString("orgname");
				this.positionconfigcount = r.getString("positionconfigcount");
				this.memo = r.getString("memo");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public DataTable getOrgcodebyPost(String PositionCode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select orgcode from base_orgposition where PositionCode ='"+PositionCode+"'");
			return dt;
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
			String sql = "insert into BASE_ORGPOSITION(orgpositionid,PositionCode,PositionName,orgcode,orgname,memo,positionconfigcount) values(orgpositionid.nextVal,?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.positioncode), new Parameter.String(this.positionname), new Parameter.String(this.orgcode),new Parameter.String(this.orgname),new Parameter.String(this.memo),new Parameter.Int(Integer.parseInt(this.positionconfigcount))};// new Parameter.Int(this.roleid)
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
	public boolean Insert(Position[] positions)//批量插入
	{
		try
		{
			for(int i=0;i<positions.length;i++)
			{
				this.positioncode=positions[i].getPositionCode();
				this.positionname=positions[i].getPositionName();
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
			String sql = "update BASE_ORGPOSITION set orgpositionid=?, PositionName=?,orgname=?,memo=?,positionconfigcount=? where PositionCode=? and orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.Int(this.orgpositionid),new Parameter.String(this.positionname),new Parameter.String(this.orgname),new Parameter.String(this.memo),new Parameter.Int(Integer.parseInt(this.positionconfigcount)),new Parameter.String(this.positioncode), new Parameter.String(this.orgcode)};
		
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
				v.add("delete from BASE_ORGPOSITION where orgpositionid like '" + BmString
						+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from BASE_ORGPOSITION where orgpositionid like '" +bm[i]
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
	public DataTable getOrgPositionList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,positioncode as 岗位编码,positionname as 岗位名称,positionconfigcount as 配置人数,memo as 备注,'<a href=\"#\" onclick=F1(\"'||OrgCode||'\",\"'||positioncode||'\")  class=\"button4\">修 改</a><a href=\"relationposition_list.jsp?bm='||OrgCode||'&positioncode='||positioncode||'&positionname='||positionname||'\" class=\"button4\" target=\"temp\">关联岗位</a><a href=\"#\" onClick=dele(\"'||orgpositionid||'\") class=\"button4\">删除</a> ' as 操作   from base_orgposition where OrgCode ='"+orgcode+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	public DataTable getPositionList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select positioncode as 岗位编码,positionname as 岗位名称,positionconfigcount as 配置人数,memo as 岗位说明  from base_orgposition where OrgCode ='"+orgcode+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getPositionList2(int pageno, int perpage,String orgcode)//有checkbox的
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||positioncode||'\">' as 选择,positioncode as 岗位编码,positionname as 岗位名称,positionconfigcount as 配置人数,memo as 岗位说明  from base_orgposition where OrgCode ='"+orgcode+"' order by positioncode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	

	
	public DataTable getOrgPositionListstd(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,positioncode as 岗位编码,positionname as 岗位名称,positionconfigcount as 配置人数,memo as 备注,'<a href=\"#\" onClick=F8(\"'||positioncode||'\",\"'||positionname||'\",\""+orgcode+"\") class=\"button4\">查看标准</a>' as 操作   from base_orgposition where OrgCode ='"+orgcode+"' order by orgpositionid";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTable getRelationOrgPositionList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,positioncode as 岗位编码,positionname as 岗位名称 ,positionconfigcount as 配置人数,memo as 备注  from base_orgposition where OrgCode ='"+orgcode+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllRelationOrgPositionList(String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,positioncode as 岗位编码,positionname as 岗位名称 ,positionconfigcount as 配置人数,memo as 备注  from base_orgposition where OrgCode ='"+orgcode+"'";

			
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getRoleOrgPositionList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||positioncode||'\">' as 选择,positioncode as 岗位编码,positionname as 岗位名称,positionconfigcount as 配置人数,memo as 备注 from base_orgposition where OrgCode ='"+orgcode+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable stdGetOrgPositionList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||positioncode||'\">' as 选择,positioncode as 岗位编码,positionname as 岗位名称   from base_orgposition where OrgCode ='"+orgcode+"' order by positioncode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllOrgPositionList(String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from base_orgposition where OrgCode ='"+orgcode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllOrgPositionListDrawTable(String orgname,String positionname)
	{
		try
		{
			
			DBObject db = new DBObject();
			DataTable dt;
			//String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,orgcode as 机构编码,orgname as 机构名称,positioncode as 岗位编码,positionname as 岗位名称   from base_orgposition where OrgCode ='"+orgcode+"'";
			if(orgname==null&&positionname==null)
				 dt= db.runSelectQuery("select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,orgcode as 机构编码,orgname as 机构名称,positioncode as 岗位编码,positionname as 岗位名称   from base_orgposition");
			else if(positionname.equals("请输入关键字"))
			{
				
				dt=db.runSelectQuery("select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,orgcode as 机构编码,orgname as 机构名称,positioncode as 岗位编码,positionname as 岗位名称   from base_orgposition where orgname like '%"+orgname+"%' order by orgcode" );
				
			}
			else if(orgname.equals("请输入关键字"))
				dt=db.runSelectQuery("select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,orgcode as 机构编码,orgname as 机构名称,positioncode as 岗位编码,positionname as 岗位名称   from base_orgposition where positionname like '%"+positionname+"%' order by orgcode" );
			else
			{
				/*System.out.println(orgname);
				System.out.println(positionname);*/
				dt=db.runSelectQuery("select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||orgpositionid||'\">' as 选择,orgcode as 机构编码,orgname as 机构名称,positioncode as 岗位编码,positionname as 岗位名称   from base_orgposition where positionname like '%"+positionname+"%' and orgname like '%"+orgname+"%' order by orgcode" );
			}
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable  getOrgposition(String staffcode){
		
		DBObject dbObject=new DBObject();
		String sql="select orgcode,staffname,positioncode from base_orgmember where staffcode='"+staffcode+"'";
		
		try {
			return dbObject.runSelectQuery(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		
	}	
		
	public DataTable getOrgPositionCode(String staffcode){
		DBObject dbObject = new DBObject();
		String sql = "select orgcode,positioncode from base_orgmember where staffcode='"+staffcode+"'";
		DataTable dt;
		try {
			dt = dbObject.runSelectQuery(sql);
			return dt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
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
	public String getPositionname() {
		return positionname;
	}
	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getPositionconfigcount() {
		return positionconfigcount;
	}
	public void setPositionconfigcount(String positionconfigcount) {
		this.positionconfigcount = positionconfigcount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
