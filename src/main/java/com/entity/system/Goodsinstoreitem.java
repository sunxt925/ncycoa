package com.entity.system;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class Goodsinstoreitem {
	private String INNO="";
	public String getINNO() {
		return INNO;
	}
	public void setINNO(String iNNO) {
		INNO = iNNO;
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
	public String getINDATE() {
		return INDATE;
	}
	public void setINDATE(String iNDATE) {
		INDATE = iNDATE;
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
	public String getVENDORCODE() {
		return VENDORCODE;
	}
	public void setVENDORCODE(String vENDORCODE) {
		VENDORCODE = vENDORCODE;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}
	private String STOREEVENTNO="";
	private String GOODSCODE="";
	private String GOODSNAME="";
	private String GOODSDESC="";
	private String GOODSSTYLE="";
	private String GOODSNUMBER="0";
	private String MEASUREUNIT="";
	private String INDATE="";
	private String AUDITORGCODE="";
	private String AUDITORCODE="";
	private String INPUTDATE="";
	private String VENDORCODE="";
	private String MEMO="";
	public Goodsinstoreitem()
	{
		
	}
	public Goodsinstoreitem(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select * from Com_instoreitem t where t.INNO=?";
			//String sql = "select * from BASE_ORG where orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.INNO = PK;
				this.STOREEVENTNO = r.getString("StoreEventNo");
				this.GOODSCODE=r.getString("Goodscode");
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
	}
	public Goodsinstoreitem(String PK,String str)
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
	}
	public DataTable getGoodsStoreItemList(int pageno, int perpage,String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			
			
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||INNO||'\">' as ѡ��,INNO as ����������,StoreEventNo as �¼���ˮ��,GoodsName as ����Ʒ��,GoodsDesc as ˵��,GoodsStyle as ����ͺ�,avaliablenumber as ��������,GOODSNUMBER as ����,MeasureUnit as ������λ,to_char(InDate,'yyyy-mm-dd') as �������,AuditOrgCode as ���ղ���,VendorCode as ��Ӧ��,Memo as ��ע,'<a href=\"#\" onclick=F3(\"'||INNO||'\") class=\"button4\">�� ��</a><a href=\"#\" onClick=dele(\"'||INNO||'\") class=\"button4\">ɾ��</a>' as ����   from goodsin_store where StoreEventNo='"+goodscode+"'";
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
	public DataTable getGoodsStoreItemListdetail(int pageno, int perpage,String goodscode)//�����鿴�������޸�
	{
		try
		{
			DBObject db = new DBObject();
			
			
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||INNO||'\">' as ѡ��,INNO as ����������,StoreEventNo as �¼���ˮ��,GoodsName as ����Ʒ��,GoodsDesc as ˵��,GoodsStyle as ����ͺ�,GOODSNUMBER as ����,MeasureUnit as ������λ,to_char(InDate,'yyyy-mm-dd') as �������,AuditOrgCode as ���ղ���,AuditorCode as ������Ա,to_char(inputDate,'yyyy-mm-dd') as ¼������,VendorCode as ��Ӧ��,Memo as ��ע   from Com_instoreitem where StoreEventNo='"+goodscode+"'";
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
			//System.out.println("getAllGoodsStoreItemList:"+goodscode);
			DBObject db = new DBObject();
			//System.out.println(goodscode);
			DataTable dt = db.runSelectQuery("select * from Com_instoreitem where StoreEventNo  = '"+goodscode+"'");
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
		Goodsinstoreitem goodsinitem=new Goodsinstoreitem(BmString);//����ĳһ������Ʒ��Ϣ��
		GoodsStoreInfo gsi=new GoodsStoreInfo(goodsinitem.getGOODSCODE());//�����Ϣ
		//Goodsinstoreitem goodsinitem=new Goodsinstoreitem(gsetemp.getGOODSCODE(),gsetemp.getSTOREEVENTNO());
		String totalnum=Integer.toString(Integer.parseInt(gsi.getTotalNumber())-Integer.parseInt(goodsinitem.getGOODSNUMBER()));//���¶�Ӧ�Ŀ����Ϣ
		String incount=Integer.toString(Integer.parseInt(gsi.getInCount())-1);
		String AvailableNumber=Integer.toString(Integer.parseInt(gsi.getAvailableNumber())-Integer.parseInt(goodsinitem.getGOODSNUMBER()));
		String sq4 = "update com_storeinfo set TotalNumber=?,InCount=?,AvailableNumber=? where goodscode=?";
		Parameter.SqlParameter[] pp4 = new Parameter.SqlParameter[]
		{  new Parameter.String(totalnum),new Parameter.String(incount), new Parameter.String(AvailableNumber),new Parameter.String(goodsinitem.getGOODSCODE())
		};
		try {
			db.run(sq4,pp4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			
			String sqltemp="delete from Com_instoreitem where INNO='"+BmString+"'";
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
