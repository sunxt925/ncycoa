<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*"
	pageEncoding="gb2312"%>
	<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
	<%@page import="com.common.Util"%>
	<%@page import="com.common.FileUpload"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String path = getServletContext().getRealPath("/");
    
//******************************卓正PageOffice组件的使用*******************************
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	//	poCtrl1.setServerPage("poserver.do");
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
			//隐藏Office工具条
	    poCtrl1.setOfficeToolbars(false);
		//隐藏菜单栏
		poCtrl1.setMenubar(false);
		poCtrl1.addCustomToolButton("保存","Save()",1);
		poCtrl1.addCustomToolButton("另存为PDF文件", "SaveAsPDF()", 1);
		poCtrl1.addCustomToolButton("另存", "SaveElse()", 1);
		poCtrl1.addCustomToolButton("插入印章","CustomToolBar_InsertSeal()",2);
		poCtrl1.addCustomToolButton("领导圈阅","CustomToolBar_HandDraw()",3);
		//
		poCtrl1.addCustomToolButton("显示痕迹", "ShowRevisions", 5);
	    poCtrl1.addCustomToolButton("隐藏痕迹", "HiddenRevisions", 5);
		poCtrl1.addCustomToolButton("插入键盘批注", "StartRemark", 3);
		poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //文件打开之后响应函数，以删除临时文件夹下文件。
		poCtrl1.addCustomToolButton("关闭", "ShutDown()", 1);
		//设置保存页面
		poCtrl1.setSaveFilePage("SaveFile.jsp");
		//设置禁止拷贝
		poCtrl1.setAllowCopy(false);
		poCtrl1.setSaveFilePage(path+"office/Word/SaveFile.jsp");
		poCtrl1.setCaption("南充烟草office平台");
		//设置保存页面
		String policypath=request.getParameter("policypath");
		FileUpload fu=new FileUpload(); 
		String urls=path+"doc/"+policypath;
		FileUpload.copyFile(Util.getfileCfg().get("uploadfilepath")+policypath,urls);
		request.getSession().setAttribute("delpath",policypath);//供删除临时文件夹使用.
		String type=policypath.substring(policypath.length()-4,policypath.length());
			if(type.equals(".doc")||type.equals("docx")){
    			poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "张三");
    		}else if(type.equals(".xls")||type.equals("xlsx")){
    			poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "张三");
    		}
		poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="../images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        var xmlHttp;
		function createXMLHttp(){
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest() ;
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") ;
			}
		}
		function CallBack(){
			if(xmlHttp.readyState==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}
			function AfterDocumentOpened() {
// 					    document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // 禁止保存
//             			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // 禁止另存
//             			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, false); //禁止打印
//             			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // 禁止页面设置
	 		   createXMLHttp();
	 		   xmlHttp.open("POST","../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
		

    </script>

</head>
<body>
    <form id="form2">
    <div id="content">
        <div id="textcontent" style="width: auto; height: auto;">
			<!-- ****************************PageOffice 组件客户端编程************************************* -->

		   <!-- ****************************PageOffice 组件客户端编程结束************************************* -->
		   <po:PageOfficeCtrl id="PageOfficeCtrl1" />
        </div>
    </div>

    </form>
</body>
</html>