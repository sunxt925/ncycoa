package com.performance.service;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.index.Indexitem;
import com.performance.dao.Base_OrgDao;
import com.performance.dao.Base_StaffDao;
import com.performance.dao.TBM_IndexArchUserDao;
import com.performance.dao.TBM_InputTableDao;
import com.performance.dao.TBM_InputTableIndexDao;

public class IndexRelService
{
	/**
	 * 生成打分表单
	 * 
	 * @param index
	 * @return
	 */
	public String getValuationForm(Indexitem index)
	{
		String valueType = index.getValueComputingType();
		if("计算函数类".equals(valueType))
		{
			return func1(index);
		}
		else if("非计算数值型".equals(valueType))
		{
			return func2(index);
		}
		else if("非计算枚举型".equals(valueType))
		{
			return func3(index);
		}
		
		return null;
	}
	
	private String func(String name, String code)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<tr><td>");
		sb.append(name).append("(").append(code).append(")");
		sb.append("</td><td>");
		sb.append("<input type=\"text\" name=\"").append(code);
		sb.append("\" id=\"").append(code);
		sb.append("\"></input></td></tr>");
		
		return sb.toString();
	}
	
	//计算函数类
	private String func1(Indexitem index)
	{
		String valueType = index.getValueScoreSourceType();
		StringBuffer sb = new StringBuffer();
		sb.append("<table " +
				" =\"5\"");
		if("计算参数-人工录入".equals(valueType))
		{
			int i = 0;
			if(i < index.getNumPara())
			{
				i++;
			}
			
			if(i < index.getNumPara())
			{
				i++;
			}
			
			if(i < index.getNumPara())
			{
				i++;
			}
			
			if(i < index.getNumPara())
			{
				i++;
			}
		}
		else if("指标值-人工录入".equals(valueType))
		{
			sb.append(func("指标值", "indexvalue"));
		}
		else if("指标得分-人工录入".equals(valueType))
		{
			sb.append(func("指标得分", "indexscore"));
		}
		
		sb.append("</table>");
		return sb.toString();
	}
	//非计算数值型
	private String func2(Indexitem index)
	{
		String valueType = index.getValueScoreSourceType();
		StringBuffer sb = new StringBuffer(128);
		sb.append("<table width=\"100%\" height=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"");
		if("指标值-人工录入".equals(valueType))
		{
			sb.append(func("指标值", "indexvalue"));
		}
		else if("指标得分-人工录入".equals(valueType))
		{
			sb.append(func("指标得分", "indexscore"));
		}
		
		sb.append("</table>");
		return sb.toString();
	}
	//非计算枚举型
	private String func3(Indexitem index)
	{
		String type = index.getSCoreFuncType();
		StringBuffer sb = new StringBuffer(128);
		
		String[] tmps = index.getScoreFunc().split("=");
		String[] keys = tmps[0].substring(1, tmps[0].length() - 1).split(",");
		String[] values = tmps[1].substring(1, tmps[1].length() - 1).split(",");
		
		
		sb.append("<table id=\"dg\" class=\"easyui-datagrid\" data-options=\"rownumbers:true,checkOnSelect:true,selectOnCheck:true,fitColumns:true,data:enum_data");
		if("枚举多选型".equals(type))
		{
			sb.append("\">");
		}
		else if("枚举单选型".equals(type))
		{
			sb.append(",singleSelect:true\">");
		}
		sb.append("<thead><tr>");
		sb.append("<th data-options=\"field:'ck',checkbox:true\"></th>");
		sb.append("<th data-options=\"field:'key',width:100\">选项</th>");
		sb.append("<th data-options=\"field:'value',width:100\">分值</th>");
		sb.append("<th data-options=\"field:'count',width:100,editor:'numberbox'\">次数</th></tr></thead></table>");
		sb.append("<script type=\"text/javascript\">");
		sb.append("var enum_data=[");
		
		int i=0;
		for(; i<keys.length; i++)
		{
			sb.append("{\"key\":\"");sb.append(keys[i]);sb.append("\",\"value\":");sb.append(values[i]);
			sb.append(",\"count\":0},");
		}
		if(i > 0) sb.deleteCharAt(sb.length() - 1);
		sb.append("];");
		sb.append("</script>");
		return sb.toString();
	}
	
	
	
	
	/**
	 * 根据考核对象的code以及考核对象类型，获取与该对象相关的指标项
	 * 
	 * @param objCode 考核对象代码
	 * @param objType 考核对象类型（公司、部门、个人）
	 * @return
	 */
	public DataTable getIndices(String objCode, PerferReviewObjType objType)
	{
		String tableNo = getTableNo(objCode, objType);
		return new TBM_InputTableIndexDao().getIndices(tableNo);
	}
	
	
	public DataTable getIndices(PerferReviewObjType objType){
		String type;
		switch(objType)
		{
			case STAFF: type = "S";break;
			case DEPART: type = "D";break;
			case COMPANY: type = "C";break;
			default: type="S";
		}
		
		DataTable dt = null;
		try {
			DBObject db=new DBObject();
			
			String sql="select * from tbm_indexitem where length(indexcode)=3 and indexcode like '"+type+"%' order by indexcode";
			dt=db.runSelectQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	/**
	 * 根据考核对象的code以及考核对象类型，获取与该对象相关的指标项
	 * 
	 * @param objCode 考核对象代码
	 * @param objType 考核对象类型（公司、部门、个人）
	 * @param pageNo 页号
	 * @param pageSize 每页记录数
	 * @return
	 */
	public DataTable getIndices(String objCode, PerferReviewObjType objType, int pageNo, int pageSize)
	{
		String tableNo = getTableNo(objCode, objType);
		return new TBM_InputTableIndexDao().getIndices(tableNo, pageNo, pageSize);
	}
	
	/**
	 * 与给定考核对象相关的指标项的数目
	 * 
	 * @param objCode 考核对象代码
	 * @param objType 考核对象类型（公司、部门、个人）
	 * @return
	 */
	public int getIndicesCount(String objCode, PerferReviewObjType objType)
	{
		DataTable dt = getIndices(objCode, objType);
		return dt == null ? 0 : dt.getRowsCount();
	}
	
	
	/**
	 * 获取与该考核对象关联的指标表的表号
	 * 
	 * @param objCode 考核对象代码
	 * @param objType 考核对象类型（公司、部门、个人）
	 * @return
	 */
	public String getTableNo(String objCode, PerferReviewObjType objType)
	{
		TBM_IndexArchUserDao archUserDao = new TBM_IndexArchUserDao();
		String archIndexCode = null;
		
		switch(objType)
		{
			case STAFF: archIndexCode = archUserDao.getIndexArchCodeByStaffCode(objCode);break;
			case DEPART:
			case COMPANY: archIndexCode = archUserDao.getIndexArchCodeByOrgCode(objCode);break;
		}
		
		return new TBM_InputTableDao().getTableNo(archIndexCode);
	}
	
	public DataTable getOrgs(String orgcode)
	{
		return new Base_OrgDao().getOrgs(orgcode);
	}
	
	public String getOrgsJson(String orgcode) throws Exception
	{
		if(orgcode == null) return "";
		
		DataTable dt = getOrgs(orgcode);
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int i=0; i<dt.getRowsCount(); i++)
		{
			if(orgcode.equals(dt.get(i).getString("orgcode")))
			{
				sb.append("{\"id\":\"").append(orgcode).append("\",");
				sb.append("\"text\":\"").append(dt.get(i).getString("orgname")).append("\",");
				sb.append("\"children\":");
				getChildren(orgcode, dt, sb);
				sb.append("}");
				break;
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	private void getChildren(String orgcode, DataTable dt, StringBuffer sb) throws Exception
	{
		boolean isFirstInsertion = true;
		sb.append("[");
		for(int i=0; i<dt.getRowsCount(); i++)
		{
			if(orgcode.equals(dt.get(i).getString("parentorgcode")))
			{
				if(isFirstInsertion)
				{
					isFirstInsertion = false;
				}
				else
				{
					sb.append(",");
				}
				sb.append("{\"id\":\"").append(dt.get(i).getString("orgcode")).append("\",");
				sb.append("\"text\":\"").append(dt.get(i).getString("orgname")).append("\",");
				sb.append("\"children\":");
				getChildren(dt.get(i).getString("orgcode"), dt, sb);
				sb.append("}");
			}
		}
		sb.append("]");
	}
	
	public String getStaffsJson(String orgcode) throws Exception
	{
		DataTable dt = new Base_StaffDao().getStaffByOrg(orgcode);
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int i = 0;
		for(i=0; i<dt.getRowsCount(); i++)
		{
			sb.append("{\"staffcode\":\"").append(dt.get(i).getString("staffcode")).append("\",");
			sb.append("\"staffname\":\"").append(dt.get(i).getString("staffname")).append("\",");
			sb.append("\"idcard\":\"").append(dt.get(i).getString("idcard")).append("\",");
			sb.append("\"gender\":\"").append(dt.get(i).getString("gender")).append("\"},");
		}
		if(i>0) sb.delete(sb.length() - 1, sb.length());
		sb.append("]");
		return sb.toString();
	}
}
