/*
 * 创建日期 2006-7-17
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.db;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 * @author admin
 * 
 *         TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
abstract public class ExecSql
{
	protected Statement st = null;

	protected Connection con = null;

	protected String text;

	protected byte flag;

	protected Parameter.SqlParameter[] parameter = null;

	protected static String ERR_COMMAND = "无法执行命令，请稍后重试.";

	protected byte isFirst = 0; // >=1表示返回多个记录集<=0表示不返回或返回一个记录集

	protected ExecSql()
	{
	}

	public ExecSql(String sqlStr)
	{
		try
		{
			con = ConnectionPool.getConnection();
			isAutoCommit = con.getAutoCommit();
			this.setCommand(sqlStr);
		}
		catch (Exception e)
		{
			if (con != null)
			{
				try
				{
					con.close();
				}
				catch (Exception ee)
				{
					ee.printStackTrace();
				}
				con = null;
			}
		}
	}

	// 改变命令文本、命令参数
	final public void setCommand(String sqlStr)
	{
		try
		{
			if (st != null)
			{
				st.close();
			}
			st = con.createStatement();
			text = sqlStr;
			flag = 1;
			isFirst = 0;
		}
		catch (Exception e)
		{
			Dispose();
			e.printStackTrace();
		}
	}

	final public void setCommand(String sqlStr, Parameter.SqlParameter[] params)
	{
		setCommand(sqlStr, params, false);
	}

	public void setCommand(String sqlStr, Parameter.SqlParameter[] params,
			int resultCount)
	{
		setCommand(sqlStr, params, true);
	}

	public void setCommand(String sqlStr, Parameter.SqlParameter[] params,
			boolean isStoredProcedure)
	{
		try
		{
			isFirst = 0;
			if (st != null)
			{
				st.close();
			}
			text = sqlStr;
			if (isStoredProcedure)
			{
				text = "{call " + text + "}";
				st = con.prepareCall(text);
				flag = 3;
			}
			else
			{
				st = con.prepareStatement(sqlStr);
				flag = 2;
			}
			// 增加参数
			parameter = params;
			// execParameter();
		}
		catch (Exception e)
		{
			Dispose();
			e.printStackTrace();
		}
	}

	protected int params_begin = 1;

	protected void execParameter() throws Exception
	{
		if (parameter != null)
		{
			int len = parameter.length;
			PreparedStatement p = (PreparedStatement) st;
			for (int i = 0; i < len; i++)
			{
				parameter[i].execute(p, i + params_begin);
			}
		}
	}

	final protected void getOutputParameter() throws Exception
	{
		if (parameter == null || flag != 3)
		{
			return;
		}
		CallableStatement call = (CallableStatement) st;
		for (int i = 0; i < parameter.length; i++)
		{
			parameter[i].getOutputValue(call);
		}
	}

	protected boolean isAutoCommit;

	final public void beginTransaction() throws Exception
	{
		if (con != null)
		{
			try
			{
				con.setAutoCommit(false);
			}
			catch (Exception e)
			{
				con.close();
				con = null;
				throw e;
			}
		}
	}

	final public void commit() throws Exception
	{
		if (con != null)
		{
			con.commit();
			con.setAutoCommit(isAutoCommit);
		}
	}

	final public void rollback() throws Exception
	{
		if (con != null)
		{
			con.rollback();
			con.setAutoCommit(isAutoCommit);
		}
	}

	// 执行sql命令(在这里捕捉最基本的数据库错误)
	final public void execute() throws Exception
	{
		if (con == null)
		{
			throw new Exception(ERR_COMMAND);
		}
		isFirst = -1;
		try
		{
			if (flag == 1)
			{
				st.execute(text);
			}
			else
			{
				execParameter();
				PreparedStatement p = (PreparedStatement) st;
				p.execute();
			}
		}
		catch (java.sql.SQLException se)
		{
			// se.printStackTrace();
			int i = se.getErrorCode();
			if (i == 2627)
			{
				throw new Exception("这条数据已经存在，您输入了重复数据,或者信息的主码定义有误.");
			}
			else
			{
				throw se;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		getOutputParameter();
	}

	// 返回一个数据表
	final public void execute(DataTable dt) throws Exception
	{
		ResultSet rs = this.executeResultset();
		if (rs != null)
		{
			fillDataTable(dt, rs);
		}
	}

	// 返回一个数据表集合
	final public void execute(java.util.List tableList) throws Exception
	{
		if (con == null)
		{
			throw new Exception(ERR_COMMAND);
		}
		try
		{
			int i = 0;
			ResultSet rs = this.NextResult();
			while (rs != null)
			{
				i++;
				DataTable dt = new DataTable("table" + i);
				dt.fill(rs);
				tableList.add(dt);
				rs = this.NextResult();
			}
		}
		catch (Exception e)
		{
		}
	}

	public ResultSet executeResultset() throws Exception
	{
		if (con == null)
		{
			throw new Exception(ERR_COMMAND);
		}
		try
		{
			ResultSet rs = null;
			isFirst = -1;
			if (flag == 1)
			{
				rs = st.executeQuery(text);
			}
			else
			{
				execParameter();
				PreparedStatement p = (PreparedStatement) st;
				rs = p.executeQuery();
			}
			getOutputParameter();
			return rs;
		}
		catch (java.sql.SQLException se)
		{
			throw se;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	abstract public ResultSet NextResult() throws Exception;

	final public String executeOneValue() throws Exception
	{
		ResultSet rs = executeResultset();
		if (rs != null && rs.next())
		{
			return rs.getString(1);
		}
		else
		{
			return null;
		}
	}

	// 释放资源
	final public void Dispose()
	{
		if (st != null)
		{
			try
			{
				st.close();
			}
			catch (Exception e)
			{
				try
				{
					con.close();
				}
				catch (Exception ee)
				{
					ee.printStackTrace();
				}
				con = null;
				e.printStackTrace();
			}
			st = null;
		}
		if (con != null)
		{
			try
			{
				con.close();
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
			}
		}
	}

	protected void finalize()
	{
		if (con != null)
		{
			Dispose();
		}
	}

	final protected void fillDataTable(DataTable dt, ResultSet rs)
			throws Exception
	{
		dt.fill(rs);
	}
}