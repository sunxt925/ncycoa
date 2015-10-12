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
  <body class="easyui-layout">
    <div id="left" data-options="region:'west',split:true" style="width:250px;">
    <ul id="tt" class="easyui-tree"   data-options="url:'unitjson.jsp?unitccm=NC.01',onClick:change">
			</ul>
	</div>
	<div id="center" data-options="region:'center'" style="padding:5px;background:#eee;">
	     <table id="dg" class="easyui-datagrid" data-options="fitColumns:true,singleSelect:false">
	    
	</table>
	</div>
	<input type="button" id="btn_ok" style="display: none" onclick="ret()">
	<script type="text/javascript">
	    function change(){
	    	 $('#tt').tree({
	  			onClick: function(node){
	  				var u="orgjson.jsp?orgccm="+node.id;
	  			    $('#dg').datagrid({
	  			      url:u,
	  			      columns:[[
	  			       {filed:'ck',checkbox:true},
	  			      {field:'orgcode',title:'机构编码',width:100},
	  			      {field:'orgname',title:'机构名称',width:100}
	  			      ]]
	  			      });
	  			}
	  		});
	    }
	    function ret(){
	    	var api = frameElement.api;
	    	var row = $('#dg').datagrid('getSelections');
	    	(api.data)({code:(row)});
	    	
	    }
	</script>
  </body>
</html>
