<%@ page language="java" pageEncoding="gb2312"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
</style>
</head>
<body>
	  <table id="dg" class="easyui-datagrid" style="height:700px"
                     data-options="fitColumns:true,singleSelect:true,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'task_id',width:100">����ID</th>
	    <th data-options="field:'task_name',width:100">��������</th>
	    <th data-options="field:'pro_def_id',width:100">Ԥ����ID</th>
	    <th data-options="field:'task_def_key',width:100">����Key</th>
	    <th data-options="field:'start_time',width:100">���̿�ʼʱ��</th>
	    <!-- <th data-options="field:'assignee',width:100">����ִ����</th> -->
	    <th data-options="field:'task_exe',width:100">��������</th>
	    <th data-options="field:'work_flow_diagram',width:100">�鿴����ͼ</th>
	    
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${tasks}" var="task">
           <tr>
              <td>${task.id }</td>
              <td>${task.name }</td>
              <td>${task.processDefinitionId }</td>
              <td>${task.taskDefinitionKey }</td>
              <td>${task.createTime }</td>
              <%-- <td>${task.assignee }</td> --%>
              <td><a href="#" onclick="exe('repair_management.htm?submitTask&taskId=${task.id }')">[��������]</a></td>
              <td><a href="#" onclick="view('repair_management/r.jsp?taskId=${task.id }&processInstanceId=${task.processDefinitionId }')">[�鿴����ͼ]</a></td>
           </tr>
       </c:forEach>
    </tbody>
    
    </table>
</body>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
function exe(url){
	createwindow('ά�޴���',url,700,600);
	
}
function view(url){
	createwindow('ά�޽���',url,1000,600);
}
function returnValue(data){
	alert('����ɹ�');
	window.location.reload();
}
function createwindow(title, url, width, height) {
	
	$.dialog({
			id:'CLHG1976D',
			data:returnValue,
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			zIndex :2000,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return true;
			},
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
}
</script>
</html>