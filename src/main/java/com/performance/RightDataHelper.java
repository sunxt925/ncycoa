package com.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.Org;
import com.entity.system.StaffInfo;

public class RightDataHelper {
	public static List<StaffInfo> getStaffByTaskType(String taskType){
		List<StaffInfo> returnValue = new ArrayList<StaffInfo>();
		Map<String, Integer> flag= new HashMap<String, Integer>();
		try {
			DBObject db=new DBObject();
			
			String sql="select staffcode from tbm_meritinputtaskperson where tasktype = '" + taskType + "'";
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null){
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow r=dt.get(i);
					
					String staffcode = r.getString("staffcode");
					if(flag.get(staffcode) == null){
						flag.put(staffcode, 1);
						returnValue.add(new StaffInfo(staffcode));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public static List<Org> getOrgs(String staffcode, String taskType){
		List<Org> returnValue = new ArrayList<Org>();
		try {
			DBObject db=new DBObject();
			
			String sql="select orgcode from tbm_meritinputtaskperson where tasktype = '" + taskType + "' and staffcode='" + staffcode + "'";
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null){
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow r=dt.get(i);
					String tmp = r.getString("orgcode");
					if(tmp != null && !"".equals(tmp)){
						returnValue.add(new Org(r.getString("orgcode")));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public static void insertTask(String staffcode, String taskType){
		try {
			DBObject db=new DBObject();
			String value =db.executeOneValue("select count(*) from tbm_meritinputtaskperson where tasktype = '" + taskType + "' and staffcode='" + staffcode + "'");
			if("0".equals(value)){
				String sql="insert into tbm_meritinputtaskperson(staffcode,tasktype,adminmode) values(?,?,0)";
				Parameter.SqlParameter[] paras = new Parameter.SqlParameter[]{new Parameter.String(staffcode), new Parameter.String(taskType)};
				db.run(sql, paras);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertTask(String staffcode, String taskType, String orgcode){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_meritinputtaskperson where tasktype = '" + taskType + "' and staffcode='" + staffcode + "' and orgcode is null");
			
			String sql="insert into tbm_meritinputtaskperson(staffcode,tasktype,orgcode,adminmode) values(?,?,?,0)";
			Parameter.SqlParameter[] paras = new Parameter.SqlParameter[]{new Parameter.String(staffcode), new Parameter.String(taskType), new Parameter.String(orgcode)};
			db.run(sql, paras);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delTask(String staffcode, String taskType){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_meritinputtaskperson where tasktype = '" + taskType + "' and staffcode='" + staffcode + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delTask(String staffcode, String taskType, String orgcode){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_meritinputtaskperson where tasktype = '" + taskType + "' and staffcode='" + staffcode + "' and orgcode='" + orgcode + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
