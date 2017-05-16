package com.entity.task;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class Task {
	
	private String taskno;  //任务编号
	private String taskname;  //任务名称
	private String taskdesc; //任务内容
	private String month; //执行月份
	private String taskorgcode; //任务机构
	private int numparticipant; //参与人数
	private String operator; //录入人
	private String inputdate;// 录入日期
	private String taskauditflag;//任务审核标志
    private String finishedflag; //任务完成时间
    private String memo; //备注

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
	public String getTaskdesc() {
		return taskdesc;
	}
	public void setTaskdesc(String taskdesc) {
		this.taskdesc = taskdesc;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getTaskorgcode() {
		return taskorgcode;
	}
	public void setTaskorgcode(String taskorgcode) {
		this.taskorgcode = taskorgcode;
	}
	public int getNumparticipant() {
		return numparticipant;
	}
	public void setNumparticipant(int numparticipant) {
		this.numparticipant = numparticipant;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	public String getTaskauditflag() {
		return taskauditflag;
	}
	public void setTaskauditflag(String taskauditflag) {
		this.taskauditflag = taskauditflag;
	}
	public String getFinishedflag() {
		return finishedflag;
	}
	public void setFinishedflag(String finishedflag) {
		this.finishedflag = finishedflag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Task(String taskno)
	{
  	  try
		{
  		
			DBObject db = new DBObject();
			String sql = "select * from MONTHTASK where TASKNO=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(taskno)
			
			};
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.taskno = taskno;
				this.taskname = r.getString("taskname");	
				this.taskdesc = r.getString("taskdesc");
				this.taskorgcode = r.getString("taskorgcode");
				this.taskauditflag = r.getString("taskauditflag");
				this.operator = r.getString("operator");
				this.numparticipant = Integer.parseInt(r.getString("numparticipant"));
				this.month = Format.NullToBlank(r.getString("month"));
				if(!(r.getString("month").equals(""))&&!(r.getString("month")==null))
					 this.month = r.getString("month").substring(0,10);
				
				this.memo = r.getString("memo");
				this.inputdate = r.getString("inputdate");
				this.finishedflag = r.getString("finishedflag");
		//		this.positiontime = Format.NullToBlank(r.getString("positiontime"));
		//		if(!(r.getString("positiontime").equals(""))&&!(r.getString("positiontime")==null))
		//			this.positiontime = r.getString("positiontime").substring(0,10);
				//DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
				//this.positiontime = format.format(r.getString("positiontime")) ;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static final DataTable getTaskList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注,TASKAUDITFLAG as 审核标识,'<a href=\"#\" onClick=mod(\"'||TASKNO||'\") class=\"button4\">修改</a> <a href=\"#\" onClick=getmember(\"'||TASKNO||'\") class=\"button4\">人员分配</a> <a href=\"#\" onClick=del(\"'||TASKNO||'\") class=\"button4\">删除</a>' as 操作   from MONTHTASK where TaskOrgCode='"+orgcode+"'"; 


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
	public static final DataTable getTaskList(int pageno, int perpage,String orgcode,String date)
	{
		try
		{
			Calendar calendar = new GregorianCalendar();
	         java.util.Date trialTime = new java.util.Date();
	         calendar.setTime(trialTime);
	         int year = calendar.get(Calendar.YEAR);
	         int month = calendar.get(Calendar.MONTH)+1;
	         int day = calendar.get(Calendar.DAY_OF_MONTH);
	        // System.out.println(date.equals("null"));
			DBObject db = new DBObject();

			String thisDate="";
			String nextDate="";
			if(date.equals(""))
			{
				thisDate="1950-01-01";
				nextDate=year+"-"+month+"-"+day;
			}
			else
			{
				thisDate= date+"-1";
				nextDate= date+"-31";
			}
			
			


			String base_sql = "select TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注,TASKAUDITFLAG as 审核标识,'<a href=\"#\" onClick=mod(\"'||TASKNO||'\") class=\"button4\">修改</a> <a href=\"#\" onClick=getmember(\"'||TASKNO||'\") class=\"button4\">人员分配</a> <a href=\"#\" onClick=del(\"'||TASKNO||'\") class=\"button4\">删除</a>' as 操作   from MONTHTASK where TaskOrgCode='"+orgcode+"' and TASKAUDITFLAG='11'and MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')"; 


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
	public static final int getTaskListCount(String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from monthtask where  TaskOrgCode='"+orgcode+"' and TASKAUDITFLAG='11'");
			return dt.getRowsCount();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public static final int getTaskListCount(String orgcode,String date)
	{
		try
		{
			Calendar calendar = new GregorianCalendar();
	         java.util.Date trialTime = new java.util.Date();
	         calendar.setTime(trialTime);
	         int year = calendar.get(Calendar.YEAR);
	         int month = calendar.get(Calendar.MONTH)+1;
	         int day = calendar.get(Calendar.DAY_OF_MONTH);
	        // System.out.println(date.equals("null"));
			DBObject db = new DBObject();

			String thisDate="";
			String nextDate="";
			if(date.equals(""))
			{
				thisDate="1950-01-01";
				nextDate=year+"-"+month+"-"+day;
			}
			else
			{
				thisDate= date+"-1";
				nextDate= date+"-31";
			}
			
			DataTable dt = db.runSelectQuery("select * from monthtask where  TaskOrgCode='"+orgcode+"' and TASKAUDITFLAG='11' and MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')");
			return dt.getRowsCount();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public static final DataTable getTaskListByDate(int pageno, int perpage,String orgcode,String date)
	{
		try
		{
			Calendar calendar = new GregorianCalendar();
	         java.util.Date trialTime = new java.util.Date();
	         calendar.setTime(trialTime);
	         int year = calendar.get(Calendar.YEAR);
	         int month = calendar.get(Calendar.MONTH)+1;
	         int day = calendar.get(Calendar.DAY_OF_MONTH);
	        // System.out.println(date.equals("null"));
			DBObject db = new DBObject();

			String thisDate="";
			String nextDate="";
			if(date.equals(""))
			{
				thisDate="1950-01-01";
				nextDate=year+"-"+month+"-"+day;
			}
			else
			{
				thisDate= date+"-1";
				nextDate= date+"-31";
			}
			
			String base_sql = "select TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注,TaskAuditFlag as 审核结果,'<a href=\"#\" onClick=mod(\"'||TASKNO||'\") class=\"button4\">修改</a> <a href=\"#\" onClick=getmember(\"'||TASKNO||'\") class=\"button4\">人员分配</a> <a href=\"#\" onClick=del(\"'||TASKNO||'\") class=\"button4\">删除</a>' as 操作   from MONTHTASK where TaskAuditFlag!='11' and TaskOrgCode='"+orgcode+"' and MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')"; 

			
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static final int getTaskListCountByDate(String orgcode,String date)
	{
		try
		{
			Calendar calendar = new GregorianCalendar();
	         java.util.Date trialTime = new java.util.Date();
	         calendar.setTime(trialTime);
	         int year = calendar.get(Calendar.YEAR);
	         int month = calendar.get(Calendar.MONTH)+1;
	         int day = calendar.get(Calendar.DAY_OF_MONTH);
	         //System.out.println(year+"-"+month+"-"+day);
			DBObject db = new DBObject();

			String thisDate="";
			String nextDate="";
			if(date.equals(""))
			{
				thisDate="1950-01-01";
				nextDate=year+"-"+month+"-"+day;
			}
			else
			{
				thisDate= date+"-1";
				nextDate= date+"-31";
			}
			DataTable dt=null;
			if(orgcode.equals(""))
			{
				dt = db.runSelectQuery("select * from monthtask where TaskAuditFlag!='11' and MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')");
			}
			else
			{
				dt = db.runSelectQuery("select * from monthtask where  TaskAuditFlag!='11' and TaskOrgCode='"+orgcode+"' and MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')");
			}
			 
			return dt.getRowsCount();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public static final int getCheckedTaskListCountByDate(String orgcode,String date)
	{
		try
		{
			Calendar calendar = new GregorianCalendar();
	         java.util.Date trialTime = new java.util.Date();
	         calendar.setTime(trialTime);
	         int year = calendar.get(Calendar.YEAR);
	         int month = calendar.get(Calendar.MONTH)+1;
	         int day = calendar.get(Calendar.DAY_OF_MONTH);
	         //System.out.println(year+"-"+month+"-"+day);
			DBObject db = new DBObject();

			String thisDate="";
			String nextDate="";
			if(date.equals(""))
			{
				thisDate="1950-01-01";
				nextDate=year+"-"+month+"-"+day;
			}
			else
			{
				thisDate= date+"-1";
				nextDate= date+"-31";
			}
			DataTable dt=null;
			if(orgcode.equals(""))
			{
				dt = db.runSelectQuery("select * from monthtask where TaskAuditFlag='0' and  MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')");
			}
			else
			{
				dt = db.runSelectQuery("select * from monthtask where TaskAuditFlag='0' and  TaskOrgCode='"+orgcode+"' and MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')");
			}
			 
			return dt.getRowsCount();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public static final DataTable getAllTaskList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||TaskNo||'\">' as 选择,TaskNo as 工作序号,TaskAuditFlag as 审核结果,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注   from MONTHTASK "; 


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
	
	public static final DataTable getAllTaskListByQuery(int pageno, int perpage,String date,String orgcode)
	{
		try
		{
			Calendar calendar = new GregorianCalendar();
	         java.util.Date trialTime = new java.util.Date();
	         calendar.setTime(trialTime);
	         int year = calendar.get(Calendar.YEAR);
	         int month = calendar.get(Calendar.MONTH)+1;
	         int day = calendar.get(Calendar.DAY_OF_MONTH);
	        // System.out.println(date.equals("null"));
			DBObject db = new DBObject();

			String thisDate="";
			String nextDate="";
			if(date.equals(""))
			{
				thisDate="1950-01-01";
				nextDate=year+"-"+month+"-"+day;
			}
			else
			{
				thisDate= date+"-1";
				nextDate= date+"-31";
			}
			
			String base_sql="";
			if(orgcode.equals(""))
			{
				base_sql= "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||TaskNo||'\">' as 选择,TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注,TaskAuditFlag as 审核结果   from MONTHTASK where  MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')"; 
			}
			else
			{
				base_sql= "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||TaskNo||'\">' as 选择,TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注  ,TaskAuditFlag as 审核结果  from MONTHTASK where TaskOrgCode='"+orgcode+"' and MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')"; 
			}


			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			//System.out.println(base_sql);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static final DataTable getModTaskList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注,TaskAuditFlag as 审核结果,'<a href=\"#\" onClick=mod(\"'||TASKNO||'\") class=\"button4\">修改</a> <a href=\"#\" onClick=audit(\"'||TASKNO||'\") class=\"button4\">审核</a> <a href=\"#\" onClick=del(\"'||TASKNO||'\") class=\"button4\">删除</a>' as 操作   from MONTHTASK "; 
			//String base_sql = "select TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注,TaskAuditFlag as 审核结果,'' as 操作   from MONTHTASK "; 

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
	
	
	public static final DataTable getModTaskListByQuery(int pageno, int perpage,String date,String orgcode)
	{
		try
		{
			Calendar calendar = new GregorianCalendar();
	         java.util.Date trialTime = new java.util.Date();
	         calendar.setTime(trialTime);
	         int year = calendar.get(Calendar.YEAR);
	         int month = calendar.get(Calendar.MONTH)+1;
	         int day = calendar.get(Calendar.DAY_OF_MONTH);
	        // System.out.println(date.equals("null"));
			DBObject db = new DBObject();

			String thisDate="";
			String nextDate="";
			if(date.equals(""))
			{
				thisDate="1950-01-01";
				nextDate=year+"-"+month+"-"+day;
			}
			else
			{
				thisDate= date+"-1";
				nextDate= date+"-31";
			}
			
			String base_sql="";
			if(orgcode.equals(""))
			{
				base_sql = "select TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注,TaskAuditFlag as 审核结果,'操作' as 操作   from MONTHTASK where TaskAuditFlag='0' and  MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')";  
			}
			else
			{
				base_sql = "select TaskNo as 工作序号,TaskName as 计划名称,TaskDesc as 计划内容,NumParticipant as 参与人数目,Memo as 备注,TaskAuditFlag as 审核结果,'操作' as 操作   from MONTHTASK where TaskAuditFlag='0' and TaskOrgCode='"+orgcode+"' and MONTH>=to_date('"+thisDate+"','yyyy-MM-dd') and MONTH<=to_date('"+nextDate+"','yyyy-MM-dd')";  
			}

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
	
	public static final int getAllTaskListCount()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from monthtask");
			return dt.getRowsCount();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public static boolean audit(String taskno,String auditflag)
	{
		try
		{

			DBObject db = new DBObject();
			String sql = "update  MONTHTASK set taskauditflag=? where taskno=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.String(auditflag),
					new Parameter.String(taskno)

					
			};
		
			if (db.run(sql,pp))
			{
				String sql0 = "update  MONTHTASKPARTICIPANT set taskauditflag=? where taskno=?";
				Parameter.SqlParameter[] pp0 = new Parameter.SqlParameter[]
				{
						new Parameter.String(auditflag),
						new Parameter.String(taskno)

						
				};
				
				if(db.run(sql0, pp0))
					return true;
				else 
					return false;
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
}
