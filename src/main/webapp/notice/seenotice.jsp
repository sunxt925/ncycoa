<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构管理</title>
<!--框架必需start-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>
<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script> 
<script language="JavaScript" src="<%=path%>/js/form/MyDatePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
<style type="text/css"> 
table.table1{
    font-size: 16px;
    font-weight: bold;
    line-height: 0.6em;
    font-style: normal;
    border-collapse:separate;
}
.table1 thead th{
    padding:6px;
    text-align:center;
    color:#666;
    text-shadow:1px 1px 1px #fff;
    border:2px solid #000000;
}
.table1 thead th:empty{
    background:transparent;
    border:none;
}
.table1 tbody th{
    color:#fff;
    text-shadow:1px 1px 1px #fff;
    background-color:#9DD929;
    border:2px solid #000000;
    border-right:3px solid #9ED929;
}
.table1 tbody td{
    padding:6px;
    text-align:center;
    border: 2px solid #000000;
    color:#666;
    text-shadow:1px 1px 1px #fff;
}
</style>

<script type="text/javascript">
    function show(){
    	//var temp=request.getParameter("id");
    	alert(temp);
    }
</script>
</head>
<body>
<form name="form1" id="form1" action="notice!addNotice.action" target="noticemanage" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>标题：</td><td>
			${notice.title}
			</td>
		</tr>
		<tr>
			<td>作者：</td><td>
			${notice.author}
			</td>
		</tr>
		<tr>
			<td>发布时间：</td><td>
			${notice.publishTime}
			</td>
		</tr>
		<tr style="display:none">
			<td>发布时间：</td>
			<td>
			</td>
		</tr>
	</table>
	<div>
	${notice.content}
	</div>
	</form>	
</body>
</html>