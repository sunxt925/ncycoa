package com.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.CodeDictionary;
import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.UserInfo;

public class BuyReportItem {

	private Logger logger = Logger.getLogger(BuyReportItem.class);
	private String ReportNo="";//	�ʱ���¼���
	private String EventNo="";//	�¼���ˮ��
	private String ProcjetCode="";//	����������(��Ŀ����)
	private String ProjectName="";//	��Ŀ����
	private String relatedOrgCode="";//	ʵʩ����
	private String DealMode="";//	ʵʩ��ʽ
	private float TotalCost=0;//	�漰���
	private String CheckContents="";//	�������
	private String FirstAuditOrg="";//	����λ
	private String FirstAuditor="";//	������
	private String FirstAuditOpinion	="";//�������
	private String FirstAuditSummary="";//	����ժҪ
	private Date FirstAuditDate=null;//	��������
	private String FirstAuditFlag="";//	�����־
	private String SecondAuditOrg="";//	����λ
	private String SecondAuditor="";//������
	private String SecondAudiOpiniont="";//�������
	private Date SecondAuditDate=null;//	��������
	private String SecondAuditFlag="";//	�����־
	private String FinalAuditOpinion="";//�������
	private Date FinalAuditDate=null;//	��������
	private String FinalAuditFlag="";//	�����־
	private String des="";//��ע
	private String summitflag="";//�ʱ���־
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
	/**
	 * ����ʱ��б�����
	 * @return
	 */
	public String getBuyreportJson(){
		return getBuyreportJson(false);
	}
    public String getBuyreportJson(boolean state){
		try {
			List<BuyReportItem> list = getBuyreportList(state);
			StringBuilder sbuilder = new StringBuilder();
			sbuilder.append("[");
			if(list.size()>0){
				for(BuyReportItem reportItem : list){
					sbuilder.append("{");
				
					sbuilder.append("\"reportno\":").append("\""+reportItem.getReportNo()+"\"").append(",");
					sbuilder.append("\"eventno\":").append("\""+reportItem.getEventNo()+"\"").append(",");
					sbuilder.append("\"projectcode\":").append("\""+reportItem.getProcjetCode()+"\"").append(",");
					sbuilder.append("\"projectname\":").append("\""+reportItem.getProjectName()+"\"").append(",");
					sbuilder.append("\"relatedorgcode\":").append("\""+reportItem.getRelatedOrgCode()+"\"").append(",");
					sbuilder.append("\"dealmode\":").append("\""+CodeDictionary.code_traslate("dealmode", reportItem.getDealMode())+"\"").append(",");
					sbuilder.append("\"totalcost\":").append("\""+reportItem.getTotalCost()+"\"").append(",");
					sbuilder.append("\"checkcontents\":").append("\""+reportItem.getCheckContents()+"\"").append(",");
					sbuilder.append("\"firstauditorg\":").append("\""+reportItem.getFirstAuditOrg()+"\"").append(",");
					sbuilder.append("\"firstauditor\":").append("\""+reportItem.getFirstAuditor()+"\"").append(",");
					sbuilder.append("\"firstauditopinion\":").append("\""+reportItem.getFirstAuditOpinion()+"\"").append(",");
					sbuilder.append("\"firstauditsummary\":").append("\""+reportItem.getFirstAuditSummary()+"\"").append(",");
					sbuilder.append("\"firstauditdate\":").append("\""+Format.dateToStr(reportItem.getFirstAuditDate())+"\"").append(",");
					sbuilder.append("\"firstauditflag\":").append("\""+reportItem.getFirstAuditFlag()+"\"").append(",");
					sbuilder.append("\"secondauditorg\":").append("\""+reportItem.getSecondAuditOrg()+"\"").append(",");
					sbuilder.append("\"secondauditor\":").append("\""+reportItem.getSecondAuditor()+"\"").append(",");
					sbuilder.append("\"secondaudiopiniont\":").append("\""+reportItem.getSecondAudiOpiniont()+"\"").append(",");
					sbuilder.append("\"secondauditdate\":").append("\""+Format.dateToStr(reportItem.getSecondAuditDate())+"\"").append(",");
					sbuilder.append("\"secondauditflag\":").append("\""+reportItem.getSecondAuditFlag()+"\"").append(",");
					sbuilder.append("\"finalauditopinion\":").append("\""+reportItem.getFinalAuditOpinion()+"\"").append(",");
					sbuilder.append("\"finalauditdate\":").append("\""+Format.dateToStr(reportItem.getFinalAuditDate())+"\"").append(",");
					sbuilder.append("\"des\":").append("\""+reportItem.getDes()+"\"").append(",");
					sbuilder.append("\"summitflag\":").append("\""+reportItem.getSummitflag()+"\"").append(",");
					sbuilder.append("\"op\":").append("\""+"<a href='#' onclick='modify()'>�޸�</a>"+" "+"<a href='#' onclick='detail()'>��ϸ</a>"+"\"").append(",");
					sbuilder.append("\"op_office\":").append("\""+"<a href='#' onclick='audit()'>���</a>"+"\"");
					
					sbuilder.append("}");
					sbuilder.append(",");
				}
				sbuilder.delete(sbuilder.length()-1, sbuilder.length());
			}
			
			sbuilder.append("]");
			return sbuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}
    /**
	 * ����ʱ��б�����
     * @param state
     * @return
     */
    public List<BuyReportItem> getBuyreportList(boolean state){
    	try {
    		List<BuyReportItem> list=new ArrayList<BuyReportItem>();
    		DBObject db = new DBObject();
			DataTable dt=new DataTable();
			if(!state)
			{	
				dt = db.runSelectQuery("select * from com_buyreportitem");//+ and relatedorgcode='orgcode'";
			}else{
				 dt = db.runSelectQuery("select * from com_buyreportitem where summitflag='0'");//+ and relatedorgcode='orgcode'";
			}
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow r=null;
				for(int i=0;i<dt.getRowsCount();i++){
					r=dt.get(i);
					BuyReportItem buyReportItem = new BuyReportItem();
					buyReportItem.setReportNo(r.getString("reportno"));//	�ʱ���¼���
					buyReportItem.setProcjetCode(r.getString("projectcode"));//	����������(��Ŀ����)
					buyReportItem.setProjectName(r.getString("projectname"));//	��Ŀ����
					buyReportItem.setRelatedOrgCode(r.getString("relatedorgcode"));//	ʵʩ����
					buyReportItem.setDealMode(r.getString("dealmode"));//	ʵʩ��ʽ
					buyReportItem.setTotalCost(Float.parseFloat(Format.NullToZero(r.getString("totalcost"))));//	�漰���
					buyReportItem.setCheckContents(r.getString("checkcontents"));//	�������
					buyReportItem.setFirstAuditOrg(r.getString("firstauditorg"));//	����λ
					buyReportItem.setFirstAuditor(r.getString("firstauditor"));//	������
					buyReportItem.setFirstAuditOpinion(r.getString("firstauditopinion"));//�������
					buyReportItem.setFirstAuditSummary(r.getString("firstauditsummary"));//	����ժҪ
					buyReportItem.setFirstAuditDate(Format.strToDate(r.getString("firstauditdate")));//	��������
					buyReportItem.setFirstAuditFlag(r.getString("firstauditflag"));//	�����־
					buyReportItem.setSecondAuditOrg(r.getString("secondauditorg"));//	����λ
					buyReportItem.setSecondAuditor(r.getString("secondauditor"));//	������SECONDAUDIOPINIONT
					buyReportItem.setSecondAudiOpiniont(r.getString("secondaudiopiniont"));//	�������
					buyReportItem.setSecondAuditDate(Format.strToDate(r.getString("secondauditdate")));//	��������
					buyReportItem.setSecondAuditFlag(r.getString("secondauditflag"));//	�����־
					buyReportItem.setFinalAuditOpinion(r.getString("finalauditopinion"));//	�������
					buyReportItem.setFinalAuditDate(Format.strToDate(r.getString("finalauditdate")));//	��������
					buyReportItem.setFinalAuditFlag(r.getString("finalauditflag"));//	�����־
					buyReportItem.setDes(r.getString("memo"));//��ע
					buyReportItem.setSummitflag(r.getString("summitflag"));//�ʱ���־
					list.add(buyReportItem);
				}
				
			}
			return list;
		} catch (Exception e) {
			logger.info("��ѯ�ʱ������");
			return null;
		}
    }
    /**
     * 
     * @param reportno
     * @param projectcode
     * @param auditflag
     * @return
     */
    public String getBuyReportitemJson(String reportno,String projectcode,String auditflag){
   		try {
   			List<BuyGoodsItem> goodslist = getBuyReportitemlist(reportno,projectcode,auditflag);
   			StringBuilder sbuilder = new StringBuilder();
   			sbuilder.append("[");
   			if(goodslist.size()>0){
   				for(BuyGoodsItem goodsItem : goodslist){
   					sbuilder.append("{");
   					sbuilder.append("\"buyno\":").append("\""+goodsItem.getBuyNo()+"\"").append(",");
   					sbuilder.append("\"projectname\":").append("\""+goodsItem.getProjectName()+"\"").append(",");
   					sbuilder.append("\"goodscode\":").append("\""+goodsItem.getGoodsCode()+"\"").append(",");
   					sbuilder.append("\"goodsname\":").append("\""+goodsItem.getGoodsName()+"\"").append(",");
   					sbuilder.append("\"goodstyle\":").append("\""+goodsItem.getGoodsStyle()+"\"").append(",");
   					sbuilder.append("\"goodsunit\":").append("\""+goodsItem.getGoodsUnit()+"\"").append(",");
   					sbuilder.append("\"goodsnumber\":").append("\""+goodsItem.getGoodsNumber()+"\"").append(",");
   					sbuilder.append("\"goodsprice\":").append("\""+goodsItem.getGoodsPrice()+"\"").append(",");
   					sbuilder.append("\"totalcost\":").append("\""+goodsItem.getTotalCost()+"\"").append(",");
   					sbuilder.append("\"buymode\":").append("\""+goodsItem.getBuyMode()+"\"").append(",");
   					sbuilder.append("\"buyorgcode\":").append("\""+goodsItem.getBuyOrgCode()+"\"").append(",");
   					sbuilder.append("\"needmonth\":").append("\""+Format.dateToStr(goodsItem.getNeedMonth())+"\"");
   					sbuilder.append("}");
   					sbuilder.append(",");
   				}
   				sbuilder.delete(sbuilder.length()-1, sbuilder.length());
   			}
   			
   			sbuilder.append("]");
   			return sbuilder.toString();
   		} catch (Exception e) {
   			return "";
   			
   		}
   	}
    /**
     * 
     * @param reportno
     * @param projectcode
     * @param auditflag
     * @return
     */
   	public List<BuyGoodsItem> getBuyReportitemlist(String reportno,String projectcode,String auditflag){
   		try {
   			String sql="select a.* from com_buygoodsitem a ,com_buygoodsreportrelation b   where b.reportno='"+reportno+"'  and a.projectcode='"+projectcode+"'  and a.auditflag='"+auditflag+"' and a.eventno=b.eventno order by a.buyno";
   			DBObject db = new DBObject();
   			DataTable dt = db.runSelectQuery(sql);
   			List<BuyGoodsItem> list=new ArrayList<BuyGoodsItem>();
   			if(dt!=null&&dt.getRowsCount()>=1){
   				DataRow r=null;
   				for(int i=0;i<dt.getRowsCount();i++){
   					r=dt.get(i);
   					BuyGoodsItem buyGoodsItem=new BuyGoodsItem();
   					buyGoodsItem.setBuyNo(r.getString("buyno"));
   					buyGoodsItem.setEventNo(r.getString("eventno"));
   					buyGoodsItem.setProjectCode(r.getString("projectcode"));
   					buyGoodsItem.setProjectName(r.getString("projectname"));//	��Ŀ����
   					buyGoodsItem.setGoodsCode(r.getString("goodscode"));//����Ʒ������
   					buyGoodsItem.setGoodsName(r.getString("goodsname"));//	����Ʒ��
   					buyGoodsItem.setGoodsStyle(r.getString("goodsstyle"));//	����ͺ�
   					buyGoodsItem.setGoodsUnit(r.getString("goodsunit"));//	������λ
   					buyGoodsItem.setBuyGoodsDesc(r.getString("buygoodsdesc"));//	˵��
   				
   					
   					buyGoodsItem.setGoodsPrice(Float.parseFloat(Format.NullToZero(r.getString("goodsprice"))));//	����
   					
   					buyGoodsItem.setGoodsNumber(Integer.parseInt(Format.NullToZero(r.getString("goodsnumber"))));//	����
   					buyGoodsItem.setTotalCost(Float.parseFloat(Format.NullToZero(r.getString("totalcost"))));//	���
   					buyGoodsItem.setNeedMonth(Format.strToDate(r.getString("needmonth")));//	����ʱ��
   					
   					buyGoodsItem.setBuyMode(r.getString("buymode"));//	�ɹ�ģʽ
   					buyGoodsItem.setBuyOrgCode(r.getString("buyorgcode"));//	���벿��
   					buyGoodsItem.setHandler(r.getString("handler"));//	������
   					buyGoodsItem.setInputDate(Format.strToDate(r.getString("inputdate")));//	��дʱ��
   					buyGoodsItem.setSummitDate(Format.strToDate(r.getString("summitdate")));//	�ύʱ��
   					buyGoodsItem.setSummitFlag(Integer.parseInt(Format.NullToZero(r.getString("summitflag"))));//	�ύ��־
   					buyGoodsItem.setAuditOrgCode(r.getString("auditorgcode"));//	��˲���
   					buyGoodsItem.setAuditOpinion(r.getString("auditopinion"));//	������
   					buyGoodsItem.setAuditFlag(r.getString("auditflag"));//	��˱�־
   					list.add(buyGoodsItem);
   				}
   			}
   			return list;
   		} catch (Exception e) {
   			logger.info("��ȡ�ɹ���Ʒ�б����");
   			return null;
   		}
   	}
   	/**
   	 * 
   	 * @param eventno
   	 * @return
   	 */
   	public String getReportEventitemJson(String eventno){
   		try {
			List<BuyReportItem> reportItems = getReportEventitemlist(eventno);
			StringBuilder sbuilder = new StringBuilder();
			sbuilder.append("[");
			if(reportItems.size()>0){
				for(BuyReportItem reportItem : reportItems){
					sbuilder.append("{");
					sbuilder.append("\"reportno\":").append("\""+reportItem.getReportNo()+"\"").append(",");
					sbuilder.append("\"eventno\":").append("\""+reportItem.getEventNo()+"\"").append(",");
					sbuilder.append("\"projectcode\":").append("\""+reportItem.getProcjetCode()+"\"").append(",");
					sbuilder.append("\"projectname\":").append("\""+reportItem.getProjectName()+"\"").append(",");
					sbuilder.append("\"relatedorgcode\":").append("\""+reportItem.getRelatedOrgCode()+"\"").append(",");
					sbuilder.append("\"dealmode\":").append("\""+reportItem.getDealMode()+"\"").append(",");
					sbuilder.append("\"totalcost\":").append("\""+reportItem.getTotalCost()+"\"").append(",");
					sbuilder.append("\"checkcontents\":").append("\""+reportItem.getCheckContents()+"\"").append(",");
					sbuilder.append("\"firstauditorg\":").append("\""+reportItem.getFirstAuditOrg()+"\"").append(",");
					sbuilder.append("\"firstauditor\":").append("\""+reportItem.getFirstAuditor()+"\"").append(",");
					sbuilder.append("\"firstauditopinion\":").append("\""+reportItem.getFirstAuditOpinion()+"\"").append(",");
					sbuilder.append("\"firstauditsummary\":").append("\""+reportItem.getFirstAuditSummary()+"\"").append(",");
					sbuilder.append("\"firstauditdate\":").append("\""+Format.dateToStr(reportItem.getFirstAuditDate())+"\"").append(",");
					sbuilder.append("\"firstauditflag\":").append("\""+reportItem.getFirstAuditFlag()+"\"").append(",");
					sbuilder.append("\"secondauditorg\":").append("\""+reportItem.getSecondAuditOrg()+"\"").append(",");
					sbuilder.append("\"secondauditor\":").append("\""+reportItem.getSecondAuditor()+"\"").append(",");
					sbuilder.append("\"secondaudiopiniont\":").append("\""+reportItem.getSecondAudiOpiniont()+"\"").append(",");
					sbuilder.append("\"secondauditdate\":").append("\""+Format.dateToStr(reportItem.getSecondAuditDate())+"\"").append(",");
					sbuilder.append("\"secondauditflag\":").append("\""+reportItem.getSecondAuditFlag()+"\"").append(",");
					sbuilder.append("\"finalauditopinion\":").append("\""+reportItem.getFinalAuditOpinion()+"\"").append(",");
					sbuilder.append("\"finalauditdate\":").append("\""+Format.dateToStr(reportItem.getFinalAuditDate())+"\"").append(",");
					sbuilder.append("\"des\":").append("\""+reportItem.getDes()+"\"").append(",");
					sbuilder.append("\"summitflag\":").append("\""+CodeDictionary.code_traslate("summitflag", reportItem.getSummitflag())+"\"").append(",");
					sbuilder.append("\"op\":").append("\""+"<a href='#' onclick='detail()'>��ϸ</a>"+"\"");
					sbuilder.append("}");
					sbuilder.append(",");
				}
				sbuilder.delete(sbuilder.length()-1, sbuilder.length());
			}
			
			sbuilder.append("]");
			return sbuilder.toString();
		} catch (Exception e) {
			return "";
		}
   	}
   	/**
   	 * 
   	 * @param eventno
   	 * @return
   	 */
   	public List<BuyReportItem> getReportEventitemlist(String eventno){
   		try {
   			List<BuyReportItem> reportItems = new ArrayList<BuyReportItem>();
   			DBObject db = new DBObject();
			DataTable dt=db.runSelectQuery("select * from com_buyreportitem where eventno='"+eventno+"'");
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow r=null;
				for(int i=0;i<dt.getRowsCount();i++){
					r=dt.get(i);
					BuyReportItem buyReportItem = new BuyReportItem();
					buyReportItem.setReportNo(r.getString("reportno"));//	�ʱ���¼���
					buyReportItem.setProcjetCode(r.getString("projectcode"));//	����������(��Ŀ����)
					buyReportItem.setProjectName(r.getString("projectname"));//	��Ŀ����
					buyReportItem.setRelatedOrgCode(r.getString("relatedorgcode"));//	ʵʩ����
					buyReportItem.setDealMode(r.getString("dealmode"));//	ʵʩ��ʽ
					buyReportItem.setTotalCost(Float.parseFloat(Format.NullToZero(r.getString("totalcost"))));//	�漰���
					buyReportItem.setCheckContents(r.getString("checkcontents"));//	�������
					buyReportItem.setFirstAuditOrg(r.getString("firstauditorg"));//	����λ
					buyReportItem.setFirstAuditor(r.getString("firstauditor"));//	������
					buyReportItem.setFirstAuditOpinion(r.getString("firstauditopinion"));//�������
					buyReportItem.setFirstAuditSummary(r.getString("firstauditsummary"));//	����ժҪ
					buyReportItem.setFirstAuditDate(Format.strToDate(r.getString("firstauditdate")));//	��������
					buyReportItem.setFirstAuditFlag(r.getString("firstauditflag"));//	�����־
					buyReportItem.setSecondAuditOrg(r.getString("secondauditorg"));//	����λ
					buyReportItem.setSecondAuditor(r.getString("secondauditor"));//	������SECONDAUDIOPINIONT
					buyReportItem.setSecondAudiOpiniont(r.getString("secondaudiopiniont"));//	�������
					buyReportItem.setSecondAuditDate(Format.strToDate(r.getString("secondauditdate")));//	��������
					buyReportItem.setSecondAuditFlag(r.getString("secondauditflag"));//	�����־
					buyReportItem.setFinalAuditOpinion(r.getString("finalauditopinion"));//	�������
					buyReportItem.setFinalAuditDate(Format.strToDate(r.getString("finalauditdate")));//	��������
					buyReportItem.setFinalAuditFlag(r.getString("finalauditflag"));//	�����־
					buyReportItem.setDes(r.getString("memo"));//��ע
					buyReportItem.setSummitflag(r.getString("summitflag"));//�ʱ���־
					reportItems.add(buyReportItem);
				}
			}
			return reportItems;
		} catch (Exception e) {
			logger.info("��ѯ�ʱ������ݳ���");
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
public static String Report(String[] str,String buymode,String auditflag,String staffcode){
	try {
		
	
	BuyGoodsItem buyGoodsItem=new BuyGoodsItem();
	 
	String eventno=BuyReportEvent.getEventno(str, buymode,staffcode);
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
