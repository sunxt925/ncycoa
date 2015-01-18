package com.entity.query;

import java.io.Reader;
import java.sql.Clob;

import com.common.ClobTransfer;
import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;
import com.db.ExecSql;
import com.db.Parameter;
import com.db.SqlHelper;
public class QueryBaseinfo {

	public String queryid="";  //��ѯid
	public String group_code=""; //��������
	public String queryname="";//��ѯ����
	public String querydes="";//��ѯ����
	public Clob querysql=null;//��ѯsql
	public String ispage="";//�Ƿ��ҳ��ʾ
	
	public String pagerows="";//��ҳ��ʾ����
	public String isrownum="";//�Ƿ���ʾ�к�
	public String isbasequery="";//�Ƿ������ѯ
	public String staticcolumn="";//�̶���ʾ����
	
	public String newqueryid="";
	ClobTransfer ct=new ClobTransfer();
	public QueryBaseinfo(){
		
	}
    public QueryBaseinfo(String queryid){
    	try {
			
	
    	DBObject db = new DBObject();
    	DBObject db2 = new DBObject();
		String sql = "select * from query_baseinfo where queryid=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		{ new Parameter.Int(Integer.parseInt(queryid)) };
		DataTable dt = db.runSelectQuery(sql, pp);
		
		if(dt!=null&&dt.getRowsCount()==1){
			this.queryid=queryid;
			this.group_code=dt.get(0).getString("group_code");
			this.queryname=dt.get(0).getString("queryname");
			this.querydes=dt.get(0).getString("querydes");
			this.querysql=(Clob)dt.get(0).get("querysql");
			//this.querysql=db2.getClob("query_baseinfo", "querysql", "queryid="+queryid);
			this.ispage=dt.get(0).getString("ispage");
			this.pagerows=dt.get(0).getString("pagerows");
			this.isrownum=dt.get(0).getString("isrownum");
			this.isbasequery=dt.get(0).getString("isbasequery");
			this.staticcolumn=dt.get(0).getString("staticcolumn");
			
		}
		
    	} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * ����
	 * @return
	 */
    
    public String ClobToStr(Clob c){
    	String res="";
    	try {
			
		
    	Reader inStream = c.getCharacterStream();
		char[] c1 = new char[(int) c.length()];
		inStream.read(c1);
		// data�Ƕ�������Ҫ���ص����ݣ�������String
		res = new String(c1);
		inStream.close();
    	} catch (Exception e) {
			// TODO: handle exception
		}
         return res;
    }
	public boolean insert(){
		
		try {
		DBObject db = new DBObject();
		String sql = "insert into query_baseinfo (queryid,group_code,queryname,querydes,querysql,ispage,pagerowa,isrownum,isbasequery,staticcolumn) values (?,?,?,?,?,?,?,?,?,?)";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		{ new Parameter.String(this.queryid),
				new Parameter.String(this.group_code),
				new Parameter.String(this.queryname),
				new Parameter.String(this.querydes),
				new Parameter.String(ClobToStr(this.querysql)),
				new Parameter.String(this.ispage),
				new Parameter.String(this.pagerows),
				new Parameter.String(this.isrownum),
				new Parameter.String(this.isbasequery),
				
				new Parameter.String(this.staticcolumn) };
		
			if(db.run(sql,pp)){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
		
	}
	/**
	 * ����
	 * @return
	 */
	public boolean update(){
		
		try
		{
			DBObject db = new DBObject();
			String sql = "update query_baseinfo set queryid=?,group_code=?��,queryname=?,querydes=?,querysql=?,ispage=?,pagerowa=?,isrownum=?,isbasequery=?,staticcolumn=? where queryid=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.newqueryid),
					
					new Parameter.String(this.group_code),
					new Parameter.String(this.queryname),
					new Parameter.String(this.querydes),
					new Parameter.String(ClobToStr(this.querysql)),
					new Parameter.String(this.ispage),
					new Parameter.String(this.pagerows),
					new Parameter.String(this.isrownum),
					new Parameter.String(this.isbasequery),
					
					new Parameter.String(this.staticcolumn),
					new Parameter.String(this.staticcolumn),
					new Parameter.String(this.queryid)		
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
	 * ɾ��
	 * @param param
	 * @return
	 */
    public boolean delete(String param){
    	
    	ExecSql comm = null;
		try
		{
			String sql = "delete from query_baseinfo where queryid=?";
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
    /**
     * ��ȡ������Ϣ
     * @param group_code
     * @return
     */
    public DataTable getQueryGrouplist(String group_code,int pageno,int perpage){
    	
    	try {
			DBObject db=new DBObject();
			if(group_code.equals(""))
			{
				
			
			//String base_sql = "select  'ѡ��' as ѡ�� ,queryid,queryid as ��ѯID,group_code, queryname as ��ѯ���� ,querydes as ��ѯ����, querysql as ��ѯ���,ispage as �Ƿ��ҳ��ʾ,pagerows as ��ҳ��ʾ����,isrownum as �Ƿ���ʾ�к�, isbasequery as �Ƿ������ѯ,staticcolumn as �̶���ʾ���� , '����' as ���� from (select * from query_baseinfo) order by queryid";
			String base_sql = "select  'ѡ��' as ѡ�� ,queryid,queryid as ��ѯID,queryname as ��ѯ���� ,querydes as ��ѯ����, ispage as �Ƿ��ҳ��ʾ,pagerows as ��ҳ��ʾ����,isrownum as �Ƿ���ʾ�к�, isbasequery as �Ƿ������ѯ,staticcolumn as �̶���ʾ���� , '����' as ���� from (select * from query_baseinfo) order by queryid";
			
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
		
			return db.runSelectQuery(sql_run);}
			else{
				String base_sql = "select  'ѡ��' as ѡ�� ,queryid,queryid as ��ѯID,queryname as ��ѯ���� ,querydes as ��ѯ����, ispage as �Ƿ��ҳ��ʾ,pagerows as ��ҳ��ʾ����,isrownum as �Ƿ���ʾ�к�, isbasequery as �Ƿ������ѯ,staticcolumn as �̶���ʾ���� , '����' as ���� from (select * from query_baseinfo where group_code='"
					+ group_code + "')"+"order by queryid";
				String sql_run = Format.getFySql(base_sql, pageno, perpage);
				return db.runSelectQuery(sql_run);}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
    }
  
    public DataTable getAllQueryGrouplist(String group_code)
	{
		try
		{
			DBObject db = new DBObject();
			if(group_code.equals("")){
			
			DataTable dt = db.runSelectQuery("select * from query_baseinfo");
			return dt;	
			}else {
				DataTable dt = db.runSelectQuery("select * from query_baseinfo where group_code="+group_code);
				return dt;	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public String getQueryid() {
		return queryid;
	}
	public void setQueryid(String queryid) {
		this.queryid = queryid;
	}
	public String getGroup_code() {
		return group_code;
	}
	public void setGroup_code(String groupCode) {
		group_code = groupCode;
	}
	public String getQueryname() {
		return queryname;
	}
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	public String getQuerydes() {
		return querydes;
	}
	public void setQuerydes(String querydes) {
		this.querydes = querydes;
	}
	public Clob getQuerysql() {
		return querysql;
	}
	public void setQuerysql(Clob querysql) {
		this.querysql = querysql;
	}
	public String getIspage() {
		return ispage;
	}
	public void setIspage(String ispage) {
		this.ispage = ispage;
	}
	public String getPagerows() {
		return pagerows;
	}
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	public String getIsrownum() {
		return isrownum;
	}
	public void setIsrownum(String isrownum) {
		this.isrownum = isrownum;
	}
	public String getIsbasequery() {
		return isbasequery;
	}
	public void setIsbasequery(String isbasequery) {
		this.isbasequery = isbasequery;
	}
	public String getStaticcolumn() {
		return staticcolumn;
	}
	public void setStaticcolumn(String staticcolumn) {
		this.staticcolumn = staticcolumn;
	}


	public String getNewqueryid() {
		return newqueryid;
	}


	public void setNewqueryid(String newqueryid) {
		this.newqueryid = newqueryid;
	}
	
	
}
