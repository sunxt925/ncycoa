<%@page import="com.entity.system.OrgPosition"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
                  request.getServerName()+":"+
		          request.getServerPort()+path+"/";
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

<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
  UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
  String orgcode=request.getParameter("unitccm");
  String uu="memberjson.jsp?unitccm="+orgcode;
 %>
<body class="easyui-layout">

	<div id="top"
		data-options="region:'north',title:'North Title',split:true"
		>
		<table id="dg_staff" class="easyui-datagrid"
			data-options="fitColumns:true,singleSelect:true,url:'<%=uu%>'"
			>
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:100"></th>
                    <th data-options="field:'positionname',width:100">岗位名称</th>
					<th data-options="field:'staffname',width:100">员工姓名</th>
					<th data-options="field:'orgname',width:100">所属部门</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<input type="button" id="btn_ok" style="display: none" onclick="ret()">
  <script type="text/javascript">
    
    var positioncode="";
    $("#btn_ref").click(function(){
    	window.location.reload();
    	    });
  
    function ret(){
     	var api = frameElement.api;
     	var row= $('#dg_staff').datagrid('getSelected');
          	(api.data)({code:(row.staffcode)});
     }
</script>
</body>
</html>
