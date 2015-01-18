package com.entity.system;

import java.util.Vector;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class unitclass {
	public String unit_ccm = "";

	public String parent_ccm = "";

	public String unit_name = "";
	public String unit_newccm = "";
	public unitclass()
	{

	}
	public unitclass(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_UNIT where unit_ccm=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.unit_ccm = PK;
				this.parent_ccm = r.getString("parent_ccm");
				this.unit_name = r.getString("unit_name");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into SYSTEM_UNIT(unit_ccm,parent_ccm,unit_name) values (?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.unit_ccm), new Parameter.String(this.parent_ccm),
					new Parameter.String(this.unit_name),
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
			String sql = "update SYSTEM_UNIT set unit_ccm=?,parent_ccm=?,unit_name=? where unit_ccm=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.unit_newccm),
					new Parameter.String(this.parent_ccm),
					new Parameter.String(this.unit_name),
					new Parameter.String(this.unit_ccm) };
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
				v.add("delete from SYSTEM_UNIT where unit_ccm like '" + BmString
						+ "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from SYSTEM_UNIT where unit_ccm like '" + bm[i]
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
	public void setUnit_ccm(String unit_ccm)
	{
		this.unit_ccm = unit_ccm;
	}

	/**
	 * @return 返回 ccm。
	 */
	public String getUnit_ccm()
	{
		return unit_ccm;
	}
	public void setUnit_newccm(String unit_newccm)
	{
		this.unit_newccm = unit_newccm;
	}

	/**
	 * @return 返回 ccm。
	 */
	public String getUnit_newccm()
	{
		return unit_newccm;
	}
	public void setUnit_name(String unit_name)
	{
		this.unit_name = unit_name;
	}

	/**
	 * @return 返回 ccm。
	 */
	public String getUnit_name()
	{
		return unit_name;
	}
	public void setParent_ccm(String parent_ccm)
	{
		this.parent_ccm = parent_ccm;
	}

	/**
	 * @return 返回 ccm。
	 */
	public String getParent_ccm()
	{
		return parent_ccm;
	}
}
