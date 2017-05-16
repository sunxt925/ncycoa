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
String indexclass=(String)request.getAttribute("classT");
String ul="targetmanage/indexjson.jsp?indexclass="+indexclass;
System.out.println(ul);
%>
<body  class="easyui-layout">
   
   <div data-options="region:'west',title:'指标体系',split:false" style="width:230px;">
     <table id="dg" class="easyui-datagrid" style="width:250px;"
                             data-options="url:'<%=ul %>',fitColumns:true,singleSelect:true">
	    <thead>
			<tr>
				<th data-options="field:'indexname',width:80">指标体系名称</th>
			</tr>
		</thead>
	</table>
   </div>
   <div id="center" data-options="region:'center',title:'关联对象'" style="padding:5px;">
      <iframe  style="border: 0px; width: 100%;height: 95%" src="" name="indexdeflist">
      </iframe>
   </div>
   <script type="text/javascript">
     $('#dg').datagrid({
    	 onClickRow: function(){
    		 var row = $('#dg').datagrid('getSelected');
    		 var u="objindexarchuser.htm?itemlist&indexarchcode="+row.indexcode+"&indexclass="+"<%=indexclass%>";
    		 window.open(u,"indexdeflist");
		 }
		
	 });
     
   </script>
</body>
</html>
