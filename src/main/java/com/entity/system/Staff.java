package com.entity.system;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.ExecSql;
import com.db.Parameter;
import com.db.SqlHelper;

public class Staff {

	private String staffcode;
	private String staffname;
	private String positioncode;
	private String positionname;
	private String orgcode;
	private String orgname;
	private String gender;
	private String idcard;
	private String memberid;
	private String positiontime;

	public Staff() {
	}

	public Staff(String staffcode) {
		try {
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGMEMBER where staffcode=? order by orgcode";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(staffcode) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null) {
				DataRow r = dt.get(0);
				this.staffcode = staffcode;
				this.staffname = r.getString("staffname");
				this.positioncode = r.getString("positioncode");
				this.positionname = r.getString("positionname");
				this.orgcode = r.getString("orgcode");
				this.orgname = r.getString("orgname");
				this.gender = r.getString("gender");
				this.idcard = r.getString("idcard");
				this.memberid = r.getString("memberid");
				this.positiontime = Format.NullToBlank(r.getString("positiontime"));
				if (!(r.getString("positiontime").equals("")) && !(r.getString("positiontime") == null))
					this.positiontime = r.getString("positiontime").substring(0, 10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Staff(String staffcode, String positioncode) {
		try {

			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGMEMBER where staffcode=? and positioncode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(staffcode), new Parameter.String(positioncode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1) {
				DataRow r = dt.get(0);
				this.staffcode = staffcode;
				this.staffname = r.getString("staffname");
				this.positioncode = r.getString("positioncode");
				this.positionname = r.getString("positionname");
				this.orgcode = r.getString("orgcode");
				this.orgname = r.getString("orgname");
				this.gender = r.getString("gender");
				this.idcard = r.getString("idcard");
				this.memberid = r.getString("memberid");
				this.positiontime = Format.NullToBlank(r.getString("positiontime"));
				if (!(r.getString("positiontime").equals("")) && !(r.getString("positiontime") == null))
					this.positiontime = r.getString("positiontime").substring(0, 10);
				// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				// this.positiontime =
				// format.format(r.getString("positiontime")) ;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添入
	 * 
	 * @return
	 */
	public boolean Insert() {
		try {
			DBObject db = new DBObject();
			String sql = "insert into BASE_ORGMEMBER(positioncode,orgcode,staffcode,gender,staffname,idcard,orgname,positionname,memberid,positiontime) values(?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'))";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(this.positioncode), new Parameter.String(this.orgcode),
					new Parameter.String(this.staffcode), new Parameter.String(this.gender), new Parameter.String(this.staffname),
					new Parameter.String(this.idcard), new Parameter.String(this.orgname), new Parameter.String(this.positionname),
					new Parameter.String(this.memberid), new Parameter.String(this.positiontime) };
			if (db.run(sql, pp)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public boolean Update() {
		try {

			DBObject db = new DBObject();
			String sql = "update  BASE_ORGMEMBER set orgcode=?,staffcode=?,gender=?,staffname=?,idcard=?,orgname=?,positionname=?,memberid=?,positiontime=to_date(?,'yyyy-mm-dd') where staffcode=? and positioncode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(this.orgcode), new Parameter.String(this.staffcode),
					new Parameter.String(this.gender), new Parameter.String(this.staffname), new Parameter.String(this.idcard),
					new Parameter.String(this.orgname), new Parameter.String(this.positionname), new Parameter.String(this.memberid),
					new Parameter.String(this.positiontime), new Parameter.String(this.staffcode), new Parameter.String(this.positioncode)

			};

			if (db.run(sql, pp)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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

	public String getPositioncode() {
		return positioncode;
	}

	public void setPositioncode(String positioncode) {
		this.positioncode = positioncode;
	}

	public String getPositionname() {
		return positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
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

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getPositiontime() {
		return positiontime;
	}

	public void setPositiontime(String positiontime) {
		this.positiontime = positiontime;
	}

	/**
	 * 删除
	 * 
	 * @param BmString
	 * @return
	 */
	public boolean Delete(String staffcode, String positioncode) {
		return Delete(staffcode, positioncode,"");
	}
	public boolean Delete(String staffcode, String positioncode,String orgcode){
		try {

			DBObject db = new DBObject();
			String sql="";
			if(!orgcode.equals("")){
				sql = "delete from BASE_ORGMEMBER where staffcode=? and positioncode=? and orgcode='"+orgcode+"'";
			}else{
				sql = "delete from BASE_ORGMEMBER where staffcode=? and positioncode=?";
			}
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(staffcode), new Parameter.String(positioncode)

			};

			if (db.run(sql, pp)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public DataTable getAllOrgPostList(String staffcode) {
		try {
			DBObject db = new DBObject();
			DataTable dt = db
					.runSelectQuery("select base_orgmember.orgcode,base_orgposition.orgname,base_orgmember.positioncode,base_orgposition.positionname from base_orgposition INNER JOIN base_orgmember on base_orgposition.orgcode=base_orgmember.orgcode and base_orgmember.positioncode=base_orgposition.positioncode where base_orgmember.staffcode='"
							+ staffcode + "'");
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getAllOrgByStaffCode(String staffcode) {
		try {
			DBObject db = new DBObject();

			DataTable dt = db.runSelectQuery("select distinct orgcode,orgname from base_orgmember where staffcode='" + staffcode + "'");
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getMemberList(int pageno, int perpage, String positioncode,String orgcode) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"radio\" id=\"items\" name=\"items\" value=\"'||staffcode||'\">' as 选择,staffcode as 员工编码,staffname as 员工姓名,idcard as 身份证号,gender as 性别,memberid as 证件号码,to_char(positiontime,'yyyy-mm-dd') as 入职时间,'操作' as 操作   from base_orgmember where positioncode='"
					+ positioncode + "' and orgcode='"+orgcode+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			// System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getRoleMemberList(int pageno, int perpage, String positioncode,String orgcode) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||staffcode||'\">' as 选择,staffcode as 员工编码,staffname as 员工姓名,idcard as 身份证号,gender as 性别,memberid as 证件号码,to_char(positiontime,'yyyy-mm-dd') as 入职时间   from base_orgmember where positioncode='"
					+ positioncode + "'  and orgcode='"+orgcode+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getRoleMemberListAddName(int pageno, int perpage, String positioncode,String orgcode) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||staffcode||','||staffname||'\">' as 选择,staffcode as 员工编码,staffname as 员工姓名,idcard as 身份证号,gender as 性别,memberid as 证件号码,to_char(positiontime,'yyyy-mm-dd') as 入职时间   from base_orgmember where positioncode='"
					+ positioncode + "' and orgcode='"+orgcode+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getAllMemberList(String positioncode,String orgcode) {
		try {
			DBObject db = new DBObject();
			String sql="";
			if(positioncode==null||positioncode.equals("null")||positioncode.equals("")){
				sql = "select * from base_orgmember where  orgcode='"+orgcode+"'";
			}else{
				sql = "select * from base_orgmember where  positioncode='" + positioncode + "' and orgcode='"+orgcode+"'";
			}
			DataTable dt = db.runSelectQuery(sql);
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getAllMemberListByorg(String org) {
		try {
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from base_orgmember where  orgcode='" + org + "'");
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getBlongMemberList(int pageno, int perpage, String orgcode) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select 'XX' as 选择,staffcode as 员工编码,staffname as 员工姓名,idcard as 身份证号,gender as 性别   from base_orgmember where orgcode='"
					+ orgcode + "'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getMemberListByorg(int pageno, int perpage, String orgcode) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||b.staffcode||'\">' as 选择,b.staffcode as 员工编码,b.staffname as 员工姓名,s.MOBILEPHONE as 电话,s.EMAIL as 邮箱  from base_orgmember b,base_staff s where orgcode='"
					+ orgcode + "'and b.staffcode=s.staffcode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getAllBlongMemberList(int pageno, int perpage, String orgcode) {
		try {
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from base_orgmember where orgcode='" + orgcode + "'");
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean Choose(String param, String orgcode, String orgname, String positioncode, String positionname) {

		ExecSql comm = null;

		String staffcode = "";
		String staffname = "";
		String gender = "";
		String idcard = "";
		try {

			String sql = "insert into base_orgmember(orgcode,orgname,positioncode,positionname,idcard,staffcode,staffname,gender) values(?,?,?,?,?,?,?,?)";
			if (param.indexOf(",") == -1) {

				DBObject db = new DBObject();
				String sql0 = "select * from BASE_ORGMEMBER where staffcode=?";
				Parameter.SqlParameter[] pp0 = new Parameter.SqlParameter[] { new Parameter.String(param) };
				DataTable dt = db.runSelectQuery(sql0, pp0);
				if (dt != null) {
					DataRow r = dt.get(0);

					staffcode = r.getString("staffcode");
					staffname = r.getString("staffname");
					gender = r.getString("gender");
					idcard = r.getString("idcard");

				}

				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(orgcode), new Parameter.String(orgname),
						new Parameter.String(positioncode), new Parameter.String(positionname), new Parameter.String(idcard), new Parameter.String(staffcode),
						new Parameter.String(staffname),

						new Parameter.String(gender) };
				comm = SqlHelper.helper().createCommand(sql, pp);
				comm.beginTransaction();
				comm.execute();
			} else {
				String[] ids = param.split(",");

				DBObject db = new DBObject();
				String sql0 = "select * from BASE_ORGMEMBER where staffcode=?";
				Parameter.SqlParameter[] pp0 = new Parameter.SqlParameter[] { new Parameter.String(ids[0]) };
				DataTable dt = db.runSelectQuery(sql0, pp0);
				if (dt != null) {
					DataRow r = dt.get(0);

					staffcode = r.getString("staffcode");
					staffname = r.getString("staffname");
					gender = r.getString("gender");
					idcard = r.getString("idcard");

				}

				Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[] { new Parameter.String(orgcode), new Parameter.String(orgname),
						new Parameter.String(positioncode), new Parameter.String(positionname), new Parameter.String(idcard), new Parameter.String(staffcode),
						new Parameter.String(staffname),

						new Parameter.String(gender) };

				comm = SqlHelper.helper().createCommand(sql, pp1);
				comm.beginTransaction();
				comm.execute();
				for (int i = 1; i < ids.length; i++) {

					DBObject db0 = new DBObject();
					// String sql1 =
					// "select * from BASE_ORGMEMBER where staffcode=?";
					Parameter.SqlParameter[] pp3 = new Parameter.SqlParameter[] { new Parameter.String(ids[i]) };
					DataTable dt0 = db0.runSelectQuery(sql0, pp3);
					if (dt0 != null) {
						DataRow r = dt0.get(0);

						staffcode = r.getString("staffcode");
						staffname = r.getString("staffname");
						gender = r.getString("gender");
						idcard = r.getString("idcard");

					}
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[] { new Parameter.String(orgcode), new Parameter.String(orgname),
							new Parameter.String(positioncode), new Parameter.String(positionname), new Parameter.String(idcard),
							new Parameter.String(staffcode), new Parameter.String(staffname),

							new Parameter.String(gender) };
					comm.setCommand(sql, pp2);
					comm.execute();
				}
			}
			comm.commit();
			return true;
		} catch (Exception e) {
			try {
				if (comm != null) {
					comm.rollback();
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (comm != null) {
				comm.Dispose();
			}
		}
	}

}
