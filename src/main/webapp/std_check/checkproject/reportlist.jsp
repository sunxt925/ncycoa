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
	<h:datagrid actionUrl="checkproject.htm?dgreportdata" fit="true" fitColumns="true" queryMode="group" name="stdgetlist">
		<h:dgColumn field="id" title="id" hidden="true" align="center"  width="50"></h:dgColumn>
		<h:dgColumn field="aboutCommit" title="相关技术委员会" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="endTime" title="完成时限" query="true" align="center" dateFormatter="yyyy-MM-dd HH:mm:ss" width="80" queryMode="scope"></h:dgColumn>
		<h:dgToolBar url="checkproject.htm?openoffice2" icon="icon-reload" funname="detailcanoperate" title="查看评审报告"></h:dgToolBar>
		<h:dgToolBar url="checkproject.htm?delreport" icon="icon-remove" funname="del" title="删除评审报告"></h:dgToolBar>
	</h:datagrid>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='endTime_begin']").attr("class","easyui-datebox");
		$("input[name='endTime_end']").attr("class","easyui-datebox");
		
	});
</script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
</html>
