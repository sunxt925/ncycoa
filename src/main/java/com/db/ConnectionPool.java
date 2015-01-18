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
			//�������� url��user��password
			dds.setUrl(DBUrl);
			dds.setUsername(DBUser);
			dds.setPassword(DBPwd);
			
			// ���ó�ʼ����С����С�����
			dds.setInitialSize(1);
			dds.setMinIdle(1);
			dds.setMaxActive(20);
			
			// ���û�ȡ���ӵȴ���ʱ��ʱ��
			dds.setMaxWait(60000);
			
			// ���ü����òŽ���һ�μ�⣬�����Ҫ�رյĿ������ӣ���λ�Ǻ���
			dds.setTimeBetweenEvictionRunsMillis(60000);
			
			// ����һ�������ڳ�����С�����ʱ�䣬��λ�Ǻ���
			dds.setMinEvictableIdleTimeMillis(300000);
			
			//
			dds.setValidationQuery("select 1 from dual");
			dds.setTestWhileIdle(true);
			dds.setTestOnBorrow(false);
			dds.setTestOnReturn(false);
			
			// ��PSCache������ָ��ÿ��������PSCache�Ĵ�С
			dds.setPoolPreparedStatements(true);
			dds.setMaxPoolPreparedStatementPerConnectionSize(20);
			
			// ���ü��ͳ�����ص�filters
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
				// ���ļ���д
				in = new java.io.BufferedInputStream(
						new java.io.FileInputStream(name));
			}
				
			try
			{
				m_JDBCProperties = new java.util.Properties();
				// װ�������ļ�
				m_JDBCProperties.load(in);
				// �õ���������
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
			throw new UserException("[���ӳ�]:���ӳ������ļ� '" + name + "' δ���ҵ�.");
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
			// �ҵ���������Ա�����ȥ
			if (s != null)
			{
				p.remove(key);
			}
		}
		return s;
	}
}
