package com.entity.index;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class ReferObjectIndex {

	private String recno;
	private String objectcode;
	private String objecttype;
	private String indexcode;
	private String paracode;
	private String refobjectcode;
	private String refobjecttype;
	private String refindexcode;
	private String refmode;
	private String memo;
	public ReferObjectIndex(){
		
	}
	public ReferObjectIndex(String rec_no){
		try {
			String sql="select * from tbm_referobjectindex where recno=?";
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.String(rec_no)	
			};
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql, pp);
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow r=dt.get(0);
				recno=rec_no;
				objectcode=r.getString("objectcode");
				objecttype=r.getString("objecttype");
				indexcode=r.getString("indexcode");
				paracode=r.getString("paracode");
				refobjectcode=r.getString("refobjectcode");
				refobjecttype=r.getString("refobjecttype");
				refindexcode=r.getString("refindexcode");
				refmode=r.getString("refmode");
				memo=r.getString("memo");
			}
		} catch (Exception e) {
			
		}
	}
	public DataTable getReferobjectlist(String indexcode,String paracode,int pageno,int perpage){
		try {
			DBObject db=new DBObject();
			String base_sql = "select  'ѡ��' as ѡ��,recno,objectcode as ʹ����,objecttype as ��������,indexcode as ָ�����,paracode as ��������,refobjectcode as ��������,refobjecttype as �����������,refindexcode as ������ָ�����,refmode as ����ģʽ,memo,'����' as ���� "
					+ "from (select * from tbm_referobjectindex order by objectcode) where indexcode='"+indexcode+"' and paracode='"+paracode+"'";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
		    return db.runSelectQuery(sql_run);
			
		} catch (Exception e) {
			return null;
		}
	}
	public DataTable getAllReferobjectlist(String indexcode,String paracode){
		
		try {
			DBObject db=new DBObject();
			String base_sql = "select * from tbm_referobjectindex where indexcode='"+indexcode+"' and paracode='"+paracode+"' order by objectcode ";
			
			return db.runSelectQuery(base_sql);
			
		} catch (Exception e) {
			return null;
		}
	}
	public DataTable getReferobjectdeflist(String indexcode,String paracode,String refmode,int pageno,int perpage){
		try {
			DBObject db=new DBObject();
			String base_sql = "select a.objectcode,a.objectcode as ʹ����,a.objecttype as ��������,'"+indexcode+"' as ָ�����,'"+indexcode+"' as indexcode,'"+paracode+"' as paracode,'"+paracode+"' as ��������,'' as ��������,'' as �����������,'' as ������ָ�����,'"+refmode+"' as ����ģʽ,(select count(*) from tbm_referobjectindex  where indexcode='"+indexcode+"' and paracode='"+paracode+"' and objectcode=a.objectcode) as ����״̬,'����' as ����  from (select * from tbm_indexarchuser where  indexarchcode=substr('"+indexcode+"',1,3)  ) a";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
		    return db.runSelectQuery(sql_run);
			
		} catch (Exception e) {
			return null;
		}
	}
	public DataTable getAllReferobjectdeflist(String indexcode,String paracode){
		
		try {
			DBObject db=new DBObject();
			String base_sql = "select objectcode,objecttype ,'"+indexcode+"' as indexcode,'"+paracode+"' as paracode,'����ģʽ' as refmode,(select count(*) from tbm_referobjectindex where indexcode='"+indexcode+"' and paracode='"+paracode+"' ) as ����״̬  from (select * from tbm_indexarchuser where  indexarchcode=substr('"+indexcode+"',1,3) )";
			
			return db.runSelectQuery(base_sql);
			
		} catch (Exception e) {
			return null;
		}
	}
	public String getRecno() {
		return recno;
	}
	public void setRecno(String recno) {
		this.recno = recno;
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
	public String getIndexcode() {
		return indexcode;
	}
	public void setIndexcode(String indexcode) {
		this.indexcode = indexcode;
	}
	
	public String getParacode() {
		return paracode;
	}
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	public String getRefobjectcode() {
		return refobjectcode;
	}
	public void setRefobjectcode(String refobjectcode) {
		this.refobjectcode = refobjectcode;
	}
	public String getRefobjecttype() {
		return refobjecttype;
	}
	public void setRefobjecttype(String refobjecttype) {
		this.refobjecttype = refobjecttype;
	}
	public String getRefindexcode() {
		return refindexcode;
	}
	public void setRefindexcode(String refindexcode) {
		this.refindexcode = refindexcode;
	}
	public String getRefmode() {
		return refmode;
	}
	public void setRefmode(String refmode) {
		this.refmode = refmode;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
