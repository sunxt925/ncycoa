<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

</head>
<body>  
     <div style="width: 100%">
      <a id="btn_sendMsg"
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true">短信通知</a>
    <table id="dg" class="easyui-datagrid" style="height:"
    data-options="fitColumns:true,singleSelect:false,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'staffcode',width:100">员工编码</th>
	    <th data-options="field:'staffname',width:100">姓名</th>
    </tr>
    </thead>
    <tbody>
       <c:forEach items="${maps}" var="item">
           <tr>
              <td>${item.key }</td><td>${item.value }</td>
           </tr>
       </c:forEach>
    </tbody>
    </table>
    </div>
    
</body>
<script type="text/javascript">
$("#btn_sendMsg").click(function(){
	var staffs = "${maps}";
	var ids = "${id}";
	staffs = staffs.substring(1,staffs.length-1);
	var objs = staffs.split(",");
	staffs = "";
	for(var i=0;i<objs.length;i++){
		staffs += objs[i].split("=")[0]+",";
	}
	$.post('meeting_management.htm?sendMsg',
		  {obj:staffs,id:ids},
		  function(data){
		  alert(data);
	});
});
</script>
</html>