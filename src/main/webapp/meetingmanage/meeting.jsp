<%@page import="java.util.List"%>
<%@page import="edu.cqu.ncycoa.domain.MeetingInfo"%>
<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>计划管理</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_Datatype.js"></script>

<script type="text/javascript" src="js/MyDatePicker/WdatePicker.js"></script></HEAD>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
<script type="text/javascript">
	function resetTrNum(tableId) {
		$tbody = $("#" + tableId + "");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function() {
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if (name != null) {
					if (name.indexOf("#index#") >= 0) {
						$this.attr("name",name.replace('#index#',i));
					} else {
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s + 1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
		});
	}
	
	$(function() {
		$("#formobj").Validform({
			tiptype : 1,
			btnSubmit : "#btn_sub",
			btnReset : "#btn_reset",
			ajaxPost : true,
			callback : function(data) {
				var win = frameElement.api.opener;
				if (data.success == true) {
					frameElement.api.close();
					win.tip(data.msg);
					
				} else {
					if (data.responseText == ''|| data.responseText == undefined){
						$("#formobj").html(data.msg);
					}else{
						$("#formobj").html(data.responseText);
					}
					return false;
				}
				win.reloadTable();
			}
		});
		
		$('#addBtn').bind('click', function(){   
	 		 var tr =  $("#add_participant_table_template tr").clone();
		 	 $("#add_participant_table").append(tr);
		 	 resetTrNum('add_participant_table');
	    });  
		$('#delBtn').bind('click', function(){   
	       $("#add_participant_table").find("input:checked").parent().parent().remove();   
	        resetTrNum('add_participant_table');
	    });
	});
</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="meeting_management.htm?save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${meetingInfo.id}">
<table style="border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 会议名称 </label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="meetingName" name="meetingName" value="${meetingInfo.meetingName}" datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 会议主题 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="meetingTopics" name="meetingTopics" value="${meetingInfo.meetingTopics}" datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 开始时段 </label></td>
		<td class="value">
		 <input name="meetingBeginDate" type="Wdate" class="input1" id="meetingBeginDate" onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${meetingInfo.meetingBeginDate}" size="30"  maxlength="30">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 结束时段 </label></td>
		<td class="value">
		<input name="meetingEndDate" type="Wdate" class="input1" id="meetingEndDate" onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${meetingInfo.meetingEndDate}" size="30"  maxlength="30">
		
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 开会地点 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="meetingRoom" name="meetingRoom" value="${meetingInfo.meetingRoom}" >
		<a id="btn_selectroom" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 会议人数 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="numAttendee" name="numAttendee" value="${meetingInfo.numAttendee}" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 参与人员 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="participants" name="participants" value="${meetingInfo.participants}">
		<a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 需求部门 </label></td>
		<td class="value">
		<input type="hidden" style="width:150px;" id="applyOrgCode" name="applyOrgCode" value="${meetingInfo.applyOrgCode}">
		<input class="inputxt" style="width:150px;" id="applyOrgName" name="applyOrgName" value="${applyOrgName}">
		<a id="btn_selectorg" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 其他需求 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="meetingReport" name="meetingReport" value="${meetingInfo.meetingReport}" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<input  type="hidden" id="meetingFlag" name="meetingFlag" value="${meetingInfo.meetingFlag}">
	<input  type="hidden" id="auditFlag" name="auditFlag" value="${meetingInfo.auditFlag}">
</table>
</form>
<script type="text/javascript">
$("#btn_selectorg").click(function(){
	
	createwindow('选择部门','indexmanage/selectunit.jsp',500,500,returnorgValue );
    });
function returnorgValue(data){
	var org = data.code;
	if(org.length>1){
		alert("最多只能选择一个部门");
	}else{
		$('#applyOrgCode').val(org[0].orgcode);
		$('#applyOrgName').val(org[0].orgname);
	}
	
	
}
$("#btn_selectroom").click(function(){
	var begindate = $('#meetingBeginDate').val();
	var enddate = $('#meetingEndDate').val();
	
	if(begindate == ""){
		alert("请先选择会议开始时间");
	}
	if(enddate == "" ){
		alert("请先选择会议结束时间");
	}
	if(begindate != "" && enddate != ""){
		createwindow('选择会议室','meetingmanage/selectroom.jsp',500,500,returnroomValue );
	}
	
    });

function returnroomValue(data){

	var room = data.code;
	var begindate = $('#meetingBeginDate').val();
	var enddate = $('#meetingEndDate').val();
	var url = "meetingmanage/validroom.jsp?roomno="+room.roomid+"&startdate="+begindate+"&enddate="+enddate;
	$.get(url,function(data,status){
		if(data=="1"){
			$('#meetingRoom').val(room.roomid);
		}else{
			$('#meetingRoom').val('');
			alert("选择的会议室有冲突");
		}
		
	});
	
	
}
$("#btn_selectobject").click(function(){
	
	createwindow('选择人员','indexmanage/selectstaff.jsp',500,500,returnobjValue );
    });
    
 
function returnobjValue(data){

	var array = data.code;
	var staffs="";
	for(var i=0;i<array.length;i++){
		staffs += array[i].staffcode+",";
	}
	
	$('#participants').val(staffs);
	$('#numAttendee').val(array.length);
	
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
			cancelVal : '关闭',
			cancel : true/* 为true等价于function(){} */
		});
}
</script>
</body>