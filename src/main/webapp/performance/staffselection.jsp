<%@page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*" errorPage=""%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
</head>
<body class="easyui-layout" style="width:100%;height:100%;margin:0;padding:0;overflow:hidden">
<div data-options="region:'west',split:true,border:false" style="width:400px;">
<ul id="orgtree" class="easyui-tree" data-options="url:'departjson.jsp?orgcode=NC.01',onClick:onLeafClick,animate:true"></ul>
</div>
<div data-options="region:'center',border:false" style="background:#eee;">
<table id="staffs" class="easyui-datagrid" data-options="url:'staffjson.jsp',fit:true,singleSelect:true,idField:'code'">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'staffcode'">员工编码</th>
            <th data-options="field:'staffname',width:100">员工姓名</th>
            <th data-options="field:'gender',width:100">性别</th>
        </tr>
    </thead>
</table>
</div>
<input type="button" id="btn_sub" style="display:none"></input>
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
	function onLeafClick(node){
		//if($('#orgtree').tree('isLeaf', node.target)){
			$('#staffs').datagrid('load',{
				orgcode:node.id
			});
		//}	
	}
	
	function getSelectRows()
	{
		return $('#staffs').datagrid('getSelected');
	}

	function exit()
	{
		 window.returnValue = null;
		 window.close();
	}
</script>
</body>
</html>
