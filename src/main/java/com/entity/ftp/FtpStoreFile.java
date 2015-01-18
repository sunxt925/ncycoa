package com.entity.ftp;

import java.util.*;

import com.common.Format;
import com.db.*;

/**
 * @author ��ï
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class FtpStoreFile
{
	private String storefileno = "";// �ļ���ˮ�ţ�������FTP�������ϵ����֣�
	
	private String docno = "";  //�ĵ���ˮ��
	
	private String docclass = ""; //�ļ���Դ��

	private String filename = "";// �ļ������û��ɼ���

	private String storedirurl = "";//  �ļ�·����FTP��

	private String filecontenttype = "";//�ļ�ͷ����ʾ�ļ����ͣ�
	
	private String sourceflag = ""; //��ʽ˵��

	private String createdate = "";  //��������
	
	private String lastupdatedate = ""; //�޸�����
	
	private String memo = "";  //��ע
	
	
	

	public String getStorefileno() {
		return storefileno;
	}

	public void setStorefileno(String storefileno) {
		this.storefileno = storefileno;
	}



	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public String getDocclass() {
		return docclass;
	}

	public void setDocclass(String docclass) {
		this.docclass = docclass;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getStoredirurl() {
		return storedirurl;
	}

	public void setStoredirurl(String storedirurl) {
		this.storedirurl = storedirurl;
	}

	public String getFilecontenttype() {
		return filecontenttype;
	}

	public void setFilecontenttpye(String filecontenttype) {
		this.filecontenttype = filecontenttype;
	}

	public String getSourceflag() {
		return sourceflag;
	}

	public void setSourceflag(String sourceflag) {
		this.sourceflag = sourceflag;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getLastupdatedate() {
		return lastupdatedate;
	}

	public void setLastupdatedate(String lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public FtpStoreFile()
	{

	}

	public FtpStoreFile(String storefileno)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_DOCSTOREFILE where storefileno=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(storefileno) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.storefileno = storefileno;
				this.filename =  r.getString("filename");
				this.docno = r.getString("docno");
				this.docclass = r.getString("docclass");
				this.filecontenttype = r.getString("filecontenttype");
				this.sourceflag = r.getString("sourceflag");
				this.storedirurl = r.getString("storedirurl");
				this.memo = r.getString("memo");
				this.createdate = r.getString("createdate");
				this.lastupdatedate = r.getString("lastupdatedate");
			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ����
	 * 
	 * @return
	 */
	public boolean Insert()
	{
		try
		{
		
			DBObject db = new DBObject();
			String sql = "insert into SYSTEM_DOCSTOREFILE(storefileno,docno,docclass,storedirurl,filecontenttype,sourceflag,createdate,lastupdatedate,memo,filename) values (?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.storefileno),
					new Parameter.String(this.docno),
					new Parameter.String(this.docclass),
					new Parameter.String(this.storedirurl),
					new Parameter.String(this.filecontenttype),
					new Parameter.String(this.sourceflag),
					new Parameter.String(this.createdate),
					new Parameter.String(this.lastupdatedate),
					new Parameter.String(this.memo),
					new Parameter.String(this.filename)
				
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
	
	
	public boolean delete(String storefileno)
	{
		try{
			DBObject db = new DBObject();
			String sql = "delete from SYSTEM_DOCSTOREFILE where storefileno=?";
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


	

	



	
	
}