<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*"
	pageEncoding="gb2312"%>
			<%@page import="com.common.Util"%>
	<%@page import="com.common.FileUpload"%>
	<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String path = getServletContext().getRealPath("/");
    
//******************************׿��PageOffice�����ʹ��*******************************
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	//	poCtrl1.setServerPage("poserver.do");
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
			//����Office������
	    poCtrl1.setOfficeToolbars(false);
		//���ز˵���
		poCtrl1.setMenubar(false);
		poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //�ļ���֮����Ӧ��������ɾ����ʱ�ļ������ļ���
		//���ý�ֹ����
		poCtrl1.setAllowCopy(false);
		poCtrl1.setCaption("�ϳ��̲�officeƽ̨");
		//���ñ���ҳ��
    	String policypath=request.getParameter("policypath");
    	request.getSession().setAttribute("delpath",policypath);//��ɾ����ʱ�ļ���ʹ��.
    	FileUpload fu=new FileUpload(); 
		String urls=path+"doc/"+policypath;
		FileUpload.copyFile(Util.getfileCfg().get("uploadfilepath")+policypath,urls);
  	//  System.out.println("ooooooooooo  "+frand_name);
	    			poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "����");
		poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="../images/csstg.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
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
// 			    document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // ��ֹ����
// 				document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, true); // ��ֹ���
// 				document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, true); //��ֹ��ӡ
// 				document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // ��ֹҳ������   
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
		   <po:PageOfficeCtrl id="PageOfficeCtrl1" />
        </div>
    </div>

    </form>
</body>
</html>