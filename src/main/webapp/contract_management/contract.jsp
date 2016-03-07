<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>��ͬ����</title>
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


<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
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
<form id="formobj" name="formobj" action="contract-management.htm?save" method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${contract.id}">

<table style="width:100%;border-spacing:1px;" class="formtable">
	<%-- <tr>
		<td align="right"><label class="Validform_label"> ��ͬ���� </label></td>
		<td class="value"><input class="inputxt" id="code" name="code" value="${contract.code}" datatype="s2-20">
		<span class="Validform_checktip"></span>
		</td>
	</tr> --%>

	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ���� </label></td>
		<td class="value"><input class="inputxt" id="name" name="name" value="${contract.name}" datatype="s2-50">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> �а첿�� </label></td>
		<td class="value">
		
		<input class="inputxt" disabled id="relevantDepartment_disp" name="relevantDepartment_disp" value="${relevantDepartment_disp}"></input>
		<input type="hidden" id="relevantDepartment" name="relevantDepartment" value="${contract.relevantDepartment}"></input>
		 <h:choose textname="orgname" hiddenid="orgcode" inputTextname="relevantDepartment_disp" hiddenName="relevantDepartment" url="indexmanage/selectunit.jsp" icon="icon-search" title="�����б�" isclear="true"></h:choose>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ���� </label></td>
		<td class="value">
		<select id="type" name="type" >
		<c:if test="${type == null}">
		  <option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
		<c:if test="${type == 1}">
		<option value="1" selected="selected">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
	
		</c:if>
		<c:if test="${type == 2}">
		  <option value="1">������ͬ</option>
		  <option value="2" selected="selected">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
		<c:if test="${type == 3}">
		 	<option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3"  selected="selected">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
		<c:if test="${type == 4}">
		  <option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4" selected="selected">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
		<c:if test="${type == 5}">
		  <option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5" selected="selected">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
			<c:if test="${type == 6}">
		  <option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6" selected="selected">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
			<c:if test="${type == 7}">
		  <option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7" selected="selected">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
			<c:if test="${type == 8}">
		  <option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8" selected="selected">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
			<c:if test="${type == 9}">
		  <option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9" selected="selected">�����ͬ</option>
		  <option value="0">������ͬ</option>
		</c:if>
			<c:if test="${type == 0}">
		  <option value="1">������ͬ</option>
		  <option value="2">���޺�ͬ</option>
		  <option value="3">�ִ���ͬ</option>
		  <option value="4">������ͬ</option>
		  <option value="5">����ʩ��(ά��)��ͬ</option>
		  <option value="6">������ͬ</option>
		  <option value="7">ί�к�ͬ</option>
		  <option value="8">�����ͬ</option>
		  <option value="9">�����ͬ</option>
		  <option value="0" selected="selected">������ͬ</option>
		</c:if>
			
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
    <tr>
    <td align="right"><label class="Validform_label"> ʵʩ��ʽ </label></td>
		<td class="value">
		<select id="contactMethod" name="contactMethod" >
		<c:if test="${contactMethod == null}">
		  <option value="0">�����б�</option>
		  <option value="1">�����б�</option>
		  <option value="2">������̸��</option>
		  <option value="3">ѯ��</option>
		  <option value="4">��һ��Դ</option>
		</c:if>
		<c:if test="${contactMethod == 0}">
		  <option value="0" selected="selected">�����б�</option>
		  <option value="1">�����б�</option>
		  <option value="2">������̸��</option>
		  <option value="3">ѯ��</option>
		  <option value="4">��һ��Դ</option>
		</c:if>
		<c:if test="${contactMethod == 1}">
		  <option value="0">�����б�</option>
		  <option value="1"  selected="selected">�����б�</option>
		  <option value="2">������̸��</option>
		  <option value="3">ѯ��</option>
		  <option value="4">��һ��Դ</option>
		</c:if>
		<c:if test="${contactMethod == 2}">
		  <option value="0">�����б�</option>
		  <option value="1">�����б�</option>
		  <option value="2"  selected="selected">������̸��</option>
		  <option value="3">ѯ��</option>
		  <option value="4">��һ��Դ</option>
		</c:if>
		<c:if test="${contactMethod == 3}">
		  <option value="0">�����б�</option>
		  <option value="1">�����б�</option>
		  <option value="2">������̸��</option>
		  <option value="3"  selected="selected">ѯ��</option>
		  <option value="4">��һ��Դ</option>
		</c:if>
		<c:if test="${contactMethod == 4}">
		  <option value="0">�����б�</option>
		  <option value="1">�����б�</option>
		  <option value="2">������̸��</option>
		  <option value="3">ѯ��</option>
		  <option value="4"  selected="selected">��һ��Դ</option>
		</c:if>
		</select>
		
		</span>
		</td>
    </tr>
    <td align="right"><label class="Validform_label"> �������� </label></td>
		<td class="value"><select id="auditctx" name="auditctx" >
		<c:if test="${auditctx == null}">
		  <option value="0">ǩ������ͬ</option>
		  <option value="1">ǩ������Э��</option>
		  <option value="2">ǩ�����Э��</option>
		</c:if>
		<c:if test="${auditctx == 0}">
		  <option value="0" selected="selected">ǩ������ͬ</option>
		  <option value="1">ǩ������Э��</option>
		  <option value="2">ǩ�����Э��</option>
		</c:if>
		<c:if test="${auditctx == 1}">
		  <option value="0">ǩ������ͬ</option>
		  <option value="1"  selected="selected">ǩ������Э��</option>
		  <option value="2">ǩ�����Э��</option>
		</c:if>
		<c:if test="${auditctx == 2}">
		  <option value="0">ǩ������ͬ</option>
		  <option value="1">ǩ������Э��</option>
		  <option value="2"  selected="selected">ǩ�����Э��</option>
		</c:if>
		
		</select>
		
		</span>
		</td>
    </tr>
    <tr>
		<td align="right"><label class="Validform_label"> ��ͬ���� </label></td>
		<td class="value"><input class="inputxt" id="content" name="content" value="${contract.content}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ�Է����� </label></td>
		<td class="value"><input class="inputxt" id="partyName" name="partyName" value="${contract.partyName}" datatype="s2-50">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ�Է�ס�� </label></td>
		<td class="value"><input class="inputxt" id="partyaddress" name="partyaddress" value="${contract.partyaddress}" datatype="s2-50">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ�Է����� </label></td>
		<td class="value"><input class="inputxt" id="partyType" name="partyType" value="${contract.partyType}" datatype="s2-50">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ�Է��ʱ� </label></td>
		<td class="value"><input class="inputxt" id="partyRegValue" name="partyRegValue" value="${contract.partyRegValue}" datatype="s2-50">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%-- <tr>
		<td align="right"><label class="Validform_label"> �׷� </label></td>
		<td class="value"><input class="inputxt" id="partyA" name="partyA" value="${contract.partyA}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> �ҷ� </label></td>
		<td class="value"><input class="inputxt" id="partyB" name="partyB" value="${contract.partyB}">
		<span class="Validform_checktip"></span>
		</td>
	</tr> --%>
	<tr>
		<td align="right"><label class="Validform_label"> Ԥ���ʽ� </label></td>
		<td class="value"><input class="easyui-numberbox" id="budgetValue" name="budgetValue" value="${contract.budgetValue}" style="width: 151px" datatype="s2-50">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ��� </label></td>
		<td class="value"><input class="inputxt" id="contractValue" name="contractValue" value="${contract.contractValue}" style="width: 151px">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ��� </label></td>
		<td class="value"><input class="inputxt" id="contractObject" name="contractObject" value="${contract.contractObject}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ǩ������ </label></td>
		<td class="value"><input class="easyui-datebox" id="signingDate" name="signingDate" value="${contract.signingDate}" style="width: 157px">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ִ����� </label></td>
		<td class="value"><input class="inputxt" id="implementationStage" name="implementationStage" value="${contract.implementationStage}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ִ�н�� </label></td>
		<td class="value"><input class="easyui-numberbox" id="implementationFree" name="implementationFree" value="${contract.implementationFree}" style="width: 151px">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ������� </label></td>
		<td class="value"><input class="easyui-datebox" id="finishingDate" name="finishingDate" value="${contract.finishingDate}" style="width: 157px">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��Ч�� </label></td>
		<td class="value"><input class="easyui-datebox" id="effectiveDate" name="effectiveDate" value="${contract.effectiveDate}"  style="width: 157px">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��ǩ��� </label></td>
		<td class="value"><input class="inputxt" id="renewal" name="renewal" value="${contract.renewal}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> �ϴ���ͬ </label></td>
		<td class="value"><input class="inputxt" id="contractFilePath" name="contractFilePath" value="${contract.contractFilePath}">
		 <a id="btn_uploadfile" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">�ϴ��ļ�</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> �ϴ����ɹ������ </label></td>
		<td class="value"><input class="inputxt" id="otherfile" name="otherfile" value="${contract.otherfile}">
		 <a id="btn_uploadfile2" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">�ϴ��ļ�</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
</table>

</form>
<script type="text/javascript">
$("#btn_uploadfile").click(function(){
	createwindow("�ļ��ϴ�","fileupload/fileupload.jsp",350,130,returnFile);
	    });
$("#btn_uploadfile2").click(function(){
	createwindow("�ļ��ϴ�","fileupload/fileupload.jsp",350,130,returnFile2);
	    });
function returnFile(data){
	$('#contractFilePath').val(data.code);
   
}
function returnFile2(data){
	$('#otherfile').val(data.code);
   
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