package com.business;

import java.util.Date;

import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class BuyReportEvent {

	public String EventNo="";//	�¼���ˮ��
	public String EventType="";//	�¼����
	public String EventDesc="";//	�¼�˵��
	public int NumGoodsItem=0;//	�ɹ���Ʒ��Ŀ�����ʱ���Ŀ��
	public String BuyMode="";//	�ɹ�ģʽ
	public String RelatedOrgCode="";//	�������벿�ųʱ����벿��
	public String Handler="";//	������
	public Date InputDate=null;//	��дʱ��
	public Date SummitDate=null;//	�ύʱ��
	public int SummitFlag=0;//	�ύ��־
	public String AuditOpinion="";//	�ϼ�������
	public Date AuditDate=null;//	�ϼ��������
	public int AuditFlag=0;//	�ϼ���˱�־

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
			    
				String base_sql="select 'ѡ��' as ѡ�� ,eventno,eventno as �¼����,eventdesc as �¼�˵��,numgoodsitem as ���ʲɹ���������,summitdate as ��������,handler as ¼����,memo as ˵��,buymode as �ɹ�ģʽ,summitflag as ������,'����' as ����  from (select * from com_buyreportevent order by eventno) where eventno like '"+q_flag+"%' and  "+summitdate+"  and  "+buymode+"  and "+orgcode+"";//+ and relatedorgcode='orgcode'";
			
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
	    //�����ʱ��¼�
	public static String getEventno(String[] str,String buymode)
	{
		try {
			
		    
			DBObject db=new DBObject(); 
			String sql="insert into com_buyreportevent (eventno,eventtype,numgoodsitem,buymode) values(?,?,?,?) ";
			String eventno=BuyGoodsApp.getEventno("COM_REPORTEVENT","RP");
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
		
				new Parameter.String(""+eventno),
				new Parameter.String("�ʱ�"),
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
