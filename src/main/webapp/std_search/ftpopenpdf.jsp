<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*"
	pageEncoding="gb2312"%>
	<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String storefileno=request.getParameter("storefileno");
    String path = getServletContext().getRealPath("/")+"doc";
    
//******************************׿��PageOffice�����ʹ��*******************************
	FtpStoreFile file=new FtpStoreFile(storefileno);
	String contenttype=file.getFilecontenttype();
		PDFCtrl pdfCtrl = new PDFCtrl(request);//����PDFCtrl�ؼ�����
		pdfCtrl.setServerPage(request.getContextPath()+"/poserver.zz"); //���÷�����ҳ��
		pdfCtrl.setTheme(ThemeType.CustomStyle);//����������ʽ
		pdfCtrl.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()");  //�ĵ���֮����Ӧ�ĺ���
		pdfCtrl.addCustomToolButton("-", "", 0);
		pdfCtrl.addCustomToolButton("ʵ�ʴ�С", "SetPageReal()", 16);
		pdfCtrl.addCustomToolButton("�ʺ�ҳ��", "SetPageFit()", 17);
		pdfCtrl.addCustomToolButton("�ʺϿ��", "SetPageWidth()", 18);
		pdfCtrl.addCustomToolButton("-", "", 0);
		pdfCtrl.addCustomToolButton("��ҳ", "FirstPage()", 8);
		pdfCtrl.addCustomToolButton("��һҳ", "PreviousPage()", 9);
		pdfCtrl.addCustomToolButton("��һҳ", "NextPage()", 10);
		pdfCtrl.addCustomToolButton("βҳ", "LastPage()", 11);
		pdfCtrl.addCustomToolButton("-", "", 0);
		pdfCtrl.addCustomToolButton("��ת", "RotateLeft()", 12);
		pdfCtrl.addCustomToolButton("��ת", "RotateRight()", 13);
		pdfCtrl.addCustomToolButton("-", "", 0);
		pdfCtrl.addCustomToolButton("�Ŵ�", "ZoomIn()", 14);
		pdfCtrl.addCustomToolButton("��С", "ZoomOut()", 15);
		pdfCtrl.addCustomToolButton("-", "", 0);
		pdfCtrl.addCustomToolButton("ȫ��", "SwitchFullScreen()", 4);
		pdfCtrl.setCaption("�ϳ��̲�officeƽ̨");
		//���ز˵���
		pdfCtrl.setMenubar(false);

		//���ý�ֹ����
		pdfCtrl.setAllowCopy(false);
		FtpStore ftp=new FtpStore();
    	String frand_name=ftp.FtpDownload(file,path);  //��ftp���������ص�web����������ʱ�ļ��У�������Ϊ������������֡�
    	String urls=path+"\\"+frand_name;
  	//  System.out.println("ooooooooooo  "+frand_name);
    	request.getSession().setAttribute("delpath",frand_name);//��ɾ����ʱ�ļ���ʹ��.
    	if(frand_name!=null){
	    			pdfCtrl.webOpen(urls);
		}else{
	    		System.out.println("���ط���Ϊ�� ");
		}
		pdfCtrl.setTagId("PageOfficeCtrl1"); //���б���		
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
					   // document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // ��ֹ����
            			//document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // ��ֹ���
            			//document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, false); //��ֹ��ӡ
            			//document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // ��ֹҳ������
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
			<!-- ****************************PageOffice ����ͻ��˱��************************************* -->

		   <!-- ****************************PageOffice ����ͻ��˱�̽���************************************* -->
		   <po:PDFCtrl id="PageOfficeCtrl1" />
        </div>
    </div>

    </form>
</body>
</html>
