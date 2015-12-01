<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<%
String path = request.getContextPath();
%>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>南充烟草专卖局</title>
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
	createdetailwindow(title,url,width,height);
}
function operate(title, actionUrl, gname) {
	var rows = null;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('请选择');
		return;
	}
	
	var ids = '';
	for(var i=0; i<rows.length; i++) {
		ids += rows[i].id + ',';
	}
	if(actionUrl.indexOf("?") == -1) {
		actionUrl += '?id='+ ids;
	} else {
		actionUrl += '&id='+ ids;
	}
	createdialog('操作确认', '确定如此操作吗 ?', actionUrl, gname);
}
function download(title, actionUrl, gname){
	var rows = null;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('请选择');
		return;
	}
	
	var ids = '';
	for(var i=0; i<rows.length; i++) {
		ids += rows[i].id + ',';
	}
	actionUrl += '&id='+ ids;
	window.open(actionUrl);
}
</script>
<body>
	<h:datagrid actionUrl="stdget.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="stdgetlist">
		<h:dgColumn field="id" title="id" hidden="true" align="center"  width="50"></h:dgColumn>
		<h:dgColumn field="gstFilecode" title="文件编号" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstFilename" title="文件名称" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstFiletype" title="文件类型" replace="法律法规_0,规范性文件_1,外来标准_2" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstFilelevel" title="文件层级" replace="国家_0,地方_1,行业_2" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstPublictime" title="发布时间" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstYear" title="年度" query="true" align="center"  width="40"></h:dgColumn>
		<h:dgColumn field="gstStaffname" title="录入人员" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstStafforg" title="管理部门" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstOperate" title="处理状态" replace="未处理_0,作废_1,全文引用_2,部分引用_3,转化为标准_4" style="color:red_0,color:blue_1,color:green_2,color:red_3,color:blue_4" query="true"  align="center"  width="80"></h:dgColumn>
		<h:dgToolBar url="stdget.htm?detail" icon="icon-reload" funname="detailcanoperate" title="采标内容查看"></h:dgToolBar>
		<h:dgToolBar url="stdget.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?quote0" icon="icon-remove" funname="operate" title="作废"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?quote1" icon="icon-reload" funname="operate" title="全文引用"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?quote2" icon="icon-reload" funname="operate" title="部分引用"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?turntostd" icon="icon-reload" funname="update" title="转化为标准"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?download" icon="icon-remove" funname="download" title="下载采标文件"></h:dgToolBar>
 		
	</h:datagrid>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='inputDate_begin']").attr("class","easyui-datebox");
		$("input[name='inputDate_end']").attr("class","easyui-datebox");
	});
</script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
</html>
