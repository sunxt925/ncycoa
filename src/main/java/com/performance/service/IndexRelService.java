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
	 * ���ɴ�ֱ�
	 * 
	 * @param index
	 * @return
	 */
	public String getValuationForm(Indexitem index)
	{
		String valueType = index.getValueComputingType();
		if("���㺯����".equals(valueType))
		{
			return func1(index);
		}
		else if("�Ǽ�����ֵ��".equals(valueType))
		{
			return func2(index);
		}
		else if("�Ǽ���ö����".equals(valueType))
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
	
	//���㺯����
	private String func1(Indexitem index)
	{
		String valueType = index.getValueScoreSourceType();
		StringBuffer sb = new StringBuffer();
		sb.append("<table " +
				" =\"5\"");
		if("�������-�˹�¼��".equals(valueType))
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
		else if("ָ��ֵ-�˹�¼��".equals(valueType))
		{
			sb.append(func("ָ��ֵ", "indexvalue"));
		}
		else if("ָ��÷�-�˹�¼��".equals(valueType))
		{
			sb.append(func("ָ��÷�", "indexscore"));
		}
		
		sb.append("</table>");
		return sb.toString();
	}
	//�Ǽ�����ֵ��
	private String func2(Indexitem index)
	{
		String valueType = index.getValueScoreSourceType();
		StringBuffer sb = new StringBuffer(128);
		sb.append("<table width=\"100%\" height=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"");
		if("ָ��ֵ-�˹�¼��".equals(valueType))
		{
			sb.append(func("ָ��ֵ", "indexvalue"));
		}
		else if("ָ��÷�-�˹�¼��".equals(valueType))
		{
			sb.append(func("ָ��÷�", "indexscore"));
		}
		
		sb.append("</table>");
		return sb.toString();
	}
	//�Ǽ���ö����
	private String func3(Indexitem index)
	{
		String type = index.getSCoreFuncType();
		StringBuffer sb = new StringBuffer(128);
		
		String[] tmps = index.getScoreFunc().split("=");
		String[] keys = tmps[0].substring(1, tmps[0].length() - 1).split(",");
		String[] values = tmps[1].substring(1, tmps[1].length() - 1).split(",");
		
		
		sb.append("<table id=\"dg\" class=\"easyui-datagrid\" data-options=\"rownumbers:true,checkOnSelect:true,selectOnCheck:true,fitColumns:true,data:enum_data");
		if("ö�ٶ�ѡ��".equals(type))
		{
			sb.append("\">");
		}
		else if("ö�ٵ�ѡ��".equals(type))
		{
			sb.append(",singleSelect:true\">");
		}
		sb.append("<thead><tr>");
		sb.append("<th data-options=\"field:'ck',checkbox:true\"></th>");
		sb.append("<th data-options=\"field:'key',width:100\">ѡ��</th>");
		sb.append("<th data-options=\"field:'value',width:100\">��ֵ</th>");
		sb.append("<th data-options=\"field:'count',width:100,editor:'numberbox'\">����</th></tr></thead></table>");
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
	 * ���ݿ��˶����code�Լ����˶������ͣ���ȡ��ö�����ص�ָ����
	 * 
	 * @param objCode ���˶������
	 * @param objType ���˶������ͣ���˾�����š����ˣ�
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
	 * ���ݿ��˶����code�Լ����˶������ͣ���ȡ��ö�����ص�ָ����
	 * 
	 * @param objCode ���˶������
	 * @param objType ���˶������ͣ���˾�����š����ˣ�
	 * @param pageNo ҳ��
	 * @param pageSize ÿҳ��¼��
	 * @return
	 */
	public DataTable getIndices(String objCode, PerferReviewObjType objType, int pageNo, int pageSize)
	{
		String tableNo = getTableNo(objCode, objType);
		return new TBM_InputTableIndexDao().getIndices(tableNo, pageNo, pageSize);
	}
	
	/**
	 * ��������˶�����ص�ָ�������Ŀ
	 * 
	 * @param objCode ���˶������
	 * @param objType ���˶������ͣ���˾�����š����ˣ�
	 * @return
	 */
	public int getIndicesCount(String objCode, PerferReviewObjType objType)
	{
		DataTable dt = getIndices(objCode, objType);
		return dt == null ? 0 : dt.getRowsCount();
	}
	
	
	/**
	 * ��ȡ��ÿ��˶��������ָ���ı��
	 * 
	 * @param objCode ���˶������
	 * @param objType ���˶������ͣ���˾�����š����ˣ�
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
