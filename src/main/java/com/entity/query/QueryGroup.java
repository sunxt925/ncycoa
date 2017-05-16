package com.entity.query;

import java.util.*;

import com.common.Format;
import com.db.*;

/**
 * @author ��ï
 */
public class QueryGroup
{
	public String ccm = "";// �����

	public String flmc = "";// ��������

	public String flms = "";// ��������
	public String pgroup_code="";//���������
	public String pgroup_name="";//�������������

	public String newccm = "";// �²����

	public QueryGroup()
	{

	}

	public QueryGroup(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from query_group where group_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.ccm = PK;
				this.flmc = r.getString("group_name");
				this.flms = r.getString("group_desc");
				this.pgroup_code=r.getString("pgroup_code");
				this.pgroup_name=r.getString("pgroup_name");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into query_group (groupid,group_name,group_desc,pgroup_code,pgroup_name) values (?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.ccm), 
					new Parameter.String(this.flmc),
					new Parameter.String(this.flms),
					new Parameter.String(this.pgroup_code),
					new Parameter.String(this.pgroup_name)};
			if (db.run(sql, pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update query_group set group_code=?,group_name=?,group_desc=? ,pgroup_code=?,pgroup_name=? where group_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.newccm),
					new Parameter.String(this.flmc),
					new Parameter.String(this.flms),
					
					new Parameter.String(this.pgroup_code),
					new Parameter.String(this.pgroup_name),
					new Parameter.String(this.ccm) 		
			};
			if (db.run(sql, pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ɾ��
	 * 
	 * @param BmString
	 * @return
	 */
	public boolean Delete(String BmString)
	{
		try
		{
			Vector v = new Vector();
			DBObject db = new DBObject();
			if (BmString.indexOf(",") == -1)
			{
				v.add("delete from query_group where group_code like '"
						+ BmString + "%'");
			}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					v.add("delete from query_group where group_code like '"
							+ bm[i] + "%'");
				}
			}
			if (db.executeBatch(v))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public DataTable getGroupList(int pageno, int perpage){
		
		return getGroupList("",pageno, perpage);
	}
	public DataTable getGroupList(String grouo_code,int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();
			if(grouo_code.equals("")){
				String base_sql = "select 'ѡ��' as ѡ��,group_code,group_code as �����,group_name as ģ������,group_desc as ģ������,'����' as ����  from (select * from query_group order by group_code)";
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
				// System.out.print(sql_run);
				return db.runSelectQuery(sql_run);
			}else{
				
				String base_sql = "select 'ѡ��' as ѡ��,group_code,group_code as �����,group_name as ģ������,group_desc as ģ������,'����' as ����  from (select * from query_group where group_code like '"+grouo_code+"%' order by group_code)";
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
				// System.out.print(sql_run);
				return db.runSelectQuery(sql_run);
			}
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	//�õ��ñ���������
	public DataTable getAllgroupList(){
		return getAllgroupList("");
	}
	public DataTable getAllgroupList(String group_code)
	{
		try
		{
			DBObject db = new DBObject();
			if(group_code.equals("")){
				return db.runSelectQuery("select * from query_group order by group_code");
			}else{
				
				return db.runSelectQuery("select * from query_group where group_code like '"+group_code+"%' order by group_code");
			}
			
			 
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String getCcm()
	{
		return ccm;
	}

	public void setCcm(String ccm)
	{
		this.ccm = ccm;
	}

	public String getFlmc()
	{
		return flmc;
	}

	public void setFlmc(String flmc)
	{
		this.flmc = flmc;
	}

	public String getFlms()
	{
		return flms;
	}

	public void setFlms(String flms)
	{
		this.flms = flms;
	}

	public String getNewccm()
	{
		return newccm;
	}

	public void setNewccm(String newccm)
	{
		this.newccm = newccm;
	}

	public String getPgroup_code() {
		return pgroup_code;
	}

	public void setPgroup_code(String pgroupCode) {
		pgroup_code = pgroupCode;
	}

	public String getPgroup_name() {
		return pgroup_name;
	}

	public void setPgroup_name(String pgroupName) {
		pgroup_name = pgroupName;
	}
	
}