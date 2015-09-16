<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<script type="text/javascript" src="js/MyDatePicker/WdatePicker.js"></script>
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
a.dgopt{
	color:#000;
	cursor:pointer;
	text-decoration:none;
	border-bottom:1px dotted #000;
}
</style>
</head>
<body>
	<h:datagrid actionUrl="plan-management.htm?review_data" fit="true" fitColumns="true" checkbox="true" queryMode="group" name="planlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="�ƻ�����" query="true"></h:dgColumn>
		<h:dgColumn field="description" title="����"></h:dgColumn>
		<h:dgColumn field="inputUser" title="¼��Ա" dictionary="base_staff,staffcode,staffname" query="true"></h:dgColumn>
		<h:dgColumn field="inputDate" title="¼��ʱ��" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgToolBar url="plan-management.htm?review" icon="icon-default" funname="review" title="����"></h:dgToolBar>
		<h:dgColumn field="opt" title="����"></h:dgColumn>
		<h:dgFunOpt funname="myedit_1({id})" title="����"></h:dgFunOpt>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
function myedit_1(id){
	var url = "plan-management.htm?update";
	if(url.indexOf("?") >= 0) {
		url += '&id='+ id;
	} else {
		url += '?id='+ id;
	}
	openwindow("�鿴�ƻ�", url,"plan_audit_list", 800, 600);
}

function review(title, actionUrl, gname, width, height) {
	gridname=gname;
	var rows;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('����ѡ��һ����¼');
		return;
	}
	
	var ids = '';
	for(var i=0; i<rows.length; i++) {
		ids += rows[i].id + ',';
	}
	if(actionUrl.indexOf("?") == -1) {
		actionUrl += '?id='+ ids;
	} else {
		actionUrl += '&id='+ ids;
	}
	
	width =  width ? width : 200;
	height = height ? height : 100;
	$.dialog({
		content: "������˼ƻ�",
		lock : true,
		width:width,
		height:height,
		title:title,
		opacity : 0.3,
		cache:false,
	   button:[{
		   name:"ͨ��",
		   callback:function(){
			   $.get(actionUrl+"&pass=true",{},function(data){
					var win = frameElement.api.opener;
					data = $.parseJSON(data);
					if (data.success == true) {
						frameElement.api.close();
						win.tip(data.msg);
					} else {
						if (data.responseText == ''|| data.responseText == undefined){
							$("#formobj").html(data.msg);
						}else{
							$("#formobj").html(data.responseText);
						}
						return false;
					}
					win.reloadTable();
				});
		   }
	   },{
		   name:"��ͨ��",
		   callback:function(){
			   $.get(actionUrl+"&pass=false",{},function(data){
					var win = frameElement.api.opener;
					data = $.parseJSON(data);
					if (data.success == true) {
						frameElement.api.close();
						win.tip(data.msg);
					} else {
						if (data.responseText == ''|| data.responseText == undefined){
							$("#formobj").html(data.msg);
						}else{
							$("#formobj").html(data.responseText);
						}
						return false;
					}
					win.reloadTable();
				});
		   }
	   },{
		   name:"ȡ��",
		   callback:function(){}
	   }]
	});
}

$(document).ready(function(){
	$("input[name='inputDate_begin']").click(function(){WdatePicker();});
	$("input[name='inputDate_end']").click(function(){WdatePicker();});
});
</script>
</html>