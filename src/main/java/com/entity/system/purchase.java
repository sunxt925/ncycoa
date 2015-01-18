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
	 * ɾ��
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


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||MeetingNo||'\">' as ѡ��,MEETINGNAME as ��������,to_char(MeetingBeginDate,'yyyy-mm-dd hh24:mi') as ��ʼʱ��,to_char(MeetingEndDate,'yyyy-mm-dd hh24:mi') as ����ʱ��,MeetingRoom as ����ص�,NUMATTENDEE as ��������,ApplyOrgCode as ������,REQUESTDESC as ����Ҫ�� ,'<a href=\"attend.jsp?bm='||MeetingNo||'\" class=\"button4\" target=\"memember\">�λ���Ա</a><a href=\"#\" onclick=F1(\"'||MeetingNo||'\") class=\"button4\">�� ��</a><a href=\"#\" onClick=F6(\"'||MeetingNo||'\") class=\"button4\">ɾ��</a>'as ����   from COM_MEETINGINFO where AuditFlag='0'"; 

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


			String base_sql = "select 'ѡ��' as ѡ��,meetingno,MEETINGNAME as ��������,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as ��ʼʱ��,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as ����ʱ��,MeetingRoom as ����ص�,NUMATTENDEE as ��������,ApplyOrgCode as ������,REQUESTDESC as ����Ҫ�� ,AUDITOPINION as ������,AuditFlag as ���, '����  'as ����   from COM_MEETINGINFO where AuditFlag='10'"; 

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


			String base_sql = "select 'ѡ��' as ѡ��,meetingno,MEETINGNAME as ��������,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as ��ʼʱ��,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as ����ʱ��,MeetingRoom as ����ص�,NUMATTENDEE as ��������,ApplyOrgCode as ������,REQUESTDESC as ����Ҫ�� ,AUDITOPINION as ������,AuditFlag as ���, '����  'as ����   from COM_MEETINGINFO where AuditFlag='11'"; 

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


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||MeetingNo||'\">' as ѡ��,MEETINGNAME as ��������,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as ��ʼʱ��,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as ����ʱ��,MeetingRoom as ����ص�,NUMATTENDEE as ��������,ApplyOrgCode as ������,REQUESTDESC as ����Ҫ�� ,AuditOpinion as ��������AUDITFLAG as ������ ��'<a href=\"#\" onClick=F1(\"'||MeetingNo||'\") class=\"button4\">�������</a>'as ����   from COM_MEETINGINFO where AuditFlag='10'or AuditFlag='0'"; 

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


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||staffcode||'\">' as ѡ��,staffcode as Ա������,staffname as Ա������,idcard as ���֤��,gender as �Ա�,memberid as ֤������,to_char(positiontime,'yyyy-mm-dd') as ��ְʱ��   from base_orgmember where positioncode='"+positioncode+"'"; 


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


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"\">' as ѡ��,APPLYORGCODE as ���벿��,APPLYCLASS as �������,ITEM as �ɹ���Ŀ,USE as ��Ŀ��;,TOTALMONEY as �ܽ�� ,to_char(DATETIME,'yyyy/mm/dd') as ֧������,SUPPLYUNIT as ������λ ��ILLUSTRATION as ����˵��   from PURCHASE where APPLYORGCODE='"+orgcode+"'";

			
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


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"\">' as ѡ��,APPLYORGCODE as ���벿��,APPLYCLASS as �������,ITEM as �ɹ���Ŀ,USE as ��Ŀ��;,TOTALMONEY as �ܽ�� ,to_char(DATETIME,'yyyy/mm/dd') as ֧������,SUPPLYUNIT as ������λ ��ILLUSTRATION as ����˵��   from PURCHASE where APPLYID='"+id+"'"; 

			
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


			String base_sql = "select SERIAL as ���,EQUIPMENT as �豸Ʒ��,EQUIPMENTCLASS as �豸����,SIZENUMBER as ����ͺ�,SINGLEPRICE as ���� ,AMOUNT as ����,TOTOALPRICCE as С��    from PURCHASESUB where applyid= '"+id+"'"; 

			
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


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||MeetingNo||'\">' as ѡ��,MEETINGNAME as ��������,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as ��ʼʱ��,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as ����ʱ��,MeetingRoom as ����ص�,NUMATTENDEE as ��������,ApplyOrgCode as ������,REQUESTDESC as ����Ҫ�� ,AuditOpinion as ��������AUDITFLAG as ������ ��MEETINGFLAG as ���鿪���־,'<a href=\"#\" onClick=F1(\"'||MEETINGFLAG||','||MeetingNo||'\") class=\"button4\">����</a> <a href=\"#\" onClick=F6(\"'||MeetingNo||'\") class=\"button4\">ɾ������</a>'as ����   from COM_MEETINGINFO where APPLYORGCODE='"+orgcode+"'and MeetingBeginDate like to_date('"+date+"','yyyy/mm/dd')"; 

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


			String base_sql = "select  MEETINGNAME as ��������,to_char(MeetingBeginDate,'yyyy-mm-dd hh24:mi') as ��ʼʱ��,to_char(MeetingEndDate,'yyyy-mm-dd hh24:mi') as ����ʱ��,MeetingRoom as ����ص�,ApplyOrgCode as ������,AUDITFLAG as ������ ,MEETINGFLAG as ���鿪���־   from COM_MEETINGINFO where MeetingRoom='"+roomid+"'and AUDITFLAG='11' "; 

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
