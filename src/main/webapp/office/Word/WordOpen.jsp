<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String path = getServletContext().getRealPath("/")+"doc";
    FtpUse ftp=new FtpUse();
    
//******************************卓正PageOffice组件的使用*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
//	poCtrl1.setServerPage("poserver.do");
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //文件打开之后响应函数，以删除临时文件夹下文件。
	//隐藏菜单栏
	poCtrl1.setMenubar(true);
	poCtrl1.setCaption("南充烟草office平台");
	poCtrl1.addCustomToolButton("保存","Save()",1);
	poCtrl1.addCustomToolButton("另存为PDF文件", "SaveAsPDF()", 1);
	poCtrl1.addCustomToolButton("另存", "SaveElse()", 1);
	poCtrl1.addCustomToolButton("插入印章","CustomToolBar_InsertSeal()",2);
	poCtrl1.addCustomToolButton("领导圈阅","CustomToolBar_HandDraw()",3);
	//
	poCtrl1.addCustomToolButton("显示痕迹", "ShowRevisions", 5);
    poCtrl1.addCustomToolButton("隐藏痕迹", "HiddenRevisions", 5);
	poCtrl1.addCustomToolButton("插入键盘批注", "StartRemark", 3);
	
	poCtrl1.addCustomToolButton("关闭", "ShutDown()", 1);
	//设置保存页面
	poCtrl1.setSaveFilePage("SaveFile.jsp");
	request.getSession().setAttribute("savekind","save");
	//打开Word文件
	String f_url=request.getParameter("f_url"); //文件在ftp服务器下的目录。
	String f_name=request.getParameter("f_name");//文件的真实名字。
	poCtrl1.setCaption(f_name);
	String f_id=request.getParameter("f_id");//文件的id。
    request.getSession().setAttribute("url",f_url);
    request.getSession().setAttribute("name",f_name); 
	request.getSession().setAttribute("id",f_id);
	FtpFile file=new FtpFile();
	file.setPath(f_url);
	file.setId(f_id);
	file.setContenttpye("0");//0  代表word文档，后面还要改的更标准一点
    String frand_name=ftp.FtpDownload(file,path);  //从ftp服务器下载到web服务器的临时文件夹，并命名为随机产生的名字。
    String urls=path+"\\"+frand_name;
  //  System.out.println("ooooooooooo  "+frand_name);
    request.getSession().setAttribute("delpath",frand_name);//供删除临时文件夹使用.
    if(frand_name!=null){
	    poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "张三");
	}else{
	    System.out.println("下载返回为空 啦啦啦啦啦啦啦啦啦啦");
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
        //另存为PDF文件
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
            //alert('保存成功！'); 
        }
        function ShutDown(){
            window.open("WordManage.jsp","_self");
        }
        //插入印章。
        function CustomToolBar_InsertSeal() {
	    	alert("请使用此用户的印章测试\r\n用户名：李志 \r\n初始密码：111111");
        	var zoomseal = document.getElementById("PageOfficeCtrl1").ZoomSeal;
	  		if (zoomseal != null)
	       		zoomseal.AddSeal();
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
		
		
		function AfterDocumentOpened() {
					        //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // 禁止保存
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // 禁止另存
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, false); //禁止打印
            //document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // 禁止页面设置
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../../servlet/DeleteDocFile",false);
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
