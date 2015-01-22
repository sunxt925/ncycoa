package edu.cqu.gem.ncycoa.service;

import edu.cqu.gem.ncycoa.domain.DataDictionary;
import edu.cqu.gem.ncycoa.domain.DataDictionaryItem;

public interface SystemService {
	
	/**
	 * ��־���
	 */
	public void addLog(String LogContent, Short loglevel,Short operatetype);
	/**
	 * �������ͱ�����������ƻ�ȡDataDictionaryItem,���Ϊ���򴴽�һ��
	 */
	public DataDictionaryItem getDataDictionaryItem(String code, String name, DataDictionary dict);
	/**
	 * �������ͷ����������ƻ�ȡDataDictionary,���Ϊ���򴴽�һ��
	 */
	public DataDictionary getDataDictionary(String code, String name);
	/**
	 * ���ݱ����ȡ�ֵ���
	 */
	public DataDictionary getDataDictionaryByCode(String code);
	/**
	 * �������ֵ���л���
	 */
	public void initAllDataDictionarys();
	/**
	 * ˢ���ֵ仺��
	 */
	public void refleshDataDictionaryItemCach(DataDictionary type);
	/**
	 * ˢ���ֵ���黺��
	 */
	public void refleshDataDictionaryCach();
	
}