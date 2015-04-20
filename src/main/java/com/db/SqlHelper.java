/*
 * 创建日期 2006-7-17
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.db;

import com.db.oracle.OracleSqlHelper;

/**
 * @author admin
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
abstract public class SqlHelper
{
    /////////////////数据库类型//////////////////////////////
    public static final int DB_MSSQLSERVER = 1;

    public static final int DB_ORACLE = 2;

    private static SqlHelper help;

    private int dbType = 1; //数据库类型

    protected String dbOwner; //数据库所有者

    //protected String dbPackage=entc.common.Format.Empty;

    protected SqlHelper()
    {
    }

    final public int getDBType()
    {
        return dbType;
    }

    abstract protected void init();

    /**
     * <p>
     * 将一个sql语句格式化，使其返回前n条记录
     * </p>
     */
    public String top(String sql, boolean hasWhere, int n)
    {
        return top(sql, hasWhere, false, n);
    }

    abstract public String top(String sql, boolean hasWhere, boolean hasOrder, int n);

    abstract public String getSystemObject(String objName);

    abstract public String getUserObject(String objName);

    abstract public boolean getBoolean(Object o);

    abstract public byte[] getBLOB(java.sql.ResultSet rs, int index);

    abstract public byte[] getBLOB(java.sql.ResultSet rs, String colName);

    abstract public byte[] getBLOB(java.sql.CallableStatement st, int index);

    abstract public String getCLOB(java.sql.CallableStatement st, int index);

    abstract public void setBLOB(java.sql.PreparedStatement st, int index, byte[] value)
            throws java.sql.SQLException;

    abstract public void setCLOB(java.sql.PreparedStatement st, int index, String valu)
            throws java.sql.SQLException;

    abstract public String getCLOB(java.sql.ResultSet rs, int index);

    abstract public String getCLOB(java.sql.ResultSet rs, String colName);

    abstract public ExecSql createCommand(String sql);

    abstract public ExecSql createCommand(String sqlStr, Parameter.SqlParameter[] params,
            boolean isStoredProcedure);

    abstract public ExecSql createCommand(String sqlStr, Parameter.SqlParameter[] params,
            int resultCount); //指定了需返回的记录集个数

    final public ExecSql createCommand(String sqlStr, Parameter.SqlParameter[] params)
    {
        return createCommand(sqlStr, params, false);
    }

    ///////////////////////////////静态方法//////////////////////////////////////////////////
    public static SqlHelper helper()
    {
        initSqlHelper();
        return help;
    }

    //初适化
    static boolean initSqlHelper()
    {
        try
        {
            help = new OracleSqlHelper();
            help.dbType = DB_ORACLE;
            help.init();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}