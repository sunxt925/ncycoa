<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<%
String path = request.getContextPath();
%>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>四川南充烟草专卖</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/datagrid.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>

<script type="text/javascript">
function detailcanoperate(title,url, id,width,height) {
	var rowsData = $('#'+id).datagrid('getSelections');
//	if (rowData.id == '') {
//		tip('锟斤拷选锟斤拷榭达拷锟侥�');
//		return;
//	}
	
	if (!rowsData || rowsData.length == 0) {
		tip('请选择查看项目');
		return;
	}
	if (rowsData.length > 1) {
		tip('请选择一条记录再查看');
		return;
	}
    url += '&id='+rowsData[0].id;
	window.open(url,"评审方案内容");
}
</script>
<body>
	<h:datagrid actionUrl="checkproject.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="stdgetlist">
		<h:dgColumn field="id" title="id" hidden="true" align="center"  width="50"></h:dgColumn>
		<h:dgColumn field="checkCode" title="方案编号" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="checkName" title="方案名称" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="staffName" title="发起人" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="startTime" title="发起时间" query="true" align="center" dateFormatter="yyyy-MM-dd HH:mm:ss" width="80" queryMode="scope"></h:dgColumn>
		<h:dgToolBar url="checkproject.htm?openoffice" icon="icon-reload" funname="detailcanoperate" title="查看评审方案"></h:dgToolBar>
		<h:dgToolBar url="checkproject.htm?del" icon="icon-remove" funname="del" title="删除评审方案"></h:dgToolBar>
<%--  		<h:dgToolBar url="stdget.htm?add" icon="icon-add" funname="add" title="上传多个文件�"></h:dgToolBar> --%>
	</h:datagrid>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='startTime_begin']").attr("class","easyui-datebox");
		$("input[name='startTime_end']").attr("class","easyui-datebox");
		
	});
</script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
</html>
