package com.entity.task;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class Participant {
	
	private String taskno;//�������
	private String taskname; //��������
	private String participantcode; //������
	private String orgcode;  //���������ڲ���
	private String taskdesc; //����������˵��
	private String finisheddate;  //ʵ�����ʱ��
	private String finisheddesc;  //ʵ��������
	private String finishedreport; //��ɱ���
	private String finishedflag;  //��ɱ�־
	private String quality;  //�������
	private String auditor;  //�����
	private String auditdate;  //���ʱ��
	private String memo;  //��ע
	
	
	public Participant(String taskno,String participantcode)
	{
  	  try
		{
  		
			DBObject db = new DBObject();
			String sql = "select * from MONTHTASKPARTICIPANT where TASKNO=? and PARTICIPANTCODE=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(taskno),
			  new Parameter.String(participantcode)	
			
			
			};
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.taskno = taskno;
				this.taskname = r.getString("taskname");	
				this.taskdesc = r.getString("taskdesc");
				this.quality = r.getString("quality");
				this.participantcode = participantcode;
				this.orgcode = r.getString("orgcode");
				this.memo = r.getString("memo");
				
				this.finisheddate = Format.NullToBlank(r.getString("finisheddate"));
				if(!(r.getString("finisheddate").equals(""))&&!(r.getString("finisheddate")==null))
					 this.finisheddate = r.getString("finisheddate").substring(0,10);
				
				this.finishedreport = r.getString("finishedreport");
				this.finisheddesc = r.getString("finisheddesc");
				this.finishedflag = r.getString("finishedflag");
				
				this.auditor = r.getString("auditor");
				this.auditdate = Format.NullToBlank(r.getString("auditdate"));
				if(!(r.getString("auditdate").equals(""))&&!(r.getString("auditdate")==null))
					 this.auditdate = r.getString("auditdate").substring(0,10);
				
				

			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	public String getTaskno() {
		return taskno;
	}
	public void setTaskno(String taskno) {
		this.taskno = taskno;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getParticipantcode() {
		return participantcode;
	}
	public void setParticipantcode(String participantcode) {
		this.participantcode = participantcode;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getTaskdesc() {
		return taskdesc;
	}
	public void setTaskdesc(String taskdesc) {
		this.taskdesc = taskdesc;
	}
	public String getFinisheddate() {
		return finisheddate;
	}
	public void setFinisheddate(String finisheddate) {
		this.finisheddate = finisheddate;
	}
	public String getFinisheddesc() {
		return finisheddesc;
	}
	public void setFinisheddesc(String finisheddesc) {
		this.finisheddesc = finisheddesc;
	}
	public String getFinishedreport() {
		return finishedreport;
	}
	public void setFinishedreport(String finishedreport) {
		this.finishedreport = finishedreport;
	}
	public String getFinishedflag() {
		return finishedflag;
	}
	public void setFinishedflag(String finishedflag) {
		this.finishedflag = finishedflag;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public static final DataTable getParticipantList(int pageno, int perpage,String taskno)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select ParticipantCode as ������,TaskDesc as �������,FINISHEDDATE as ���ʱ��,Memo as ��ע,'<a href=\"#\" onClick=gettask(\"'||TASKNO||'\",\"'||ParticipantCode||'\") class=\"button4\">��������</a> <a href=\"#\" onClick=deleteparticipant(\"'||TASKNO||'\",\"'||ParticipantCode||'\") class=\"button4\">ɾ��</a>' as ����   from MONTHTASKPARTICIPANT where TASKNO='"+taskno+"'"; 


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
	public static final int getAllParticipantList(String taskno)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from MONTHTASKPARTICIPANT where  taskno='"+taskno+"'");
			return dt.getRowsCount();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public static final DataTable getMyTaskList(int pageno, int perpage,String participantcode)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select TASKNO as ��������,TaskDesc as �������,FINISHEDDATE as ���ʱ��,Memo as ��ע,FinishedDesc as ������,'<a href=\"#\" onClick=taskreport(\"'||TASKNO||'\") class=\"button4\">����걨</a>  <a href=\"#\" onClick=postreport(\"'||TASKNO||'\",\"'||ParticipantCode||'\") class=\"button4\">ȷ���ύ</a> ' as ����   from MONTHTASKPARTICIPANT where PARTICIPANTCODE='"+participantcode+"' and TASKAUDITFLAG='11'"; 


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
	
	public static final DataTable getMyTaskListByDate(int pageno, int perpage,String participantcode,String date)
	{
		try
		{
			String thisDate = date+"-1";
			String nextDate = date+"-28";
			
			DBObject db = new DBObject();
			
		


			String base_sql = "select TASKNO as ��������,TaskDesc as �������,FINISHEDDATE as ���ʱ��,Memo as ��ע,FinishedDesc as ������,'<a href=\"#\" onClick=taskreport(\"'||TASKNO||'\") class=\"button4\">����걨</a>  <a href=\"#\" onClick=postreport(\"'||TASKNO||'\",\"'||ParticipantCode||'\") class=\"button4\">ȷ���ύ</a> ' as ����   from MONTHTASKPARTICIPANT where PARTICIPANTCODE='"+participantcode+"' and "; 


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
	
	public static final DataTable getAuditTaskList(int pageno, int perpage,String orgcode,String taskno)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select PARTICIPANTCODE as Ա������,TASKNO as ��������,TaskDesc as �������,FINISHEDDATE as ���ʱ��,Memo as ��ע,FinishedDesc as ������,Quality as ������,' <a href=\"#\" onClick=audittask(\"'||TASKNO||'\",\"'||ParticipantCode||'\") class=\"button4\">���</a> <a href=\"#\" onClick=uploadfile(\"'||FinishedReport||'\") class=\"button4\">����ļ�</a>' as ����   from MONTHTASKPARTICIPANT where ORGCODE='"+orgcode+"' and TASKNO='"+taskno+"'"; 


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
	
	public static final DataTable getAuditTaskListByDate(int pageno, int perpage,String orgcode,String date)
	{
		try
		{
			String thisDate = date+"-1";
			String nextDate = date+"-28";
			
			DBObject db = new DBObject();


			String base_sql = "select TASKNO as ��������,TaskDesc as �������,FINISHEDDATE as ���ʱ��,Memo as ��ע,FinishedDesc as ������,Quality as ������,' <a href=\"#\" onClick=audittask(\"'||TASKNO||'\",\"'||ParticipantCode||'\") class=\"button4\">���</a> <a href=\"#\" onClick=uploadfile(\"'||FinishedReport||'\") class=\"button4\">����ļ�</a>' as ����   from MONTHTASKPARTICIPANT where ORGCODE='"+orgcode+"' and FINISHEDFLAG='11' and FINISHEDDATE>to_date('"+thisDate+"','yyyy-MM-dd') and FINISHEDDATE<to_date('"+nextDate+"','yyyy-MM-dd')"; 


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
	
	public static final int getAllAuditList(String orgcode,String taskno)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from MONTHTASKPARTICIPANT where  ORGCODE='"+orgcode+"' and TASKNO='"+taskno+"'");
			return dt.getRowsCount();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public static final int getAllAuditListByDate(String orgcode,String date)
	{
		try
		{
			String thisDate = date+"-1";
			String nextDate = date+"-28";
			
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from MONTHTASKPARTICIPANT where  ORGCODE='"+orgcode+"' and FINISHEDDATE>to_date('"+thisDate+"','yyyy-MM-dd') and FINISHEDDATE<to_date('"+nextDate+"','yyyy-MM-dd')");
			return dt.getRowsCount();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}


