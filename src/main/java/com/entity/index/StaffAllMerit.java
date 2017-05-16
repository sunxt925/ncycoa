package com.entity.index;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.common.CodeDictionary;
import com.common.Format;
import com.dao.system.StaffDao;
import com.db.ConnectionPool;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
public class StaffAllMerit {
private String recno;
private String year;
private String period;
private String staffcode;
private String companycode;
private String departcode;
private String positioncode;
private String groupno;
private String allmeritfunc;
private double cvalue;
private double dvalue;
private double svalue;
private double changevalue;
private double staffallmerit;
private double keyindexscore;
private double baseindexscore;
private double commindexscore;
private double otherindexscore;
private String stafforg;
private String memo;
public StaffAllMerit(){
	
}
public StaffAllMerit(String recno){
	try{
		String sql="select * from tbm_staffallmerit where recno='"+recno+"'";
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql);
		if(dt!=null&&dt.getRowsCount()==1){
			DataRow row=dt.get(0);
			this.recno=recno;
			this.period=row.getString("period");
			this.staffcode=row.getString("staffcode");
			this.companycode=row.getString("companycode");
			this.departcode=row.getString("departcode");
			this.positioncode=row.getString("positioncode");
			this.groupno=row.getString("groupno");
			this.allmeritfunc=row.getString("allmeritfunc");
			this.cvalue=Double.parseDouble(Format.NullToZero(row.getString("cvalue")));
			this.dvalue=Double.parseDouble(Format.NullToZero(row.getString("dvalue")));
			this.svalue=Double.parseDouble(Format.NullToZero(row.getString("svalue")));
			this.changevalue=Double.parseDouble(Format.NullToZero(row.getString("changevalue")));
			this.staffallmerit=Double.parseDouble(Format.NullToZero(row.getString("staffallmerit")));
			this.keyindexscore=Double.parseDouble(Format.NullToZero(row.getString("keyindexscore")));
			this.baseindexscore=Double.parseDouble(Format.NullToZero(row.getString("baseindexscore")));
			this.commindexscore=Double.parseDouble(Format.NullToZero(row.getString("commindexscore")));
			this.otherindexscore=Double.parseDouble(Format.NullToZero(row.getString("otherindexscore")));
			this.memo=row.getString("memo");
		}
	}catch (Exception e){
		e.printStackTrace();
	}
}

