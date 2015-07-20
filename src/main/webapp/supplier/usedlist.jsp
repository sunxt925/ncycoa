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
	<h:datagrid actionUrl="supplier.htm?useddgdata" fit="true" fitColumns="true" queryMode="group" name="usedlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="supplier" title="��Ӧ������" query="true"></h:dgColumn>
		<h:dgColumn field="usedGoods" title="�������" query="true"></h:dgColumn>
     	<h:dgColumn field="usedDepart" title="ʹ�ò���" query="true"></h:dgColumn>
     	<h:dgColumn field="usedMoney" title="�ɹ����"></h:dgColumn>
     	<h:dgColumn field="usedCount" title="�ɹ�����"></h:dgColumn>
     	<h:dgColumn field="usedTime" title="ʹ��ʱ��" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		
		<h:dgToolBar url="supplier.htm?used_addi" icon="icon-add" funname="add" title="������¼"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?used_update" icon="icon-reload" funname="myedit" title="���¼�¼"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='usedTime_begin']").attr("class","easyui-datebox");
		$("input[name='usedTime_end']").attr("class","easyui-datebox");
	});
	
	function add(title, actionUrl, gname, width, height) {
		gridname=gname;
		createwindow(title, actionUrl, 600, 250);
	}
	
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
		createwindow(title, actionUrl, 600, 250);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>