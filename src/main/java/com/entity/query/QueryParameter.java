package com.entity.query;


import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.ExecSql;
import com.db.Parameter;
import com.db.SqlHelper;

public class QueryParameter {

	public int queryid = 0;// 查询id

	public String paracode = "";// 参数编码

	public String paraname = "";// 参数名称

	public String defaultvalue = "";// 默认值

	public String focepara = "";// 必填条件

	public String paratype = "";// 参数类型

	public String code = "";// 引用编码
	
	public String codepara=""; //编码参数

	public int rankno = 0;// 排序序号
	
	
	
	public QueryParameter(){
		
	}

//	public QueryParameter(int queryid){
//		try
//		{
//			DBObject db = new DBObject();
//			String sql = "select * from query_parameter where queryid=?";
//			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
//			{ new Parameter.Int(queryid)};
//			DataTable dt = db.runSelectQuery(sql, pp);
//			if (dt != null && dt.getRowsCount() == 1)
//			{
//				DataRow r = dt.get(0);
//				this.queryid=queryid;
//				this.paracode=r.getString("paracode");
//			    this.paraname=r.getString("paraname");
//			    this.defaultvalue=r.getString("defaultvalue");
//			    this.focepara=r.getString("focepara");
//			    this.paracode=r.getString("paracode");
//			    this.paratype=r.getString("paratype");
//			    this.code=r.getString("code");
//			    this.codepara=r.getString("codepara");
//			    this.rankno=Integer.parseInt(r.getString("rankno"));
//			
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	public QueryParameter(int queryid, String paracode){
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from query_parameter where queryid=? and paracode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(queryid), new Parameter.String(paracode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.queryid=queryid;
				this.paracode=paracode;
			    this.paraname=r.getString("paraname");
			    this.defaultvalue=r.getString("defaultvalue");
			    this.focepara=r.getString("focepara");
			    this.paracode=r.getString("paracode");
			    this.paratype=r.getString("paratype");
			    this.code=r.getString("code");
			    this.codepara=r.getString("codepara");
			    this.rankno=Integer.parseInt(r.getString("rankno"));
			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean Insert()
	{
		try
		{
			// System.out.print(this.queryid);
			DBObject db = new DBObject();
			String sql = "insert into query_parameter (queryid,paracode,paraname,defaultvalue,focepara,paratype,code,codepara,rankno) values (?,?,?,?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.Int(this.queryid),
					new Parameter.String(this.paracode),
					new Parameter.String(this.paraname),
					new Parameter.String(this.defaultvalue),
					new Parameter.String(this.focepara),
					new Parameter.String(this.paratype),
					new Parameter.String(this.code),
					new Parameter.String(this.codepara),
					new Parameter.Int(this.rankno) };
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

	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update query_parameter set paracode=?,paraname=?,defaultvalue=?,focepara=?,paratype=?,code=?,codepara=?,rankn=? where queryid=? and querycode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					
					new Parameter.String(this.paracode),
					new Parameter.String(this.paraname),
					new Parameter.String(this.defaultvalue),
					new Parameter.String(this.focepara),
					new Parameter.String(this.paratype),
					new Parameter.String(this.code),
					new Parameter.String(this.codepara),
					new Parameter.Int(this.rankno) ,
					new Parameter.Int(this.queryid),
					new Parameter.Int(paracode)
					
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

	public boolean Delete(int queryid, String param)
	{
		ExecSql comm = null;
		try
		{
			String sql = "delete from query_parameter where queryid=? and querycode=?";
			if (param.indexOf(",") == -1)
			{
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.Int(queryid), new Parameter.String(param) };
				comm = SqlHelper.helper().createCommand(sql, pp);
				comm.beginTransaction();
				comm.execute();
			}
			else
			{
				String[] ids = param.split(",");
				Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
				{ new Parameter.Int(queryid), new Parameter.String(ids[0]) };
				comm = SqlHelper.helper().createCommand(sql, pp1);
				comm.beginTransaction();
				comm.execute();
				for (int i = 1; i < ids.length; i++)
				{
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
					{ new Parameter.Int(queryid), new Parameter.String(ids[i]) };
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
	
	public DataTable getQueryParameterList(int queryid, int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '选择' as 选择,paracode,paracode as 参数编码,paraname as 参数名称,defaultvalue as 默认值,focepara as 必填条件,paratype as 参数类型,code as 引用编码,codepara as 编码参数,rankno as 排序序号,'操作' as 操作  from query_parameter where queryid="+queryid;
			
			
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

	// 得到该表所有数据
	public DataTable getAllQueryParameterList(int queryid)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from query_parameter where queryid="+queryid);
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllparameter(int queryid){
		
		try {
			
			String sql="select * from query_parameter orber by rankno where queryid=? ";
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.Int(queryid)	
			};
			DBObject db=new DBObject();
			return db.runSelectQuery(sql,pp);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public int getQueryid() {
		return queryid;
	}

	public void setQueryid(int queryid) {
		this.queryid = queryid;
	}

	public String getParacode() {
		return paracode;
	}

	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	public String getParaname() {
		return paraname;
	}

	public void setParaname(String paraname) {
		this.paraname = paraname;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getFocepara() {
		return focepara;
	}

	public void setFocepara(String focepara) {
		this.focepara = focepara;
	}

	public String getParatype() {
		return paratype;
	}

	public void setParatype(String paratype) {
		this.paratype = paratype;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodepara() {
		return codepara;
	}

	public void setCodepara(String codepara) {
		this.codepara = codepara;
	}

	public int getRankno() {
		return rankno;
	}

	public void setRankno(int rankno) {
		this.rankno = rankno;
	}

	
}
