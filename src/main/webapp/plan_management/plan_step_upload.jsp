<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>计划执行</title>

<link rel="stylesheet" href="jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<style type="text/css">
*{font-size:12px;font-family:微软雅黑,新宋体}
</style>

</head>
<body style="overflow-x:hidden">

<form id="formobj" name="formobj" method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${id}">
<input id="taskId" name="taskId" type="hidden" value="${taskId}">
<table style="width:100%;border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 文件 </label></td>
		<td class="value">
		<h:upload name="files" buttonText="选择要上传的文件" dialog=false uploader="pending-task.htm?h_upload" callback="onComplete" extend="*.*;" id="file_upload"></h:upload>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"></label></td>
		<td class="value">
		<div class="form" id="filediv"></div>
		</td>
	</tr>
</table>

</form>

<script type="text/javascript">
$(function() {
	
	$("#btn_sub").click(function(){
		 $('#file_upload').uploadify('settings','formData',{"id":$("#id").val(), "taskId":$("#taskId").val()});
		 upload();
		 return true;
	});
	
	function onComplete(queueData){
		$.dialog.tips('成功上传' + queueData.uploadsSuccessful + '个文件', 600, null, function(){
			var windowapi = frameElement.api;
			var win = windowapi.opener; 	  
	        win.reloadTable();
	        win.tip(serverMsg);
			windowapi.close();
		});
	}
	
});
</script>
</body>
</html>