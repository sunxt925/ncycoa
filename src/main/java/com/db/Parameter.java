/*
 * 创建日期 2006-7-17
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.db;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Types;

/**
 * @author admin
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class Parameter
{
    //参数类型
    public static class ParameterType
    {
        //int _value;
        //private ParameterType(int value) {_value=value;}
        public static ParameterType Input = new ParameterType();

        public static ParameterType Output = new ParameterType();
        //public static ParameterType Return=new ParameterType();
    }

    public static class SqlParameter
    {
        protected boolean notNull = true;

        protected int _index;

        ParameterType _type;

        SqlParameter()
        {
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
        }
    }

    public static class Int extends SqlParameter
    {
        private int _value;

        public Int(int value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public Int(ParameterType type)
        {
            this._type = type;
        }

        public Int(Object value)
        {
            notNull = false;
            _type = ParameterType.Input;
        }

        public int getValue()
        {
            return _value;
        }

        public void setValue(int value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getInt(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (notNull)
                {
                    st.setInt(index, _value);
                }
                else
                {
                    st.setNull(index, Types.INTEGER);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.INTEGER);
                }
            _index = index;
        }
    }

    public static class Double extends SqlParameter
    {
        private double _value;

        public Double(double value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public Double(ParameterType type)
        {
            _type = type;
        }

        public Double(Object value)
        {
            notNull = false;
            _type = ParameterType.Input;
        }

        public double getValue()
        {
            return _value;
        }

        public void setValue(double value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getDouble(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (notNull)
                {
                    st.setDouble(index, _value);
                }
                else
                {
                    st.setNull(index, Types.DOUBLE);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.DOUBLE);
                }
            _index = index;
        }
    }

    public static class Float extends SqlParameter
    {
        private float _value;

        public Float(float value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public Float(ParameterType type)
        {
            _type = type;
        }

        public Float(Object value)
        {
            notNull = false;
            _type = ParameterType.Input;
        }

        public float getValue()
        {
            return _value;
        }

        public void setValue(float value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getFloat(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (notNull)
                {
                    st.setFloat(index, _value);
                }
                else
                {
                    st.setNull(index, Types.FLOAT);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.FLOAT);
                }
            _index = index;
        }
    }

    public static class Byte extends SqlParameter
    {
        private byte _value;

        public Byte(byte value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public Byte(ParameterType type)
        {
            _type = type;
        }

        public Byte(Object value)
        {
            notNull = false;
            _type = ParameterType.Input;
        }

        public byte getValue()
        {
            return _value;
        }

        public void setValue(byte value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getByte(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (notNull)
                {
                    st.setByte(index, _value);
                }
                else
                {
                    st.setNull(index, Types.TINYINT);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.TINYINT);
                }
            _index = index;
        }
    }

    public static class Boolean extends SqlParameter
    {
        private boolean _value;

        public Boolean(boolean value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public Boolean(ParameterType type)
        {
            _type = type;
        }

        public Boolean(Object value)
        {
            notNull = false;
            _type = ParameterType.Input;
        }

        public boolean getValue()
        {
            return _value;
        }

        public void setValue(boolean value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getBoolean(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (notNull)
                {
                    st.setBoolean(index, _value);
                }
                else
                {
                    st.setNull(index, Types.BIT);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.BIT);
                }
            _index = index;
        }
    }

    public static class Short extends SqlParameter
    {
        private short _value;

        public Short(short value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public Short(ParameterType type)
        {
            _type = type;
        }

        public Short(Object value)
        {
            notNull = false;
            _type = ParameterType.Input;
        }

        public short getValue()
        {
            return _value;
        }

        public void setValue(short value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getShort(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (notNull)
                {
                    st.setShort(index, _value);
                }
                else
                {
                    st.setNull(index, Types.SMALLINT);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.SMALLINT);
                }
            _index = index;
        }
    }

    public static class Long extends SqlParameter
    {
        private long _value;

        public Long(long value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public Long(ParameterType type)
        {
            _type = type;
        }

        public Long(Object value)
        {
            notNull = false;
            _type = ParameterType.Input;
        }

        public long getValue()
        {
            return _value;
        }

        public void setValue(long value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getLong(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (notNull)
                {
                    st.setLong(index, _value);
                }
                else
                {
                    st.setNull(index, Types.BIGINT);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.BIGINT);
                }
            _index = index;
        }
    }

    public static class String extends SqlParameter
    {
        private java.lang.String _value;

        public String(java.lang.String value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public String(ParameterType type)
        {
            _type = type;
        }

        public java.lang.String getValue()
        {
            return _value;
        }

        public void setValue(java.lang.String value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getString(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (_value != null)
                {
                    st.setString(index, _value);
                }
                else
                {
                    st.setNull(index, Types.VARCHAR);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.VARCHAR);
                }
            _index = index;
        }
    }

    public static class Bytes extends SqlParameter
    {
        private byte[] _value;

        public Bytes(byte[] value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public Bytes(ParameterType type)
        {
            _type = type;
        }

        public byte[] getValue()
        {
            return _value;
        }

        public void setValue(byte[] value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                _value = st.getBytes(_index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                if (_value != null)
                {
                    st.setBytes(index, _value);
                }
                else
                {
                    st.setNull(index, Types.BINARY);
                }
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.BINARY);
                }
            _index = index;
        }
    }

    //二近制流

    public static class BLOB extends SqlParameter
    {
        private byte[] _value;

        private java.io.InputStream in;

        private int len;

        public BLOB(byte[] value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public BLOB(java.io.InputStream in, int len)
        {
            this.in = in;
            this.len = len;
            _type = ParameterType.Input;
        }

        public BLOB(ParameterType type)
        {
            _type = type;
        }

        public byte[] getValue()
        {
            return _value;
        }

        public void setValue(byte[] value)
        {
            _value = value;
        }

        public void setValue(java.io.InputStream in, int len)
        {
            this.in = in;
            this.len = len;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                //_value = st.getBytes(_index);
                _value = SqlHelper.helper().getBLOB(st, _index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (in != null)
            {
                st.setBinaryStream(index, in, len);
            }
            else
            {
                st.setNull(index, java.sql.Types.BINARY);
            }
            /*
             * if (_type == ParameterType.Input) {
             * SqlHelper.helper().setBLOB(st,index,_value); } else if (_type ==
             * ParameterType.Output) { ( (CallableStatement)
             * st).registerOutParameter(index, Types.BLOB); }
             */
            _index = index;
        }
    }

    //大文本

    public static class CLOB extends SqlParameter
    {
        private java.lang.String _value;

        public CLOB(java.lang.String value)
        {
            _value = value;
            _type = ParameterType.Input;
        }

        public CLOB(ParameterType type)
        {
            _type = type;
        }

        public java.lang.String getValue()
        {
            return _value;
        }

        public void setValue(java.lang.String value)
        {
            _value = value;
        }

        void getOutputValue(CallableStatement st) throws Exception
        {
            if (_type == ParameterType.Output)
            {
                //_value = st.getBytes(_index);
                _value = SqlHelper.helper().getCLOB(st, _index);
            }
        }

        void execute(PreparedStatement st, int index) throws Exception
        {
            if (_type == ParameterType.Input)
            {
                SqlHelper.helper().setCLOB(st, index, _value);
            }
            else
                if (_type == ParameterType.Output)
                {
                    ((CallableStatement) st).registerOutParameter(index,
                            Types.CLOB);
                }
            _index = index;
        }
    }
}