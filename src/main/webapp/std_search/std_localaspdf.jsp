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
    			String frand_name=ftp.FtpDownload(file,path);  //从ftp服务器下载到web服务器的临时文件夹，并命名为随机产生的名字。
    			String urls=path+"\\"+frand_name;
			FileMakerCtrl fmCtrl = new FileMakerCtrl(request);
			fmCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
			WordDocument doc = new WordDocument();
			//禁用右击事件
			//doc.setDisableWindowRightClick(true);
			//给数据区域赋值，即把数据填充到模板中相应的位置
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
			fmCtrl.setTagId("FileMakerCtrl1"); //此行必须
	//fmCtrl.setTagId("FileMakerCtrl1"); //此行必须
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


			<!--**************   卓正 PageOffice 客户端代码开始    ************************-->
              <input name="pdfname" type="hidden" id="pdfname" value="<%=pdfname%>" >
              <input name="docname" type="hidden" id="docname" value="<%=frand_name%>" >

			<!--**************   卓正 PageOffice 客户端代码结束    ************************-->
			<po:FileMakerCtrl id="FileMakerCtrl1"/>

		</div>

	</body>
</html>
