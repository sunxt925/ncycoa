/*
 * 创建日期 2006-7-17
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.db;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.common.Format;

/**
 * @author admin
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class DataRow
{
    protected DataTable dt;

    protected Map _columns;

    protected List _data;

    public DataRow(DataTable dt)
    {
        this.dt = dt;
        if (dt != null)
        {
            _columns = dt.getCols();
            _data = new ArrayList(_columns.size());
        }
        else
        {
            _data = null;
        }
    }

    //得到本行对应列的数据
    public Object get(String ColumnName) throws Exception
    {
        int index = dt.getIndex(ColumnName);
        return get(index);
    }

    public String getString(String ColumnName) throws Exception
    {
        Object o = get(ColumnName);
        if (o == null)
        {
            return "";
        }
        else
        {
            return o.toString();
        }
    }
    
    public String getString(int index) throws Exception
    {
    	 Object o = get(index);
         if (o == null)
         {
             return "";
         }
         else
         {
             return o.toString();
         }
    }

    public String getBoolean(String ColumnName) throws Exception
    {
        Object o = get(ColumnName);
        if (o == null)
        {
            return "未设置";
        }
        else
        {
            if (o.toString().equals("0"))
            {
                return "否";
            }
            else
            {
                return "是";
            }
        }
    }

    public Object get(int index) throws Exception
    {
        return (_data == null ? null : _data.get(index));
    }

    public void set(String ColumnName, Object o) throws Exception
    {
        int index = dt.getIndex(ColumnName);
        set(index, o);
    }

    public void set(int index, Object o) throws Exception
    {
        if (_data == null)
        {
            return;
        }
        //如果不存在index处的元素，则扩充之
        if (index >= _columns.size())
        {
            return;
        }
        if (o != null && o instanceof String)
        {
            o = Format.trim(o.toString());
        }
        if (_data.size() < _columns.size())
        {
            int len = _columns.size() - _data.size();
            for (int i = 0; i < len; i++)
            {
                _data.add(null);
            }
        }
        _data.set(index, o);
    }
}