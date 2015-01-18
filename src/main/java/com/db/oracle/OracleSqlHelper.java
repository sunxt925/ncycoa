/*
 * 创建日期 2006-7-17
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.db.oracle;

import com.common.Format;
import com.db.SqlHelper;
import com.db.ExecSql;
import com.db.Parameter;
import java.util.*;

/**
 * @author admin
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class OracleSqlHelper extends SqlHelper
{
    private HashMap sysobjs = new HashMap();

    protected void init()
    {
        sysobjs.put("length", "length"); //字符串长度函数
        sysobjs.put("substring", "substr"); //
        sysobjs.put("trim", "trim"); //
        sysobjs.put("nvl", "nvl");
        sysobjs.put("sysdate", "sysdate");
        sysobjs.put("+", " || "); //字符串相连
        sysobjs.put("to_char", "to_char("); //注意，调用to_char时已经加上了左括号,主要是为了与sql
        // server兼容
    }

    public String top(String sql, boolean hasWhere, boolean hasOrder, int n)
    {
        if (sql == null || sql.length() <= 0)
        {
            return "";
        }
        else
        {
            String ret = sql;
            if (hasOrder)
            {
                ret = "select * from (" + sql + ") where rownum<=" + n;
            }
            else
            {
                if (hasWhere)
                {
                    ret = ret + " and rownum<=" + n;
                }
                else
                {
                    ret = ret + " where rownum<" + n;
                }
            }
            return ret;
        }
    }

    final public String getSystemObject(String objName)
    {
        Object o = sysobjs.get(objName);
        if (o == null)
        {
            return objName;
        }
        else
        {
            return o.toString();
        }
    }

    final public String getUserObject(String objName)
    {
        return objName;
    }

    final public boolean getBoolean(Object o)
    {
        if (o == null || (!o.toString().equals("1") && !o.toString().toLowerCase().equals("true")))
        {
            return false;
        }
        return true;
    }

    final private void setBLOB(oracle.sql.BLOB blob, byte[] data) throws Exception
    {
        java.io.BufferedOutputStream out = null;
        java.io.BufferedInputStream in = null;
        try
        {
            out = new java.io.BufferedOutputStream(blob.getBinaryOutputStream());
            in = new java.io.BufferedInputStream(new java.io.ByteArrayInputStream(data));
            int c;
            while ((c = in.read()) != -1)
            {
                out.write(c);
            }
        }
        catch (Exception ie)
        {
            throw ie;
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                out.close();
            }
            catch (Exception e)
            {
            }
        }
    }

    final public void setBLOB(java.sql.ResultSet rs, int index, byte[] data) throws Exception
    {
        oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(index);
        setBLOB(blob, data);
    }

    final public void setBLOB(java.sql.ResultSet rs, String colName, byte[] data) throws Exception
    {
        oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(colName);
        setBLOB(blob, data);
    }

    final private void setCLOB(oracle.sql.CLOB clob, String data) throws Exception
    {
        java.io.BufferedWriter out = null;
        java.io.BufferedReader in = null;
        try
        {
            out = new java.io.BufferedWriter(clob.getCharacterOutputStream());
            in = new java.io.BufferedReader(new java.io.StringReader(data));
            int c;
            while ((c = in.read()) != -1)
            {
                out.write(c);
            }
        }
        catch (Exception ie)
        {
            throw ie;
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                out.close();
            }
            catch (Exception e)
            {
            }
        }
    }

    final public void setCLOB(java.sql.ResultSet rs, int index, String data) throws Exception
    {
        oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob(index);
        setCLOB(clob, data);
    }

    final public void setCLOB(java.sql.ResultSet rs, String colName, String data) throws Exception
    {
        oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob(colName);
        setCLOB(clob, data);
    }

    public byte[] getBLOB(java.sql.ResultSet rs, int index)
    {
        java.io.InputStream in = null;
        try
        {
            java.sql.Blob blob = rs.getBlob(1);
            in = blob.getBinaryStream();
            byte[] data = new byte[(int) blob.length()];
            in.read(data);
            return data;
        }
        catch (Exception e)
        {
            return null;
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e)
            {
            }
        }
    }

    public byte[] getBLOB(java.sql.ResultSet rs, String colName)
    {
        java.io.InputStream in = null;
        try
        {
            java.sql.Blob blob = rs.getBlob(colName);
            in = blob.getBinaryStream();
            byte[] data = new byte[(int) blob.length()];
            in.read(data);
            return data;
        }
        catch (Exception e)
        {
            return null;
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e)
            {
            }
        }
    }

    public String getCLOB(java.sql.ResultSet rs, int index)
    {
        java.io.Reader reader = null;
        try
        {
            java.sql.Clob clob = rs.getClob(index);
            reader = clob.getCharacterStream();
            char[] chs = new char[(int) clob.length()];
            reader.read(chs);
            return new String(chs);
        }
        catch (Exception e)
        {
            return Format.Empty;
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (Exception e)
            {

            }
        }
    }

    public String getCLOB(java.sql.ResultSet rs, String colName)
    {
        java.io.Reader reader = null;
        try
        {
            java.sql.Clob clob = rs.getClob(colName);
            reader = clob.getCharacterStream();
            char[] chs = new char[(int) clob.length()];
            reader.read(chs);
            return new String(chs);
        }
        catch (Exception e)
        {
            return Format.Empty;
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (Exception e)
            {

            }
        }
    }

    public byte[] getBLOB(java.sql.CallableStatement st, int index)
    {
        java.io.InputStream in = null;
        try
        {
            java.sql.Blob blob = st.getBlob(index);
            in = blob.getBinaryStream(); //blob.getBytes()
            byte[] data = new byte[(int) blob.length()];
            in.read(data);
            return data;
        }
        catch (Exception e)
        {
            return null;
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e)
            {
            }
        }
    }

    public String getCLOB(java.sql.CallableStatement st, int index)
    {
        java.io.Reader reader = null;
        try
        {
            java.sql.Clob clob = st.getClob(index);
            reader = clob.getCharacterStream();
            char[] chs = new char[(int) clob.length()];
            reader.read(chs);
            return new String(chs);
        }
        catch (Exception e)
        {
            return Format.Empty;
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (Exception e)
            {

            }
        }
    }

    public void setBLOB(java.sql.PreparedStatement st, int index, byte[] value) throws java.sql.SQLException
    {
        oracle.sql.BLOB b = null;
        if (value != null)
        {
            b = new oracle.sql.BLOB((oracle.jdbc.driver.OracleConnection) st.getConnection(), value);
        }
        //st.setBinaryStream(index,i);
        st.setBlob(index, (java.sql.Blob) b);
    }

    public void setCLOB(java.sql.PreparedStatement st, int index, String value) throws java.sql.SQLException
    {
        try
        {
            // st.setString(index, value);
            oracle.sql.CLOB c = null;
            if (value != null)
            {
                c = new oracle.sql.CLOB((oracle.jdbc.driver.OracleConnection) st.getConnection(), value
                        .getBytes());
            }
            st.setClob(index, (java.sql.Clob) c);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    final public ExecSql createCommand(String sql)
    {
        return new OracleCommand(sql);
    }

    final public ExecSql createCommand(String sqlStr, Parameter.SqlParameter[] params,
            boolean isStoredProcedure)
    {
        return new OracleCommand(sqlStr, params, isStoredProcedure, 1);
    }

    public ExecSql createCommand(String sqlStr, Parameter.SqlParameter[] params, int resultCount) //指定了需返回的记录集个数
    {
        return new OracleCommand(sqlStr, params, true, resultCount);
    }
}