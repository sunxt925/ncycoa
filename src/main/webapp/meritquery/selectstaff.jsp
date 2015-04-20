<%@page import="com.common.Format"%>
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
    <ul id="tt" class="easyui-tree"   data-options="url:'unit_json.jsp?unitccm=<%=request.getParameter("companycode") %>',onClick:change">
			</ul>
	</div>
	<div id="center" data-options="region:'center'" style="padding:5px;background:#eee;">
	</div>
	<script type="text/javascript">
	    function change(){
	    	 $('#tt').tree({
	  			onClick: function(node){
	  				var url1='memberlist.jsp?unitccm='+node.id;
	  				$('#center').panel({
	  		    		 href:url1
	  		    	 });
	  			}
	  		});
	    }
	</script>
  </body>
</html>
