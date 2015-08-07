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
	<h:datagrid actionUrl="meeting_management.htm?dgdata&flag=all" fit="true" fitColumns="true" queryMode="group" name="meetinglist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="meetingName" title="会议名称" ></h:dgColumn>
		<h:dgColumn field="meetingTopics" title="会议主题"></h:dgColumn>
		<h:dgColumn field="meetingBeginDate" title="开始时段"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="meetingEndDate" title="结束时段" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="meetingRoom" title="开会地点"   dictionary="ncycoa_meetingroom,room_no,room_name" ></h:dgColumn>
		<h:dgColumn field="numAttendee" title="会议人数"  ></h:dgColumn>
		<h:dgColumn field="auditFlag" title="审核状态" replace="通过_11,未审核_0,未通过_10" style="color:red_10,color:blue_11,color:green_0" ></h:dgColumn>
		<h:dgToolBar url="meeting_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="meeting_management.htm?update" icon="icon-add" funname="edit" title="编辑"></h:dgToolBar>
		
		<h:dgToolBar url="meeting_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='meetingBeginDate']").attr("class","easyui-datebox");
		$("input[name='meetingEndDate']").attr("class","easyui-datebox");
	});
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
		if(rows[0].auditFlag != 0){
			$.dialog.alert("会议已经审核通过,不能修改!");
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