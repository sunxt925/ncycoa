<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,com.entity.stdapply.*,java.io.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.DataRegion"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.WordDocument"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%!
// 拷贝文件
public void copyFile(String oldPath, String newPath){
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //文件存在时 
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小 
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread); 
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
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

String tempPath = getServletContext().getRealPath("/")+"applytabletemp";//临时文件的路径
String tempname=tempPath+"\\tabletemp.doc";
String applytablepath=tempPath+"\\"+applyid+".doc";
copyFile(tempname,applytablepath);





    String storefileno=request.getParameter("storefileno");
    String path = getServletContext().getRealPath("/")+"doc";
    
//******************************卓正PageOffice组件的使用*******************************
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	//	poCtrl1.setServerPage("poserver.do");
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
		poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //文件打开之后响应函数，以删除临时文件夹下文件。
			//隐藏Office工具条
	    poCtrl1.setOfficeToolbars(false);
		//隐藏菜单栏
		poCtrl1.setCustomToolbar(true);
		poCtrl1.setMenubar(true);
		//设置禁止拷贝
		poCtrl1.setAllowCopy(false);
		//设置保存页面
		
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
    	//request.getSession().setAttribute("delpath",filename);//供删除临时文件夹使用.
    	if(filename!=null){
	    			poCtrl1.webOpen(applytablepath, OpenModeType.docNormalEdit, "张三");
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
					    document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // 禁止保存
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