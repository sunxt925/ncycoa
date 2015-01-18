/*
 * �������� 2006-7-18
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.dao.system;

import com.db.*;
import com.entity.system.UserInfo;
import com.common.*;

/**
 * @author ��ï
 * 
 *         TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class UnitTreeClick
{

	// �û���
	public static String getTreeNew(String unitccm, String pageurl,
			String pagetarget)
	{
		try
		{
			if (unitccm.equals(""))
				unitccm = "1151";
			String res = "";
			int pre_level = 0;
			DBObject db = new DBObject();
			String sql = "";
			sql = "select * from base_org where orgcode like '" + unitccm
					+ "%' and orgcode!='" + unitccm + "' order by orgcode";
			// System.out.print(sql);
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					String ccm = r.getString("orgcode");
					String orgclass = r.getString("orgclass");
					
					String parent_ccm = ccm.substring(0, ccm.length() - 4);
					String unit_name = r.getString("orgname");// ccm.substring(0,
					// ccm.length()
					// - 3);
					String p_isparent = "";
					String p_url = "";
					if (parent_ccm.equals(""))
					{
						parent_ccm = "0";
						p_isparent = ",isParent:true";
					}
					else
					{
						p_url = ",url:\"" + pageurl + "?unitccm=" + ccm +"&orgname="+unit_name +"&orgclass="+orgclass
								+ "\",target:\"" + pagetarget + "\"";
					}
					res = res + "{id:" + ccm + ",pId:" + parent_ccm
							+ ",name:\"" + unit_name + "\"" + p_url
							+ p_isparent + "}\r";
					if (i != dt.getRowsCount() - 1)
					{
						res = res + ",";
					}
				}

			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}