public boolean insert(StaffAllMerit staffAllMerit){
	Connection conn=null;
	 PreparedStatement stmt = null;
	try {
		conn=ConnectionPool.getConnection();
		conn.setAutoCommit(false);
		String sql="insert into tbm_staffallmerit(recno,year,period,companycode,departcode,staffcode,positioncode,groupno,allmeritfunc,cvalue,dvalue,svalue,staffallmerit,keyindexscore,baseindexscore,commindexscore,otherindexscore,memo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		stmt=conn.prepareStatement(sql);
		stmt.setString(1,staffAllMerit.getRecno());
		stmt.setString(2,staffAllMerit.getYear());
		stmt.setString(3,staffAllMerit.getPeriod());
		stmt.setString(4,staffAllMerit.getCompanycode());
		stmt.setString(5,staffAllMerit.getDepartcode());
		stmt.setString(6,staffAllMerit.getStaffcode());
		stmt.setString(7,staffAllMerit.getPositioncode());
		stmt.setString(8,staffAllMerit.getGroupno());
		stmt.setString(9,staffAllMerit.getAllmeritfunc());
		stmt.setDouble(10,staffAllMerit.getCvalue());
		stmt.setDouble(11,staffAllMerit.getDvalue());
		stmt.setDouble(12,staffAllMerit.getSvalue());
		stmt.setDouble(13,staffAllMerit.getStaffallmerit());
		stmt.setDouble(14, staffAllMerit.getKeyindexscore());
		stmt.setDouble(15, staffAllMerit.getBaseindexscore());
		stmt.setDouble(16, staffAllMerit.getCommindexscore());
		stmt.setDouble(17, staffAllMerit.getOtherindexscore());
		stmt.setString(18, staffAllMerit.getMemo());
		stmt.execute();
		conn.commit();
		conn.close();
		return true;
	} catch (Exception e) {
		try {
			conn.rollback();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return false;
	}
}
public boolean delete(String year,String period,String flag){
    try {
	   DBObject db=new DBObject();
	   String sql="delete from tbm_staffallmerit where year=? and period=? and "+flag;
	   Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
		     new Parameter.String(year),
		     new Parameter.String(period)
	   };
	   return db.run(sql, pp);
    } catch (Exception e) {
	    e.printStackTrace();
	    return false;
    }
}
public List<StaffAllMerit> getAllMerit(String year,String period,String flag,String companycode,String departcode){
	try {
		DBObject db=new DBObject();
		String p="";
		if(!period.equals("")){
			p="period='"+period+"'";
		}else{
			p="1=1";
		}
		String filtercodition="";//过滤单位和部门
		if(companycode.equals("")&&departcode.equals("")){
			filtercodition="1=1";
		}
		if(!companycode.equals("")&&departcode.equals("")){
			filtercodition="companycode='"+companycode+"'";
		}
		if(!companycode.equals("")&&!departcode.equals("")){
			if(companycode.equals("NC.00")){
				companycode="NC.01.00";
			}
			filtercodition="companycode='"+companycode+"'  and  departcode='"+departcode+"'";
		}
		if(companycode.equals("")&&!departcode.equals("")){
			if(departcode.equals("NC.00")){
				filtercodition=" substr(companycode,7,2)<'20' ";
			}else{
				filtercodition="companycode='"+departcode+"'";
			}
			
		}
		
		
		String sql="select * from (select a.*,(select orgcode from base_orgmember where positioncode=a.positioncode and staffcode=a.staffcode) as stafforg from tbm_staffallmerit a) where   year=? and  "+p+"  and memo=? and "+filtercodition+" order by companycode,departcode,stafforg,positioncode,staffcode";
		String memo="";
		if(flag.equals("c")){
			memo="company";
		}
		if(flag.equals("d")){
			memo="depart";
		}
		if(flag.equals("s")){
			memo="staff";
		}
		Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
			    new Parameter.String(year),
			    new Parameter.String(memo)
		};
		List<StaffAllMerit> staffAllMerits=new ArrayList<StaffAllMerit>();
		DataTable dt=db.runSelectQuery(sql, pp);
		if(dt!=null&&dt.getRowsCount()>=1){
			DataRow row=null;
			for(int i=0;i<dt.getRowsCount();i++){
				row=dt.get(i);
				StaffAllMerit staffAllMerit=new StaffAllMerit();
				staffAllMerit.setRecno(row.getString("recno"));
				staffAllMerit.setYear(year);
				staffAllMerit.setCompanycode(row.getString("companycode"));
				staffAllMerit.setDepartcode(row.getString("departcode"));
				staffAllMerit.setStaffcode(row.getString("staffcode"));
				staffAllMerit.setPositioncode(row.getString("positioncode"));
				staffAllMerit.setGroupno(row.getString("groupno"));
				staffAllMerit.setAllmeritfunc(row.getString("allmeritfunc"));
				staffAllMerit.setStaffallmerit(Double.parseDouble(row.getString("staffallmerit")));
				staffAllMerit.setPeriod(period);
				staffAllMerit.setCvalue(Double.parseDouble(row.getString("cvalue")));
				staffAllMerit.setDvalue(Double.parseDouble(row.getString("dvalue")));
				staffAllMerit.setSvalue(Double.parseDouble(row.getString("svalue")));
				staffAllMerit.setChangevalue(Double.parseDouble(Format.NullToZero(row.getString("changevalue"))));
				staffAllMerit.setKeyindexscore(Double.parseDouble(Format.NullToZero(row.getString("keyindexscore"))));
				staffAllMerit.setBaseindexscore(Double.parseDouble(Format.NullToZero(row.getString("baseindexscore"))));
				staffAllMerit.setCommindexscore(Double.parseDouble(Format.NullToZero(row.getString("commindexscore"))));
				staffAllMerit.setOtherindexscore(Double.parseDouble(Format.NullToZero(row.getString("otherindexscore"))));
				staffAllMerit.setMemo(row.getString("memo"));
				if(flag.equals("s"))
					staffAllMerit.setStafforg(row.getString("stafforg"));
				staffAllMerits.add(staffAllMerit);
			}
		}
		return staffAllMerits;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
public String getMeritChangeJson(String year,String period,String companycode,String departcode){
	try {
		List<StaffAllMerit> staffAllMerits=getAllMerit(year, period,"s",companycode,departcode);
		StringBuilder sbuilder=new StringBuilder();
		sbuilder.append("[");
		StaffAllMerit laststaffAllMerit=null;
		for(StaffAllMerit staffAllMerit:staffAllMerits){
			String depart=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getDepartcode());
			String company=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getCompanycode());
			sbuilder.append("{");
			sbuilder.append("\"recno\":").append("\""+staffAllMerit.getRecno()+"\"").append(",");
			sbuilder.append("\"staffcode\":").append("\""+staffAllMerit.getStaffcode()+"\"").append(",");
			sbuilder.append("\"name\":").append("\""+CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", staffAllMerit.getStaffcode())+"\"").append(",");
			sbuilder.append("\"companymerit\":").append("\""+staffAllMerit.getCvalue()+"\"").append(",");
			sbuilder.append("\"departmerit\":").append("\""+staffAllMerit.getDvalue()+"\"").append(",");
			if(laststaffAllMerit!=null){
				if(staffAllMerit.getCompanycode().equals(laststaffAllMerit.getCompanycode())){
					sbuilder.append("\"company\":").append("\"\"").append(",");
				}else{
					sbuilder.append("\"company\":").append("\""+company+"\"").append(",");
				}
				if(staffAllMerit.getDepartcode().equals(laststaffAllMerit.getDepartcode())&&staffAllMerit.getCompanycode().equals(laststaffAllMerit.getCompanycode())){
					sbuilder.append("\"depart\":").append("\"\"").append(",");
				}else{
					sbuilder.append("\"depart\":").append("\""+depart+"\"").append(",");
				}
				if(staffAllMerit.getStafforg().equals(laststaffAllMerit.getStafforg())){
					sbuilder.append("\"stafforg\":").append("\"\"").append(",");
				}else{
					sbuilder.append("\"stafforg\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getStafforg()).replace(depart, "")+"\"").append(",");
				}
				if(staffAllMerit.getPositioncode().equals(laststaffAllMerit.getPositioncode())&&staffAllMerit.getCompanycode().equals(laststaffAllMerit.getCompanycode())&&staffAllMerit.getDepartcode().equals(laststaffAllMerit.getDepartcode())){
					sbuilder.append("\"position\":").append("\"\"").append(",");
				}else{
					sbuilder.append("\"position\":").append("\""+CodeDictionary.syscode_traslate("base_position", "positioncode", "positionname", staffAllMerit.getPositioncode())+"\"").append(",");
				}
			}else{
				sbuilder.append("\"company\":").append("\""+company+"\"").append(",");
				sbuilder.append("\"depart\":").append("\""+depart+"\"").append(",");
				sbuilder.append("\"position\":").append("\""+CodeDictionary.syscode_traslate("base_position", "positioncode", "positionname", staffAllMerit.getPositioncode())+"\"").append(",");
				sbuilder.append("\"stafforg\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getStafforg()).replace(depart, "")+"\"").append(",");
		    }
		//	sbuilder.append("\"depart\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname", staffAllMerit.getCompanycode())+"\"").append(",");
			sbuilder.append("\"departcode\":").append("\""+staffAllMerit.getCompanycode()+"\"").append(",");
			//sbuilder.append("\"position\":").append("\""+CodeDictionary.syscode_traslate("base_position", "positioncode", "positionname",staffAllMerit.getPositioncode())+"\"").append(",");
			sbuilder.append("\"keyindexscore\":").append("\""+staffAllMerit.getKeyindexscore()+"\"").append(",");
			sbuilder.append("\"baseindexscore\":").append("\""+staffAllMerit.getBaseindexscore()+"\"").append(",");
			sbuilder.append("\"commindexscore\":").append("\""+staffAllMerit.getCommindexscore()+"\"").append(",");
		    sbuilder.append("\"othermerit\":").append("\""+staffAllMerit.getOtherindexscore()+"\"").append(",");
		    sbuilder.append("\"addscore\":").append("\""+staffAllMerit.getChangevalue()+"\"").append(",");
			sbuilder.append("\"staffallmerit\":").append("\""+staffAllMerit.getStaffallmerit()+"\"");
			sbuilder.append("}");
			sbuilder.append(",");
			laststaffAllMerit=staffAllMerit;
		}
		sbuilder.delete(sbuilder.length()-1, sbuilder.length());
		sbuilder.append("]");
		return sbuilder.toString();
	} catch (Exception e) {
		return "";
		
	}
}
public String getMeritJson(String year,String period,String flag){
	return getMeritJson(year,period,flag,"");
}
public String getMeritJson(String year,String period,String flag,String companycode){
	return getMeritJson(year,period,flag,companycode,"");
}
public String getIndividualMeritJson(String year,String period,String end_mon,String staffcode){
	try {
		if(end_mon.equals("")||end_mon==null||end_mon.equals("null")){
			end_mon=period;
		}
		String sql="select * from (select a.*,(select orgcode from base_orgmember where positioncode=a.positioncode and staffcode=a.staffcode) as stafforg from tbm_staffallmerit a) where   year=? and  period>=? and period<=?  and memo=? and staffcode=? order by year,period";
		Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
			new Parameter.String(year),
			new Parameter.String(period),
			new Parameter.String(end_mon),
			new Parameter.String("staff"),
			new Parameter.String(staffcode)
		};
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql, pp);
		StringBuilder sbuilder=new StringBuilder();
		sbuilder.append("[");
		if(dt!=null&&dt.getRowsCount()>=1){
			DataRow row=null;
			for(int i=0;i<dt.getRowsCount();i++){
				row=dt.get(i);
				StaffAllMerit staffAllMerit=getStaffAllMeritByRow(row);
				String depart=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getDepartcode());
				String company=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getCompanycode());
				sbuilder.append("{");
				//sbuilder.append("\"company\":").append("\""+company+"\"").append(",");
				//sbuilder.append("\"depart\":").append("\""+depart+"\"").append(",");
				//sbuilder.append("\"stafforg\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getStafforg()).replace(depart, "")+"\"").append(",");
		       // sbuilder.append("\"position\":").append("\""+CodeDictionary.syscode_traslate("base_position", "positioncode", "positionname",staffAllMerit.getPositioncode())+"\"").append(",");
	           // sbuilder.append("\"name\":").append("\""+CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",staffAllMerit.getStaffcode())+"\"").append(",");
				sbuilder.append("\"mon\":").append("\""+MonthCovert.getMonth(row.getString("period"))+"\"").append(",");
				sbuilder.append("\"companymerit\":").append("\""+staffAllMerit.getCvalue()+"\"").append(",");
				sbuilder.append("\"departmerit\":").append("\""+staffAllMerit.getDvalue()+"\"").append(",");
				sbuilder.append("\"keyindexscore\":").append("\""+staffAllMerit.getKeyindexscore()+"\"").append(",");
				sbuilder.append("\"baseindexscore\":").append("\""+staffAllMerit.getBaseindexscore()+"\"").append(",");
				sbuilder.append("\"commindexscore\":").append("\""+staffAllMerit.getCommindexscore()+"\"").append(",");
				sbuilder.append("\"othermerit\":").append("\""+staffAllMerit.getOtherindexscore()+"\"").append(",");
				sbuilder.append("\"addscore\":").append("\""+staffAllMerit.getChangevalue()+"\"").append(",");
				sbuilder.append("\"staffallmerit\":").append("\""+staffAllMerit.getStaffallmerit()+"\"");
				sbuilder.append("}").append(",");
			}
			sbuilder.delete(sbuilder.length()-1, sbuilder.length());
			
		}
		sbuilder.append("]");
		return sbuilder.toString();
	} catch (Exception e) {
		e.printStackTrace();
		return "[]";
	}
}
//通过返回的数据行数据row构造StaffAllMerit对象
public StaffAllMerit getStaffAllMeritByRow(DataRow row){
	try {
		StaffAllMerit staffAllMerit=new StaffAllMerit();
		staffAllMerit.setRecno(row.getString("recno"));
		staffAllMerit.setYear(year);
		staffAllMerit.setCompanycode(row.getString("companycode"));
		staffAllMerit.setDepartcode(row.getString("departcode"));
		staffAllMerit.setStaffcode(row.getString("staffcode"));
		staffAllMerit.setPositioncode(row.getString("positioncode"));
		staffAllMerit.setGroupno(row.getString("groupno"));
		staffAllMerit.setAllmeritfunc(row.getString("allmeritfunc"));
		staffAllMerit.setStaffallmerit(Double.parseDouble(row.getString("staffallmerit")));
		staffAllMerit.setPeriod(period);
		staffAllMerit.setCvalue(Double.parseDouble(row.getString("cvalue")));
		staffAllMerit.setDvalue(Double.parseDouble(row.getString("dvalue")));
		staffAllMerit.setSvalue(Double.parseDouble(row.getString("svalue")));
		staffAllMerit.setChangevalue(Double.parseDouble(Format.NullToZero(row.getString("changevalue"))));
		staffAllMerit.setKeyindexscore(Double.parseDouble(Format.NullToZero(row.getString("keyindexscore"))));
		staffAllMerit.setBaseindexscore(Double.parseDouble(Format.NullToZero(row.getString("baseindexscore"))));
		staffAllMerit.setCommindexscore(Double.parseDouble(Format.NullToZero(row.getString("commindexscore"))));
		staffAllMerit.setOtherindexscore(Double.parseDouble(Format.NullToZero(row.getString("otherindexscore"))));
		staffAllMerit.setMemo(row.getString("memo"));
		staffAllMerit.setStafforg(row.getString("stafforg"));
		return staffAllMerit;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	
}
public String getDepartMeritJson(String year,String period,String end_mon,String companycode,String departcode){
	try {
		if(end_mon.equals("")||end_mon==null||end_mon.equals("null")){
			end_mon=period;
		}
		String filtercodition="companycode='"+companycode+"'  and  departcode='"+departcode+"'";
		String sql="select * from (select a.*,(select orgcode from base_orgmember where positioncode=a.positioncode and staffcode=a.staffcode) as stafforg from tbm_staffallmerit a) where   year=? and  period>=? and period<=?  and memo=? and "+filtercodition+" order by companycode,departcode,stafforg,positioncode,staffcode,year,period";
		Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
			new Parameter.String(year),
			new Parameter.String(period),
			new Parameter.String(end_mon),
			new Parameter.String("depart")
		};
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql, pp);
		StringBuilder sbuilder=new StringBuilder();
		sbuilder.append("[");
		if(dt!=null&&dt.getRowsCount()>=1){
			DataRow row=null;
			for(int i=0;i<dt.getRowsCount();i++){
				row=dt.get(i);
				StaffAllMerit staffAllMerit=getStaffAllMeritByRow(row);
				String depart=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getDepartcode());
				String company=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getCompanycode());
				sbuilder.append("{");
				sbuilder.append("\"depart\":").append("\""+depart+"\"").append(",");
				sbuilder.append("\"company\":").append("\""+company+"\"").append(",");
				sbuilder.append("\"mon\":").append("\""+MonthCovert.getMonth(row.getString("period"))+"\"").append(",");
				sbuilder.append("\"keyindexscore\":").append("\""+staffAllMerit.getKeyindexscore()+"\"").append(",");
				sbuilder.append("\"baseindexscore\":").append("\""+staffAllMerit.getBaseindexscore()+"\"").append(",");
				sbuilder.append("\"commindexscore\":").append("\""+staffAllMerit.getCommindexscore()+"\"").append(",");
				sbuilder.append("\"otherindexscore\":").append("\""+staffAllMerit.getOtherindexscore()+"\"").append(",");
				sbuilder.append("\"addscore\":").append("\""+staffAllMerit.getChangevalue()+"\"").append(",");
				sbuilder.append("\"staffallmerit\":").append("\""+staffAllMerit.getStaffallmerit()+"\"");
				sbuilder.append("}").append(",");
			}
			sbuilder.delete(sbuilder.length()-1, sbuilder.length());
			
		}
		sbuilder.append("]");
		return sbuilder.toString();
	} catch (Exception e) {
		e.printStackTrace();
		return "[]";
	}
}
public String getMeritJson(String year,String period,String flag,String companycode,String departcode){
	try {
		List<StaffAllMerit> staffAllMerits=getAllMerit(year, period,flag,companycode,departcode);
		StringBuilder sbuilder=new StringBuilder();
		
		sbuilder.append("[");
		for(int i=0;i<staffAllMerits.size();i++){
		//for(StaffAllMerit staffAllMerit:staffAllMerits){
			StaffAllMerit staffAllMerit=staffAllMerits.get(i);
			StaffAllMerit lastStaffAllMerit=null;
			
			sbuilder.append("{");
			if(flag.equals("c")){
				sbuilder.append("\"company\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getCompanycode())+"\"").append(",");
				sbuilder.append("\"companycode\":").append("\""+staffAllMerit.getCompanycode()+"\"").append(",");
				sbuilder.append("\"recno\":").append("\""+staffAllMerit.getRecno()+"\"").append(",");
				
			}
			if(flag.equals("d")){
				String depart=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getDepartcode());
				String company=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getCompanycode());
				if(i==0){
					sbuilder.append("\"depart\":").append("\""+depart+"\"").append(",");
					sbuilder.append("\"company\":").append("\""+company+"\"").append(",");
				}else{
					lastStaffAllMerit=staffAllMerits.get(i-1);
					if(staffAllMerit.getCompanycode().equals(lastStaffAllMerit.getCompanycode())){
						sbuilder.append("\"company\":").append("\"\"").append(",");
					}else{
						sbuilder.append("\"company\":").append("\""+company+"\"").append(",");
					}
					if(staffAllMerit.getDepartcode().equals(lastStaffAllMerit.getDepartcode())){
						sbuilder.append("\"depart\":").append("\"\"").append(",");
					}else{
						sbuilder.append("\"depart\":").append("\""+depart+"\"").append(",");
					}
				}
				}
			if(flag.equals("s")){
				String depart=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getDepartcode());
				String company=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getCompanycode());
				if(i==0){
					sbuilder.append("\"company\":").append("\""+company+"\"").append(",");
					sbuilder.append("\"depart\":").append("\""+depart+"\"").append(",");
					sbuilder.append("\"stafforg\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getStafforg()).replace(depart, "")+"\"").append(",");
		            sbuilder.append("\"position\":").append("\""+CodeDictionary.syscode_traslate("base_position", "positioncode", "positionname",staffAllMerit.getPositioncode())+"\"").append(",");
                    
				}else{
					lastStaffAllMerit=staffAllMerits.get(i-1);
					if(staffAllMerit.getCompanycode().equals(lastStaffAllMerit.getCompanycode())){
						sbuilder.append("\"company\":").append("\"\"").append(",");
					}else{
						sbuilder.append("\"company\":").append("\""+company+"\"").append(",");
					}
					if(staffAllMerit.getDepartcode().equals(lastStaffAllMerit.getDepartcode())){
						sbuilder.append("\"depart\":").append("\"\"").append(",");
					}else{
						sbuilder.append("\"depart\":").append("\""+depart+"\"").append(",");
					}
					if(staffAllMerit.getPositioncode().equals(lastStaffAllMerit.getPositioncode())){
						sbuilder.append("\"position\":").append("\"\"").append(",");
					}else{
						sbuilder.append("\"position\":").append("\""+CodeDictionary.syscode_traslate("base_position", "positioncode", "positionname",staffAllMerit.getPositioncode())+"\"").append(",");
					}
					if(staffAllMerit.getStafforg().equals(lastStaffAllMerit.getStafforg())){
						sbuilder.append("\"stafforg\":").append("\"\"").append(",");
					}else{
						sbuilder.append("\"stafforg\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",staffAllMerit.getStafforg()).replace(depart, "")+"\"").append(",");
					}
				}
				sbuilder.append("\"name\":").append("\""+CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",staffAllMerit.getStaffcode())+"\"").append(",");
				sbuilder.append("\"companymerit\":").append("\""+staffAllMerit.getCvalue()+"\"").append(",");
				sbuilder.append("\"departmerit\":").append("\""+staffAllMerit.getDvalue()+"\"").append(",");
			}
			
			
			sbuilder.append("\"keyindexscore\":").append("\""+staffAllMerit.getKeyindexscore()+"\"").append(",");
			sbuilder.append("\"baseindexscore\":").append("\""+staffAllMerit.getBaseindexscore()+"\"").append(",");
			sbuilder.append("\"commindexscore\":").append("\""+staffAllMerit.getCommindexscore()+"\"").append(",");
		    if(!flag.equals("s")){
		    	sbuilder.append("\"otherindexscore\":").append("\""+staffAllMerit.getOtherindexscore()+"\"").append(",");
		    }else{
		    	sbuilder.append("\"othermerit\":").append("\""+staffAllMerit.getOtherindexscore()+"\"").append(",");
		    }
			sbuilder.append("\"addscore\":").append("\""+staffAllMerit.getChangevalue()+"\"").append(",");
			sbuilder.append("\"staffallmerit\":").append("\""+staffAllMerit.getStaffallmerit()+"\"");
			sbuilder.append("}");
			sbuilder.append(",");
		}
		sbuilder.delete(sbuilder.length()-1, sbuilder.length());
		sbuilder.append("]");
		return sbuilder.toString();
	} catch (Exception e) {
		return "";
		
	}
}
public Map<String,Double> getStaffallmeritValue(String staffcode,String year,String memo){
	try {
		DBObject db=new DBObject();
		Map<String, Double> map=new HashMap<String, Double>();
		String sql="select period,staffallmerit from tbm_staffallmerit where  staffcode=? and  year=?  and memo=? order by period";
		Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.String(staffcode),
			    new Parameter.String(year),
			    new Parameter.String(memo)
		};
		DataTable dt=db.runSelectQuery(sql, pp);
		if(dt!=null&&dt.getRowsCount()>=0){
			DataRow row=null;
			for(int i=0;i<dt.getRowsCount();i++){
				row=dt.get(i);
				String period=row.getString("period");
			    double staffallmerit=Double.parseDouble(row.getString("staffallmerit"));
			    map.put(period, staffallmerit);
			}
			if(map.size()<12){
				for(int j=1;j<13;j++){
					String p="";
					if(j<10){
						p="M0"+j;
					}else{
						p="M"+j;
					}
					if(map.get(p)==null){
						map.put(p, 0.0);
					}
				}
			}
		}
		return map;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
public String getAllmeritJson(String year,String staffname,String orgcode){
	try {
		Map<String,AllMeritGroupMember> allMeritGroupMembers=AllMeritGroupMember.getAllmember();
		
		//Map<String,AllMeritGroupMember> allsearhlistMap=new HashMap<String, AllMeritGroupMember>();
		Map<String,AllMeritGroupMember> allsearhlistMap=new LinkedHashMap<String, AllMeritGroupMember>();
		List<String> staffcodelist=null;
		/**
		 * 根据员工名字进行查询
		 */
		if(!staffname.equals("")&&orgcode.equals("")){
			staffcodelist=StaffDao.getstafflistByname(staffname);
		}
		 /**
         *根据orgcode查询 
         */
		if(!orgcode.equals("")){
			staffcodelist=StaffDao.getstafflistByorg(orgcode);
	    }	
        if(!staffname.equals("")||!orgcode.equals("")){
			for(String staffcode:staffcodelist){
				if(allMeritGroupMembers.get(staffcode)!=null){
					allsearhlistMap.put(staffcode, allMeritGroupMembers.get(staffcode));
				}
				
			}
			allMeritGroupMembers=allsearhlistMap;
		}
        /***/
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("[");
		Iterator iterator=allMeritGroupMembers.entrySet().iterator();
		AllMeritGroupMember lastaAllMeritGroupMember=null;
		while(iterator.hasNext()){
			Map.Entry entry=(Map.Entry)iterator.next();
			AllMeritGroupMember allMeritGroupMember=(AllMeritGroupMember)entry.getValue();
			Map<String, Double> staffAllMerits=getStaffallmeritValue(allMeritGroupMember.getStaffCode(), year, "staff");
			sBuilder.append("{");
			if(lastaAllMeritGroupMember!=null){
				if(allMeritGroupMember.getOrgCode().equals(lastaAllMeritGroupMember.getOrgCode())){
					sBuilder.append("\"orgname\":").append("\"\"").append(",");
				}else{
					sBuilder.append("\"orgname\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",allMeritGroupMember.getOrgCode())+"\"").append(",");
				}
				if(allMeritGroupMember.getPositionCode().equals(lastaAllMeritGroupMember.getPositionCode())&&allMeritGroupMember.getOrgCode().equals(lastaAllMeritGroupMember.getOrgCode())){
					sBuilder.append("\"positionname\":").append("\"\"").append(",");
				}else{
					sBuilder.append("\"positionname\":").append("\""+CodeDictionary.syscode_traslate("base_position", "positioncode", "positionname",allMeritGroupMember.getPositionCode())+"\"").append(",");
				}
				
			}else{
				sBuilder.append("\"orgname\":").append("\""+CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",allMeritGroupMember.getOrgCode())+"\"").append(",");
				sBuilder.append("\"positionname\":").append("\""+CodeDictionary.syscode_traslate("base_position", "positioncode", "positionname",allMeritGroupMember.getPositionCode())+"\"").append(",");
			}
			sBuilder.append("\"name\":").append("\""+CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",allMeritGroupMember.getStaffCode())+"\"").append(",");
			for(int i=0;i<staffAllMerits.size();i++){
				if(i<9){
					sBuilder.append("\"m0"+(i+1)+"\":").append("\"<a onclick=detial('"+allMeritGroupMember.getStaffCode()+"','"+(i+1)+"')>"+staffAllMerits.get("M0"+(i+1))+"</a>\"").append(",");
				}else{
					sBuilder.append("\"m"+(i+1)+"\":").append("\"<a onclick=detial('"+allMeritGroupMember.getStaffCode()+"','"+(i+1)+"')>"+staffAllMerits.get("M"+(i+1))+"</a>\"").append(",");
				}
				
			}
			sBuilder.delete(sBuilder.length()-1, sBuilder.length());
			sBuilder.append("}").append(",");
			lastaAllMeritGroupMember=allMeritGroupMember;
		}

		sBuilder.delete(sBuilder.length()-1, sBuilder.length());
		sBuilder.append("]");
		
		return sBuilder.toString();
	} catch (Exception e) {
		e.printStackTrace();
		return "";
	}
}
public boolean changescore(double changescore,String recno,String flag){
	 StaffAllMerit staffallmerit=new StaffAllMerit(recno);
	 double merit = changescore+staffallmerit.getStaffallmerit();
	 double changes=0;
	 if(staffallmerit.getChangevalue()!=0){
		 changes = changescore+staffallmerit.getChangevalue();
	 }else{
		 changes = changescore;
	 }
	 
	 Connection conn=null;
	 PreparedStatement stmt = null;
	try {
		conn=ConnectionPool.getConnection();
		conn.setAutoCommit(false);
		String sql="update tbm_staffallmerit  set changevalue=?,staffallmerit=? where recno=?";
		stmt=conn.prepareStatement(sql);
		stmt.setDouble(1, changes);
		stmt.setDouble(2, merit);
		stmt.setString(3, recno);
		stmt.execute();
		conn.commit();
		return true;
	} catch (Exception e) {
		try {
			conn.rollback();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return false;
	}
}
/**
 * 获取机构汇总后得分
 * @param companycode
 * @param year
 * @param period
 * @param flag
 * @return
 */
@SuppressWarnings("finally")
public static Double getStaffMeritScore(String companycode,String year,String period,String flag) {
	DBObject db=new DBObject();
	
	String sql="select staffallmerit from tbm_staffallmerit  where companycode=?  and year=? and period=? and  memo=? ";
	Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
			new Parameter.String(companycode),
		    new Parameter.String(year),
		    new Parameter.String(period),
		    new Parameter.String(flag)
	};
	double score=0;
	try{
		DataTable dt=db.runSelectQuery(sql, pp);
		if(dt!=null&&dt.getRowsCount()==1){
			score = Double.parseDouble(dt.get(0).getString("staffallmerit"));
		    return score;
		}else{
			return null;
		}
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
	
}
public String getRecno() {
	return recno;
}
public void setRecno(String recno) {
	this.recno = recno;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}

public String getPeriod() {
	return period;
}
public void setPeriod(String period) {
	this.period = period;
}
public String getStaffcode() {
	return staffcode;
}
public void setStaffcode(String staffcode) {
	this.staffcode = staffcode;
}

public String getCompanycode() {
	return companycode;
}
public void setCompanycode(String companycode) {
	this.companycode = companycode;
}
public String getDepartcode() {
	return departcode;
}
public void setDepartcode(String departcode) {
	this.departcode = departcode;
}

public String getPositioncode() {
	return positioncode;
}
public void setPositioncode(String positioncode) {
	this.positioncode = positioncode;
}
public String getGroupno() {
	return groupno;
}
public void setGroupno(String groupno) {
	this.groupno = groupno;
}
public String getAllmeritfunc() {
	return allmeritfunc;
}
public void setAllmeritfunc(String allmeritfunc) {
	this.allmeritfunc = allmeritfunc;
}
public double getCvalue() {
	return cvalue;
}
public void setCvalue(double cvalue) {
	this.cvalue = cvalue;
}
public double getDvalue() {
	return dvalue;
}
public void setDvalue(double dvalue) {
	this.dvalue = dvalue;
}
public double getSvalue() {
	return svalue;
}
public void setSvalue(double svalue) {
	this.svalue = svalue;
}

public double getChangevalue() {
	return changevalue;
}
public void setChangevalue(double changevalue) {
	this.changevalue = changevalue;
}
public double getStaffallmerit() {
	return staffallmerit;
}
public void setStaffallmerit(double staffallmerit) {
	this.staffallmerit = staffallmerit;
}

public double getKeyindexscore() {
	return keyindexscore;
}
public void setKeyindexscore(double keyindexscore) {
	this.keyindexscore = keyindexscore;
}
public double getBaseindexscore() {
	return baseindexscore;
}
public void setBaseindexscore(double baseindexscore) {
	this.baseindexscore = baseindexscore;
}
public double getCommindexscore() {
	return commindexscore;
}
public void setCommindexscore(double commindexscore) {
	this.commindexscore = commindexscore;
}

public double getOtherindexscore() {
	return otherindexscore;
}
public void setOtherindexscore(double otherindexscore) {
	this.otherindexscore = otherindexscore;
}
public String getMemo() {
	return memo;
}
public void setMemo(String memo) {
	this.memo = memo;
}
public String getStafforg() {
	return stafforg;
}
public void setStafforg(String stafforg) {
	this.stafforg = stafforg;
}

}

