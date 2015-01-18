package com.entity.system;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class meetingattendee {
	private String MeetingNo;
	public String getMeetingNo() {
		return MeetingNo;
	}
	public void setMeetingNo(String meetingNo) {
		MeetingNo = meetingNo;
	}
	public String getAttendeeCode() {
		return AttendeeCode;
	}
	public void setAttendeeCode(String attendeeCode) {
		AttendeeCode = attendeeCode;
	}
	public String getAttendeeOrgcode() {
		return AttendeeOrgcode;
	}
	public void setAttendeeOrgcode(String attendeeOrgcode) {
		AttendeeOrgcode = attendeeOrgcode;
	}

	public String getMailInformFlag() {
		return MailInformFlag;
	}
	public void setMailInformFlag(String mailInformFlag) {
		MailInformFlag = mailInformFlag;
	}
	public String getMailInformTime() {
		return MailInformTime;
	}
	public void setMailInformTime(String mailInformTime) {
		MailInformTime = mailInformTime;
	}
	public String getMobilePhone() {
		return MobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}
	public String getPhoneInformFlag() {
		return PhoneInformFlag;
	}
	public void setPhoneInformFlag(String phoneInformFlag) {
		PhoneInformFlag = phoneInformFlag;
	}
	public String getPhoneInformDate() {
		return PhoneInformDate;
	}
	public void setPhoneInformDate(String phoneInformDate) {
		PhoneInformDate = phoneInformDate;
	}
	public String getAttendFlag() {
		return AttendFlag;
	}
	public void setAttendFlag(String attendFlag) {
		AttendFlag = attendFlag;
	}
	public String getAuditOrgcode() {
		return AuditOrgcode;
	}
	public void setAuditOrgcode(String auditOrgcode) {
		AuditOrgcode = auditOrgcode;
	}
	public String getAuditor() {
		return Auditor;
	}
	public void setAuditor(String auditor) {
		Auditor = auditor;
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
	private String AttendeeCode;
	private String AttendeeOrgcode;
	private String Email;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	private String	MailInformFlag;
	private String	MailInformTime;
	private String	MobilePhone;
	private String	PhoneInformFlag;
	private String	PhoneInformDate;
	private String	AttendFlag;
	private String	AuditOrgcode;
	private String	Auditor;
	private String	AuditDate;
	private String	AuditFlag;
	private String	Memo;
	
	public meetingattendee(){
		
	}
	
	public meetingattendee(String pk){
  	  try
		{
  		   String ds[]=pk.split(",");
			DBObject db = new DBObject();
			String sql = "select * from COM_MEETINGATTENDEE where MEETINGNO=? and AttendeeCode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ 
			  new Parameter.String(ds[0]),
			  new Parameter.String(ds[1])};
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.MeetingNo=r.getString("MEETINGNO");
				this.AttendeeCode = r.getString("AttendeeCode");	
				this.AttendeeOrgcode = r.getString("AttendeeOrgcode");
				this.Email = r.getString("Email");
				this.MobilePhone= r.getString("MobilePhone");
				this.Memo= r.getString("Memo");
				//DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
				//this.positiontime = format.format(r.getString("positiontime")) ;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
	
public int renshu(String pk) throws Exception{
	  	 
	  		   int renshu;
				DBObject db = new DBObject();
				String sql = "select * from COM_MEETINGATTENDEE where MEETINGNO=? ";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ 
				 
				  new Parameter.String(pk)};
				DataTable dt = db.runSelectQuery(sql, pp);
				renshu=dt.getRowsCount();
				
		return renshu;
	    }
		
	public boolean Insert(String BmString ,String meetingno,String orgcode)
	{
		
		try
		{
			
		Vector v = new Vector();
			DBObject db = new DBObject();
		//System.out.println(BmString);
			// SystemRole sr=new SystemRole(rolecode);
			if (BmString.indexOf(",") == -1)
			{  
				MeetingInfo MeetingInfo=new MeetingInfo(meetingno);
			//	System.out.println(this.renshu(meetingno)+"   "+MeetingInfo.getNumAttendee());
			
				if(this.renshu(meetingno)+1>Integer.parseInt(MeetingInfo.getNumAttendee())){
					return false;
				}
				else{
				StaffInfo op=new StaffInfo(BmString);
				v.add("insert into COM_MEETINGATTENDEE(ATTENDEECODE,ATTENDEEORGCODE,EMAIL,MAILINFORMFLAG,MOBILEPHONE,PHONEINFORMFLAG,AttendFlag,MEETINGNO) values('"+BmString+"','"+orgcode+"','"+op.getEmail()+"','0','"+op.getMobilephone()+"','"+0+"','"+0+"','"+meetingno+"')");
				}}
			else
			{
				String[] bm = BmString.split(",");
				MeetingInfo MeetingInfo=new MeetingInfo(meetingno);
				//System.out.println(this.renshu(meetingno)+"   "+MeetingInfo.getNumAttendee());
				if(this.renshu(meetingno)+ bm.length>Integer.parseInt(MeetingInfo.getNumAttendee())){
					return false;
				}
				else{
					
				
				for (int i = 0; i < bm.length; i++)
				{
					StaffInfo op=new StaffInfo(bm[i]);
					v.add("insert into COM_MEETINGATTENDEE(ATTENDEECODE,ATTENDEEORGCODE,EMAIL,MAILINFORMFLAG,MOBILEPHONE,PHONEINFORMFLAG,AttendFlag,MEETINGNO) values('"+bm[i]+"','"+orgcode+"','"+op.getEmail()+"','0','"+op.getMobilephone()+"','"+0+"','"+0+"','"+meetingno+"')");
			}
			}}
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
  /**
	 * 添入
	 * 
	 * @return
	 */
	/*public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into COM_MEETINGATTENDEE(meetingno,MEETINGNAME,MeetingBeginDate,MeetingEndDate,MeetingRoom,NUMATTENDEE,ApplyOrgCode,REQUESTDESC) values((MEETINGNO.NEXTVAL,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?)";
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

	
	*//**
	 * 删除
	 * 
	 * @param BmString
	 * @return
	 *//*
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
	*/

	public DataTable  getMemberList(int pageno, int perpage,String pk)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||ATTENDEECODE||'\">' as 选择,ATTENDEECODE as 参会人员,ATTENDEEORGCODE as 所在部门,EMAIL as 邮件,MOBILEPHONE as 手机号,MEMO as 备注,'<a href=\"#\" onClick=F6(\"'||MeetingNo||'\") class=\"button4\">删除</a>'as 操作   from COM_MEETINGATTENDEE where MEETINGNO='"+pk+"'"; 

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
	public DataTable  getMemberinfomList(int pageno, int perpage,String pk)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||ATTENDEECODE||'\">' as 选择,ATTENDEECODE as 参会人员,ATTENDEEORGCODE as 所在部门,EMAIL as 邮件,MOBILEPHONE as 手机号,MEMO as 备注   from COM_MEETINGATTENDEE where MEETINGNO='"+pk+"'"; 

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
	public DataTable getAllmemberList(String pk)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from COM_MEETINGATTENDEE where MEETINGNO='"+pk+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	

}
