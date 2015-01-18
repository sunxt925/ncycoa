<%@ page language="java" import="java.util.*,java.io.*,com.ftp.*,com.entity.ftp.*" pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	// ����˵����������Home��End��PageUp��PageDown�������ƶ���ҳ�����ּ���+��-�����Ŵ���С�����ּ���/��*������תҳ�档
	PDFCtrl pdfCtrl = new PDFCtrl(request);//����PDFCtrl�ؼ�����
	pdfCtrl.setServerPage(request.getContextPath()+"/poserver.zz"); //���÷�����ҳ��
	pdfCtrl.setTheme(ThemeType.CustomStyle);//����������ʽ
	pdfCtrl.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()");  //�ĵ���֮����Ӧ�ĺ���
	//����Զ��尴ť
	pdfCtrl.addCustomToolButton("��ӡ", "Print()", 6);
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
	pdfCtrl.addCustomToolButton("�ر�", "ShutDown()", 0);
		//���ز˵���
	pdfCtrl.setMenubar(true);
	//���ý�ֹ����
	pdfCtrl.setAllowCopy(false);
	String path = getServletContext().getRealPath("/")+"doc";   //��ʱ�ļ���Ŀ¼
	
	
    String url=request.getParameter("f_url");  //ftp��������Ŀ¼
	String filename =request.getParameter("f_name");//pdf�ļ�����
	String fileid =request.getParameter("f_id");
	FtpUse ftp=new FtpUse();
	FtpFile file=new FtpFile();
	file.setPath(url);
	file.setId(fileid);
	file.setContenttpye("3");//3  ����pdf�ĵ������滹Ҫ�ĵĸ���׼һ�� 
	String fRandName=ftp.FtpDownload(file,path);  //����Ҫ�򿪵�pdf�ļ���web�������ϵ���ʱ�ļ����µ����֣���������֣���ֹ����ͬʱ�༭ͬһ�ĵ���
	String urls=path+"\\"+fRandName;
	request.getSession().setAttribute("delpath",fRandName);	
	//System.out.println(fRandName+"        "+urls);
	if(fRandName!=null){
	    pdfCtrl.webOpen(urls);
	}
	pdfCtrl.setCaption(filename);
	pdfCtrl.setTagId("PDFCtrl1");//���б���
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��PDF�ļ�</title>
		<!--**************   ׿�� PageOffice �ͻ��˴��뿪ʼ    ************************-->
		<script language="javascript" type="text/javascript">
	function Print() {
		//alert(document.getElementById("PDFCtrl1").Caption);//��ʾ����
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
	function AfterDocumentOpened() {     //��Ӧɾ����ʱ�ļ����µ��ļ���
		        //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // ��ֹ����
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // ��ֹ���
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, false); //��ֹ��ӡ
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // ��ֹҳ������
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
</script>
		<!--**************   ׿�� PageOffice �ͻ��˴������    ************************-->
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

