package edu.cqu.gem.ncycoa.service;

import edu.cqu.gem.ncycoa.domain.DataDictionary;
import edu.cqu.gem.ncycoa.domain.DataDictionaryItem;

public interface SystemService {
	
	/**
	 * 日志添加
	 */
	public void addLog(String LogContent, Short loglevel,Short operatetype);
	/**
	 * 根据类型编码和类型名称获取DataDictionaryItem,如果为空则创建一个
	 */
	public DataDictionaryItem getDataDictionaryItem(String code, String name, DataDictionary dict);
	/**
	 * 根据类型分组编码和名称获取DataDictionary,如果为空则创建一个
	 */
	public DataDictionary getDataDictionary(String code, String name);
	/**
	 * 根据编码获取字典组
	 */
	public DataDictionary getDataDictionaryByCode(String code);
	/**
	 * 对数据字典进行缓存
	 */
	public void initAllDataDictionarys();
	/**
	 * 刷新字典缓存
	 */
	public void refleshDataDictionaryItemCach(DataDictionary type);
	/**
	 * 刷新字典分组缓存
	 */
	public void refleshDataDictionaryCach();
	
}