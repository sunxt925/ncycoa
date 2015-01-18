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

	public boolean FtpConnet() // ����FTP����
	{
		boolean success = false;
		ftp = new FTPClient();

		try
		{
			int reply;
			ftp.setControlEncoding("GBK"); // �����˱���

			// ����FTP������

			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftp.connect("172.20.91.13", 21);
			// ��¼ftp
			ftp.login("xiaojin", "123456");

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

	public String FtpUpload(FtpFile file, InputStream input)
	{

		if (FtpConnet())
		{
			boolean success = false;

			try
			{

				boolean flag = false; // /��ʾ�Ƿ����½�һ���ļ�

				// ת��ָ���ϴ�Ŀ¼
				ftp.changeWorkingDirectory(file.getPath());

				if (file.getId().equals(""))
				{
					String id = Util.getSequence(file.getCode());
					file.setId(id);
					flag = true;
				}
				else    //�޸��ļ�����
				{
					ftp.deleteFile(file.getId());
					ftp.changeWorkingDirectory(file.getPath());
					
					ftp.storeFile(file.getId(), input);
					// �ر�������
					input.close();
					// �˳�ftp
					ftp.logout();
					
					return file.getId();
					
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

				// ���ϴ��ļ��洢��ָ��Ŀ¼
				file.setPath(file.getPath() + "/" + year + "/" + date);
				ftp.storeFile(file.getId(), input);
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

				// ת��ָ������Ŀ¼

				ftp.changeWorkingDirectory(file.getPath());

				FTPFile[] fs = ftp.listFiles();
				// ���������ļ����ҵ�ָ�����ļ�
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

	public boolean deleteFile(FtpFile file)
	{
		if (FtpConnet())
		{
			boolean success = false;

			try
			{

				// ת��ָ��ɾ��Ŀ¼
				ftp.changeWorkingDirectory(file.getPath());

				ftp.deleteFile(file.getId());

				// �˳�ftp
				ftp.logout();
				// ��ʾɾ���ɹ�
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
