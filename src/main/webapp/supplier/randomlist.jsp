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
	<h:datagrid actionUrl="random.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="randomlist">
		<h:dgColumn field="ID" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="randomTime" title="抽取时间" dateFormatter="yyyy-MM-dd"  queryMode="scope"></h:dgColumn>
		<h:dgColumn field="depart" title="抽取部门" query="true" dictionary="base_org,orgcode,orgname"></h:dgColumn>
     	<h:dgColumn field="purpose" title="抽取目的"></h:dgColumn>
     	<h:dgColumn field="result" title="抽取结果"></h:dgColumn>
		<h:dgColumn title="操作2" field="opt"></h:dgColumn>
		<h:dgOpenOpt url="random.htm?queryAttender&id={ID}" title="候选机构" width="300" height="400"></h:dgOpenOpt>
		
	</h:datagrid>
</body>

<script type="text/javascript">
function fileload(fileName){
	   window.open("fileupload/downweb.jsp?filename="+fileName);
}
	$(document).ready(function(){
		$("input[name='randomTime_begin']").attr("class","easyui-datebox");
		$("input[name='randomTime_end']").attr("class","easyui-datebox");
	});
	
	
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
			actionUrl += '&id='+ rows[0].id;
		} else {
			actionUrl += '?id='+ rows[0].id;
		}
		createwindow(title, actionUrl, width, height);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>