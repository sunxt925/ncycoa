package com.entity.std;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class DocPlan {
	private int id;
	private String plandate="";
	private String planname="年度计划";
	private String planpath="";
	private String uptime="";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlandate() {
		return plandate;
	}
	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}
	public String getPlanname() {
		return planname;
	}
	public void setPlanname(String planname) {
		this.planname = planname;
	}
	public DocPlan() {
	}
	public DocPlan(String plandate, String planname, String planpath,String uptime) {
		super();
		this.plandate = plandate;
		this.planname = planname;
		this.planpath = planpath;
		this.uptime = uptime;
	}
	public String getPlanpath() {
		return planpath;
	}
	public void setPlanpath(String planpath) {
		this.planpath = planpath;
	}
	
	public boolean insert(){
		try
		{
			DataTable dt=getPlanBydate(this.plandate);
			if(dt.getRowsCount()>0)
				return false;
			DBObject db = new DBObject();
			String sql = "insert into std_plan(id,plandate,planname,planpath,uptime) values(recid.nextVal,?,?,?,to_date(?,'yyyy-mm-dd'))";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.plandate) ,new Parameter.String(this.planname), new Parameter.String(this.planpath), new Parameter.String(this.uptime) };// new Parameter.Int(this.roleid)
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
	public DataTable getAllPlan(){
		try {
			DBObject db=new DBObject();
			String sql="select id,plandate,planname,planpath,uptime from std_plan order by plandate desc";
			DataTable dt=db.runSelectQuery(sql);
			return dt;
		} catch (Exception e) {
			return null;
		}
	}
	public DataTable getPlanBydate(String date){
		try {
			DBObject db=new DBObject();
			String sql="select id,plandate,planname,planpath,uptime from std_plan where plandate='"+date+"'";
			DataTable dt=db.runSelectQuery(sql);
			return dt;
		} catch (Exception e) {
			return null;
		}
	}
	public boolean deletebyID(int id){
		try
		{
			
			DBObject db = new DBObject();
			String sql="delete from std_plan where id = '" +id+"'";
			if (db.run(sql))
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
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
}
