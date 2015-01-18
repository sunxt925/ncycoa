package com.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class EntityOperation {
	
	private String sequence="";
	private Map<String,String> map=null;
	private String entity;
	private int dateflag; //��ʶ��Ҫ�����ڸ�ʽ  0����ȷ������ 1����ȷ��������
	private Map<String,String> deletemap = null;  //��������ɾ�����������
	private boolean getcurrentdate=false;  //��ʾ�Ƿ��÷�������ǰʱ��
	private Map<String, String> addmoreMap=new HashMap<String, String>();
	public boolean isGetcurrentdate() {
		return getcurrentdate;
	}

	public void setGetcurrentdate(boolean getcurrentdate) {
		this.getcurrentdate = getcurrentdate;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public Map<String, String> getDeletemap() {
		return deletemap;
	}

	public void setDeletemap(Map<String, String> deletemap) {
		this.deletemap = deletemap;
	}

	public int getDateflag() {
		return dateflag;
	}

	public void setDateflag(int dateflag) {
		this.dateflag = dateflag;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}



	public Map<String, String> getAddmoreMap() {
		return addmoreMap;
	}

	public void setAddmoreMap(Map<String, String> addmoreMap) {
		this.addmoreMap = addmoreMap;
	}

	public EntityOperation()
	{
		
	}
	
	public String Add(HttpServletRequest request)
	{
		StringBuilder res = new StringBuilder("");
		try{
			
			
			//String entity = request.getParameter("entity");
			
  			StringBuilder addsql = new StringBuilder("insert into ");
  			StringBuilder values = new StringBuilder("values(");
  			addsql.append(entity);
  			addsql.append("(");
  			
	/*	if(!sequence.equals(""))
		{
				int newsequence = SequenceUtil.getSequence(entity);
				
				addsql.append(sequence);
				addsql.append(",");
				
				values.append(newsequence);
				
			}
			*/
			

			
			
			String sql = "select * from system_entity_column where entity_code=?";
  			DBObject db = new DBObject();
  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
  			{ new Parameter.String(entity)
  			  };
  			DataTable dt = db.runSelectQuery(sql, pp);
  			

  			
  			
  			
  			String column_code="";
  			String is_primarykey="";
  			String foreign_entity="";
  			String column_type="";
  			int column_length=0;
  			String column_value="";
  			
  			boolean isexist = true;
  			Map pkmap = new HashMap();  //��¼������ֵ��������������
  			
  			for(int i=0,n=dt.getRowsCount();i<n;i++)               //ѭ��ƴ��sql
  			{
  				DataRow r = dt.get(i);  				
  				column_code = r.getString("column_code");  	
  		//		if(column_code.equals(sequence))
  		//			continue;
  				addsql.append(column_code);
  				addsql.append(",");
  				String column_code0 = entity+"."+column_code;
  				String fString=Format.NullToBlank(addmoreMap.get(column_code0));
  				if(fString.equals("1")){
  					column_value = Format.NullToBlank((String)request.getAttribute(column_code0));
  				}else {
  					column_value = Format.NullToBlank(request.getParameter(column_code0));
				}
  				
  				
  				is_primarykey = r.getString("is_primarykey"); 
  				
  				foreign_entity = Format.NullToBlank(r.getString("foreign_entity"));
  				
  				column_type = r.getString("column_type");
  				
  				column_length = Integer.parseInt(r.getString("column_length"));
  				



  				if(!foreign_entity.equals(""))                    //У������Ƿ����
  				{
  					if(!IsExist(foreign_entity,column_code,column_value))
  					{
  						res.append(r.getString("column_name")+"���������,");
  					}
  				}
  				
  				
  				if(column_value.length()>column_length)     //У�鳤���Ƿ񳬹�
  				{
  					res.append(r.getString("column_name"));
  					res.append("������󳤶�");
  				}
  				
  				if(column_type.equals("NUMBER"))                      //��ȡ�ֶ����ͣ����в�ͬ��sqlƴ��
  				{
  					//int column_value0 = Integer.parseInt(Format.NullToBlank(request.getParameter(column_code)));
  					column_value = getAddValue(column_code,column_value);
  					if(column_value.equals("")||column_value==null)
  						values.append("''");
  					else 
  						values.append(column_value);
  					values.append(",");
  					
  				}
  				
  				else if(column_type.equals("VARCHAR2"))
  				{
  					column_value = getAddValue(column_code,column_value);
  					values.append("'");
  					values.append(column_value);
  					values.append("'");
  					values.append(",");
  				}
  				
  				else if(column_type.equals("DATE"))
  				{
  					
  					
  					if(getcurrentdate&&column_value.equals(""))  //�����û�д���ֵ��ȥ��ȡ��������ǰʱ��
  					{
  						Calendar cal=Calendar.getInstance();    
  	  					int y=cal.get(Calendar.YEAR);    
  	  					int m=cal.get(Calendar.MONTH)+1;    
  	  					int d=cal.get(Calendar.DATE);    
  	  					column_value=String.valueOf(y)+'-'+String.valueOf(m)+'-'+String.valueOf(d);
  					}
  						//column_value = TimeUtil.getNowDate(dateflag);
  					
  					//values.append("'");
  					values.append("to_date('");
  					values.append(column_value);
  					values.append("','yyyy-mm-dd hh24:mi:ss'),");
  					
  				}
  				
  				else if(column_type.equals("CLOB"))
  				{
  					column_value = getAddValue(column_code,column_value);
  					values.append("'");
  					values.append(column_value);
  					//System.out.println(column_value);
  					values.append("'");
  					values.append(",");
  				}
  				
  			
  				if(is_primarykey.equals("Y"))                     //У�������Ƿ��Ѿ����ڣ���������������У�������Ƿ�Ϊ��
  				{
  					pkmap.put(column_code, column_value);
  				//	if(isexist)
  				//	{
  				//	if(!IsExist(entity,column_code,column_value))
  				//		isexist = false;
  				//	}
  					
  				    if(column_value.equals(""))
  	  				 {
  	  					res.append(r.getString("column_name"));
  	  					res.append("����Ϊ��,");
  	  				 }
  					

  					
  				}
  				
  				
  				
  				
  			}
  		
  			addsql.deleteCharAt(addsql.length()-1);   //ȥ�����һ������
  			addsql.append(") ");
  			values.deleteCharAt(values.length()-1);
  			values.append(")");
  			
  			addsql.append(values.toString());
  			//System.out.println(addsql.toString());
  			if(!IsExist(pkmap))
  			{
  			
			if (db.run(addsql.toString()))
			{
				//System.out.print(addsql.toString());
				res.append("����ɹ�");
			}
			else
			{
				//System.out.print(addsql.toString());
				res.append("����ʧ��");
			}
  			}
  			else
  				res.append("�Ѵ��ڸö���");
			
			
		}catch(Exception e){
			
		}
		
		return res.toString();
	}
	
	public String Update(HttpServletRequest request)
	{
		StringBuilder res = new StringBuilder("");
		try{
			
		//	String entity = request.getParameter("entity");
						
			String sql = "select * from system_entity_column where entity_code=?";
  			DBObject db = new DBObject();
  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
  			{ new Parameter.String(entity)
  			  };
  			DataTable dt = db.runSelectQuery(sql, pp);
  			
  			StringBuilder updatesql = new StringBuilder("update ");
  			updatesql.append(entity);
  			updatesql.append(" set ");
  			
  			String column_code="";
  			String is_primarykey="";
  			String foreign_entity="";
  			String column_type="";
  			int column_length=0;
  			String column_value="";
  			StringBuilder primarykey = new StringBuilder("where ");  //������������
  			
  			boolean isexist = true;
  			
  			for(int i=0,n=dt.getRowsCount();i<n;i++)               //ѭ��ƴ��sql
  			{
  				DataRow r = dt.get(i);
  				
  				column_code = r.getString("column_code");
  				
  				
  				String column_code0 = entity+"."+column_code;
  			
  				column_value = Format.NullToBlank(request.getParameter(column_code0));
  				
  				is_primarykey = r.getString("is_primarykey"); 
  				
  				foreign_entity = Format.NullToBlank(r.getString("foreign_entity"));
  				
  				column_type = r.getString("column_type");
  				
  				column_length = Integer.parseInt(r.getString("column_length"));
  				
  				
  				if(column_value.length()>column_length)     //У�鳤���Ƿ񳬹�
  				{
  					res.append(r.getString("column_name"));
  					res.append("������󳤶�");
  				}
  				
  				if(is_primarykey.equals("Y"))                     //�����ֶ��Ƿ�Ϊ���������ɲ�ѯ����
  				{
  									
  				    if(column_value.equals(""))
  				    {
  					res.append(r.getString("column_name"));
  					res.append("����Ϊ��,");
  				    }
  				    else
  				    {
  				    	String old_code = "old_"+column_code;
  				    	String old_code_value =  Format.NullToBlank(request.getParameter(old_code));
  				    	primarykey.append(getSql(column_type,column_code,old_code_value));
  				    	primarykey.append(" and ");            //���ӷָ���
  				    	
  				    	
  				    }
  					
  				}
  			//	if(column_value.equals("")||column_value==null)
  			//		continue;
  				updatesql.append(getSql(column_type,column_code,column_value));
  				updatesql.append(",");                    //���۷ָ���
  			}
  			
  			
  		    updatesql.deleteCharAt(updatesql.length()-1);   //ȥ�����һ������
  			updatesql.append(" ");
  		    
  			//primarykey.deleteCharAt(primarykey.length()-1);
  			
  			
  			updatesql.append(primarykey.substring(0, primarykey.length()-4).toString());  //ƴ���γ�����sql
  			
  			
  			if (db.run(updatesql.toString()))
			{
				//System.out.print(updatesql.toString());
				res.append("���ĳɹ�");
			}
			else
			{
				//System.out.print(updatesql.toString());
				res.append("����ʧ��");
			}	

  				
			
		}catch(Exception e)
		{
			res.append("����");
		}
		
		return res.toString();
	}
	
	
	public String Delete()
	{
		StringBuilder res = new StringBuilder("");
		StringBuilder delsql = new StringBuilder("delete from ");
		delsql.append(entity);
		delsql.append(" where ");
		
		Iterator it = deletemap.entrySet().iterator();
		try{
			DBObject db = new DBObject();
		while(it.hasNext())
		{
			Map.Entry<String, String> entry = (Entry<String, String>) it.next();
			
				
				String sql = "select * from system_entity_column where entity_code=? and column_code=?";
	  			
	  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	  			{ new Parameter.String(entity),
	  			new Parameter.String(entry.getKey())
	  			  };
	  			//System.out.println(entity);
	  			//System.out.println(entry.getKey());
	  			DataTable dt = db.runSelectQuery(sql, pp);
	  			DataRow r = dt.get(0);
	  			String column_type = r.getString("COLUMN_TYPE");
	  			
	  			delsql.append(getSql(column_type,entry.getKey(),entry.getValue()));
	  			delsql.append(" and ");
				


		}
		
		String sql = delsql.substring(0, delsql.length()-4).toString();
		

		
		if (db.run(sql))
		{
			//System.out.print(sql);
			res.append("ɾ���ɹ�");
		}
		else
		{
			//System.out.print(delsql);
			res.append("ɾ��ʧ��");
		}	
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	

		
		return res.toString();
	}
	


	public boolean IsExist(Map pkmap)   // �ж��Ƿ���ں���������ͨ��
	{
		
		boolean isexist = false; 
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(entity).append(" where ");
		Iterator it = pkmap.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<String, String> entry = (Entry<String, String>) it.next();
			sql.append(entry.getKey()).append("='").append(entry.getValue()).append("' and ");

		}
		String sql0 = sql.substring(0, sql.length()-4).toString();
		
		
		try{

  			DBObject db = new DBObject();

  			
  			DataTable dt = db.runSelectQuery(sql0);
  			
  			if(dt.getRowsCount()>0)
  			{
  				isexist = true;
  			}
			
		}catch(Exception e)
		{
			
		}
		return isexist;
	}
	
	public boolean IsExist(String entity,String columncode,String value)   // �ж��Ƿ���ں������������ͨ��
	{
		
		boolean isexist = false; 
		try{
			//System.out.print(entity);
			String sql = "select * from "+entity+" where "+columncode+"=?";
  			DBObject db = new DBObject();
  			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
  			{ 
  					new Parameter.String(value)
  			  };
  			
  			DataTable dt = db.runSelectQuery(sql, pp);
  			
  			if(dt.getRowsCount()>0)
  			{
  				isexist = true;
  			}
			
		}catch(Exception e)
		{
			
		}
		return isexist;
	}
	
	public String getSql(String column_type,String column_code,String column_value )
	{
		
		StringBuilder sql = new StringBuilder();
		if(column_type.equals("NUMBER"))                      //��ȡ�ֶ����ͣ����в�ͬ��sqlƴ��
			{
				
				sql.append(column_code);
				sql.append("=");
				if(column_value.equals("")||column_value==null)
  					sql.append("''");
  				else 
  					
  				{
  					//�������ͺ͸�����
  					String[] ss=column_value.split("\\.");
  					if(ss.length==1){
  						int column_value0 = Integer.parseInt(column_value);
  						sql.append(column_value0);
  					}
  					if(ss.length==2){
  						float column_value0 = Float.parseFloat(column_value);
  						sql.append(column_value0);
  					}
  					
  				}
				
				
				//sql.append(column_value0);
				

			}
			
			else if(column_type.equals("VARCHAR2"))
			{
				
			    sql.append(column_code);
			    sql.append("=");
			    sql.append("'");
			    sql.append(column_value);
			    sql.append("'");

			}
			
			else if(column_type.equals("DATE"))
			{
				
				if(column_value.equals(""))  //�����û�д���ֵ��ȥ��ȡ��������ǰʱ��
					{
						Calendar cal=Calendar.getInstance();    
	  					int y=cal.get(Calendar.YEAR);    
	  					int m=cal.get(Calendar.MONTH)+1;    
	  					int d=cal.get(Calendar.DATE);    
	  					column_value=String.valueOf(y)+'-'+String.valueOf(m)+'-'+String.valueOf(d);
					}
						//column_value = TimeUtil.getNowDate(dateflag);
					
					//values.append("'");
					//values.append("to_date('");
				//	values.append(column_value);
					//values.append("','yyyy-mm-dd'),");
				
				
				//String column_value0 = column_value;
				//if(column_value0.equals(""))
				////  column_value0 = TimeUtil.getNowDate(dateflag);
			    sql.append(column_code);
			    sql.append("=");
			   // sql.append("'");
			    sql.append("to_date('");
			   // sql.append(column_value0+"'");
			    sql.append(column_value);
			    sql.append("','yyyy-mm-dd hh24:mi:ss')");
			}
		
			else if(column_type.equals("CLOB"))
			{
				
			    sql.append(column_code);
			    sql.append("=");
			    sql.append("'");
			    sql.append(column_value);
			    sql.append("'");

			}
		
		return sql.toString();
	    
	}
	
	public String getAddValue(String column_code,String column_value)
	{
		String column_value0 = column_value;  //value0 Ϊ����ֵ
		
		if(column_code.equals(sequence))
		{
		  column_value0 = ""+SequenceUtil.getSequence(entity);
		}
		else if(map!=null)
		{
			Iterator it = map.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry<String, String> entry = (Entry<String, String>) it.next();
				if(entry.getKey().equals(column_code))
				{
					column_value0 = entry.getValue();
				}
			}
		}
		
		
		return column_value0; 
	}

	
}
