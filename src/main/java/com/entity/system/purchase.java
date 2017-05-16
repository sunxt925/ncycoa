package com.entity.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;


import com.business.BuyGoodsApp;
import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.ExecSql;
import com.db.Parameter;
import com.db.SqlHelper;

public class purchase {
	
	private String ITEM;
    public String getITEM() {
		return ITEM;
	}


	public void setITEM(String iTEM) {
		ITEM = iTEM;
	}


	public String getTOTALMONEY() {
		return TOTALMONEY;
	}


	public void setTOTALMONEY(String tOTALMONEY) {
		TOTALMONEY = tOTALMONEY;
	}


	public String getSUPPLYUNIT() {
		return SUPPLYUNIT;
	}


	public void setSUPPLYUNIT(String sUPPLYUNIT) {
		SUPPLYUNIT = sUPPLYUNIT;
	}


	public String getAPPLYCLASS() {
		return APPLYCLASS;
	}


	public void setAPPLYCLASS(String aPPLYCLASS) {
		APPLYCLASS = aPPLYCLASS;
	}


	public String getUSE() {
		return USE;
	}


	public void setUSE(String uSE) {
		USE = uSE;
	}


	public String getDATETIME() {
		return DATETIME;
	}


	public void setDATETIME(String dATETIME) {
		DATETIME = dATETIME;
	}


	public String getILLUSTRATION() {
		return ILLUSTRATION;
	}


	public void setILLUSTRATION(String iLLUSTRATION) {
		ILLUSTRATION = iLLUSTRATION;
	}


	public String getAPPLYID() {
		return APPLYID;
	}


	public void setAPPLYID(String aPPLYID) {
		APPLYID = aPPLYID;
	}


	public String getAPPLYORGCODE() {
		return APPLYORGCODE;
	}


	public void setAPPLYORGCODE(String aPPLYORGCODE) {
		APPLYORGCODE = aPPLYORGCODE;
	}


	public String getPROCESSINSTANCEID() {
		return PROCESSINSTANCEID;
	}


	public void setPROCESSINSTANCEID(String pROCESSINSTANCEID) {
		PROCESSINSTANCEID = pROCESSINSTANCEID;
	}


	public String getSTATUS() {
		return STATUS;
	}


	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	private String TOTALMONEY;
    private String SUPPLYUNIT;
    private String APPLYCLASS;
    private String USE;
    private String DATETIME;
    private String ILLUSTRATION;
    private String APPLYID;
    private String APPLYORGCODE;
    private String PROCESSINSTANCEID;
	private String STATUS;
   
   

