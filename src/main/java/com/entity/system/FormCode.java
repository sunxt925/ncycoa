package com.entity.system;


import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class FormCode {
	private String prmtyp;
	private String prmcod;
	private String depart;//   varchar2(3) not null,
	private String  prmcdd;//   varchar2(60),
	private String  ssmfld;//   varchar2(1),
	private String bstcfg;//   varchar2(1),
	private String almnfg;//   varchar2(1),
	private String fldtyp;//   varchar2(1),
	private int datlen;//   number,
	private int prmdec;//   number,
	private String  standby1;// varchar2(10),
	private String  standby2;// varchar2(10),
	private String note;//     varchar2(60),
	private String  lchsdt;//   varchar2(8),
	private String  lchstm;//   varchar2(8),
	private String  lmnemp;//   varchar2(20) 
	
	
	public FormCode(){
		
	}
	public FormCode(String prmcod){
		try {
			DBObject db=new DBObject();
			String sql="select * from codctctp where prmcod='"+prmcod+"'";
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow r=dt.get(0);
				this.prmtyp=r.getString("prmtyp");
				this.prmcod=r.getString("prmcod");
				this.depart=r.getString("depart");//   varchar2(3) not null,
				this. prmcdd=r.getString("prmcdd");//   varchar2(60),
				this. ssmfld=r.getString("ssmfld");//   varchar2(1),
				this.bstcfg=r.getString("bstcfg");//   varchar2(1),
				this.almnfg=r.getString("almnfg");//   varchar2(1),
				this.fldtyp=r.getString("fldtyp");//   varchar2(1),
				this.datlen=Integer.parseInt(r.getString("datlen"));//   number,
				this.prmdec=Integer.parseInt(r.getString("prmdec"));//   number,
				this. standby1=r.getString("standby1");// varchar2(10),
				this. standby2=r.getString("standby2");// varchar2(10),
				this.note=r.getString("note");//     varchar2(60),
				this. lchsdt=r.getString("lchsdt");//   varchar2(8),
				this. lchstm=r.getString("lchstm");//   varchar2(8),
				this. lmnemp=r.getString("lmnemp");//   varchar2(20)
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public DataTable getFormcodelist(int pageno,int perpage){
		try {
			
			DBObject db=new DBObject();

					String base_sql = "select  '选择' as 选择 ,prmtyp,prmcod,depart,ssmfld, bstcfg,almnfg,fldtyp,datlen,prmdec,standby1,standby2,note,lchsdt,lchstm,lmnemp, '操作' as 操作    from (select * from codctctp )";
					String sql_run = Format.getFySql(base_sql, pageno, perpage);
				
					return db.runSelectQuery(sql_run);
				} catch (Exception e) {
					// TODO: handle exception
					return null;
				}
	}
	public DataTable getAllFormcodelist(){
		try {
			String sql="select * from codctctp";
			DBObject dbObject=new DBObject();
			return dbObject.runSelectQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public String getPrmtyp() {
		return prmtyp;
	}
	public void setPrmtyp(String prmtyp) {
		this.prmtyp = prmtyp;
	}
	public String getPrmcod() {
		return prmcod;
	}
	public void setPrmcod(String prmcod) {
		this.prmcod = prmcod;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getPrmcdd() {
		return prmcdd;
	}
	public void setPrmcdd(String prmcdd) {
		this.prmcdd = prmcdd;
	}
	public String getSsmfld() {
		return ssmfld;
	}
	public void setSsmfld(String ssmfld) {
		this.ssmfld = ssmfld;
	}
	public String getBstcfg() {
		return bstcfg;
	}
	public void setBstcfg(String bstcfg) {
		this.bstcfg = bstcfg;
	}
	public String getAlmnfg() {
		return almnfg;
	}
	public void setAlmnfg(String almnfg) {
		this.almnfg = almnfg;
	}
	public String getFldtyp() {
		return fldtyp;
	}
	public void setFldtyp(String fldtyp) {
		this.fldtyp = fldtyp;
	}
	public int getDatlen() {
		return datlen;
	}
	public void setDatlen(int datlen) {
		this.datlen = datlen;
	}
	public int getPrmdec() {
		return prmdec;
	}
	public void setPrmdec(int prmdec) {
		this.prmdec = prmdec;
	}
	public String getStandby1() {
		return standby1;
	}
	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	public String getStandby2() {
		return standby2;
	}
	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLchsdt() {
		return lchsdt;
	}
	public void setLchsdt(String lchsdt) {
		this.lchsdt = lchsdt;
	}
	public String getLchstm() {
		return lchstm;
	}
	public void setLchstm(String lchstm) {
		this.lchstm = lchstm;
	}
	public String getLmnemp() {
		return lmnemp;
	}
	public void setLmnemp(String lmnemp) {
		this.lmnemp = lmnemp;
	}
	

}
