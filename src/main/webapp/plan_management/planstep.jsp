<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>任务步骤</title>
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

<body style="overflow-x:hidden">
<form id="formobj" name="formobj">
<table style="width:100%;border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 任务负责人 </label></td>
		<td class="value">
		<input class="inputxt" style="width:400px;" disabled id="participantList" name="participantList" value="${participantList}"></input>
		<input type="hidden" id="participantList_id" name="participantList_id" value="${participantList_id}"></input>
		<h:choose textname="staffname" hiddenid="staffcode" inputTextname="participantList" hiddenName="participantList_id" url="indexmanage/selectstaff.jsp" icon="icon-search" title="员工列表" isclear="true"></h:choose>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 任务类型 </label></td>
		<td class="value">
		<select class="inputxt" id="type" name="type" style="width:206px;">
			<option value="0">普通</option>
			<option value="1">审核</option>
			<option value="2">文件提交</option>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 任务内容 </label></td>
		<td class="value">
		<textarea class="inputxt" id="content" name="content" style="overflow-x:hidden;width:400px;height:100px"></textarea>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
</table>

<input type="button" id="btn_ok" style="display:none" onclick="ret()">

</form>
 <script type="text/javascript">
 	$(function(){
 		var data = frameElement.api.data.data;
 		if(data){
 			$('#participantList').val(data.participant);
 			$('participantList_id').val(data.participantValue);
 			$('#type').val(data.typeValue);
 			$('#content').val(data.content);
 		}
 	});
 
    function ret(){
     	var api = frameElement.api;
     	var row = {
     			participant:$('#participantList').val(),
     			participantValue:$('#participantList_id').val(),	
     			typeValue:$('#type').val(),
				type:$('#type').find("option:selected").text(),
				content:$('#content').val()
     	};
     	if(api.data.data){
     		row.id = api.data.data.id;
     	}
     	(api.data.callback)(row);
     }
</script>
</body>