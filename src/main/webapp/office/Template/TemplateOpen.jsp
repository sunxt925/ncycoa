<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String p=request.getParameter("file_path");
    String path = getServletContext().getRealPath("/")+p;
    
//******************************׿��PageOffice�����ʹ��*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);

	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���

	//���ز˵���
	poCtrl1.setMenubar(true);
	poCtrl1.setCaption("�ϳ��̲�officeƽ̨");
	poCtrl1.addCustomToolButton("����","Save()",1);
	poCtrl1.addCustomToolButton("�ر�", "ShutDown()", 1);
	//���ñ���ҳ��
	poCtrl1.setSaveFilePage("SaveFile.jsp");
	//��Word�ļ�

	String f_name=request.getParameter("filename");//�ļ�����ʵ���֡�
	poCtrl1.setCaption(f_name);
    String urls= path+"\\"+f_name;
    String type=request.getParameter("file_type");
    if(type.equals("0")){
         poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "����");
    }else if(type.equals("1")){
         poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "����");
    }else if(type.equals("2")){
         poCtrl1.webOpen(urls, OpenModeType.pptNormalEdit, "����");
    }
	
	poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="../../images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();    
            //alert('����ɹ���');
        }
        function ShutDown(){
            window.open("TemplateManage.jsp","_self");
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
