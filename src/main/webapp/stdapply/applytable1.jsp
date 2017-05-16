<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,com.entity.stdapply.*,java.io.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.DataRegion"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.WordDocument"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%!
// �����ļ�
public void copyFile(String oldPath, String newPath){
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //�ļ�����ʱ 
				InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ� 
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //�ֽ��� �ļ���С 
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread); 
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("���Ƶ����ļ���������");
			e.printStackTrace();
		}

}
%>
<%
String applyid=request.getParameter("applyid");
String applyorg=request.getParameter("applyorg");
String applydate=request.getParameter("applydate");
String applyreason=request.getParameter("applyreason");
DocReviseInifo revise=new DocReviseInifo();
String doccodestring=revise.getProcessDoccode(applyid);
String docnamestring=revise.getProcessDocname(applyid);

String tempPath = getServletContext().getRealPath("/")+"applytabletemp";//��ʱ�ļ���·��
String tempname=tempPath+"\\tabletemp.doc";
String applytablepath=tempPath+"\\"+applyid+".doc";
copyFile(tempname,applytablepath);





    String storefileno=request.getParameter("storefileno");
    String path = getServletContext().getRealPath("/")+"doc";
    
//******************************׿��PageOffice�����ʹ��*******************************
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	//	poCtrl1.setServerPage("poserver.do");
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
		poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //�ļ���֮����Ӧ��������ɾ����ʱ�ļ������ļ���
			//����Office������
	    poCtrl1.setOfficeToolbars(false);
		//���ز˵���
		poCtrl1.setCustomToolbar(true);
		poCtrl1.setMenubar(true);
		//���ý�ֹ����
		poCtrl1.setAllowCopy(false);
		//���ñ���ҳ��
		
 		WordDocument doc = new WordDocument();
		DataRegion org = doc.openDataRegion("PO_org");
		org.setValue(applyorg);
		DataRegion date = doc.openDataRegion("PO_date");
		date.setValue(applydate);
		DataRegion reason = doc.openDataRegion("PO_reason");
		reason.setValue(applyreason);
		DataRegion doccode = doc.openDataRegion("PO_doccode");
		doccode.setValue(doccodestring);
		DataRegion docname = doc.openDataRegion("PO_docname");
		docname.setValue(docnamestring);
		poCtrl1.setWriter(doc);
    String filename=applyid+".doc";
    	//request.getSession().setAttribute("delpath",filename);//��ɾ����ʱ�ļ���ʹ��.
    	if(filename!=null){
	    			poCtrl1.webOpen(applytablepath, OpenModeType.docNormalEdit, "����");
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
					    document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // ��ֹ����
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