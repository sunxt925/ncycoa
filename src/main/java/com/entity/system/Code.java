package com.entity.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.common.ExecuteResult;
import com.common.Format;
import com.dao.system.Bm;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.ExecSql;
import com.db.Parameter;
import com.db.SqlHelper;

public class Code {

	public String table_name=""; //编码表名
	public String code_class="";    //编码表类别
	public String table_title="";    //编码表标题
	public String code_col="";    //编码字段名称
	public String value_col="";   //编码值字段
	public String pcode_col="";   //父级编码字段值
	public String isload="";      //是否加载内存
	public String new_table_name ="";  // 新编码表名
	//public String code_class = "";  // 编码表类型
	
	public Code() {
		
	}
	
	public Code(String table_name)
	{
		DBObject db = new DBObject();
		String sql = "select * from system_tablecodemeta where table_name=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		{ new Parameter.String(table_name) };
		try
		{
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.setTable_name(table_name);
				this.setCode_class(r.getString("code_class"));
				this.setTable_title(r.getString("table_title"));
				this.setCode_col(r.getString("code_col"));
				this.setValue_col(r.getString("value_col"));
				this.setPcode_col(r.getString("pcode_col"));
				this.setIsload(r.getString("isload"));
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	

	/**
	 * 添加
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into system_tablecodemeta (table_name,code_class,table_title,code_col,value_col,pcode_col,isload) values (?,?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{       new Parameter.String(this.table_name), 
					new Parameter.String(this.code_class),
					new Parameter.String(this.table_title),
					new Parameter.String(this.code_col),
					new Parameter.String(this.value_col),
					new Parameter.String(this.pcode_col),
					new Parameter.String(this.isload)};
					
			if (db.run(sql, pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			
			String sql = "update system_tablecodemeta set table_name=?,code_class=?,table_title=?,code_col=?,value_col=?,pcode_col=?,isload=? where table_name=?";
			System.out.print(table_title);
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{       new Parameter.String(this.new_table_name),
					new Parameter.String(this.code_class),
					new Parameter.String(this.table_title),
					new Parameter.String(this.code_col),
					new Parameter.String(this.value_col),
					new Parameter.String(this.pcode_col),
					new Parameter.String(this.isload),
					new Parameter.String(this.table_name)
					 };
			if (db.run(sql, pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除
	 * 
	 * @param BmString
	 * @return
	 */
	public boolean Delete(String param)
	{
		ExecSql comm = null;
		try
		{
			String sql = "delete from system_tablecodemeta where table_name=?";
			if (param.indexOf(",") == -1)
			{
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(param) };
				comm = SqlHelper.helper().createCommand(sql, pp);
				comm.beginTransaction();
				comm.execute();
			}
			else
			{
				String[] ids = param.split(",");
				Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
				{ new Parameter.String(ids[0]) };
				comm = SqlHelper.helper().createCommand(sql, pp1);
				comm.beginTransaction();
				comm.execute();
				for (int i = 1; i < ids.length; i++)
				{
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
					{ new Parameter.String(ids[i]) };
					comm.setCommand(sql, pp2);
					comm.execute();
				}
			}
			comm.commit();
			return true;
		}
		catch (Exception e)
		{
			try
			{
				if (comm != null)
				{
					comm.rollback();
				}
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (comm != null)
			{
				comm.Dispose();
			}
		}
	}

	public boolean DeleteCol(String param,String code_id)
	{
		ExecSql comm = null;
		
		try
		{
			
			String sql = "delete from system_tablecodemeta_col where table_name=? and code_id=?";
			DBObject db=new DBObject();
			DataTable dt=new DataTable();
			if (code_id.indexOf(",") == -1)
			{
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(param),
						new Parameter.String(code_id)};
				
				String sql3= "select * from system_tablecodemeta_col where table_name=? and pcode_id=?";
				DBObject db2=new DBObject();
				DataTable dt2=db2.runSelectQuery(sql3,pp);
				//System.out.println(param+pcodeidString+dt2.getRowsCount());
				if(dt2.getRowsCount()<=0)
				{
					comm = SqlHelper.helper().createCommand(sql, pp);
				comm.beginTransaction();
				comm.execute();
				}	
				else {
					
					return false;
				}
			}
			else
			{
				String[] ids = code_id.split(",");
				Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
				{ new Parameter.String(param),
					new Parameter.String(ids[0])};
				String sql4= "select * from system_tablecodemeta_col where table_name=? and pcode_id=?";
				DBObject db2=new DBObject();
				DataTable dt2=db2.runSelectQuery(sql4,pp1);
				if(dt2.getRowsCount()<=0)
				{
					comm = SqlHelper.helper().createCommand(sql, pp1);
				comm.beginTransaction();
				comm.execute();
				}	
				else {
					
					return false;
				}
				
				for (int i = 1; i < ids.length; i++)
				{
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
					{ new Parameter.String(param),
							new Parameter.String(ids[i]) };
				    String sql3= "select * from system_tablecodemeta_col where table_name=? and pcode_id=?";
					DBObject db3=new DBObject();
					DataTable dt3=db3.runSelectQuery(sql3,pp2);
					//System.out.println(param+pcodeidString+dt2.getRowsCount());
					if(dt2.getRowsCount()<=0)
					{
						comm = SqlHelper.helper().createCommand(sql, pp2);
					comm.beginTransaction();
					comm.execute();
					}	
					else {
						
						return false;
					}
					
				}
			}
			comm.commit();
			return true;
		}
		catch (Exception e)
		{
			try
			{
				if (comm != null)
				{
					comm.rollback();
				}
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (comm != null)
			{
				comm.Dispose();
			}
		}
	}
	/**
	 * 加载
	 * 
	 * @param BmString
	 * @return
	 */
	public boolean Load(String param)
	{
		try
		{
			
			if (param.indexOf(",") == -1)
			{
				Bm.Init(param);
			}
			else
			{
				String[] ids = param.split(",");
				for (int i = 0; i < ids.length; i++)
				{
					Bm.Init(ids[i]);
				}
			}
			return true;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return false;
		}
	}

public DataTable getTableCodeColumnList(String table_name,int pageno,int perpage){
		
		
		try {
			DBObject db=new DBObject();
			
			String base_sql = "select * from (select * from system_tablecodemeta_col where table_name='"
				+ table_name + "')"+"order by code_id";
			
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
		
			return db.runSelectQuery(sql_run);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	public DataTable getTableCodeColumn(String table_name)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db
					.runSelectQuery("select * from (select * from system_tablecodemeta_col where table_name='"
							+ table_name + "')"+"order by code_id");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//获取部分编码
	public DataTable getTableCodeColumn(String table_name,String code_id)
	{
		try
		{
			
			DBObject db = new DBObject();
			DataTable dt = db
					.runSelectQuery("select * from system_tablecodemeta_col where table_name='"
							+ table_name + "'"+"and code_id like '"+code_id+"%' order by code_id");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public DataTable getCodeItemColumnList(String table_name,String level_id,int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||code_id||'\">' as 选择,code_id as 编码,code_name as 编码说明  from (select * from system_tablecodemeta_col where table_name='"
							+ table_name + "'"+"and level_id like '"+level_id+"%' order by code_id)";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			// System.out.print(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ExecuteResult UpdateColumn(HttpServletRequest request)
	{
		ExecuteResult er = new ExecuteResult();
		
		try
		{
			String[] ids = request.getParameterValues("items");
			String para = "";
			for (int i = 0; i < ids.length; i++)
			{
				if (i == ids.length - 1)
				{
					para = para + ids[i];
				}
				else
				{
					para = para + ids[i] + ",";
				}
			}
			
			String sql = "update system_tablecodemeta_col set code_id=?,code_name=? where table_name=? and code_id=?";
			DBObject db = new DBObject();
			if(para.indexOf(",") == -1){
				
				//String code_id=Format.NullToBlank(request
				//				.getParameter("code_id"));
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				 { new Parameter.String(request
							.getParameter("code_id_" + para)),
				   new Parameter.String(request
							.getParameter("code_name_" + para)),
				   new Parameter.String(this.table_name),
				   new Parameter.String(para) };
				db.run(sql, pp);
				er.setRes(true);
			}
			else
			{
				String[] idsss = para.split(",");
				
				Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
				{new Parameter.String(request.getParameter("code_id_" + idsss[0])),
				   new Parameter.String(request.getParameter("code_name_" + idsss[0])),
				   new Parameter.String(this.table_name),
				   new Parameter.String(idsss[0]) };
				db.run(sql, pp1);
				er.setRes(true);
				for (int i = 1; i < idsss.length; i++)
				{
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
				{new Parameter.String(request.getParameter("code_id_" + idsss[i])),
					new Parameter.String(request.getParameter("code_name_" + idsss[i])),
					 new Parameter.String(this.table_name),
					new Parameter.String(idsss[i]) };
					db.run(sql, pp2);
					er.setRes(true);
				}
		
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			er.setRes(false);
			er.setRes_str(e.getMessage());
		}
		return er;
		

	}
	
public DataTable getAllCodeList(){
		
		
		try {
			DBObject db=new DBObject();
			String sql="select * from system_tablecodemeta order by code_class";
			DataTable dt=new DataTable();
			return db.runSelectQuery(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	
		
		
	}


	public DataTable getCodeList(int pageno,int perpage){
		
		
		try {
			DBObject db=new DBObject();
		
			String base_sql = "select '选择' as 选择, table_name,table_name as 编码表名,code_class  as 编码类别,table_title as 编码表说明, code_col as 编码字字段名,value_col as 编码值字段名, pcode_col as 父级编码字段名,isload as 是否加载内存 ,'操作' as  操作  from system_tablecodemeta order by code_class";
			
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
		 	return db.runSelectQuery(sql_run);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	public  DataTable getEntityCodeList(String table_name,int page_no,int per_page){
		SystemEntity se=new SystemEntity();
		DBObject db=new DBObject();
		DataTable dt=se.getEntityColumn(table_name);
		StringBuilder base_sql=new StringBuilder();
		base_sql.append("select ");
		try {
		for(int i=0;i<dt.getRowsCount()-1;i++){
			
			base_sql.append(dt.get(i).getString("column_code")+" ,");
			
		}
		base_sql.append(dt.get(dt.getRowsCount()-1).getString("column_code")+" ");
		base_sql.append("from "+table_name);
	    String sql_run = Format.getFySql(base_sql.toString(), page_no, per_page);
		return db.runSelectQuery(sql_run);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public DataTable getAllEntityCodeList(String table_name){
		SystemEntity se=new SystemEntity();
		DBObject db=new DBObject();
		DataTable dt=se.getEntityColumn(table_name);
		StringBuilder base_sql=new StringBuilder();
		base_sql.append("select ");
		try {
		for(int i=0;i<dt.getRowsCount()-1;i++){
			
			base_sql.append(dt.get(i).getString("column_code")+" ,");
			
		}
		base_sql.append(dt.get(dt.getRowsCount()-1).getString("column_code")+" ");
		base_sql.append("from "+table_name);
		return db.runSelectQuery(base_sql.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public String code_class_traslate()
	{
		String res="";
		List list=(List)Bm.getTb();
		
		return res;
	}
	//初始化新增公共编码
	public static void initialCode(HttpServletRequest request){
		int codenum=0;
		try {
			
		
		DBObject db=new DBObject();
		DataTable dTable=db.runSelectQuery("select * from system_tablecodemeta_col where level_id='1'");
		codenum=dTable.getRowsCount()+1;
		
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("");
		for(int i=0;i<4-Format.getIntlength(codenum);i++)
		{
			sBuilder.append("0");
		}
    	String sql="insert into system_tablecodemeta_col(table_name,code_id,code_name,level_id) values(?,?,?,?)";
    	Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
    		new Parameter.String(request.getParameter("SYSTEM_TABLECODEMETA.TABLE_NAME")),
    		new Parameter.String(sBuilder.toString()+codenum),
    		new Parameter.String(request.getParameter("SYSTEM_TABLECODEMETA.TABLE_TITLE")),
    		new Parameter.String("1")
    	};
    	DBObject db2=new DBObject();
    	db2.run(sql, pp);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String tableName) {
		table_name = tableName;
	}

	public String getCode_class() {
		return code_class;
	}

	public void setCode_class(String codeClass) {
		code_class = codeClass;
	}

	public String getTable_title() {
		return table_title;
	}

	public void setTable_title(String tableTitle) {
		table_title = tableTitle;
	}

	public String getCode_col() {
		return code_col;
	}

	public void setCode_col(String codeCol) {
		code_col = codeCol;
	}

	public String getValue_col() {
		return value_col;
	}

	public void setValue_col(String valueCol) {
		value_col = valueCol;
	}

	public String getPcode_col() {
		return pcode_col;
	}

	public void setPcode_col(String pcodeCol) {
		pcode_col = pcodeCol;
	}

	public String getIsload() {
		return isload;
	}

	public void setIsload(String isload) {
		this.isload = isload;
	}

	public String getNew_table_name() {
		return new_table_name;
	}

	public void setNew_table_name(String newTableName) {
		new_table_name = newTableName;
	}
	
	
}
