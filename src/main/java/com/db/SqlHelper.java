/*
 * �������� 2006-7-17
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.db;

import com.db.oracle.OracleSqlHelper;

/**
 * @author admin
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
abstract public class SqlHelper
{
    /////////////////���ݿ�����//////////////////////////////
    public static final int DB_MSSQLSERVER = 1;

    public static final int DB_ORACLE = 2;

    private static SqlHelper help;

    private int dbType = 1; //���ݿ�����

    protected String dbOwner; //���ݿ�������

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
     * ��һ��sql����ʽ����ʹ�䷵��ǰn����¼
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
            int resultCount); //ָ�����践�صļ�¼������

    final public ExecSql createCommand(String sqlStr, Parameter.SqlParameter[] params)
    {
        return createCommand(sqlStr, params, false);
    }

    ///////////////////////////////��̬����//////////////////////////////////////////////////
    public static SqlHelper helper()
    {
        initSqlHelper();
        return help;
    }

    //���ʻ�
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