<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*"
	pageEncoding="gb2312"%>
	<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String storefileno=request.getParameter("storefileno");
    String path = getServletContext().getRealPath("/")+"doc";
    
//******************************卓正PageOffice组件的使用*******************************
	FtpStoreFile file=new FtpStoreFile(storefileno);
	String contenttype=file.getFilecontenttype();
		PDFCtrl pdfCtrl = new PDFCtrl(request);//定义PDFCtrl控件对象
		pdfCtrl.setServerPage(request.getContextPath()+"/poserver.zz"); //设置服务器页面
		pdfCtrl.setTheme(ThemeType.CustomStyle);//设置主题样式
		pdfCtrl.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()");  //文档打开之后响应的函数
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
		pdfCtrl.setCaption("南充烟草office平台");
		//隐藏菜单栏
		pdfCtrl.setMenubar(false);

		//设置禁止拷贝
		pdfCtrl.setAllowCopy(false);
		FtpStore ftp=new FtpStore();
    	String frand_name=ftp.FtpDownload(file,path);  //从ftp服务器下载到web服务器的临时文件夹，并命名为随机产生的名字。
    	String urls=path+"\\"+frand_name;
  	//  System.out.println("ooooooooooo  "+frand_name);
    	request.getSession().setAttribute("delpath",frand_name);//供删除临时文件夹使用.
    	if(frand_name!=null){
	    			pdfCtrl.webOpen(urls);
		}else{
	    		System.out.println("下载返回为空 ");
		}
		pdfCtrl.setTagId("PageOfficeCtrl1"); //此行必须		
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="../images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
	function SwitchFullScreen() {
		document.getElementById("PageOfficeCtrl1").FullScreen = !document
				.getElementById("PageOfficeCtrl1").FullScreen;
	}
	function SetPageReal() {
		document.getElementById("PageOfficeCtrl1").SetPageFit(1);
	}
	function SetPageFit() {
		document.getElementById("PageOfficeCtrl1").SetPageFit(2);
	}
	function SetPageWidth() {
		document.getElementById("PageOfficeCtrl1").SetPageFit(3);
	}
	function ZoomIn() {
		document.getElementById("PageOfficeCtrl1").ZoomIn();
	}
	function ZoomOut() {
		document.getElementById("PageOfficeCtrl1").ZoomOut();
	}
	function FirstPage() {
		document.getElementById("PageOfficeCtrl1").GoToFirstPage();
	}
	function PreviousPage() {
		document.getElementById("PageOfficeCtrl1").GoToPreviousPage();
	}
	function NextPage() {
		document.getElementById("PageOfficeCtrl1").GoToNextPage();
	}
	function LastPage() {
		document.getElementById("PageOfficeCtrl1").GoToLastPage();
	}
	function RotateRight() {
		document.getElementById("PageOfficeCtrl1").RotateRight();
	}
	function RotateLeft() {
		document.getElementById("PageOfficeCtrl1").RotateLeft();
	}
        var xmlHttp;
    function createXMLHttp(){
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest() ;
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") ;
			}
		}
		function CallBack(){
			if(xmlHttp.readyState==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}
			function AfterDocumentOpened() {
					   // document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // 禁止保存
            			//document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // 禁止另存
            			//document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, false); //禁止打印
            			//document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // 禁止页面设置
	 		//   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		createXMLHttp();
	 		   xmlHttp.open("POST","../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
		

    </script>

</head>
<body>
    <form id="form2">
    <div id="content">
        <div id="textcontent" style="width: auto; height: auto;">
			<!-- ****************************PageOffice 组件客户端编程************************************* -->

		   <!-- ****************************PageOffice 组件客户端编程结束************************************* -->
		   <po:PDFCtrl id="PageOfficeCtrl1" />
        </div>
    </div>

    </form>
</body>
</html>
