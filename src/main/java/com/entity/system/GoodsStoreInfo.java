package com.entity.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class GoodsStoreInfo {
	private String GoodsCode="";
	public String getGoodsCode() {
		return GoodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		GoodsCode = goodsCode;
	}
	public String getGoodsname() {
		return Goodsname;
	}
	public void setGoodsname(String goodsname) {
		Goodsname = goodsname;
	}
	public String getGoodsDesc() {
		return GoodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		GoodsDesc = goodsDesc;
	}
	public String getGoodsStyle() {
		return GoodsStyle;
	}
	public void setGoodsStyle(String goodsStyle) {
		GoodsStyle = goodsStyle;
	}
	public String getMeasureUnit() {
		return MeasureUnit;
	}
	public void setMeasureUnit(String measureUnit) {
		MeasureUnit = measureUnit;
	}
	public String getTotalNumber() {
		return TotalNumber;
	}
	public void setTotalNumber(String totalNumber) {
		TotalNumber = totalNumber;
	}
	public String getInCount() {
		return InCount;
	}
	public void setInCount(String inCount) {
		InCount = inCount;
	}
	public String getAvailableNumber() {
		return AvailableNumber;
	}
	public void setAvailableNumber(String availableNumber) {
		AvailableNumber = availableNumber;
	}
	public String getOutNumber() {
		return OutNumber;
	}
	public void setOutNumber(String outNumber) {
		OutNumber = outNumber;
	}
	public String getOutCount() {
		return outCount;
	}
	public void setOutCount(String outCount) {
		this.outCount = outCount;
	}
	public String getFirstInDate() {
		return FirstInDate;
	}
	public void setFirstInDate(String firstInDate) {
		FirstInDate = firstInDate;
	}
	public String getLastOutDate() {
		return LastOutDate;
	}
	public void setLastOutDate(String lastOutDate) {
		LastOutDate = lastOutDate;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	private String Goodsname="";
	private String GoodsDesc="";
	private String GoodsStyle="";
	private String MeasureUnit="";
	private String TotalNumber="0";
	private String InCount="0";
	private String AvailableNumber="0";
	private String OutNumber="0";
	private String outCount="0";
	private String FirstInDate="";
	private String LastOutDate="";
	private String Memo="";
	public GoodsStoreInfo()
	{
		
	}
	public GoodsStoreInfo(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select * from Com_StoreInfo t where t.GoodsCode=?";
			//String sql = "select * from BASE_ORG where orgcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.GoodsCode = PK;
				this.Goodsname = r.getString("Goodsname");
				this.GoodsDesc=r.getString("GoodsDesc");
				this.GoodsStyle = r.getString("GoodsStyle");
				this.MeasureUnit = r.getString("MeasureUnit");
				this.TotalNumber = r.getString("TotalNumber");
				this.InCount = r.getString("InCount");
				this.AvailableNumber = r.getString("AvailableNumber");
				this.OutNumber = r.getString("OutNumber");	
				this.outCount = r.getString("outCount");
				this.FirstInDate = r.getString("FirstInDate");
				this.LastOutDate = r.getString("LastOutDate");
				this.Memo = r.getString("Memo");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public DataTable getGoodsStoreInfo(int pageno, int perpage,String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||GoodsCode||'\">' as 选择,GoodsCode as 物资品名代码,GoodsName as 物资品名,GoodsDesc as 说明,GoodsStyle as 规格型号,MeasureUnit as 计量单位,TotalNumber as 总数,InCount as 入库次数,AvailableNumber as 可用数量,OutNumber as 领用数量,outCount as 出库次数,to_char(FirstInDate,'yyyy-mm-dd') as 最早入库时间,to_char(LastOutDate,'yyyy-mm-dd') as 最后出库时间,Memo as 备注     from Com_StoreInfo where GoodsCode='"+goodscode+"' order by GoodsCode";
			//System.out.println(base_sql);
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getGoodsStoreInfoNumber(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();
			
			//,'<a href=\"#\" onclick=F1(\"'||GoodsCode||','||GoodsName||','||AvailableNumber||'\") class=\"button4\">出库</a>' as 操作
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||GoodsCode||'\">' as 选择,GoodsCode as 物资品名代码,GoodsName as 物资品名,GoodsDesc as 说明,GoodsStyle as 规格型号,MeasureUnit as 计量单位,TotalNumber as 总数,InCount as 入库次数,AvailableNumber as 可用数量,OutNumber as 领用数量,outCount as 出库次数,to_char(FirstInDate,'yyyy-mm-dd') as 最早入库时间,to_char(LastOutDate,'yyyy-mm-dd') as 最后出库时间,Memo as 备注     from Com_StoreInfo where AvailableNumber >0 order by GoodsCode";
			//System.out.println(base_sql);
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getGoodsStoreInfoNumberAndName(int pageno, int perpage,String name)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql=null;
			if(name==null||name.equals("null"))
			{
				base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||GoodsCode||'\">' as 选择,GoodsCode as 物资品名代码,GoodsName as 物资品名,GoodsDesc as 说明,GoodsStyle as 规格型号,MeasureUnit as 计量单位,TotalNumber as 总数,InCount as 入库次数,AvailableNumber as 可用数量,OutNumber as 领用数量,outCount as 出库次数,to_char(FirstInDate,'yyyy-mm-dd') as 最早入库时间,to_char(LastOutDate,'yyyy-mm-dd') as 最后出库时间,Memo as 备注     from Com_StoreInfo where AvailableNumber >0 order by GoodsCode";
			}
			else
			{
				base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||GoodsCode||'\">' as 选择,GoodsCode as 物资品名代码,GoodsName as 物资品名,GoodsDesc as 说明,GoodsStyle as 规格型号,MeasureUnit as 计量单位,TotalNumber as 总数,InCount as 入库次数,AvailableNumber as 可用数量,OutNumber as 领用数量,outCount as 出库次数,to_char(FirstInDate,'yyyy-mm-dd') as 最早入库时间,to_char(LastOutDate,'yyyy-mm-dd') as 最后出库时间,Memo as 备注     from Com_StoreInfo where AvailableNumber >0 and GoodsName like '%"+name+"%' order by GoodsCode";
			}
			
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getNextGoodsStoreInfo(int pageno, int perpage,String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			
			//,'<a href=\"#\" onclick=F1(\"'||GoodsCode||','||GoodsName||','||AvailableNumber||'\") class=\"button4\">出库</a>' as 操作
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||GoodsCode||'\">' as 选择,GoodsCode as 物资品名代码,GoodsName as 物资品名,GoodsDesc as 说明,GoodsStyle as 规格型号,MeasureUnit as 计量单位,TotalNumber as 总数,InCount as 入库次数,AvailableNumber as 可用数量,OutNumber as 领用数量,outCount as 出库次数,to_char(FirstInDate,'yyyy-mm-dd') as 最早入库时间,to_char(LastOutDate,'yyyy-mm-dd') as 最后出库时间,Memo as 备注     from Com_StoreInfo where GoodsCode like'"+goodscode+"%' and TotalNumber!=0";
			//System.out.println(base_sql);
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllNextGoodsStoreInfo(String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			
			//,'<a href=\"#\" onclick=F1(\"'||GoodsCode||','||GoodsName||','||AvailableNumber||'\") class=\"button4\">出库</a>' as 操作
			String base_sql = "select * from Com_StoreInfo where GoodsCode like'"+goodscode+"%'";
			//System.out.println(base_sql);
			
			return db.runSelectQuery(base_sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  DataTable getStoreInfoSearch(int pageno, int perpage,String styleIn,String styleOut,String goodscode,String startdate,String enddate,String department)
	{//DataTable
		try
		{
			DBObject db = new DBObject();
			String[] goodscodes=goodscode.split(";");
			String temp="";
			String temping="like ";
			for(int i=0;i<goodscodes.length-1;i++)
			{
				
				temp+="'"+goodscodes[i]+"',";
				temping+="'"+goodscodes[i]+"%' or goodscode like ";
			}
			temp+="'"+goodscodes[goodscodes.length-1]+"'";
			temping+="'"+goodscodes[goodscodes.length-1]+"%'";
			//System.out.println(temping);
			String base_sql="";
			String base_sql1="";
			String base_sq2="";
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
			String time = dateFormat.format( now ); 
			if(temping.equals("like '%'"))
			{
				base_sql1 += "select Storeeventno 事件,goodsname 物资名称,auditorgcode 相关部门,auditorcode 操作人员,goodsnumber 数量,indate 日期 from com_instoreitem" +
				" where 1=1 and Storeeventno like '"+styleIn+"%' ";
				base_sq2+="union all select Storeeventno,goodsname,usingorgcode,handler,goodsnumber,outdate from com_outstoreitem " +
				"where 1=1 and Storeeventno like '"+styleOut+"%' ";
			}
			else
			{
				/*base_sql1 += "select Storeeventno 事件,goodsname 物资名称,auditorgcode 相关部门,auditorcode 操作人员,goodsnumber 数量,indate 日期 from com_instoreitem" +
				" where goodscode in ("+temp+") and Storeeventno like '"+styleIn+"%' ";
				base_sq2+="union all select Storeeventno,goodsname,usingorgcode,handler,goodsnumber,outdate from com_outstoreitem " +
				"where goodscode in ("+temp+") and Storeeventno like '"+styleOut+"%'  ";*/
				base_sql1 += "select Storeeventno 事件,goodsname 物资名称,auditorgcode 相关部门,auditorcode 操作人员,goodsnumber 数量,indate 日期 from com_instoreitem" +
				" where goodscode "+temping+" and Storeeventno like '"+styleIn+"%' ";
				base_sq2+="union all select Storeeventno,goodsname,usingorgcode,handler,goodsnumber,outdate from com_outstoreitem " +
				"where goodscode "+temping+" and Storeeventno like '"+styleOut+"%'  ";
			}
			if(startdate.equals(""))
			{
				base_sql1+="and indate >=to_date('1900-01-01','yyyy-MM-dd') ";
				base_sq2+="and outdate >=to_date('1900-01-01','yyyy-MM-dd') ";
			}
			else
			{
				base_sql1+="and indate >=to_date('"+startdate+"','yyyy-MM-dd') ";
				base_sq2+="and outdate >=to_date('"+startdate+"','yyyy-MM-dd') ";
			}
			if(enddate.equals(""))
			{
				base_sql1+="and indate <=to_date('"+time+"','yyyy-MM-dd') ";
				base_sq2+="and outdate <=to_date('"+time+"','yyyy-MM-dd') ";
			}
			else
			{
				base_sql1+="and indate <=to_date('"+enddate+"','yyyy-MM-dd') ";
				base_sq2+="and outdate <=to_date('"+enddate+"','yyyy-MM-dd') ";
			}
			if(department.equals(""))
			{
				base_sql1+="and 1=1  ";
				base_sq2+="and 1=1  ";
			}
			else
			{
				base_sql1+="and auditorgcode like '"+enddate+"%' ";
				base_sq2+="and usingorgcode like '"+enddate+"%' ";
			}
			
			base_sql=base_sql1+base_sq2;
			
			//,'<a href=\"#\" onclick=F1(\"'||GoodsCode||','||GoodsName||','||AvailableNumber||'\") class=\"button4\">出库</a>' as 操作
			/*base_sql = "select Storeeventno 事件,goodsname 物资名称,auditorgcode 相关部门,auditorcode 操作人员,goodsnumber 数量,indate 日期 from com_instoreitem" +
					" where goodscode in ("+temp+") and Storeeventno like '"+styleIn+"%' and indate between '"+startdate+"' and '"+enddate+"' and auditorgcode='"+department+"' "  +
					"union all select Storeeventno,goodsname,usingorgcode,handler,goodsnumber,outdate from com_outstoreitem " +
					"where goodscode in ("+temp+") and Storeeventno like '"+styleOut+"%' and outdate between '"+startdate+"' and '"+enddate+"' and usingorgcode='"+department+"' ";*/
			//System.out.println(base_sql);
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllGoodsStoreInfo(String goodscode)
	{
		try
		{
			DBObject db = new DBObject();
			//System.out.println(goodscode);
			DataTable dt = db.runSelectQuery("select * from Com_StoreInfo where GoodsCode  = '"+goodscode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllGoodsStoreInfo()
	{
		try
		{
			DBObject db = new DBObject();
			//System.out.println(goodscode);
			DataTable dt = db.runSelectQuery("select * from Com_StoreInfo where AvailableNumber>0 order by GoodsCode");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllNextGoodsStoreInfo(String styleIn,String styleOut,String goodscode,String startdate,String enddate,String department)
	{
		try
		{
			DBObject db = new DBObject();
			String[] goodscodes=goodscode.split(";");
			String temp="";
			String temping="like ";
			for(int i=0;i<goodscodes.length-1;i++)
			{
				
				temp+="'"+goodscodes[i]+"',";
				temping+="'"+goodscodes[i]+"%' or goodscode like ";
			}
			temp+="'"+goodscodes[goodscodes.length-1]+"'";
			temping+="'"+goodscodes[goodscodes.length-1]+"%'";
			//System.out.println(temping);
			String base_sql="";
			String base_sql1="";
			String base_sq2="";
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
			String time = dateFormat.format( now ); 
			if(temping.equals("like '%'"))
			{
				base_sql1 += "select Storeeventno 事件,goodsname 物资名称,auditorgcode 相关部门,auditorcode 操作人员,goodsnumber 数量,indate 日期 from com_instoreitem" +
				" where 1=1 and Storeeventno like '"+styleIn+"%' ";
				base_sq2+="union all select Storeeventno,goodsname,usingorgcode,handler,goodsnumber,outdate from com_outstoreitem " +
				"where 1=1 and Storeeventno like '"+styleOut+"%' ";
			}
			else
			{
				/*base_sql1 += "select Storeeventno 事件,goodsname 物资名称,auditorgcode 相关部门,auditorcode 操作人员,goodsnumber 数量,indate 日期 from com_instoreitem" +
				" where goodscode in ("+temp+") and Storeeventno like '"+styleIn+"%' ";
				base_sq2+="union all select Storeeventno,goodsname,usingorgcode,handler,goodsnumber,outdate from com_outstoreitem " +
				"where goodscode in ("+temp+") and Storeeventno like '"+styleOut+"%'  ";*/
				base_sql1 += "select Storeeventno 事件,goodsname 物资名称,auditorgcode 相关部门,auditorcode 操作人员,goodsnumber 数量,indate 日期 from com_instoreitem" +
				" where goodscode "+temping+" and Storeeventno like '"+styleIn+"%' ";
				base_sq2+="union all select Storeeventno,goodsname,usingorgcode,handler,goodsnumber,outdate from com_outstoreitem " +
				"where goodscode "+temping+" and Storeeventno like '"+styleOut+"%'  ";
			}
			if(startdate.equals(""))
			{
				base_sql1+="and indate >=to_date('1900-01-01','yyyy-MM-dd') ";
				base_sq2+="and outdate >=to_date('1900-01-01','yyyy-MM-dd') ";
			}
			else
			{
				base_sql1+="and indate >=to_date('"+startdate+"','yyyy-MM-dd') ";
				base_sq2+="and outdate >=to_date('"+startdate+"','yyyy-MM-dd') ";
			}
			if(enddate.equals(""))
			{
				base_sql1+="and indate <=to_date('"+time+"','yyyy-MM-dd') ";
				base_sq2+="and outdate <=to_date('"+time+"','yyyy-MM-dd') ";
			}
			else
			{
				base_sql1+="and indate <=to_date('"+enddate+"','yyyy-MM-dd') ";
				base_sq2+="and outdate <=to_date('"+enddate+"','yyyy-MM-dd') ";
			}
			if(department.equals(""))
			{
				base_sql1+="and 1=1  ";
				base_sq2+="and 1=1  ";
			}
			else
			{
				base_sql1+="and auditorgcode like '"+enddate+"%' ";
				base_sq2+="and usingorgcode like '"+enddate+"%' ";
			}
			
			base_sql=base_sql1+base_sq2;
			//System.out.println("base_sql:   "+base_sql);
			//,'<a href=\"#\" onclick=F1(\"'||GoodsCode||','||GoodsName||','||AvailableNumber||'\") class=\"button4\">出库</a>' as 操作
			/*base_sql = "select Storeeventno 事件,goodsname 物资名称,auditorgcode 相关部门,auditorcode 操作人员,goodsnumber 数量,indate 日期 from com_instoreitem" +
					" where goodscode in ("+temp+") and Storeeventno like '"+styleIn+"%' and indate between '"+startdate+"' and '"+enddate+"' and auditorgcode='"+department+"' "  +
					"union all select Storeeventno,goodsname,usingorgcode,handler,goodsnumber,outdate from com_outstoreitem " +
					"where goodscode in ("+temp+") and Storeeventno like '"+styleOut+"%' and outdate between '"+startdate+"' and '"+enddate+"' and usingorgcode='"+department+"' ";*/
			//System.out.println(base_sql);
			//String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(base_sql);}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public boolean Delete(String BmString)
	{
		try
		{
			DBObject db = new DBObject();
			String sqltemp="delete from Com_instoreitem where INNO='"+BmString+"'";
			if(db.run(sqltemp))
				return true;
			else
				return false;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
