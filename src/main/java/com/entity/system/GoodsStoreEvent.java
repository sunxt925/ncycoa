package com.entity.system;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class GoodsStoreEvent {
	private String StoreEventNo="";
	public String getStoreEventNo() {
		return StoreEventNo;
	}
	public void setStoreEventNo(String storeEventNo) {
		StoreEventNo = storeEventNo;
	}
	public String getEventType() {
		return EventType;
	}
	public void setEventType(String eventType) {
		EventType = eventType;
	}
	public String getEventDesc() {
		return EventDesc;
	}
	public void setEventDesc(String eventDesc) {
		EventDesc = eventDesc;
	}
	public String getGoodsItemNum() {
		return GoodsItemNum;
	}
	public void setGoodsItemNum(String goodsItemNum) {
		GoodsItemNum = goodsItemNum;
	}
	public String getInputOrgCode() {
		return InputOrgCode;
	}
	public void setInputOrgCode(String inputOrgCode) {
		InputOrgCode = inputOrgCode;
	}
	public String getAuditorCode() {
		return AuditorCode;
	}
	public void setAuditorCode(String auditorCode) {
		AuditorCode = auditorCode;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	private String EventType="";
	private String EventDesc="";
	private String GoodsItemNum="0";
	private String InputOrgCode="";
	private String AuditorCode="";
	private String inputDate="";
	private String Memo="";
	public GoodsStoreEvent()
	{
		
	}
	public GoodsStoreEvent(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select * from com_storeevent t where t.StoreEventNo=?";
			//String sql = "select * from BASE_ORG where orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.StoreEventNo = PK;
				this.EventType = r.getString("EventType");
				this.EventDesc = r.getString("EventDesc");
				this.GoodsItemNum = r.getString("GoodsItemNum");
				this.InputOrgCode = r.getString("InputOrgCode");
				this.AuditorCode = r.getString("AuditorCode");
				this.inputDate = r.getString("inputDate");
				this.Memo = r.getString("Memo");	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public DataTable getAllGoodsStore(int pageno, int perpage,String type)
	{
		try
		{
			DBObject db = new DBObject();
			

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||StoreEventNo||'\">' as 选择,StoreEventNo as 事件流水号,EventType as 事件类别,EventDesc as 事件说明,GoodsItemNum as 商品类别数量,InputOrgCode as 录入部门,AuditorCode as 操作人员, to_char(inputDate,'yyyy-mm-dd') as 录入日期,memo as 备注,'<a href=\"#\" onClick=detail(\"'||StoreEventNo||'\") class=\"button4\">明细</a><a href=\"#\" onClick=dele(\"'||StoreEventNo||','||GoodsItemNum||'\") class=\"button4\">删除</a>' as 操作   from Com_StoreEvent where EventType='"+type+"' ORDER BY INPUTDATE desc";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllGoodsStore(String type)
	{
		try
		{
			DBObject db = new DBObject();
			//System.out.println(goodscode);
			DataTable dt = db.runSelectQuery("select * from Com_StoreEvent where EventType='"+type+"'");
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
			String sql="delete from com_outstoreitem where StoreEventNo='"+BmString+"'";//删除出库事件item
			String sql_in="delete from com_instoreitem where StoreEventNo='"+BmString+"'";//删除入库事件item
			String sqltemp="delete from Com_StoreEvent where StoreEventNo='"+BmString+"'";//删除出入库事件
			if(db.run(sql)&&db.run(sql_in)&&db.run(sqltemp))
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
