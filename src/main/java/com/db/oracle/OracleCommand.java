/*
 * �������� 2006-7-17
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.db.oracle;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

import com.db.ConnectionPool;
import com.db.ExecSql;
import com.db.Parameter;

/**
 * @author admin
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class OracleCommand extends ExecSql
{
    int resultCount = 0;

    public OracleCommand(String sqlStr)
    {
        super(sqlStr);
    }

    //���resultCount�ĸ�������2�����ô洢���̵�����������ض���α�
    public OracleCommand(String sqlStr, Parameter.SqlParameter[] params, boolean isStoredProcedure,
            int resultCount)
    {
        this.resultCount = resultCount;
        try
        {
            con = ConnectionPool.getConnection();
            isAutoCommit = con.getAutoCommit();
            this.setCommand(sqlStr, params, isStoredProcedure, resultCount);
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

    final public void setCommand(String sqlStr, Parameter.SqlParameter[] params, int resultCount)
    {
        setCommand(sqlStr, params, true, resultCount);
    }

    final public void setCommand(String sqlStr, Parameter.SqlParameter[] params,
            boolean isStoredProcedure)
    {
        setCommand(sqlStr, params, isStoredProcedure, 1);
    }

    final private void setCommand(String sqlStr, Parameter.SqlParameter[] params,
            boolean isStoredProcedure, int resultCount)
    {
        this.resultCount = resultCount;
        try
        {
            if (st != null)
            {
                st.close();
            }
            text = sqlStr;
            if (isStoredProcedure)
            {
                if (this.resultCount > 1)
                {
                    StringBuffer buf1 = new StringBuffer(text.trim());
                    StringBuffer buf = new StringBuffer("?");
                    for (int i = 1; i < this.resultCount; i++)
                    {
                        buf.append(",?");
                    }
                    if (buf1.indexOf(",") >= 0)
                    {
                        buf.insert(0, ",");
                    }
                    buf1.insert(buf1.length() - 1, buf);
                    buf1.insert(0, "{ call ");
                    buf1.append("}");
                    text = buf1.toString();
                    flag = 3;
                }
                else
                {
                    text = "{call ?:=" + text + "}"; //��oracle�У����ǹ涨ֻд����
                    flag = 4;
                    params_begin = 2;
                }
                st = con.prepareCall(text);
            }
            else
            {
                st = con.prepareStatement(sqlStr);
                flag = 2;
            }
            //���Ӳ���
            parameter = params;
        }
        catch (Exception e)
        {
            Dispose();
            e.printStackTrace();
        }
    }

    final protected void execParameter() throws Exception
    {
        execParameter(false);
    }

    final private void execParameter(boolean hasResultSet) throws Exception
    {
        if (flag == 4)
        {
            //ע��һ������ֵ
            CallableStatement call = (CallableStatement) st;
            if (hasResultSet)
            {
                call.registerOutParameter(1, OracleTypes.CURSOR);
            }
            else
            {
                call.registerOutParameter(1, java.sql.Types.INTEGER); //���򷵻�һ������
            }
        }
        super.execParameter();
        if (flag == 3) //ע��oracle�洢���̵��������
        {
            CallableStatement call = (CallableStatement) st;
            int begin = 1;
            if (this.parameter != null)
            {
                begin = this.parameter.length + 1;
            }
            for (int i = 0, j = begin; i < this.resultCount; i++, j++)
            {
                call.registerOutParameter(j, OracleTypes.CURSOR);
            }
        }
    }

    final public ResultSet executeResultset() throws Exception
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
                execParameter(true);
                if (flag == 4)
                {
                    CallableStatement p = (CallableStatement) st;
                    p.execute();
                    rs = (ResultSet) p.getObject(1);
                }
                else if (flag == 3)
                {
                    int i = 1;
                    if (this.parameter != null)
                    {
                        i = this.parameter.length + 1;
                    }
                    CallableStatement p = (CallableStatement) st;
                    p.execute();
                    rs = (ResultSet) p.getObject(i);
                }
                else if (flag == 2)
                {
                    PreparedStatement p = (PreparedStatement) st;
                    rs = p.executeQuery();
                }
            }
            getOutputParameter();
            return rs;
        }
        catch (java.sql.SQLException se)
        {
            // int i = se.getErrorCode();
            throw se;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    final public ResultSet NextResult() throws Exception
    {
        if (con == null)
        {
            throw new Exception(ERR_COMMAND);
        }
        try
        {
            if (isFirst < 0)
            {
                throw new Exception("�������ô���.");
            }
            ResultSet rs = null;
            int j = -1;
            boolean b = false;
            if (isFirst == 0) //�õ���һ����¼��
            {
                if (flag == 1)
                {
                    b = st.execute(text);
                    j = st.getUpdateCount();
                }
                else
                {
                    execParameter(true);
                    if (flag == 4)
                    {
                        CallableStatement p = (CallableStatement) st;
                        p.execute();
                        rs = (ResultSet) p.getObject(1);
                        b = false;
                        j = -1;
                    }
                    else if (flag == 3)
                    {
                        int i = 1;
                        if (this.parameter != null)
                        {
                            i = this.parameter.length + 1;
                        }
                        CallableStatement p = (CallableStatement) st;
                        p.execute();
                        rs = (ResultSet) p.getObject(i);
                        b = false;
                        j = -1;
                    }
                    else if (flag == 2)
                    {
                        PreparedStatement p = (PreparedStatement) st;
                        b = p.execute();
                        j = st.getUpdateCount();
                    }
                }
                while (b || j > 0)
                {
                    if (b)
                    {
                        rs = st.getResultSet();
                        break;
                    }
                    b = st.getMoreResults();
                    j = st.getUpdateCount();
                }
                isFirst++;
                getOutputParameter();
            }
            else
            {
                if (flag == 4)
                {
                    throw new Exception("�����õ�oracle�������ܷ��ض����¼��.");
                }
                else if (flag == 3)
                {
                    int i = isFirst + 1;
                    if (this.parameter != null)
                    {
                        i = this.parameter.length + i;
                    }
                    CallableStatement p = (CallableStatement) st;
                    rs = (ResultSet) p.getObject(i);
                }
                else
                {
                    b = st.getMoreResults();
                    j = st.getUpdateCount();
                    while (b || j > 0)
                    {
                        if (b)
                        {
                            rs = st.getResultSet();
                            break;
                        }
                        b = st.getMoreResults();
                        j = st.getUpdateCount();
                    }
                }
                isFirst++;
            }
            return rs;
        }
        catch (java.sql.SQLException se)
        {
            //int i = se.getErrorCode();
            throw se;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}