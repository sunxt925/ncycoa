<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.FileMakerCtrl,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,com.entity.ftp.*,com.ftp.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
  String path = getServletContext().getRealPath("/")+"doc";
  String storefileno=request.getParameter("storefileno");
  	FtpStoreFile file=new FtpStoreFile(storefileno);
	String contenttype=file.getFilecontenttype();   
				FtpStore ftp=new FtpStore();
    			String frand_name=ftp.FtpDownload(file,path);  //��ftp���������ص�web����������ʱ�ļ��У�������Ϊ������������֡�
    			String urls=path+"\\"+frand_name;
			FileMakerCtrl fmCtrl = new FileMakerCtrl(request);
			fmCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
			WordDocument doc = new WordDocument();
			//�����һ��¼�
			//doc.setDisableWindowRightClick(true);
			//����������ֵ������������䵽ģ������Ӧ��λ��
			doc.openDataRegion("PO_company").setValue("");
			fmCtrl.setSaveFilePage("std_saveaspdf.jsp");
			fmCtrl.setWriter(doc);
			fmCtrl.setJsFunction_OnProgressComplete("openpdf()");
			String pdfname=frand_name.substring(0,frand_name.length()-4);
    		pdfname=pdfname+".pdf";
    		String url=path+"\\"+"template.doc";
    		if(frand_name!=null){
    			if(contenttype.equals("doc")){
	    			fmCtrl.fillDocumentAsPDF(urls, DocumentOpenType.Word, pdfname);
	    		}else if(contenttype.equals("ppt")){
	    			fmCtrl.fillDocumentAsPDF(urls, DocumentOpenType.PowerPoint, pdfname);
	    		}else if(contenttype.equals("xls")){
	    			fmCtrl.fillDocumentAsPDF(urls, DocumentOpenType.Excel, pdfname);
	    		}
			}
			fmCtrl.setTagId("FileMakerCtrl1"); //���б���
	//fmCtrl.setTagId("FileMakerCtrl1"); //���б���
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

	</head>		
	<script language="javascript" type="text/javascript">
	
	function openpdf()
{       window.close();
   var pdfname = document.getElementById('pdfname').value;
   var docname = document.getElementById('docname').value;
   var newurl = 'localopenpdf.jsp?pdfname='+pdfname+'&docname='+docname;
   window.showModalDialog(newurl,window,"dialogWidth=1500px;dialogHeight=1200px");
}
</script>
	<body>
		<div >


			<!--**************   ׿�� PageOffice �ͻ��˴��뿪ʼ    ************************-->
              <input name="pdfname" type="hidden" id="pdfname" value="<%=pdfname%>" >
              <input name="docname" type="hidden" id="docname" value="<%=frand_name%>" >

			<!--**************   ׿�� PageOffice �ͻ��˴������    ************************-->
			<po:FileMakerCtrl id="FileMakerCtrl1"/>

		</div>

	</body>
</html>
