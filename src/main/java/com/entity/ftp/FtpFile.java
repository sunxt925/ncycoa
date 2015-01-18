package com.entity.ftp;

import java.util.*;

import com.common.Format;
import com.db.*;

/**
 * @author ��ï
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class FtpFile
{
	private String id = "";// �ļ���ˮ�ţ�������FTP�������ϵ����֣�

	private String filename = "";// �ļ������û��ɼ���

	private String path = "";//  �ļ�·����FTP��

	private String contenttpye = "";//�ļ�ͷ����ʾ�ļ����ͣ�
	
	private String code = "";   //ϵͳ����
	

	public FtpFile()
	{

	}

	public FtpFile(String id)
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "select * from SYSTEM_FTP where file_id=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(id) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.id = id;
				this.filename =  r.getString("file_name");
				this.path = r.getString("file_url");
				this.contenttpye = r.getString("file_type");
			
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
			String sql = "insert into SYSTEM_FTP(file_id,file_name,file_url,file_type) values (?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.id), new Parameter.String(this.filename),
					new Parameter.String(this.path),
					new Parameter.String(this.contenttpye)
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
	
	
	public boolean delete(String id)
	{
		try{
			DBObject db = new DBObject();
			String sql = "delete from SYSTEM_FTP where file_id=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(id) };
		
			
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContenttpye() {
		return contenttpye;
	}

	public void setContenttpye(String contenttpye) {
		this.contenttpye = contenttpye;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

	



	
	
}