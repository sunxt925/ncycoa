<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,com.entity.stdapply.*,java.io.*"
	pageEncoding="gb2312"%>
	<%@page import="com.zhuozhengsoft.pageoffice.PDFCtrl"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String storefileno=request.getParameter("storefileno");
    String path = getServletContext().getRealPath("/")+"doc";
    
//******************************卓正PageOffice组件的使用*******************************
	DocApplyStore file=new DocApplyStore(storefileno);
	String contenttype=file.getFileContentType();
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	//	poCtrl1.setServerPage("poserver.do");
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
//		poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //文件打开之后响应函数，以删除临时文件夹下文件。
			//隐藏Office工具条
	    poCtrl1.setOfficeToolbars(true);
		//隐藏菜单栏
		poCtrl1.setCustomToolbar(true);
		poCtrl1.setMenubar(false);
		poCtrl1.addCustomToolButton("保存","Save()",1);
			poCtrl1.addCustomToolButton("领导圈阅","CustomToolBar_HandDraw()",3);
	//
	poCtrl1.addCustomToolButton("显示痕迹", "ShowRevisions", 5);
    poCtrl1.addCustomToolButton("隐藏痕迹", "HiddenRevisions", 5);
	poCtrl1.addCustomToolButton("插入键盘批注", "StartRemark", 3);
		//设置禁止拷贝
		poCtrl1.setAllowCopy(true);
		//设置保存页面
		poCtrl1.setSaveFilePage("SaveFile.jsp");
		request.getSession().setAttribute("savekind","save");
		String filename=file.getFileName();
		poCtrl1.setCaption(filename);
		String urls=file.getStoreDirURL();
    
    
        request.getSession().setAttribute("url",urls);
     //   request.getSession().setAttribute("name",filename); 
	    request.getSession().setAttribute("id",storefileno);
    	//request.getSession().setAttribute("delpath",filename);//供删除临时文件夹使用.
    	if(filename!=null){
    			if(contenttype.equals("doc")){
	    			poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "张三");
	    		}else if(contenttype.equals("ppt")){
	    			poCtrl1.webOpen(urls, OpenModeType.pptNormalEdit, "张三");
	    		}else if(contenttype.equals("xls")){
	    			poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "张三");
	    		}else if(contenttype.equals("vsd")){
	    			poCtrl1.webOpen(urls, OpenModeType.vsdNormalEdit, "张三");
	    		}
		}else{
	    		System.out.println("下载返回为空 ");
		}
		poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	
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
					    document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, true); // 禁止保存
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, true); // 禁止另存
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, true); //禁止打印
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, true); // 禁止页面设置
	 		 //  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		 //  xmlHttp.open("POST","../servlet/DeleteDocFile",false);
	 		 //  xmlHttp.onreadystatechange=CallBack;
	         //  xmlHttp.send(null);     
        }
		 function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();    
            //alert('保存成功！');
        }
       		//领导圈阅。			
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
		  //显示痕迹
        function ShowRevisions() {
            document.getElementById("PageOfficeCtrl1").ShowRevisions = true;
        }

        //隐藏痕迹
        function HiddenRevisions() {
            document.getElementById("PageOfficeCtrl1").ShowRevisions = false;
        }
        // 插入键盘批注
        function StartRemark() {
            var appObj = document.getElementById("PageOfficeCtrl1").WordInsertComment();

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