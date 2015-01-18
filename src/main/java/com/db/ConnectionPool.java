package com.db;

import java.sql.Connection;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.common.UserException;

public final class ConnectionPool
{
	public static String Debug;
	private static String DBUrl;
	private static String DBUser;
	private static String DBPwd;
	private static DataSource ds;

	private java.util.Properties m_JDBCProperties;

	public ConnectionPool()
	{

	}

	public void CreatePool()
	{
		try
		{
			LoadConfig("/dbconfig.properties");
			DruidDataSource dds = new DruidDataSource();
			//基本属性 url、user、password
			dds.setUrl(DBUrl);
			dds.setUsername(DBUser);
			dds.setPassword(DBPwd);
			
			// 配置初始化大小、最小、最大
			dds.setInitialSize(1);
			dds.setMinIdle(1);
			dds.setMaxActive(20);
			
			// 配置获取连接等待超时的时间
			dds.setMaxWait(60000);
			
			// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
			dds.setTimeBetweenEvictionRunsMillis(60000);
			
			// 配置一个连接在池中最小生存的时间，单位是毫秒
			dds.setMinEvictableIdleTimeMillis(300000);
			
			//
			dds.setValidationQuery("select 1 from dual");
			dds.setTestWhileIdle(true);
			dds.setTestOnBorrow(false);
			dds.setTestOnReturn(false);
			
			// 打开PSCache，并且指定每个连接上PSCache的大小
			dds.setPoolPreparedStatements(true);
			dds.setMaxPoolPreparedStatementPerConnectionSize(20);
			
			// 配置监控统计拦截的filters
			dds.setFilters("mergeStat");
		
			ds = dds;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void LoadConfig(String name) throws UserException
	{
		try
		{
			ClassLoader cl = getClass().getClassLoader();
			java.io.InputStream in;
			if (cl != null)
			{
				in = cl.getResourceAsStream(name);
			}
			else
			{
				in = ClassLoader.getSystemResourceAsStream(name);
			}
			if (in == null)
			{
				// 用文件读写
				in = new java.io.BufferedInputStream(
						new java.io.FileInputStream(name));
			}
				
			try
			{
				m_JDBCProperties = new java.util.Properties();
				// 装载配置文件
				m_JDBCProperties.load(in);
				// 得到配置内容
				DBUrl = consume(m_JDBCProperties, "jdbc.url");
				DBUser = consume(m_JDBCProperties, "jdbc.username");
				DBPwd = consume(m_JDBCProperties, "jdbc.password");
				Debug = "false";//consume(m_JDBCProperties, "debug");
			}
			finally
			{
				if (in != null)
				{
					try
					{
						in.close();
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new UserException("[连接池]:连接池配置文件 '" + name + "' 未被找到.");
		}
	}

	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			conn = ds.getConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	private String consume(java.util.Properties p, String key)
	{
		String s = null;
		if ((p != null) && (key != null))
		{
			s = p.getProperty(key);
			// 找到，则从属性表中移去
			if (s != null)
			{
				p.remove(key);
			}
		}
		return s;
	}
}
