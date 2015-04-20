/*
 * �������� 2006-7-18
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.common;

import com.dao.system.Bm;
import com.dao.system.LoadCode;
import com.db.*;

/**
 * @author ��ï
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class Coder
{
	public static String getSelectItem(String CodeName)
	{
		return getSelectItem(CodeName, "");
	}

	// ����getSelectItemȡ���ص���ҳ��Select����
	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param ItemName
	 *            ��ҳ�ؼ���
	 * @return
	 */
	public static String getSelectItem(String CodeName, String ItemName)
	{
		return getSelectItem(CodeName, ItemName, "");
	}

	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param ItemName
	 *            ��ҳ�ؼ���
	 * @param DefaultValue
	 *            Ĭ��ֵ
	 * @return
	 */
	public static String getSelectItem(String CodeName, String ItemName,
			String DefaultValue)
	{
		return getSelectItem(CodeName, ItemName, DefaultValue, true);
	}

	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param ItemName
	 *            ��ҳ�ؼ���
	 * @param DefaultValue
	 *            Ĭ��ֵ
	 * @param NullList
	 *            �Ƿ���ʾ��==��ѡ��==
	 * @return
	 */
	public static String getSelectItem(String CodeName, String ItemName,
			String DefaultValue, boolean NullList)
	{
		return getSelectItem(CodeName, ItemName, DefaultValue, NullList, null,
				null, null, false);
	}

	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param ItemName
	 *            ��ҳ�ؼ���
	 * @param DefaultValue
	 *            Ĭ��ֵ
	 * @param NullList
	 *            �Ƿ���ʾ��==��ѡ��==
	 * @param BmField
	 *            �����ֶ���
	 * @param ValueField
	 *            ֵ�ֶ���
	 * @param YxjField
	 *            ���ȼ��������ֶ���
	 * @return
	 */
	public static String getSelectItem(String CodeName, String ItemName,
			String DefaultValue, boolean NullList, String BmField,
			String ValueField, String YxjField, boolean ViewTitle)
	{
		String res = "";
		// DBObject db = new DBObject();
		if (BmField == null)
		{
			BmField = "bm";
		}
		if (ValueField == null)
		{
			ValueField = "bmnr";
		}
		if (YxjField == null)
		{
			YxjField = "yxj";
		}
		if (ItemName == null)
		{
			ItemName = CodeName;
		}
		try
		{
			// String sql = "select " + BmField + "," + ValueField + " from " +
			// CodeName + " order by " + YxjField + "";
			if (ViewTitle)
			{
				res = res + "<select name='" + ItemName + "' id='" + ItemName
						+ "'>";
			}
			if (NullList)
			{
				res = res + "<option value=''>==��ѡ��==</option>";
			}
			// DataTable dt = db.runSelectQuery(sql);
			DataTable dt = Coder.getTable(CodeName);
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					res = res + "<option value='" + r.getString(BmField) + "'";
					if (r.getString(BmField).equals(DefaultValue))
					{
						res = res + " selected";
					}
					res = res + ">" + r.getString(ValueField) + "</option>";
				}
			}
			if (ViewTitle)
			{
				res = res + "</select>";
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param jbm
	 *            �ֱ���
	 * @return
	 */
	public static String getSelectItemByJbm(String CodeName, String jbm)
	{
		return getSelectItemByJbm(CodeName, "", jbm);
	}

	// ����getSelectItemȡ���ص���ҳ��Select����
	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param ItemName
	 *            ��ҳ�ؼ���
	 * @param jbm
	 *            �ֱ���
	 * @return
	 */
	public static String getSelectItemByJbm(String CodeName, String ItemName,
			String jbm)
	{
		return getSelectItemByJbm(CodeName, ItemName, "", jbm);
	}

	public static String getSelectItemByJbm(String CodeName, String ItemName,
			String DefaultValue, String jbm, boolean ViewTitle)
	{
		return getSelectItemByJbm(CodeName, ItemName, DefaultValue, true, null,
				null, null, ViewTitle, jbm);
	}

	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param ItemName
	 *            ��ҳ�ؼ���
	 * @param DefaultValue
	 *            Ĭ��ֵ
	 * @param jbm
	 *            �ֱ���
	 * @return
	 */
	public static String getSelectItemByJbm(String CodeName, String ItemName,
			String DefaultValue, String jbm)
	{
		return getSelectItemByJbm(CodeName, ItemName, DefaultValue, true, jbm);
	}

	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param ItemName
	 *            ��ҳ�ؼ���
	 * @param DefaultValue
	 *            Ĭ��ֵ
	 * @param NullList
	 *            �Ƿ���ʾ��==��ѡ��==
	 * @param jbm
	 *            �ֱ���
	 * @return
	 */
	public static String getSelectItemByJbm(String CodeName, String ItemName,
			String DefaultValue, boolean NullList, String jbm)
	{
		return getSelectItemByJbm(CodeName, ItemName, DefaultValue, NullList,
				null, null, null, false, jbm);
	}

	/**
	 * 
	 * @param CodeName
	 *            �������
	 * @param ItemName
	 *            ��ҳ�ؼ���
	 * @param DefaultValue
	 *            Ĭ��ֵ
	 * @param NullList
	 *            �Ƿ���ʾ��==��ѡ��==
	 * @param BmField
	 *            �����ֶ���
	 * @param ValueField
	 *            ֵ�ֶ���
	 * @param YxjField
	 *            ���ȼ��������ֶ���
	 * @param jbm
	 *            �ֱ���
	 * @return
	 */
	public static String getSelectItemByJbm(String CodeName, String ItemName,
			String DefaultValue, boolean NullList, String BmField,
			String ValueField, String YxjField, boolean ViewTitle, String jbm)
	{
		String res = "";
		// DBObject db = new DBObject();
		if (BmField == null)
		{
			BmField = "bm";
		}
		if (ValueField == null)
		{
			ValueField = "bmnr";
		}
		if (YxjField == null)
		{
			YxjField = "yxj";
		}
		if (ItemName == null)
		{
			ItemName = CodeName;
		}
		try
		{
			// String sql = "select " + BmField + "," + ValueField + " from " +
			// CodeName + " where jbm='" + jbm + "' order by " + YxjField + "";
			if (ViewTitle)
			{
				res = res + "<select name='" + ItemName + "' id='" + ItemName
						+ "'>";
			}
			if (NullList)
			{
				res = res + "<option value=''>==��ѡ��==</option>";
			}
			// DataTable dt = db.runSelectQuery(sql);
			String param = "jbm=" + jbm;
			DataTable dt = Coder.RebuildTable(Coder.getTable(CodeName), param);
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					res = res + "<option value='" + r.getString(BmField) + "'";
					if (r.getString(BmField).equals(DefaultValue))
					{
						res = res + " selected";
					}
					res = res + ">" + r.getString(ValueField) + "</option>";
				}
			}
			if (ViewTitle)
			{
				res = res + "</select>";
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	// ȡradio�Ĳ������б�
	public static String getBoolealRadioItem(String ItemName,
			String DefaultValue)
	{
		if (DefaultValue == null)
		{
			DefaultValue = "";
		}
		if (DefaultValue.equals(""))
		{
			DefaultValue = "1";
		}
		String res = "<input type='radio' name='" + ItemName + "' value='1'";
		if (DefaultValue.equals("1"))
		{
			res = res + " checked='checked'";
		}
		res = res
				+ " onKeyDown='EnterKeyDo(\"\")'>�ǡ�<input type='radio' name='"
				+ ItemName + "' value='0'";
		if (DefaultValue.equals("0"))
		{
			res = res + " checked='checked'";
		}
		res = res + " onKeyDown='EnterKeyDo(\"\")'>��";
		return res;
	}

	// ��ö��ֵ���㣬value��ʾ��ǰֵ��defineʾ����"1,ȫ��;2,�־�"
	public static String getEnumValue(String value, String define)
	{
		try
		{
			String res = "";
			String[] list = define.split(";");
			for (int i = 0; i < list.length; i++)
			{
				String[] list1 = list[i].split(",");
				if (list1[0].equals(value))
				{
					res = list1[1];
				}
			}
			if (res.equals(""))
			{
				res = value;
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	// �򵥵�booleanֵ(0,1��ʾ)ת��������
	public static String getBooleanValue(String value)
	{
		if (value == null)
		{
			return "";
		}
		else if (value.equals("0"))
		{
			return "��";
		}
		else if (value.equals("1"))
		{
			return "��";
		}
		else
		{
			return "";
		}
	}

	// �򵥵�booleanֵ��0,1��ʾ��ת����boolean��
	public static boolean getBoolean(String value)
	{
		if (value != null && value.equals("1"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// ��booleanת����0,1��ʾ
	public static String getBooleanNum(boolean var)
	{
		if (var)
		{
			return "1";
		}
		else
		{
			return "0";
		}
	}

	// ���ַ�������select�ؼ���str������ʽ{val1,title1;val2,title2}
	public static String getSelectFromString(String str, String ItemName,
			String DefaultValue)
	{
		try
		{
			DefaultValue = Format.NullToBlank(DefaultValue);
			String res = "";
			if (str.indexOf(";") > -1)
			{
				String[] l = str.split(";");
				if (l.length > 1)
				{
					res = res + "<select name='" + ItemName + "'>";
					for (int i = 0; i < l.length; i++)
					{
						String[] ll = l[i].split(",");
						if (ll[0].equals(DefaultValue))
						{
							res = res + "<option value='" + ll[0]
									+ "' selected>" + ll[1] + "</option>";
						}
						else
						{
							res = res + "<option value='" + ll[0] + "'>"
									+ ll[1] + "</option>";
						}
					}
					res = res + "</select>";
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

	public static String getSelectFromStringWithNull(String str,
			String ItemName, String DefaultValue)
	{
		try
		{
			DefaultValue = Format.NullToBlank(DefaultValue);
			String res = "";
			if (str.indexOf(";") > -1)
			{
				String[] l = str.split(";");
				if (l.length > 1)
				{
					res = res + "<select name='" + ItemName + "'>";
					res = res + "<option value=''>=��ѡ��=</option>";
					for (int i = 0; i < l.length; i++)
					{
						String[] ll = l[i].split(",");
						if (ll[0].equals(DefaultValue))
						{
							res = res + "<option value='" + ll[0]
									+ "' selected>" + ll[1] + "</option>";
						}
						else
						{
							res = res + "<option value='" + ll[0] + "'>"
									+ ll[1] + "</option>";
						}
					}
					res = res + "</select>";
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

	// �ɲ��������α�˵�
	public static String getCdfromccm(String ccm)
	{
		try
		{
			ccm = Format.NullToBlank(ccm);
			String res = "";
			int level = (int) Math.floor(ccm.length() / 3);
			for (int i = 0; i < level; i++)
			{
				res = res + "|--";
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	// ȡ��sequence��ID
	public static int getId(String SequenceName)
	{
		try
		{
			int res = 0;
			DBObject db = new DBObject();
			String sql = "select " + SequenceName + ".nextval as seq from dual";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				res = Integer.parseInt(r.getString("seq"));
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	// ȡ�ñ�������ݣ��ڴ��ȡ

	public static String getBmVal(String table_name, String code)
	{
		return getBmVal(table_name, code, null);
	}

	public static String getBmVal(String table_name, String code,
			String col_name)
	{
		try
		{
			// List bm = Bm.bm;
			// List tb = Bm.tb;
			String res = "";
			int bmsx = Bm.getBm().indexOf(table_name.toUpperCase());

			if (bmsx > -1)
			{
				DataTable dt = (DataTable) Bm.getTb().get(bmsx);
				String[] col = dt.getColumnNames();
				if (col_name == null)
				{
					col_name = "bmnr";
				}
				boolean aaa = false;
				// ����Ƿ���ڸ���
				for (int j = 0; j < col.length; j++)
				{
					if (col_name.equals(col[j]))
					{
						aaa = true;
					}
				}
				if (aaa)
				{
					for (int i = 0; i < dt.getRowsCount(); i++)
					{
						DataRow r = dt.get(i);
						// System.out.println(r.getString("bm"));
						if (r.getString("bm").trim().equals(code.trim()))
						{
							res = r.getString(col_name);
						}
					}
				}
			}
			// System.out.print("=="+table_name+"="+code);
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public static String getBmVal(String table_name, String col_name,
			String col_val_name, String code)
	{
		try
		{
			// List bm = Bm.bm;
			// List tb = Bm.tb;
			String res = "";
			int bmsx = Bm.getBm().indexOf(table_name.toUpperCase().trim());
			// int bmsx = Bm.getBm().indexOf("T_DM_GY_SWJG");
			// System.out.println(bmsx);
			// for (int x=0;x<Bm.getBm().size();x++)
			// {
			// System.out.println(Bm.getBm().get(x));
			// }
			if (bmsx > -1)
			{
				DataTable dt = (DataTable) Bm.getTb().get(bmsx);
				String[] col = dt.getColumnNames();
				boolean aaa = false;
				boolean bbb = false;
				// ����Ƿ���ڸ���
				for (int j = 0; j < col.length; j++)
				{
					if (col_name.toLowerCase().trim().equals(col[j].trim()))
					{
						aaa = true;
					}
					if (col_val_name.toLowerCase().trim().equals(col[j].trim()))
					{
						bbb = true;
					}
					// System.out.println(col_val_name.toLowerCase().trim());
				}
				// System.out.println(aaa);
				// System.out.println(bbb);
				// System.out.print(col_name);
				if (aaa && bbb)
				{
					for (int i = 0; i < dt.getRowsCount(); i++)
					{
						DataRow r = dt.get(i);
						// System.out.println(r.getString(col_name).trim());
						if (r.getString(col_name).trim().equals(code.trim()))
						{
							res = r.getString(col_val_name);
						}
					}
				}
			}
			else
			{
				res = code;
			}
			// System.out.print("=="+table_name+"="+code);
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public static DataTable getTable(String table_name)
	{
		try
		{
			// List bm = Bm.bm;
			// List tb = Bm.tb;
			int bmsx = Bm.getBm().indexOf(table_name.toUpperCase());
			if (bmsx > -1)
			{
				DataTable dt = (DataTable) Bm.getTb().get(bmsx);
				// System.out.print("========================="+dt.getRowsCount());
				return dt;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	// ���ڴ��еı��봫����������datatable���¹���,����datatable
	// ˵��������Ŀǰֻ����ȫ���ȫ������ͣ����ʽ֧��'!=','='
	// ȫ�����'&'����������ȫ�����'|'��������
	// ����1��"jbm=00&fzbm=01"
	// ����1: "jbm!=01|fzbm!=02"
	public static DataTable RebuildTable(DataTable dt, String tj)
	{
		// return dt;
		// /*
		try
		{
			DataTable dt_new = new DataTable(String.valueOf(System
					.currentTimeMillis()));
			dt_new.set_columns(dt.get_columns());
			dt_new.setTotalrowcount(dt.getTotalrowcount());
			// �������
			// if (dt != null)
			// {
			// dt_new.getRows().clear();
			// }
			// ��������������
			// ��������
			DataRow r = null;
			String[] a = null;
			String[] b = null;
			if (dt != null)
			{
				if (tj.indexOf("=") > -1)
				{
					if (tj.indexOf("&") == -1 && tj.indexOf("|") == -1)
					{
						for (int i = 0; i < dt.getRowsCount(); i++)
						{
							r = dt.get(i);
							if (!tj.equals("1=2"))
							{
								if (tj.indexOf("!=") > 0)
								{
									a = tj.split("!=");
									if (!r.getString(a[0]).trim().equals(
											a[1].trim()))
									{
										dt_new.addRow(r);
									}
								}
								else
								{
									a = tj.split("=");
									if (r.getString(a[0]).trim().equals(
											a[1].trim()))
									{
										dt_new.addRow(r);
									}
								}
							}
						}
					}
					else
					{
						for (int i = 0; i < dt.getRowsCount(); i++)
						{
							r = dt.get(i);
							String c = "";
							if (tj.indexOf("|") > 0)
							{
								boolean bb = false;
								a = tj.split("|");
								for (int j = 0; j < a.length; j++)
								{
									c = a[j];
									if (c.indexOf("!=") > 0)
									{
										b = c.split("!=");
										if (!r.getString(b[0]).trim().equals(
												b[1].trim()))
										{
											bb = true;
										}
									}
									if (c.indexOf("=") > 0
											&& c.indexOf("!=") == -1)
									{
										b = c.split("=");
										if (r.getString(b[0]).trim().equals(
												b[1].trim()))
										{
											bb = true;
										}
									}
								}
								if (bb)
								{
									dt_new.addRow(r);
								}
							}
							else
							{
								boolean bb = true;
								a = tj.split("&");
								for (int j = 0; j < a.length; j++)
								{
									c = a[j];
									if (c.indexOf("!=") > 0)
									{
										b = c.split("!=");
										if (r.getString(b[0]).trim().equals(
												b[1].trim()))
										{
											bb = false;
										}
									}
									if (c.indexOf("=") > 0
											&& c.indexOf("!=") == -1)
									{
										b = c.split("=");
										if (!r.getString(b[0]).trim().equals(
												b[1].trim()))
										{
											bb = false;
										}
									}
								}
								if (bb)
								{
									dt_new.addRow(r);
								}
							}
						}
					}
				}
				else
				{
					dt_new = dt;
				}
			}
			return dt_new;
		}
		catch (Exception e)
		{
			//System.out.print(tj);
			e.printStackTrace();
			return dt;
		}
	}

	public static DataTable RebuildTable(DataTable dt, String tj, String column)
	{
		// return dt;
		// /*
		try
		{
			DataTable dt_res_temp = RebuildTable(dt, tj);
			// ������Ҫ��������ع�DataTable��column������Ҫ��������
			DataTable dt_res = null;
			if (column.indexOf(",") > -1)
			{
				dt_res = new DataTable(String.valueOf(System
						.currentTimeMillis()));
				// ������
				String[] col = column.split(",");
				dt_res._columns.clear(); // int j=0;
				for (int i = 1; i <= col.length; i++)
				{
					Object o = col[i - 1];
					if (dt_res._columns.containsKey(o))
					{
						o = o.toString() + i;
					}
					if (o != null)
					{
						o = o.toString().toLowerCase();
					}
					dt_res._columns.put(o, new Integer(i - 1));
				}
				// ������
				dt_res.setTotalrowcount(dt_res_temp.getRowsCount());
				dt_res._rows.clear();
				DataRow r = null;
				DataRow rr = null;
				Object oo = null;
				for (int j = 0; j < dt_res_temp.getRowsCount(); j++)
				{
					r = dt_res_temp.get(j);
					rr = new DataRow(dt_res);
					for (int k = 0; k < col.length; k++)
					{
						oo = r.get(col[k]);
						// System.out.println(oo);

						rr.set(k, oo);
					}
					dt_res._rows.add(rr);
					rr = null;
				}
			}
			return dt_res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static DataTable getPartOfTable(DataTable dt, int from_rowno,
			int to_rowno)
	{
		try
		{
			DataTable dt_new = new DataTable(String.valueOf(System
					.currentTimeMillis()));
			dt_new.set_columns(dt.get_columns());
			// ����ȡ���ݵ�ǰ��
			if (dt.getRowsCount() >= from_rowno)
			{
				for (int i = from_rowno - 1; i < dt.getRowsCount(); i++)
				{
					if (i < to_rowno)
					{
						dt_new.addRow(dt.get(i));
					}
				}
			}
			return dt_new;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
}