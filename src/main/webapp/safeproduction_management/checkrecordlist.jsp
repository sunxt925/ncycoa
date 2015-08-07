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
	<h:datagrid actionUrl="checkrecord_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="checkrecordlist">
		<h:dgColumn field="id" title="��ˮ��" ></h:dgColumn>
		<h:dgColumn field="place" title="���ص�" query="true"></h:dgColumn>
		<h:dgColumn field="checkTime" title="���ʱ��" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
     	<h:dgColumn field="host" title="������"></h:dgColumn>
		<h:dgColumn field="participants" title="�μ���Ա" query="true"></h:dgColumn>
		<h:dgColumn field="checkContent" title="�������" ></h:dgColumn>
		<h:dgColumn field="checkResult" title="�����"></h:dgColumn>
		<h:dgColumn field="changeRequire" title="����Ҫ��"></h:dgColumn>
		<h:dgColumn field="filePath" title="����ļ�" query="true"></h:dgColumn>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="fileload({filePath},{id})" title="��������"></h:dgFunOpt>
		<h:dgToolBar url="checkrecord_management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="checkrecord_management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
		<h:dgToolBar url="checkrecord_management.htm?update" icon="icon-reload" funname="myedit" title="����"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
function fileload(fileName,id){
	   window.open("fileupload/downwebpoi.jsp?type=checkrecord&filename="+fileName+"&id="+id);
}

	$(document).ready(function(){
		$("input[name='checkTime_begin']").attr("class","easyui-datebox");
		$("input[name='checkTime_end']").attr("class","easyui-datebox");
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