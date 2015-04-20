package com.performance.dao;

import com.db.DBObject;
import com.db.DataTable;

public class TBM_IndexArchUserDao {

	/**
	 * ��ȡ�û���������ָ����ϵ����
	 * 
	 * @param orgCode
	 *            ��������
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
	 * ��ȡ��Ա��������ָ����ϵ����
	 * 
	 * @param staffCode
	 *            Ա������
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
	 * ��ȡ�û���������UniIndexCode
	 * 
	 * @param orgCode
	 *            ��������
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
	 * ��ȡ��Ա��������UniIndexCode
	 * 
	 * @param staffCode
	 *            Ա������
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
