<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
</head>
 <%
 String objectcode=request.getParameter("objectcode");
 String ul="refindexjson.jsp?objectcode="+objectcode;
 %>
<body>

    <table id="dg" class="easyui-treegrid"    data-options="url:'<%=ul %>' ,method: 'get',rownumbers: true,idField: 'indexcode',treeField: 'indexname',fitColumns:true" >
		<thead>
			<tr>
				<th data-options="field:'indexname',width:100">指标体系名称</th>
			</tr>
		</thead>
	</table> 

	<script type="text/javascript">
	function  filter(){
		var row = $('#dg').datagrid('getSelected');
		if(row.indexcode=='公司'||row.indexcode=='部门'||row.indexcode=='个人'){
			alert("选择数据无效，请重新选择");
		}
		
	}
    function ret(){
    	var api = frameElement.api;
    	var row = $('#dg').datagrid('getSelected');
    	(api.data)({code:(replaceAll(row.indexcode)), name:(row.indexname)});
    	
    }
    function replaceAll(str)  
    {  
        if(str!=null)  
        	var v=new Array();
            v=str.split("n");
            var retvalue="";
        	for(var i=0;i<v.length;i++){
        		retvalue=retvalue+v[i]+".";
        	}
        	retvalue=retvalue.substring(0, retvalue.length-1);
        return retvalue;  
    }
	</script>
	<input type="button" id="btn_ok" style="display: none" onclick="ret()">
</body>
</html>
