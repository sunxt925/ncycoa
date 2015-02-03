package com.action.stdapply;

import java.io.File;

import javax.servlet.http.HttpServletRequest;


import com.action.ActionInterface;
import com.entity.ftp.FtpStoreFile;
import com.entity.stdapply.DocApplyStore;
import com.ftp.FtpStore;

public class StdFileAction extends ActionInterface{
	public String getResult(HttpServletRequest request)
	{
			String res="";
			String action=request.getParameter("act");
		
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
				String storefileno=request.getParameter("storefileno");
				System.out.println("storefileno                :::"+storefileno);
				DocApplyStore file=new DocApplyStore(storefileno);
				String url=file.getStoreDirURL();
				File f=new File(url);
				if(f.delete()&&file.delete(storefileno))
				{
					res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("docno")+"\";";
					res += "window.open('../stdapply/std_filelist.jsp?docNo='+ccm,'_self');";
					res += "parent.unittree.location.reload();";

				}
				else
				{
					res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，可能个别文件删除不了联系！');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var ccm=\""+request.getParameter("docno")+"\";";
					res += "window.open('../stdapply/std_filelist.jsp?docNo='+ccm,'_self');";
				}
			}
			else
			{

			}
			return res;
		}

}
