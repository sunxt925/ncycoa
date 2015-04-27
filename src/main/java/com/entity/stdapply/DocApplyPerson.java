package com.entity.stdapply;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class DocApplyPerson {
	private int applyid=0;
	private String applystaffcode="";
	private String applyperson="";
	private String applyapart="";
	private String applydate="";
	private String applyreason="";
	private String applyorgcode="";
	private String ProcessInstanceId="";
	private String flag="0";
	private String applydoccode="";
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	private String applydocname="";
	private String filepath="";
	public String getApplydoccode() {
		return applydoccode;
	}
	public void setApplydoccode(String applydoccode) {
		this.applydoccode = applydoccode;
	}
	public String getApplydocname() {
		return applydocname;
	}
	public void setApplydocname(String applydocname) {
		this.applydocname = applydocname;
	}
	public void setApplyid(int applyid) {
		this.applyid = applyid;
	}
	public int getApplyid() {
		return applyid;
	}
	public void setApplystaffcode(String applystaffcode) {
		this.applystaffcode = applystaffcode;
	}
	public String getApplystaffcode() {
		return applystaffcode;
	}
	public void setApplyapart(String applyapart) {
		this.applyapart = applyapart;
	}
	public String getApplyapart() {
		return applyapart;
	}
	public void setApplyperson(String applyperson) {
		this.applyperson = applyperson;
	}
	public String getApplyperson() {
		return applyperson;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	public String getApplydate() {
		return applydate;
	}
	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}
	public String getApplyreason() {
		return applyreason;
	}
	public DocApplyPerson(){}
	public DocApplyPerson(int applyid){
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from STD_DOCAPPLYPERSON where applyid=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(applyid) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.applyid = applyid;
				this.applyapart = r.getString("applyapart");
				this.applyperson = r.getString("applyperson");
				this.applyreason = r.getString("applyreason");
				this.applydate=r.getString("applydate");
				this.applystaffcode = r.getString("applystaffcode");
				this.applyorgcode=r.getString("applyorgcode");
				this.ProcessInstanceId=r.getString("processinstanceid");
				this.flag=r.getString("flag");
				this.applydoccode=r.getString("applydoccode");
				this.applydocname=r.getString("applydocname");
				this.filepath=r.getString("filepath");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public DataTable check(int applyid){
		try
		{
			DBObject db = new DBObject();
			String sql = "select applyid,processinstanceid from STD_DOCAPPLYPERSON where applyid=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(applyid) };
			DataTable dt = db.runSelectQuery(sql, pp);
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public boolean Insert()
	{
		try{
			
		DBObject db = new DBObject();
		String sql = "insert into STD_DOCAPPLYPERSON(applyid,applystaffcode,applyperson,applyapart,applydate,applyreason,applyorgcode,processinstanceid,flag,applydoccode,applydocname,filepath) values (?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?)";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		   { new Parameter.Int(this.applyid),
		     new Parameter.String(this.applystaffcode),
		     new Parameter.String(this.applyperson),
		     new Parameter.String(this.applyapart),
		    new Parameter.String(this.applydate),
		     new Parameter.String(this.applyreason),
		     new Parameter.String(this.applyorgcode),
		   new Parameter.String(this.ProcessInstanceId),                              				
		   new Parameter.String(this.flag),
		   new Parameter.String(this.applydoccode),
		   new Parameter.String(this.applydocname),
		   new Parameter.String(this.filepath)
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
	}
		return false;
	}
	public boolean UpdateFlag(int applyidString)
	{
		try{
			
		DBObject db = new DBObject();
		String sql = "update STD_DOCAPPLYPERSON set flag=? where applyid=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		   { 
		   new Parameter.String("1"),
		   new Parameter.Int(applyidString)
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
		}
			return false;
		}
	public boolean UpdateFilePath(int applyidString,String filepath)
	{
		try{
			
		DBObject db = new DBObject();
		String sql = "update STD_DOCAPPLYPERSON set filepath=? where applyid=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		   { 
		   new Parameter.String(filepath),
		   new Parameter.Int(applyidString)
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
		}
			return false;
		}
	public boolean UpdateDoc(int applyidString,String doccode,String docname)
	{
		try{
			
		DBObject db = new DBObject();
		String sql = "update STD_DOCAPPLYPERSON set applydoccode=?,applydocname=? where applyid=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		   { 
		   new Parameter.String(doccode),
		   new Parameter.String(docname),
		   new Parameter.Int(applyidString)
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
		}
			return false;
		}
	public boolean Update()
	{
		try{
			
		DBObject db = new DBObject();
		String sql = "update STD_DOCAPPLYPERSON set applyid=?,applystaffcode=?,applyperson=?,applyapart=?,applydate=to_date(?,'yyyy-mm-dd'),applyreason=?,applyorgcode=?,processinstanceid=?,flag=?,applydoccode=?,applydocname=?,filepath=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		   { new Parameter.Int(this.applyid),
		     new Parameter.String(this.applystaffcode),
		     new Parameter.String(this.applyperson),
		     new Parameter.String(this.applyapart),
		    new Parameter.String(this.applydate),
		     new Parameter.String(this.applyreason),
		     new Parameter.String(this.applyorgcode),
		   new Parameter.String(this.ProcessInstanceId), 
		   new Parameter.String(this.flag),
		   new Parameter.String(this.applydoccode),
		   new Parameter.String(this.applydocname),
		   new Parameter.String(this.filepath)
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
		}
			return false;
		}
	public boolean delete(int applyid)
	{
		try{
			DBObject db = new DBObject();
			String sql = "delete from STD_DOCAPPLYPERSON where applyid=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(applyid) };
		
			
			if (db.run(sql,pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(Exception e)
		{
			return false;
		}
	}
	public void setApplyorgcode(String applyorgcode) {
		this.applyorgcode = applyorgcode;
	}
	public String getApplyorgcode() {
		return applyorgcode;
	}
	public void setProcessInstanceId(String processInstanceId) {
		ProcessInstanceId = processInstanceId;
	}
	public String getProcessInstanceId() {
		return ProcessInstanceId;
	}
	public String getAudit(String processinstanceid){
		String approString="";
		DBObject db = new DBObject();
		int id=Integer.valueOf(processinstanceid);
		String sql="select aduittime,dept,aduituser,advise from WFFORMADUIT where PROCESSINSTANCEID=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		                                              		   { new Parameter.Int(id) 
		                                              		   };
		try {
			DataTable dt = db.runSelectQuery(sql,pp);
			for(int i=0;i<dt.getRowsCount();i++){
				approString=approString+dt.get(i).get(0).toString()+" ";
				String orgcode=dt.get(i).get(1).toString();
				DataTable dt1 = db.runSelectQuery("select orgname from base_org where orgcode='"+orgcode+"'");
				approString=approString+dt1.get(0).get(0).toString()+" ";
				String aduitcode=dt.get(i).get(2).toString();
				DataTable dt2 = db.runSelectQuery("select staffname from base_staff where staffcode='"+aduitcode+"'");
				approString=approString+dt2.get(0).get(0).toString()+":";
				//System.out.println(dt.get(i).get(3));
				if(dt.get(i).get(3)==null){
					approString=approString+"      ";
				}else{
					approString=approString+dt.get(i).get(3).toString()+"      ";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return approString;
	}
	public DataTable getApplyInfo(int pageno, int perpage){
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select applyperson as 申请人,applyapart as 申请部门,to_char(applydate,'yyyy-mm-dd') as 修订日期," +
					"'<a href=\"#\" onClick=OpenTable(\"'||applyid||'\") class=\"button4\">企业标准修订申请表</a>' as 操作   from STD_DOCAPPLYPERSON where flag = '1' order by applydate desc";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllApplyInfo()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from STD_DOCAPPLYPERSON where flag = '1'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return flag;
	}
}
