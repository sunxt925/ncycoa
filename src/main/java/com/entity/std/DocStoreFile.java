package com.entity.std;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class DocStoreFile {
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
	public DocStoreFile(){}
	public DocStoreFile(String StoreFileNo){
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from system_docstorefile where storefileno=?";
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
	public String  TranslateLink(String docno) throws Exception{
		 String res="";
	     DBObject db = new DBObject();
	     String sql = "select storefileno,filecontenttype from system_docstorefile where docno = ? ";
	     Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	                                                          { new Parameter.String(docno)				
	                                                          };
	     DataTable dt = db.runSelectQuery(sql, pp);
	     if(dt.getRowsCount()==0){
	    	 sql = "select storefileno,filecontenttype from STD_DOCREVISEPATH where docno = ? ";
	    	 dt = db.runSelectQuery(sql, pp);
	     }
	     for(int i=0;i<dt.getRowsCount();i++){
	    	 String fileno=dt.get(i).get(0).toString();
	    	 String contenttype=dt.get(i).get(1).toString();
	    	 //\""+fileno+"\",\""+contenttype+"\"
	    	 res=res+"<a href=\"javascript:window.OpenFile('"+fileno+"','"+contenttype+"')\" class=\"button4\">"+contenttype+"</a>"+"&nbsp";
	     }
		return res;
	}
	public DataTable HaveFile(String docno) throws Exception{
	     DBObject db = new DBObject();
	     String sql = "select storefileno from system_docstorefile where docno = ? ";
	     Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	                                                          { new Parameter.String(docno)				
	                                                          };
	     DataTable dt = db.runSelectQuery(sql, pp);
	     return dt;
	}
	public DataTable getAllFileList(String DocNo)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from system_docstorefile where docno ='"+DocNo+"'");
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

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||storefileno||'\">' as 选择,filename as 文件名,filecontenttype as 文档内容格式,createdate as 创建日期,lastupdatedate as 最近修改日期,memo as 备注,'<a href=\"#\" onClick=office(\"'||storefileno||'\",\"'||filecontenttype||'\") class=\"button4\">阅读</a>' as 操作   from system_docstorefile where docno ='"+DocNo+"' order by storefileno";

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

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||storefileno||'\">' as 选择,filename as 文件名,filecontenttype as 文档内容格式,to_char(createdate,'yyyy-MM-dd')  as 创建日期,memo as 备注,'<a href=\"#\" onClick=dele(\"'||storefileno||'\") class=\"button4\">删除</a>' as 操作    from system_docstorefile where docno ='"+DocNo+"' order by storefileno";

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
