package edu.cqu.ncycoa.plan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.Org;
import com.entity.system.StaffInfo;
import com.performance.ParaDataHelper;

public class PlanHelper {
	public static String getYearJson() {
		StringBuilder sb = new StringBuilder();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		if (month == 0 || month == 1) {
			sb.append("[{text:'").append(year - 1).append("',value:").append(year - 1).append("}");
			sb.append(",{text:'").append(year).append("',value:").append(year).append(",selected:true").append("}]");
		} else {
			sb.append("[{text:'").append(year).append("',value:").append(year).append(",selected:true").append("}]");
		}

		return sb.toString();
	}
	
	public static String getPeriodJson(){
		Map<Integer, String> maps = new TreeMap<Integer, String>();
		for (int i = 1; i <= 9; i++) {
			maps.put(i - 1, "M0" + i);
		}
		for (int i = 10; i <= 12; i++) {
			maps.put(i - 1, "M" + i);
		}
		
		StringBuilder sb = new StringBuilder();
		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH) - 1;
		sb.append("[");
		for (Integer tmp : maps.keySet()) {
			sb.append("{value:'").append(maps.get(tmp)).append("',text:'");
			sb.append(ParaDataHelper.code2Name(maps.get(tmp))).append("'");
			if(tmp.equals(month)){
				sb.append(",selected:true");
				sb.append("},");
				break;
			}
			sb.append("},");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	public static String periodConverter(String preperiod, String desc){
		int month = Integer.parseInt(preperiod.charAt(0) == '0' ? preperiod.substring(2) : preperiod.substring(1));
		if("D00.H00".equals(desc)){
			return "H0" + ((month - 1) / 6 + 1);
		}else if("D00.Y00".equals(desc)){
			return "Y01";
		}else if("D00.S00".equals(desc)){
			return "S0" + ((month - 1) / 3 + 1);
		}else if("D00.M00".equals(desc)){
			return preperiod;
		}
		
		return preperiod;
	}
	
	public static void insertTask(String staffcode){
		try {
			DBObject db=new DBObject();
			String value = db.executeOneValue("select count(*) from tbm_admindpt where staffcode='" + staffcode + "'");
			if("0".equals(value)){
				String sql="insert into tbm_admindpt(staffcode, adminmode) values(?, ?)";
				Parameter.SqlParameter[] paras = new Parameter.SqlParameter[]{new Parameter.String(staffcode), new Parameter.String("工作")};
				db.run(sql, paras);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertTask(String staffcode, String orgcode){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_admindpt where staffcode='" + staffcode + "' and adminmode='工作' and (orgcode is null or orgcode='"+orgcode+"')");
			
			String sql="insert into tbm_admindpt(staffcode,orgcode,adminmode) values(?,?,?)";
			Parameter.SqlParameter[] paras = new Parameter.SqlParameter[]{new Parameter.String(staffcode), new Parameter.String(orgcode), new Parameter.String("工作")};
			db.run(sql, paras);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delTask(String staffcode){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_admindpt where staffcode='" + staffcode + "' and adminmode='工作'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delTask(String staffcode, String orgcode){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_admindpt where staffcode='" + staffcode + "' and adminmode='工作' and orgcode='" + orgcode + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<StaffInfo> getStaff(){
		List<StaffInfo> returnValue = new ArrayList<StaffInfo>();
		Map<String, Integer> flag= new HashMap<String, Integer>();
		try {
			DBObject db=new DBObject();
			
			String sql="select staffcode from tbm_admindpt where adminmode = '工作'";
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
	
	public static List<Org> getOrgs(String staffcode){
		List<Org> returnValue = new ArrayList<Org>();
		try {
			DBObject db=new DBObject();
			
			String sql="select orgcode from tbm_admindpt where adminmode= '工作' and staffcode='" + staffcode + "'";
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
	
}
