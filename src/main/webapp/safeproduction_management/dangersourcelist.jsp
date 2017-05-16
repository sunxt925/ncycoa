<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body>
	<h:datagrid actionUrl="dangersource_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="dangersourcelist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="activityType" title="活动类别和场所" query="true"></h:dgColumn>
		<h:dgColumn field="jobActivity" title="作业活动"></h:dgColumn>
     	<h:dgColumn field="mainDangerSource" title="危险源"></h:dgColumn>
		<h:dgColumn field="danger" title="可能导致的事故"></h:dgColumn>
		<h:dgColumn field="dangerLevel" title="风险级别" replace="重大_0,一般_1" query="true" ></h:dgColumn>
		<h:dgColumn field="measureA" title="控制措施A"></h:dgColumn>
		<h:dgColumn field="memo" title="备注"></h:dgColumn>
		<h:dgToolBar url="dangersource_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="dangersource_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgToolBar url="dangersource_management.htm?update" icon="icon-reload" funname="myedit" title="更新"></h:dgToolBar>
		<h:dgToolBar onclick="success()" url="dangersource_management.htm?import" icon="icon-reload" title="从Excel导入"></h:dgToolBar>
	</h:datagrid>
	<div>导入时在此选择文件：<input type="file" name="f" id="f" /></div> 
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='checkTime_begin']").attr("class","easyui-datebox");
		$("input[name='checkTime_end']").attr("class","easyui-datebox");
	});
	
	
	function myedit(title, actionUrl, gname, width, height) {
		gridname=gname;
		var rows;
		try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
		try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
		
		if (!rows || rows.length==0) {
			tip('请先选择一条记录');
			return;
		}
		if (rows.length > 1) {
			tip('不能同时对多条记录编辑，请勾选一条记录');
			return;
		}
	
		if(actionUrl.indexOf("?") >= 0) {
			actionUrl += '&id='+ rows[0].id;
		} else {
			actionUrl += '?id='+ rows[0].id;
		}
		createwindow(title, actionUrl, width, height);
	}
	
	function success(){
		alert("导入成功");
		//alert(document.getElementById("f").value);
		//alert(getPath(document.getElementById("f"));
		self.location.href="dangersource_management.htm?import&path="+document.getElementById("f").value ;
	}
	
	//附带不用修改浏览器安全配置的javascript代码，兼容ie， firefox全系列

	function getPath(obj)  
	{  
	  if(obj)  
	    {  
	 
	    if (window.navigator.userAgent.indexOf("MSIE")>=1)  
	      {  
	        obj.select();  
	 
	      return document.selection.createRange().text;  
	      }  
	 
	    else if(window.navigator.userAgent.indexOf("Firefox")>=1)  
	      {  
	      if(obj.files)  
	        {  
	 
	        return obj.files.item(0).getAsDataURL();  
	        }  
	      return obj.value;  
	      }  
	    return obj.value;  
	    }  
	}  
	//参数obj为input file对象
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>