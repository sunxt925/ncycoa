package com.entity.stdapply;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class DocApplySuggest {
	private String applyid;
	private String wheresug;
	private String sugstaffcode;
	private String suggestion;
	private String sugdate;
	public DocApplySuggest() {
		// TODO Auto-generated constructor stub
	}
	public void setWheresug(String wheresug) {
		this.wheresug = wheresug;
	}
	public String getWheresug() {
		return wheresug;
	}
	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}
	public String getApplyid() {
		return applyid;
	}
	public void setSugstaffcode(String sugstaffcode) {
		this.sugstaffcode = sugstaffcode;
	}
	public String getSugstaffcode() {
		return sugstaffcode;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public boolean Insert()
	{
		try{
			
		DBObject db = new DBObject();
		String sql = "insert into STD_DOCAPPLYSUG(applyid,sugstaffcode,wheresug,suggestion,sugdate) values (?,?,?,?,?)";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		   { new Parameter.String(this.applyid),
		     new Parameter.String(this.sugstaffcode),
		     new Parameter.String(this.wheresug),
		     new Parameter.String(this.suggestion),
		     new Parameter.String(this.sugdate)
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
	public DataTable getAllApplySuggest(String applyid)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from STD_DOCAPPLYSUG where applyid = '"+applyid+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public void setSugdate(String sugdate) {
		this.sugdate = sugdate;
	}
	public String getSugdate() {
		return sugdate;
	}
}
