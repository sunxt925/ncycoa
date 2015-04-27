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
div.node span{
display:inline-block;line-height:1.4em;vertical-align:middle;
margin-right:10px;
}
</style>

</head>
<body style="overflow-x:hidden;text-align:center">
<div style="margin:0 auto;text-align:left">

<c:forEach var="task" items="${taskList}" varStatus="stuts">

<div class="node" style="height:50px;line-height:50px;font-size:0;">
<c:if test="${task.status != 1 || task.status == null}">
<span>
<img alt="" src="plan_management/circle.jpg" />
</span>
</c:if>

<c:if test="${task.status == 1}">
<span>
<img alt="" src="plan_management/circle_red.jpg" />
</span>
</c:if>

<span>
${task.participant}<br/>
${task.content}
</span>
</div>

<c:if test="${stuts.last == false}">
<div><img style="margin-left:3px;" alt="" src="plan_management/arrow.jpg"></div>
</c:if>
</c:forEach>

</div>

</body>
</html>