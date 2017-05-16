package com.entity.system;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class TBM_AdminDept {
	private int RECNO;
	
	public int getRECNO() {
		return RECNO;
	}
	public void setRECNO(int rECNO) {
		RECNO = rECNO;
	}
	public String getStaffCode() {
		return StaffCode;
	}
	public void setStaffCode(String staffCode) {
		StaffCode = staffCode;
	}
	public String getOrgCode() {
		return OrgCode;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	public int getAdminMode() {
		return AdminMode;
	}
	public void setAdminMode(int adminMode) {
		AdminMode = adminMode;
	}
	
	private String StaffCode;
	private String OrgCode;
	private int AdminMode;
	private String Memo;
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	/**
	 * ÌíÈë
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into tbm_admindpt(RECNO,StaffCode,OrgCode,AdminMode,Memo) values(seq_leaderDesc.NEXTVAL,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.StaffCode), 
					new Parameter.String(this.OrgCode),
					new Parameter.Int(1),
					new Parameter.String(this.Memo),
					
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
	public boolean Delete(String staffcode)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="delete  from tbm_admindpt where staffcode ='"+staffcode+"' ";
			//System.out.println(sql);
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
	public DataTable getMemberList()
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select distinct bo.staffcode ,staffname  from base_orgmember bo,tbm_admindpt tm where bo.staffcode=tm.staffcode"; 


			//String sql_run = Format.getFySql(base_sql);
			//System.out.println(sql_run);
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * ¸üÐÂ
	 * 
	 * @return
	 */
	/*public boolean Update()
	{
		try
		{

			DBObject db = new DBObject();
			String sql = "update  TBM_MeritInputTaskPerson set orgcode=?,staffcode=?,gender=?,staffname=?,idcard=?,orgname=?,positionname=?,memberid=?,positiontime=to_date(?,'yyyy-mm-dd') where staffcode=? and positioncode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.String(this.orgcode),
					new Parameter.String(this.staffcode),
					new Parameter.String(this.gender),
					new Parameter.String(this.staffname),
					new Parameter.String(this.idcard),
					new Parameter.String(this.orgname),
					new Parameter.String(this.positionname),
					new Parameter.String(this.memberid),
					new Parameter.String(this.positiontime),
					new Parameter.String(this.staffcode),
					new Parameter.String(this.positioncode)
					
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
		}*/
	
}
