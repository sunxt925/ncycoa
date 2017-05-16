<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>任务明细</title>
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<style type="text/css">
*{font-size:14px; font-family:微软雅黑,新宋体}
a.attachment{
	color: #468847;
	display:inline-block;
	margin:3px;
	text-decoration:none;
	border-bottom:1px dashed #468847;"
}
</style>
<script type="text/javascript">
$(function() {
	var description = $("#description_ctn").val();
	CKEDITOR.replace('description');
	CKEDITOR.on('instanceReady',function(event){
		editor=event.editor;
		editor.setReadOnly(true); //只读
		$('#'+editor.id+'_top').hide();
		$('#'+editor.id+'_bottom').hide();
	});
	CKEDITOR.instances.description.setData(description);
	
	$("a.attachment").click(function(){
		$.dialog({
			width:800,
			height:600,
			content: 'url:officeonline/officeopen.jsp?filename='+$(this).data,
			title: "任务明细",
			zIndex: 4000,
			lock : true,
			opacity : 0.4,
			cache:false, 
		    cancelVal: '关闭',
		    cancel: true /*为true等价于function(){}*/
		});
	});
});
</script>
</head>

<body style="overflow-x:hidden">
<form id="formobj" name="formobj" method="post">
<table style="margin:20px;width:95%;border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 附件 </label></td>
		<td class="value">
		<c:forEach var="asset" items="${task.uploadedFiles}">
		<a class="attachment" href="javascript:void" data="${asset.realName }">${asset.friendlyName }</a><br/>
		</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 工作内容描述 </label></td>
		<td class="value">
		<textarea class="inputxt" id="description" name="description" style="overflow-x:hidden;width:400px;height:100px"></textarea>
		<input id="description_ctn" type="hidden" value="${task.description}"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>