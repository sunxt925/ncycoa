package com.entity.std;

import java.util.ArrayList;
import java.util.List;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class DocPolicy {
	private int id;
	private String editdate="";
	private String policyname="";
	private String policypath="";
	private String classtype="";
	public DocPolicy(String editdate,String policyname,String policypath,String classtype){
		this.editdate=editdate;
		this.policyname=policyname;
		this.policypath=policypath;
		this.classtype=classtype;
	}
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	public DocPolicy(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEditdate() {
		return editdate;
	}
	public void setEditdate(String editdate) {
		this.editdate = editdate;
	}
	public String getPolicyname() {
		return policyname;
	}
	public void setPolicyname(String policyname) {
		this.policyname = policyname;
	}
	public String getPolicypath() {
		return policypath;
	}
	public void setPolicypath(String policypath) {
		this.policypath = policypath;
	}
	public boolean insert(){
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into std_policy(id,editdate,policyname,policypath,classtype) values(recid.nextVal,to_date(?,'yyyy-mm-dd hh:mi'),?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.editdate) ,new Parameter.String(this.policyname), new Parameter.String(this.policypath),new Parameter.String(this.classtype) };// new Parameter.Int(this.roleid)
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
	public DataTable getAllPolicy(){
		try {
			DBObject db=new DBObject();
			String sql="select id,editdate,policyname,policypath,classtype from std_policy order by editdate desc";
			DataTable dt=db.runSelectQuery(sql);
			return dt;
		} catch (Exception e) {
			return null;
		}
	}
	public DataTable getPolicyBytype(String type){
		try {
			DBObject db=new DBObject();
			String sql="select id,editdate,policyname,policypath,classtype from std_policy where classtype='"+type+"' order by editdate desc";
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
			String sql="delete from std_policy where id = '" +id+"'";
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
}
