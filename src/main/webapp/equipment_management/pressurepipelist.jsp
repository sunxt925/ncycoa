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
	<h:datagrid actionUrl="pressurepipe_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="pressurepipe">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="diameter" title="����ֱ��" query="true"></h:dgColumn>
     	<h:dgColumn field="pipeMaterial" title="�ܵ�����" query="true"></h:dgColumn>
		<h:dgColumn field="thickness" title="�ܵ��ں�" ></h:dgColumn>
		<h:dgColumn field="length" title="�ܵ�����" ></h:dgColumn>
		<h:dgColumn field="workPressure" title="�ܵ�����ѹ��"></h:dgColumn>
		<h:dgColumn field="strengthPressure" title="�ܵ�ǿ������ѹ��" ></h:dgColumn>
		<h:dgColumn field="rigorousPressure" title="�ܵ�����������ѹ��" ></h:dgColumn>
		<h:dgColumn field="workTemperature" title="�ܵ������¶�" ></h:dgColumn>
		<h:dgColumn field="useDate" title="�ܵ�Ͷ������" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="layingMethod" title="�ܵ����跽ʽ" ></h:dgColumn>
		<h:dgColumn field="anticorrosionMethod" title="�ܵ�������ʽ" ></h:dgColumn>
		<h:dgColumn field="adiabaticMethod" title="�ܵ����ȷ�ʽ " ></h:dgColumn>
		<h:dgColumn field="designCode" title="�ܵ���ƹ淶 " ></h:dgColumn>
		<h:dgColumn field="manageDepart" title="��ز���" ></h:dgColumn>
		<h:dgColumn field="manager" title="��ظ�����" ></h:dgColumn>
		<h:dgToolBar url="pressurepipe_management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="pressurepipe_management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
		<h:dgToolBar url="pressurepipe_management.htm?update" icon="icon-reload" funname="myedit" title="����"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='useDate_begin']").attr("class","easyui-datebox");
		$("input[name='useDate_end']").attr("class","easyui-datebox");
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