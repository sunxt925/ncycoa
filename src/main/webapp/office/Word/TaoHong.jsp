<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.sql.*,java.io.*,javax.servlet.*,javax.servlet.http.*,com.ftp.*,com.entity.ftp.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.DataRegion"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.WordDocument"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%!

// 拷贝文件
public void copyFile(String oldPath, String newPath){
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //文件存在时 
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小 
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread); 
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}

}
%>
<%
	String fileName ="template.doc";//服务器doc文件夹下的临时文件
	String taohongPath = getServletContext().getRealPath("/")+"taohong";//套红模板的路径
	String tempPath = getServletContext().getRealPath("/")+"doc";//临时文件的路径
	String templatePath =  tempPath+"\\"+fileName;//模板的路径、名字
	
	
    FtpUse ftp=new FtpUse();
	String mbName = request.getParameter("mb");
	String fRandName=null;//从FTP下载下来的文件名
	String taohongtemp=null;//临时文件
	boolean flag= false;
    
    
	//***************************卓正PageOffice组件的使用********************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	//String file_name = request.getParameter("f_name");
	//poCtrl1.setCaption(file_name);	
	//隐藏菜单栏
	poCtrl1.setMenubar(true);
	poCtrl1.addCustomToolButton("一键套红","taoHong()",1);
	poCtrl1.addCustomToolButton("保存","Save()",1);
	poCtrl1.addCustomToolButton("关闭","Close()",1);
	poCtrl1.setSaveFilePage("SaveFile.jsp");

	   if (mbName != null && mbName.trim() != "") {//选择模板并点击一键套红之后.
		// 选择模板后执行套红
		String cap=(String)request.getSession().getAttribute("f_name");
		poCtrl1.setCaption(cap);
		// 复制模板，命名为正式发文的文件名：zhengshi.doc
	    fRandName = request.getParameter("frand_name");
	    taohongtemp = request.getParameter("taohongtemp");
		String urls = tempPath+"\\"+fRandName;
		//String templateName = request.getParameter("mb");
		String mbPath =taohongPath+"\\"+mbName;
		String taohongtempPath=tempPath+"\\"+taohongtemp;
			
		copyFile(mbPath, urls); 

		// 填充数据和正文内容到下载下来的文件
		WordDocument doc = new WordDocument();
		DataRegion copies = doc.openDataRegion("PO_Copies");
		copies.setValue("6");
		DataRegion docNum = doc.openDataRegion("PO_DocNum");
		docNum.setValue("001");
		DataRegion issueDate = doc.openDataRegion("PO_IssueDate");
		issueDate.setValue("2013-5-30");
		DataRegion issueDept = doc.openDataRegion("PO_IssueDept");
		issueDept.setValue("企管科");
		DataRegion sTextS = doc.openDataRegion("PO_STextS");
		sTextS.setValue("[word]"+taohongtempPath+"[/word]");
		DataRegion sTitle = doc.openDataRegion("PO_sTitle");
		sTitle.setValue("南充烟草公司文件");
		DataRegion topicWords = doc.openDataRegion("PO_TopicWords");
		topicWords.setValue("Pageoffice、 套红");
		poCtrl1.setWriter(doc);
		
		
	}//首次点击套红之后从ftp服务器下载文档到web服务器载。
	 else{
	 				Random random = new Random(System.currentTimeMillis());
				taohongtemp="Taohong"+random.nextInt()+".doc";//多人下载，临时文件不同
				request.getSession().setAttribute("taohongtemp",taohongtemp);
				String oldPath=templatePath;
			    String newPath=tempPath + "\\"+taohongtemp;
				try { 
           			int bytesum = 0; 
           			int byteread = 0; 
           			File oldfile = new File(oldPath);
           			if (oldfile.exists()) { //文件存在时 
               			InputStream inStream = new FileInputStream(oldPath); //读入原文件 
               			FileOutputStream fs = new FileOutputStream(newPath); 
               			byte[] buffer = new byte[1444]; 
               			int length; 
               			while ( (byteread = inStream.read(buffer)) != -1) { 
                   			bytesum += byteread; //字节数 文件大小 
                   			fs.write(buffer, 0, byteread); 
               			} 
               			inStream.close(); 
           			} 
           		}catch (Exception e) { 
           			System.out.println("复制单个文件操作出错"); 
           			e.printStackTrace(); 
       		} 
       		
       		String f_name = request.getParameter("f_name");
       		request.getSession().setAttribute("f_name",f_name);
       		poCtrl1.setCaption(f_name);
       		
	    	String f_url = request.getParameter("f_url");
	    	request.getSession().setAttribute("url",f_url);	
	    	String f_id = request.getParameter("f_id");    
	    	request.getSession().setAttribute("id",f_id);	
	    	FtpFile file=new FtpFile();
			file.setPath(f_url);
			file.setId(f_id);
			file.setContenttpye("0");//0  代表word文档，后面还要改的更标准一点
	    	fRandName= ftp.FtpDownload(file,tempPath);//将文件下载到doc
	    	request.getSession().setAttribute("fRandName",fRandName);
	    	String urls = tempPath+"\\"+fRandName;
	       
       		copyFile(urls,newPath);//将临时文件复制到web服务器下的doc文件夹中新生产的一文件中。
	}
	
	 String urls=tempPath+"\\"+fRandName;
	 poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "张三");
	 String dels=fRandName+","+taohongtemp+",";
	 request.getSession().setAttribute("delpath",dels);//以备删除临时文件用。
	
	 poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	  显示出原文件。
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title></title>
		<link href="../images/csstg.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	//初始加载模板列表
	function load() {
		if (getQueryString("mb") != null)
			document.getElementById("templateName").value = getQueryString("mb");
	}

	//获取url参数 
	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		else
			return null;
	}

	//套红
	function taoHong() {
		var mb = document.getElementById("templateName").value;
		//document.getElementById("PageOfficeCtrl1").WebSave();
		document.getElementById("form1").action = "TaoHong.jsp?mb=" + mb+"&frand_name="+"<%=request.getSession().getAttribute("fRandName")%>" + "&taohongtemp=" + "<%=request.getSession().getAttribute("taohongtemp")%>";

		document.forms[0].submit();
	}

	var xmlHttp;
	function CallBack(){
		if(xmlHttp.status==4){
			if(xmlHttp.status==200){
				var text=xmlHttp.responseText;
			}
		}
	}
	function AfterDocumentOpened() {
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
                //保存
        function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();
        }

	//关闭
	function Close() {
		AfterDocumentOpened();
		location.href = "WordManage.jsp";
	}
</script>
	</head>
	<body onload="load();" >
		<div id="content">
			<div id="textcontent" style="width: auto; height: 800px;">
				<div class="flow4">
					<a href="WordManage.jsp"> <img alt="返回" src="images/return.gif"
							border="0" />文件列表</a>
					<span style="width: 100px;"> </span><strong>文档主题：</strong>
					<span style="color: Red;">测试文件</span>
					<form method="post" id="form1">
						<strong>模板列表：</strong>
						<span style="color: Red;"> <select name="templateName"
								id="templateName" style='width: 240px;'>
								<option value='temp2008.doc' selected="selected">
									模板一
								</option>
								<option value='temp2009.doc'>
									模板二
								</option>
								<option value='temp2010.doc'>
									模板三
								</option>
							</select> </span>
					</form>
				</div>
				<!--**************   卓正 PageOffice组件 ************************-->

				<po:PageOfficeCtrl id="PageOfficeCtrl1" />
			</div>
		</div>

	</body>
</html>
