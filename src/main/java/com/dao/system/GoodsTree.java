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
public class GoodsTree
{

	// ��Ʒ�����
	public static String deletechar(String str)
	{
		String newstring="";
		String[] temp=str.split("\\.");
		for(int i=0;i<temp.length;i++)
		{
			newstring+=temp[i];
		}
			
		return newstring;
	}
	public static String getTreeNew(String unitccm, String pageurl,
			String pagetarget)
	{
		try
		{
			if (unitccm.equals(""))
				unitccm = "WF";
			//System.out.println(unitccm);
			String res = "";
			int pre_level = 0;
			DBObject db = new DBObject();
			String sql = "";
			sql = "select * from Com_GoodsClass where goodscode like '" + unitccm
					+ "%' and isparent='1' order by goodscode";
			// System.out.print(sql);
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					//String goodscode="";
					String goodscode = r.getString("goodscode");
					//goodscode=deletechar(goodscodetemp);
					//String orgclass = r.gdeletecharetString("orgclass");
					
					//String parent_ccm = ccm.substring(0, ccm.length() - 4);
					//String parentgoodscode="";
					String parentgoodscode = r.getString("parentgoodscode");
					//parentgoodscode=deletechar(parentgoodscodetemp);
					String goodsname = r.getString("goodsname");// ccm.substring(0,
					// ccm.length()
					// - 3);
					String p_isparent = "";
					String p_url = "";
					/*if (parentgoodscode.equals(""))
					{
						parentgoodscode = "0";
						p_isparent = ",isParent:true";
					}*/
					//else{
						p_url = ",\"url\":\"" + pageurl + "?goodscode=" + goodscode +"&orgname="+goodsname
								+ "\",\"target\":\"" + pagetarget + "\"";
					//}
					res = res + "{\"id\":\"" + goodscode + "\",\"pId\":\"" + parentgoodscode
							+ "\",\"name\":\"" + goodsname + "\"" + p_url
							+ p_isparent + "}\r";
					if (i != dt.getRowsCount() - 1)
					{
						res = res + ",";
					}
				}

			}
			//System.out.println(res);
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}