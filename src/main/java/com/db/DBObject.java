/*
 * 创建日期 2006-7-17
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.db;

import java.sql.*;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.io.*;

import com.common.*;

/**
 * @author admin
 * 
 *         TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class DBObject
{
	// 默认的JNDI取值
	private String JndiName = "";
	private String ServerType = "TOMCAT";

	public DBObject()
	{
		this.JndiName = "wlfpcx";
		this.ServerType = "TOMCAT";
	}

	public DBObject(String jndi_name)
	{
		this.JndiName = jndi_name;
	}

	public int getRowsCount(String sql) throws Exception
	{
		try
		{
			int res = 0;
			sql = "select count(1) as rowscount from (" + sql + ")";
			//System.out.print(sql);
			DataTable dt = runSelectQuery(sql);
			if (dt != null && dt.getRowsCount() == 1)
			{
				res = Integer.parseInt(dt.get(0).getString("rowscount"));
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	// 执行Sql查询语句，返回DataTable
	public DataTable runSelectQuery(String sqlStr)
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlStr);
			DataTable dt = new DataTable();
			dt.fill(rs);
			rs.close();
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// System.out.print(sqlStr);
			return null;
		}
		finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (Exception e)
			{
				System.err.println("Statement关闭时异常");
				stmt = null;
			}
			
			try
			{
				if (con != null)
				{
					con.close();
				}
			}
			catch (Exception e)
			{
				System.err.println("Connection关闭时异常");
				con = null;
			}
		}
	}

	public DataTable runSelectQuery(String sqlStr, int start, int end)
			throws Exception
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlStr);
			DataTable dt = new DataTable();
			dt.fill(rs, start, end);
			rs.close();
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (Exception e)
			{
				con.close();
				con = null;
			}
			if (con != null)
			{
				con.close();
			}
		}
	}

	public DataTable runSelectQueryNoRow(String sqlStr) throws Exception
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			if (stmt.execute(sqlStr))
			{
				rs = stmt.executeQuery(sqlStr);
				DataTable dt = new DataTable();
				dt.fill_norow(rs);
				rs.close();
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
		finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (Exception e)
			{
				con.close();
				con = null;
			}
			if (con != null)
			{
				con.close();
			}
		}
	}

	public DataTable runSelectQuery(String sqlStr,
			Parameter.SqlParameter[] param) throws Exception
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			
			con = ConnectionPool.getConnection();
			ps = con.prepareStatement(sqlStr);
			for (int i = 0; i < param.length; i++)
			{
				param[i].execute(ps, i + 1);
				
			}
			rs = ps.executeQuery();
			DataTable dt = new DataTable();
			dt.fill(rs);
			rs.close();
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if (ps != null)
				{
					ps.close();
				}
			}
			catch (Exception e)
			{
				con.close();
				con = null;
			}
			if (con != null)
			{
				con.close();
			}
		}
	}

	public String getClob(String tab_name, String col_name, String param)
			throws Exception
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String res = "";
		try
		{
			String sqlStr = "select " + col_name + " from " + tab_name
					+ " where " + param;
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlStr);
			if (rs.next())
			{
				// Clob clob=(Clob)rs.getClob(1);
				java.sql.Clob clob = (Clob) rs.getClob(1);
				Reader inStream = clob.getCharacterStream();
				char[] c = new char[(int) clob.length()];
				inStream.read(c);
				// data是读出并需要返回的数据，类型是String
				res = new String(c);
				inStream.close();
			}
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
		finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (Exception e)
			{
				con.close();
				con = null;
			}
			if (con != null)
			{
				con.close();
			}
		}
	}

	/*
	public boolean setClob(String tab_name, String col_name, String param,
			String val) throws Exception
	{
		Connection con = ConnectionPool.getConnection();
		con.setAutoCommit(false);
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement ptmt = null;
		try
		{
			String sqlStr = "select " + col_name + " from " + tab_name
					+ " where " + param + " for update";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlStr);
			if (rs.next())
			{
				OracleThinClob clob = (OracleThinClob) rs.getClob(1);
				Writer outStream = clob.getCharacterOutputStream();
				char[] c = val.toCharArray();
				outStream.write(c, 0, c.length);
				outStream.flush();
				outStream.close();
				con.commit();
			}
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (stmt != null)
				{
					stmt.close();
				}
				if (ptmt != null)
				{
					ptmt.close();
				}
			}
			catch (Exception e)
			{
				con.close();
				con = null;
			}
			if (con != null)
			{
				con.close();
			}
		}
	}
	*/

	public boolean setNullclob(String tab_name, String col_name, String param)
			throws Exception
	{
		try
		{
			String sql = "update " + tab_name + " set " + col_name
					+ "=empty_clob() where " + param;
			if (run(sql))
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

	// 执行一条插入，删除，更新操作
	public boolean run(String sqlStr) throws Exception
	{
		Connection con = null;
		Statement stmt = null;
		try
		{
			// System.out.print(sqlStr);
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			stmt.execute(sqlStr);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (Exception e)
			{
				con.close();
				con = null;
			}
			if (con != null)
			{
				con.close();
			}
		}
	}

	public boolean run(String sqlStr, Parameter.SqlParameter[] param)
			throws Exception
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
		    
			con = ConnectionPool.getConnection();
			ps = con.prepareStatement(sqlStr);
			
			for (int i = 0; i < param.length; i++)
			{
				param[i].execute(ps, i + 1);
				
			}
			
			ps.execute();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if (ps != null)
				{
					ps.close();
				}
			}
			catch (Exception e)
			{
				con.close();
				con = null;
			}
			if (con != null)
			{
				con.close();
			}
		}
	}

	public String executeOneValue(String sql)
	{
		try
		{
			DataTable dt = runSelectQuery(sql);
			if (dt != null && dt.getColumnsCount() == 1
					&& dt.getRowsCount() >= 1)
			{
				DataRow r = dt.get(0);
				Object o = r.get(0);
				if (o != null)
				{
					return r.get(0).toString();
				}
				else
				{
					return "";
				}
			}
			else
			{
				return "";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public boolean executeBatch(Vector v)
	{
		String sql = "";
		ExecSql comm = null;
		boolean execute_success = false;
		try
		{
			if (v != null)
			{
				if (v.size() > 0)
				{
					sql = v.get(0).toString();
				}
			}
			// System.out.print(sql);
			comm = SqlHelper.helper().createCommand(sql);
			comm.beginTransaction();
			comm.execute();
			for (int i = 1; i < v.size(); i++)
			{
				sql = v.get(i).toString();
				comm.setCommand(sql);
				comm.execute();
			}
			comm.commit();
			execute_success = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (comm != null)
			{
				try
				{
					execute_success = false;
					comm.rollback();
				}
				catch (Exception ee)
				{
					ee.printStackTrace();
				}
			}
		}
		finally
		{
			if (comm != null)
			{
				comm.Dispose();
			}
		}
		return execute_success;
	}

	public String runProc(String p_name, String Para, String split_str)
	{
		Connection con = null;
		CallableStatement proc = null;
		try
		{
			String res = "";
			String[] p = Para.split(split_str);
			int x = p.length;
			con = ConnectionPool.getConnection();
			for (int i = 0; i < x; i++)
			{
				proc.setString(i + 1, p[i]);
			}
			proc.registerOutParameter(x + 1, Types.VARCHAR);
			proc.execute();
			res = Format.NullToBlank(proc.getString(x + 1));
			// System.out.println("procres============="+res);
			return res;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "未知错误！";
		}
		finally
		{
			try
			{
				if (proc != null)
				{
					proc.close();
				}
				if (con != null)
				{
					con.close();
				}
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
			}
		}
	}
}
