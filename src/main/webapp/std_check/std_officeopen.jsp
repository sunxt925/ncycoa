<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*"
	pageEncoding="GBK"%>
	<%@page import="com.common.Util"%>
	<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
String rootpath = request.getContextPath();
%>
<%
    String filename=request.getParameter("filename");
	if(filename==null){
		filename=request.getAttribute("filepath").toString();
	}
    String path = Util.getfileCfg().get("uploadfilepath").toString();
    
//******************************׿��PageOffice�����ʹ��*******************************
	String contenttype=filename.substring(filename.lastIndexOf("."), filename.length());
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	//	poCtrl1.setServerPage("poserver.do");
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
		poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //�ļ���֮����Ӧ��������ɾ����ʱ�ļ������ļ���
			//����Office������
	    poCtrl1.setOfficeToolbars(true);
		//���ز˵���
		poCtrl1.setMenubar(false);
		//���ý�ֹ����
		poCtrl1.setAllowCopy(true);
		poCtrl1.setCaption("�ϳ��̲�officeƽ̨");
		//���ñ���ҳ��
    	String urls=path+filename;
  	//  System.out.println("ooooooooooo  "+frand_name);
    			if(contenttype.equals(".doc")||contenttype.equals(".docx")){
	    			poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "����");
	    		}else if(contenttype.equals(".ppt")||contenttype.equals(".pptx")){
	    			poCtrl1.webOpen(urls, OpenModeType.pptNormalEdit, "����");
	    		}else if(contenttype.equals(".xls")||contenttype.equals(".xlsx")){
	    			poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "����");
	    		}
		poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="<%=rootpath%>/images/csstg.css" rel="stylesheet" type="text/css" />

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
					    document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, true); // ��ֹ����
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, true); // ��ֹ���
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, true); //��ֹ��ӡ
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, true); // ��ֹҳ������   
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