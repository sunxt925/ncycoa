<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.sql.*,java.io.*,javax.servlet.*,javax.servlet.http.*,com.ftp.*,com.entity.ftp.*"
	pageEncoding="gb2312"%>
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
	String fileName ="template.doc";//������doc�ļ����µ���ʱ�ļ�
	String taohongPath = getServletContext().getRealPath("/")+"taohong";//�׺�ģ���·��
	String tempPath = getServletContext().getRealPath("/")+"doc";//��ʱ�ļ���·��
	String templatePath =  tempPath+"\\"+fileName;//ģ���·��������
	
	
    FtpUse ftp=new FtpUse();
	String mbName = request.getParameter("mb");
	String fRandName=null;//��FTP�����������ļ���
	String taohongtemp=null;//��ʱ�ļ�
	boolean flag= false;
    
    
	//***************************׿��PageOffice�����ʹ��********************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
	//String file_name = request.getParameter("f_name");
	//poCtrl1.setCaption(file_name);	
	//���ز˵���
	poCtrl1.setMenubar(true);
	poCtrl1.addCustomToolButton("һ���׺�","taoHong()",1);
	poCtrl1.addCustomToolButton("����","Save()",1);
	poCtrl1.addCustomToolButton("�ر�","Close()",1);
	poCtrl1.setSaveFilePage("SaveFile.jsp");

	   if (mbName != null && mbName.trim() != "") {//ѡ��ģ�岢���һ���׺�֮��.
		// ѡ��ģ���ִ���׺�
		String cap=(String)request.getSession().getAttribute("f_name");
		poCtrl1.setCaption(cap);
		// ����ģ�壬����Ϊ��ʽ���ĵ��ļ�����zhengshi.doc
	    fRandName = request.getParameter("frand_name");
	    taohongtemp = request.getParameter("taohongtemp");
		String urls = tempPath+"\\"+fRandName;
		//String templateName = request.getParameter("mb");
		String mbPath =taohongPath+"\\"+mbName;
		String taohongtempPath=tempPath+"\\"+taohongtemp;
			
		copyFile(mbPath, urls); 

		// ������ݺ��������ݵ������������ļ�
		WordDocument doc = new WordDocument();
		DataRegion copies = doc.openDataRegion("PO_Copies");
		copies.setValue("6");
		DataRegion docNum = doc.openDataRegion("PO_DocNum");
		docNum.setValue("001");
		DataRegion issueDate = doc.openDataRegion("PO_IssueDate");
		issueDate.setValue("2013-5-30");
		DataRegion issueDept = doc.openDataRegion("PO_IssueDept");
		issueDept.setValue("��ܿ�");
		DataRegion sTextS = doc.openDataRegion("PO_STextS");
		sTextS.setValue("[word]"+taohongtempPath+"[/word]");
		DataRegion sTitle = doc.openDataRegion("PO_sTitle");
		sTitle.setValue("�ϳ��̲ݹ�˾�ļ�");
		DataRegion topicWords = doc.openDataRegion("PO_TopicWords");
		topicWords.setValue("Pageoffice�� �׺�");
		poCtrl1.setWriter(doc);
		
		
	}//�״ε���׺�֮���ftp�����������ĵ���web�������ء�
	 else{
	 				Random random = new Random(System.currentTimeMillis());
				taohongtemp="Taohong"+random.nextInt()+".doc";//�������أ���ʱ�ļ���ͬ
				request.getSession().setAttribute("taohongtemp",taohongtemp);
				String oldPath=templatePath;
			    String newPath=tempPath + "\\"+taohongtemp;
				try { 
           			int bytesum = 0; 
           			int byteread = 0; 
           			File oldfile = new File(oldPath);
           			if (oldfile.exists()) { //�ļ�����ʱ 
               			InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ� 
               			FileOutputStream fs = new FileOutputStream(newPath); 
               			byte[] buffer = new byte[1444]; 
               			int length; 
               			while ( (byteread = inStream.read(buffer)) != -1) { 
                   			bytesum += byteread; //�ֽ��� �ļ���С 
                   			fs.write(buffer, 0, byteread); 
               			} 
               			inStream.close(); 
           			} 
           		}catch (Exception e) { 
           			System.out.println("���Ƶ����ļ���������"); 
           			e.printStackTrace(); 
       		} 
       		
       		String f_name = request.getParameter("f_name");
       		request.getSession().setAttribute("f_name",f_name);
       		poCtrl1.setCaption(f_name);
       		
	    	String f_url = request.getParameter("f_url");
	    	request.getSession().setAttribute("url",f_url);	
	    	String f_id = request.getParameter("f_id");    
	    	request.getSession().setAttribute("id",f_id);	
	    	FtpFile file=new FtpFile();
			file.setPath(f_url);
			file.setId(f_id);
			file.setContenttpye("0");//0  ����word�ĵ������滹Ҫ�ĵĸ���׼һ��
	    	fRandName= ftp.FtpDownload(file,tempPath);//���ļ����ص�doc
	    	request.getSession().setAttribute("fRandName",fRandName);
	    	String urls = tempPath+"\\"+fRandName;
	       
       		copyFile(urls,newPath);//����ʱ�ļ����Ƶ�web�������µ�doc�ļ�������������һ�ļ��С�
	}
	
	 String urls=tempPath+"\\"+fRandName;
	 poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "����");
	 String dels=fRandName+","+taohongtemp+",";
	 request.getSession().setAttribute("delpath",dels);//�Ա�ɾ����ʱ�ļ��á�
	
	 poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	  ��ʾ��ԭ�ļ���
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title></title>
		<link href="../images/csstg.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	//��ʼ����ģ���б�
	function load() {
		if (getQueryString("mb") != null)
			document.getElementById("templateName").value = getQueryString("mb");
	}

	//��ȡurl���� 
	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		else
			return null;
	}

	//�׺�
	function taoHong() {
		var mb = document.getElementById("templateName").value;
		//document.getElementById("PageOfficeCtrl1").WebSave();
		document.getElementById("form1").action = "TaoHong.jsp?mb=" + mb+"&frand_name="+"<%=request.getSession().getAttribute("fRandName")%>" + "&taohongtemp=" + "<%=request.getSession().getAttribute("taohongtemp")%>";

		document.forms[0].submit();
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
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
                //����
        function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();
        }

	//�ر�
	function Close() {
		AfterDocumentOpened();
		location.href = "WordManage.jsp";
	}
</script>
	</head>
	<body onload="load();" >
		<div id="content">
			<div id="textcontent" style="width: auto; height: 800px;">
				<div class="flow4">
					<a href="WordManage.jsp"> <img alt="����" src="images/return.gif"
							border="0" />�ļ��б�</a>
					<span style="width: 100px;"> </span><strong>�ĵ����⣺</strong>
					<span style="color: Red;">�����ļ�</span>
					<form method="post" id="form1">
						<strong>ģ���б�</strong>
						<span style="color: Red;"> <select name="templateName"
								id="templateName" style='width: 240px;'>
								<option value='temp2008.doc' selected="selected">
									ģ��һ
								</option>
								<option value='temp2009.doc'>
									ģ���
								</option>
								<option value='temp2010.doc'>
									ģ����
								</option>
							</select> </span>
					</form>
				</div>
				<!--**************   ׿�� PageOffice��� ************************-->

				<po:PageOfficeCtrl id="PageOfficeCtrl1" />
			</div>
		</div>

	</body>
</html>
