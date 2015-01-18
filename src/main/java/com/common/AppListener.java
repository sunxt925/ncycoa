/*
 * 创建日期 2006-8-11
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.common;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dao.system.Bm;
import com.db.*;


/**
 * @author 林茂
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class AppListener extends HttpServlet implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent sce)
    {
        //加载编码
        try
        {
        	//初始化一个连接池
        	ConnectionPool cp=new ConnectionPool();
        	cp.CreatePool();
        	System.out.println("连接池初始化成功!");
            Bm.Init();
            System.out.println("成功加载编码");
        }
        catch (Exception e)
        {
            System.out.println("未能成功加载编信息.");
            return;
        }
    }

    public void contextDestroyed(ServletContextEvent sce)
    {
      
    }
}
