package com.query;

import com.db.*;

public class BaseQuery
{
	public BaseQuery()
	{

	}

	public static String getXssx(String cxid, String zcxid)
	{
		try
		{
			DBObject db = new DBObject();
			return db
					.executeOneValue("select xssx from cxtj_yzcxgl where cxid="
							+ cxid + " and zcxid=" + zcxid);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public static boolean sfyx(String cxid,String zcxid)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt= db.runSelectQuery("select xssx from cxtj_yzcxgl where cxid="
							+ cxid + " and zcxid=" + zcxid);
			if (dt!=null && dt.getRowsCount()==1)
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
	

	public boolean Sz(String para1, String para2, String queryid)
	{
		ExecSql comm = null;
		try
		{
			String sql_d = "delete from cxtj_yzcxgl where cxid=?";
			Parameter.SqlParameter[] p = new Parameter.SqlParameter[]
			{ new Parameter.Int(Integer.parseInt(queryid)) };
			comm = SqlHelper.helper().createCommand(sql_d, p);
			comm.beginTransaction();
			comm.execute();
			String sql = "insert into cxtj_yzcxgl (cxid,zcxid,xssx) values (?,?,?)";
			if (para1.indexOf(",") == -1)
			{
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.Int(Integer.parseInt(queryid)),
						new Parameter.Int(Integer.parseInt(para1)),
						new Parameter.Int(Integer.parseInt(para2)) };
				comm.setCommand(sql, pp);
				comm.execute();
			}
			else
			{
				String[] ids = para1.split(",");
				String[] xssx = para2.split(",");
				for (int i = 0; i < ids.length; i++)
				{
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
					{ new Parameter.Int(Integer.parseInt(queryid)),
							new Parameter.Int(Integer.parseInt(ids[i])),
							new Parameter.Int(Integer.parseInt(xssx[i])) };
					comm.setCommand(sql, pp2);
					comm.execute();
				}
			}
			comm.commit();
			return true;
		}
		catch (Exception e)
		{
			try
			{
				if (comm != null)
				{
					comm.rollback();
				}
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (comm != null)
			{
				comm.Dispose();
			}
		}
	}
}
