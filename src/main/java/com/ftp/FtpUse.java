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

public class FtpUse
{

	private FTPClient ftp;

	public boolean FtpConnet() // 创建FTP连接
	{
		boolean success = false;
		ftp = new FTPClient();

		try
		{
			int reply;
			ftp.setControlEncoding("GBK"); // 设置了编码

			// 连接FTP服务器

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.connect("172.20.91.13", 21);
			// 登录ftp
			ftp.login("xiaojin", "123456");

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

	public String FtpUpload(FtpFile file, InputStream input)
	{

		if (FtpConnet())
		{
			boolean success = false;

			try
			{

				boolean flag = false; // /标示是否是新建一个文件

				// 转到指定上传目录
				ftp.changeWorkingDirectory(file.getPath());

				if (file.getId().equals(""))
				{
					String id = Util.getSequence(file.getCode());
					file.setId(id);
					flag = true;
				}
				else    //修改文件操作
				{
					ftp.deleteFile(file.getId());
					ftp.changeWorkingDirectory(file.getPath());
					
					ftp.storeFile(file.getId(), input);
					// 关闭输入流
					input.close();
					// 退出ftp
					ftp.logout();
					
					return file.getId();
					
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
						System.out.println(path);
						FTPFile[] fs0 = ftp.listFiles();
						for (FTPFile ff0 : fs0)
						{

							System.out.println(ff0.getName());
							if (ff0.getName().equals(date))
							{

								ftp.changeWorkingDirectory(date);
								System.out.println(path);
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
					addDateRemote(file.getPath(), year);
					addDateRemote(year, date);

					ftp.changeWorkingDirectory(date);
				}

				// 将上传文件存储到指定目录
				file.setPath(file.getPath() + "/" + year + "/" + date);
				ftp.storeFile(file.getId(), input);
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
			return file.getId();
		}
		return null;
	}

	public String FtpDownload(FtpFile file, String localPath)

	{
		if (FtpConnet())
		{

			try
			{

				// FtpFile file = new FtpFile(id);

				// 转到指定下载目录

				ftp.changeWorkingDirectory(file.getPath());

				FTPFile[] fs = ftp.listFiles();
				// 遍历所有文件，找到指定的文件
				for (FTPFile ff : fs)
				{
					if (ff.getName().equals(file.getId()))
					{

						Random random = new Random(System.currentTimeMillis());

						String ext = "";
						if (file.getContenttpye().equals("0"))
							ext = ".doc";
						else if (file.getContenttpye().equals("1"))
							ext = ".xls";
						else if (file.getContenttpye().equals("2"))
							ext = ".ppt";
						else if (file.getContenttpye().equals("3"))
							ext = ".pdf";

						String random0 = "" + random.nextInt();
						String filename = localPath + "/" + file.getId()
								+ random0 + ext;
						String f_name = file.getId() + random0 + ext;
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

	public boolean deleteFile(FtpFile file)
	{
		if (FtpConnet())
		{
			boolean success = false;

			try
			{

				// 转到指定删除目录
				ftp.changeWorkingDirectory(file.getPath());

				ftp.deleteFile(file.getId());

				// 退出ftp
				ftp.logout();
				// 表示删除成功
				success = true;

				if (file.delete(file.getId()))
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

}
