<%@ page language="java" import="java.util.*,java.io.*,com.ftp.*,com.entity.ftp.*" pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	// 按键说明：光标键、Home、End、PageUp、PageDown可用来移动或翻页；数字键盘+、-用来放大缩小；数字键盘/、*用来旋转页面。
	PDFCtrl pdfCtrl = new PDFCtrl(request);//定义PDFCtrl控件对象
	pdfCtrl.setServerPage(request.getContextPath()+"/poserver.zz"); //设置服务器页面
	pdfCtrl.setTheme(ThemeType.CustomStyle);//设置主题样式
	pdfCtrl.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()");  //文档打开之后响应的函数
	//添加自定义按钮
	pdfCtrl.addCustomToolButton("打印", "Print()", 6);
	pdfCtrl.addCustomToolButton("-", "", 0);
	pdfCtrl.addCustomToolButton("实际大小", "SetPageReal()", 16);
	pdfCtrl.addCustomToolButton("适合页面", "SetPageFit()", 17);
	pdfCtrl.addCustomToolButton("适合宽度", "SetPageWidth()", 18);
	pdfCtrl.addCustomToolButton("-", "", 0);
	pdfCtrl.addCustomToolButton("首页", "FirstPage()", 8);
	pdfCtrl.addCustomToolButton("上一页", "PreviousPage()", 9);
	pdfCtrl.addCustomToolButton("下一页", "NextPage()", 10);
	pdfCtrl.addCustomToolButton("尾页", "LastPage()", 11);
	pdfCtrl.addCustomToolButton("-", "", 0);
	pdfCtrl.addCustomToolButton("左转", "RotateLeft()", 12);
	pdfCtrl.addCustomToolButton("右转", "RotateRight()", 13);
	pdfCtrl.addCustomToolButton("-", "", 0);
	pdfCtrl.addCustomToolButton("放大", "ZoomIn()", 14);
	pdfCtrl.addCustomToolButton("缩小", "ZoomOut()", 15);
	pdfCtrl.addCustomToolButton("-", "", 0);
	pdfCtrl.addCustomToolButton("全屏", "SwitchFullScreen()", 4);
	pdfCtrl.addCustomToolButton("关闭", "ShutDown()", 0);
		//隐藏菜单栏
	pdfCtrl.setMenubar(true);
	//设置禁止拷贝
	pdfCtrl.setAllowCopy(false);
	String path = getServletContext().getRealPath("/")+"doc";   //临时文件夹目录
	
	
    String url=request.getParameter("f_url");  //ftp服务器下目录
	String filename =request.getParameter("f_name");//pdf文件名称
	String fileid =request.getParameter("f_id");
	FtpUse ftp=new FtpUse();
	FtpFile file=new FtpFile();
	file.setPath(url);
	file.setId(fileid);
	file.setContenttpye("3");//3  代表pdf文档，后面还要改的更标准一点 
	String fRandName=ftp.FtpDownload(file,path);  //返回要打开的pdf文件在web服务器上的临时文件夹下的名字，随机的名字，防止多人同时编辑同一文档。
	String urls=path+"\\"+fRandName;
	request.getSession().setAttribute("delpath",fRandName);	
	//System.out.println(fRandName+"        "+urls);
	if(fRandName!=null){
	    pdfCtrl.webOpen(urls);
	}
	pdfCtrl.setCaption(filename);
	pdfCtrl.setTagId("PDFCtrl1");//此行必须
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>打开PDF文件</title>
		<!--**************   卓正 PageOffice 客户端代码开始    ************************-->
		<script language="javascript" type="text/javascript">
	function Print() {
		//alert(document.getElementById("PDFCtrl1").Caption);//显示标题
		document.getElementById("PDFCtrl1").ShowDialog(4);
	}
	function SwitchFullScreen() {
		document.getElementById("PDFCtrl1").FullScreen = !document
				.getElementById("PDFCtrl1").FullScreen;
	}
	function SetPageReal() {
		document.getElementById("PDFCtrl1").SetPageFit(1);
	}
	function SetPageFit() {
		document.getElementById("PDFCtrl1").SetPageFit(2);
	}
	function SetPageWidth() {
		document.getElementById("PDFCtrl1").SetPageFit(3);
	}
	function ZoomIn() {
		document.getElementById("PDFCtrl1").ZoomIn();
	}
	function ZoomOut() {
		document.getElementById("PDFCtrl1").ZoomOut();
	}
	function FirstPage() {
		document.getElementById("PDFCtrl1").GoToFirstPage();
	}
	function PreviousPage() {
		document.getElementById("PDFCtrl1").GoToPreviousPage();
	}
	function NextPage() {
		document.getElementById("PDFCtrl1").GoToNextPage();
	}
	function LastPage() {
		document.getElementById("PDFCtrl1").GoToLastPage();
	}
	function RotateRight() {
		document.getElementById("PDFCtrl1").RotateRight();
	}
	function RotateLeft() {
		document.getElementById("PDFCtrl1").RotateLeft();
	}
	function ShutDown(){
        window.open("PDFManage.jsp","_self");
    }
	var xmlHttp;
	function CallBack(){
		if(xmlHttp.status==4){
			if(xmlHttp.status==200){
				var text=xmlHttp.responseText;
			}
		}
	}
	function AfterDocumentOpened() {     //响应删除临时文件夹下的文件。
		        //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // 禁止保存
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // 禁止另存
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, false); //禁止打印
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // 禁止页面设置
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
</script>
		<!--**************   卓正 PageOffice 客户端代码结束    ************************-->
	</head>
	<body>
		<form id="form1">
			<div style="width: auto; height: auto;">
				<po:PDFCtrl id="PDFCtrl1">
				</po:PDFCtrl>
			</div>
		</form>
	</body>
</html>

