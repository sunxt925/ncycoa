package com.business;

import java.util.Date;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class BuyReportItem {

	public String ReportNo="";//	�ʱ���¼���
	public String EventNo="";//	�¼���ˮ��
	public String ProcjetCode="";//	����������(��Ŀ����)
	public String ProjectName="";//	��Ŀ����
	public String relatedOrgCode="";//	ʵʩ����
	public String DealMode="";//	ʵʩ��ʽ
	public float TotalCost=0;//	�漰���
	public String CheckContents="";//	�������
	public String FirstAuditOrg="";//	����λ
	public String FirstAuditor="";//	������
	public String FirstAuditOpinion	="";//�������
	public String FirstAuditSummary="";//	����ժҪ
	public Date FirstAuditDate=null;//	��������
	public String FirstAuditFlag="";//	�����־
	public String SecondAuditOrg="";//	����λ
	public String SecondAuditor="";//������
	public String SecondAudiOpiniont="";//�������
	public Date SecondAuditDate=null;//	��������
	public String SecondAuditFlag="";//	�����־
	public String FinalAuditOpinion="";//�������
	public Date FinalAuditDate=null;//	��������
	public String FinalAuditFlag="";//	�����־
	public String des="";//��ע
	public String summitflag="";//�ʱ���־
	public BuyReportItem(){
		
	}
	public BuyReportItem(String reportno){
		try {
			String sql="select * from com_buyreportitem where reportno=?";
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.String(reportno)	
			};
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql, pp);
			if(dt!=null&&dt.getRowsCount()==1)
			{
				DataRow r=dt.get(0);
				this.ReportNo=r.getString("reportno");//	�ʱ���¼���
				
				this.ProcjetCode=r.getString("projectcode");//	����������(��Ŀ����)
				this.ProjectName=r.getString("projectname");//	��Ŀ����
				this.relatedOrgCode=r.getString("relatedorgcode");//	ʵʩ����
				this.DealMode=r.getString("dealmode");//	ʵʩ��ʽ
				this.TotalCost=Float.parseFloat(Format.NullToZero(r.getString("totalcost")));//	�漰���
				this.CheckContents=r.getString("checkcontents");//	�������
				this.FirstAuditOrg=r.getString("firstauditorg");//	����λ
				this.FirstAuditor=r.getString("firstauditor");//	������
				this.FirstAuditOpinion	=r.getString("firstauditopinion");//�������
				this.FirstAuditSummary=r.getString("firstauditsummary");//	����ժҪ
				this.FirstAuditDate=Format.strToDate(r.getString("firstauditdate"));//	��������
				this.FirstAuditFlag=r.getString("firstauditflag");//	�����־
				this.SecondAuditOrg=r.getString("secondauditorg");//	����λ
				this.SecondAuditor=r.getString("secondauditor");//	������SECONDAUDIOPINIONT
				this.SecondAudiOpiniont=r.getString("secondaudiopiniont");//	�������
				this.SecondAuditDate=Format.strToDate(r.getString("secondauditdate"));//	��������
				this.SecondAuditFlag=r.getString("secondauditflag");//	�����־
				this.FinalAuditOpinion=r.getString("finalauditopinion");//	�������
				this.FinalAuditDate=Format.strToDate(r.getString("finalauditdate"));//	��������
				this.FinalAuditFlag=r.getString("finalauditflag");//	�����־
				this.des=r.getString("memo");//��ע
				this.summitflag=r.getString("summitflag");//�ʱ���־
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public String getProcjetCode() {
		return ProcjetCode;
	}
	public void setProcjetCode(String procjetCode) {
		ProcjetCode = procjetCode;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getRelatedOrgCode() {
		return relatedOrgCode;
	}
	public void setRelatedOrgCode(String relatedOrgCode) {
		this.relatedOrgCode = relatedOrgCode;
	}
	public String getDealMode() {
		return DealMode;
	}
	public void setDealMode(String dealMode) {
		DealMode = dealMode;
	}
	public float getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(float totalCost) {
		TotalCost = totalCost;
	}
	public String getCheckContents() {
		return CheckContents;
	}
	public void setCheckContents(String checkContents) {
		CheckContents = checkContents;
	}
	public String getFirstAuditOrg() {
		return FirstAuditOrg;
	}
	public void setFirstAuditOrg(String firstAuditOrg) {
		FirstAuditOrg = firstAuditOrg;
	}
	public String getFirstAuditor() {
		return FirstAuditor;
	}
	public void setFirstAuditor(String firstAuditor) {
		FirstAuditor = firstAuditor;
	}
	public String getFirstAuditOpinion() {
		return FirstAuditOpinion;
	}
	public void setFirstAuditOpinion(String firstAuditOpinion) {
		FirstAuditOpinion = firstAuditOpinion;
	}
	public String getFirstAuditSummary() {
		return FirstAuditSummary;
	}
	public void setFirstAuditSummary(String firstAuditSummary) {
		FirstAuditSummary = firstAuditSummary;
	}
	public Date getFirstAuditDate() {
		return FirstAuditDate;
	}
	public void setFirstAuditDate(Date firstAuditDate) {
		FirstAuditDate = firstAuditDate;
	}
	public String getFirstAuditFlag() {
		return FirstAuditFlag;
	}
	public void setFirstAuditFlag(String firstAuditFlag) {
		FirstAuditFlag = firstAuditFlag;
	}
	public String getSecondAuditOrg() {
		return SecondAuditOrg;
	}
	public void setSecondAuditOrg(String secondAuditOrg) {
		SecondAuditOrg = secondAuditOrg;
	}
	public String getSecondAuditor() {
		return SecondAuditor;
	}
	public void setSecondAuditor(String secondAuditor) {
		SecondAuditor = secondAuditor;
	}
	public String getSecondAudiOpiniont() {
		return SecondAudiOpiniont;
	}
	public void setSecondAudiOpiniont(String secondAudiOpiniont) {
		SecondAudiOpiniont = secondAudiOpiniont;
	}
	public Date getSecondAuditDate() {
		return SecondAuditDate;
	}
	public void setSecondAuditDate(Date secondAuditDate) {
		SecondAuditDate = secondAuditDate;
	}
	public String getSecondAuditFlag() {
		return SecondAuditFlag;
	}
	public void setSecondAuditFlag(String secondAuditFlag) {
		SecondAuditFlag = secondAuditFlag;
	}
	public String getFinalAuditOpinion() {
		return FinalAuditOpinion;
	}
	public void setFinalAuditOpinion(String finalAuditOpinion) {
		FinalAuditOpinion = finalAuditOpinion;
	}
	public Date getFinalAuditDate() {
		return FinalAuditDate;
	}
	public void setFinalAuditDate(Date finalAuditDate) {
		FinalAuditDate = finalAuditDate;
	}
	public String getFinalAuditFlag() {
		return FinalAuditFlag;
	}
	public void setFinalAuditFlag(String finalAuditFlag) {
		FinalAuditFlag = finalAuditFlag;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	public String getSummitflag() {
		return summitflag;
	}
	public void setSummitflag(String summitflag) {
		this.summitflag = summitflag;
	}
	//��ȡreportno
	public static String getReportno(){
		return  BuyGoodsApp.getEventno("COM_BUYREPORTITEM", "RR");
	}
	public DataTable getBuyreportList(int pageno, int perpage,int flag/*,String orgcode*/){
		return getBuyreportList(pageno, perpage,flag,false/*,String orgcode*/);
	}
  public DataTable getBuyreportList(int pageno, int perpage,int flag,boolean state/*,String orgcode*/){
		try {
			DBObject db=new DBObject();
            String base_sql="";
            if(state==false){
            if(flag==0){
     		   base_sql = "select  'ѡ��' as ѡ�� ,reportno,reportno as �ʱ����,projectcode as  ��Ŀ���� ,projectname as ��Ŀ����,relatedorgcode as  ʵʩ����, dealmode as ʵʩ��ʽ ,totalcost as �漰���,checkcontents as  �����ص� ,firstauditorg as ������,firstauditopinion as  ������� ,firstauditsummary as ժҪ ,summitflag as �ʱ���־ ,'����' as ����  from (select * from com_buyreportitem order by reportno)";

            }
            if(flag==1) {
      		   base_sql = "select  'ѡ��' as ѡ�� ,reportno,reportno as �ʱ����,projectcode as  ��Ŀ���� ,projectname as ��Ŀ����,relatedorgcode as  ʵʩ����, dealmode as ʵʩ��ʽ ,totalcost as �漰���,checkcontents as  �����ص� ,firstauditorg as ������,firstauditopinion as  ������� ,secondauditflag as ������, finalauditflag   as ������   ,firstauditsummary as ժҪ ,summitflag as �ʱ���־  ,'����' as ����  from (select * from com_buyreportitem order by reportno)";//+ and relatedorgcode='orgcode'";

			}}
            if(state==true){
            	
           		   base_sql = "select  'ѡ��' as ѡ�� ,reportno,reportno as �ʱ����,projectcode as  ��Ŀ���� ,projectname as ��Ŀ����,relatedorgcode as  ʵʩ����, dealmode as ʵʩ��ʽ ,totalcost as �漰���,checkcontents as  �����ص� ,firstauditorg as ������,firstauditopinion as  ������� ,firstauditsummary as ժҪ ,summitflag as �ʱ���־ ,'����' as ����  from (select * from com_buyreportitem order by reportno) where summitflag='0'";//+ and relatedorgcode='orgcode'";

                
            }
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
				return db.runSelectQuery(sql_run);
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
  public DataTable getAllBuyreportList(){
	  return getAllBuyreportList(false);
  }
	public DataTable getAllBuyreportList(boolean state){
		try
		{
			DBObject db = new DBObject();
			DataTable dt=new DataTable();
			if(state==false)
			{	 dt = db.runSelectQuery("select * from com_buyreportitem");//+ and relatedorgcode='orgcode'";
			}
			if(state==true)
			{
				dt = db.runSelectQuery("select * from com_buyreportitem where summitflag='0'");//+ and relatedorgcode='orgcode'";
			}
				return dt;	
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//��ȡ�ʱ��¼���
	public DataTable getReportEventItemlist(int pageno, int perpage,String eventno){
		try {
			DBObject db=new DBObject();
            String base_sql="";
          	
           		   base_sql = "select  'ѡ��' as ѡ�� ,reportno,reportno as �ʱ����,projectcode as  ��Ŀ���� ,projectname as ��Ŀ����,relatedorgcode as  ʵʩ����, dealmode as ʵʩ��ʽ ,totalcost as �漰���,checkcontents as  �����ص� ,firstauditorg as ������,firstauditopinion as  ������� ,firstauditsummary as ժҪ ,summitflag as �ʱ���־ ,'����' as ����  from (select * from com_buyreportitem order by reportno) where eventno='"+eventno+"'";

				String sql_run = Format.getFySql(base_sql, pageno, perpage);
				return db.runSelectQuery(sql_run);
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllReportEventItemlist(String eventno){
		try
		{
			DBObject db = new DBObject();
			DataTable dt=new DataTable();
			dt = db.runSelectQuery("select * from com_buyreportitem where eventno='"+eventno+"'");
			
				return dt;	
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static String AddReportItem(String[] str,String reportno)
	{
	   try {
		   float sum=0;
		for(int i=0;i<str.length;i++)
		{
			BuyGoodsItem buyItem=new BuyGoodsItem(str[i]);
			BuyGoodsReportRelation buyRelation=new BuyGoodsReportRelation();
			buyRelation.insert(reportno, buyItem.getEventNo());
			sum+=buyItem.getTotalCost();
		}
		DBObject db=new DBObject();
		String sql="update com_buyreportitem set totalcost=? where reportno=?";
		Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
			new Parameter.Float(sum),
			new Parameter.String(reportno)
		};
		db.run(sql, pp);
		String sql2="update com_buygoodsitem set auditflag=? where buyno=?";
		for(int j=0;j<str.length;j++)
		{
			DBObject db2=new DBObject();
			Parameter.SqlParameter[] pp2=new Parameter.SqlParameter[]{
					new Parameter.String("01"),
					new Parameter.String(str[j])
			};
			db2.run(sql2, pp2);
		}
		return "�ɹ�";
	} catch (Exception e) {
		// TODO: handle exception
		return "ʧ��";
	}
	}
	public static String AutoGetreport()
	{
		try {
			//��ȡ�걨�¼�
			
			String sql_appevent="select eventno from com_buyreportevent where summitflag!='0' and eventno like 'BY%' and auditflag='00'";
			DBObject db_appevent=new DBObject();
			DataTable dt_appevent=new DataTable();
			dt_appevent=db_appevent.runSelectQuery(sql_appevent);
			
			
			String sql_appeventitem="select projectcode ,projectname,sum(totalcost),count(buyno)  from com_buygoodsitem where eventno=? and summitflag=? and auditflag=? group by projectcode,projectname";
			if(dt_appevent!=null&&dt_appevent.getRowsCount()>0){
				
			      for(int i=0;i<dt_appevent.getRowsCount();i++){
				      
			    	  //�����걨�¼�����걨������
			    	  String byeventno=dt_appevent.get(i).getString("eventno");
			    	  DBObject db_appeventitem=new DBObject();
			    	  DataTable dt_appeventitem=new DataTable();
			    	  Parameter.SqlParameter[] pp1=new Parameter.SqlParameter[]{
			    			  new Parameter.String(byeventno),
			    			  new Parameter.String("1"),
			    			  new Parameter.String("00"),
			    	  };
			    	  dt_appeventitem=db_appeventitem.runSelectQuery(sql_appeventitem, pp1);
			    	  
			    	  if(dt_appeventitem!=null&&dt_appeventitem.getRowsCount()>0){
			    	      
			    		  String reportno="";
			    		    for(int j=0;j<dt_appeventitem.getRowsCount();j++){
			    		    	//�����ʱ���
			    		    	 reportno=getReportno();
								  String sql_report="insert into com_buyreportitem(reportno,projectcode,projectname,totalcost,summitflag,firstauditflag,secondauditflag,finalauditflag) values(?,?,?,?,?,?,?,?) ";
			                      DBObject db_report=new DBObject();
			                      Parameter.SqlParameter[] pp2=new Parameter.SqlParameter[]{
			                    	new Parameter.String(reportno),
			                    	new Parameter.String(dt_appeventitem.get(j).getString("projectcode")),
			                    	new Parameter.String(dt_appeventitem.get(j).getString("projectname")),
			                    	new Parameter.String(dt_appeventitem.get(j).getString("sum(totalcost)")),
			                    	new Parameter.String("0"),
			                    	new Parameter.String("00"),
			                    	new Parameter.String("00"),
			                    	new Parameter.String("00")
			                      };
			                      db_report.run(sql_report, pp2);
			                      
			                    //���ɳʱ��������ʹ�ϵ
			                      String  sql_reportgoodsre="insert into com_buygoodsreportrelation(reportno,eventno) values(?,?)";
			                      DBObject db_reportgoodsre=new DBObject();
			                      Parameter.SqlParameter[] pp3=new Parameter.SqlParameter[]{
			                    	
			                    		  new Parameter.String(reportno),
			                    		  new Parameter.String(byeventno)
			                    		  
			                      };
			                      db_reportgoodsre.run(sql_reportgoodsre, pp3);
			                    //�޸����ʱ�־
			                     String sql_modgoods="update com_buygoodsitem set auditflag=? where projectcode=? and summitflag=? and auditflag=? and eventno=?";
			                     DBObject db_modgoods=new DBObject();
			                     Parameter.SqlParameter[] pp4=new Parameter.SqlParameter[]{
			                    	
			                    		 new Parameter.String("01"),
			                    		 new Parameter.String(dt_appeventitem.get(j).getString("projectcode")),
			                    		 new Parameter.String("1"),
			                    		 new Parameter.String("00"),
			                    		 new Parameter.String(byeventno)
			                     };
			                     db_modgoods.run(sql_modgoods, pp4);
			                      
			    		    }
			    	  
			    	  }
			   	  
			      }
			}
			
		
			return "�Զ������ɹ�";
		} catch (Exception e) {
			// TODO: handle exception
			return "�Զ�����ʧ��";
		}
	}
	//�ʱ�
public static String Report(String[] str,String buymode,String auditflag){
	try {
		
	
	BuyGoodsItem buyGoodsItem=new BuyGoodsItem();
	String eventno=BuyReportEvent.getEventno(str, buymode);
	String sql="update com_buygoodsitem set auditflag=? where buyno=?";
	String sql2="update com_buyreportitem set summitflag=? ,eventno=?,firstauditflag=? where reportno=?";
	for(int i=0;i<str.length;i++)
	{
		BuyReportItem buyReportItem=new BuyReportItem(str[i]);
		DataTable dt=buyGoodsItem.getAllBuyReportitemlist2(str[i], buyReportItem.getProcjetCode(),"10");
		for(int j=0;j<dt.getRowsCount();j++)
		{
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.String(auditflag),
				new Parameter.String(dt.get(j).getString("buyno"))
			};
			DBObject dbObject=new DBObject();
			dbObject.run(sql, pp);
		}
		DBObject db2=new DBObject();
		Parameter.SqlParameter[] pp2=new Parameter.SqlParameter[]{
			new Parameter.String("1"),
			new Parameter.String(eventno),
			new Parameter.String("11"),
			new Parameter.String(str[i])
		};
		db2.run(sql2, pp2);
	}
	return "�ʱ��ɹ�";
	} catch (Exception e) {
		// TODO: handle exception
		return "�ʱ�ʧ��";
	}
	
}

public static boolean Reportcheck(String[] str)
{
	boolean flag=true;
	try {
		for(int i=0;i<str.length;i++)
		{
		BuyReportItem buyReportItem=new BuyReportItem(str[i]);
		String projectcode=buyReportItem.getProcjetCode();
		
		String sql="select *  from (select a.* from com_buygoodsitem a ,com_buygoodsreportrelation b   where b.reportno='"+str[i]+"' and a.auditflag='10' and a.projectcode='"+projectcode+"'  and a.eventno=b.eventno ) ";
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql);
		if(dt.getRowsCount()==0)
			flag=false;
		}
		return flag;
	} catch (Exception e) {
		return false;
		// TODO: handle exception
	}
}
//�������״̬�޸�
public static boolean modGoodsstate(String str,String auditflag,String lastflag)
{
	try {
		
		BuyGoodsItem buyGoodsItem=new BuyGoodsItem();
		
		String sql="update com_buygoodsitem set auditflag=? where buyno=?";
		
		
			BuyReportItem buyReportItem=new BuyReportItem(str);
			DataTable dt=buyGoodsItem.getAllBuyReportitemlist2(str, buyReportItem.getProcjetCode(),lastflag);
			for(int j=0;j<dt.getRowsCount();j++)
			{
				Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
					new Parameter.String(auditflag),
					new Parameter.String(dt.get(j).getString("buyno"))
				};
				DBObject dbObject=new DBObject();
				dbObject.run(sql, pp);
			}
		
		
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
}
//�޸Ļ��ܽ��
public static boolean modTotalcost(String reportno,String projectcode)
{
	try {
		String sql="select  sum(totalcost)  from (select a.* from com_buygoodsitem a ,com_buygoodsreportrelation b   where b.reportno='"+reportno+"' and a.auditflag='10' and a.projectcode='"+projectcode+"'  and a.eventno=b.eventno order by a.buyno)";	
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql);
		
		float sum=Float.parseFloat(Format.NullToZero(dt.get(0).getString("sum(totalcost)")));
		String sql_modtotalcost="update com_buyreportitem set totalcost="+sum+"  where reportno='"+reportno+"' ";
		DBObject db2=new DBObject();
		db2.run(sql_modtotalcost);
		return true;
		
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}


}
}
