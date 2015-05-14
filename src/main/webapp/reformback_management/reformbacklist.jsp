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
	<h:datagrid actionUrl="reformback_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="reformbacklist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="backname" title="��������"  query="true"></h:dgColumn>
		<h:dgColumn field="reformId" title="������ĿID"  query="true"></h:dgColumn>
		
		<h:dgColumn field="fileName" title="����" ></h:dgColumn>
		<h:dgColumn field="memo" title="˵��"  query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="subDate" title="�ύ����" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="subUser" title="�ύ��"   dictionary="base_staff,staffcode,staffname"></h:dgColumn>
		<h:dgToolBar url="reformback_management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="reformback_management.htm?update" icon="icon-add" funname="update" title="�༭"></h:dgToolBar>
		
		<h:dgToolBar url="reformback_management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>