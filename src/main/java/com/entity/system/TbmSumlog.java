package com.entity.system;

import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;

public class TbmSumlog {
	private int id;
	public DataTable getIndexList(String parayear,int pageno,int perpage){
		try {
			DBObject db=new DBObject();
			String base_sql="select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||M1.summonth||'\">' as ѡ�� ," +
					"M1.summonth as �·�,M1.sumstate as ����״̬,M1.operator as ������,M1.operatetime as ����ʱ��,cast(M1.c00count as varchar2(20))||'/'||cast(M2.gongsiNumber as varchar2(20)) as ��˾������,cast(M1.d00count as varchar2(20))||'/'||cast(M2.bumenNumber as varchar2(20)) as ���Ŷ�����,cast(M1.S00COUNT as varchar2(20))||'/'||cast(M2.yuangongNumber as varchar2(20)) as ���˶�����,'����' as ���� " +
					"  from MonthHuizong M1,MonthHuizongSmall M2 where M2.gongsiNumber>0 and M2.bumenNumber>0 and M1.summonth=M2.scoreperiod and M1.sumyear='"+parayear+"' and M2.scoreyear='"+parayear+"'  order by M1.summonth";
			/*String base_sql1 = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||summonth||'\">' as ѡ��" +
					",summonth as �·�,sumstate as ����״̬,operator as ������,operatetime as ����ʱ��,c00count as ��˾������,d00count as ���Ŷ�����,S00COUNT as ���˶�����,'<a href=\"#\" onclick=F1(\"'||summonth||'\")  class=\"button4\">�� ��</a>' as ����   from tbm_sumlog where sumyear='"+parayear+"'";*/
			
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
		if(para.equals("δ����")){
			result="";
		}
		else if(para.equals("��ʱ����")){
			
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
					String type="����";
					if(j==2)
						type="��˾";
					if(j==3)
						type="Ա��";
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
	private int coocount;//��˾������Ŀ
	private int doocount;//���Ŷ�����Ŀ
	private int soocount;//���˶�����Ŀ
}
