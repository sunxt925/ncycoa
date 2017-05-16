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
	<h:datagrid actionUrl="meeting_management.htm?dgdata&flag=uaudited" fit="true" fitColumns="true" queryMode="group" name="meetinglist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="meetingName" title="会议名称"  ></h:dgColumn>
		<h:dgColumn field="meetingTopics" title="会议主题" ></h:dgColumn>
		<h:dgColumn field="meetingBeginDate" title="开始时段"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="meetingEndDate" title="结束时段" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="meetingRoom" title="开会地点"   dictionary="ncycoa_meetingroom,room_no,room_name" ></h:dgColumn>
		<h:dgColumn field="numAttendee" title="会议人数" ></h:dgColumn>
		<h:dgColumn field="auditFlag" title="审核状态"  replace="通过_11,未审核_0" style="color:red_0,color:blue_11" ></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgConfOpt title="通过" url="meeting_management.htm?audit&id={id}&res=yes" message="确认审核？" />
		<h:dgConfOpt title="不通过" url="meeting_management.htm?audit&id={id}&res=no" message="确认审核？" />
		<h:dgToolBar url="meeting_management.htm?audit" icon="icon-search" funname="update" title="审核"></h:dgToolBar>
	
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