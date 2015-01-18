/*
 * 创建日期 2006-7-26
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.query;

import com.db.*;
import com.common.*;

/**
 * @author 林茂
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class QueryParameter
{
	public int queryid = 0;// 查询id

	public String para_bm = "";// 参数编码

	public String para_mc = "";// 参数名称

	public String para_def = "";// 默认值

	public boolean qztj = false;// 是否强制条件

	public String fyjgj = "";// 翻译结果集［编码类条件］

	public int xssx = 0;// 显示顺序

	public String sjlx = "1";// 数据类型，0为编码，1为字符串，2为数字，3为日期

	public String fxbm = "0";// 是否复选编码，数据类型为编码该选项可用，0为否，1为是

	public String newpara_bm = "";// 新查询编码
	
	public boolean bdqx=false;//是否绑定权限，在实例化一个查询的时候使用
	
	public String bdqx_val="";//绑定权限时的取值
	
	public String bdqx_name="";//绑定权限时的输出名称

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
	 * @return 返回 para_bm。
	 */
	public String getPara_bm()
	{
		return para_bm;
	}

	/**
	 * @param para_bm
	 *            要设置的 para_bm。
	 */
	public void setPara_bm(String para_bm)
	{
		this.para_bm = para_bm;
	}

	/**
	 * @return 返回 para_def。
	 */
	public String getPara_def()
	{
		return para_def;
	}

	/**
	 * @param para_def
	 *            要设置的 para_def。
	 */
	public void setPara_def(String para_def)
	{
		this.para_def = para_def;
	}

	/**
	 * @return 返回 para_mc。
	 */
	public String getPara_mc()
	{
		return para_mc;
	}

	/**
	 * @param para_mc
	 *            要设置的 para_mc。
	 */
	public void setPara_mc(String para_mc)
	{
		this.para_mc = para_mc;
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
	 * @return 返回 qztj。
	 */
	public boolean isQztj()
	{
		return qztj;
	}

	/**
	 * @param qztj
	 *            要设置的 qztj。
	 */
	public void setQztj(boolean qztj)
	{
		this.qztj = qztj;
	}

	/**
	 * @return 返回 sjlx。
	 */
	public String getSjlx()
	{
		return sjlx;
	}

	/**
	 * @param sjlx
	 *            要设置的 sjlx。
	 */
	public void setSjlx(String sjlx)
	{
		this.sjlx = sjlx;
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
	 * @return 返回 newpara_bm。
	 */
	public String getNewpara_bm()
	{
		return newpara_bm;
	}

	/**
	 * @param newpara_bm
	 *            要设置的 newpara_bm。
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