package com.action.system;

import com.dao.system.Entity;
import com.db.DataRow;
import com.db.DataTable;

public class EntityAction {

	public static String getselectItem(){
		
		Entity en=new Entity();
		String res="";
		
		DataTable dt=en.getSelectItem();
		
		 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
				try {
					res+="<option value='"+r.getString("table_name")+"'>"+r.getString("table_name")+"</option>";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 }
		 
		 return res;
		
	}
public static String getselectItem(String item){
		
		Entity en=new Entity();
		String res="";
		
		DataTable dt=en.getSelectItem(item);
		
		 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
				try {
					res+="<option value='"+r.getString("code_name")+"'>"+r.getString("code_name")+"</option>";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 }
		 
		 return res;
		
	}
}
