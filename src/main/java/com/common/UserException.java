/*
 * �������� 2006-7-17
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.common;

/**
 * @author admin
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class UserException extends Exception
{
    private final static String UNKNOWN_ERROR="δ֪����";
    public UserException()
    {
       super(UNKNOWN_ERROR);
    }

    public UserException(String msg)
    {
       super(msg);
    }
}
