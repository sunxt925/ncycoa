<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,com.db.*,java.awt.*,com.entity.system.*,com.ftp.*,com.entity.ftp.*,com.entity.stdapply.*,java.io.*"
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
DocApplyPerson applyer=new DocApplyPerson(Integer.valueOf(applyid));
String applyorg=applyer.getApplyapart();
String applydate=applyer.getApplydate().substring(0,10);
String applyreason=applyer.getApplyreason();
DocReviseInifo revise=new DocReviseInifo();
String doccodestring=applyer.getApplydoccode();
String docnamestring=applyer.getApplydocname();

DocApplySuggest applysuggest=new DocApplySuggest();
DataTable dt=applysuggest.getAllApplySuggest(applyid);
String technologysug="";
String weiyuansug="";
String apporgregion="";
for(int i=0;i<dt.getRowsCount();i++){
	String where=dt.get(i).getString("wheresug");
	String approstaffcode=dt.get(i).getString("sugstaffcode");
	StaffInfo staffinfo=new StaffInfo(approstaffcode);
	String sugstaffname=staffinfo.getName();
	String suggest=dt.get(i).getString("suggestion");
	String sugdate=dt.get(i).getString("sugdate");
	if(where.equals("weiyuan")){
		weiyuansug=weiyuansug+"  "+sugdate+sugstaffname+":"+suggest;
	}else{
		technologysug=technologysug+"  "+sugdate+sugstaffname+":"+suggest;
		if(where.equals("yingxiao")){
			apporgregion="营销技术委员会意见";
		}else if(where.equals("wuliu")){
			apporgregion="物流技术委员会意见";
		}else if(where.equals("zhuanmai")){
			apporgregion="专卖技术委员会意见";
		}else if(where.equals("anquan")){
			apporgregion="安全技术委员会意见";
		}else if(where.equals("jichu")){
			apporgregion="基础管理技术委员会意见";
		}
	}
}



String tempPath = getServletContext().getRealPath("/")+"applytabletemp";//临时文件的路径
String tempPath2 = getServletContext().getRealPath("/")+"doc";
String tempname=tempPath+"\\企业标准修订申请表.doc";
String applytablepath=tempPath2+"\\"+applyid+".doc";
copyFile(tempname,applytablepath);





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
		DataRegion po_org = doc.openDataRegion("PO_orgapp");
		po_org.setValue(apporgregion);
		DataRegion advise1 = doc.openDataRegion("PO_advise1");
		advise1.setValue(technologysug);
		DataRegion advise2 = doc.openDataRegion("PO_advise2");
		advise2.setValue(weiyuansug);
		poCtrl1.setWriter(doc);
    String filename=applyid+".doc";
    	request.getSession().setAttribute("delpath",filename);//供删除临时文件夹使用.
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
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
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