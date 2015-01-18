/*
 * 创建日期 2006-7-17
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.common;

/**
 * @author admin
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class UserException extends Exception
{
    private final static String UNKNOWN_ERROR="未知错误";
    public UserException()
    {
       super(UNKNOWN_ERROR);
    }

    public UserException(String msg)
    {
       super(msg);
    }
}
