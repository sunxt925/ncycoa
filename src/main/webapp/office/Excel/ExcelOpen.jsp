<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.excelwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String f_url=request.getParameter("f_url");//�ļ���ftp�������µ�Ŀ¼��
	String f_name=request.getParameter("f_name");//�ļ�����ʵ���֡�
	String f_id = request.getParameter("f_id");//�ļ���id��
	if (f_id == null || f_id == "") {
		out.println("<script>alert('δ����ļ�ID�ţ�');location.href='ExcelManage.jsp'</script>");
	}
    String path = getServletContext().getRealPath("/")+"doc";
    FtpUse ftp=new FtpUse();
    
//******************************׿��PageOffice�����ʹ��*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
//	poCtrl1.setServerPage("poserver.do");
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
	poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()");
	//���ز˵���
	poCtrl1.setMenubar(true);
	poCtrl1.setCaption(f_name);
	//���ñ���ҳ��
	poCtrl1.setSaveFilePage("SaveFile.jsp");
	request.getSession().setAttribute("savekind","save");
	//����Զ���˵���
		poCtrl1.setCustomMenuCaption("�Զ���˵�");
		poCtrl1.addCustomMenuItem("��ʾ����","CustomMenuItem1_Click()",true);
		poCtrl1.addCustomMenuItem("�쵼Ȧ��","CustomMenuItem2_Click()",true);
		
		//����Զ��幤����
		poCtrl1.addCustomToolButton("����","Save()",1);
	    poCtrl1.addCustomToolButton("���ΪPDF�ļ�", "SaveAsPDF()", 1);
	    poCtrl1.addCustomToolButton("���", "SaveElse()", 1);
		poCtrl1.addCustomToolButton("����ӡ��","CustomToolBar_InsertSeal()",2);
		poCtrl1.addCustomToolButton("�쵼Ȧ��","CustomToolBar_HandDraw()",3);
		poCtrl1.addCustomToolButton("ȫ��/��ԭ","CustomToolBar_FullScreen()",4);
	    poCtrl1.addCustomToolButton("�ر�", "ShutDown()", 1);
	
	//��Excel�ļ�
	poCtrl1.setCaption(f_name);
    request.getSession().setAttribute("url",f_url);
    request.getSession().setAttribute("name",f_name);
	request.getSession().setAttribute("id",f_id);
	//System.out.println("uuuuuuuuuuuuu    "+f_url+"//"+f_id);
	FtpFile file=new FtpFile();
	file.setPath(f_url);
	file.setId(f_id);
	file.setContenttpye("1");//0  ����excel�ĵ������滹Ҫ�ĵĸ���׼һ��
    String frand_name=ftp.FtpDownload(file,path);  //��ftp���������ص�web����������ʱ�ļ��У�������Ϊ������������֡�
    String urls=path+"\\"+frand_name;
  //  System.out.println("ooooooooooo  "+frand_name);
    request.getSession().setAttribute("delpath",frand_name);//��ɾ����ʱ�ļ���ʹ��.
    if(frand_name!=null){
	    poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "����");    
	}else{
	    System.out.println("����excel����Ϊ�� ��������������������");
	}
	poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="../../images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
       function CustomMenuItem1_Click() {
		     alert("�ò˵��ı����ǣ�" + document.getElementById("PageOfficeCtrl1").Caption);
		};
					    
	   function CustomMenuItem2_Click() {
		  document.getElementById("PageOfficeCtrl1").HandDraw.Start();
		};   
					   
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
        function CustomToolBar_InsertSeal() {
	    	alert("��ʹ�ô��û���ӡ�²���\r\n�û�������־ \r\n��ʼ���룺111111");
        	var zoomseal = document.getElementById("PageOfficeCtrl1").ZoomSeal;
	  		if (zoomseal != null)
	       		zoomseal.AddSeal();
	    }
						
	   function CustomToolBar_HandDraw() {
			document.getElementById("PageOfficeCtrl1").HandDraw.Start();
		}
						
       function CustomToolBar_FullScreen() {
			document.getElementById("PageOfficeCtrl1").FullScreen = !document.getElementById("PageOfficeCtrl1").FullScreen;
	  }
			        
        function ShutDown(){
            window.open("ExcelManage.jsp","_self");
        }
        var xmlHttp;
		function CallBack(){
			if(xmlHttp.status==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
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
    <form id="form2" name="form2">
    <div id="content">
        <div id="textcontent" style="width: auto; height: auto;">
			<!-- ****************************PageOffice ����ͻ��˱��************************************* -->

		   <!-- ****************************PageOffice ����ͻ��˱�̽���************************************* -->
		   <po:PageOfficeCtrl id="PageOfficeCtrl1" />
        </div>
    </div>
<input name="savekind" type="hidden" id="savekind" value="1">
    </form>
</body>
</html>
