package com.business;

import java.util.Date;

import com.common.Format;
import com.dao.system.LoadCode;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class BuyGoodsItem {

	//���ֶ�
	public String BuyNo="";//	�������
	public String EventNo="";//	�¼����
	public String ProjectCode="";//	��Ŀ����
	public String ProjectName="";//	��Ŀ����
	public String GoodsCode="";//����Ʒ������
	public String GoodsName="";//	����Ʒ��
	public String GoodsStyle="";//	����ͺ�
	public String GoodsUnit="";//	������λ
	public String BuyGoodsDesc="";//	˵��
	public float GoodsPrice=0;//	����
	public int GoodsNumber=0;//	����
	public float TotalCost=0;//	���
	public Date NeedMonth=null;//	����ʱ��
	public String BuyMode="";//	�ɹ�ģʽ
	public String BuyOrgCode="";//	���벿��
	public String Handler="";//	������
	public Date InputDate=null;//	��дʱ��
	public Date SummitDate=null;//	�ύʱ��
	public int SummitFlag=0;//	�ύ��־
	public String AuditOrgCode="";//	��˲���
	public String AuditOpinion="";//	������
	public String AuditFlag="";//	��˱�־

	
	public BuyGoodsItem(){
		
	}
	public BuyGoodsItem(String buyno){
		try {
		String sql="select * from com_buygoodsitem where buyno=?";
		Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
			new Parameter.String(buyno)	
		};
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql, pp);
		if(dt!=null&&dt.getRowsCount()==1)
		{
			DataRow r=dt.get(0);
			this.BuyNo=buyno;
			
			this.EventNo=r.getString("eventno");//	�¼����
			
			this.ProjectCode=r.getString("projectcode");//	��Ŀ����
			this.ProjectName=r.getString("projectname");//	��Ŀ����
			this.GoodsCode=r.getString("goodscode");//����Ʒ������
			this.GoodsName=r.getString("goodsname");//	����Ʒ��
			this.GoodsStyle=r.getString("goodsstyle");//	����ͺ�
			this.GoodsUnit=r.getString("goodsunit");//	������λ
			this.BuyGoodsDesc=r.getString("buygoodsdesc");//	˵��
		
			
			this.GoodsPrice=Float.parseFloat(Format.NullToZero(r.getString("goodsprice")));//	����
			
			this.GoodsNumber=Integer.parseInt(Format.NullToZero(r.getString("goodsnumber")));//	����
			this.TotalCost=Float.parseFloat(Format.NullToZero(r.getString("totalcost")));//	���
			this.NeedMonth=Format.strToDate(r.getString("needmonth"));//	����ʱ��
			
			this.BuyMode=r.getString("buymode");//	�ɹ�ģʽ
			this.BuyOrgCode=r.getString("buyorgcode");//	���벿��
			this.Handler=r.getString("handler");//	������
			this.InputDate=Format.strToDate(r.getString("inputdate"));//	��дʱ��
			this.SummitDate=Format.strToDate(r.getString("summitdate"));//	�ύʱ��
			this.SummitFlag=Integer.parseInt(Format.NullToZero(r.getString("summitflag")));//	�ύ��־
			this.AuditOrgCode=r.getString("auditorgcode");//	��˲���
			this.AuditOpinion=r.getString("auditopinion");//	������
			this.AuditFlag=r.getString("auditflag");//	��˱�־
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getBuyNo() {
		return BuyNo;
	}
	public void setBuyNo(String buyNo) {
		BuyNo = buyNo;
	}
	public String getEventNo() {
		return EventNo;
	}
	public void setEventNo(String eventNo) {
		EventNo = eventNo;
	}
	public String getProjectCode() {
		return ProjectCode;
	}
	public void setProjectCode(String projectCode) {
		ProjectCode = projectCode;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getGoodsCode() {
		return GoodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		GoodsCode = goodsCode;
	}
	public String getGoodsName() {
		return GoodsName;
	}
	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}
	public String getGoodsStyle() {
		return GoodsStyle;
	}
	public void setGoodsStyle(String goodsStyle) {
		GoodsStyle = goodsStyle;
	}
	public String getGoodsUnit() {
		return GoodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		GoodsUnit = goodsUnit;
	}
	public String getBuyGoodsDesc() {
		return BuyGoodsDesc;
	}
	public void setBuyGoodsDesc(String buyGoodsDesc) {
		BuyGoodsDesc = buyGoodsDesc;
	}
	public float getGoodsPrice() {
		return GoodsPrice;
	}
	public void setGoodsPrice(float goodsPrice) {
		GoodsPrice = goodsPrice;
	}
	public int getGoodsNumber() {
		return GoodsNumber;
	}
	public void setGoodsNumber(int goodsNumber) {
		GoodsNumber = goodsNumber;
	}
	public float getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(float totalCost) {
		TotalCost = totalCost;
	}
	public Date getNeedMonth() {
		return NeedMonth;
	}
	public void setNeedMonth(Date needMonth) {
		NeedMonth = needMonth;
	}
	public String getBuyMode() {
		return BuyMode;
	}
	public void setBuyMode(String buyMode) {
		BuyMode = buyMode;
	}
	public String getBuyOrgCode() {
		return BuyOrgCode;
	}
	public void setBuyOrgCode(String buyOrgCode) {
		BuyOrgCode = buyOrgCode;
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
	public String getAuditOrgCode() {
		return AuditOrgCode;
	}
	public void setAuditOrgCode(String auditOrgCode) {
		AuditOrgCode = auditOrgCode;
	}
	public String getAuditOpinion() {
		return AuditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		AuditOpinion = auditOpinion;
	}
	public String getAuditFlag() {
		return AuditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		AuditFlag = auditFlag;
	}
	public static String getBuyno(){
		return BuyGoodsApp.getEventno("COM_BUYGOODSITEM", "BR");
	}
	public static String audit(String[] str)
	{
		try {
			String sql="update com_buygoodsitem set auditflag=? where buyno=?";
		for(int i=0;i<str.length;i++)
		{
			DBObject db=new DBObject();
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.String("10"),
				new  Parameter.String(str[i])
			};
			db.run(sql, pp);
		}
		return "��˳ɹ�";
		} catch (Exception e) {
			// TODO: handle exception
			return "���ʧ��";
		}
	}
	
	//��ȡ�ɹ�¼���б�
	 public DataTable getBuyGoodsitemlist(int pageno,int perpage){
	    	
	    	try {
				DBObject db=new DBObject();
			    String base_sql = "select  'ѡ��' as ѡ�� ,buyno,projectcode as ��Ŀ���� ,goodscode as Ʒ��, goodsstyle as ����ͺ�,goodsunit as ������λ, goodsnumber as �ɹ�����,goodsprice as ����,totalcost as Ԥ����,buygoodsdesc as �ɹ�˵��,needmonth as ����ʱ�� , summitflag as �ύ״̬, '����' as ����  from (select * from com_buygoodsitem order by buyno )where summitflag='0'";
					String sql_run = Format.getFySql(base_sql, pageno, perpage);
					return db.runSelectQuery(sql_run);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
	    }
	  
	    public DataTable getAllBuyGoodsitemlist()
		{
			try
			{
				DBObject db = new DBObject();
				
					DataTable dt = db.runSelectQuery("select * from com_buygoodsitem where summitflag='0'");
					return dt;	
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}

 public DataTable getBuyGoodsitemlist(String appflag,int pageno,int perpage){
	    	
	    	try {
				DBObject db=new DBObject();
			    String base_sql = "select  'ѡ��' as ѡ�� ,buyno,projectcode as ��Ŀ���� ,goodscode as Ʒ��, goodsstyle as ����ͺ�,goodsunit as ������λ, goodsnumber as �ɹ�����,goodsprice as ����,totalcost as Ԥ����,buymode as �ɹ�˵��,needmonth as ����ʱ�� ,inputdate as ¼��ʱ��, '����' as ����  from (select * from com_buygoodsitem where eventno is "+appflag+" order by buyno)";
				//System.out.println(base_sql);	
			    String sql_run = Format.getFySql(base_sql, pageno, perpage);
					return db.runSelectQuery(sql_run);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
	    }
	  
	    public DataTable getAllBuyGoodsitemlist(String appflag)
		{
			try
			{
				DBObject db = new DBObject();
				
					DataTable dt = db.runSelectQuery("select * from com_buygoodsitem where eventno is "+appflag+"");
					return dt;	
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
	    
	  //��ȡ�걨�¼������б�
 public DataTable getBuyAppeventitemlist(String eventno,int pageno,int perpage){
	    	
	    	try {
				DBObject db=new DBObject();

			    String base_sql = "select  'ѡ��' as ѡ�� ,buyno,projectcode as ��Ŀ���� ,goodscode as Ʒ��, goodsstyle as ����ͺ�,goodsunit as ������λ, goodsnumber as �ɹ�����,goodsprice as ����,totalcost as Ԥ����,buymode as �ɹ�˵��,needmonth as ����ʱ�� , auditflag as ������   from (select * from com_buygoodsitem where eventno='"+eventno+"' order by buyno)";
					String sql_run = Format.getFySql(base_sql, pageno, perpage);
					return db.runSelectQuery(sql_run);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
	    }
	  
	    public DataTable getAllBuyAppitemlist(String eventno)
		{
			try
			{
				DBObject db = new DBObject();
				
					DataTable dt = db.runSelectQuery("select * from com_buygoodsitem where eventno='"+eventno+"'");
					return dt;	
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}

	    
//��ȡָ����Ŀ��Ӧ�����б�
 public DataTable getBuyReportGoodsItemlist(String projectcode,int pageno,int perpage){
 	
 	try {
			DBObject db=new DBObject();

		    String base_sql = "select  'ѡ��' as ѡ�� ,buyno,projectcode as ��Ŀ���� ,goodscode as Ʒ��, goodsstyle as ����ͺ�,goodsunit as ������λ, goodsnumber as �ɹ�����,goodsprice as ����,totalcost as Ԥ����,buymode as �ɹ�˵��,needmonth as ����ʱ��  from (select * from com_buygoodsitem where summitflag='1' and auditflag='00' and projectcode='"+projectcode+"' order by buyno)";
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
				return db.runSelectQuery(sql_run);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
 }

 public DataTable getAllBuyReportGoodsItemlist(String projectcode)
	{
		try
		{
			DBObject db = new DBObject();
			
				DataTable dt = db.runSelectQuery("select * from com_buygoodsitem where summitflag='1' and auditflag='00' and projectcode='"+projectcode+"'");
				return dt;	
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
//��ȡ�ʱ���Ŀ��Ӧ�����б�
 public DataTable getBuyReportitemlist(String reportno,String projectcode,int pageno,int perpage,String auditflag){
 	
 	try {
			DBObject db=new DBObject();

		    String base_sql = "select  'ѡ��' as ѡ�� ,buyno,projectname as ��Ŀ���� ,goodscode as Ʒ��, goodsstyle as ����ͺ�,goodsunit as ������λ, goodsnumber as �ɹ�����,goodsprice as ����,totalcost as Ԥ����,buymode as �ɹ�˵��,needmonth as ����ʱ�� , buyorgcode as �ɹ����� , '����' as ����  from (select a.* from com_buygoodsitem a ,com_buygoodsreportrelation b   where b.reportno='"+reportno+"' and a.auditflag='"+auditflag+"' and a.projectcode='"+projectcode+"'  and a.eventno=b.eventno order by a.buyno)";
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
				return db.runSelectQuery(sql_run);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
 }
	    public DataTable getAllBuyReportitemlist(String reportno,String projectcode,String auditflag)
		{
			try
			{
				DBObject db = new DBObject();
				
					DataTable dt = db.runSelectQuery("select a.* from com_buygoodsitem a ,com_buygoodsreportrelation b   where b.reportno='"+reportno+"'  and a.projectcode='"+projectcode+"'  and a.auditflag='"+auditflag+"' and a.eventno=b.eventno order by a.buyno");
					return dt;	
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
	    
	
	    //�õ���ڲ����Ѿ�ͨ����˵��б�
	    public DataTable getAllBuyReportitemlist2(String reportno,String projectcode,String auditflag)
		{
			try
			{
				DBObject db = new DBObject();
				
					DataTable dt = db.runSelectQuery("select a.* from com_buygoodsitem a ,com_buygoodsreportrelation b   where b.reportno='"+reportno+"'  and a.projectcode='"+projectcode+"'  and a.auditflag='"+auditflag+"' and a.eventno=b.eventno order by a.buyno");
					return dt;	
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
	    //��ڲ����˺��ϱ�����
	    public static boolean deleteReportgoods(String buyno){
	    	try {
				String sql="update com_buygoodsitem set auditflag='010' where buyno='"+buyno+"'";
				DBObject db=new DBObject();
				return db.run(sql);
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
	    }
	    //��ȡ���ʹ�ڲ���
	    public static String getAuditorgcode(String goodscode){
	    	try {
				LoadCode ld=new LoadCode();
				DataTable dt=ld.getSelectItem("COM_GOODSCLASS");
				String auditorgcode="";
				for(int i=0;i<dt.getRowsCount();i++)
				{
					if(goodscode.equals(dt.get(i).getString("goodscode"))){
						auditorgcode=dt.get(i).getString("auditorgcode");
						break;
					}
				}
	    		return auditorgcode;
			} catch (Exception e) {
				return null;
				// TODO: handle exception
			}
	    	
	    	
	    }
}
