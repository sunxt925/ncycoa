package com.action.std;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.entity.ftp.FtpStoreFile;
import com.ftp.FtpStore;

public class StdFileAction extends ActionInterface{
	public String getResult(HttpServletRequest request)
	{
			String res="";
			String action=request.getParameter("act");
			FtpStoreFile storefile=null;
			FtpStore  Ftp=new FtpStore();
		
			if (action!=null && action.equals("del"))
			{
/*				String[] ids=request.getParameterValues("items");
				String para="";
				boolean flag=true;
				for (int i=0;i<ids.length;i++)
				{
					storefile=new FtpStoreFile(ids[i]);
					if(!Ftp.deleteFile(storefile)){
						flag=false;
					}
				}*/
				boolean flag=true;
				String del=request.getParameter("storefileno");
				storefile=new FtpStoreFile(del);
				if(!Ftp.deleteFile(storefile)){
					flag=false;
				}
				if(flag)
				{

					res += "show('删除成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("docno")+"\";";
					res += "window.open('../std/std_filelist.jsp?docNo='+ccm,'_self');";
					res += "parent.unittree.location.reload();";

				}
				else
				{

					res += "show('删除失败，可能个别文件删除不了联系！');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("docno")+"\";";
					res += "window.open('../std/std_filelist.jsp?docNo='+ccm,'_self');";
					res += "parent.unittree.location.reload();";
				}
			}
			else
			{

			}
			return res;
		}

}
