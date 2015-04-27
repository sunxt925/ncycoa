package com.entity.system;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class FormCodeItem {
private String prmtyp;
private String prmcod;
private String depart;
private String prmcon;
private String prmcnd;


public FormCodeItem(){
	
}
public FormCodeItem(String prmcod,String prmcon){
	try {
		DBObject db=new DBObject();
		String sql="select * from codctdep where prmcod='"+prmcod+"'  and  prmcon='"+prmcon+"'";
		DataTable dt=db.runSelectQuery(sql);
		if(dt!=null&&dt.getRowsCount()==1){
			DataRow r=dt.get(0);
			this.prmtyp=r.getString("prmtyp");
			this.prmcod=prmcod;
			this.prmcon=prmcon;
			this.depart=r.getString("depart");
			this.prmcnd=r.getString("prmcnd");
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
}



public DataTable getFormcodeitemlist(String prmcod,int pageno,int perpage){
	try {
		
		DBObject db=new DBObject();

				String base_sql = "select  '选择' as 选择 ,prmcod,prmtyp,prmcon,depart,prmcnd,'操作' as 操作    from (select * from codctdep where prmcod='"+prmcod+"' )";
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
			
				return db.runSelectQuery(sql_run);
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
}
public DataTable getAllFormcodeitemlist(String prmcod){
	try {
		DBObject db=new DBObject();

		String sql = "select * from codctdep where prmcod='"+prmcod+"'";
		return db.runSelectQuery(sql);
	} catch (Exception e) {
		return null;
		// TODO: handle exception
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
public String getPrmcon() {
	return prmcon;
}
public void setPrmcon(String prmcon) {
	this.prmcon = prmcon;
}
public String getPrmcnd() {
	return prmcnd;
}
public void setPrmcnd(String prmcnd) {
	this.prmcnd = prmcnd;
} 

}
