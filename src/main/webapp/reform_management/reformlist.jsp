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
	<h:datagrid actionUrl="reform_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="reformlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="整改项目名称" ></h:dgColumn>
		<h:dgColumn field="xdzgOrgcode" title="整改下达单位"  dictionary="base_org,orgcode,orgname"  query="true"></h:dgColumn>
		
		<h:dgColumn field="handler" title="整改下达者" dictionary="base_staff,staffcode,staffname"></h:dgColumn>
		<h:dgColumn field="clOrgcode" title="要求整改部门"  dictionary="base_org,orgcode,orgname"></h:dgColumn>
		<h:dgColumn field="xdDate" title="下达日期" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		
		<h:dgColumn field="memo" title="整改说明"></h:dgColumn>
		<h:dgColumn field="flag" title="状态" replace="已反馈_1,未反馈_0" style="color:red_1,color:green_0"></h:dgColumn>
		<h:dgToolBar url="reform_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="reform_management.htm?update" icon="icon-add" funname="edit" title="编辑"></h:dgToolBar>
		<h:dgToolBar url="reform_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		
	
	</h:datagrid>
</body>
<script type="text/javascript">
function edit(title, actionUrl, gname, width, height){
	var rows = null;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('请选择');
		return;
	}
	if (rows.length > 1) {
		tip('不能同时删除多条记录，请勾选一条记录');
		return;
	}
	if(rows[0].flag != 0){
		$.dialog.alert("整改通知已处理结束,不能修改!");
		return;
	}
	if(actionUrl.indexOf("?") == -1) {
		actionUrl += '?id='+ rows[0].id;
	} else {
		actionUrl += '&id='+ rows[0].id;
	}
	createwindow(title, actionUrl, width, height);
}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>