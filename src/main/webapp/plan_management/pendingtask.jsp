<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>������ϸ</title>
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
</style>

<body style="overflow-x:hidden">
<div>
${task.content}<br/>
${task.genDate}<br/>

${task.plan.plan.name }<br/>
${task.plan.plan.description }<br/>

${task.plan.content }<br/>
${task.plan.type }<br/>
</div>
</body>
</html>