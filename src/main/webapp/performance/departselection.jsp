<%@page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*" errorPage=""%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
</head>
<body class="easyui-layout"  style="width:100%;height:100%;margin:0;padding:0;overflow:hidden">
<div data-options="region:'center',border:false" style="background:#eee;">
<ul id="orgtree" class="easyui-tree" data-options="fit:true,url:'departjson.jsp?orgcode=NC.01',animate:true,checkbox:true,onCheck:oncheck,onSelect:onSelect"></ul>
</div>
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
	function onSelect(node){
		if(node.checked){
			$('#orgtree').tree("uncheck", node.target);
		}
		else{
			$('#orgtree').tree("check", node.target);
		}
	}

	var nodeLastChecked = undefined;
	function oncheck(node, checked){
		if(checked == true){
			if(nodeLastChecked === undefined || nodeLastChecked.id == node.id){
				nodeLastChecked = node;
				return;
			}
			
			$('#orgtree').tree("uncheck", nodeLastChecked.target);
			nodeLastChecked = node;
		}
	}
	
	function getSelectRows(){
	    return $("#orgtree").tree("getChecked");
	}

	function exit()
	{
		 window.returnValue = null;
		 window.close();
	}
</script>
</body>
</html>
