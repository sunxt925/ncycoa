<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.excelwriter.*,java.awt.*,com.ftp.*,com.entity.ftp.*,java.io.*;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    String f_url=request.getParameter("f_url");//文件在ftp服务器下的目录。
	String f_name=request.getParameter("f_name");//文件的真实名字。
	String f_id = request.getParameter("f_id");//文件的id。
	if (f_id == null || f_id == "") {
		out.println("<script>alert('未获得文件ID号！');location.href='ExcelManage.jsp'</script>");
	}
    String path = getServletContext().getRealPath("/")+"doc";
    FtpUse ftp=new FtpUse();
    
//******************************卓正PageOffice组件的使用*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
//	poCtrl1.setServerPage("poserver.do");
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()");
	//隐藏菜单栏
	poCtrl1.setMenubar(true);
	poCtrl1.setCaption(f_name);
	//设置保存页面
	poCtrl1.setSaveFilePage("SaveFile.jsp");
	request.getSession().setAttribute("savekind","save");
	//添加自定义菜单栏
		poCtrl1.setCustomMenuCaption("自定义菜单");
		poCtrl1.addCustomMenuItem("显示标题","CustomMenuItem1_Click()",true);
		poCtrl1.addCustomMenuItem("领导圈阅","CustomMenuItem2_Click()",true);
		
		//添加自定义工具栏
		poCtrl1.addCustomToolButton("保存","Save()",1);
	    poCtrl1.addCustomToolButton("另存为PDF文件", "SaveAsPDF()", 1);
	    poCtrl1.addCustomToolButton("另存", "SaveElse()", 1);
		poCtrl1.addCustomToolButton("插入印章","CustomToolBar_InsertSeal()",2);
		poCtrl1.addCustomToolButton("领导圈阅","CustomToolBar_HandDraw()",3);
		poCtrl1.addCustomToolButton("全屏/还原","CustomToolBar_FullScreen()",4);
	    poCtrl1.addCustomToolButton("关闭", "ShutDown()", 1);
	
	//打开Excel文件
	poCtrl1.setCaption(f_name);
    request.getSession().setAttribute("url",f_url);
    request.getSession().setAttribute("name",f_name);
	request.getSession().setAttribute("id",f_id);
	//System.out.println("uuuuuuuuuuuuu    "+f_url+"//"+f_id);
	FtpFile file=new FtpFile();
	file.setPath(f_url);
	file.setId(f_id);
	file.setContenttpye("1");//0  代表excel文档，后面还要改的更标准一点
    String frand_name=ftp.FtpDownload(file,path);  //从ftp服务器下载到web服务器的临时文件夹，并命名为随机产生的名字。
    String urls=path+"\\"+frand_name;
  //  System.out.println("ooooooooooo  "+frand_name);
    request.getSession().setAttribute("delpath",frand_name);//供删除临时文件夹使用.
    if(frand_name!=null){
	    poCtrl1.webOpen(urls, OpenModeType.xlsNormalEdit, "张三");    
	}else{
	    System.out.println("下载excel返回为空 啦啦啦啦啦啦啦啦啦啦");
	}
	poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="../../images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
       function CustomMenuItem1_Click() {
		     alert("该菜单的标题是：" + document.getElementById("PageOfficeCtrl1").Caption);
		};
					    
	   function CustomMenuItem2_Click() {
		  document.getElementById("PageOfficeCtrl1").HandDraw.Start();
		};   
					   
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
        function CustomToolBar_InsertSeal() {
	    	alert("请使用此用户的印章测试\r\n用户名：李志 \r\n初始密码：111111");
        	var zoomseal = document.getElementById("PageOfficeCtrl1").ZoomSeal;
	  		if (zoomseal != null)
	       		zoomseal.AddSeal();
	    }
						
	   function CustomToolBar_HandDraw() {
			document.getElementById("PageOfficeCtrl1").HandDraw.Start();
		}
						
       function CustomToolBar_FullScreen() {
			document.getElementById("PageOfficeCtrl1").FullScreen = !document.getElementById("PageOfficeCtrl1").FullScreen;
	  }
			        
        function ShutDown(){
            window.open("ExcelManage.jsp","_self");
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
    <form id="form2" name="form2">
    <div id="content">
        <div id="textcontent" style="width: auto; height: auto;">
			<!-- ****************************PageOffice 组件客户端编程************************************* -->

		   <!-- ****************************PageOffice 组件客户端编程结束************************************* -->
		   <po:PageOfficeCtrl id="PageOfficeCtrl1" />
        </div>
    </div>
<input name="savekind" type="hidden" id="savekind" value="1">
    </form>
</body>
</html>
