/*
 * 创建日期 2006-7-26
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.query;

import com.common.*;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.ExecSql;
import com.db.Parameter;
import com.db.SqlHelper;

/**
 * @author admin
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class QueryColumn
{
	public int queryid = 0;// 查询id

	public String cxzdm = "";// 查询字段名

	public String xsbt = "";// 显示标题

	public String dqfs = "1";// 对齐方式，１表示居中，２表示居左，３表示居右

	public String xsgs = "";// 显示格式（数字列用）

	public boolean mcbz = false;// 行名称标志

	public String bdscl = "";// 绑定的输出列

	public String tjlx = "";// 统计类型

	public boolean sftxfx = false;// 是否参与图形分析

	public boolean sxzbz = false;// 上转转标志

	public String fyjgj = "";// 翻译结果集［编码类条件］

	public int sxzcxid = 0;// 上下转查询ID

	public boolean zdyzcx = false;// 上下转查询是否原子查询

	public int xssx = 0;// 显示顺序

	public QueryColumn()
	{

	}

	public QueryColumn(int queryid, String cxzdm)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from cxtj_scsz where cxid=? and cxzdm=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(queryid), new Parameter.String(cxzdm) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.queryid = queryid;
				this.cxzdm = cxzdm;
				this.xsbt = r.getString("xsbt");
				this.dqfs = r.getString("dqfs");
				this.xsgs = r.getString("xsgs");
				this.mcbz = Coder.getBoolean(r.getString("mcbz"));
				this.bdscl = r.getString("bdscl");
				this.tjlx = r.getString("tjlx");
				this.sxzbz = Coder.getBoolean(r.getString("sxzbz"));
				this.sftxfx = Coder.getBoolean(r.getString("sftxfx"));
				this.fyjgj = r.getString("fyjgj");
				this.xssx = Integer.parseInt(r.getString("xssx"));
				this.sxzcxid = Integer.parseInt(Format.NullToZero(r
						.getString("sxzcxid")));
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
			String sql = "insert into cxtj_scsz (cxid,cxzdm,xsbt,dqfs,xsgs,sxzbz,sftxfx,sxzcxid,fyjgj,xssx,mcbz,bdscl,tjlx) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.Int(this.queryid),
					new Parameter.String(this.cxzdm),
					new Parameter.String(this.xsbt),
					new Parameter.String(this.dqfs),
					new Parameter.String(this.xsgs),
					new Parameter.Int(Integer.parseInt(Coder
							.getBooleanNum(this.sxzbz))),
					new Parameter.Int(Integer.parseInt(Coder
							.getBooleanNum(this.sftxfx))),
					new Parameter.Int(this.sxzcxid),
					new Parameter.String(this.fyjgj),
					new Parameter.Int(this.xssx),
					new Parameter.Int(Integer.parseInt(Coder
							.getBooleanNum(this.mcbz))),
					new Parameter.String(this.bdscl),
					new Parameter.Int(Integer.parseInt(this.tjlx)) };
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

	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update cxtj_scsz set xsbt=?,dqfs=?,xsgs=?,sxzbz=?,sftxfx=?,sxzcxid=?,fyjgj=?,xssx=?,mcbz=?,bdscl=?,tjlx=? where cxid=? and cxzdm=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.String(this.xsbt),
					new Parameter.String(this.dqfs),
					new Parameter.String(this.xsgs),
					new Parameter.Int(Integer.parseInt(Coder
							.getBooleanNum(this.sxzbz))),
					new Parameter.Int(Integer.parseInt(Coder
							.getBooleanNum(this.sftxfx))),
					new Parameter.Int(this.sxzcxid),
					new Parameter.String(this.fyjgj),
					new Parameter.Int(this.xssx),
					new Parameter.Int(Integer.parseInt(Coder
							.getBooleanNum(this.mcbz))),
					new Parameter.String(this.bdscl),
					new Parameter.Int(Integer.parseInt(this.tjlx)),
					new Parameter.Int(this.queryid),
					new Parameter.String(this.cxzdm) };
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

	public boolean Delete(int queryid, String param)
	{
		ExecSql comm = null;
		try
		{
			String sql = "delete from cxtj_scsz  where cxid=? and cxzdm=?";
			if (param.indexOf(",") == -1)
			{
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.Int(queryid), new Parameter.String(param) };
				comm = SqlHelper.helper().createCommand(sql, pp);
				comm.beginTransaction();
				comm.execute();
			}
			else
			{
				String[] ids = param.split(",");
				Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
				{ new Parameter.Int(queryid), new Parameter.String(ids[0]) };
				comm = SqlHelper.helper().createCommand(sql, pp1);
				comm.beginTransaction();
				comm.execute();
				for (int i = 1; i < ids.length; i++)
				{
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
					{ new Parameter.Int(queryid), new Parameter.String(ids[i]) };
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

	/**
	 * @return 返回 cxzdm。
	 */
	public String getCxzdm()
	{
		return cxzdm;
	}

	/**
	 * @param cxzdm
	 *            要设置的 cxzdm。
	 */
	public void setCxzdm(String cxzdm)
	{
		this.cxzdm = cxzdm;
	}

	/**
	 * @return 返回 dqfs。
	 */
	public String getDqfs()
	{
		return dqfs;
	}

	/**
	 * @param dqfs
	 *            要设置的 dqfs。
	 */
	public void setDqfs(String dqfs)
	{
		this.dqfs = dqfs;
	}

	/**
	 * @return 返回 fyjgj。
	 */
	public String getFyjgj()
	{
		return fyjgj;
	}

	/**
	 * @param fyjgj
	 *            要设置的 fyjgj。
	 */
	public void setFyjgj(String fyjgj)
	{
		this.fyjgj = fyjgj;
	}

	/**
	 * @return 返回 queryid。
	 */
	public int getQueryid()
	{
		return queryid;
	}

	/**
	 * @param queryid
	 *            要设置的 queryid。
	 */
	public void setQueryid(int queryid)
	{
		this.queryid = queryid;
	}

	/**
	 * @return 返回 sxzbz。
	 */
	public boolean isSxzbz()
	{
		return sxzbz;
	}

	/**
	 * @param sxzbz
	 *            要设置的 sxzbz。
	 */
	public void setSxzbz(boolean sxzbz)
	{
		this.sxzbz = sxzbz;
	}

	/**
	 * @return 返回 sxzcxid。
	 */
	public int getSxzcxid()
	{
		return sxzcxid;
	}

	/**
	 * @param sxzcxid
	 *            要设置的 sxzcxid。
	 */
	public void setSxzcxid(int sxzcxid)
	{
		this.sxzcxid = sxzcxid;
	}

	/**
	 * @return 返回 xsbt。
	 */
	public String getXsbt()
	{
		return xsbt;
	}

	/**
	 * @param xsbt
	 *            要设置的 xsbt。
	 */
	public void setXsbt(String xsbt)
	{
		this.xsbt = xsbt;
	}

	/**
	 * @return 返回 xsgs。
	 */
	public String getXsgs()
	{
		return xsgs;
	}

	/**
	 * @param xsgs
	 *            要设置的 xsgs。
	 */
	public void setXsgs(String xsgs)
	{
		this.xsgs = xsgs;
	}

	/**
	 * @return 返回 xssx。
	 */
	public int getXssx()
	{
		return xssx;
	}

	/**
	 * @param xssx
	 *            要设置的 xssx。
	 */
	public void setXssx(int xssx)
	{
		this.xssx = xssx;
	}

	/**
	 * @return 返回 mcbz。
	 */
	public boolean isMcbz()
	{
		return mcbz;
	}

	/**
	 * @param mcbz
	 *            要设置的 mcbz。
	 */
	public void setMcbz(boolean mcbz)
	{
		this.mcbz = mcbz;
	}

	public String getBdscl()
	{
		return bdscl;
	}

	public void setBdscl(String bdscl)
	{
		this.bdscl = bdscl;
	}

	/**
	 * @return 返回 tjlx。
	 */
	public String getTjlx()
	{
		return tjlx;
	}

	/**
	 * @param tjlx
	 *            要设置的 tjlx。
	 */
	public void setTjlx(String tjlx)
	{
		this.tjlx = tjlx;
	}

	public boolean isZdyzcx()
	{
		return zdyzcx;
	}

	public void setZdyzcx(boolean zdyzcx)
	{
		this.zdyzcx = zdyzcx;
	}

	public boolean isSftxfx()
	{
		return sftxfx;
	}

	public void setSftxfx(boolean sftxfx)
	{
		this.sftxfx = sftxfx;
	}

}