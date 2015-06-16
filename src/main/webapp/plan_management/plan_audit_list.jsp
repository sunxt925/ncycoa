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
	<h:datagrid actionUrl="plan-management.htm?dgdata_audit" checkbox="true" fit="true" fitColumns="true" queryMode="group" name="plan_audit_list">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="�ƻ�����" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="�ƻ�����" replace="��λ�ƻ�_0,���żƻ�_1" query="true"></h:dgColumn>
		<h:dgColumn field="inputUser" title="¼����Ա" dictionary="base_staff,staffcode,staffname" query="true"></h:dgColumn>
		<h:dgColumn field="inputDate" title="¼��ʱ��" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="myedit_1({id})" title="�鿴"></h:dgFunOpt>
		<h:dgConfOpt title="ͨ��" url="plan-management.htm?audit&id={id}&pass=true" message="ȷ��ͨ���üƻ���ͨ���󣬼ƻ������Ա�ִ��" />
		<h:dgConfOpt title="��ͨ��" url="plan-management.htm?audit&id={id}&pass=false" message="��ͨ���üƻ����ƻ�������ԭ�����������޶��༭" />
		<h:dgToolBar url="plan-management.htm?audit" icon="icon-default" funname="batchaudit" title="�������"></h:dgToolBar>
	</h:datagrid>
</body>

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

function batchaudit(title, actionUrl, gname, width, height) {
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
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>