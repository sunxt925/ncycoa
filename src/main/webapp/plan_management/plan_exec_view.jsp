<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>计划管理</title>
<style type="text/css">
*{font-size:12px;font-family:微软雅黑,新宋体}
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
</style>
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
	
	负责人：${participant.participantName}<br/>
	完成时间：
		<c:choose>
		<c:when test="${participant.handleDate != null}">
		${participant.handleDate}
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