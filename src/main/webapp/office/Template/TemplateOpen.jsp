<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String p=request.getParameter("file_path");
    String path = getServletContext().getRealPath("/")+p;
    
//******************************卓正PageOffice组件的使用*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);

	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须

	//隐藏菜单栏
	poCtrl1.setMenubar(true);
	poCtrl1.setCaption("南充烟草office平台");
	poCtrl1.addCustomToolButton("保存","Save()",1);
	poCtrl1.addCustomToolButton("关闭", "ShutDown()", 1);
	//设置保存页面
	poCtrl1.setSaveFilePage("SaveFile.jsp");
	//打开Word文件

	String f_name=request.getParameter("filename");//文件的真实名字。
	poCtrl1.setCaption(f_name);
    String urls= path+"\\"+f_name;
    String type=request.getParameter("file_type");
    if(type.equals("0")){
         poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "张三");
    }else if(type.equals("1")){
         poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "张三");
    }else if(type.equals("2")){
         poCtrl1.webOpen(urls, OpenModeType.pptNormalEdit, "张三");
    }
	
	poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="../../images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();    
            //alert('保存成功！');
        }
        function ShutDown(){
            window.open("TemplateManage.jsp","_self");
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
