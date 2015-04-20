package com.entity.stdapply;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.ftp.FtpStoreFile;

public class DocApplyStore {
	private String   StoreFileNo=""; //文件流水号
	private String   DocNo="";       //文档流水号
	private String   DocClass="";    //资源类
	private String   StoreDirURL="";
	private String   FileContentType="";
	private String   SourceFlag="";
	private String   Createdate="";
	private String   LastUpdatedate="";
	private String   Memo="";
	private String   FileName="";
	public DocApplyStore(){}
	public DocApplyStore(String StoreFileNo){
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from STD_DOCREVISEPATH where storefileno=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(StoreFileNo) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.StoreFileNo = StoreFileNo;
				this.Createdate = r.getString("Createdate");
				this.DocClass = r.getString("DocClass");
				this.DocNo = r.getString("DocNo");
				this.FileContentType = r.getString("FileContentType");
				this.FileName = r.getString("FileName");
				this.LastUpdatedate = r.getString("LastUpdatedate");
				this.Memo = r.getString("Memo");
				this.SourceFlag = r.getString("SourceFlag");
				this.StoreDirURL = r.getString("StoreDirURL");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public DataTable HaveFile(String docno) throws Exception{
	     DBObject db = new DBObject();
	     String sql = "select storefileno from STD_DOCREVISEPATH where docno = ? ";
	     Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	                                                          { new Parameter.String(docno)				
	                                                          };
	     DataTable dt = db.runSelectQuery(sql, pp);
	     return dt;
	}
	public boolean delete(String storefileno)
	{
		try{
			DBObject db = new DBObject();
			String sql = "delete from STD_DOCREVISEPATH where storefileno=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(storefileno) };
		
			
			if (db.run(sql,pp))
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(Exception e)
		{
			return false;
		}
	}
	public boolean Insert(FtpStoreFile docstorefile,String url)
	{
		try{
			
			DBObject db = new DBObject();
			String sql = "insert into STD_DOCREVISEPATH(storefileno,docno,docclass,storedirurl,filecontenttype,sourceflag,createdate,lastupdatedate,memo,filename) values (?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			   { 
			     new Parameter.String(docstorefile.getStorefileno()),
				 new Parameter.String(docstorefile.getDocno()),
			     new Parameter.String(docstorefile.getDocclass()),
			     new Parameter.String(url),
			    new Parameter.String(docstorefile.getFilecontenttype()),
			     new Parameter.String(docstorefile.getSourceflag()),
			       new Parameter.String((docstorefile.getCreatedate()).substring(0, 10)),
			 new Parameter.String((docstorefile.getLastupdatedate()).substring(0, 10)),
			      new Parameter.String(docstorefile.getMemo()),
			      new Parameter.String(docstorefile.getFilename())
			                                             				
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
	public boolean Insert()
	{
		try{
			
		DBObject db = new DBObject();
		String sql = "insert into STD_DOCREVISEPATH(storefileno,docno,docclass,storedirurl,filecontenttype,sourceflag,createdate,lastupdatedate,memo,filename) values (storefileno.nextVal,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?)";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		   { 
		     new Parameter.String(this.DocNo),
		     new Parameter.String(this.DocClass),
		     new Parameter.String(this.StoreDirURL),
		    new Parameter.String(this.FileContentType),
		     new Parameter.String(this.SourceFlag),
		       new Parameter.String(this.Createdate),
		 new Parameter.String(this.LastUpdatedate),
		      new Parameter.String(this.Memo),
		      new Parameter.String(this.FileName)
		                                             				
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
	public DataTable getAllFileList(String DocNo)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from STD_DOCREVISEPATH where docno ='"+DocNo+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getFileList(int pageno, int perpage,String DocNo)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||storefileno||'\">' as 选择,filename as 文件名,filecontenttype as 文档内容格式,to_char(createdate,'yyyy-MM-dd') as 创建日期,memo as 备注,'<a href=\"#\" onClick=dele(\"'||storefileno||'\") class=\"button4\">删除</a> <a href=\"#\" onClick=office(\"'||storefileno||'\",\"'||filecontenttype||'\") class=\"button4\">在线编辑</a>' as 操作   from STD_DOCREVISEPATH where docno ='"+DocNo+"' order by storefileno";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getFileListInit(int pageno, int perpage,String DocNo)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||storefileno||'\">' as 选择,filename as 文件名,filecontenttype as 文档内容格式,to_char(createdate,'yyyy-MM-dd') as 创建日期,memo as 备注,'<a href=\"#\" onClick=dele(\"'||storefileno||'\") class=\"button4\">删除</a>' as 操作    from STD_DOCREVISEPATH where docno ='"+DocNo+"'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public String getStoreFileNo() {
		return StoreFileNo;
	}
	public void setStoreFileNo(String storeFileNo) {
		StoreFileNo = storeFileNo;
	}
	public String getDocNo() {
		return DocNo;
	}
	public void setDocNo(String docNo) {
		DocNo = docNo;
	}
	public String getDocClass() {
		return DocClass;
	}
	public void setDocClass(String docClass) {
		DocClass = docClass;
	}
	public String getStoreDirURL() {
		return StoreDirURL;
	}
	public void setStoreDirURL(String storeDirURL) {
		StoreDirURL = storeDirURL;
	}
	public String getFileContentType() {
		return FileContentType;
	}
	public void setFileContentType(String fileContentType) {
		FileContentType = fileContentType;
	}
	public String getSourceFlag() {
		return SourceFlag;
	}
	public void setSourceFlag(String sourceFlag) {
		SourceFlag = sourceFlag;
	}
	public String getCreatedate() {
		return Createdate;
	}
	public void setCreatedate(String createdate) {
		Createdate = createdate;
	}
	public String getLastUpdatedate() {
		return LastUpdatedate;
	}
	public void setLastUpdatedate(String lastUpdatedate) {
		LastUpdatedate = lastUpdatedate;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}

}
