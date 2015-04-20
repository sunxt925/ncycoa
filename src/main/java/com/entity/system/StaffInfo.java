package com.entity.system;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.performance.ReviewableObj;

public class StaffInfo implements ReviewableObj {
	
	private String staffcode;
    private String staffname;
    private String gender;
    private String idcard;
    private String birthday;
    private String marriage;
    private String nationalitycode;
    private String nationality;
    private String nativeplace;
    private String salarylevel;
    private String begincareerdate;
    private String email;
    private String qq;
    private String mobilephone;
    private String officephone;
    private String homephone;
    private String homeaddress;

	public String getType() {
		return "staff";
	}
	public void setType(String type) {
	}
	
	public String getCode() {
		return staffcode;
	}
	public void setCode(String staffcode) {
		this.staffcode = staffcode;
	}
	public String getName() {
		return staffname;
	}
	public void setName(String staffname) {
		this.staffname = staffname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getNationalitycode() {
		return nationalitycode;
	}
	public void setNationalitycode(String nationalitycode) {
		this.nationalitycode = nationalitycode;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNativeplace() {
		return nativeplace;
	}
	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}
	public String getSalarylevel() {
		return salarylevel;
	}
	public void setSalarylevel(String salarylevel) {
		this.salarylevel = salarylevel;
	}
	public String getBegincareerdate() {
		return begincareerdate;
	}
	public void setBegincareerdate(String begincareerdate) {
		this.begincareerdate = begincareerdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}
	public String getHomephone() {
		return homephone;
	}
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}
	public String getHomeaddress() {
		return homeaddress;
	}
	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}
	public StaffInfo(){
    }
    public StaffInfo(String staffcode){
    	  try
  		{
    		  
  			DBObject db = new DBObject();
  			String sql = "select * from BASE_STAFF where staffcode=?";
  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
  			{ new Parameter.String(staffcode) };
  			DataTable dt = db.runSelectQuery(sql, pp);
  			if (dt != null && dt.getRowsCount() == 1)
  			{
  				DataRow r = dt.get(0);
  				this.staffcode = r.getString("staffcode");
  				this.staffname = r.getString("staffname");
  				
  				this.birthday = Format.NullToBlank(r.getString("birthday"));
  				
  				if(!(r.getString("birthday").equals(""))&&!(r.getString("birthday")==null))
  					this.birthday = r.getString("birthday").substring(0,10);
  				
  				this.email = r.getString("email");
  				this.qq = r.getString("qq");
  			    
  				this.begincareerdate = Format.NullToBlank(r.getString("begincareerdate"));
  				if(!(r.getString("begincareerdate").equals(""))&&!(r.getString("begincareerdate")==null))
  					this.begincareerdate = r.getString("begincareerdate").substring(0,10);
  				this.gender = r.getString("gender");
  				this.idcard = r.getString("idcard");
  				this.nationality = r.getString("nationality");
  				this.nationalitycode = r.getString("nationalitycode");
  				this.nativeplace = r.getString("nativeplace");
  				this.salarylevel = r.getString("salarylevel");
  				this.officephone = r.getString("officephone");
  				this.mobilephone = r.getString("mobilephone");
  				this.homephone = r.getString("homephone");
  				this.homeaddress = r.getString("homeaddress");
  				this.marriage = r.getString("marriage");
  				
  			}
  			
  		}
  		catch (Exception e)
  		{
  			e.printStackTrace();
  		}
      }
    /**
	 * 添入
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into BASE_STAFF(staffcode,staffname,idcard,gender,birthday,nationalitycode,nationality,nativeplace,marriage,salarylevel,begincareerdate,email,qq,mobilephone,officephone,homephone,homeaddress) values(?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.staffcode), 
					new Parameter.String(this.staffname),
					new Parameter.String(this.idcard),
					new Parameter.String(this.gender),
					new Parameter.String(this.birthday),
					new Parameter.String(this.nationalitycode),
					new Parameter.String(this.nationality),
					new Parameter.String(this.nativeplace),
					new Parameter.String(this.marriage),
					new Parameter.String(this.salarylevel),
					new Parameter.String(this.begincareerdate),
					new Parameter.String(this.email),
					new Parameter.String(this.qq),
					new Parameter.String(this.mobilephone),
					new Parameter.String(this.officephone),
					new Parameter.String(this.homephone),
					new Parameter.String(this.homeaddress)
					
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
	
	/**
	 * 更新
	 * 
	 * @return
	 */
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update  BASE_STAFF set staffcode=?,staffname=?,idcard=?,gender=?,birthday=to_date(?,'yyyy-mm-dd'),nationalitycode=?,nationality=?,nativeplace=?,marriage=?,salarylevel=?,begincareerdate=to_date(?,'yyyy-mm-dd'),email=?,qq=?,mobilephone=?,officephone=?,homephone=?,homeaddress=? where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.staffcode), 
					new Parameter.String(this.staffname),
					new Parameter.String(this.idcard),
					new Parameter.String(this.gender),
					new Parameter.String(this.birthday),
					new Parameter.String(this.nationalitycode),
					new Parameter.String(this.nationality),
					new Parameter.String(this.nativeplace),
					new Parameter.String(this.marriage),
					new Parameter.String(this.salarylevel),
					new Parameter.String(this.begincareerdate),
					new Parameter.String(this.email),
					new Parameter.String(this.qq),
					new Parameter.String(this.mobilephone),
					new Parameter.String(this.officephone),
					new Parameter.String(this.homephone),
					new Parameter.String(this.homeaddress),
			        new Parameter.String(this.staffcode) 
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
	 * 删除
	 * 
	 * @param BmString
	 * @return
	 */
	public boolean Delete(String BmString)
	{
		try
		{
			Vector v = new Vector();
			DBObject db = new DBObject();
			if (BmString.indexOf(",") == -1)
			{
				v.add("delete from SYSTEM_USER_ROLE where role_id like '" + Integer.parseInt(BmString)
						+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from SYSTEM_USER_ROLE where role_id like '" +Integer.parseInt(bm[i])
							+ "%'");
				}
			}
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
	
	
	public boolean isexist(String idcard)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from BASE_STAFF where idcard=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(idcard) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
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
		return true;
	}
	
	public int isNameExist(String staffname){
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from BASE_STAFF where staffname=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(staffname) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null)
			{
			   
			   return dt.getRowsCount();
			
			}
			else
			{
				 
				 return 0;
			}
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public DataTable getAllOrgPostList(String staffcode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select base_orgmember.orgcode,base_orgposition.orgname,base_orgmember.positioncode,base_orgposition.positionname from base_orgposition INNER JOIN base_orgmember on base_orgposition.orgcode=base_orgmember.orgcode and base_orgmember.positioncode=base_orgposition.positioncode where base_orgmember.staffcode='"+staffcode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getOrgPostList(int pageno, int perpage,String staffcode)
	{
		try
		{
			DBObject db = new DBObject();


			String base_sql = "select base_orgmember.orgcode as 机构编码,base_orgposition.orgname as 机构名称,base_orgmember.positioncode as 岗位编码,base_orgposition.positionname as 岗位名称 ,'<a href=\"#\" onClick=F8(\"'||base_orgmember.positioncode||'\",\"'||base_orgposition.positionname||'\",\"'||base_orgmember.orgcode||'\") class=\"button4\">查看标准</a>' as 操作   from base_orgposition INNER JOIN base_orgmember on base_orgposition.orgcode=base_orgmember.orgcode and base_orgmember.positioncode=base_orgposition.positioncode where base_orgmember.staffcode='"+staffcode+"' order by base_orgmember.orgcode";


			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getStaffname(String staffcode) {
		DBObject db = new DBObject();
		DataTable dt;
		try {
			dt = db.runSelectQuery("select staffname from base_staff where staffcode='"+staffcode+"'");
			return dt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public DataTable getMemberList(int pageno, int perpage, String staffname) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select staffcode as 员工编码,staffname as 员工姓名,idcard as 身份证号,gender as 性别,'操作' as 操作   from base_staff where staffname='"
					+ staffname + "'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			// System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTable getAllMemberList(String staffname) {
		try {
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from base_staff where  staffname='" + staffname + "'");
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void setByIdcard(String idcard)
	{
		  try
	  		{
	    		  
	  			DBObject db = new DBObject();
	  			String sql = "select * from BASE_STAFF where idcard=?";
	  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	  			{ new Parameter.String(idcard) };
	  			DataTable dt = db.runSelectQuery(sql, pp);
	  			if (dt != null && dt.getRowsCount() == 1)
	  			{
	  				DataRow r = dt.get(0);
	  				this.staffcode = r.getString("staffcode");
	  				this.staffname = r.getString("staffname");
	  				
	  				this.birthday = Format.NullToBlank(r.getString("birthday"));
	  				
	  				if(!(r.getString("birthday").equals(""))&&!(r.getString("birthday")==null))
	  					this.birthday = r.getString("birthday").substring(0,10);
	  				
	  				this.email = r.getString("email");
	  				this.qq = r.getString("qq");
	  			    
	  				this.begincareerdate = Format.NullToBlank(r.getString("begincareerdate"));
	  				if(!(r.getString("begincareerdate").equals(""))&&!(r.getString("begincareerdate")==null))
	  					this.begincareerdate = r.getString("begincareerdate").substring(0,10);
	  				this.gender = r.getString("gender");
	  				this.idcard = r.getString("idcard");
	  				this.nationality = r.getString("nationality");
	  				this.nationalitycode = r.getString("nationalitycode");
	  				this.nativeplace = r.getString("nativeplace");
	  				this.salarylevel = r.getString("salarylevel");
	  				this.officephone = r.getString("officephone");
	  				this.mobilephone = r.getString("mobilephone");
	  				this.homephone = r.getString("homephone");
	  				this.homeaddress = r.getString("homeaddress");
	  				this.marriage = r.getString("marriage");
	  				
	  			}
	  			
	  		}
	  		catch (Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	}
	
	public void setByStaffname(String staffname){
		try
  		{
    		  
  			DBObject db = new DBObject();
  			String sql = "select * from BASE_STAFF where staffname=?";
  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
  			{ new Parameter.String(staffname) };
  			DataTable dt = db.runSelectQuery(sql, pp);
  			if (dt != null && dt.getRowsCount() == 1)
  			{
  				DataRow r = dt.get(0);
  				this.staffcode = r.getString("staffcode");
  				this.staffname = r.getString("staffname");
  				
  				this.birthday = Format.NullToBlank(r.getString("birthday"));
  				
  				if(!(r.getString("birthday").equals(""))&&!(r.getString("birthday")==null))
  					this.birthday = r.getString("birthday").substring(0,10);
  				
  				this.email = r.getString("email");
  				this.qq = r.getString("qq");
  			    
  				this.begincareerdate = Format.NullToBlank(r.getString("begincareerdate"));
  				if(!(r.getString("begincareerdate").equals(""))&&!(r.getString("begincareerdate")==null))
  					this.begincareerdate = r.getString("begincareerdate").substring(0,10);
  				this.gender = r.getString("gender");
  				this.idcard = r.getString("idcard");
  				this.nationality = r.getString("nationality");
  				this.nationalitycode = r.getString("nationalitycode");
  				this.nativeplace = r.getString("nativeplace");
  				this.salarylevel = r.getString("salarylevel");
  				this.officephone = r.getString("officephone");
  				this.mobilephone = r.getString("mobilephone");
  				this.homephone = r.getString("homephone");
  				this.homeaddress = r.getString("homeaddress");
  				this.marriage = r.getString("marriage");
  				
  			}
  			
  		}
  		catch (Exception e)
  		{
  			e.printStackTrace();
  		}
	}

}
