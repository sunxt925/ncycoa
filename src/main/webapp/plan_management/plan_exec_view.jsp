<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>�ƻ�����</title>
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<style type="text/css">
*{font-size:12px;font-family:΢���ź�,������}
div.node{
height:70px;
line-height:70px;
}

div.node img{
vertical-align:middle;
}
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

span.participant.executing{
color:#b94a48;
background-color:#f2dede;
border-color:#eed3d7;
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
			title: "������ϸ",
			zIndex: 3000,
			lock : true,
			opacity : 0.4,
			cache:false, 
		    cancelVal: '�ر�',
		    cancel: true /*Ϊtrue�ȼ���function(){}*/
		});
	});
});
</script>
</head>

<body style="overflow-x:hidden;">

<c:forEach var="task" items="${taskList}" varStatus="stuts">
<div class="node">

	<c:if test="${task.key.status != 1 || task.key.status == null}">
	<img alt="" src="plan_management/circle.jpg" />
	</c:if>
	<c:if test="${task.key.status == 1}">
	<img alt="" src="plan_management/circle_red.jpg" />
	</c:if>

	<c:forEach var="participant" items="${task.value}">
	<c:choose>
		<c:when test="${participant.status == 1}">
		<span class="participant executing">
		</c:when>
		
		<c:when test="${participant.status == 2}">
		<span class="participant finished">
		</c:when>
	
		<c:otherwise>
		<span class="participant">
		</c:otherwise>
	</c:choose>
	<span style="display:none;">${participant.id}</span>
	<span class="label">�����ˣ�</span> ${participant.participantName}<br/>
	<span class="label">���ʱ�䣺</span> 
		<c:choose>
		<c:when test="${participant.handleDate != null}">
		<fmt:formatDate value="${participant.handleDate}" type="date" dateStyle="long"/>
		</c:when>
		<c:otherwise>
		--
		</c:otherwise>
		</c:choose>
	</span>
	</c:forEach>

</div>
<c:if test="${stuts.last == false}">
<div><img style="margin-left:8px;" alt="" src="plan_management/arrow.jpg"></div>
</c:if>
</c:forEach>

</body>
</html>