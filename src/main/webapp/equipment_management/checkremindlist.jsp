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
	<h:datagrid actionUrl="specialequipment_management.htm?dgdata_remind" fit="true" fitColumns="true" queryMode="group" name="checkplanlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="�豸����" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="�豸����" replace="���� _0,ѹ������ _1,���ڻ����� _2,������_3,�����豸_4,�����豸_5,Ѳ���豸_6,�����豸_7" query="true"></h:dgColumn>
		<h:dgColumn field="maintenDepart" title="ά����λ"></h:dgColumn>
		<h:dgColumn field="checkCycle" title="�������ڣ��£�"></h:dgColumn>
		<h:dgColumn field="checkDate" title="��ǰ�������" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="checkStatus" title="��ǰ������"></h:dgColumn>
     	<h:dgColumn field="nextCheckDate" title="�´μ������" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		<h:dgToolBar url="specialequipment_management.htm?update_check" icon="icon-add" funname="myedit" title="��Ӽ����־"></h:dgToolBar>
		<h:dgToolBar url="specialequipment_management.htm?sub" icon="icon-search" funname="myedit" title="�鿴����¼"></h:dgToolBar>
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