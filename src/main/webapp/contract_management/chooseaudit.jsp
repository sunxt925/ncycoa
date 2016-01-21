<%@ page contentType="text/html; charset=gb2312" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html>
<html>
<head>
<%
String base_path=request.getContextPath();
request.setAttribute("request_path", base_path);
%>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<title>��ͬ����</title>
<link rel="stylesheet" type="text/css" href="${request_path }/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${request_path }/jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="${request_path }/jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="${request_path }/jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="${request_path }/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${request_path }/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request_path }/jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request_path }/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="${request_path }/jscomponent/tools/datagrid.js"></script>
<script type="text/javascript" src="${request_path }/jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="${request_path }/jscomponent/validform/js/Validform_Datatype.js"></script>

<script type="text/javascript" src="${request_path }/js/MyDatePicker/WdatePicker.js"></script></HEAD>
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
</style>

<%
String id = request.getParameter("id");
%>
</head>
<body style="overflow-x:hidden">
<label><span style="font-size:12px; font-family:΢���ź�,������">ѡ������:</span></label>
<select id="choosetype" name="type" onchange="change(this.value)">
    <option value="0">�̶�����</option>
    <option value="1">�Զ�������</option>
</select>
<br>
<br>

<div id="choose" style="display: none">
<label><span style="font-size:12px; font-family:΢���ź�,������">�������:</span></label>
<input type="hidden" name="participants" id="participants">
<input class="inputxt" style="width:150px;" id="participantsname" name="participantsname">
<a id="btn_selectobject" href="#" class="easyui-linkbutton" data-options="iconCls:\'icon-search\',plain:true">ѡ��</a>
</div>
<input id="btn_ok" type="hidden" onclick="ret()">
<script type="text/javascript">
function ret(){
	 var api = frameElement.api;
	var type=$('#choosetype').val();
	var participants = $('#participants').val();
	var id = "<%=id%>";
	$.post("../contract-management.htm?commit&id="+id+"&type="+type+"&participants="+participants,function(data,status){
		
		(api.data)({code:data.msg});
	});  
	
	
}
function change(val){
	if(val=="1"){
		document.getElementById('choose').style.display="";
		
	}else{
		document.getElementById('choose').style.display="none";
	}
}

$("#btn_selectobject").click(function(){
	
	createwindow('ѡ����Ա','indexmanage/selectstaff.jsp',500,500,returnobjValue );
    });
function returnobjValue(data){

	var array = data.code;
	var staffcodes="";
	var staffnames="";
	for(var i=0;i<array.length;i++){
		staffcodes += array[i].staffcode+",";
		staffnames += array[i].staffname+",";
	}
	
	$('#participants').val(staffcodes);
	$('#participantsname').val(staffnames);
	
}
function createwindow(title, url, width, height,func) {
	
	$.dialog({
			id:'CLHG1976D',
			data:func,
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			zIndex :2000,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return true;
			},
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
}   
</script>
</body>