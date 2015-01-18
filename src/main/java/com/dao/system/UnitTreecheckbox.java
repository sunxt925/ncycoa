
package com.dao.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.system.Menuchecked;
import com.entity.system.OrgLittle;

public class UnitTreecheckbox
{

	// 用户树
	public static JSONArray getTreeNew()
	{
		try
		{
			DBObject db = new DBObject();
			//String sql = "";
			String sql="select * from base_org where orgcode  like 'NC.01%' order by orgcode";
			// System.out.print(sql);
			
			DataTable dt2 = null;
			dt2 = db.runSelectQuery(sql);
			JSONArray jsonarray=new JSONArray();
			String temp;
			int pre_level=1;
			if(dt2!=null && dt2.getRowsCount()>0)
			 {
			 
			    OrgLittle org=new OrgLittle();
			    JSONObject jsonObject;
			 
			 	for (int i=0;i<dt2.getRowsCount();i++)
				{
				
					DataRow r=dt2.get(i);
					temp=r.getString("orgcode");
					
					org.setId(temp);
					org.setName(r.getString("orgname"));
					org.setPId(r.getString("parentorgcode"));
					//jsonObject = JSONObject.fromObject(org);
					jsonObject = JSON.parseObject(JSON.toJSONString(org));;
					jsonarray.add(i,jsonObject); 	
			    }
			  }
			//System.out.println(jsonarray);
			return jsonarray;
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static JSONArray getTreeNew(String sheetname,String code,String codename,String parentcode,String str)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select * from "+sheetname+" where "+code+"  like '"+str+"%' order by "+code+"";
			
			DataTable dt2 = null;
			dt2 = db.runSelectQuery(sql);
			JSONArray jsonarray=new JSONArray();
			String temp;
			int pre_level=1;
			if(dt2!=null && dt2.getRowsCount()>0)
			 {
			 
			    OrgLittle org=new OrgLittle();
			    JSONObject jsonObject;
			    LoadCode ld=new LoadCode();
			    DataTable dt=ld.getSelectItem(sheetname.toUpperCase());
			    
			 	for (int i=0;i<dt2.getRowsCount();i++)
				{
				
					DataRow r=dt2.get(i);
					temp=r.getString(code);
					
					org.setId(temp);
					org.setName(r.getString(codename));
					org.setPId(r.getString(parentcode));
					for(int j=0;j<dt.getRowsCount();j++){
						if(dt.get(j).getString(code).equals(r.getString(parentcode)))
						org.setPName(dt.get(j).getString(codename));
					}
					//org.setPname(CodeDictionary.syscode_traslate(sheetname, code, codename, r.getString(parentcode)));
					//org.setPname("ssss");
					//jsonObject = JSONObject.fromObject(org);
					jsonObject = (JSONObject)JSON.toJSON(org);
					jsonarray.add(i,jsonObject); 	
			    }
			  }
			//double end  = System.currentTimeMillis() ; 	
			//System.out.println("run time:"+(end-start));
			//System.out.println(jsonarray);
			return jsonarray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static JSONArray getTreeNewtemp()
	{
		try
		{
			DBObject db = new DBObject();
			//String sql = "";
			String sql="select * from com_goodsclass where goodscode  like 'WF%' order by goodscode";
			// System.out.print(sql);
			
			DataTable dt2 = null;
			dt2 = db.runSelectQuery(sql);
			JSONArray jsonarray=new JSONArray();
			String temp;
			int pre_level=1;
			if(dt2!=null && dt2.getRowsCount()>0)
			 {
			    OrgLittle org=new OrgLittle();
			    JSONObject jsonObject;
			 
			 	for (int i=0;i<dt2.getRowsCount();i++)
				{
					DataRow r=dt2.get(i);
					temp=r.getString("goodscode");
					
					org.setId(temp);
					org.setName(r.getString("goodsname"));
					org.setPId(r.getString("parentgoodscode"));
					//jsonObject = JSONObject.fromObject(org);
					jsonObject = JSON.parseObject(JSON.toJSONString(org));;
					jsonarray.add(i,jsonObject); 
			    }
			  }
			System.out.println(jsonarray);
			return jsonarray;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static JSONArray getTreeNew(String unitccm)
	{
		try
		{
			if (unitccm.equals(""))
				unitccm = "1151";
			String res = "";
			DBObject db = new DBObject();
			//String sql = "";
			String sql="select * from base_org where orgcode not like '"+unitccm+"%' order by orgcode";
			// System.out.print(sql);
			
			DataTable dt2 = null;
			dt2 = db.runSelectQuery(sql);
			JSONArray jsonarray=new JSONArray();
			String temp;
			int pre_level=1;
			if(dt2!=null && dt2.getRowsCount()>0)
			 {
			 
			    OrgLittle org=new OrgLittle();
			    JSONObject jsonObject;
			 
			 	for (int i=0;i<dt2.getRowsCount();i++)
				{
				
					DataRow r=dt2.get(i);
					temp=r.getString("orgcode");
					
					org.setId(temp);
					org.setName(r.getString("orgname"));
					if((temp.length()/4)==pre_level){
						if(temp.length()/4==1){
							org.setPId("0");
						}
						else {
							org.setPId(temp.substring(0,temp.length()-4));
						}
						
					}
					else if((temp.length()/4)>pre_level){
						org.setPId(temp.substring(0,temp.length()-4));
						pre_level=temp.length()/4;
					}
					else{
						pre_level=temp.length()/4;
						org.setPId(temp.substring(0,temp.length()-4));
					}
					//jsonObject = JSONObject.fromObject(org);
					jsonObject = JSON.parseObject(JSON.toJSONString(org));;
					jsonarray.add(i,jsonObject); 	
			    }
			  }
			return jsonarray;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static JSONArray getTreeMenu(String roid)
	{
		try
		{
			DBObject db = new DBObject();
			//String sql = "";
			String sqlroleprivillege="select * from SYSTEM_MENU_PRIVILLEGE where role_id="+roid;
			String sql="select * from system_menu order by level_code";
			// System.out.print(sql);
			DataTable dt1 = null;
			DataTable dt2 = null;
			dt1=db.runSelectQuery(sqlroleprivillege);
			dt2 = db.runSelectQuery(sql);
			JSONArray jsonarray=new JSONArray();
			String temp;
			int pre_level=1;
			
					
					if(dt2!=null && dt2.getRowsCount()>0)
					 {
					 
						
					    JSONObject jsonObject;
					 
					 	for (int i=0;i<dt2.getRowsCount();i++)
						{
					 		Menuchecked org=new Menuchecked();
							DataRow r=dt2.get(i);
							temp=r.getString("level_code");
							//org.setChecked(true);
							if(dt1!=null&&dt1.getRowsCount()>0)
							{
								
								for(int j=0;j<dt1.getRowsCount();j++)
								{
									//System.out.println(temp);
									//System.out.println(dt1.get(j).getString("level_code"));
									
									if(temp.equals(dt1.get(j).getString("level_code")))
									{
										org.setChecked(true);
										//System.out.println("相等");
									}
										
								}
							}
							org.setOpen(true);
							org.setId(temp);
							org.setName(r.getString("menu_name"));
							if((temp.length()/3)==pre_level){
								if(temp.length()/3==1)
									org.setPId("0");
								else
								org.setPId(temp.substring(0,temp.length()-3));
							}
								 
							else if((temp.length()/3)>pre_level){
								org.setPId(temp.substring(0,temp.length()-3));
								pre_level=temp.length()/3;
							}
							else{
								pre_level=temp.length()/3;
								org.setPId(temp.substring(0,temp.length()-3));
							}
							//jsonObject = JSONObject.fromObject(org);
							jsonObject = JSON.parseObject(JSON.toJSONString(org));;
							jsonarray.add(i,jsonObject); 	
					    }
					  }
			//System.out.println(jsonarray);
			
			return jsonarray;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}