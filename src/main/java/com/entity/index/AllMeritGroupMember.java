package com.entity.index;

import java.util.LinkedHashMap;
import java.util.Map;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class AllMeritGroupMember {
	private String recno;
private String GroupNo;
private String objectType;
private String OrgCode;
private String PositionCode;
private String StaffCode;
private String Memo;


public AllMeritGroupMember(){
	
}
public AllMeritGroupMember(String groupno,String staffcode){
	try {
		DBObject dbObject=new DBObject();
		String sql="select * from tbm_allmeritgroupmember where groupno='"+groupno+"' and staffcode='"+staffcode+"'";
		DataTable dtDataTable=dbObject.runSelectQuery(sql);
		if(dtDataTable!=null&&dtDataTable.getRowsCount()==1){
			DataRow r=dtDataTable.get(0);
			this.GroupNo=groupno;
			this.objectType=r.getString("objecttype");
			this.OrgCode=r.getString("orgcode");
			this.PositionCode=r.getString("positioncode");
			this.StaffCode=staffcode;
			this.Memo=r.getString("memo");
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
}
public DataTable getMeritgroupMemberlist(String groupno,int pageno,int perpage){
	try {
		
		DBObject db=new DBObject();

				String base_sql = "select  '选择' as 选择 ,groupno,objecttype as 使用者类别,staffcode as 员工编码,orgcode as 机构编码,positioncode as 岗位编码,staffcode,memo as 备注, '操作' as 操作    from (select * from tbm_allmeritgroupmember where groupno='"+groupno+"' )";
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
			
				return db.runSelectQuery(sql_run);
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
}
public DataTable getAllMeritgroupMemberlist(String groupno){
	try {
		String sql="select * from tbm_allmeritgroupmember where groupno='"+groupno+"'";
		DBObject dbObject=new DBObject();
		return dbObject.runSelectQuery(sql);
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
}
public static AllMeritGroup getAllmeritMode(String objectcode){
	try{
		String sql="select groupno from tbm_allmeritgroupmember where staffcode='"+objectcode+"'";
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql);
		AllMeritGroup allMeritGroup=null;
		if(dt!=null&&dt.getRowsCount()==1){
			DataRow row=dt.get(0);
			String groupno=row.getString("groupno");
			allMeritGroup=new AllMeritGroup(groupno);
		}
		return allMeritGroup;
	}catch (Exception e){
		return null;
	}
	
}
public static Map<String,AllMeritGroupMember> getAllmember(){
	try {
		DBObject db=new DBObject();
		String sql="select * from tbm_allmeritgroupmember order by orgcode,positioncode,staffcode";
		DataTable dt=db.runSelectQuery(sql);
		//Map<String,AllMeritGroupMember> allMeritGroupMembers=new HashMap<String, AllMeritGroupMember>();
		Map<String,AllMeritGroupMember> allMeritGroupMembers=new LinkedHashMap<String, AllMeritGroupMember>();
		if(dt!=null&&dt.getRowsCount()>=1){
			for(int i=0;i<dt.getRowsCount();i++){
				DataRow row=dt.get(i);
				AllMeritGroupMember allMeritGroupMember=new AllMeritGroupMember();
				allMeritGroupMember.setGroupNo(row.getString("groupno"));
				allMeritGroupMember.setObjectType(row.getString("objecttype"));
				allMeritGroupMember.setOrgCode(row.getString("orgcode"));
				allMeritGroupMember.setPositionCode(row.getString("positioncode"));
				allMeritGroupMember.setStaffCode(row.getString("staffcode"));
				allMeritGroupMember.setMemo("");
				allMeritGroupMembers.put(row.getString("staffcode"), allMeritGroupMember);
			}
			
		}
		return allMeritGroupMembers;
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
public String getGroupNo() {
	return GroupNo;
}
public void setGroupNo(String groupNo) {
	GroupNo = groupNo;
}
public String getObjectType() {
	return objectType;
}
public void setObjectType(String objectType) {
	this.objectType = objectType;
}
public String getOrgCode() {
	return OrgCode;
}
public void setOrgCode(String orgCode) {
	OrgCode = orgCode;
}
public String getPositionCode() {
	return PositionCode;
}
public void setPositionCode(String positionCode) {
	PositionCode = positionCode;
}
public String getStaffCode() {
	return StaffCode;
}
public void setStaffCode(String staffCode) {
	StaffCode = staffCode;
}
public String getMemo() {
	return Memo;
}
public void setMemo(String memo) {
	Memo = memo;
}




}
