package com.entity.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.common.CodeDictionary;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.system.Staff;

public class MeritQueryHelper {

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public static List<Staff> queryWithoutIndexRelation(){
		List<Staff> staffsList=new ArrayList<Staff>();
		try {
			DBObject db=new DBObject();
			String sql2 = "select distinct objectcode from TBM_INDEXARCHUSER where objectcode like '115%'";
			DataTable dt_arch=db.runSelectQuery(sql2);
			List<String> archusers = new ArrayList<String>();
			if(dt_arch!=null&&dt_arch.getRowsCount()>=1){
				for(int i=0;i<dt_arch.getRowsCount();i++){
					DataRow row = dt_arch.get(i);
					archusers.add(row.getString("objectcode"));
				}
			}
			
			String sql="select distinct staffcode from (select *  from base_orgmember order by orgcode,staffcode)";
			DataTable dt=db.runSelectQuery(sql);
			
			if(dt!=null&&dt.getRowsCount()>=1){
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow row = dt.get(i);
					if(!archusers.contains(row.getString("staffcode"))){
						if(row.getString("staffcode")==null||row.getString("staffcode").equals("")||row.getString("staffcode").equals("null")){
							System.out.println("");
						}
						Staff staff = null;
						try{
							staff = new Staff(row.getString("staffcode"));
						}catch (Exception e){
							staff = new Staff();
							staff.setStaffcode(row.getString("staffcode"));
							staff.setStaffname(CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", row.getString("staffcode")));
							staff.setOrgname("нч");
						}
						

						staffsList.add(staff);
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		Collections.sort(staffsList);
		return staffsList;
	}
	public static List<StaffIndexRelation>  queryMutilIndexRelation(){
		List<StaffIndexRelation> archusers = new ArrayList<StaffIndexRelation>();
		try {
			DBObject db=new DBObject();
			String sql2 = "select t1.objectcode,t1.indexarchcode from TBM_INDEXARCHUSER t1,TBM_INDEXARCHUSER t2 where t1.objectcode like '115%' and  t1.objectcode=t2.objectcode and t1.indexarchcode!=t2.indexarchcode order by t1.objectcode,t1.indexarchcode";
			DataTable dt_arch=db.runSelectQuery(sql2);
			
			if(dt_arch!=null&&dt_arch.getRowsCount()>=1){
				for(int i=0;i<dt_arch.getRowsCount();i++){
					DataRow row = dt_arch.get(i);
					Staff staff = new Staff(row.getString("objectcode"));
					Indexitem indexitem = new Indexitem(row.getString("indexarchcode"));
					StaffIndexRelation staffIndexRelation=new StaffIndexRelation();
					staffIndexRelation.setStaffcode(staff.getStaffcode());
					staffIndexRelation.setStaffname(staff.getStaffname());
					staffIndexRelation.setOrgcode(staff.getOrgcode());
					staffIndexRelation.setOrgname(staff.getOrgname());
					staffIndexRelation.setIndexcode(indexitem.getIndexName());
					archusers.add(staffIndexRelation);
				}
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		Collections.sort(archusers);
		return archusers;
	}
	public static List<String> queryMutilAllMeritRelation(){
		List<String> archusers = new ArrayList<String>();
		try {
			DBObject db=new DBObject();
			String sql2 = "select t1.staffcode,t1.groupno from TBM_allmeritgroupmember t1,TBM_allmeritgroupmember t2 where t1.staffcode like '115%' and  t1.staffcode=t2.staffcode and t1.groupno!=t2.groupno order by t1.staffcode,t1.groupno";
			DataTable dt_arch=db.runSelectQuery(sql2);
			
			if(dt_arch!=null&&dt_arch.getRowsCount()>=1){
				for(int i=0;i<dt_arch.getRowsCount();i++){
					DataRow row = dt_arch.get(i);
					Staff staff = new Staff(row.getString("staffcode"));
					AllMeritGroup group = new AllMeritGroup(row.getString("groupno"));
					archusers.add(staff.getStaffcode()+","+staff.getStaffname()+","+staff.getOrgname()+","+group.getGroupName());
				}
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return archusers;
	}
	/*public static String queryWithoutMeritData(String year,String month){
		
	}*/
	
}
