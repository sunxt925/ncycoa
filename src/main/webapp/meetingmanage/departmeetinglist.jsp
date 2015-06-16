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
	<h:datagrid actionUrl="meeting_management.htm?dgdata&flag=depart" fit="true" fitColumns="true" queryMode="group" name="meetinglist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="meetingName" title="会议名称"  query="true"></h:dgColumn>
		<h:dgColumn field="meetingTopics" title="会议主题"  query="true"></h:dgColumn>
		<h:dgColumn field="meetingBeginDate" title="开始时段"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="meetingEndDate" title="结束时段" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="meetingRoom" title="开会地点" replace="会议室1_0,会议室2_1,会议室3_2" query="true"></h:dgColumn>
		<h:dgColumn field="numAttendee" title="会议人数"  query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="meetingFlag" title="会议状态" replace="完成_11,取消_10,未处理_0" style="color:green_10,color:red_0,color:blue_11" query="true"></h:dgColumn>
		<h:dgColumn field="auditFlag" title="审核状态" replace="通过_11" style="color:color:green_11" query="true"></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgToolBar url="meeting_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgConfOpt title="完成" url="meeting_management.htm?execute&id={id}&res=yes" message="确认完成？" />
		<h:dgConfOpt title="取消" url="meeting_management.htm?execute&id={id}&res=no" message="确认取消？" />
		
		</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='meetingBeginDate']").attr("class","easyui-datebox");
		$("input[name='meetingEndDate']").attr("class","easyui-datebox");
	});
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>