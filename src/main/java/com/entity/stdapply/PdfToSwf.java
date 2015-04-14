package com.entity.stdapply;

import java.io.BufferedReader;

import java.io.IOException;
   
import java.io.InputStream;
   
import java.io.InputStreamReader;
   
public class PdfToSwf {
   
   
	private java.util.Properties m_ftpProperties;
	private static String EXEPATH;
    public static synchronized void pdf2swf(String fileDir, String exePath) throws IOException {
   
        //文件路径
   
        String filePath = fileDir.substring(0, fileDir.lastIndexOf("/"));
   
        //文件名，不带后缀
   
        String fileName = fileDir.substring((filePath.length() + 1), fileDir.lastIndexOf("."));
   
        Process pro = null;
   
        if (isWindowsSystem()) {
   
            //如果是windows系统
   
            //命令行命令
   
            String cmd = exePath + " \"" + fileDir + "\" -s flashversion=9 -o \"" + filePath + "/" + fileName + ".swf\"";
   
            //Runtime执行后返回创建的进程对象
   
            pro = Runtime.getRuntime().exec(cmd);
   
        } else {
   
            //如果是linux系统,路径不能有空格，而且一定不能用双引号，否则无法创建进程
   
            String[] cmd = new String[3];
   
            cmd[0] = exePath;
   
            cmd[1] = fileDir;
   
            cmd[2] = filePath + "/" + fileName + ".swf";
   
            //Runtime执行后返回创建的进程对象
   
            pro = Runtime.getRuntime().exec(cmd);
   
        }
   
        //非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
   
        new DoOutput(pro.getInputStream()).start();
   
        new DoOutput(pro.getErrorStream()).start();
   
        try {
   
            //调用waitFor方法，是为了阻塞当前进程，直到cmd执行完
   
            pro.waitFor();
   
           // flag = true;
   
        } catch (InterruptedException e) {
   
            e.printStackTrace();
   
        }
   
    }
   
   
   
    //判断是否是windows操作系统
   
    private static boolean isWindowsSystem() {
   
        String p = System.getProperty("os.name");
   
        return p.toLowerCase().indexOf("windows") >= 0 ? true : false;
   
    }
   
   
   
    //多线程内部类 读取转换时cmd进程的标准输出流和错误输出流，这样做是因为如果不读取流，进程将死锁
   
    private static class DoOutput extends Thread {
   
   
   
        public InputStream is;
   
   
   
        public DoOutput(InputStream is) {
   
            this.is = is;
   
        }
   
   
   
        @Override
   
        public void run() {
   
            BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
   
            String str = null;
   
            try {
   
                //这里并没有对流的内容进行处理，只是读了一遍
   
                while ((str = br.readLine()) != null);
   
            } catch (IOException e) {
   
                e.printStackTrace();
   
            } finally {
   
                if (br != null) {
   
                    try {
   
                        br.close();
   
                    } catch (IOException e) {
   
                        e.printStackTrace();
   
                    }
   
                }
   
            }
   
        }
   
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
				EXEPATH = consume(m_ftpProperties, "EXEPATH");
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
   
    public void PdfSwf(String file) {
   
        //转换器安装路径
    	String FilePath="";
    	FilePath=file.replace('\\', '/');
    //	System.out.println("filename   ："+FilePath);  
    	LoadConfig("PdfToSwf.properties");
        String exePath = EXEPATH;
   
        try {
   
            PdfToSwf.pdf2swf(FilePath, exePath);
   
        } catch (IOException e) {
   
            System.err.println("转换出错！");
   
            e.printStackTrace();
   
        }
   
    }
   
}