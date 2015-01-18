package com.entity.system;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class Goods {
	private String GOODSCODE;
	public String getGOODSCODE() {
		return GOODSCODE;
	}
	public void setGOODSCODE(String gOODSCODE) {
		GOODSCODE = gOODSCODE;
	}
	public String getGOODSNAME() {
		return GOODSNAME;
	}
	public void setGOODSNAME(String gOODSNAME) {
		GOODSNAME = gOODSNAME;
	}
	public String getGOODSDESC() {
		return GOODSDESC;
	}
	public void setGOODSDESC(String gOODSDESC) {
		GOODSDESC = gOODSDESC;
	}
	
	public String getPARENTGOODSCODE() {
		return PARENTGOODSCODE;
	}
	public void setPARENTGOODSCODE(String pARENTGOODSCODE) {
		PARENTGOODSCODE = pARENTGOODSCODE;
	}
	public String getISPARENT() {
		return ISPARENT;
	}
	public void setISPARENT(String iSPARENT) {
		ISPARENT = iSPARENT;
	}
	public String getVALIDFLAG() {
		return VALIDFLAG;
	}
	public void setVALIDFLAG(String vALIDFLAG) {
		VALIDFLAG = vALIDFLAG;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}
	public String getAUDITORGCODE() {
		return AUDITORGCODE;
	}
	public void setAUDITORGCODE(String aUDITORGCODE) {
		AUDITORGCODE = aUDITORGCODE;
	}
	private String GOODSNAME;
	private String GOODSDESC;
	private String AUDITORGCODE;
	
	private String PARENTGOODSCODE;
	private String ISPARENT;
	private String VALIDFLAG;
	private String MEMO;
	public Goods()
	{
		
	}
	public Goods(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select * from Com_GoodsClass t where t.GOODSCODE=?";
			//String sql = "select * from BASE_ORG where orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.GOODSCODE = PK;
				this.GOODSNAME = r.getString("GOODSNAME");
				this.GOODSDESC = r.getString("GOODSDESC");
				this.AUDITORGCODE = r.getString("AUDITORGCODE");
				this.PARENTGOODSCODE = r.getString("PARENTGOODSCODE");
				this.ISPARENT = r.getString("ISPARENT");
				this.VALIDFLAG = r.getString("VALIDFLAG");
				this.MEMO = r.getString("MEMO");	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public DataTable getnextGoodsList(int pageno, int perpage,String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||GOODSCODE||'\">' as 选择,GOODSCODE as 物资编码,GOODSNAME as 物资名称,GOODSDESC as 说明,AUDITORGCODE as 归口部门,VALIDFLAG as 有效标志,MEMO as 备注,'<a href=\"#\" onclick=F1(\"'||GOODSCODE||'\") class=\"button4\">修 改</a><a href=\"#\" onClick=dele(\"'||GOODSCODE||'\") class=\"button4\">删除</a>' as 操作   from Com_GoodsClass where PARENTGOODSCODE like '"+goodscode+"' order by GOODSCODE";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllnextgoodsList(String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			//System.out.println(goodscode);
			DataTable dt = db.runSelectQuery("select * from Com_GoodsClass where PARENTGOODSCODE  like '"+goodscode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public boolean Delete(String BmString)
	{
		try
		{
			DBObject db = new DBObject();
			String sqltemp="delete from Com_GoodsClass where GOODSCODE='"+BmString+"'";
			if(db.run(sqltemp))
				return true;
			else
				return false;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
