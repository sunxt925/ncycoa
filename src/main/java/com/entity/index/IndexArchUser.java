package com.entity.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class IndexArchUser {
	
	private String IndexArchCode;//	指标分类编码
	private String UniIndexCode;//	指标统一编码
	private String objectcode;
	private String objecttype;
	private String stafforg;
	private int multiindexorder;
	private Date startDate;
	private Date endDate;
	private String memo;//备注	
	
	public IndexArchUser(){
		
	}
	public IndexArchUser(String indexarchcode,String objectcode){
		try {
			DBObject db=new DBObject();
			String sql="select * from tbm_indexarchuser where indexarchcode='"+indexarchcode+"' and objectcode='"+objectcode+"'";
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow r=dt.get(0);
				this.IndexArchCode=indexarchcode;
				this.UniIndexCode=r.getString("uniindexcode");
				this.objectcode=objectcode;
				this.objecttype=r.getString("objecttype");
				this.stafforg=r.getString("stafforg");
				this.multiindexorder=Integer.parseInt(r.getString("multiindexorder"));
				this.startDate=Format.strToDate(r.getString("startdate"));
				this.endDate=Format.strToDate(r.getString("enddate"));
				this.memo=r.getString("memo");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List<String> getIndexcodeByObjectcode(String objectcode){
		try {
			String indexcode="";
			List<String> indexcodes=new ArrayList<String>();
			DBObject db=new DBObject();
			String sql="select indexarchcode from tbm_indexarchuser where objectcode='"+objectcode+"'";
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()>=0){
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow r=dt.get(i);
					indexcode=r.getString("indexarchcode");
					indexcodes.add(indexcode);
				}
				
				
			}
			return indexcodes;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public DataTable getIndexarchuserlist(String indexarchcode,int pageno,int perpage){
		try {
			DBObject db=new DBObject();
			String base_sql="";
			if(indexarchcode.substring(0, 1).equals("S")){
				base_sql="select  '选择' as 选择 ,indexarchcode,indexarchcode as 所属的指标类别,stafforg as 所属部门,objectcode,objectcode as 对象编码,objecttype as 对象类型,multiindexorder as 指标顺序,memo as 备注, '操作' as 操作    from (select * from tbm_indexarchuser where indexarchcode='"+indexarchcode+"' order by objectcode)";
				
			}else{
				base_sql="select  '选择' as 选择 ,indexarchcode,indexarchcode as 所属的指标类别,objectcode,objectcode as 对象编码,objecttype as 对象类型,multiindexorder as 指标顺序,memo as 备注, '操作' as 操作    from (select * from tbm_indexarchuser where indexarchcode='"+indexarchcode+"' order by objectcode)";
				
			}
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			
			return db.runSelectQuery(sql_run);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllIndexarchuserlist(String indexarchcode){
		try {
			DBObject db=new DBObject();
			String sql="select * from tbm_indexarchuser where indexarchcode='"+indexarchcode+"'";
			return db.runSelectQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getIndexarchcodeByobject(String objectcode,String multiindexorder){
		try {
			DBObject db=new DBObject();
			String sql="select indexarchcode from tbm_indexarchuser where objectcode='"+objectcode+"' and multiindexorder='"+multiindexorder+"'";
			DataTable dt=db.runSelectQuery(sql);
			String indexarchcodeString="";
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow r=dt.get(0);
				indexarchcodeString=r.getString("indexarchcode");
			}
			return indexarchcodeString;
		} catch (Exception e) {
			return "";
		}
	}
	public List<IndexArchUser> getUser(String userclass){
		try {
			DBObject db=new DBObject();
			String sql="select * from tbm_indexarchuser where objecttype=?";
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				    new Parameter.String(userclass)	
			};
			DataTable dt=db.runSelectQuery(sql, pp);
			List<IndexArchUser> indexArchUsers=new ArrayList<IndexArchUser>();
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow row=null;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					IndexArchUser indexArchUser=new IndexArchUser();
					indexArchUser.setIndexArchCode(row.getString("indexarchcode"));
					indexArchUser.setUniIndexCode(row.getString("uniindexcode"));
					indexArchUser.setObjectcode(row.getString("objectcode"));
					indexArchUser.setObjecttype(userclass);
					indexArchUser.setStafforg(row.getString("stafforg"));
					indexArchUser.setMultiindexorder(Integer.parseInt(row.getString("multiindexorder")));
					indexArchUser.setStartDate(Format.strToDate(row.getString("startdate")));
					indexArchUser.setEndDate(Format.strToDate(row.getString("enddate")));
					indexArchUser.setMemo(row.getString("memo"));
					indexArchUsers.add(indexArchUser);
				}
			}
			return indexArchUsers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getIndexArchCode() {
		return IndexArchCode;
	}
	public void setIndexArchCode(String indexArchCode) {
		IndexArchCode = indexArchCode;
	}
	public String getUniIndexCode() {
		return UniIndexCode;
	}
	public void setUniIndexCode(String uniIndexCode) {
		UniIndexCode = uniIndexCode;
	}
	
	public String getObjectcode() {
		return objectcode;
	}
	public void setObjectcode(String objectcode) {
		this.objectcode = objectcode;
	}
	public String getObjecttype() {
		return objecttype;
	}
	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
	
	public String getStafforg() {
		return stafforg;
	}
	public void setStafforg(String stafforg) {
		this.stafforg = stafforg;
	}
	public int getMultiindexorder() {
		return multiindexorder;
	}
	public void setMultiindexorder(int multiindexorder) {
		this.multiindexorder = multiindexorder;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
