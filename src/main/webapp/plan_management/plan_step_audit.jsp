<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>计划执行</title>

<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_Datatype.js"></script>
<script type="text/javascript" src="jscomponent/ckeditor/ckeditor.js"></script>

<style type="text/css">
*{font-size:12px;font-family:微软雅黑,新宋体}
.width_auto{width:auto !important;}
span.participant{
display:inline-block;
vertical-align:middle;
line-height:1.4em;
margin-top:-3px;
padding:10px;
border:1px solid transparent;
color:#c09853;
background-color:#fcf8e3;
border-color:#fbeed5;
border-radius:0;
cursor:pointer;
}

span.participant.finished{
color:#468847;
background-color:#dff0d8;
border-color:#d6e9c6;
}
span.label{
	display:inline-block;
	width:60px;
	text-align:right;
}
</style>
<script type="text/javascript">
$(function() {
	$("span.participant.finished").click(function(){
		var taskId = $("span", this).html();
		$.dialog({
			width:600,
			height:450,
			content: 'url:pending-task.htm?view&id='+taskId,
			title: "任务明细",
			zIndex: 3000,
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
<c:forEach var="preTask" items="${preTasks}">
<div class="easyui-panel" title="${preTask.key.summary}" style="width:auto;" data-options="headerCls:'width_auto',bodyCls:'width_auto'">
<div style="padding:10px;">
	<c:forEach var="task" items="${preTask.value}">
			<span class="participant finished">
			<span style="display:none;">${task.id}</span>
			<span class="label">负责人：</span>${task.participantName}<br/>
			<span class="label">完成时间：</span><fmt:formatDate value="${task.handleDate}" type="date" dateStyle="long"/> 
			</span>
	</c:forEach>
</div>
</div>
<hr style="height:1px;border:none;border-top:1px dashed #dff0d8;" />
</c:forEach>
<br/>

<form id="formobj" name="formobj" method="post">
<div class="easyui-panel" title="处理意见" style="width:auto;" data-options="headerCls:'width_auto',bodyCls:'width_auto'">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${id}">
<input id="taskId" name="taskId" type="hidden" value="${taskId}">
	
	<div style="padding:10px;font-size:14px;text-align:center;line-height:1.4em">
	<span id="warnmsg" style="display:inline-block;padding:15px;margin-bottom:10px;border:1px solid transparent;color:#c09853;background-color:#fcf8e3;border-color:#fbeed5;border-radius:0">
	上一步流程的参与人将全部得到通知，重做
	</span>
	<br/>
	<input type="radio" name="audit" checked value="0">通过
	<input type="radio" name="audit" value="1">驳回
	</div>
	
	<div id="taskprocessing" style="padding:5px 30px;font-size:14px;text-align:center;line-height:1.4em;">
	<table style="width:100%;border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 文件 </label></td>
		<td class="value">
		<h:upload name="files" buttonText="添加附件" dialog="false" uploader="pending-task.htm?h_upload" callback="onComplete" extend="*.*;" id="file_upload"></h:upload>
		<div id="filediv"></div>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 工作完成情况描述 </label></td>
		<td class="value">
		<textarea class="inputxt" id="description" name="description" style="overflow-x:hidden;width:400px;height:100px"></textarea>
		<script type="text/javascript">CKEDITOR.replace('description');</script>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	</table>
	</div>
	
</div>

</form>
<script type="text/javascript">
$(function() {
	
	if($("input[name='audit']").val() == 0) {
    	$('#warnmsg').hide();
    	$('#taskprocessing').show();
    } else {
    	$('#warnmsg').show();
    	$('#taskprocessing').hide();
    }
	
	$("input[name='audit']").on('click',function(e){
        if($(this).val() == 0) {
        	$('#warnmsg').fadeOut("fast", function(){$('#taskprocessing').fadeIn();});
        } else {
        	$('#taskprocessing').fadeOut("fast", function(){$('#warnmsg').fadeIn();});
        }
    });
	
	$("#btn_sub").click(function(){
		 $('#file_upload').uploadify('settings','formData', {
			 "id":$("#id").val(), 
			 "taskId":$("#taskId").val(),
			 "audit":$("input[name='audit']").val(),
			 "description":CKEDITOR.instances.description.getData()
		 });
		 upload();
		 return true;
	});
	
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
</script>
</body>
</html>