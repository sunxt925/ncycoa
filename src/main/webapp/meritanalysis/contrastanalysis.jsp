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
     <div data-options="region:'west',split:true" style="width:200px;">
        <ul id="tt" class="easyui-tree" data-options="url:'../meritquery/unitjson.jsp?class=d',onClick:change">
        </ul>
       
    </div>
   
	</div>
	<div id="center" data-options="region:'center',split:true" style="padding:5px;background:#eee;width:15px;">
	 <button onclick="addobj()">》</button>
	</div>
	<div id="west" data-options="region:'east',split:true" style="padding:5px;background:#eee;width:200px;">
	 <table id='objtb'>
	 </table>
	</div>
	<input type="button" id="btn_ok" style="display: none" onclick="ret()">
	<script type="text/javascript">
	var tmp=null;
	var res=[];
	 function ret(){
		   var api = frameElement.api;
	    	(api.data)({code:res});
	   }

 function change(){
	 $('#tt').tree({
		onClick: function(node){
            tmp = node;
		} 
	 });
 }
	   
 function addobj(){
	 if(tmp!=null){
		 var tr="<tr>"+
		        "<td>"+tmp.text+"</td>"+
		        "<td><a href=\"javascript:Goto();\" style=\"text-decoration: none\"  onclick=\"{deleteCurrentRow(this,'"+tmp.id+"');}\">x</a></td>"+
		        "</tr>";
		 $("#objtb").append(tr);  
		 res.push(tmp.id);
		 tmp=null;
	 }else{
		 alert('请选择单位');
	 }
 }
 function Goto(){}
 function deleteCurrentRow(obj,id){
	   var tr=obj.parentNode.parentNode;
	   var tbody=tr.parentNode;
	   tbody.removeChild(tr);
	   var restmp=[];
	   for(var i=0;i<res.length;i++){
		   if(res[i]!=id){
			   restmp.push(res[i]);
		   }
	   }
	   res=null;
	   res = restmp;
	   }
	</script>
  </body>
</html>
