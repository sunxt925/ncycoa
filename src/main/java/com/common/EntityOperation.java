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
	private int dateflag; //标识需要的日期格式  0代表精确到分秒 1代表精确到年月日
	private Map<String,String> deletemap = null;  //用于生成删除的条件语句
	private boolean getcurrentdate=false;  //标示是否获得服务器当前时间
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
  			Map pkmap = new HashMap();  //记录主键键值，兼容联合主键
  			
  			for(int i=0,n=dt.getRowsCount();i<n;i++)               //循环拼接sql
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
  				



  				if(!foreign_entity.equals(""))                    //校验外键是否存在
  				{
  					if(!IsExist(foreign_entity,column_code,column_value))
  					{
  						res.append(r.getString("column_name")+"外键不存在,");
  					}
  				}
  				
  				
  				if(column_value.length()>column_length)     //校验长度是否超过
  				{
  					res.append(r.getString("column_name"));
  					res.append("超过最大长度");
  				}
  				
  				if(column_type.equals("NUMBER"))                      //获取字段类型，进行不同的sql拼接
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
  					
  					
  					if(getcurrentdate&&column_value.equals(""))  //如果表单没有传递值才去获取服务器当前时间
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
  				
  			
  				if(is_primarykey.equals("Y"))                     //校验主键是否已经存在，包括联合主键，校验主键是否为空
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
  	  					res.append("不能为空,");
  	  				 }
  					

  					
  				}
  				
  				
  				
  				
  			}
  		
  			addsql.deleteCharAt(addsql.length()-1);   //去掉最后一个逗号
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
				res.append("插入成功");
			}
			else
			{
				//System.out.print(addsql.toString());
				res.append("插入失败");
			}
  			}
  			else
  				res.append("已存在该对象");
			
			
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
  			StringBuilder primarykey = new StringBuilder("where ");  //用来生成条件
  			
  			boolean isexist = true;
  			
  			for(int i=0,n=dt.getRowsCount();i<n;i++)               //循环拼接sql
  			{
  				DataRow r = dt.get(i);
  				
  				column_code = r.getString("column_code");
  				
  				
  				String column_code0 = entity+"."+column_code;
  			
  				column_value = Format.NullToBlank(request.getParameter(column_code0));
  				
  				is_primarykey = r.getString("is_primarykey"); 
  				
  				foreign_entity = Format.NullToBlank(r.getString("foreign_entity"));
  				
  				column_type = r.getString("column_type");
  				
  				column_length = Integer.parseInt(r.getString("column_length"));
  				
  				
  				if(column_value.length()>column_length)     //校验长度是否超过
  				{
  					res.append(r.getString("column_name"));
  					res.append("超过最大长度");
  				}
  				
  				if(is_primarykey.equals("Y"))                     //检测该字段是否为主键，生成查询条件
  				{
  									
  				    if(column_value.equals(""))
  				    {
  					res.append(r.getString("column_name"));
  					res.append("不能为空,");
  				    }
  				    else
  				    {
  				    	String old_code = "old_"+column_code;
  				    	String old_code_value =  Format.NullToBlank(request.getParameter(old_code));
  				    	primarykey.append(getSql(column_type,column_code,old_code_value));
  				    	primarykey.append(" and ");            //增加分隔符
  				    	
  				    	
  				    }
  					
  				}
  			//	if(column_value.equals("")||column_value==null)
  			//		continue;
  				updatesql.append(getSql(column_type,column_code,column_value));
  				updatesql.append(",");                    //正价分隔符
  			}
  			
  			
  		    updatesql.deleteCharAt(updatesql.length()-1);   //去掉最后一个逗号
  			updatesql.append(" ");
  		    
  			//primarykey.deleteCharAt(primarykey.length()-1);
  			
  			
  			updatesql.append(primarykey.substring(0, primarykey.length()-4).toString());  //拼接形成最后的sql
  			
  			
  			if (db.run(updatesql.toString()))
			{
				//System.out.print(updatesql.toString());
				res.append("更改成功");
			}
			else
			{
				//System.out.print(updatesql.toString());
				res.append("更改失败");
			}	

  				
			
		}catch(Exception e)
		{
			res.append("错误");
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
			res.append("删除成功");
		}
		else
		{
			//System.out.print(delsql);
			res.append("删除失败");
		}	
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	

		
		return res.toString();
	}
	


	public boolean IsExist(Map pkmap)   // 判断是否存在函数，主键通用
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
	
	public boolean IsExist(String entity,String columncode,String value)   // 判断是否存在函数，主键外键通用
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
		if(column_type.equals("NUMBER"))                      //获取字段类型，进行不同的sql拼接
			{
				
				sql.append(column_code);
				sql.append("=");
				if(column_value.equals("")||column_value==null)
  					sql.append("''");
  				else 
  					
  				{
  					//区分整型和浮点型
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
				
				if(column_value.equals(""))  //如果表单没有传递值才去获取服务器当前时间
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
		String column_value0 = column_value;  //value0 为返回值
		
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
