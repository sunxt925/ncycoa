package com.db;

import java.sql.Connection;

import javax.sql.DataSource;

public final class ConnectionPool {
	private static DataSource ds;
	
	public static void setDataSource(DataSource ds) {
		ConnectionPool.ds = ds;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
