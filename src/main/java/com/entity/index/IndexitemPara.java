package com.entity.index;


import com.db.DBObject;
import com.db.Parameter;

public class IndexitemPara {

	private String indexcode;
	private String paraid;
	private String paracode;
	private String paravaluemode;
	private String memo;
	private String referparacode;
	private String paraperiod;
	public IndexitemPara(){
		 
	}
	
	public static boolean insert(IndexitemPara indexitemPara){
		try {
			String sql = "insert into tbm_indexitempara (indexcode,paraid,paracode,paravaluemode,memo,referparacode,paraperiod) values (?,?,?,?,?,?,?)";
			DBObject db=new DBObject();
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{       new Parameter.String(indexitemPara.indexcode), 
					new Parameter.String(indexitemPara.paraid), 
					new Parameter.String(indexitemPara.paracode),
					new Parameter.String(indexitemPara.paravaluemode),
					new Parameter.String(indexitemPara.memo),
					new Parameter.String(indexitemPara.referparacode),
					new Parameter.String(indexitemPara.paraperiod)};
			return db.run(sql, pp);
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean update(IndexitemPara indexitemPara){
		try {
			String sql = "update tbm_indexitempara set paracode=?,paravaluemode=?,memo=?,referparacode=?,paraperiod=? where indexcode=? and paraid=?";
			DBObject db=new DBObject();
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{       
					new Parameter.String(indexitemPara.paracode),
					new Parameter.String(indexitemPara.paravaluemode),
					new Parameter.String(indexitemPara.memo),
					new Parameter.String(indexitemPara.referparacode),
					new Parameter.String(indexitemPara.paraperiod),
					new Parameter.String(indexitemPara.indexcode), 
					new Parameter.String(indexitemPara.paraid)};
			return db.run(sql, pp);
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 根据指标项删除关联参数
	 * @param indexcode
	 * @return
	 */
	public static boolean delete(String indexcode){
		try {
			String sql="delete from tbm_indexitempara where indexcode='"+indexcode+"'";
			DBObject db=new DBObject();
			return db.run(sql);
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean deletesub(String indexcode){
		try {
			String sql="delete from tbm_indexitempara where indexcode like '"+indexcode+"%'";
			DBObject db=new DBObject();
			return db.run(sql);
		} catch (Exception e) {
			return false;
		}
	}
	public String getIndexcode() {
		return indexcode;
	}
	public void setIndexcode(String indexcode) {
		this.indexcode = indexcode;
	}
	
	public String getParaid() {
		return paraid;
	}

	public void setParaid(String paraid) {
		this.paraid = paraid;
	}

	public String getParacode() {
		return paracode;
	}
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	public String getParavaluemode() {
		return paravaluemode;
	}
	public void setParavaluemode(String paravaluemode) {
		this.paravaluemode = paravaluemode;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getReferparacode() {
		return referparacode;
	}
	public void setReferparacode(String referparacode) {
		this.referparacode = referparacode;
	}
	public String getParaperiod() {
		return paraperiod;
	}
	public void setParaperiod(String paraperiod) {
		this.paraperiod = paraperiod;
	}
	
}
