<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<link rel="stylesheet" type="text/css" href="<%=path %>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path %>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path %>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path %>/jscomponent/tools/datagrid.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body>
	<h:datagrid actionUrl="/ncycoa/staff.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="stafflist">
		<h:dgColumn field="idcard" title="身份证号" query="true" ></h:dgColumn>
		<h:dgColumn field="staffcode" title="员工编码" query="true" ></h:dgColumn>
		<h:dgColumn field="staffname" title="员工姓名" query="true" ></h:dgColumn>
     	<h:dgColumn field="gender" title="性别" ></h:dgColumn>
		<h:dgColumn field="salarylevel" title="薪资等级" ></h:dgColumn>
		<h:dgColumn field="nationality" title="民族" ></h:dgColumn>
		<h:dgColumn field="email" title="email"></h:dgColumn>
		<h:dgColumn field="qq" title="QQ" ></h:dgColumn>
		<h:dgColumn field="mobilephone" title="电话" ></h:dgColumn>
		<h:dgColumn field="birthday" title="生日" dateFormatter="yyyy-MM-dd"></h:dgColumn>
		<h:dgColumn field="begincareerdate" title="入职时间" dateFormatter="yyyy-MM-dd"></h:dgColumn>
	</h:datagrid>
	<input id="btn_ok" type="hidden" onclick="ret()">
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='birthday_begin']").attr("class","easyui-datebox");
		$("input[name='birthday_end']").attr("class","easyui-datebox");
		$("input[name='begincareerdate_begin']").attr("class","easyui-datebox");
		$("input[name='begincareerdate_end']").attr("class","easyui-datebox");
	});
	function ret(){
		 var api = frameElement.api;
		 var rows=null;
		 try{rows=$('#stafflist').datagrid('getSelections');}catch(ex){}
		 (api.data)({code:rows[0]});
		
	}
	
</script>
<script type="text/javascript" src="<%=path %>/jscomponent/easyui/jquery.easyui.min.js"></script>
</html>