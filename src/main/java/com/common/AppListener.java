/*
 * �������� 2006-8-11
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.common;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dao.system.Bm;
import com.db.*;


/**
 * @author ��ï
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class AppListener extends HttpServlet implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent sce)
    {
        //���ر���
        try
        {
        	//��ʼ��һ�����ӳ�
        	ConnectionPool cp=new ConnectionPool();
        	cp.CreatePool();
        	System.out.println("���ӳس�ʼ���ɹ�!");
            Bm.Init();
            System.out.println("�ɹ����ر���");
        }
        catch (Exception e)
        {
            System.out.println("δ�ܳɹ����ر���Ϣ.");
            return;
        }
    }

    public void contextDestroyed(ServletContextEvent sce)
    {
      
    }
}
