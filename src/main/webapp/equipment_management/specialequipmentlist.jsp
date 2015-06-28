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
	<h:datagrid actionUrl="checkplan_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="checkplanlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="�豸����" query="true"></h:dgColumn>
		<h:dgColumn field="model" title="�豸�ͺ�" query="true"></h:dgColumn>
		<h:dgColumn field="manufacturer" title="��������" query="true"></h:dgColumn>
		<h:dgColumn field="serialNum" title="�������" query="true"></h:dgColumn>
		<h:dgColumn field="weight" title="�豸����" query="true"></h:dgColumn>
		<h:dgColumn field="installPosition" title="��װλ��" query="true"></h:dgColumn>
		<h:dgColumn field="fileNum" title="�������" query="true"></h:dgColumn>
		<h:dgColumn field="size" title="���γߴ�" query="true"></h:dgColumn>
		<h:dgColumn field="madeTime" title="��������" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
     	<h:dgColumn field="useTime" title="����ʱ��" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
  		<h:dgToolBar url="checkplan_management.htm?add" icon="icon-add" funname="add" title="���������豸"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?del" icon="icon-remove" funname="del" title="ɾ�������豸"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?update" icon="icon-reload" funname="myedit" title="���������豸"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?addsub" icon="icon-add" funname="add" title="��Ӹ����豸"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?sub" icon="icon-search" funname="add" title="�鿴�����豸"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='madeTime_begin']").attr("class","easyui-datebox");
		$("input[name='madeTime_end']").attr("class","easyui-datebox");
	});
	
	
	function myedit(title, actionUrl, gname, width, height) {
		gridname=gname;
		var rows;
		try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
		try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
		
		if (!rows || rows.length==0) {
			tip('����ѡ��һ����¼');
			return;
		}
		if (rows.length > 1) {
			tip('����ͬʱ�Զ�����¼�༭���빴ѡһ����¼');
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