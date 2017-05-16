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
	<h:datagrid actionUrl="meetingroom_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="meetingroomlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="roomNo" title="会议室编号"  query="true"></h:dgColumn>
		<h:dgColumn field="roomName" title="会议室名称"  query="true"></h:dgColumn>
		
		<h:dgColumn field="belongOrg" title="所属单位"  replace="市局公司部门_NC.00,顺庆区局_NC.01.20,高坪区局_NC.01.21,嘉陵区局_NC.01.22,西充县局_NC.01.24,南部县局_NC.01.25,阎中市局_NC.01.26,仪陇县局_NC.01.27,营山县局_NC.01.28,蓬安县局_NC.01.29,物流中心_NC.01.30"></h:dgColumn>
		<h:dgColumn field="galleryful" title="容纳人数"  query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="roomMemo" title="会议室说明"></h:dgColumn>
		<h:dgToolBar url="meetingroom_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="meetingroom_management.htm?update" icon="icon-add" funname="update" title="编辑"></h:dgToolBar>
		
		<h:dgToolBar url="meetingroom_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>