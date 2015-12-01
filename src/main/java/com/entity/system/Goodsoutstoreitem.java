package com.entity.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class Goodsoutstoreitem {
	private String OUTNO="";
	
	public String getOUTNO() {
		return OUTNO;
	}
	public void setOUTNO(String oUTNO) {
		OUTNO = oUTNO;
	}
	public String getSTOREEVENTNO() {
		return STOREEVENTNO;
	}
	public void setSTOREEVENTNO(String sTOREEVENTNO) {
		STOREEVENTNO = sTOREEVENTNO;
	}
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
	public String getGOODSSTYLE() {
		return GOODSSTYLE;
	}
	public void setGOODSSTYLE(String gOODSSTYLE) {
		GOODSSTYLE = gOODSSTYLE;
	}
	public String getGOODSNUMBER() {
		return GOODSNUMBER;
	}
	public void setGOODSNUMBER(String gOODSNUMBER) {
		GOODSNUMBER = gOODSNUMBER;
	}
	public String getMEASUREUNIT() {
		return MEASUREUNIT;
	}
	public void setMEASUREUNIT(String mEASUREUNIT) {
		MEASUREUNIT = mEASUREUNIT;
	}
	public String getOUTDATE() {
		return OUTDATE;
	}
	public void setOUTDATE(String oUTDATE) {
		OUTDATE = oUTDATE;
	}
	public String getAUDITORGCODE() {
		return AUDITORGCODE;
	}
	public void setAUDITORGCODE(String aUDITORGCODE) {
		AUDITORGCODE = aUDITORGCODE;
	}
	public String getAUDITORCODE() {
		return AUDITORCODE;
	}
	public void setAUDITORCODE(String aUDITORCODE) {
		AUDITORCODE = aUDITORCODE;
	}
	public String getINPUTDATE() {
		return INPUTDATE;
	}
	public void setINPUTDATE(String iNPUTDATE) {
		INPUTDATE = iNPUTDATE;
	}
	public String getUSINGORGCODE() {
		return USINGORGCODE;
	}
	public void setUSINGORGCODE(String uSINGORGCODE) {
		USINGORGCODE = uSINGORGCODE;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}
	public String getHANDLER() {
		return HANDLER;
	}
	public void setHANDLER(String hANDLER) {
		HANDLER = hANDLER;
	}
	
	
	public String getIsconfirm() {
		return isconfirm;
	}
	public void setIsconfirm(String isconfirm) {
		this.isconfirm = isconfirm;
	}
	public Date getConfirmdate() {
		return confirmdate;
	}
	public void setConfirmdate(Date confirmdate) {
		this.confirmdate = confirmdate;
	}

	private String STOREEVENTNO="";
	private String GOODSCODE="";
	private String GOODSNAME="";
	private String GOODSDESC="";
	private String GOODSSTYLE="";
	private String GOODSNUMBER="0";
	private String MEASUREUNIT="";
	private String OUTDATE="";
	private String AUDITORGCODE="";
	private String AUDITORCODE="";
	private String INPUTDATE="";
	private String USINGORGCODE="";
	private String MEMO="";
	private String HANDLER="";
	private String isconfirm;
	private Date confirmdate;
	public Goodsoutstoreitem()
	{
		
	}
	public Goodsoutstoreitem(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select * from Com_outstoreitem t where t.OUTNO=?";
			//String sql = "select * from BASE_ORG where orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.OUTNO = PK;
				this.STOREEVENTNO = r.getString("StoreEventNo");
				this.GOODSCODE=r.getString("Goodscode");
				this.GOODSNAME = r.getString("GoodsName");
				this.GOODSDESC = r.getString("GoodsDesc");
				this.GOODSSTYLE = r.getString("GoodsStyle");
				this.GOODSNUMBER = r.getString("GOODSNUMBER");
				this.MEASUREUNIT = r.getString("MeasureUnit");
				this.OUTDATE = r.getString("OUTDATE");	
				this.AUDITORGCODE = r.getString("AuditOrgCode");
				this.AUDITORCODE = r.getString("AuditorCode");
				this.INPUTDATE = r.getString("inputDate");
				this.USINGORGCODE = r.getString("USINGORGCODE");
				this.MEMO = r.getString("Memo");
				this.HANDLER=r.getString("HANDLER");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static List<Goodsoutstoreitem> getGoodsoutstoreitemsByOutNo(String storeeventNO){
		List<Goodsoutstoreitem> goodsoutstoreitems = new ArrayList<Goodsoutstoreitem>();
		try{
			
			DBObject db = new DBObject();
			String sql="select * from Com_outstoreitem t where t.STOREEVENTNO=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(storeeventNO) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() >= 1)
			{
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow r = dt.get(i);
					Goodsoutstoreitem goodsoutstoreitem = new Goodsoutstoreitem();
					goodsoutstoreitem.setOUTNO(r.getString("StoreEventNo"));
					goodsoutstoreitem.setSTOREEVENTNO(r.getString("StoreEventNo"));
					goodsoutstoreitem.setGOODSCODE(r.getString("Goodscode"));
					goodsoutstoreitem.setGOODSNAME(r.getString("GoodsName"));
					goodsoutstoreitem.setGOODSDESC(r.getString("GoodsDesc"));
					goodsoutstoreitem.setGOODSSTYLE(r.getString("GoodsStyle"));
					goodsoutstoreitem.setGOODSNUMBER(r.getString("GOODSNUMBER"));
					goodsoutstoreitem.setMEASUREUNIT(r.getString("MeasureUnit"));
					goodsoutstoreitem.setOUTDATE(r.getString("OUTDATE"));	
					goodsoutstoreitem.setAUDITORGCODE(r.getString("AuditOrgCode"));
					goodsoutstoreitem.setAUDITORCODE(r.getString("AuditorCode"));
					goodsoutstoreitem.setINPUTDATE(r.getString("inputDate"));
					goodsoutstoreitem.setUSINGORGCODE(r.getString("USINGORGCODE"));
					goodsoutstoreitem.setMEMO(r.getString("Memo"));
					goodsoutstoreitem.setHANDLER(r.getString("HANDLER"));
					goodsoutstoreitem.setIsconfirm(r.getString("isconfirm"));
					goodsoutstoreitem.setConfirmdate(Format.strToDate(r.getString("confirmdate")));
					goodsoutstoreitems.add(goodsoutstoreitem);
				}
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return goodsoutstoreitems;
		
	}
	/*public Goodsoutstoreitem(String PK,String str)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select * from Com_instoreitem t where t.GOODSCODE=? and t.STOREEVENTNO=?";
			//String sql = "select * from BASE_ORG where orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK),new Parameter.String(str) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.INNO = r.getString("inno");
				this.STOREEVENTNO = str;
				this.GOODSCODE=PK;
				this.GOODSNAME = r.getString("GoodsName");
				this.GOODSDESC = r.getString("GoodsDesc");
				this.GOODSSTYLE = r.getString("GoodsStyle");
				this.GOODSNUMBER = r.getString("GOODSNUMBER");
				this.MEASUREUNIT = r.getString("MeasureUnit");
				this.INDATE = r.getString("InDate");	
				this.AUDITORGCODE = r.getString("AuditOrgCode");
				this.AUDITORCODE = r.getString("AuditorCode");
				this.INPUTDATE = r.getString("inputDate");
				this.VENDORCODE = r.getString("VendorCode");
				this.MEMO = r.getString("Memo");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/
	public DataTable getGoodsStoreItemList(int pageno, int perpage,String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			
			
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||OUTNO||'\">' as 选择,OUTNO as 物资出库序号" +
			",StoreEventNo as 事件流水号,GoodsName as 物资品名,GoodsDesc as 说明,GoodsStyle as 规格型号,avaliablenumber as 可用数量 ,GOODSNUMBER as 出库数量,MeasureUnit as 计量单位," +
			"HANDLER as 领用人,USINGORGCODE as 领用部门,to_char(OutDate,'yyyy-mm-dd') as 出库日期,'<a href=\"#\" onclick=F3(\"'||OUTNO||','||avaliablenumber||'\") class=\"button4\">修 改</a><a href=\"#\" onClick=dele(\"'||OUTNO||'\") class=\"button4\">删除</a>' as 操作   from goodsout_store where StoreEventNo='"+goodscode+"'";
			//System.out.println(base_sql);
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getGoodsStoreItemListdetail(int pageno, int perpage,String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||OUTNO||'\">' as 选择,OUTNO as 物资出库序号,StoreEventNo as 事件流水号,GoodsName as 物资品名,GoodsDesc as 说明,GoodsStyle as 规格型号,GOODSNUMBER as 出库数量,MeasureUnit as 计量单位,HANDLER as 领用人,USINGORGCODE as 领用部门,to_char(OutDate,'yyyy-mm-dd') as 出库日期,AuditOrgCode as 验收部门,AuditorCode as 操作人员,to_char(inputDate,'yyyy-mm-dd') as 录入日期,Memo as 备注  from Com_outstoreitem where StoreEventNo='"+goodscode+"'";
			//System.out.println(base_sql);
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllGoodsStoreItemList(String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			//System.out.println(goodscode);
			DataTable dt = db.runSelectQuery("select * from Com_outstoreitem where StoreEventNo  like '"+goodscode+"'");
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
		DBObject db = new DBObject();
		//Goodsinstoreitem gsetemp=new Goodsinstoreitem(BmString);
		Goodsoutstoreitem goodsoutitem=new Goodsoutstoreitem(BmString);//本次出库某一具体物品信息。
		GoodsStoreInfo gsi=new GoodsStoreInfo(goodsoutitem.getGOODSCODE());//库存信息
		//Goodsinstoreitem goodsinitem=new Goodsinstoreitem(gsetemp.getGOODSCODE(),gsetemp.getSTOREEVENTNO());
		String outnum=Integer.toString(Integer.parseInt(gsi.getOutNumber())-Integer.parseInt(goodsoutitem.getGOODSNUMBER()));//更新对应的库存信息
		String outcount=Integer.toString(Integer.parseInt(gsi.getOutCount())-1);
		String AvailableNumber=Integer.toString(Integer.parseInt(gsi.getAvailableNumber())+Integer.parseInt(goodsoutitem.getGOODSNUMBER()));
		String sq4 = "update com_storeinfo set outNumber=?,outCount=?,AvailableNumber=? where goodscode=?";
		Parameter.SqlParameter[] pp4 = new Parameter.SqlParameter[]
		{  new Parameter.String(outnum),new Parameter.String(outcount), new Parameter.String(AvailableNumber),new Parameter.String(goodsoutitem.getGOODSCODE())
		};
		try {
			db.run(sq4,pp4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			
			String sqltemp="delete from Com_outstoreitem where OUTNO='"+BmString+"'";
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
