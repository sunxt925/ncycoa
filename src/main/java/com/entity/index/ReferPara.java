package com.entity.index;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class ReferPara {

	private String paracode;
	private String paraname;
	private String paraperiod;
	private String memo;
	private String getparavaluefunc;
	private Integer defaultvalue;
	private Integer usingflag;
	private int paraorder;
	
	public ReferPara(){
		
	}
	public ReferPara(String para_code){
		try {
			String sql="select * from tbm_referpara where paracode=?";
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.String(para_code)	
			};
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql, pp);
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow r=dt.get(0);
				paracode=para_code;
				paraname=r.getString("paraname");
				paraperiod=r.getString("paraperiod");
				memo=r.getString("memo");
				getparavaluefunc=r.getString("getparavaluefunc");
				if(r.getString("defaultvalue") == null || "".equals(r.getString("defaultvalue").trim())) {
					defaultvalue = null;
				} else {
					defaultvalue=Integer.parseInt(r.getString("defaultvalue"));
				}
				if(r.getString("usingflag") == null || "".equals(r.getString("usingflag").trim())) {
					usingflag = null;
				} else {
					usingflag=Integer.parseInt(r.getString("usingflag"));
				}
				paraorder=Integer.parseInt(r.getString("paraorder"));
			}
		} catch (Exception e) {
			
		}
	}
	public DataTable getReferlist(int pageno,int perpage){
		try {
			DBObject db=new DBObject();
			String base_sql = "select  '选择' as 选择,paracode as 参数代码,paraname as 参数名称,paraperiod as 参数采集周期,defaultvalue  as 缺省值,getparavaluefunc as 采集函数,usingflag,paraorder as 参数排序,memo,'操作' as 操作 "
					+ "from (select * from tbm_referpara order by paraorder)";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
		    return db.runSelectQuery(sql_run);
			
		} catch (Exception e) {
			return null;
		}
	}
	public DataTable getAllReferlist(){
		
		try {
			DBObject db=new DBObject();
			String base_sql = "select * from tbm_referpara order by paraorder";
			
			return db.runSelectQuery(base_sql);
			
		} catch (Exception e) {
			return null;
		}
	}
	public String getparajson(){
		try {
			DBObject db=new DBObject();
			String sql="select * from tbm_referpara";
			DataTable dt=db.runSelectQuery(sql);
			StringBuilder sBuilder=new StringBuilder();
			sBuilder.append("[");
			if(dt!=null&&dt.getRowsCount()>=0){
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow r=dt.get(i);
					sBuilder.append("{");
					sBuilder.append("\"paracode\":").append("\""+r.getString("paracode")+"\"").append(",");
					sBuilder.append("\"paraname\":").append("\""+r.getString("paraname")+"\"");
					sBuilder.append("},");
				}
				sBuilder.delete(sBuilder.length()-1, sBuilder.length());
				
			}
			sBuilder.append("]");
			return sBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}
	public String getParacode() {
		return paracode;
	}
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	public String getParaname() {
		return paraname;
	}
	public void setParaname(String paraname) {
		this.paraname = paraname;
	}
	public String getParaperiod() {
		return paraperiod;
	}
	public void setParaperiod(String paraperiod) {
		this.paraperiod = paraperiod;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getGetparavaluefunc() {
		return getparavaluefunc;
	}
	public void setGetparavaluefunc(String getparavaluefunc) {
		this.getparavaluefunc = getparavaluefunc;
	}
	public Integer getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(Integer defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public Integer getUsingflag() {
		return usingflag;
	}
	public void setUsingflag(Integer usingflag) {
		this.usingflag = usingflag;
	}
	public int getParaorder() {
		return paraorder;
	}
	public void setParaorder(int paraorder) {
		this.paraorder = paraorder;
	}
	
}
