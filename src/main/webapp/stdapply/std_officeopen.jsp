<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,com.entity.stdapply.*,java.io.*"
	pageEncoding="gb2312"%>
	<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String storefileno=request.getParameter("storefileno");
    String path = getServletContext().getRealPath("/")+"doc";
    
//******************************׿��PageOffice�����ʹ��*******************************
	DocApplyStore file=new DocApplyStore(storefileno);
	String contenttype=file.getFileContentType();
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	//	poCtrl1.setServerPage("poserver.do");
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
//		poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //�ļ���֮����Ӧ��������ɾ����ʱ�ļ������ļ���
			//����Office������
	    poCtrl1.setOfficeToolbars(true);
		//���ز˵���
		poCtrl1.setCustomToolbar(true);
		poCtrl1.setMenubar(false);
		poCtrl1.addCustomToolButton("����","Save()",1);
			poCtrl1.addCustomToolButton("�쵼Ȧ��","CustomToolBar_HandDraw()",3);
	//
	poCtrl1.addCustomToolButton("��ʾ�ۼ�", "ShowRevisions", 5);
    poCtrl1.addCustomToolButton("���غۼ�", "HiddenRevisions", 5);
	poCtrl1.addCustomToolButton("���������ע", "StartRemark", 3);
		//���ý�ֹ����
		poCtrl1.setAllowCopy(true);
		//���ñ���ҳ��
		poCtrl1.setSaveFilePage("SaveFile.jsp");
		request.getSession().setAttribute("savekind","save");
		String filename=file.getFileName();
		poCtrl1.setCaption(filename);
		String urls=file.getStoreDirURL();
    
    
        request.getSession().setAttribute("url",urls);
     //   request.getSession().setAttribute("name",filename); 
	    request.getSession().setAttribute("id",storefileno);
    	//request.getSession().setAttribute("delpath",filename);//��ɾ����ʱ�ļ���ʹ��.
    	if(filename!=null){
    			if(contenttype.equals("doc")){
	    			poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "����");
	    		}else if(contenttype.equals("ppt")){
	    			poCtrl1.webOpen(urls, OpenModeType.pptNormalEdit, "����");
	    		}else if(contenttype.equals("xls")){
	    			poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "����");
	    		}else if(contenttype.equals("vsd")){
	    			poCtrl1.webOpen(urls, OpenModeType.vsdNormalEdit, "����");
	    		}
		}else{
	    		System.out.println("���ط���Ϊ�� ");
		}
		poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        var xmlHttp;
		function CallBack(){
			if(xmlHttp.status==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}
		    function createXMLHttp(){
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest() ;
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") ;
			}
		}
			function AfterDocumentOpened() {
					    document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, true); // ��ֹ����
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, true); // ��ֹ���
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, true); //��ֹ��ӡ
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, true); // ��ֹҳ������
	 		 //  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		 //  xmlHttp.open("POST","../servlet/DeleteDocFile",false);
	 		 //  xmlHttp.onreadystatechange=CallBack;
	         //  xmlHttp.send(null);     
        }
		 function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();    
            //alert('����ɹ���');
        }
       		//�쵼Ȧ�ġ�			
	   function CustomToolBar_HandDraw() {
			document.getElementById("PageOfficeCtrl1").HandDraw.Start();
		}
        var xmlHttp;
		function CallBack(){
			if(xmlHttp.status==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}
		  //��ʾ�ۼ�
        function ShowRevisions() {
            document.getElementById("PageOfficeCtrl1").ShowRevisions = true;
        }

        //���غۼ�
        function HiddenRevisions() {
            document.getElementById("PageOfficeCtrl1").ShowRevisions = false;
        }
        // ���������ע
        function StartRemark() {
            var appObj = document.getElementById("PageOfficeCtrl1").WordInsertComment();

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