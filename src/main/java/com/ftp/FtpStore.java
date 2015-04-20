package com.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.common.Format;
import com.common.Util;
import com.db.DBObject;
import com.db.Parameter;
import com.entity.ftp.FtpFile;
import com.entity.ftp.FtpStoreFile;

public class FtpStore
{
	public static String ip;
	private static String username;
	private static String passwd;
	private java.util.Properties m_ftpProperties;
	private FTPClient ftp;

	public boolean FtpConnet() // ����FTP����
	{
		boolean success = false;
		ftp = new FTPClient();

		try
		{
			int reply;
			ftp.setControlEncoding("GBK"); // �����˱���
			LoadConfig("ftp.properties");
			// ����FTP������

			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftp.connect(ip, 21);
			// ��¼ftp
			ftp.login(username, passwd);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

			// �����ص�ֵ�ǲ���230������ǣ���ʾ��½�ɹ�
			reply = ftp.getReplyCode();
			// ��2��ͷ�ķ���ֵ�ͻ�Ϊ��
			if (!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				return success;
			}

			success = true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return success;

	}

	public String FtpUpload(FtpStoreFile file, InputStream input)
	{

		if (FtpConnet())
		{
			boolean success = false;

			try
			{

				boolean flag = false; // /��ʾ�Ƿ����½�һ���ļ�
				
				
				String dir = Util.getSeqcode(file.getDocclass());

				// ת��ָ���ϴ�Ŀ¼
				ftp.changeWorkingDirectory(file.getStoredirurl());
				

				if (file.getStorefileno().equals(""))
				{
					ftp.changeWorkingDirectory(dir);
					String id = Util.getSequence(file.getDocclass());
					file.setStorefileno(id);
					flag = true;
				}
				else    //�޸��ļ�����
				{
					ftp.deleteFile(file.getStorefileno());
					ftp.changeWorkingDirectory(file.getStoredirurl());
					
					ftp.storeFile(file.getStorefileno(), input);
					// �ر�������
					input.close();
					// �˳�ftp
					ftp.logout();
					
					return file.getStorefileno();
					
				}
				// ��ȡ��ǰʱ����
				Calendar c = Calendar.getInstance();
				String year = "" + c.get(c.YEAR);
				String month = "" + (c.get(c.MONTH) + 1);
				String day = "" + c.get(c.DATE);
				String date = month + "-" + day;
				FTPFile[] fs = ftp.listFiles();
				String path = null;
				String separator = java.io.File.separator;

				boolean Yexist = false;
				// ���������ļ����ҵ�ָ�����ļ�
				for (FTPFile ff : fs)
				{

					if (ff.getName().equals(year))
					{
						Yexist = true;
						boolean Dexist = false;

						ftp.changeWorkingDirectory(year);
						FTPFile[] fs0 = ftp.listFiles();
						for (FTPFile ff0 : fs0)
						{

							if (ff0.getName().equals(date))
							{

								ftp.changeWorkingDirectory(date);
								Dexist = true;
							}

						}
						if (!Dexist)
						{
							addDateRemote(year, date);
							ftp.changeWorkingDirectory(date);
						}
					}

				}
				if (!Yexist)
				{
					addDateRemote(dir, year);
					addDateRemote(year, date);

					ftp.changeWorkingDirectory(date);
				}

				// ���ϴ��ļ��洢��ָ��Ŀ¼
				file.setStoredirurl(dir + "/" + year + "/" + date);
				ftp.storeFile(file.getStorefileno(), input);
				// �ر�������
				input.close();
				// �˳�ftp
				ftp.logout();
				// ��ʾ�ϴ��ɹ�
				success = true;

				if (flag)
					file.Insert();

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (ftp.isConnected())
				{
					try
					{
						ftp.disconnect();
					}
					catch (IOException ioe)
					{
					}
				}
			}
			return file.getStorefileno();
		}
		return null;
	}

	public String FtpDownload(FtpStoreFile file, String localPath)

	{
		if (FtpConnet())
		{

			try
			{

				// FtpFile file = new FtpFile(id);

				// ת��ָ������Ŀ¼

				ftp.changeWorkingDirectory(file.getStoredirurl());

				FTPFile[] fs = ftp.listFiles();
				// ���������ļ����ҵ�ָ�����ļ�
				for (FTPFile ff : fs)
				{
					if (ff.getName().equals(file.getStorefileno()))
					{

						Random random = new Random(System.currentTimeMillis());

						String ext = "";
						if (file.getFilecontenttype().equals("doc"))
							ext = ".doc";
						else if (file.getFilecontenttype().equals("xls"))
							ext = ".xls";
						else if (file.getFilecontenttype().equals("ppt"))
							ext = ".ppt";
						else if (file.getFilecontenttype().equals("pdf"))
							ext = ".pdf";
						else if (file.getFilecontenttype().equals("vsd"))
							ext = ".vsd";
						else if (file.getFilecontenttype().equals("swf"))
							ext = ".swf";

						String random0 = "" + random.nextInt();
						String filename = localPath + "/" + file.getStorefileno()
								+ random0 + ext;
						String f_name = file.getStorefileno() + random0 + ext;
						File localFile = new File(filename);
						// �����
						OutputStream is = new FileOutputStream(localFile);
						// �����ļ�
						ftp.retrieveFile(new String(ff.getName()
								.getBytes("GBK"), "iso-8859-1"), is);

						// InputStream in = ftp.retrieveFileStream(new
						// String(filename.getBytes("GBK"),"iso-8859-1"));

						is.close();

						return f_name;
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (ftp.isConnected())
				{
					try
					{
						// �˳�ftp
						ftp.logout();
						ftp.disconnect();
					}
					catch (IOException ioe)
					{
					}
				}
			}

		}
		return null;

	}

	public boolean addRemote(String remotepath, String foldername)
	{
		if (FtpConnet())
		{

			try
			{

				// ת��ָ�����Ŀ¼
				ftp.changeWorkingDirectory(remotepath);
				ftp.makeDirectory(foldername);
				ftp.logout();

				return true;

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{

				if (ftp.isConnected())
				{
					try
					{
						ftp.disconnect();
					}
					catch (IOException ioe)
					{
					}
				}

			}

		}
		return false;
	}

	public boolean addDateRemote(String remotepath, String foldername)
	{

		try
		{

			// ת��ָ�����Ŀ¼
			ftp.changeWorkingDirectory(remotepath);
			ftp.makeDirectory(foldername);

			return true;

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteFile(FtpStoreFile storefile)
	{
		if (FtpConnet())
		{
			boolean success = false;

			try
			{
				

				// ת��ָ��ɾ��Ŀ¼
				ftp.changeWorkingDirectory(storefile.getStoredirurl());

				ftp.deleteFile(storefile.getStorefileno());

				// �˳�ftp
				ftp.logout();
				// ��ʾɾ���ɹ�
				success = true;

				if (storefile.delete(storefile.getStorefileno()))
				{
					return success;
				}

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (ftp.isConnected())
				{
					try
					{
						ftp.disconnect();
					}
					catch (IOException ioe)
					{
					}
				}
			}
			return success;
		}
		return false;
	}
	public void LoadConfig(String name) 
	{
		try
		{
			ClassLoader cl = getClass().getClassLoader();
			java.io.InputStream in;
			if (cl != null)
			{
				in = cl.getResourceAsStream(name);
			}
			else
			{
				in = ClassLoader.getSystemResourceAsStream(name);
			}
			if (in == null)
			{
				// ���ļ���д
				in = new java.io.BufferedInputStream(
						new java.io.FileInputStream(name));
			}
				
			try
			{
				m_ftpProperties = new java.util.Properties();
				// װ�������ļ�
				m_ftpProperties.load(in);
				// �õ���������
				ip = consume(m_ftpProperties, "ip");
				username = consume(m_ftpProperties, "username");
				passwd = consume(m_ftpProperties, "passwd");
			}
			finally
			{
				if (in != null)
				{
					try
					{
						in.close();
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private String consume(java.util.Properties p, String key)
	{
		String s = null;
		if ((p != null) && (key != null))
		{
			s = p.getProperty(key);
			// �ҵ���������Ա�����ȥ
			if (s != null)
			{
				p.remove(key);
			}
		}
		return s;
	}
}
