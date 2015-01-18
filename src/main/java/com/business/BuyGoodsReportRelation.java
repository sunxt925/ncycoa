package com.business;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class BuyGoodsReportRelation {

	public String ReportNo="";//	呈报记录序号
	public String EventNo="";//	事件流水号
	public String Relation="";//	关系
	
	public BuyGoodsReportRelation()
	{
		
	}
	public BuyGoodsReportRelation(String reportno){
		
	}
	public boolean insert(String reportno,String eventno){
		try {
			
			String sql="insert into com_buygoodsreportrelation(reportno,eventno) values(?,?)";
			DBObject dbObject=new DBObject();
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.String(reportno),
				new Parameter.String(eventno)
			};
			
			String sql2="select * from com_buygoodsreportrelation where reportno=? and eventno=?";
			Parameter.SqlParameter[] pp2=new Parameter.SqlParameter[]{
					new Parameter.String(reportno),
					new Parameter.String(eventno)
				};
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql2,pp2);
			if(dt.getRowsCount()>0)
			{
				return false;
			}
			else{
				return dbObject.run(sql, pp);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	public String getReportNo() {
		return ReportNo;
	}
	public void setReportNo(String reportNo) {
		ReportNo = reportNo;
	}
	public String getEventNo() {
		return EventNo;
	}
	public void setEventNo(String eventNo) {
		EventNo = eventNo;
	}
	public String getRelation() {
		return Relation;
	}
	public void setRelation(String relation) {
		Relation = relation;
	}
//	public static String autoGetgoodsreportRelation()
//	{
//		try {
//			String  sql="select * from com_buyreportitem where ";
//			String sql2="select * from com_buygoodsitem where ";
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
    
}
