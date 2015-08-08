<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>安全生产管理</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_Datatype.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
<script type="text/javascript">
	function resetTrNum(tableId) {
		$tbody = $("#" + tableId + "");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function() {
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if (name != null) {
					if (name.indexOf("#index#") >= 0) {
						$this.attr("name",name.replace('#index#',i));
					} else {
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s + 1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
		});
	}
	
	$(function() {
		$("#formobj").Validform({
			tiptype : 1,
			btnSubmit : "#btn_sub",
			btnReset : "#btn_reset",
			ajaxPost : true,
			callback : function(data) {
				var win = frameElement.api.opener;
				if (data.success == true) {
					frameElement.api.close();
					win.tip("hello");
					
				} else {
					if (data.responseText == ''|| data.responseText == undefined){
						$("#formobj").html(data.msg);
					}else{
						$("#formobj").html(data.responseText);
					}
					return false;
				}
				win.reloadTable();
			}
		});
		
		$('#addBtn').bind('click', function(){   
	 		 var tr =  $("#add_participant_table_template tr").clone();
		 	 $("#add_participant_table").append(tr);
		 	 resetTrNum('add_participant_table');
	    });  
		$('#delBtn').bind('click', function(){   
	       $("#add_participant_table").find("input:checked").parent().parent().remove();   
	        resetTrNum('add_participant_table');
	    });
	});
</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="checkplan_management.htm?save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${checkPlan.id}">
<table style="width:600px;border-spacing:1px;" class="formtable">
    <tr>
		<td align="right"><label class="Validform_label">计划编号</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="planNum" name="planNum"  value="${checkPlan.planNum}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
     <tr>
		<td align="right"><label class="Validform_label">检查类别</label></td>
		<td class="value">
		<select class="inputxt" id="type" name="type" style="width:156px;">
			<option value="0">综合检查</option>
			<option value="1">专项检查</option>
			<option value="2">节假日检查</option>
			<option value="3">季节性检查</option>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">检查时间</label></td>
		<td class="value"><input class="easyui-datebox" style="width:150px;" id="checkTime" name="checkTime" value="${checkPlan.checkTime}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">检查人</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="checker" name="checker" value="${checkPlan.checker}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>

	<tr>
		<td align="right"><label class="Validform_label">受检单位/部门</label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;display:none" id="checkedDepart" name="checkedDepart" value="${checkPlan.checkedDepart}">
		<input class="inputxt" style="width:150px;" id="apporgName" name="apporgName" value="${orgname}">
		<a id="btn_selectorg" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">检查重点内容</label></td>
		<td class="value"><input class="inputxt" style="width:250px;" id="keyContent" name="keyContent" value="${checkPlan.keyContent}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">检查方法</label></td>
		<td class="value"><input class="inputxt" style="width:100px;" id="checkMethod" name="checkMethod" value="${checkPlan.checkMethod}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">备注</label></td>
		<td class="value"><input class="inputxt" style="width:160px;" id="memo" name="memo" value="${checkPlan.memo}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
</table>
<div style="width: 690px; height: 1px;"></div>

</form>
<script type="text/javascript">
$("#btn_selectorg").click(function(){
	
	createwindow('选择部门','indexmanage/selectunit.jsp',500,500,returnorgValue );
    });
function returnorgValue(data){
	var org = data.code;
	var codes="";
	if(org.length>1){
		for(var i=0;i<org.length;i++){
			codes+=org[i].orgcode;
			codes+=",";
		}
		$('#checkedDepart').val(codes);
		$('#apporgName').val(org[0].orgname);
	}else{
		$('#checkedDepart').val(org[0].orgcode);
		$('#apporgName').val(org[0].orgname);
	}
}
function createwindow(title, url, width, height,func) {
	
	$.dialog({
			id:'CLHG1976D',
			data:func,
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			zIndex :2000,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return true;
			},
			cancelVal : '关闭',
			cancel : true/* 为true等价于function(){} */
		});
}

</script>

<table style="display: none">
	<tbody id="add_participant_table_template">
		<tr>
			<td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
			<td align="left"><input name="name" type="text" value=""></td>
			<td align="left"><input name="depart" type="text" value=""></td>
			<td align="left"><input name="task" type="text" value=""></td>
			<td align="left"><input name="memo" maxlength="200" type="text" value=""></td>
		</tr>
	</tbody>
</table>
</body>