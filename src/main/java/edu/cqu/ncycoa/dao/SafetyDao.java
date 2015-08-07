package edu.cqu.ncycoa.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class SafetyDao {

	public static String getAllRelevantParties() {
		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select relevantparty_name from SAFE_RELEVANTPARTY t";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<option value='"+r.getString("relevantparty_name")+"'>";
					try {
						result=result+r.getString("relevantparty_name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</option>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getPartyContentByName(String partyName) {
		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select CONTENT from SAFE_RELEVANTPARTY t where relevantparty_name='"+partyName+"'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
					DataRow r = dt.get(0);
					result=r.getString("content");
				}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getManagerByName(String partyName) {
		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select RESPONSIBELPERSON from SAFE_RELEVANTPARTY t where relevantparty_name='"+partyName+"'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
					DataRow r = dt.get(0);
					result=r.getString("RESPONSIBELPERSON");
				}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void setRemindFlag() {
		Date today=new Date();
		try {
			DBObject db = new DBObject();
			String sql = "select nextcheck_date+0,ID from SAFE_SPECIALEQUIPMENT t";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					Date nextDate=(Date)r.get(0);
					long days=(nextDate.getTime()-today.getTime())/(1000*60*60*24);
					if(days<=30){
						DBObject db2 = new DBObject();
						String sql2 = "update SAFE_SPECIALEQUIPMENT set REMIND=1 where ID="+r.getString("ID");				        
						db2.run(sql2);
					}else {
						DBObject db2 = new DBObject();
						String sql2 = "update SAFE_SPECIALEQUIPMENT set REMIND=0 where ID="+r.getString("ID");				        
						db2.run(sql2);
					}
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> getDocMap(String type,String id){
		Map<String, String> map=new HashMap<String, String>();
		if(type.equals("checkrecord")){
			DBObject db = new DBObject();
			String sql = "select CHECK_TIME+0,PLACE,HOST,PARTICIPANTS,CHECK_CONTENT,CHECK_RESULT,CHANGE_REQUIRE from SAFE_CHECKRECORD t where ID="+id;
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				DataRow r = dt.get(0);
				try {
					String time=r.getString("CHECK_TIME+0");
					time=time.substring(0, 10);
					map.put("time", time);
					map.put("place",r.getString("PLACE"));
					map.put("host", r.getString("HOST"));
					map.put("attend", r.getString("PARTICIPANTS"));
					map.put("content", r.getString("CHECK_CONTENT"));
					map.put("result", r.getString("CHECK_RESULT"));
					map.put("require", r.getString("CHANGE_REQUIRE"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(type.equals("relevant")){
			DBObject db = new DBObject();
			String sql = "select PARTY_NAME,PARTY_CONTENT,MANAGER,JD_TIME+0,CONTENT from SAFE_RELEVANTPARTY_JD t where ID="+id;
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				DataRow r = dt.get(0);
				try {
					String time=r.getString("JD_TIME+0");
					time=time.substring(0, 10);
					map.put("time", time);
					map.put("name",r.getString("PARTY_NAME"));
					map.put("yewu", r.getString("PARTY_CONTENT"));
					map.put("manager", r.getString("MANAGER"));
					map.put("content",r.getString("CONTENT"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(type.equals("dangerimprove")){
			DBObject db = new DBObject();
			String sql = "select DPLACE,DANGER_DATE+0,DANGER_TYPE,DANGER_CONTENT,IMPROVE_METHOD,IMPROVE_DEPART,IMPROVE_STATUS from SAFE_HIDDENDANGERIMPR t where ID="+id;
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				DataRow r = dt.get(0);
				try {
					String time=r.getString("DANGER_DATE+0");
					time=time.substring(0, 10);
					map.put("time", time);
					map.put("place",r.getString("DPLACE"));
					map.put("type", r.getString("DANGER_TYPE"));
					map.put("content", r.getString("DANGER_CONTENT"));
					map.put("change", r.getString("IMPROVE_METHOD"));
					map.put("result", r.getString("IMPROVE_STATUS"));
					map.put("manager", r.getString("IMPROVE_DEPART"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        
		return map;	
	}
}
