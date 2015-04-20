<%@ page language="java" import="java.util.*,com.db.*,com.entity.ftp.*,com.ftp.*,com.zhuozhengsoft.pageoffice.*" pageEncoding="utf-8"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
String path0 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path0+"/";
%>
<%
//******************************卓正PageOffice组件的使用*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须	
		//隐藏菜单栏
	poCtrl1.setMenubar(true);
WordDocument doc = new WordDocument();  

String sid=request.getParameter("sid");
String[] rsid=sid.split(",");
String path = getServletContext().getRealPath("/")+"doc";
FtpUse ftp=new FtpUse();
String nameString="";
String frand_name=null;
String MergeName=rsid[rsid.length-1];
request.getSession().setAttribute("mergename",MergeName);
String f_url=null;
for(int j=0;j<rsid.length-1;j++){
	DBObject db = new DBObject();
	String sql="select * from system_ftp where file_id=?";
	Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]{ new Parameter.String(rsid[j])};
	DataTable dt = db.runSelectQuery(sql, pp);
	//DataTable dt=db.runSelectQuery(sql);
	if (dt!=null && dt.getRowsCount()>0){
				DataRow r=dt.get(0);
				f_url=r.getString("file_url");
				FtpFile file=new FtpFile();
				file.setPath(f_url);
				file.setId(rsid[j]);
				file.setContenttpye("0");//0  代表word文档，后面还要改的更标准一点
    			frand_name=ftp.FtpDownload(file,path);  //从ftp服务器下载到web服务器的临时文件夹，并命名为随机产生的名字。
    			//System.out.println("ppppppp    "+frand_name);
	}
	//创建数据区域，createDataRegion 方法中的三个参数分别代表“新建的数据区域名称”，“数据区域将要插入的位置”，
	//“与新建的数据区域相关联的数据区域名称”，若当前Word文档中尚无数据区域（书签）或者想在文档的最开头创建时，那么第三个参数为“[home]”
	//若想在文档的结尾处创建数据区域则第三个参数为“[end]”
	if(j==0){
	      DataRegion dataRegion =  doc.createDataRegion("PO_p"+j,DataRegionInsertType.After,"[home]");
	      //设置创建的数据区域的可编辑性
	      dataRegion.setEditing(true);
	      //给数据区域赋值
	     // dataRegion.setValue("第一个数据区域\r\n");
	}else{
	      DataRegion dataRegion =  doc.createDataRegion("PO_p"+j,DataRegionInsertType.After,"PO_p"+(j-1));
	      //设置创建的数据区域的可编辑性
	      dataRegion.setEditing(true);
	      //给数据区域赋值
	   //   dataRegion.setValue("第一个数据区域\r\n");
	}
	DataRegion data1 = doc.openDataRegion("PO_p"+j);
	String urls = path+"\\"+frand_name;
	data1.setValue("[word]" +urls+ "[/word]");
	nameString=nameString+frand_name+",";
}
poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //文件打开之后响应函数，以删除临时文件夹下文件。
request.getSession().setAttribute("delpath",nameString);//供删除临时文件夹使用.
request.getSession().setAttribute("FM_url",f_url);
poCtrl1.addCustomToolButton("保存","Save()",1);
poCtrl1.addCustomToolButton("关闭", "ShutDown()", 1);
//设置保存页面
poCtrl1.setSaveFilePage("MergeSaveFile.jsp");
poCtrl1.setWriter(doc);
Random random = new Random(System.currentTimeMillis());
//String tempname=(String)request.getSession().getAttribute("tempname");
String urls=path+"\\"+"template.doc";
poCtrl1.webOpen(urls, OpenModeType.docNormalEdit, "张三");
poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
        function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();    
            //alert('保存成功！');
        }
        function ShutDown(){
            window.open("WordManage.jsp","_self");
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
    <title>My JSP 'Merge.jsp' starting page</title>
    
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
