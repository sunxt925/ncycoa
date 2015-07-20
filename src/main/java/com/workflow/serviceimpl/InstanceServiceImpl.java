package com.workflow.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.workflow.orm.InstanceInfo;
import com.workflow.service.InstanceService;
@Service("InstanceService")
@Transactional
public class InstanceServiceImpl implements InstanceService{

	public InstanceInfo loadInstanceById(String instanceid) throws Exception {
		// TODO Auto-generated method stub
		DBObject db = new DBObject();
		String sql = "select * from WF_INSTANCEINFO where instanceid=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		{ new Parameter.String(instanceid) };
		DataTable dt = db.runSelectQuery(sql, pp);
		InstanceInfo instanceInfo=new InstanceInfo();
		if (dt != null && dt.getRowsCount() == 1)
		{
			DataRow r = dt.get(0);
			instanceInfo.setInstanceid(instanceid);
			instanceInfo.setInitstaffcode(r.getString("initstaffcode"));
			instanceInfo.setInitstaffname(r.getString("initstaffname"));
			instanceInfo.setInitdate(r.getString("initdate"));
			instanceInfo.setPng(r.getString("png"));
			instanceInfo.setInstancename(r.getString("instancename"));
			return instanceInfo;
		}
		return null;
	}

	public boolean saveInstance(InstanceInfo instanceinfo) {
		// TODO Auto-generated method stub
		try
		{
		
			DBObject db = new DBObject();
			String sql = "insert into WF_INSTANCEINFO(instanceid,instancename,initstaffcode,initstaffname,initdate,png)" +
					" values (?,?,?,?,to_date(?,'yyyy-mm-dd'),?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{       new Parameter.String(instanceinfo.getInstanceid()), 
					new Parameter.String(instanceinfo.getInstancename()),
					new Parameter.String(instanceinfo.getInitstaffcode()),
					new Parameter.String(instanceinfo.getInitstaffname()), 
					new Parameter.String(instanceinfo.getInitdate()),
					new Parameter.String(instanceinfo.getPng())
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
	public boolean delete()
	{
		try{
			DBObject db = new DBObject();
			String sql = "delete from WF_INSTANCEINFO";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{  };
		
			
			if (db.run(sql,pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(Exception e)
		{
			return false;
		}
	}
	public boolean deleteById(String pid)
	{
		try{
			DBObject db = new DBObject();
			String sql = "delete from WF_INSTANCEINFO where instanceid=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(pid) };
		
			
			if (db.run(sql,pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(Exception e)
		{
			return false;
		}
	}
	public DataTable getAllInstanceList(String staffcode,String type)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from WF_INSTANCEINFO where initstaffcode= '"+staffcode+"' and png='"+type+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getInstanceList(int pageno, int perpage,String staffcode,String type)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||instanceid||'\">' as 选择,instancename as 实例名称,initstaffname as 发起人,initdate as 发起时间," +
					"'<a href=\"#\" onClick=view(\"'||instanceid||'\") class=\"easyui-linkbutton\">查看实例图</a><a href=\"#\" onClick=deleteinstance(\"'||instanceid||'\") class=\"easyui-linkbutton\">删除实例</a>' as 操作   from WF_INSTANCEINFO where initstaffcode= '"+staffcode+"' and png='"+type+"'";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
