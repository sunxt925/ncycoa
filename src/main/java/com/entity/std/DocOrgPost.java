package com.entity.std;

import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class DocOrgPost {
    private int recid=0;
	private String PositionCode="";
    private String DocCode="";
    private String OrgCode="";
    private String Relation="";
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public String getPositionCode() {
		return PositionCode;
	}
	public void setPositionCode(String positionCode) {
		PositionCode = positionCode;
	}
	public String getDocCode() {
		return DocCode;
	}
	public void setDocCode(String docCode) {
		DocCode = docCode;
	}
	public String getOrgCode() {
		return OrgCode;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	public String getRelation() {
		return Relation;
	}
	public void setRelation(String relation) {
		Relation = relation;
	}
	
	/*增加
	 * 不知recid.nextVal正确否
	 * */
	public boolean insert(){
		try
		{
			DBObject db = new DBObject();
			String sql = "insert into std_org_post(recid,positioncode,doccode,orgcode,relation) values(recid.nextVal,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.PositionCode) ,new Parameter.String(this.DocCode), new Parameter.String(this.OrgCode),new Parameter.String(this.Relation) };// new Parameter.Int(this.roleid)
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
	 * 更新std_docmetainfo
	 * 
	 * @return
	 */
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update std_org_post set positioncode=?,doccode=?,orgcode=?,relation=? where recid=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{new Parameter.String(this.PositionCode), new Parameter.String(this.DocCode),new Parameter.String(this.OrgCode),new Parameter.String(this.Relation),new Parameter.Int(this.recid)};
		
			if (db.run(sql,pp))
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
	public boolean Delete(String recid,String myorgcode)
	{
		try
		{	boolean flag=false;
			Vector v = new Vector();
			DBObject db = new DBObject();

			if (recid.indexOf(",") == -1)
			{
				DataTable  dt = db.runSelectQuery("select orgcode,doccode from std_org_post where recid = '"+ recid+"'");
				String orgcode=dt.get(0).get(0).toString();
				String doccode=dt.get(0).get(1).toString();
				v.add("delete from std_org_post where recid = '" + recid
						+ "'");
				if (db.executeBatch(v))
				{
					flag=true;
				}
				if(flag){
	
					DataTable  dt2 = db.runSelectQuery("select * from std_org_post where doccode = '"+ doccode+"' and orgcode= '"+orgcode+"'");
					if(dt2.getRowsCount()==0&&(!orgcode.equals(myorgcode))){
						String sql="delete from std_docorg where orgcode = '" +orgcode+"' and doccode='"+doccode+"'";
						if (db.run(sql))
						{
							flag=true;
						}
					}
				}
			}
			else
			{
				String[] bm = recid.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					DataTable  dt = db.runSelectQuery("select orgcode,doccode from std_org_post where recid = '"+ bm[i]+"'");
					String orgcode=dt.get(0).get(0).toString();
					String doccode=dt.get(0).get(1).toString();
					v.add("delete from std_org_post where recid = '" +bm[i]
							+ "'");
					if (db.executeBatch(v))
					{
						flag=true;
					}
					if(flag){

						DataTable  dt2 = db.runSelectQuery("select * from std_org_post where doccode = '"+ doccode+"' and orgcode= '"+orgcode+"'");
						if(dt2.getRowsCount()==0&&(!orgcode.equals(myorgcode))){
							String sql="delete from std_docorg where orgcode = '" +orgcode+"' and doccode='"+doccode+"'";
							if (db.run(sql))
							{
								flag=true;
							}
						}
					}
				}
			}


			return flag;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据docCode删除std_docorg中的数据
	 * 
	 * @param 
	 * @return
	 */
	public boolean DeleteByDocCode(String docCode)
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql="delete from std_org_post where doccode like '" +docCode+"'";
			if (db.run(sql))
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
	 * 根据orgCode删除std_docorg中的数据
	 * 
	 * @param 
	 * @return
	 */
	public boolean DeleteByOrgCode(String orgCode)
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql="delete from std_org_post where orgcode like " +orgCode;
			if (db.run(sql))
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
	
	//根据positionCode 查询标准编码
	public DataTable getMetaCodeByPosCode(String positionCode)
	{
		try {
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select doccode from std_org_post where positioncode ='"+positionCode+"'");
			return dt;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getpostByOrgdocCode(String orgcode , String doccode)
	{
		try {
			DBObject db = new DBObject();
			DataTable  dt = db.runSelectQuery("select positioncode from std_org_post where doccode = '"+ doccode +"' and orgcode = '" + orgcode + "'");
			return dt;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllStdPostList(String orgcode,String doccode)
	{
		try
		{
			DBObject db = new DBObject();
			//DataTable dt = db.runSelectQuery("select positioncode , positionname from base_position where positioncode in ( select positioncode from std_org_post where doccode = '"+ doccode +"' and orgcode = '" + orgcode + "' )" );
			DataTable dt = db.runSelectQuery(" select std_org_post.recid,base_org.orgcode, base_org.orgname,std_org_post.positioncode, base_position.positionname from base_org,base_position, std_org_post where base_position.positioncode=std_org_post.positioncode and base_org.orgcode=std_org_post.orgcode and std_org_post.doccode = '"+ doccode + "'" );
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getStdPostList(int pageno, int perpage,String orgcode,String doccode)
	{
		try
		{
			DBObject db = new DBObject();

			//String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||std_org_post.recid||'\">' as 选择,std_org_post.positioncode as 岗位编码 , base_position.positionname 岗位名称,'<a href=\"#\" onClick=dele(\"'||std_org_post.recid||'\") class=\"button4\">删除</a>' as 操作  from base_position FULL JOIN std_org_post on base_position.positioncode=std_org_post.positioncode  where std_org_post.doccode = '" + doccode + "' and std_org_post.orgcode = '" + orgcode + "'";
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||std_org_post.recid||'\">' as 选择,base_org.orgcode as 机构编码, base_org.orgname as 机构名称 ,std_org_post.positioncode as 岗位编码 , base_position.positionname as 岗位名称  from base_org,base_position, std_org_post where base_position.positioncode=std_org_post.positioncode and base_org.orgcode=std_org_post.orgcode and  std_org_post.doccode = '" + doccode +  "' order by std_org_post.positioncode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getStdPostListsearch(int pageno, int perpage,String orgcode,String doccode)
	{
		try
		{
			DBObject db = new DBObject();

			//String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||std_org_post.recid||'\">' as 选择,std_org_post.positioncode as 岗位编码 , base_position.positionname 岗位名称,'<a href=\"#\" onClick=dele(\"'||std_org_post.recid||'\") class=\"button4\">删除</a>' as 操作  from base_position FULL JOIN std_org_post on base_position.positioncode=std_org_post.positioncode  where std_org_post.doccode = '" + doccode + "' and std_org_post.orgcode = '" + orgcode + "'";
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||std_org_post.recid||'\">' as 选择,std_org_post.positioncode as 岗位编码 , base_position.positionname as 岗位名称  from base_position FULL JOIN std_org_post on base_position.positioncode=std_org_post.positioncode  where std_org_post.doccode = '" + doccode +  "' order by std_org_post.positioncode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllStdList(String orgcode,String positioncode,String begin,String end,String docname,String doccode,String drawupperson,String gettype)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="";
			String searchdoccode="";
			String condition="";
			String nameselect="";
			String drawupdate="";
			String personcondition="";
			String getstdtypecondition="";
			if(doccode.equals("")){
				searchdoccode="";
			}else {
				searchdoccode=" and doccode like '%"+doccode+"%' ";
			}
			if((begin.equals(""))&&(end.equals(""))){
				drawupdate="";
			}else if((!begin.equals(""))&&(!end.equals(""))){
				drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
			}else if((!begin.equals(""))&&(end.equals(""))){
				drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') ";
			}else if((begin.equals(""))&&(!end.equals(""))){
				drawupdate=" and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
			}
			if(!docname.equals("")){//'<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,
				nameselect=" and DocVersionName like '%"+docname+"%' ";
			}
			if(!drawupperson.equals("")){
				personcondition=" and drawupperson like '%"+drawupperson+"%' ";
			}
			if(!gettype.equals("")){
				if(gettype.equals("gl")){
					getstdtypecondition=" and doccode like 'Q/NCYC.GL%' ";
				}else if(gettype.equals("gz")){
					getstdtypecondition=" and doccode like 'Q/NCYC.GZ%' ";
				}else if(gettype.equals("js")){
					getstdtypecondition=" and doccode like 'Q/NCYC.JS%' ";
				}
					
			}
			condition=condition+nameselect+getstdtypecondition+searchdoccode+drawupdate+personcondition;
			sql = "select * from std_docmetaversioninfo where doccode in (select doccode from std_org_post where positioncode='"+positioncode+"')"+" and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+condition+" order by DRAWUPDATE desc";
			DataTable dt = db.runSelectQuery(sql);
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getStdList(int pageno, int perpage,String orgcode,String positioncode,String begin,String end,String docname,String doccode,String sorttype,String drawupperson,String gettype)
	{
		try
		{
			DBObject db = new DBObject();
			String condition="";
			String base_sql="";
			String searchdoccode="";
			String sort="";
			String nameselect="";
			String drawupdate="";
			String personcondition="";
			String getstdtypecondition="";
			if(doccode.equals("")){
				searchdoccode="";
			}else {
				searchdoccode=" and doccode like '%"+doccode+"%' ";
			}
			if(sorttype.equals("code")){
				sort=" order by doccode asc";
			}else{
				sort=" order by drawupdate desc";
			}
			if((begin.equals(""))&&(end.equals(""))){
				drawupdate="";
			}else if((!begin.equals(""))&&(!end.equals(""))){
				drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
			}else if((!begin.equals(""))&&(end.equals(""))){
				drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') ";
			}else if((begin.equals(""))&&(!end.equals(""))){
				drawupdate=" and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
			}
			if(!docname.equals("")){//'<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,
				nameselect=" and DocVersionName like '%"+docname+"%' ";
			}
			if(!drawupperson.equals("")){
				personcondition=" and drawupperson like '%"+drawupperson+"%' ";
			}
			if(!gettype.equals("")){
				if(gettype.equals("gl")){
					getstdtypecondition=" and doccode like 'Q/NCYC.GL%' ";
				}else if(gettype.equals("gz")){
					getstdtypecondition=" and doccode like 'Q/NCYC.GZ%' ";
				}else if(gettype.equals("js")){
					getstdtypecondition=" and doccode like 'Q/NCYC.JS%' ";
				}
					
			}//'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=F7(\"'||docno||'\",\"'||DocVersionName||'\") class=\"button4\">附件查看</a> <a href=\"#\" onClick=F8(\"'||docno||'\",\"'||docversionname||'\",\""+orgcode+"\") class=\"button4\">涉及岗位</a>' as 操作 
			condition=condition+getstdtypecondition+nameselect+searchdoccode+drawupdate+personcondition+sort;
			base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,docno as 有无附件,docno as 标准正文   from std_docmetaversioninfo where doccode in (select doccode from std_org_post where positioncode='"+positioncode+"')"+" and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+condition;
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllStdByClass(String begin,String end,String docname,String doccode,String drawupperson,String gettype)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="";
			String searchdoccode="";
			String condition="";
			String nameselect="";
			String drawupdate="";
			String personcondition="";
			String getstdtypecondition="";
			if(doccode.equals("")){
				searchdoccode="";
			}else {
				searchdoccode=" and doccode like '%"+doccode+"%' ";
			}
			if((begin.equals(""))&&(end.equals(""))){
				drawupdate="";
			}else if((!begin.equals(""))&&(!end.equals(""))){
				drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
			}else if((!begin.equals(""))&&(end.equals(""))){
				drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') ";
			}else if((begin.equals(""))&&(!end.equals(""))){
				drawupdate=" and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
			}
			if(!docname.equals("")){//'<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,
				nameselect=" and DocVersionName like '%"+docname+"%' ";
			}
			if(!drawupperson.equals("")){
				personcondition=" and drawupperson like '%"+drawupperson+"%' ";
			}
			if(!gettype.equals("")){
				if(gettype.equals("gl")){
					getstdtypecondition=" and doccode like 'Q/NCYC.GL%' ";
				}else if(gettype.equals("gz")){
					getstdtypecondition=" and doccode like 'Q/NCYC.GZ%' ";
				}else if(gettype.equals("js")){
					getstdtypecondition=" and doccode like 'Q/NCYC.JS%' ";
				}
					
			}
			condition=condition+nameselect+getstdtypecondition+searchdoccode+drawupdate+personcondition;
			sql = "select * from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+condition+" order by DRAWUPDATE desc";
			DataTable dt = db.runSelectQuery(sql);
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getStdByClass(int pageno, int perpage,String begin,String end,String docname,String doccode,String sorttype,String drawupperson,String gettype)
	{
		try
		{
			DBObject db = new DBObject();
			String condition="";
			String base_sql="";
			String searchdoccode="";
			String sort="";
			String nameselect="";
			String drawupdate="";
			String personcondition="";
			String getstdtypecondition="";
			if(doccode.equals("")){
				searchdoccode="";
			}else {
				searchdoccode=" and doccode like '%"+doccode+"%' ";
			}
			if(sorttype.equals("code")){
				sort=" order by doccode asc";
			}else{
				sort=" order by drawupdate desc";
			}
			if((begin.equals(""))&&(end.equals(""))){
				drawupdate="";
			}else if((!begin.equals(""))&&(!end.equals(""))){
				drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
			}else if((!begin.equals(""))&&(end.equals(""))){
				drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') ";
			}else if((begin.equals(""))&&(!end.equals(""))){
				drawupdate=" and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
			}
			if(!docname.equals("")){//'<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,
				nameselect=" and DocVersionName like '%"+docname+"%' ";
			}
			if(!drawupperson.equals("")){
				personcondition=" and drawupperson like '%"+drawupperson+"%' ";
			}
			if(!gettype.equals("")){
				if(gettype.equals("gl")){
					getstdtypecondition=" and doccode like 'Q/NCYC.GL%' ";
				}else if(gettype.equals("gz")){
					getstdtypecondition=" and doccode like 'Q/NCYC.GZ%' ";
				}else if(gettype.equals("js")){
					getstdtypecondition=" and doccode like 'Q/NCYC.JS%' ";
				}
					
			}//'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=F7(\"'||docno||'\",\"'||DocVersionName||'\") class=\"button4\">附件查看</a> <a href=\"#\" onClick=F8(\"'||docno||'\",\"'||docversionname||'\",\""+orgcode+"\") class=\"button4\">涉及岗位</a>' as 操作 
			condition=condition+getstdtypecondition+nameselect+searchdoccode+drawupdate+personcondition+sort;
			base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,docno as 有无附件,docno as 标准正文   from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+condition;
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAllStdModList(String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="";
				sql="select * from std_docmetaversioninfo where drawuporg='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除'" ;
			DataTable dt = db.runSelectQuery(sql);
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getStdModList(int pageno, int perpage,String orgcode,String type)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,doccode as 标准编号,DocVersionName as 标准名称,drawupdate as 编制日期,doccode as 有无附件  from std_docmetaversioninfo where drawuporg='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";
			//String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,docno as 文档流水号,DocVersionName as 文档名称,docversion as 文档版本,docclassname as 文档类名称,doccontenttype as 文档内容类别  from std_docmetaversioninfo where doccode in (select doccode from std_org_post where orgcode='"+orgcode+"') and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";
			//String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,docno as 文档流水号,DocVersionName as 文档名称,docversion as 文档版本,docclassname as 文档类名称,doccontenttype as 文档内容类别,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">申请"+type+"标准</a>' as 操作 from std_docmetaversioninfo where doccode in (select doccode from std_org_post where orgcode='"+orgcode+"' and positioncode='"+positioncode+"')"+" and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
