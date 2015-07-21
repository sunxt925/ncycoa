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
*{font-size:12px; font-family:΢���ź�,������}
</style>
</head>
<body>
	<h:datagrid actionUrl="meetingroom_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="meetingroomlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="roomNo" title="�����ұ��"  query="true"></h:dgColumn>
		<h:dgColumn field="roomName" title="����������"  query="true"></h:dgColumn>
		
		<h:dgColumn field="belongOrg" title="������λ"  replace="�оֹ�˾����_NC.00,˳������_NC.01.20,��ƺ����_NC.01.21,��������_NC.01.22,�����ؾ�_NC.01.24,�ϲ��ؾ�_NC.01.25,�����о�_NC.01.26,��¤�ؾ�_NC.01.27,Ӫɽ�ؾ�_NC.01.28,��ؾ�_NC.01.29,��������_NC.01.30"></h:dgColumn>
		<h:dgColumn field="galleryful" title="��������"  query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="roomMemo" title="������˵��"></h:dgColumn>
		<h:dgToolBar url="meetingroom_management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="meetingroom_management.htm?update" icon="icon-add" funname="update" title="�༭"></h:dgToolBar>
		
		<h:dgToolBar url="meetingroom_management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>