package com.business;

import java.util.Date;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class BuyReportItem {

	public String ReportNo="";//	呈报记录序号
	public String EventNo="";//	事件流水号
	public String ProcjetCode="";//	物资类别代码(项目大类)
	public String ProjectName="";//	项目名称
	public String relatedOrgCode="";//	实施部门
	public String DealMode="";//	实施方式
	public float TotalCost=0;//	涉及金额
	public String CheckContents="";//	审核内容
	public String FirstAuditOrg="";//	初审单位
	public String FirstAuditor="";//	初审人
	public String FirstAuditOpinion	="";//初审意见
	public String FirstAuditSummary="";//	初审摘要
	public Date FirstAuditDate=null;//	初审日期
	public String FirstAuditFlag="";//	初审标志
	public String SecondAuditOrg="";//	二审单位
	public String SecondAuditor="";//二审人
	public String SecondAudiOpiniont="";//终审意见
	public Date SecondAuditDate=null;//	二审日期
	public String SecondAuditFlag="";//	二审标志
	public String FinalAuditOpinion="";//宗申意见
	public Date FinalAuditDate=null;//	终审日期
	public String FinalAuditFlag="";//	终审标志
	public String des="";//备注
	public String summitflag="";//呈报标志
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
				this.ReportNo=r.getString("reportno");//	呈报记录序号
				
				this.ProcjetCode=r.getString("projectcode");//	物资类别代码(项目大类)
				this.ProjectName=r.getString("projectname");//	项目名称
				this.relatedOrgCode=r.getString("relatedorgcode");//	实施部门
				this.DealMode=r.getString("dealmode");//	实施方式
				this.TotalCost=Float.parseFloat(Format.NullToZero(r.getString("totalcost")));//	涉及金额
				this.CheckContents=r.getString("checkcontents");//	审核内容
				this.FirstAuditOrg=r.getString("firstauditorg");//	初审单位
				this.FirstAuditor=r.getString("firstauditor");//	初审人
				this.FirstAuditOpinion	=r.getString("firstauditopinion");//初审意见
				this.FirstAuditSummary=r.getString("firstauditsummary");//	初审摘要
				this.FirstAuditDate=Format.strToDate(r.getString("firstauditdate"));//	初审日期
				this.FirstAuditFlag=r.getString("firstauditflag");//	初审标志
				this.SecondAuditOrg=r.getString("secondauditorg");//	二审单位
				this.SecondAuditor=r.getString("secondauditor");//	二审人SECONDAUDIOPINIONT
				this.SecondAudiOpiniont=r.getString("secondaudiopiniont");//	终审意见
				this.SecondAuditDate=Format.strToDate(r.getString("secondauditdate"));//	终审日期
				this.SecondAuditFlag=r.getString("secondauditflag");//	二审标志
				this.FinalAuditOpinion=r.getString("finalauditopinion");//	宗申意见
				this.FinalAuditDate=Format.strToDate(r.getString("finalauditdate"));//	终审日期
				this.FinalAuditFlag=r.getString("finalauditflag");//	终审标志
				this.des=r.getString("memo");//备注
				this.summitflag=r.getString("summitflag");//呈报标志
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
	//获取reportno
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
     		   base_sql = "select  '选择' as 选择 ,reportno,reportno as 呈报编号,projectcode as  项目代码 ,projectname as 项目名称,relatedorgcode as  实施部门, dealmode as 实施方式 ,totalcost as 涉及金额,checkcontents as  审议重点 ,firstauditorg as 初审部门,firstauditopinion as  初审意见 ,firstauditsummary as 摘要 ,summitflag as 呈报标志 ,'操作' as 操作  from (select * from com_buyreportitem order by reportno)";

            }
            if(flag==1) {
      		   base_sql = "select  '选择' as 选择 ,reportno,reportno as 呈报编号,projectcode as  项目代码 ,projectname as 项目名称,relatedorgcode as  实施部门, dealmode as 实施方式 ,totalcost as 涉及金额,checkcontents as  审议重点 ,firstauditorg as 初审部门,firstauditopinion as  初审意见 ,secondauditflag as 二审结果, finalauditflag   as 终审结果   ,firstauditsummary as 摘要 ,summitflag as 呈报标志  ,'操作' as 操作  from (select * from com_buyreportitem order by reportno)";//+ and relatedorgcode='orgcode'";

			}}
            if(state==true){
            	
           		   base_sql = "select  '选择' as 选择 ,reportno,reportno as 呈报编号,projectcode as  项目代码 ,projectname as 项目名称,relatedorgcode as  实施部门, dealmode as 实施方式 ,totalcost as 涉及金额,checkcontents as  审议重点 ,firstauditorg as 初审部门,firstauditopinion as  初审意见 ,firstauditsummary as 摘要 ,summitflag as 呈报标志 ,'操作' as 操作  from (select * from com_buyreportitem order by reportno) where summitflag='0'";//+ and relatedorgcode='orgcode'";

                
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
	
	//获取呈报事件项
	public DataTable getReportEventItemlist(int pageno, int perpage,String eventno){
		try {
			DBObject db=new DBObject();
            String base_sql="";
          	
           		   base_sql = "select  '选择' as 选择 ,reportno,reportno as 呈报编号,projectcode as  项目代码 ,projectname as 项目名称,relatedorgcode as  实施部门, dealmode as 实施方式 ,totalcost as 涉及金额,checkcontents as  审议重点 ,firstauditorg as 初审部门,firstauditopinion as  初审意见 ,firstauditsummary as 摘要 ,summitflag as 呈报标志 ,'操作' as 操作  from (select * from com_buyreportitem order by reportno) where eventno='"+eventno+"'";

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
		return "成功";
	} catch (Exception e) {
		// TODO: handle exception
		return "失败";
	}
	}
	public static String AutoGetreport()
	{
		try {
			//获取申报事件
			
			String sql_appevent="select eventno from com_buyreportevent where summitflag!='0' and eventno like 'BY%' and auditflag='00'";
			DBObject db_appevent=new DBObject();
			DataTable dt_appevent=new DataTable();
			dt_appevent=db_appevent.runSelectQuery(sql_appevent);
			
			
			String sql_appeventitem="select projectcode ,projectname,sum(totalcost),count(buyno)  from com_buygoodsitem where eventno=? and summitflag=? and auditflag=? group by projectcode,projectname";
			if(dt_appevent!=null&&dt_appevent.getRowsCount()>0){
				
			      for(int i=0;i<dt_appevent.getRowsCount();i++){
				      
			    	  //根据申报事件获得申报物资项
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
			    		    	//产生呈报表
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
			                      
			                    //生成呈报——物资关系
			                      String  sql_reportgoodsre="insert into com_buygoodsreportrelation(reportno,eventno) values(?,?)";
			                      DBObject db_reportgoodsre=new DBObject();
			                      Parameter.SqlParameter[] pp3=new Parameter.SqlParameter[]{
			                    	
			                    		  new Parameter.String(reportno),
			                    		  new Parameter.String(byeventno)
			                    		  
			                      };
			                      db_reportgoodsre.run(sql_reportgoodsre, pp3);
			                    //修改物资标志
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
			
		
			return "自动产生成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "自动产生失败";
		}
	}
	//呈报
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
	return "呈报成功";
	} catch (Exception e) {
		// TODO: handle exception
		return "呈报失败";
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
//物资审核状态修改
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
//修改汇总金额
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
