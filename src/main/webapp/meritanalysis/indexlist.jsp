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
  String indexcode=request.getParameter("indexcode");
  String uu="indexitemjson.jsp?indexcode="+indexcode;
 %>
 <body>
<table id="dg_index" class="easyui-treegrid"
			data-options="url:'<%=uu %>',
			              idField:'id',
			              treeField:'indexname',
			              animate:true,
			              rownumbers: true,
			              onClickRow: select
			              "
			>
			<thead>
				<tr>
				    <th data-options="field:'indexname'">指标名称</th>
					<th data-options="field:'period'">计分周期</th>
					<th data-options="field:'standardscore'">标准分值</th>
				</tr>
			</thead>
		</table>
	
	<input type="button" id="btn_ok" style="display: none" onclick="ret()">
  <script type="text/javascript">
    function select(row){
    	if(row.children != ""){
    		alert("请选择指标项");
    	}
    } 
    function ret(){
     	var api = frameElement.api;
     	var row= $('#dg_index').treegrid('getSelected');
     		var str=row.id;
    		var strs= new Array(); //定义一数组 
    	   	strs=str.split("n");
    		var indexcode="";
    		for(var i=0;i<strs.length;i++){
    		    indexcode = indexcode + strs[i]+".";
    		}
    		indexcode = indexcode.substring(0,indexcode.length-1);
            (api.data)({indexcode:(indexcode),indexname:(row.indexname),standardscore:(row.standardscore)});
  	    
     	
       
     }
</script>
</body>
</html>
