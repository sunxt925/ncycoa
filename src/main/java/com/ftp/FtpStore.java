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

	public boolean FtpConnet() // 创建FTP连接
	{
		boolean success = false;
		ftp = new FTPClient();

		try
		{
			int reply;
			ftp.setControlEncoding("GBK"); // 设置了编码
			LoadConfig("ftp.properties");
			// 连接FTP服务器

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.connect(ip, 21);
			// 登录ftp
			ftp.login(username, passwd);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

			// 看返回的值是不是230，如果是，表示登陆成功
			reply = ftp.getReplyCode();
			// 以2开头的返回值就会为真
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

				boolean flag = false; // /标示是否是新建一个文件
				
				
				String dir = Util.getSeqcode(file.getDocclass());

				// 转到指定上传目录
				ftp.changeWorkingDirectory(file.getStoredirurl());
				

				if (file.getStorefileno().equals(""))
				{
					ftp.changeWorkingDirectory(dir);
					String id = Util.getSequence(file.getDocclass());
					file.setStorefileno(id);
					flag = true;
				}
				else    //修改文件操作
				{
					ftp.deleteFile(file.getStorefileno());
					ftp.changeWorkingDirectory(file.getStoredirurl());
					
					ftp.storeFile(file.getStorefileno(), input);
					// 关闭输入流
					input.close();
					// 退出ftp
					ftp.logout();
					
					return file.getStorefileno();
					
				}
				// 获取当前时间域
				Calendar c = Calendar.getInstance();
				String year = "" + c.get(c.YEAR);
				String month = "" + (c.get(c.MONTH) + 1);
				String day = "" + c.get(c.DATE);
				String date = month + "-" + day;
				FTPFile[] fs = ftp.listFiles();
				String path = null;
				String separator = java.io.File.separator;

				boolean Yexist = false;
				// 遍历所有文件，找到指定的文件
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

				// 将上传文件存储到指定目录
				file.setStoredirurl(dir + "/" + year + "/" + date);
				ftp.storeFile(file.getStorefileno(), input);
				// 关闭输入流
				input.close();
				// 退出ftp
				ftp.logout();
				// 表示上传成功
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

				// 转到指定下载目录

				ftp.changeWorkingDirectory(file.getStoredirurl());

				FTPFile[] fs = ftp.listFiles();
				// 遍历所有文件，找到指定的文件
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
						// 输出流
						OutputStream is = new FileOutputStream(localFile);
						// 下载文件
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
						// 退出ftp
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

				// 转到指定添加目录
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

			// 转到指定添加目录
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
				

				// 转到指定删除目录
				ftp.changeWorkingDirectory(storefile.getStoredirurl());

				ftp.deleteFile(storefile.getStorefileno());

				// 退出ftp
				ftp.logout();
				// 表示删除成功
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
				// 用文件读写
				in = new java.io.BufferedInputStream(
						new java.io.FileInputStream(name));
			}
				
			try
			{
				m_ftpProperties = new java.util.Properties();
				// 装载配置文件
				m_ftpProperties.load(in);
				// 得到配置内容
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
			// 找到，则从属性表中移去
			if (s != null)
			{
				p.remove(key);
			}
		}
		return s;
	}
}
