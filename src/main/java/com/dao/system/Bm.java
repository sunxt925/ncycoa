/*
 * �������� 2006-8-11
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.dao.system;

import java.util.*;

import com.db.*;

/**
 * @author admin
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class Bm
{
	protected static List bm = null;// ��������

	protected static List mc = null;// ���������

	protected static List tb = null;// ���������

	public static void Init(String table_name)
	{
		try
		{
			// table_name=table_name;
			DBObject db = new DBObject();
			int sx = bm.indexOf(table_name.toUpperCase());
			if (sx > -1)
			{
				bm.remove(sx);
				mc.remove(sx);
				tb.remove(sx);
			
			}
			String sql = "select * from system_tablecodemeta where table_name=? or table_name=? or table_name=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(table_name),
					new Parameter.String(table_name.toLowerCase()),
					new Parameter.String(table_name.toUpperCase()) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				String bm_bm = r.getString("table_name");
				String bm_mc = r.getString("table_title");
				
				bm.add(bm_bm.toUpperCase());
				mc.add(bm_mc);
				String sql_bm = "";
			
				
				if(r.getString("code_class").equals("00020002")||r.getString("code_class").equals("00020003")){
					sql_bm = "select * from " + bm_bm;
				}
				else{
					sql_bm = "select * from (select * from system_tablecodemeta_col where table_name=" + "'"+bm_bm+"')"+" order by code_id";
				   
				}
					
				DataTable t = db.runSelectQuery(sql_bm);
				tb.add(t);
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void Init()
	{
		try
		{
			DBObject db = new DBObject();
			if (bm != null)
			{
				bm.clear();
			}
			else
			{
				bm = new ArrayList();
			}
			if (mc != null)
			{
				mc.clear();
			}
			else
			{
				mc = new ArrayList();
			}
			if (tb != null)
			{
				tb.clear();
			}
			else
			{
				tb = new ArrayList();
			}
			String sql = "select * from system_tablecodemeta where isload='1'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					String bm_bm = r.getString("table_name");
					String bm_mc = r.getString("table_title");
					bm.add(bm_bm.toUpperCase());
					mc.add(bm_mc);
					String sql_bm = "";
				
					if(r.getString("code_class").equals("00020002")){
						sql_bm = "select * from " + bm_bm;
					}
					else{
						sql_bm = "select * from (select * from system_tablecodemeta_col where table_name=" + "'"+bm_bm+"')"+" order by code_id";
						
					}
					DataTable t = db.runSelectQuery(sql_bm);
					tb.add(t);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return ���� bm��
	 */
	public static List getBm()
	{
		return bm;
	}

	/**
	 * @return ���� mc��
	 */
	public static List getMc()
	{
		return mc;
	}

	/**
	 * @return ���� tb��
	 */
	public static List getTb()
	{
		return tb;
	}
}