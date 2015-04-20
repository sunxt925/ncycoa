/*
 * �������� 2006-7-17
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.db;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author admin
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class DataTable
{
    protected String _tableName;

    public List _rows; //�����м���

    public Map _columns;

    protected int totalrowcount;

    public Map getCols()
    {
        return _columns;
    }

    public DataTable()
    {
        this(String.valueOf(System.currentTimeMillis()));
        try
        {
            jbInit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public DataTable(String name)
    {
        _tableName = name;
        _rows = new ArrayList();
        _columns = new HashMap();
    }

    public String getTableName()
    {
        return _tableName;
    }

    public void setTableName(String name)
    {
        _tableName = name;
    }

    //�õ�ָ������
    public DataRow get(int index)
    {
        return (DataRow) _rows.get(index);
    }

    //�õ����ڵ�����
    public int getRowsCount()
    {
        return _rows.size();
    }

    public int getRowsTotalCount()
    {
        return this.totalrowcount;
    }

    public List getRows()
    {
        return _rows;
    }

    public String[] getColumnNames()
    {
        if (_columns != null)
        {
            String[] ret = new String[_columns.size()];
            java.util.Iterator it = _columns.keySet().iterator();
            while (it.hasNext())
            {
                String key = it.next().toString();
                int index = ((Integer) _columns.get(key)).intValue();
                ret[index] = key;
            }
            return ret;
        }
        else
        {
            return null;
        }
    }

    //�õ����ڵ�����
    public int getColumnsCount()
    {
        return _columns.size();
    }

    //��һ��ResultSet���DataTable������(Ŀǰ��Ψһ����DataTable�ķ���)
    final void fill(ResultSet rs) throws Exception
    {
        if (rs == null)
        {
            return;
        }
        //���ɱ����
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        _columns.clear(); //int j=0;
        for (int i = 1; i <= columnCount; i++)
        {
            Object o = md.getColumnName(i);
            if (_columns.containsKey(o))
            {
                o = o.toString() + i;
            }
            if (o != null)
            {
                o = o.toString().toLowerCase();
            }
            _columns.put(o, new Integer(i - 1));
        }
        //���ɸ����е�����
        _rows.clear();
        while (rs.next())
        {
            DataRow r = new DataRow(this);
            for (int i = 0; i < columnCount; i++)
            {
                Object o = null;
                try
                {
                    o = rs.getObject(i + 1);
                }
                catch (Exception e)
                {

                }
                r.set(i, o);
            }
            _rows.add(r);
        }
    }

    final void fill(ResultSet rs, int start, int end) throws Exception
    {
        if (rs == null)
        {
            return;
        }
        //���ɱ����
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        _columns.clear(); //int j=0;
        for (int i = 1; i <= columnCount; i++)
        {
            Object o = md.getColumnName(i);
            if (_columns.containsKey(o))
            {
                o = o.toString() + i;
            }
            if (o != null)
            {
                o = o.toString().toLowerCase();
            }
            _columns.put(o, new Integer(i - 1));
        }
        //���ɸ����е�����
        _rows.clear();
        int j = 0;
        while (rs.next())
        {
            if (j >= start - 1 && j < end)
            {
                DataRow r = new DataRow(this);
                for (int i = 0; i < columnCount; i++)
                {
                    Object o = null;
                    try
                    {
                        o = rs.getObject(i + 1);
                    }
                    catch (Exception e)
                    {

                    }
                    r.set(i, o);
                }
                _rows.add(r);
            }
            j++;
        }
        this.totalrowcount = j;
    }

    final void fill_norow(ResultSet rs) throws Exception
    {
        if (rs == null)
        {
            return;
        }
        //���ɱ����
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        _columns.clear(); //int j=0;
        for (int i = 1; i <= columnCount; i++)
        {
            Object o = md.getColumnName(i);
            if (_columns.containsKey(o))
            {
                o = o.toString() + i;
            }
            if (o != null)
            {
                o = o.toString().toLowerCase();
            }
            _columns.put(o, new Integer(i - 1));
        }
        //���ɸ����е�����
        _rows.clear();
    }

    public void addRow(DataRow row)
    {
        if (_rows == null)
        {
            _rows = new ArrayList();
        }
        _rows.add(row);
    }

    public int getIndex(String columnName) throws Exception
    {
        if (_columns == null)
        {
            return -1;
        }
        try
        {
            int index = ((Integer) _columns.get(columnName.toLowerCase())).intValue();
            return index;
        }
        catch (Exception e)
        {
            throw new Exception("������в�����:" + columnName);
        }
    }

    /**
     * @return ���� _tableName��
     */
    public String get_tableName()
    {
        return _tableName;
    }

    /**
     * @param name
     *            Ҫ���õ� _tableName��
     */
    public void set_tableName(String name)
    {
        _tableName = name;
    }

    /**
     * @return ���� totalrowcount��
     */
    public int getTotalrowcount()
    {
        return totalrowcount;
    }

    /**
     * @param totalrowcount
     *            Ҫ���õ� totalrowcount��
     */
    public void setTotalrowcount(int totalrowcount)
    {
        this.totalrowcount = totalrowcount;
    }

    /**
     * @return ���� _columns��
     */
    public Map get_columns()
    {
        return _columns;
    }

    /**
     * @param _columns
     *            Ҫ���õ� _columns��
     */
    public void set_columns(Map _columns)
    {
        this._columns = _columns;
    }

    /**
     * @return ���� _rows��
     */
    public List get_rows()
    {
        return _rows;
    }

    /**
     * @param _rows
     *            Ҫ���õ� _rows��
     */
    public void set_rows(List _rows)
    {
        this._rows = _rows;
    }

    private void jbInit() throws Exception
    {
    }
}