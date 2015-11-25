<%@ page language="java" pageEncoding="utf-8"%>
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
<link rel="stylesheet" href="jscomponent/uploadify/uploadify.css" type="text/css"></link>
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_Datatype.js"></script>
<script type="text/javascript" src="jscomponent/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="jscomponent/uploadify/jquery.uploadify.min.js"></script>

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
	
	$("div.panel-header").click(function(){
		var taskId = $(this).next().children("span").html();
		alert(taskId);
	});
	
	$("div.panel-header").mouseover(function(){
		$(this).css("cursor", "pointer");
	});
	
	$("div.panel-header").mouseout(function(){
		$(this).css("cursor", "default");
	});
	
	
});
</script>
</head>
<body style="overflow-x:hidden">

<form id="formobj" name="formobj" method="post" action="pending-task.htm?h_upload">
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
    	<input type="file" name="uploadify" id="uploadify" />  
		<div id="fileQueue"></div>  
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
<br/>


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

<script type="text/javascript">
$(function() {
	
	 var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9\+\/\=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\r\n/g,"\n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}};
	 var assetids = "";
	 
	 $("#uploadify").uploadify({ 
		 'uploader' : "pending-task.htm?fupload",
		 'swf' : 'jscomponent/uploadify/uploadify.swf',
		 'queueID' : 'fileQueue',
		 'queueSizeLimit' : 5,
		 'fileTypeExts' : "*.*",
		 "fileTypeDesc" : "文件格式:",
		 'auto' : true,
		 'multi' : true,
		 'removeCompleted': false,
		 'buttonText' : "添加附件",
		 'onUploadStart' : function(file) {
			 $("#uploadify").uploadify('settings', 'formData', {'filename':Base64.encode(file.name)});
		 },
		 'onUploadSuccess' : function(file, data, response) {
			 data = $.parseJSON(data);
			 if(data.success) {
				 assetids += data.attributes.assetid + ":;;:"
			 }
		 }
	 });
	
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
 		var desc = Base64.encode( CKEDITOR.instances.description.getData() );
 		var pdata = {};
 		pdata['id'] = $("#id").val();
 		pdata['taskId'] = $("#taskId").val();
 		pdata['audit'] = $("input[name='audit']").val();
 		pdata['description'] = desc;
 		pdata['assetids'] = assetids.length > 0 ? assetids.substring(0, assetids.length - 4) : assetids;
 		
 		var url = $("#formobj").attr("action");
		$.post(url, pdata, function(data){
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
	});
	
});
</script>
</body>
</html>