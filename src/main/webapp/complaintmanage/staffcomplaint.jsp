<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>�Ĵ��ϳ��̲�ר��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=path%>/js/public/check.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

</head>
<%
UserInfo user=(UserInfo)request.getSession().getAttribute("UserInfo");
String dataurl="complaintjson.jsp?staffcode="+user.getStaffcode();
%>
<body>
    
     <div style="width: 100%">
   <a id="btn_add" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">��������</a>
				 <a id="btn_reload" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    <table id="dg" class="easyui-datagrid" style="height:700px"
    data-options="url:'<%=dataurl %>',fitColumns:true,singleSelect:true,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'name',width:100">������</th>
	    <th data-options="field:'complainttitle',width:100">���߱���</th>
	    <th data-options="field:'complaintcategory',width:100">�������</th>
	    <th data-options="field:'complaintreason',width:100">��������</th>
	    <th data-options="field:'complaintdate',width:100">��������</th>
	    <th data-options="field:'attachedfile',width:100">���߲��ϸ���</th>
	    <th data-options="field:'enabledflag',width:100">��Ч��־</th>
	    <th data-options="field:'memo',width:100">��ע</th>
    </tr>
    </thead>
    </table>
   
    </div>
		
</body>
<script type="text/javascript">
$("#btn_reload").click(function(){
	window.location.reload();
	    });
$("#btn_add").click(function(){
	var row = $('#dg').datagrid('getSelected');
	createwindow("��������","complaintmanage/complaintadd.jsp",550,600);
	    });
function createwindow(title, url, width, height) {
	
	$.dialog({
		data:returnValue,
		id:'LHG1976D',
		content : 'url:' + url,
		lock : true,
		width : width,
		height : height,
		title : title,
		opacity : 0.3,
		cache : false
		/*  ok : function() {
			$('#btn_ok', this.iframe.contentWindow.document).click();
			return true;
		} */
		/* cancelVal : '�ر�', */
		//cancel : true/* Ϊtrue�ȼ���function(){} */
	});}
function returnValue(data){
	window.location.reload();
}
</script>
</html>