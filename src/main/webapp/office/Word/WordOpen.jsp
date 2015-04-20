<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String path = getServletContext().getRealPath("/")+"doc";
    FtpUse ftp=new FtpUse();
    
//******************************׿��PageOffice�����ʹ��*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
//	poCtrl1.setServerPage("poserver.do");
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
	poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //�ļ���֮����Ӧ��������ɾ����ʱ�ļ������ļ���
	//���ز˵���
	poCtrl1.setMenubar(true);
	poCtrl1.setCaption("�ϳ��̲�officeƽ̨");
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
	request.getSession().setAttribute("savekind","save");
	//��Word�ļ�
	String f_url=request.getParameter("f_url"); //�ļ���ftp�������µ�Ŀ¼��
	String f_name=request.getParameter("f_name");//�ļ�����ʵ���֡�
	poCtrl1.setCaption(f_name);
	String f_id=request.getParameter("f_id");//�ļ���id��
    request.getSession().setAttribute("url",f_url);
    request.getSession().setAttribute("name",f_name); 
	request.getSession().setAttribute("id",f_id);
	FtpFile file=new FtpFile();
	file.setPath(f_url);
	file.setId(f_id);
	file.setContenttpye("0");//0  ����word�ĵ������滹Ҫ�ĵĸ���׼һ��
    String frand_name=ftp.FtpDownload(file,path);  //��ftp���������ص�web����������ʱ�ļ��У�������Ϊ������������֡�
    String urls=path+"\\"+frand_name;
  //  System.out.println("ooooooooooo  "+frand_name);
    request.getSession().setAttribute("delpath",frand_name);//��ɾ����ʱ�ļ���ʹ��.
    if(frand_name!=null){
	    poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "����");
	}else{
	    System.out.println("���ط���Ϊ�� ��������������������");
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
        //���ΪPDF�ļ�
        function SaveAsPDF() {
            document.getElementById("PageOfficeCtrl1").WebSaveAsPDF();
            window.open("OpenPDF.jsp");
        }
        function SaveElse() {
             xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		 xmlHttp.open("POST","SaveElse.jsp",false);
	 		 xmlHttp.onreadystatechange=CallBack;
	         xmlHttp.send(null); 
             document.getElementById("PageOfficeCtrl1").WebSave();    
            //alert('����ɹ���'); 
        }
        function ShutDown(){
            window.open("WordManage.jsp","_self");
        }
        //����ӡ�¡�
        function CustomToolBar_InsertSeal() {
	    	alert("��ʹ�ô��û���ӡ�²���\r\n�û�������־ \r\n��ʼ���룺111111");
        	var zoomseal = document.getElementById("PageOfficeCtrl1").ZoomSeal;
	  		if (zoomseal != null)
	       		zoomseal.AddSeal();
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
		
		
		function AfterDocumentOpened() {
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
