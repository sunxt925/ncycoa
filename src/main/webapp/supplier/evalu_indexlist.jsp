<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body>
	<h:datagrid actionUrl="supplier.htm?evaluindexdgdata" fit="true" fitColumns="true" queryMode="group" name="evalu_indexlist">
		<h:dgColumn field="evaluYear" title="年度" query="true"></h:dgColumn>	
		<h:dgColumn field="indexCode" title="指标编码" sortable="true" query="true"></h:dgColumn>	
		<h:dgColumn field="indexName" title="指标名"></h:dgColumn>	
		<h:dgColumn field="indexOption" title="指标选项"></h:dgColumn>	
		<h:dgColumn field="instruction" title="说明" query="true"></h:dgColumn>	
		<h:dgToolBar url="supplier.htm?add_index" icon="icon-add" funname="add" title="添加指标定义"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?update_index" icon="icon-reload" funname="myedit" title="修改指标"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	
	function add(title, actionUrl, gname, width, height) {
		gridname=gname;
		createwindow(title, actionUrl, 600, 430);
	}
	
	function myedit(title, actionUrl, gname, width, height) {
		gridname=gname;
		var rows;
		try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
		try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
		
		if (!rows || rows.length==0) {
			tip('请先选择一条记录');
			return;
		}
		if (rows.length > 1) {
			tip('不能同时对多条记录编辑，请勾选一条记录');
			return;
		}
	
		if(actionUrl.indexOf("?") >= 0) {
			actionUrl += '&year='+ rows[0].evaluYear+"&code="+ rows[0].indexCode;
		} else {
			actionUrl += '?year='+ rows[0].evaluYear+"&code="+ rows[0].indexCode;
		}
		createwindow(title, actionUrl, 600, 430);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>