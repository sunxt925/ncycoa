<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*"
	pageEncoding="gb2312"%>
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
		poCtrl1.addCustomToolButton("����","Save()",1);
		poCtrl1.addCustomToolButton("���ΪPDF�ļ�", "SaveAsPDF()", 1);
		poCtrl1.addCustomToolButton("���", "SaveElse()", 1);
		poCtrl1.addCustomToolButton("����ӡ��","CustomToolBar_InsertSeal()",2);
		poCtrl1.addCustomToolButton("�쵼Ȧ��","CustomToolBar_HandDraw()",3);
		//
		poCtrl1.addCustomToolButton("��ʾ�ۼ�", "ShowRevisions", 5);
	    poCtrl1.addCustomToolButton("���غۼ�", "HiddenRevisions", 5);
		poCtrl1.addCustomToolButton("���������ע", "StartRemark", 3);
		
		poCtrl1.addCustomToolButton("�ر�", "ShutDown()", 1);
		//���ñ���ҳ��
		poCtrl1.setSaveFilePage("SaveFile.jsp");
		//���ý�ֹ����
		poCtrl1.setAllowCopy(false);
		poCtrl1.setSaveFilePage(path+"office/Word/SaveFile.jsp");
		poCtrl1.setCaption("�ϳ��̲�officeƽ̨");
		//���ñ���ҳ��
		String policypath=request.getParameter("policypath");
    	String urls=policypath;
		String type=policypath.substring(policypath.length()-4,policypath.length());
			if(type.equals(".doc")||type.equals("docx")){
    			poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "����");
    		}else if(type.equals(".xls")||type.equals("xlsx")){
    			poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "����");
    		}
		poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="../images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
		

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