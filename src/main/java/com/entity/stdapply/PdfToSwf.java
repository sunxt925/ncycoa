package com.entity.stdapply;

import java.io.BufferedReader;

import java.io.IOException;
   
import java.io.InputStream;
   
import java.io.InputStreamReader;
   
public class PdfToSwf {
   
   
	private java.util.Properties m_ftpProperties;
	private static String EXEPATH;
    public static synchronized void pdf2swf(String fileDir, String exePath) throws IOException {
   
        //�ļ�·��
   
        String filePath = fileDir.substring(0, fileDir.lastIndexOf("/"));
   
        //�ļ�����������׺
   
        String fileName = fileDir.substring((filePath.length() + 1), fileDir.lastIndexOf("."));
   
        Process pro = null;
   
        if (isWindowsSystem()) {
   
            //�����windowsϵͳ
   
            //����������
   
            String cmd = exePath + " \"" + fileDir + "\" -s flashversion=9 -o \"" + filePath + "/" + fileName + ".swf\"";
   
            //Runtimeִ�к󷵻ش����Ľ��̶���
   
            pro = Runtime.getRuntime().exec(cmd);
   
        } else {
   
            //�����linuxϵͳ,·�������пո񣬶���һ��������˫���ţ������޷���������
   
            String[] cmd = new String[3];
   
            cmd[0] = exePath;
   
            cmd[1] = fileDir;
   
            cmd[2] = filePath + "/" + fileName + ".swf";
   
            //Runtimeִ�к󷵻ش����Ľ��̶���
   
            pro = Runtime.getRuntime().exec(cmd);
   
        }
   
        //��Ҫ��ȡһ��cmd�������Ҫ������flush�����ļ������̣߳�
   
        new DoOutput(pro.getInputStream()).start();
   
        new DoOutput(pro.getErrorStream()).start();
   
        try {
   
            //����waitFor��������Ϊ��������ǰ���̣�ֱ��cmdִ����
   
            pro.waitFor();
   
           // flag = true;
   
        } catch (InterruptedException e) {
   
            e.printStackTrace();
   
        }
   
    }
   
   
   
    //�ж��Ƿ���windows����ϵͳ
   
    private static boolean isWindowsSystem() {
   
        String p = System.getProperty("os.name");
   
        return p.toLowerCase().indexOf("windows") >= 0 ? true : false;
   
    }
   
   
   
    //���߳��ڲ��� ��ȡת��ʱcmd���̵ı�׼������ʹ��������������������Ϊ�������ȡ�������̽�����
   
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
   
                //���ﲢû�ж��������ݽ��д���ֻ�Ƕ���һ��
   
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
			// �ҵ���������Ա�����ȥ
			if (s != null)
			{
				p.remove(key);
			}
		}
		return s;
	}
   
    public void PdfSwf(String file) {
   
        //ת������װ·��
    	String FilePath="";
    	FilePath=file.replace('\\', '/');
    //	System.out.println("filename   ��"+FilePath);  
    	LoadConfig("PdfToSwf.properties");
        String exePath = EXEPATH;
   
        try {
   
            PdfToSwf.pdf2swf(FilePath, exePath);
   
        } catch (IOException e) {
   
            System.err.println("ת������");
   
            e.printStackTrace();
   
        }
   
    }
   
}