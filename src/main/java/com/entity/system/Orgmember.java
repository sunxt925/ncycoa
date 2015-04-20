package com.entity.system;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.common.*;
import com.db.*;
import com.entity.std.DocMetaVersionInfo;
import com.entity.std.DocOrg;
public class Orgmember {
	private String OrgCode = "";
	
	public String getOrgCode() {
		return OrgCode;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	public String getStaffcode() {
		return staffcode;
	}
	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	private String staffcode = "";
	private String staffname = "";
	
	
	
	public Orgmember()
	{
		
	}
	public Orgmember(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select * from base_orgmember t where t.staffcode=?";
			//String sql = "select * from BASE_ORG where orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.staffcode = PK;
				this.OrgCode = r.getString("OrgCode");
				this.staffname = r.getString("staffname");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	


}
