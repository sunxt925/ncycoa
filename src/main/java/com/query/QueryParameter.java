/*
 * �������� 2006-7-26
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.query;

import com.db.*;
import com.common.*;

/**
 * @author ��ï
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class QueryParameter
{
	public int queryid = 0;// ��ѯid

	public String para_bm = "";// ��������

	public String para_mc = "";// ��������

	public String para_def = "";// Ĭ��ֵ

	public boolean qztj = false;// �Ƿ�ǿ������

	public String fyjgj = "";// ���������۱�����������

	public int xssx = 0;// ��ʾ˳��

	public String sjlx = "1";// �������ͣ�0Ϊ���룬1Ϊ�ַ�����2Ϊ���֣�3Ϊ����

	public String fxbm = "0";// �Ƿ�ѡ���룬��������Ϊ�����ѡ����ã�0Ϊ��1Ϊ��

	public String newpara_bm = "";// �²�ѯ����
	
	public boolean bdqx=false;//�Ƿ��Ȩ�ޣ���ʵ����һ����ѯ��ʱ��ʹ��
	
	public String bdqx_val="";//��Ȩ��ʱ��ȡֵ
	
	public String bdqx_name="";//��Ȩ��ʱ���������

	public QueryParameter()
	{

	}

	public QueryParameter(int queryid, String para_bm)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from cxtj_cxtj where cxid=? and bm=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(queryid), new Parameter.String(para_bm) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.para_mc = r.getString("bmnr");
				this.para_bm = para_bm;
				this.queryid = queryid;
				this.para_def = r.getString("mrz");
				this.qztj = Coder.getBoolean(r.getString("qztjbz"));
				this.fyjgj = r.getString("fyjgj");
				this.xssx = Integer.parseInt(r.getString("xssx"));
				this.sjlx = r.getString("sjlx");
				this.fxbm = r.getString("fxbm");
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
			// System.out.print(this.queryid);
			DBObject db = new DBObject();
			String sql = "insert into cxtj_cxtj (cxid,bm,bmnr,mrz,qztjbz,fyjgj,xssx,sjlx,fxbm) values (?,?,?,?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.Int(this.queryid),
					new Parameter.String(this.para_bm),
					new Parameter.String(this.para_mc),
					new Parameter.String(this.para_def),
					new Parameter.Int(Integer.parseInt(Coder
							.getBooleanNum(this.qztj))),
					new Parameter.String(this.fyjgj),
					new Parameter.Int(this.xssx),
					new Parameter.String(this.sjlx),
					new Parameter.String(this.fxbm) };
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
			String sql = "update cxtj_cxtj set bm=?,bmnr=?,mrz=?,qztjbz=?,fyjgj=?,xssx=?,sjlx=?,fxbm=? where cxid=? and bm=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.String(this.newpara_bm),
					new Parameter.String(this.para_mc),
					new Parameter.String(this.para_def),
					new Parameter.Int(Integer.parseInt(Coder
							.getBooleanNum(this.qztj))),
					new Parameter.String(this.fyjgj),
					new Parameter.Int(this.xssx),
					new Parameter.String(this.sjlx),
					new Parameter.String(this.fxbm),
					new Parameter.Int(this.queryid),
					new Parameter.String(this.para_bm) };
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
			String sql = "delete from cxtj_cxtj  where cxid=? and bm=?";
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
	 * @return ���� para_bm��
	 */
	public String getPara_bm()
	{
		return para_bm;
	}

	/**
	 * @param para_bm
	 *            Ҫ���õ� para_bm��
	 */
	public void setPara_bm(String para_bm)
	{
		this.para_bm = para_bm;
	}

	/**
	 * @return ���� para_def��
	 */
	public String getPara_def()
	{
		return para_def;
	}

	/**
	 * @param para_def
	 *            Ҫ���õ� para_def��
	 */
	public void setPara_def(String para_def)
	{
		this.para_def = para_def;
	}

	/**
	 * @return ���� para_mc��
	 */
	public String getPara_mc()
	{
		return para_mc;
	}

	/**
	 * @param para_mc
	 *            Ҫ���õ� para_mc��
	 */
	public void setPara_mc(String para_mc)
	{
		this.para_mc = para_mc;
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
	 * @return ���� qztj��
	 */
	public boolean isQztj()
	{
		return qztj;
	}

	/**
	 * @param qztj
	 *            Ҫ���õ� qztj��
	 */
	public void setQztj(boolean qztj)
	{
		this.qztj = qztj;
	}

	/**
	 * @return ���� sjlx��
	 */
	public String getSjlx()
	{
		return sjlx;
	}

	/**
	 * @param sjlx
	 *            Ҫ���õ� sjlx��
	 */
	public void setSjlx(String sjlx)
	{
		this.sjlx = sjlx;
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
	 * @return ���� newpara_bm��
	 */
	public String getNewpara_bm()
	{
		return newpara_bm;
	}

	/**
	 * @param newpara_bm
	 *            Ҫ���õ� newpara_bm��
	 */
	public void setNewpara_bm(String newpara_bm)
	{
		this.newpara_bm = newpara_bm;
	}

	public String getFxbm()
	{
		return fxbm;
	}

	public void setFxbm(String fxbm)
	{
		this.fxbm = fxbm;
	}

	public boolean isBdqx()
	{
		return bdqx;
	}

	public void setBdqx(boolean bdqx)
	{
		this.bdqx = bdqx;
	}

	public String getBdqx_val()
	{
		return bdqx_val;
	}

	public void setBdqx_val(String bdqx_val)
	{
		this.bdqx_val = bdqx_val;
	}

	public String getBdqx_name()
	{
		return bdqx_name;
	}

	public void setBdqx_name(String bdqx_name)
	{
		this.bdqx_name = bdqx_name;
	}
}