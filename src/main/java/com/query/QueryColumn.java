/*
 * �������� 2006-7-26
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class QueryColumn
{
	public int queryid = 0;// ��ѯid

	public String cxzdm = "";// ��ѯ�ֶ���

	public String xsbt = "";// ��ʾ����

	public String dqfs = "1";// ���뷽ʽ������ʾ���У�����ʾ���󣬣���ʾ����

	public String xsgs = "";// ��ʾ��ʽ���������ã�

	public boolean mcbz = false;// �����Ʊ�־

	public String bdscl = "";// �󶨵������

	public String tjlx = "";// ͳ������

	public boolean sftxfx = false;// �Ƿ����ͼ�η���

	public boolean sxzbz = false;// ��תת��־

	public String fyjgj = "";// ���������۱�����������

	public int sxzcxid = 0;// ����ת��ѯID

	public boolean zdyzcx = false;// ����ת��ѯ�Ƿ�ԭ�Ӳ�ѯ

	public int xssx = 0;// ��ʾ˳��

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
	 * @return ���� cxzdm��
	 */
	public String getCxzdm()
	{
		return cxzdm;
	}

	/**
	 * @param cxzdm
	 *            Ҫ���õ� cxzdm��
	 */
	public void setCxzdm(String cxzdm)
	{
		this.cxzdm = cxzdm;
	}

	/**
	 * @return ���� dqfs��
	 */
	public String getDqfs()
	{
		return dqfs;
	}

	/**
	 * @param dqfs
	 *            Ҫ���õ� dqfs��
	 */
	public void setDqfs(String dqfs)
	{
		this.dqfs = dqfs;
	}

	/**
	 * @return ���� fyjgj��
	 */
	public String getFyjgj()
	{
		return fyjgj;
	}

	/**
	 * @param fyjgj
	 *            Ҫ���õ� fyjgj��
	 */
	public void setFyjgj(String fyjgj)
	{
		this.fyjgj = fyjgj;
	}

	/**
	 * @return ���� queryid��
	 */
	public int getQueryid()
	{
		return queryid;
	}

	/**
	 * @param queryid
	 *            Ҫ���õ� queryid��
	 */
	public void setQueryid(int queryid)
	{
		this.queryid = queryid;
	}

	/**
	 * @return ���� sxzbz��
	 */
	public boolean isSxzbz()
	{
		return sxzbz;
	}

	/**
	 * @param sxzbz
	 *            Ҫ���õ� sxzbz��
	 */
	public void setSxzbz(boolean sxzbz)
	{
		this.sxzbz = sxzbz;
	}

	/**
	 * @return ���� sxzcxid��
	 */
	public int getSxzcxid()
	{
		return sxzcxid;
	}

	/**
	 * @param sxzcxid
	 *            Ҫ���õ� sxzcxid��
	 */
	public void setSxzcxid(int sxzcxid)
	{
		this.sxzcxid = sxzcxid;
	}

	/**
	 * @return ���� xsbt��
	 */
	public String getXsbt()
	{
		return xsbt;
	}

	/**
	 * @param xsbt
	 *            Ҫ���õ� xsbt��
	 */
	public void setXsbt(String xsbt)
	{
		this.xsbt = xsbt;
	}

	/**
	 * @return ���� xsgs��
	 */
	public String getXsgs()
	{
		return xsgs;
	}

	/**
	 * @param xsgs
	 *            Ҫ���õ� xsgs��
	 */
	public void setXsgs(String xsgs)
	{
		this.xsgs = xsgs;
	}

	/**
	 * @return ���� xssx��
	 */
	public int getXssx()
	{
		return xssx;
	}

	/**
	 * @param xssx
	 *            Ҫ���õ� xssx��
	 */
	public void setXssx(int xssx)
	{
		this.xssx = xssx;
	}

	/**
	 * @return ���� mcbz��
	 */
	public boolean isMcbz()
	{
		return mcbz;
	}

	/**
	 * @param mcbz
	 *            Ҫ���õ� mcbz��
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
	 * @return ���� tjlx��
	 */
	public String getTjlx()
	{
		return tjlx;
	}

	/**
	 * @param tjlx
	 *            Ҫ���õ� tjlx��
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