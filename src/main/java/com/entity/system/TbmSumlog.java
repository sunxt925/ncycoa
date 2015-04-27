package com.entity.system;

import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;

public class TbmSumlog {
	private int id;
	public DataTable getIndexList(String parayear,int pageno,int perpage){
		try {
			DBObject db=new DBObject();
			String base_sql="select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||M1.summonth||'\">' as 选择 ," +
					"M1.summonth as 月份,M1.sumstate as 汇总状态,M1.operator as 汇总人,M1.operatetime as 汇总时间,cast(M1.c00count as varchar2(20))||'/'||cast(M2.gongsiNumber as varchar2(20)) as 公司对象数,cast(M1.d00count as varchar2(20))||'/'||cast(M2.bumenNumber as varchar2(20)) as 部门对象数,cast(M1.S00COUNT as varchar2(20))||'/'||cast(M2.yuangongNumber as varchar2(20)) as 个人对象数,'操作' as 操作 " +
					"  from MonthHuizong M1,MonthHuizongSmall M2 where M2.gongsiNumber>0 and M2.bumenNumber>0 and M1.summonth=M2.scoreperiod and M1.sumyear='"+parayear+"' and M2.scoreyear='"+parayear+"'  order by M1.summonth";
			/*String base_sql1 = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||summonth||'\">' as 选择" +
					",summonth as 月份,sumstate as 汇总状态,operator as 汇总人,operatetime as 汇总时间,c00count as 公司对象数,d00count as 部门对象数,S00COUNT as 个人对象数,'<a href=\"#\" onclick=F1(\"'||summonth||'\")  class=\"button4\">修 改</a>' as 操作   from tbm_sumlog where sumyear='"+parayear+"'";*/
			
			//System.out.println(base_sql);
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			//System.out.println(sql_run);
			return db.runSelectQuery(sql_run);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	public String returnResult(String para){
		String result="";
		if(para.equals("未汇总")){
			result="";
		}
		else if(para.equals("临时汇总")){
			
		}
		else{
			result="";
		}
		return result;
		
	}
	public DataTable  getIndexAllList(String parayear){
		try {
			DBObject db=new DBObject();
			
			String base_sql = "select * from tbm_sumlog where sumyear='"+parayear+"'";
			
			return db.runSelectQuery(base_sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*public boolean insertDate(String paraYear){
		try {
			DBObject db=new DBObject();
			String sql="";
			for(int i=1;i<=12;i++){
				String month="";
				if(i>=1&&i<=9)
					month+="M0"+i;
				else
					month+="M"+i;
				for(int j=1;j<=3;j++){
					String type="部门";
					if(j==2)
						type="公司";
					if(j==3)
						type="员工";
					sql="select count(*) from tbm_indexscoredetail t where t.scoreperiod='"+month+"' and t.scoreyear='"+paraYear+"' and t.objecttype='"+type+"'";
					DataTable dt=db.runSelectQuery(sql);
					if(dt != null && dt.getRowsCount() >= 0){
						
					}
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}*/
	
	private String sumyear;
	private String summonth;
	private String sumstate;
	private int availflag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSumyear() {
		return sumyear;
	}
	public void setSumyear(String sumyear) {
		this.sumyear = sumyear;
	}
	public String getSummonth() {
		return summonth;
	}
	public void setSummonth(String summonth) {
		this.summonth = summonth;
	}
	public String getSumstate() {
		return sumstate;
	}
	public void setSumstate(String sumstate) {
		this.sumstate = sumstate;
	}
	public int getAvailflag() {
		return availflag;
	}
	public void setAvailflag(int availflag) {
		this.availflag = availflag;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	public int getCoocount() {
		return coocount;
	}
	public void setCoocount(int coocount) {
		this.coocount = coocount;
	}
	public int getDoocount() {
		return doocount;
	}
	public void setDoocount(int doocount) {
		this.doocount = doocount;
	}
	public int getSoocount() {
		return soocount;
	}
	public void setSoocount(int soocount) {
		this.soocount = soocount;
	}
	private String operator;
	private String operatetime;
	private int coocount;//公司对象数目
	private int doocount;//部门对象数目
	private int soocount;//个人对象数目
}
