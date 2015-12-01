package com.entity.index;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.common.Format;
import com.common.IndexCode;
import com.db.ConnectionPool;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class AllMeritCollection {

	public static String getCollectionJson(String yearstr){
		try {
			List<Collectentity> collectentities=getCollectionList(yearstr);
			StringBuilder sbuilder=new StringBuilder();
			sbuilder.append("[");
			for(Collectentity collectentity:collectentities){
				sbuilder.append("{");
				sbuilder.append("\"year\":").append("\""+collectentity.getYear()+"\"").append(",");
				sbuilder.append("\"period\":").append("\""+collectentity.getPeriod()+"\"").append(",");
				sbuilder.append("\"objectnum\":").append("\""+collectentity.getCollectedobjectnum()+"/"+collectentity.getNeedcollectobjectnum()+"\"").append(",");
				if(collectentity.getCollectedobjectnum()==collectentity.getNeedcollectobjectnum())
				    sbuilder.append("\"state\":").append("1").append(",");
				else 
					sbuilder.append("\"state\":").append("0").append(",");
				sbuilder.append("\"op\":").append("\"<button></button>\"");
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
	public static List<Collectentity> getCollectionList(String yearstr){
		try {
			DBObject db=new DBObject();
			String sql="select count(staffcode) as staffnum from tbm_allmeritgroupmember";
			DataTable dt=db.runSelectQuery(sql);
			List<Collectentity> collectentities=new ArrayList<Collectentity>();
			int staffnum=0;
			if(dt!=null&&dt.getRowsCount()==1){
				staffnum=Integer.parseInt(dt.get(0).getString("staffnum"));//需要汇总的对象数
			}
			for(int i=0;i<12;i++){
				int month=i+1;
				String period="M";
				if(month<10){
					period+="0"+month;
				}else{
					period+=String.valueOf(month);
				}
				String sql2="select count(staffcode) as staffnum from tbm_staffallmerit where year='"+yearstr+"' and period='"+period+"'";
				DataTable dt2=db.runSelectQuery(sql2);
				int hascollectnum=0;
				if(dt2!=null&&dt2.getRowsCount()==1){
					hascollectnum=Integer.parseInt(dt2.get(0).getString("staffnum"));//已经汇总的对象数
				}
				Collectentity collectentity=new Collectentity();
				collectentity.setYear(yearstr);
				collectentity.setPeriod(period);
				collectentity.setCollectedobjectnum(hascollectnum);
				collectentity.setNeedcollectobjectnum(staffnum);
				collectentity.setStaffneedcount(getObjectneedcount(yearstr, period, "staff", "0"));
				collectentity.setStaffsumcount(getObjectsumcount("S"));
				collectentity.setDeparneedcount(getObjectneedcount(yearstr, period, "depart", "0"));
				collectentity.setDepartsumcount(getObjectsumcount("D"));
				collectentity.setCompanyneedcount(getObjectneedcount(yearstr, period, "company", "0"));
				collectentity.setCompanysumcount(getObjectsumcount("C"));
				collectentities.add(collectentity);
				
			}
			return collectentities;
			
		} catch (Exception e) {
			return null;
		}
	}
	public static boolean meritCollect(String year,String period,String operator,String op){
		try {
			Map<String,AllMeritGroupMember> allMeritGroupMembers=AllMeritGroupMember.getAllmember();
			if(op.equals("recollect")){
				new StaffAllMerit().delete(year, period,"memo!='company'");
			}
			int count=0;
			Iterator iterator=allMeritGroupMembers.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry entry=(Map.Entry)iterator.next();
				AllMeritGroupMember allMeritGroupMember=(AllMeritGroupMember)entry.getValue();	
				AllMeritGroup allMeritGroup=new AllMeritGroup(allMeritGroupMember.getGroupNo());
				String allmeritfunc=allMeritGroup.getAllmeritfunc();
				//解析综合绩效模板
				String[] s1=allmeritfunc.split("=");
				String[] s2=s1[0].substring(1, s1[0].length()-1).split(",");//解析cxx,dxx,sxx
				String[] s3=s1[1].substring(1, s1[1].length()-1).split(",");//解析权重
				//获取对象对应的指标体系
				/*分四种情况
				 * （C00,D00,S00）
				 * （C00,Davg,S00）
				 * （C00,D00,Sx1,Sx2）
				 * （C00,Davg,Sx1,Sx2）
				 */
				if(s2.length==3){
					/*处理以下两种情况
					 * （C00,D00,S00）
				     * （C00,Davg,S00）
					 */
						StaffAllMerit staffAllMerit=getStaffAllMerit(s2[1],allMeritGroupMember, s3, year, period, allmeritfunc, "1");
						if(staffAllMerit!=null){
                    		if(new StaffAllMerit().insert(staffAllMerit)){
    							count++;
    						}
                    	}
				}else if(s2.length==4){
					/*处理以下两种情况
					 * （C00,D00,Sx1,Sx2）
				     * （C00,Davg,Sx1,Sx2）
					 */
                    	StaffAllMerit staffAllMerit=getStaffAllMerit(s2[1],allMeritGroupMember, s3, year, period, allmeritfunc, "1","2");
                    	if(staffAllMerit!=null){
                    		if(new StaffAllMerit().insert(staffAllMerit)){
    							count++;
    							
    						}
                    	}
						
				}
			}
		
			//记录日志
			if(count!=0){
				//汇总机构得分
				if(collectCompanyMerit(year, period, "depart"))
				{
					//虚拟部门综合绩效得分汇总
					virtualDepartCollet(year, period);
					return writeCollectLog(count,year,period,operator);
				}else{
					return false;
				}
				
			}else{
				return false;
			}
			
			
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 公司绩效汇总
	 * @param year
	 * @param period
	 * @param operator
	 * @param op
	 * @return
	 */
	public static boolean companyMeritCollect(String year,String period,String operator,String op){
		if(op.equals("recollect")){
			new StaffAllMerit().delete(year, period,"memo='company'");
		}
		if(collectCompanyMerit(year, period, "company")){
			return writeCollectLog(10,year,period,operator);
		}else{
			return false;
		}
	}
    public static StaffAllMerit getStaffAllMerit(String dclass,AllMeritGroupMember allMeritGroupMember,String[] s3,String year,String period,String allmeritfunc,String flag){
		
		return getStaffAllMerit(dclass,allMeritGroupMember, s3, year, period, allmeritfunc, flag,"0");
	}
	public static StaffAllMerit getStaffAllMerit(String dclass,AllMeritGroupMember allMeritGroupMember,String[] s3,String year,String period,String allmeritfunc,String flag,String flag2){
		try {
			
			    
				//String carchcode=IndexArchUser.getIndexarchcodeByobject(getOrg(allMeritGroupMember,"company"), flag);
				String darchcode="";
				if(dclass.equals("D00")){
					if(!getOrg(allMeritGroupMember,"depart").equals("-1"))//-1表示为虚拟部门
					darchcode=IndexArchUser.getIndexarchcodeByobject(getOrg(allMeritGroupMember,"depart"), flag);
				}
				
				String sarchcode=IndexArchUser.getIndexarchcodeByobject(allMeritGroupMember.getStaffCode(), flag);
				String sarchcode2="";
				if(flag2.equals("2")){
				sarchcode2=IndexArchUser.getIndexarchcodeByobject(allMeritGroupMember.getStaffCode(), flag2);
				}
				double cwight=Double.parseDouble(s3[0]);
				double dwight=Double.parseDouble(s3[1]);
				double swight=Double.parseDouble(s3[2]);
				double swight2=0;
				if(flag2.equals("2")){
				swight2=Double.parseDouble(s3[3]);
				}
				Double cscore=null;
				if(cwight!=0){
					cscore = StaffAllMerit.getStaffMeritScore(getOrg(allMeritGroupMember,"company"), year, period, "company");
					//cscore=IndexScore.getScorevalue(getOrg(allMeritGroupMember,"company"),carchcode, year, period);
				}else{
					cscore=0.0;
				}
				if(cscore==null){
					return null;//查找公司绩效不存在或者有误
				}
				Double dscore=null;
				double dscore2=0;//多部门
				if(dclass.equals("D00")){
					if(dwight!=0){
						dscore=IndexScore.getScorevalue(getOrg(allMeritGroupMember,"depart"),darchcode, year, period);
					}else{
						dscore=0.0;
					}
					
				}else{
					List<String> orglist=getAdminDept(allMeritGroupMember.getStaffCode());
					for(String org:orglist){
						Double temp=IndexScore.getScorevalue(org,IndexArchUser.getIndexarchcodeByobject(org, flag),year,period);
						if(temp!=null){
							dscore2+=temp.doubleValue();
						}else {
							return null;
						}
					}
					if(orglist.size()!=0){
						dscore=Double.valueOf(dscore2/orglist.size());
						//dscore=dscore2/orglist.size();
					}
					
				}
				if(dscore==null){
					return null;//查找部门绩效不存在或者有误
				}
				Double sscore=null;
				if(swight!=0){
					sscore=IndexScore.getScorevalue(allMeritGroupMember.getStaffCode(),sarchcode, year, period);
				}else{
					sscore=0.0;
				}
				if(sscore==null){
					return null;//查找个人绩效不存在或者有误
				}
				Double sscore2=null;
				if(flag2.equals("2")){
					sscore2=IndexScore.getScorevalue(allMeritGroupMember.getStaffCode(),sarchcode2, year, period);
					if(sscore2==null){
						return null;
					}
					}
				double staffallmerit=0;
				DecimalFormat dcmFmt = new DecimalFormat("0.00");
				if(flag2.equals("0")){
				   staffallmerit=Double.parseDouble(dcmFmt.format(cwight*cscore.doubleValue()+dwight*dscore.doubleValue()+swight*sscore.doubleValue()));
				}else{
					staffallmerit=Double.parseDouble(dcmFmt.format(cwight*cscore.doubleValue()+dwight*dscore.doubleValue()+swight*sscore.doubleValue()+swight2*sscore2.doubleValue()));
				}
				if(staffallmerit==0){
					return null;
				}
				List<Double> keyindexscore=IndexScore.getLeaveScore(allMeritGroupMember.getStaffCode(),sarchcode, year, period);
				StaffAllMerit staffMerit=new StaffAllMerit();
				staffMerit.setRecno(IndexCode.getRecno("SM"));
				staffMerit.setYear(year);
				staffMerit.setPeriod(period);
				staffMerit.setGroupno(allMeritGroupMember.getGroupNo());
				staffMerit.setCompanycode(getOrg(allMeritGroupMember,"company"));
				if(!getOrg(allMeritGroupMember,"depart").equals("-1"))
				   staffMerit.setDepartcode(getOrg(allMeritGroupMember,"depart"));//部门
				else
					staffMerit.setDepartcode(getOrg(allMeritGroupMember,"virdepart"));//虚拟部门
				staffMerit.setStaffcode(allMeritGroupMember.getStaffCode());
				staffMerit.setPositioncode(allMeritGroupMember.getPositionCode());
				staffMerit.setAllmeritfunc(allmeritfunc);
				staffMerit.setCvalue(cscore.doubleValue());
				staffMerit.setDvalue(dscore.doubleValue());
				staffMerit.setKeyindexscore(keyindexscore.get(0));
				staffMerit.setBaseindexscore(keyindexscore.get(1));
				staffMerit.setCommindexscore(keyindexscore.get(2));
				staffMerit.setOtherindexscore(keyindexscore.get(3));
				staffMerit.setMemo("staff");
				if(flag2.equals("0")){
				   staffMerit.setSvalue(sscore.doubleValue());
				}else {
				   staffMerit.setSvalue((sscore*swight+sscore2*swight2)/(swight+swight2));
				}
				staffMerit.setStaffallmerit(staffallmerit);
				return staffMerit;
			
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取领导分管部门
	 * @param staffcode
	 * @return
	 */
	public static List<String> getAdminDept(String staffcode){
		try {
			DBObject db=new DBObject();
			String sql="select orgcode from tbm_admindpt where staffcode='"+staffcode+"' and adminmode='绩效'";
			DataTable dt=db.runSelectQuery(sql);
			List<String> orgList=new ArrayList<String>();
			if(dt!=null&&dt.getRowsCount()>=1){
				
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow row=dt.get(i);
					orgList.add(row.getString("orgcode"));
				}
			}
			return orgList;
		} catch (Exception e) {
			return null;
		}
	}
	//记录汇总日志
	public static boolean writeCollectLog(int count,String year,String period,String operator){
		Connection conn=null;
		PreparedStatement stmt = null;
		try {
			conn=ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			String sql="insert into tbm_sumlog(id,sumyear,summonth,sumstate,availflag,operator,operatetime,scount) values(?,?,?,?,?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,IndexCode.getRecno("sum"));
			stmt.setString(2,year);
			stmt.setString(3,period);
			stmt.setString(4,"1");
			stmt.setInt(5,1);
			stmt.setString(6,operator);
			stmt.setDate(7,java.sql.Date.valueOf(Format.getNowtime2()));
			stmt.setInt(8,count);
			stmt.execute();
			conn.commit();
			conn.close();
			return true;
		} catch (Exception e) {
			try {
				conn.rollback();
				
			} catch (Exception e2) {
			}
			return false;
		}
	}
	//获取已经汇总对象指标体系数
	public static int getObjectneedcount(String year,String scoreperiod,String objecttype,String sumdealflag){
		try {
			DBObject db=new DBObject();
			String sql="select count(objecttype) as sumcount from tbm_indexscoredetail where length(indexcode)=3 and  scoreyear=? and scoreperiod=? and objecttype=? and sumdealflag=?";
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				  new Parameter.String(year),
				  new Parameter.String(scoreperiod),
				  new Parameter.String(objecttype),
				  new Parameter.String(sumdealflag)
			};
			DataTable dt=db.runSelectQuery(sql, pp);
			int sumcount=0;
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow row=dt.get(0);
				sumcount=Integer.parseInt(row.getString("sumcount"));
			}
			return sumcount;
		} catch (Exception e) {
			return 0;
		}
	}
	//获取总的对象数
		public static int getObjectsumcount(String objecttype){
			try {
				DBObject db=new DBObject();
				String sql="select count(*) as sumcount from TBM_INDEXARCHUSER where indexarchcode like '"+objecttype+"%'";
				DataTable dt=db.runSelectQuery(sql);
				int sumcount=0;
				if(dt!=null&&dt.getRowsCount()==1){
					DataRow row=dt.get(0);
					sumcount=Integer.parseInt(row.getString("sumcount"));
				}
				return sumcount;
			} catch (Exception e) {
				return 0;
			}
		}
	/*public static String getNowtime()
	{
		 java.util.Date nowTime=new java.util.Date(); 
	      SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
	      return time.format(nowTime);
	}*/
	
	public static String getOrg(AllMeritGroupMember  allMeritGroupMember ,String f){
		try {
			DBObject db=new DBObject();
			String sql="select orgcode from base_orgmember where staffcode='"+allMeritGroupMember.getStaffCode()+"' and positioncode='"+allMeritGroupMember.getPositionCode()+"'";
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()==1){
				String orgcode=dt.get(0).getString("orgcode");
				int flag=0;
				if(f.equals("depart")){//部门
					flag=1;
				}else if(f.equals("company")){//公司
					flag=0;
				}else if(f.equals("virdepart")){//虚拟部门
					flag=2;
				}
				String sql2="select orgcode,parentorgcode,adminclass from base_org where orgcode='"+orgcode+"'";
				DataTable dt2=db.runSelectQuery(sql2);
				if(dt2!=null&&dt2.getRowsCount()==1){
					orgcode=dt2.get(0).getString("orgcode");
					if(Integer.parseInt(dt2.get(0).getString("adminclass"))==flag){
						return orgcode;
					}else{
						orgcode=dt2.get(0).getString("parentorgcode");
						while(true){
							String sql3="select orgcode,parentorgcode,adminclass from base_org where orgcode='"+orgcode+"'";
							DataTable dt3=db.runSelectQuery(sql3);
							if(dt3!=null&&dt3.getRowsCount()==1){
								orgcode=dt3.get(0).getString("orgcode");
								if(orgcode.equals("NC.01")){
									return "NC.01.00";
								}
								if(Integer.parseInt(dt3.get(0).getString("adminclass"))==flag){
									return orgcode;
								}else {
									orgcode=dt3.get(0).getString("parentorgcode");
								}
							}
						}
					}
					
				}
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String getcompanyByobject(String orgcode){
		try {
			String comparecode=Format.replacePointtoOth(orgcode, "n");
			String[] strings=comparecode.split("n");
			int num=Integer.parseInt(strings[2]);
			if(num<20){
				return "NC.01.00";
			}else{
				int flag=0;
				while(true){
					DBObject db=new DBObject();
					String sql="select orgcode,parentorgcode,adminclass from base_org where orgcode='"+orgcode+"'";
					DataTable dt=db.runSelectQuery(sql);
					if(dt!=null&&dt.getRowsCount()==1){
						orgcode=dt.get(0).getString("orgcode");
						if(Integer.parseInt(dt.get(0).getString("adminclass"))==flag){
							return orgcode;
						}else {
							orgcode=dt.get(0).getString("parentorgcode");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static boolean collectCompanyMerit(String year,String period,String flag){
		try {
			List<IndexArchUser> indexArchUsers=new IndexArchUser().getUser(flag);
			for(IndexArchUser indexArchUser:indexArchUsers){
				if(IndexScore.getScorevalue(indexArchUser.getObjectcode(),indexArchUser.getIndexArchCode(), year, period)!=null){
					List<Double> keyindexscore=IndexScore.getLeaveScore(indexArchUser.getObjectcode(), indexArchUser.getIndexArchCode(), year, period);
					StaffAllMerit staffMerit=new StaffAllMerit();
					staffMerit.setRecno(IndexCode.getRecno("SM"));
					staffMerit.setYear(year);
					staffMerit.setPeriod(period);
					if(flag.equals("depart")){
					    	staffMerit.setDepartcode(indexArchUser.getObjectcode());
					    	staffMerit.setCompanycode(getcompanyByobject(indexArchUser.getObjectcode()));
					    	staffMerit.setMemo("depart");
					}else {
					    	staffMerit.setCompanycode(indexArchUser.getObjectcode());
					    	staffMerit.setMemo("company");
					}
					staffMerit.setKeyindexscore(keyindexscore.get(0));
					staffMerit.setBaseindexscore(keyindexscore.get(1));
					staffMerit.setCommindexscore(keyindexscore.get(2));
					staffMerit.setOtherindexscore(keyindexscore.get(3));
					staffMerit.setStaffallmerit(IndexScore.getScorevalue(indexArchUser.getObjectcode(), indexArchUser.getIndexArchCode(), year, period));
					new StaffAllMerit().insert(staffMerit);
				}
			}
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean virtualDepartCollet(String year,String period){
		try {
			List<String> orgList=getVirtualDepart();
			for(String virtualorgcode : orgList){
				List<String> memberlistList=getVirtualOrgmember(virtualorgcode);
				StaffAllMerit staffAllMerit=getVirtualDepartStaffAllMerit(virtualorgcode, memberlistList, year, period);
				if(staffAllMerit!=null)
				new StaffAllMerit().insert(staffAllMerit);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static List<String> getVirtualDepart(){
		try {
			List<String> virtualdepartList=new ArrayList<String>();
			String sql="select orgcode from base_org where adminclass='2' order by orgcode";
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()>=1){
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow row=dt.get(i);
					virtualdepartList.add(row.getString("orgcode"));
				}
			}
			return virtualdepartList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static List<String> getVirtualOrgmember(String virtualorgcode){
		try {
			List<String> virtualmemberList=new ArrayList<String>();
			String sql="select staffcode from base_orgmember where orgcode like '"+virtualorgcode+"%' order by orgcode,staffcode";
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()>=1){
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow row=dt.get(i);
					virtualmemberList.add(row.getString("staffcode"));
				}
			}
			return virtualmemberList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static StaffAllMerit getVirtualDepartStaffAllMerit(String virtualorgcode,List<String> memberlistList,String year,String period){
		try {
			StringBuilder sql=new StringBuilder();
			sql.append("select staffallmerit from tbm_staffallmerit where staffcode in (");
			for(String staffcode : memberlistList){
				sql.append(staffcode).append(",");
			}
			sql.delete(sql.length()-1, sql.length());
			sql.append(")");
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql.toString());
			if(dt!=null&&dt.getRowsCount()>=1){
				double staffallmerit=0;
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow row=dt.get(i);
					staffallmerit+=Double.parseDouble(row.getString("staffallmerit"));
				}
				staffallmerit=staffallmerit/dt.getRowsCount();
				StaffAllMerit staffMerit=new StaffAllMerit();
				staffMerit.setRecno(IndexCode.getRecno("SM"));
				staffMerit.setYear(year);
				staffMerit.setPeriod(period);
				staffMerit.setCompanycode(getcompanyByobject(virtualorgcode));
				staffMerit.setDepartcode(virtualorgcode);//部门
				staffMerit.setStaffallmerit(staffallmerit);
				return staffMerit;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
