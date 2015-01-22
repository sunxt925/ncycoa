package com.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.CodeDictionary;
import com.common.Format;
import com.dao.system.LoadCode;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class BuyGoodsItem {

	private static Logger logger=Logger.getLogger(BuyGoodsItem.class);
	//表字段
	public String BuyNo="";//	申请序号
	public String EventNo="";//	事件序号
	public String ProjectCode="";//	项目代码
	public String ProjectName="";//	项目名称
	public String GoodsCode="";//物资品名代码
	public String GoodsName="";//	物资品名
	public String GoodsStyle="";//	规格型号
	public String GoodsUnit="";//	计量单位
	public String BuyGoodsDesc="";//	说明
	public float GoodsPrice=0;//	单价
	public int GoodsNumber=0;//	数量
	public float TotalCost=0;//	金额
	public Date NeedMonth=null;//	需求时间
	public String BuyMode="";//	采购模式
	public String BuyOrgCode="";//	申请部门
	public String Handler="";//	经办人
	public Date InputDate=null;//	填写时间
	public Date SummitDate=null;//	提交时间
	public int SummitFlag=0;//	提交标志
	public String AuditOrgCode="";//	审核部门
	public String AuditOpinion="";//	审核意见
	public String AuditFlag="";//	审核标志

	
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
			
			this.EventNo=r.getString("eventno");//	事件序号
			
			this.ProjectCode=r.getString("projectcode");//	项目代码
			this.ProjectName=r.getString("projectname");//	项目名称
			this.GoodsCode=r.getString("goodscode");//物资品名代码
			this.GoodsName=r.getString("goodsname");//	物资品名
			this.GoodsStyle=r.getString("goodsstyle");//	规格型号
			this.GoodsUnit=r.getString("goodsunit");//	计量单位
			this.BuyGoodsDesc=r.getString("buygoodsdesc");//	说明
		
			
			this.GoodsPrice=Float.parseFloat(Format.NullToZero(r.getString("goodsprice")));//	单价
			
			this.GoodsNumber=Integer.parseInt(Format.NullToZero(r.getString("goodsnumber")));//	数量
			this.TotalCost=Float.parseFloat(Format.NullToZero(r.getString("totalcost")));//	金额
			this.NeedMonth=Format.strToDate(r.getString("needmonth"));//	需求时间
			
			this.BuyMode=r.getString("buymode");//	采购模式
			this.BuyOrgCode=r.getString("buyorgcode");//	申请部门
			this.Handler=r.getString("handler");//	经办人
			this.InputDate=Format.strToDate(r.getString("inputdate"));//	填写时间
			this.SummitDate=Format.strToDate(r.getString("summitdate"));//	提交时间
			this.SummitFlag=Integer.parseInt(Format.NullToZero(r.getString("summitflag")));//	提交标志
			this.AuditOrgCode=r.getString("auditorgcode");//	审核部门
			this.AuditOpinion=r.getString("auditopinion");//	审核意见
			this.AuditFlag=r.getString("auditflag");//	审核标志
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
		return "审核成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "审核失败";
		}
	}

	/**
	 * 根据flag构造采购商品数据
	 * @param flag
	 * @return
	 */
	public String getBuygoodsItemJson(int flag){
		return getBuygoodsItemJson(flag, "");
	}
	public String getBuygoodsItemJson(int flag,String eventno){
		try {
			List<BuyGoodsItem> goodslist = getBuygoodsItemlist(flag,eventno);
			StringBuilder sbuilder = new StringBuilder();
			sbuilder.append("[");
			if(goodslist.size()>0){
				for(BuyGoodsItem goodsItem : goodslist){
					sbuilder.append("{");
					
					sbuilder.append("\"buyno\":").append("\""+goodsItem.getBuyNo()+"\"").append(",");
					sbuilder.append("\"projectcode\":").append("\""+goodsItem.getProjectCode()+"\"").append(",");
					sbuilder.append("\"projectname\":").append("\""+goodsItem.getProjectName()+"\"").append(",");
					sbuilder.append("\"goodscode\":").append("\""+goodsItem.getGoodsCode()+"\"").append(",");
					sbuilder.append("\"goodsname\":").append("\""+goodsItem.getGoodsName()+"\"").append(",");
					sbuilder.append("\"goodstyle\":").append("\""+goodsItem.getGoodsStyle()+"\"").append(",");
					sbuilder.append("\"goodsunit\":").append("\""+goodsItem.getGoodsUnit()+"\"").append(",");
					sbuilder.append("\"goodsnumber\":").append("\""+goodsItem.getGoodsNumber()+"\"").append(",");
					sbuilder.append("\"goodsprice\":").append("\""+goodsItem.getGoodsPrice()+"\"").append(",");
					sbuilder.append("\"totalcost\":").append("\""+goodsItem.getTotalCost()+"\"").append(",");
					sbuilder.append("\"buygoodsdesc\":").append("\""+goodsItem.getBuyGoodsDesc()+"\"").append(",");
					sbuilder.append("\"needmonth\":").append("\""+Format.dateToStr(goodsItem.getNeedMonth())+"\"").append(",");
					sbuilder.append("\"inputmonth\":").append("\""+Format.dateToStr(goodsItem.getInputDate())+"\"").append(",");
					
					sbuilder.append("\"summitflag\":").append("\""+CodeDictionary.code_traslate("summitflag", String.valueOf(goodsItem.getSummitFlag()))+"\"");
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
	 * 根据flag获取采购商品列表
	 * @param flag
	 * @return
	 */
	public List<BuyGoodsItem> getBuygoodsItemlist(int flag){
		return getBuygoodsItemlist(flag, "");
	}
	public List<BuyGoodsItem> getBuygoodsItemlist(int flag,String eventno){
		try {
			List<BuyGoodsItem> goodList = new ArrayList<BuyGoodsItem>();
			String sql="";
			if(eventno.equals("")){
				sql = "select * from com_buygoodsitem where summitflag='"+flag+"'";
			}else{
				sql = "select * from com_buygoodsitem where summitflag='"+flag+"' and eventno is "+eventno;
			}
			
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow r = null;
				for(int i = 0;i < dt.getRowsCount();i++){
					r = dt.get(i);
					BuyGoodsItem buyGoodsItem = new BuyGoodsItem();
					buyGoodsItem.setBuyNo(r.getString("buyno"));
					buyGoodsItem.setEventNo(r.getString("eventno"));
					buyGoodsItem.setProjectCode(r.getString("projectcode"));
					buyGoodsItem.setProjectName(r.getString("projectname"));//	项目名称
					buyGoodsItem.setGoodsCode(r.getString("goodscode"));//物资品名代码
					buyGoodsItem.setGoodsName(r.getString("goodsname"));//	物资品名
					buyGoodsItem.setGoodsStyle(r.getString("goodsstyle"));//	规格型号
					buyGoodsItem.setGoodsUnit(r.getString("goodsunit"));//	计量单位
					buyGoodsItem.setBuyGoodsDesc(r.getString("buygoodsdesc"));//	说明
				
					
					buyGoodsItem.setGoodsPrice(Float.parseFloat(Format.NullToZero(r.getString("goodsprice"))));//	单价
					
					buyGoodsItem.setGoodsNumber(Integer.parseInt(Format.NullToZero(r.getString("goodsnumber"))));//	数量
					buyGoodsItem.setTotalCost(Float.parseFloat(Format.NullToZero(r.getString("totalcost"))));//	金额
					buyGoodsItem.setNeedMonth(Format.strToDate(r.getString("needmonth")));//	需求时间
					
					buyGoodsItem.setBuyMode(r.getString("buymode"));//	采购模式
					buyGoodsItem.setBuyOrgCode(r.getString("buyorgcode"));//	申请部门
					buyGoodsItem.setHandler(r.getString("handler"));//	经办人
					buyGoodsItem.setInputDate(Format.strToDate(r.getString("inputdate")));//	填写时间
					buyGoodsItem.setSummitDate(Format.strToDate(r.getString("summitdate")));//	提交时间
					buyGoodsItem.setSummitFlag(Integer.parseInt(Format.NullToZero(r.getString("summitflag"))));//	提交标志
					buyGoodsItem.setAuditOrgCode(r.getString("auditorgcode"));//	审核部门
					buyGoodsItem.setAuditOpinion(r.getString("auditopinion"));//	审核意见
					buyGoodsItem.setAuditFlag(r.getString("auditflag"));//	审核标志
					goodList.add(buyGoodsItem);
				}
			}
			return goodList;
		} catch (Exception e) {
			logger.info("获取采购物品列表出错");
			return null;
		}
	}
/*	
 public DataTable getBuyGoodsitemlist(String appflag,int pageno,int perpage){
	    	
	    	try {
				DBObject db=new DBObject();
			    String base_sql = "select  '选择' as 选择 ,buyno,projectcode as 项目名称 ,goodscode as 品名, goodsstyle as 规格型号,goodsunit as 计量单位, goodsnumber as 采购数量,goodsprice as 单价,totalcost as 预算金额,buymode as 采购说明,needmonth as 需求时间 ,inputdate as 录入时间, '操作' as 操作  from (select * from com_buygoodsitem where eventno is "+appflag+" order by buyno)";
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
		}*/
	    
	public String getBuyAppeventitemJson(String eventno){
		try {
			List<BuyGoodsItem> goodslist = getBuyAppeventitemlist(eventno);
			StringBuilder sbuilder = new StringBuilder();
			sbuilder.append("[");
			if(goodslist.size()>0){
				for(BuyGoodsItem goodsItem : goodslist){
					sbuilder.append("{");
					
					sbuilder.append("\"buyno\":").append("\""+goodsItem.getBuyNo()+"\"").append(",");
					sbuilder.append("\"projectname\":").append("\""+goodsItem.getProjectName()+"\"").append(",");
					sbuilder.append("\"goodsname\":").append("\""+goodsItem.getGoodsName()+"\"").append(",");
					sbuilder.append("\"goodstyle\":").append("\""+goodsItem.getGoodsStyle()+"\"").append(",");
					sbuilder.append("\"goodsunit\":").append("\""+goodsItem.getGoodsUnit()+"\"").append(",");
					sbuilder.append("\"goodsnumber\":").append("\""+goodsItem.getGoodsNumber()+"\"").append(",");
					sbuilder.append("\"goodsprice\":").append("\""+goodsItem.getGoodsPrice()+"\"").append(",");
					sbuilder.append("\"totalcost\":").append("\""+goodsItem.getTotalCost()+"\"").append(",");
					sbuilder.append("\"buygoodsdesc\":").append("\""+goodsItem.getBuyGoodsDesc()+"\"").append(",");
					sbuilder.append("\"needmonth\":").append("\""+Format.dateToStr(goodsItem.getNeedMonth())+"\"").append(",");
					
					sbuilder.append("\"auditflag\":").append("\""+CodeDictionary.code_traslate("summitflag", goodsItem.getAuditFlag())+"\"");
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
	public List<BuyGoodsItem> getBuyAppeventitemlist(String eventno){
		try {
			String sql="select * from com_buygoodsitem where eventno='"+eventno+"'";
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
					buyGoodsItem.setProjectName(r.getString("projectname"));//	项目名称
					buyGoodsItem.setGoodsCode(r.getString("goodscode"));//物资品名代码
					buyGoodsItem.setGoodsName(r.getString("goodsname"));//	物资品名
					buyGoodsItem.setGoodsStyle(r.getString("goodsstyle"));//	规格型号
					buyGoodsItem.setGoodsUnit(r.getString("goodsunit"));//	计量单位
					buyGoodsItem.setBuyGoodsDesc(r.getString("buygoodsdesc"));//	说明
				
					
					buyGoodsItem.setGoodsPrice(Float.parseFloat(Format.NullToZero(r.getString("goodsprice"))));//	单价
					
					buyGoodsItem.setGoodsNumber(Integer.parseInt(Format.NullToZero(r.getString("goodsnumber"))));//	数量
					buyGoodsItem.setTotalCost(Float.parseFloat(Format.NullToZero(r.getString("totalcost"))));//	金额
					buyGoodsItem.setNeedMonth(Format.strToDate(r.getString("needmonth")));//	需求时间
					
					buyGoodsItem.setBuyMode(r.getString("buymode"));//	采购模式
					buyGoodsItem.setBuyOrgCode(r.getString("buyorgcode"));//	申请部门
					buyGoodsItem.setHandler(r.getString("handler"));//	经办人
					buyGoodsItem.setInputDate(Format.strToDate(r.getString("inputdate")));//	填写时间
					buyGoodsItem.setSummitDate(Format.strToDate(r.getString("summitdate")));//	提交时间
					buyGoodsItem.setSummitFlag(Integer.parseInt(Format.NullToZero(r.getString("summitflag"))));//	提交标志
					buyGoodsItem.setAuditOrgCode(r.getString("auditorgcode"));//	审核部门
					buyGoodsItem.setAuditOpinion(r.getString("auditopinion"));//	审核意见
					buyGoodsItem.setAuditFlag(r.getString("auditflag"));//	审核标志
					list.add(buyGoodsItem);
				}
			}
			return list;
		} catch (Exception e) {
			logger.info("获取采购物品列表出错");
			return null;
		}
	}
	/*  //获取申报事件物资列表
 public DataTable getBuyAppeventitemlist(String eventno,int pageno,int perpage){
	    	
	    	try {
				DBObject db=new DBObject();

			    String base_sql = "select  '选择' as 选择 ,buyno,projectcode as 项目名称 ,goodscode as 品名, goodsstyle as 规格型号,goodsunit as 计量单位, goodsnumber as 采购数量,goodsprice as 单价,totalcost as 预算金额,buymode as 采购说明,needmonth as 需求时间 , auditflag as 处理结果   from (select * from com_buygoodsitem where eventno='"+eventno+"' order by buyno)";
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

	    */
//获取指定项目对应物资列表
 public DataTable getBuyReportGoodsItemlist(String projectcode,int pageno,int perpage){
 	
 	try {
			DBObject db=new DBObject();

		    String base_sql = "select  '选择' as 选择 ,buyno,projectcode as 项目名称 ,goodscode as 品名, goodsstyle as 规格型号,goodsunit as 计量单位, goodsnumber as 采购数量,goodsprice as 单价,totalcost as 预算金额,buymode as 采购说明,needmonth as 需求时间  from (select * from com_buygoodsitem where summitflag='1' and auditflag='00' and projectcode='"+projectcode+"' order by buyno)";
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
//获取呈报项目对应物资列表
 public DataTable getBuyReportitemlist(String reportno,String projectcode,int pageno,int perpage,String auditflag){
 	
 	try {
			DBObject db=new DBObject();

		    String base_sql = "select  '选择' as 选择 ,buyno,projectname as 项目名称 ,goodscode as 品名, goodsstyle as 规格型号,goodsunit as 计量单位, goodsnumber as 采购数量,goodsprice as 单价,totalcost as 预算金额,buymode as 采购说明,needmonth as 需求时间 , buyorgcode as 采购部门 , '操作' as 操作  from (select a.* from com_buygoodsitem a ,com_buygoodsreportrelation b   where b.reportno='"+reportno+"' and a.auditflag='"+auditflag+"' and a.projectcode='"+projectcode+"'  and a.eventno=b.eventno order by a.buyno)";
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
	    
	
	    //得到归口部门已经通过审核的列表
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
	    //归口部门退后上报物资
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
	    //获取物资归口部门
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
