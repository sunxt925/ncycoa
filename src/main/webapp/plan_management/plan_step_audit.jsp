<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</style>
<script type="text/javascript">
$(function() {
	$("span.participant.finished").click(function(){
		var taskId = $("span", this).html();
		
		$.dialog({
			content: 'url:pending-task.htm?view&id='+taskId,
			title: "任务明细",
			zIndex: 2000,
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

<div class="easyui-panel" title="上一步流程处理结果" style="width:auto;" data-options="headerCls:'width_auto',bodyCls:'width_auto'">
<div style="padding:10px;">

	<c:forEach var="preTask" items="${preTasks}">
			<span class="participant finished">
			<span style="display:none;">${preTask.id}</span>
			负责人：${preTask.participantName}<br/>
			完成时间：${preTask.handleDate}
			</span>
	</c:forEach>

</div>
</div>
<br/>
<div class="easyui-panel" title="处理意见" style="width:auto;" data-options="headerCls:'width_auto',bodyCls:'width_auto'">

<form id="formobj" name="formobj" action="pending-task.htm?h_audit"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${id}">
<input id="taskId" name="taskId" type="hidden" value="${taskId}">
	
	<div style="padding:10px;font-size:14px;text-align:center;line-height:1.4em">
	<span style="display:inline-block;padding:15px;margin-bottom:20px;border:1px solid transparent;color:#c09853;background-color:#fcf8e3;border-color:#fbeed5;border-radius:0">
	上一步流程的参与人将全部得到通知，重做
	</span>
	<br/>
	<input type="radio" name="audit" checked value="0">通过
	<input type="radio" name="audit" value="1">驳回
	</div>
</form>
</div>


<script type="text/javascript">
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
		}
	});
	
});
</script>
</body>
</html>