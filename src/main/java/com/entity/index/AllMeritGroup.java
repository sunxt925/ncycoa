package com.entity.index;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class AllMeritGroup {
	private String GroupNo;
	private String GroupName;
	private String allmeritfunc;
	private String memo;
	
	public AllMeritGroup(){
		
	}
	public AllMeritGroup(String groupno){
		try {
			DBObject dbObject=new DBObject();
			String sql="select * from tbm_allmeritgroup where groupno='"+groupno+"'";
			DataTable dTable=dbObject.runSelectQuery(sql);
			if(dTable!=null&&dTable.getRowsCount()==1){
				DataRow r=dTable.get(0);
				this.GroupNo=groupno;
				this.GroupName=r.getString("groupname");
				this.allmeritfunc=r.getString("allmeritfunc");
				this.memo=r.getString("memo");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public DataTable getmeritgrouplist(int pageno,int perpage){
		try {
			
			DBObject db=new DBObject();

					String base_sql = "select  '选择' as 选择 ,groupno,groupname as 组名, allmeritfunc as 绩效构成比重,memo as 说明, '操作' as 操作    from (select * from tbm_allmeritgroup  order by groupno)";
					String sql_run = Format.getFySql(base_sql, pageno, perpage);
				
					return db.runSelectQuery(sql_run);
				} catch (Exception e) {
					// TODO: handle exception
					return null;
				}
	}
	public DataTable getallMeritgrouplist(){
		try {
			String sql="select * from tbm_allmeritgroup";
			DBObject dbObject=new DBObject();
			return dbObject.runSelectQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public String getGroupNo() {
		return GroupNo;
	}
	public void setGroupNo(String groupNo) {
		GroupNo = groupNo;
	}
	public String getGroupName() {
		return GroupName;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public String getAllmeritfunc() {
		return allmeritfunc;
	}
	public void setAllmeritfunc(String allmeritfunc) {
		this.allmeritfunc = allmeritfunc;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

}
