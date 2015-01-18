package com.entity.index;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class InputTableIndex {

	private String TableNo;
	private String indexCode;
	private String indexname;
	private int IndexOrder;
	private int Enabled;
	private String Memo;
	
	public InputTableIndex(){
		
	}
	public InputTableIndex(String tableno,String indexcode){
		try {
			String sql="select * from tbm_inputtableindex where tableno='"+tableno+"'  and  indexcode='"+indexcode+"'";
			DBObject dbObject=new DBObject();
			DataTable dTable=dbObject.runSelectQuery(sql);
			if(dTable!=null&&dTable.getRowsCount()==1){
				DataRow r=dTable.get(0);
				this.TableNo=tableno;
				this.indexCode=indexcode;
				this.indexname=r.getString("indexname");
				this.IndexOrder=Integer.parseInt(r.getString("indexorder"));
				this.Enabled=Integer.parseInt(r.getString("enabled"));
				this.Memo=r.getString("memo");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public DataTable getInputtableIndex(String tableno,int pageno,int perpage){
		try {
			
			DBObject db=new DBObject();

					String base_sql = "select  '选择' as 选择 ,tableno,indexcode,indexname as 编码名称,indexorder as 指标顺序, enabled,memo as 备注, '操作' as 操作    from (select * from tbm_inputtableindex where tableno='"+tableno+"') order by  indexorder";
					String sql_run = Format.getFySql(base_sql, pageno, perpage);
				
					return db.runSelectQuery(sql_run);
				} catch (Exception e) {
					// TODO: handle exception
					return null;
				}
	}
	public DataTable getAllInputtableIndex(String tableno){
		try {
			DBObject dbObject=new DBObject();
			String sql="select * from tbm_inputtableindex  where tableno='"+tableno+"'  order by  indexorder";
			return dbObject.runSelectQuery(sql);
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	
	
	public String getTableNo() {
		return TableNo;
	}
	public void setTableNo(String tableNo) {
		TableNo = tableNo;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	
	public String getIndexname() {
		return indexname;
	}
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
	public int getIndexOrder() {
		return IndexOrder;
	}
	public void setIndexOrder(int indexOrder) {
		IndexOrder = indexOrder;
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
