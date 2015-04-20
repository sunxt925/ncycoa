package com.entity.stdapply;

import java.util.ArrayList;
import java.util.List;

import com.db.DataTable;
import com.entity.system.Org;

public class ApplyModOrgList {
	List<String> orgcodelist=new ArrayList<String>();
	List<String> orgnamelist=new ArrayList<String>();
	private static String orglist;
	private static String where;
	private java.util.Properties m_ftpProperties;
	public ApplyModOrgList(){}
	public void getListByStaffcode(String staffcode) throws Exception{
		DocApplyWhere applyWhere=new DocApplyWhere();
		DataTable dT=applyWhere.getTowhereByStaffcode(staffcode);
		where=dT.get(0).getString("applywhere");
		LoadConfig("/com/entity/stdapply/applyorglist.properties");
		String[] orgStringlist=orglist.split(",");
		for(int i=0;i<orgStringlist.length;i++){
			orgcodelist.add(orgStringlist[i]);
			Org org=new Org(orgStringlist[i]);
			String name=org.getName();
			orgnamelist.add(name);
		}
	}
	public List<String> getOrgcodelist() {
		return orgcodelist;
	}
	public void setOrgcodelist(List<String> orgcodelist) {
		this.orgcodelist = orgcodelist;
	}
	public List<String> getOrgnamelist() {
		return orgnamelist;
	}
	public void setOrgnamelist(List<String> orgnamelist) {
		this.orgnamelist = orgnamelist;
	}
	public void LoadConfig(String name) 
	{
		try
		{
			ClassLoader cl = getClass().getClassLoader();
			java.io.InputStream in;
			if (cl != null)
			{
				in = cl.getResourceAsStream(name);
			}
			else
			{
				in = ClassLoader.getSystemResourceAsStream(name);
			}
			if (in == null)
			{
				// 用文件读写
				in = new java.io.BufferedInputStream(
						new java.io.FileInputStream(name));
			}
				
			try
			{
				m_ftpProperties = new java.util.Properties();
				// 装载配置文件
				m_ftpProperties.load(in);
				// 得到配置内容
				orglist = consume(m_ftpProperties, where);
			}
			finally
			{
				if (in != null)
				{
					try
					{
						in.close();
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private String consume(java.util.Properties p, String key)
	{
		String s = null;
		if ((p != null) && (key != null))
		{
			s = p.getProperty(key);
			// 找到，则从属性表中移去
			if (s != null)
			{
				p.remove(key);
			}
		}
		return s;
	}
}
