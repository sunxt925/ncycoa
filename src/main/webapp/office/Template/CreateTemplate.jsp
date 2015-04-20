<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
//******************************卓正PageOffice组件的使用*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	//隐藏菜单栏
	poCtrl1.setMenubar(false);
	//隐藏工具栏
	poCtrl1.setCustomToolbar(false);
	//设置保存页面
	poCtrl1.setSaveFilePage("SaveNewTemplate.jsp");
	//String fnew_url=request.getParameter("f_url"); //文件在ftp服务器下的目录。
	//request.getSession().setAttribute("new_url",fnew_url);
	//新建Word文件，webCreateNew方法中的两个参数分别指代“操作人”和“新建Word文档的版本号”
	String tem=request.getParameter("mb");
	//System.out.println("kkkkkkkkkkkkkk     "+tem);
	if(tem!=null&&tem.length()>0){
		request.getSession().setAttribute("type",tem);
		if(tem.equals("0")){
			poCtrl1.webCreateNew("张佚名",DocumentVersion.Word2003);
		}else if(tem.equals("1")){
			poCtrl1.webCreateNew("张佚名",DocumentVersion.Excel2003);
		}else if(tem.equals("2")){
			poCtrl1.webCreateNew("张佚名",DocumentVersion.PowerPoint2003);
		}
			
	}else{
		request.getSession().setAttribute("type","0");
	    poCtrl1.webCreateNew("张佚名",DocumentVersion.Word2003);
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
		            if(document.getElementById("PageOfficeCtrl1").CustomSaveResult=="ok"){
			            alert('保存成功！');
			            location.href = "TemplateManage.jsp";
		            }else{
		            	alert('保存失败！');
		            }
		        }
		
		        function Cancel() {
		            location.href = "TemplateManage.jsp";
		        }
		
		        function getFocus() {
		            var str = document.getElementById("FileSubject").value;
		            if (str == "请输入文档主题") {
		                document.getElementById("FileSubject").value = "";
		            }
		        }
		        function lostFocus() {
		            var str = document.getElementById("FileSubject").value;
		            if (str.length <= 0) {
		                document.getElementById("FileSubject").value = "请输入文档主题";
		            }
		        }
		        function show(val){
		                window.open("CreateTemplate.jsp?mb=" + val,"_self");
		        }
		    </script>
	</head>
	<body>
		<form id="form2" action="CreateWord.aspx">
			<div id="content">
				<div id="textcontent" style="width: auto; height: auto;">
					<div class="flow4">
						<span style="width: 100px;"> &nbsp; </span>
					</div>
					<div>
						文档主题：
						<input name="FileSubject" id="FileSubject" type="text"
							onfocus="getFocus()" onBlur="lostFocus()" class="boder"
							style="width: 180px;" value="请输入文档主题" />
						<input type="button" onClick="Save()" value="保存" />
						<input type="button" onClick="Cancel()" value="取消" />
						模板类型:
						<select name="type" id="type" onChange="show(this.value)">
							<option value="0">word模板</option>
							<option value="1">excel模板</option>
							<option value="2">ppt模板</option>
						</select>
					</div>
					<div>
						&nbsp;
					</div>
					<po:PageOfficeCtrl id="PageOfficeCtrl1" />
				</div>
			</div>
			<div id="footer">
				<hr width="1000px;" />
				
			</div>
		</form>
	</body>
</html>
