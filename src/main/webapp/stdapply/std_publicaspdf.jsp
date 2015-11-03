<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.FileMakerCtrl,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*"
	pageEncoding="gb2312"%>
<%@page import="com.entity.stdapply.DocApplyStore"%>
<%@page import="com.db.DataTable"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String docno=request.getParameter("docno");
    DocApplyStore applystore=new DocApplyStore();

	FileMakerCtrl fmCtrl = new FileMakerCtrl(request);
	fmCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
	WordDocument doc = new WordDocument();
	//�����һ��¼�
	doc.setDisableWindowRightClick(true);
	//����������ֵ������������䵽ģ������Ӧ��λ��
	doc.openDataRegion("PO_company").setValue("�ϳ��̲�ר����  ");
	fmCtrl.setSaveFilePage("/ncycoa/std/std_saveaspdf.jsp");
	fmCtrl.setWriter(doc);
	//fmCtrl.setJsFunction_OnProgressComplete("OnProgressComplete()");
	    DataTable dt= applystore.HaveFile(docno);
    	 if(dt.getRowsCount()!=0){
		 for(int i=0;i<dt.getRowsCount();i++){
			 		DocApplyStore applyfile=new DocApplyStore(dt.get(i).get(0).toString());
			 		DocApplyStore pdffile=new DocApplyStore();
			 		 String filename=applyfile.getFileName();
					 String type=filename.substring(filename.length()-4,filename.length());
					 String pdfname="";
					 String temp=applyfile.getStoreDirURL();
					 if(type.equals(".doc")){
	        				pdfname=filename.substring(0,filename.length()-4)+".pdf";
	        				fmCtrl.fillDocumentAsPDF(temp, DocumentOpenType.Word, pdfname);
	     			 }else if(type.equals("docx")){
	        				pdfname=filename.substring(0,filename.length()-4)+"pdf";
	        				fmCtrl.fillDocumentAsPDF(temp, DocumentOpenType.Word, pdfname);
	     			 }else if(type.equals(".dot")){
	        				pdfname=filename.substring(0,filename.length()-4)+".pdf";
	        				fmCtrl.fillDocumentAsPDF(temp, DocumentOpenType.Word, pdfname);
	     			 }else if(type.equals(".ppt")){
	         				pdfname=filename.substring(0,filename.length()-4)+".pdf";
	        				fmCtrl.fillDocumentAsPDF(temp, DocumentOpenType.PowerPoint, pdfname);
	     			 }else if(type.equals("xlsx")){
	         				pdfname=filename.substring(0,filename.length()-4)+"pdf";
	        				fmCtrl.fillDocumentAsPDF(temp, DocumentOpenType.Excel, pdfname);
	     			 }else if(type.equals(".xls")){
	         				pdfname=filename.substring(0,filename.length()-4)+".pdf";
	        				fmCtrl.fillDocumentAsPDF(temp, DocumentOpenType.Excel, pdfname);
	     			 }

	     			 pdffile.setCreatedate(applyfile.getCreatedate());
	     			 pdffile.setDocClass(applyfile.getDocClass());
	     			 pdffile.setDocNo(applyfile.getDocNo());
	     			 pdffile.setFileContentType("pdf");
	     			 pdffile.setFileName(pdfname);
	     			 pdffile.setLastUpdatedate(applyfile.getLastUpdatedate());
	     			 pdffile.setMemo(applyfile.getMemo());
	     			 pdffile.setSourceFlag(applyfile.getSourceFlag());
	     			 String pathtemp = getServletContext().getRealPath("/")+"UploadTemp";
	     			 java.util.Random r=new java.util.Random(); 
	         	     String s=String.valueOf(r.nextInt());
	         	     pdffile.setStoreDirURL(pathtemp+"\\"+s+pdfname);
	         	     	     			 request.getSession().setAttribute("pdfname",s+pdfname);
	         	     pdffile.Insert();
			}
			}
	fmCtrl.setTagId("FileMakerCtrl1"); //���б���

	//fmCtrl.setTagId("FileMakerCtrl1"); //���б���
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'FileMaker.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<div>
			<!--**************   ׿�� PageOffice �ͻ��˴��뿪ʼ    ************************-->

			<script language="javascript" type="text/javascript">
	function OnProgressComplete() {
		//window.parent.myFunc(); //���ø�ҳ���js����
	}
</script>
			<!--**************   ׿�� PageOffice �ͻ��˴������    ************************-->
			<po:FileMakerCtrl id="FileMakerCtrl1"/>

		</div>

	</body>
</html>
