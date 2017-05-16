package com.entity.query;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.ExecSql;
import com.db.Parameter;
import com.db.SqlHelper;
import com.db.Parameter.Int;

public class QueryOutput {

	public int queryid=0; //��ѯID
	public String outcode="";//����ֶ���
	public String outname="";//����ֶ�������
	public String outalign="";//��ʾ���뷽ʽ
	public String formatpara="";//��ʽ������
	public int linkqueryid=0;//����ת��ѯID
	public String code="";//���ñ���
	public String istopiccol="";//�Ƿ�������
	public int outrank=0;//���˳��
	
	
	public QueryOutput(){
		
	}
	public QueryOutput(int queryid,String outcode){
		
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from query_output where queryid=? and outcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.Int(queryid), new Parameter.String(outcode) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.queryid=queryid;
				this.outcode=outcode;
			    this.outname=r.getString("outname");
			    this.outalign=r.getString("outalign");
			    this.formatpara=r.getString("formatpara");
			    this.linkqueryid=Integer.parseInt(r.getString("linkqueryid"));
			    this.code=r.getString("code");
			    this.istopiccol=r.getString("istopiccol");
			    this.outrank=Integer.parseInt(r.getString("outrank"));
			
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
			String sql = "insert into query_output (queryid,outcode,outname,outalign,formatpara,linkqueryid,code,istopiccol,outrank) values (?,?,?,?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					new Parameter.Int(this.queryid),
					new Parameter.String(this.outcode),
					new Parameter.String(this.outname),
					new Parameter.String(this.outalign),
					new Parameter.String(this.formatpara),
					new Parameter.Int(this.linkqueryid),
					new Parameter.String(this.code),
					new Parameter.String(this.istopiccol),
					new Parameter.Int(this.outrank) };
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
			String sql = "update query_output set outcode=?,outname=?,outalign=?,formatpara=?,linkqueryid=?,code=?,istopiccol=?,outrank=? where queryid=? and outcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{
					
					
					new Parameter.String(this.outcode),
					new Parameter.String(this.outname),
					new Parameter.String(this.outalign),
					new Parameter.String(this.formatpara),
					new Parameter.Int(this.linkqueryid),
					new Parameter.String(this.code),
					new Parameter.String(this.istopiccol),
					new Parameter.Int(this.outrank),
					new Parameter.Int(this.queryid),
					new Parameter.String(outcode)
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
			String sql = "delete from query_output where queryid=? and outcode=?";
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
	
	public DataTable getQueryOutputlist(int queryid,int pageno,int perpage){
		
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select 'ѡ��' as ѡ��,outcode,outcode as �������,outname as ��������ֶ���,outalign as ��ʾ���뷽ʽ,formatpara as ��ʽ������,linkqueryid as ����ת��ѯID,code as ���ñ���,istopiccol as �Ƿ�������,outrank as ���˳��, '����' as ����  from query_output where queryid="+queryid;
			
			
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
	public DataTable getAllQueryOutputlist(int queryid){
		
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from query_output where queryid="+queryid);
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public int getQueryid() {
		return queryid;
	}
	public void setQueryid(int queryid) {
		this.queryid = queryid;
	}
	public String getOutcode() {
		return outcode;
	}
	public void setOutcode(String outcode) {
		this.outcode = outcode;
	}
	public String getOutname() {
		return outname;
	}
	public void setOutname(String outname) {
		this.outname = outname;
	}
	public String getOutalign() {
		return outalign;
	}
	public void setOutalign(String outalign) {
		this.outalign = outalign;
	}
	public String getFormatpara() {
		return formatpara;
	}
	public void setFormatpara(String formatpara) {
		this.formatpara = formatpara;
	}
	public int getLinkqueryid() {
		return linkqueryid;
	}
	public void setLinkqueryid(int linkqueryid) {
		this.linkqueryid = linkqueryid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIstopiccol() {
		return istopiccol;
	}
	public void setIstopiccol(String istopiccol) {
		this.istopiccol = istopiccol;
	}
	public int getOutrank() {
		return outrank;
	}
	public void setOutrank(int outrank) {
		this.outrank = outrank;
	}
	
	
	
	
}
