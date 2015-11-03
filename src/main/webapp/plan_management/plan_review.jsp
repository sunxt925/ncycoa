<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>计划评审</title>

<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>

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

<c:forEach var="task" items="${tasks}">
<div class="easyui-panel" title="${task.key.summary}" style="width:auto;" data-options="headerCls:'width_auto',bodyCls:'width_auto'">
<span style="display:none;">${task.key.id}</span>
<div style="padding:10px;">
	<c:forEach var="task" items="${task.value}">
			<span class="participant finished">
			<span style="display:none;">${task.id}</span>
			<span class="label">负责人：</span> ${task.participantName}<br/>
			<span class="label">要求时间：</span> <fmt:formatDate value="${task.step.ending}" type="date" dateStyle="long"/><br/>
			<span class="label">完成时间：</span> <fmt:formatDate value="${task.handleDate}" type="date" dateStyle="long"/><br/>
			<span class="label">是否超时：</span>
			<c:choose>
			<c:when test="${task.handleDate > task.step.ending}">是</c:when>
			<c:otherwise>否</c:otherwise>
			</c:choose>
			</span>
	</c:forEach>
</div>
</div>
<hr style="height:1px;border:none;border-top:1px dashed #dff0d8;" />
</c:forEach>

<script type="text/javascript">
$(function() {
	
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