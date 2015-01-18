package com.performance.dao;

import com.db.DBObject;
import com.db.DataTable;

public class TBM_IndexArchUserDao {

	/**
	 * 获取该机构关联的指标体系代码
	 * 
	 * @param orgCode
	 *            机构编码
	 */
	public String getIndexArchCodeByOrgCode(String orgCode) {
		String res = "";
		try {
			DBObject db = new DBObject();
			String sql = "select indexarchcode from tbm_indexarchuser where orgcode='" + orgCode + "' and staffcode is null";
			DataTable dt = db.runSelectQuery(sql);

			if (dt != null && dt.getRowsCount() > 0) {
				res = dt.get(0).getString(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * 获取该员工关联的指标体系代码
	 * 
	 * @param staffCode
	 *            员工编码
	 */
	public String getIndexArchCodeByStaffCode(String staffCode) {
		String res = "";
		try {
			DBObject db = new DBObject();
			String sql = "select indexarchcode from tbm_indexarchuser where staffcode='" + staffCode + "'";
			DataTable dt = db.runSelectQuery(sql);

			if (dt != null && dt.getRowsCount() > 0) {
				res = dt.get(0).getString(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * 获取该机构关联的UniIndexCode
	 * 
	 * @param orgCode
	 *            机构编码
	 */
	public String getUniIndexCodeByOrgCode(String orgCode) {
		String res = "";
		try {
			DBObject db = new DBObject();
			String sql = "select uniindexcode from tbm_indexarchuser where orgcode='" + orgCode + "'";
			DataTable dt = db.runSelectQuery(sql);

			if (dt != null && dt.getRowsCount() > 0) {
				res = dt.get(0).getString(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * 获取该员工关联的UniIndexCode
	 * 
	 * @param staffCode
	 *            员工编码
	 */
	public String getUniIndexCodeByStaffCode(String staffCode) {
		String res = "";
		try {
			DBObject db = new DBObject();
			String sql = "select uniindexcode from tbm_indexarchuser where staffcode='" + staffCode + "'";
			DataTable dt = db.runSelectQuery(sql);

			if (dt != null && dt.getRowsCount() > 0) {
				res = dt.get(0).getString(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
}
