package com.entity.index;

import java.util.Date;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class IndexInput {
private String TableNo;
private String TableName;
private String IndexArchcode;
private int IndexCount;
private String Creator;
private Date Createdate;
private int Enabled;
private String Memo;

public IndexInput(){
	
}
public IndexInput(String tableno){
	try {
		String sql="select * from tbm_inputtable where tableno='"+tableno+"'";
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql);
		if(dt!=null&&dt.getRowsCount()==1){
			
			DataRow  r=dt.get(0);
			this.TableName=r.getString("tablename");
			this.IndexArchcode=r.getString("indexarchcode");
			this.IndexCount=Integer.parseInt(r.getString("indexcount"));
			this.Creator=r.getString("creator");
			this.Createdate=Format.strToDate(r.getString("createdate"));
			this.Enabled=Integer.parseInt(r.getString("enabled"));
			this.Memo=r.getString("memo");
			
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
}

public DataTable getindexinputtableList(int pageno,int perpage){
	try {
		
DBObject db=new DBObject();

		String base_sql = "select  '选择' as 选择 ,tableno,tablename, indexarchcode as 所属的指标类别, indexcount as 指标数量, creator as 创建人,createdate as 创建日期, enabled,memo as 备注, '操作' as 操作    from (select * from tbm_inputtable )";
		String sql_run = Format.getFySql(base_sql, pageno, perpage);
	
		return db.runSelectQuery(sql_run);
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
}
public DataTable getAllindexinputtableList(){
	try {
		String sql="select * from tbm_inputtable";
		DBObject dbObject=new DBObject();
		return dbObject.runSelectQuery(sql);
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
}
public String getTableNo() {
	return TableNo;
}
public void setTableNo(String tableNo) {
	TableNo = tableNo;
}
public String getTableName() {
	return TableName;
}
public void setTableName(String tableName) {
	TableName = tableName;
}
public String getIndexArchcode() {
	return IndexArchcode;
}
public void setIndexArchcode(String indexArchcode) {
	IndexArchcode = indexArchcode;
}
public int getIndexCount() {
	return IndexCount;
}
public void setIndexCount(int indexCount) {
	IndexCount = indexCount;
}
public String getCreator() {
	return Creator;
}
public void setCreator(String creator) {
	Creator = creator;
}
public Date getCreatedate() {
	return Createdate;
}
public void setCreatedate(Date createdate) {
	Createdate = createdate;
}
public int getEnabled() {
	return Enabled;
}
public void setEnabled(int enabled) {
	Enabled = enabled;
}
public String getMemo() {
	return Memo;
}
public void setMemo(String memo) {
	Memo = memo;
}


}
