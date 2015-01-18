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

public class MeetingInfo {
	
	public String getMeetingNo() {
		return MeetingNo;
	}
	public void setMeetingNo(String meetingNo) {
		MeetingNo = meetingNo;
	}
	public String getMeetingName() {
		return MeetingName;
	}
	public void setMeetingName(String meetingName) {
		MeetingName = meetingName;
	}
	public String getMeetingTopics() {
		return MeetingTopics;
	}
	public void setMeetingTopics(String meetingTopics) {
		MeetingTopics = meetingTopics;
	}
	public String getMeetingBeginDate() {
		return MeetingBeginDate;
	}
	public void setMeetingBeginDate(String meetingBeginDate) {
		MeetingBeginDate = meetingBeginDate;
	}
	public String getMeetingEndDate() {
		return MeetingEndDate;
	}
	public void setMeetingEndDate(String meetingEndDate) {
		MeetingEndDate = meetingEndDate;
	}
	public String getMeetingRoom() {
		return MeetingRoom;
	}
	public void setMeetingRoom(String meetingRoom) {
		MeetingRoom = meetingRoom;
	}
	public String getNumAttendee() {
		return NumAttendee;
	}
	public void setNumAttendee(String numAttendee) {
		NumAttendee = numAttendee;
	}
	public String getRequestDesc() {
		return RequestDesc;
	}
	public void setRequestDesc(String requestDesc) {
		RequestDesc = requestDesc;
	}
	public String getApplyOrgCode() {
		return ApplyOrgCode;
	}
	public void setApplyOrgCode(String applyOrgCode) {
		ApplyOrgCode = applyOrgCode;
	}
	public String getHandler() {
		return Handler;
	}
	public void setHandler(String handler) {
		Handler = handler;
	}
	
	public String getMeetingReport() {
		return MeetingReport;
	}
	public void setMeetingReport(String meetingReport) {
		MeetingReport = meetingReport;
	}
	public String getAuditOrgCode() {
		return AuditOrgCode;
	}
	public void setAuditOrgCode(String auditOrgCode) {
		AuditOrgCode = auditOrgCode;
	}
	public String getAuditor() {
		return Auditor;
	}
	public void setAuditor(String auditor) {
		Auditor = auditor;
	}
	public String getAuditOpinion() {
		return AuditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		AuditOpinion = auditOpinion;
	}
	public String getAuditDate() {
		return AuditDate;
	}
	public void setAuditDate(String auditDate) {
		AuditDate = auditDate;
	}
	public String getAuditFlag() {
		return AuditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		AuditFlag = auditFlag;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	 public String getMeetingFlag() {
			return MeetingFlag;
		}
		public void setMeetingFlag(String meetingFlag) {
			MeetingFlag = meetingFlag;
		}

	private String MeetingNo;
    private String MeetingName;
    private String MeetingTopics;
    private String MeetingBeginDate;
    private String MeetingEndDate;
    private String MeetingRoom;
    private String NumAttendee;
    private String RequestDesc;
    private String ApplyOrgCode;
    private String Handler;
	private String MeetingFlag;
    private String MeetingReport;
    private String AuditOrgCode;
    private String Auditor;
    private String AuditOpinion;
    private String AuditDate;
    private String AuditFlag;
    private String Memo;
   

	public MeetingInfo(){
    }
   public MeetingInfo(String pk){
    	  try
  		{
    		
  			DBObject db = new DBObject();
  			String sql = "select * from COM_MEETINGINFO where MEETINGNO=? ";
  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
  			{ 
  			  new Parameter.String(pk)};
  			DataTable dt = db.runSelectQuery(sql, pp);
  			if (dt != null && dt.getRowsCount() == 1)
  			{
  				
  				DataRow r = dt.get(0);
  				this.MeetingName = r.getString("MeetingName");	
  		
  				this.MeetingBeginDate = Format.NullToBlank(r.getString("MeetingBeginDate"));
  				 if(!(r.getString("MeetingBeginDate").equals(""))&&!(r.getString("MeetingBeginDate")==null))
  					this.MeetingBeginDate = r.getString("MeetingBeginDate").substring(0,19);
  				 
  				this.MeetingEndDate = Format.NullToBlank(r.getString("MeetingEndDate"));
			 if(!(r.getString("MeetingEndDate").equals(""))&&!(r.getString("MeetingEndDate")==null))
					this.MeetingEndDate = r.getString("MeetingEndDate").substring(0,19);
 				 
 				this.MeetingRoom = r.getString("MeetingRoom");
  				this.NumAttendee = r.getString("NumAttendee");
  				this.ApplyOrgCode= r.getString("ApplyOrgCode");
  				this.RequestDesc= r.getString("RequestDesc");
  				this.AuditOpinion=r.getString("AuditOpinion");
  				//DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
  				//this.positiontime = format.format(r.getString("positiontime")) ;
  			}
  			
  		}
  		catch (Exception e)
  		{
  			e.printStackTrace();
  		}
      }
   public static String  getMeetingno()
   {
	   return BuyGoodsApp.getEventno("COM_MEETINGINFO", "MN");
   }
   public static String  getmingxino()
   {
	   return BuyGoodsApp.getEventno("COM_MEETINGINFO", "eq");
   }
    /**
	 * ����
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into COM_MEETINGINFO(meetingno,MEETINGNAME,MeetingBeginDate,MeetingEndDate,MeetingRoom,NUMATTENDEE,ApplyOrgCode,REQUESTDESC) values((MEETINGNO.NEXTVAL,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.MeetingName), 
					new Parameter.String(this.MeetingBeginDate),
					new Parameter.String(this.MeetingEndDate),
					new Parameter.String(this.MeetingRoom),
					new Parameter.String(this.NumAttendee),
					new Parameter.String(this.ApplyOrgCode),
					new Parameter.String(this.RequestDesc),
					};
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
	

	public boolean Update()
	{
		try
		{

			DBObject db = new DBObject();
			String sql = "update  COM_MEETINGINFO set MEETINGNAME=?,MeetingBeginDate=to_date(?,'yyyy-mm-dd'),MeetingEndDate=to_date(?,'yyyy-mm-dd'),MeetingRoom=?,NUMATTENDEE=?,ApplyOrgCode=?,REQUESTDESC=? where MeetingNo=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.String(this.MeetingName),
					new Parameter.String(this.MeetingBeginDate),
					new Parameter.String(this.MeetingEndDate),
					new Parameter.String(this.MeetingRoom),
					new Parameter.String(this.NumAttendee),
					new Parameter.String(this.ApplyOrgCode),
					new Parameter.String(this.RequestDesc),
					new Parameter.String(this.MeetingNo)
					
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
	public  DataTable getOrgMeetingList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||MeetingNo||'\">' as ѡ��,MEETINGNAME as ��������,to_char(MeetingBeginDate,'yyyy/mm/dd hh24:mi') as ��ʼʱ��,to_char(MeetingEndDate,'yyyy/mm/dd hh24:mi') as ����ʱ��,MeetingRoom as ����ص�,NUMATTENDEE as ��������,ApplyOrgCode as ������,REQUESTDESC as ����Ҫ�� ,AuditOpinion as ��������AUDITFLAG as ������ ��MEETINGFLAG as ���鿪���־,'<a href=\"#\" onClick=F1(\"'||MEETINGFLAG||','||MeetingNo||'\") class=\"button4\">����</a> <a href=\"#\" onClick=F6(\"'||MeetingNo||'\") class=\"button4\">ɾ������</a>'as ����   from COM_MEETINGINFO where APPLYORGCODE='"+orgcode+"'"; 

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
