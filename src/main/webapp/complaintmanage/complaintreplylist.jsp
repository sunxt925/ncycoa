<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.UserInfo"%>
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
<script language="javascript" src="<%=path%>/js/public/check.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
String dataurl="complaintreplyjson.jsp?staffcode="+u.getStaffcode();
%>
<body>
    
     <div style="width: 100%">
   <a id="btn_reload" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">刷新</a>
    <table id="dg" class="easyui-datagrid" style="height:700px"
    data-options="url:'<%=dataurl %>',fitColumns:true,singleSelect:true,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'replypersonname',width:100">回复者</th>
	    <th data-options="field:'replycontent',width:100">回应内容</th>
	    <th data-options="field:'replyfile',width:100">回复材料附件</th>
	    <th data-options="field:'replydate',width:100">回复日期</th>
	    <th data-options="field:'memo',width:100">备注</th>
    </tr>
    </thead>
    </table>
   
    </div>
		
</body>
<script type="text/javascript">
$("#btn_reload").click(function(){
	window.location.reload();
	    });
</script>
</html>