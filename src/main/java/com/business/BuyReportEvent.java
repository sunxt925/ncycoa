package com.business;

import java.util.Date;

import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class BuyReportEvent {

	public String EventNo="";//	事件流水号
	public String EventType="";//	事件类别
	public String EventDesc="";//	事件说明
	public int NumGoodsItem=0;//	采购商品条目数量呈报项目数
	public String BuyMode="";//	采购模式
	public String RelatedOrgCode="";//	需求申请部门呈报申请部门
	public String Handler="";//	经办人
	public Date InputDate=null;//	填写时间
	public Date SummitDate=null;//	提交时间
	public int SummitFlag=0;//	提交标志
	public String AuditOpinion="";//	上级审核意见
	public Date AuditDate=null;//	上级审核日期
	public int AuditFlag=0;//	上级审核标志

	public BuyReportEvent(){
		
	}
	public BuyReportEvent(String eventno){
		
	}
	
	public String getEventNo() {
		return EventNo;
	}
	public void setEventNo(String eventNo) {
		EventNo = eventNo;
	}
	public String getEventType() {
		return EventType;
	}
	public void setEventType(String eventType) {
		EventType = eventType;
	}
	public String getEventDesc() {
		return EventDesc;
	}
	public void setEventDesc(String eventDesc) {
		EventDesc = eventDesc;
	}
	public int getNumGoodsItem() {
		return NumGoodsItem;
	}
	public void setNumGoodsItem(int numGoodsItem) {
		NumGoodsItem = numGoodsItem;
	}
	public String getBuyMode() {
		return BuyMode;
	}
	public void setBuyMode(String buyMode) {
		BuyMode = buyMode;
	}
	public String getRelatedOrgCode() {
		return RelatedOrgCode;
	}
	public void setRelatedOrgCode(String relatedOrgCode) {
		RelatedOrgCode = relatedOrgCode;
	}
	public String getHandler() {
		return Handler;
	}
	public void setHandler(String handler) {
		Handler = handler;
	}
	public Date getInputDate() {
		return InputDate;
	}
	public void setInputDate(Date inputDate) {
		InputDate = inputDate;
	}
	public Date getSummitDate() {
		return SummitDate;
	}
	public void setSummitDate(Date summitDate) {
		SummitDate = summitDate;
	}
	public int getSummitFlag() {
		return SummitFlag;
	}
	public void setSummitFlag(int summitFlag) {
		SummitFlag = summitFlag;
	}
	public String getAuditOpinion() {
		return AuditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		AuditOpinion = auditOpinion;
	}
	public Date getAuditDate() {
		return AuditDate;
	}
	public void setAuditDate(Date auditDate) {
		AuditDate = auditDate;
	}
	public int getAuditFlag() {
		return AuditFlag;
	}
	public void setAuditFlag(int auditFlag) {
		AuditFlag = auditFlag;
	}
	 public DataTable getBuyreporteventlist(int pageno,int perpage,String q_flag,String orgcode,String summitdate,String summitenddate,String buymode){
	    	
	    	try {
				DBObject db=new DBObject();
			    if(summitdate.equals("")&&summitenddate.equals(""))
			    	summitdate="1=1";
			    else if(!summitdate.equals("")&&summitenddate.equals(""))
			    	summitdate="summitdate>=to_date('"+summitdate+"','yyyy-MM-dd')";
			    else if(summitdate.equals("")&&!summitenddate.equals(""))
					summitdate="summitdate<=to_date('"+summitenddate+"','yyyy-MM-dd')";
			    else if(!summitdate.equals("")&&!summitenddate.equals(""))
					summitdate="summitdate>=to_date('"+summitdate+"','yyyy-MM-dd') and summitdate<=to_date('"+summitenddate+"','yyyy-MM-dd')";
			    if(buymode.equals(""))
			    	buymode="1=1";
			    else 
					buymode="buymode='"+buymode+"'";
			    if(orgcode.equals(""))
			    	orgcode="1=1";
			    else 
			    	orgcode="relatedorgcode='"+orgcode+"'";
			    
				String base_sql="select '选择' as 选择 ,eventno,eventno as 事件编号,eventdesc as 事件说明,numgoodsitem as 物资采购申请条数,summitdate as 申请日期,handler as 录入人,memo as 说明,buymode as 采购模式,summitflag as 处理结果,'操作' as 操作  from (select * from com_buyreportevent order by eventno) where eventno like '"+q_flag+"%' and  "+summitdate+"  and  "+buymode+"  and "+orgcode+"";//+ and relatedorgcode='orgcode'";
			
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
				return db.runSelectQuery(sql_run);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
	    }
	  
	    public DataTable getAllBuyreporteventlist(String q_flag,String orgcode,String summitdate,String summitenddate,String buymode)
		{
			try
			{
				DBObject db = new DBObject();
				 if(summitdate.equals("")&&summitenddate.equals(""))
				    	summitdate="1=1";
				    else if(!summitdate.equals("")&&summitenddate.equals(""))
				    	summitdate="summitdate>=to_date('"+summitdate+"','yyyy-MM-dd')";
				    else if(summitdate.equals("")&&!summitenddate.equals(""))
						summitdate="summitdate<=to_date('"+summitenddate+"','yyyy-MM-dd')";
				    else if(!summitdate.equals("")&&!summitenddate.equals(""))
						summitdate="summitdate>=to_date('"+summitdate+"','yyyy-MM-dd') and summitdate<=to_date('"+summitenddate+"','yyyy-MM-dd')";
				 	if(buymode.equals(""))
				    	buymode="1=1";
				    else 
						buymode="buymode='"+buymode+"'";
				  if(orgcode.equals(""))
				    	orgcode="1=1";
				    else 
				    	orgcode="relatedorgcode='"+orgcode+"'";
				    
					DataTable dt = db.runSelectQuery("select * from com_buyreportevent where eventno like '"+q_flag+"%'  and  "+summitdate+"  and  "+buymode+" and "+orgcode+"");//+ and relatedorgcode='orgcode'";
					return dt;	
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
	    //产生呈报事件
	public static String getEventno(String[] str,String buymode)
	{
		try {
			
		    
			DBObject db=new DBObject(); 
			String sql="insert into com_buyreportevent (eventno,eventtype,numgoodsitem,buymode) values(?,?,?,?) ";
			String eventno=BuyGoodsApp.getEventno("COM_REPORTEVENT","RP");
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
		
				new Parameter.String(""+eventno),
				new Parameter.String("呈报"),
				new Parameter.Int(str.length),
				new Parameter.String(buymode)
			//	new Parameter.String("12"),
			//	new Parameter.String(""+str.length),
			//	new Parameter.String("0"),
			//	new Parameter.String("ss"),
			//	new Parameter.String("li"),
			//	new Parameter.String("2010-10-10"),
			//	new Parameter.String("2010-10-11")
			};
			db.run(sql, pp);
			
			return eventno;
			
			
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
	}

}