	public purchase(){
    }
   

public boolean Update()
	{
		try
		{

			DBObject db = new DBObject();
			String sql = "update  PURCHASE set STATUS=?,PROCESSINSTANCEID=? where APPLYID=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.String(this.STATUS),
					new Parameter.String(this.PROCESSINSTANCEID),
					
					new Parameter.String(this.APPLYID)
					
			};
		
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
	public boolean Delete(String staffcode,String positioncode)
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql = "delete from BASE_ORGMEMBER where staffcode=? and positioncode=?"; 
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
			      new Parameter.String(staffcode),
			      new Parameter.String(positioncode)
			                                             					
			 };

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
	
	
	public DataTable  getAllMeetingList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||MeetingNo||'\">' as 选择,MEETINGNAME as 会议名称,to_char(MeetingBeginDate,'yyyy-mm-dd hh24:mi') as 开始时段,to_char(MeetingEndDate,'yyyy-mm-dd hh24:mi') as 结束时段,MeetingRoom as 开会地点,NUMATTENDEE as 会议人数,ApplyOrgCode as 需求部门,REQUESTDESC as 其他要求 ,'<a href=\"attend.jsp?bm='||MeetingNo||'\" class=\"button4\" target=\"memember\">参会人员</a><a href=\"#\" onclick=F1(\"'||MeetingNo||'\") class=\"button4\">修 改</a><a href=\"#\" onClick=F6(\"'||MeetingNo||'\") class=\"button4\">删除</a>'as 操作   from COM_MEETINGINFO where AuditFlag='0'"; 

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			//System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTable  getAuditedMeetingList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '选择' as 选择,meetingno,MEETINGNAME as 会议名称,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as 开始时段,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as 结束时段,MeetingRoom as 开会地点,NUMATTENDEE as 会议人数,ApplyOrgCode as 需求部门,REQUESTDESC as 其他要求 ,AUDITOPINION as 审核意见,AuditFlag as 审核, '操作  'as 操作   from COM_MEETINGINFO where AuditFlag='10'"; 

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			//System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable  getinformList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '选择' as 选择,meetingno,MEETINGNAME as 会议名称,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as 开始时段,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as 结束时段,MeetingRoom as 开会地点,NUMATTENDEE as 会议人数,ApplyOrgCode as 需求部门,REQUESTDESC as 其他要求 ,AUDITOPINION as 审核意见,AuditFlag as 审核, '操作  'as 操作   from COM_MEETINGINFO where AuditFlag='11'"; 

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			//System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable  getAuditingList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||MeetingNo||'\">' as 选择,MEETINGNAME as 会议名称,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as 开始时段,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as 结束时段,MeetingRoom as 开会地点,NUMATTENDEE as 会议人数,ApplyOrgCode as 需求部门,REQUESTDESC as 其他要求 ,AuditOpinion as 审核意见，AUDITFLAG as 审核情况 ，'<a href=\"#\" onClick=F1(\"'||MeetingNo||'\") class=\"button4\">进行审核</a>'as 操作   from COM_MEETINGINFO where AuditFlag='10'or AuditFlag='0'"; 

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			//System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*public DataTable getRoleMemberList(int pageno, int perpage,String positioncode)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||staffcode||'\">' as 选择,staffcode as 员工编码,staffname as 员工姓名,idcard as 身份证号,gender as 性别,memberid as 证件号码,to_char(positiontime,'yyyy-mm-dd') as 入职时间   from base_orgmember where positioncode='"+positioncode+"'"; 


			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	*/
	public  DataTable getpurchaseList1(String orgcode)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"\">' as 选择,APPLYORGCODE as 申请部门,APPLYCLASS as 申请类别,ITEM as 采购项目,USE as 项目用途,TOTALMONEY as 总金额 ,to_char(DATETIME,'yyyy/mm/dd') as 支付日期,SUPPLYUNIT as 供货单位 ，ILLUSTRATION as 供货说明   from PURCHASE where APPLYORGCODE='"+orgcode+"'";

			
			//System.out.println(sql_run);
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  DataTable getpurchaseList(String id)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"\">' as 选择,APPLYORGCODE as 申请部门,APPLYCLASS as 申请类别,ITEM as 采购项目,USE as 项目用途,TOTALMONEY as 总金额 ,to_char(DATETIME,'yyyy/mm/dd') as 支付日期,SUPPLYUNIT as 供货单位 ，ILLUSTRATION as 供货说明   from PURCHASE where APPLYID='"+id+"'"; 

			
			//System.out.println(sql_run);
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  DataTable getmxList(String id)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select SERIAL as 编号,EQUIPMENT as 设备品名,EQUIPMENTCLASS as 设备类型,SIZENUMBER as 规格型号,SINGLEPRICE as 单价 ,AMOUNT as 数量,TOTOALPRICCE as 小计    from PURCHASESUB where applyid= '"+id+"'"; 

			
			//System.out.println(sql_run);
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  DataTable getOrgMeetingList(int pageno, int perpage,String orgcode,String date)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||MeetingNo||'\">' as 选择,MEETINGNAME as 会议名称,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as 开始时段,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as 结束时段,MeetingRoom as 开会地点,NUMATTENDEE as 会议人数,ApplyOrgCode as 需求部门,REQUESTDESC as 其他要求 ,AuditOpinion as 审核意见，AUDITFLAG as 审核情况 ，MEETINGFLAG as 会议开会标志,'<a href=\"#\" onClick=F1(\"'||MEETINGFLAG||','||MeetingNo||'\") class=\"button4\">处理</a> <a href=\"#\" onClick=F6(\"'||MeetingNo||'\") class=\"button4\">删除会议</a>'as 操作   from COM_MEETINGINFO where APPLYORGCODE='"+orgcode+"'and MeetingBeginDate like to_date('"+date+"','yyyy/mm/dd')"; 

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			//System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  DataTable getRoomList(int pageno, int perpage,String roomid)
	{
		try
		{
			
			DBObject db = new DBObject();


			String base_sql = "select  MEETINGNAME as 会议名称,to_char(MeetingBeginDate,'yyyy-mm-dd hh24:mi') as 开始时段,to_char(MeetingEndDate,'yyyy-mm-dd hh24:mi') as 结束时段,MeetingRoom as 开会地点,ApplyOrgCode as 需求部门,AUDITFLAG as 审核情况 ,MEETINGFLAG as 会议开会标志   from COM_MEETINGINFO where MeetingRoom='"+roomid+"'and AUDITFLAG='11' "; 

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			//System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllRoomList(String roomid)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from COM_MEETINGINFO where AuditFlag='11'and MeetingRoom='"+roomid+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllMeetingList()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from COM_MEETINGINFO where AuditFlag='0'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTable getAllOrgMeetingList(String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from COM_MEETINGINFO where APPLYORGCODE='"+orgcode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTable getAllOrgMeetingList(String orgcode,String date)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from COM_MEETINGINFO where APPLYORGCODE='"+orgcode+"'and MeetingBeginDate like to_date('"+date+"','yyyy/mm/dd')");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	

}
