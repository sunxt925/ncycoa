<%@ page contentType="text/html; charset=gb2312" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html>
<html>
<head>

<META http-equiv=Content-Type content="text/html; charset=gb2312">
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

<script type="text/javascript" src="js/MyDatePicker/WdatePicker.js"></script></HEAD>
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
</style>
<script type="text/javascript">
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
});
	
</script>
</head>
<body style="overflow-x:hidden">



<div style="width: 90%;padding: 10px">
			<div style="width: 90%;padding: 20px">
				<div id="p" class="easyui-panel" title="��ͬ��"
					style="width:700px;height:300px;padding:10px;">
					<table style="width:100%;border-spacing:1px;" class="formtable">
	
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ���� </label></td>
		<td class="value">
		<label>${contract.name}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��ڲ��� </label></td>
		<td class="value">
		<label>${relevantDepartment_disp}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	

	
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ��� </label></td>
		<td class="value">
		
		<c:if test="${contract.type == 0 || contract.type == null}">
		<label>������ͬ</label>
		</c:if>
		<c:if test="${contract.type == 1}">
		<label>������ͬ</label>
			
		</c:if>
		<c:if test="${contract.type == 2}">
		<label>���޺�ͬ</label>
		</c:if>
		<c:if test="${contract.type == 3}">
		<label>�ִ���ͬ</label>
		</c:if>
		<c:if test="${contract.type == 4}">
		<label>������ͬ</label>
		</c:if>
		<c:if test="${contract.type == 5}">
		<label>����ʩ��(ά��)��ͬ</label>
		</c:if>
		<c:if test="${contract.type == 6}">
		<label>������ͬ</label>
		</c:if>
		<c:if test="${contract.type == 7}">
		<label>ί�к�ͬ</label>
		</c:if>
		<c:if test="${contract.type == 8}">
		<label>�����ͬ</label>
		</c:if>
		<c:if test="${contract.type == 9}">
		<label>�����ͬ</label>
		</c:if>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	
	<tr>
		<td align="right"><label class="Validform_label"> ʵʩ��ʽ</label></td>
		<td class="value">
		
		<c:if test="${contract.contactMethod == 0 || contract.contactMethod == null}">
		<label>�����б�</label>
		</c:if>
		<c:if test="${contract.contactMethod == 1}">
		<label>�����б�</label>
			
		</c:if>
		<c:if test="${contract.contactMethod == 2}">
		<label>������̸��</label>
		</c:if>
		<c:if test="${contract.contactMethod == 3}">
		<label>ѯ��</label>
		</c:if>
		<c:if test="${contract.contactMethod == 4}">
		<label>��һ��Դ</label>
		</c:if>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��������</label></td>
		<td class="value">
		
		<c:if test="${contract.auditctx == 0 || contract.auditctx == null}">
		<label>ǩ������ͬ</label>
		</c:if>
		<c:if test="${contract.auditctx == 1}">
		<label>ǩ������Э��</label>
			
		</c:if>
		<c:if test="${contract.auditctx == 2}">
		<label>ǩ�����Э��</label>
		</c:if>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ���� </label></td>
		<td class="value">
		<label>${contract.content}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ�Է����� </label></td>
		<td class="value">
		<label>${contract.partyName}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ�Է�ס�� </label></td>
		<td class="value">
		<label>${contract.partyaddress}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ�Է����� </label></td>
		<td class="value">
		<label>${contract.partyType}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ�Է��ʱ� </label></td>
		<td class="value">
		<label>${contract.partyRegValue}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%-- <tr>
		<td align="right"><label class="Validform_label"> �׷� </label></td>
		<td class="value">
		<label>${contract.partyA}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> �ҷ� </label></td>
		
		<td class="value">
		<label>${contract.partyB}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr> --%>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ��� </label></td>
		<td class="value">
		<label>${contract.contractValue}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��ͬ��� </label></td>
		<td class="value">
		<label>${contract.contractObject}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ǩ������ </label></td>
		<td class="value">
		<label>${contract.signingDate}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ִ����� </label></td>
		<td class="value">
		<label>${contract.implementationStage}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ������� </label></td>
		<td class="value">
		<label>${contract.finishingDate}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��ǩ��� </label></td>
		<td class="value">
		<label>${contract.renewal}</label>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> ��˺�ͬ </label></td>
		<td class="value">
		<a href="#" onclick="openoffice('${contract.contractFilePath}')">��ͬ</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
</table>
	</div>
	</div>
	</div>
	<form  id="formobj" name="formobj"  action="contract-management.htm?exetask"  method="post">
	<input  type="hidden" id="id" name="id" value="${contract.id}">
	<input  type="hidden" id="taskId" name="taskId" value="${taskId}">
			<div style="width: 100%;padding: 20px">
				<div id="p" class="easyui-panel" title="��ע"
					style="width:700px;height:300px;padding:10px;">
					<table style="border-spacing:1px;" >
					   <tr>
					     <td><textarea name="comment" id="comment" style="width: 400px;height: 100px">ͬ��</textarea></td>
					   </tr>
					   <tr>
					   <td>
					   <input type="button"   value="��׼"  onclick="sub('true')">
					    <input type="button"   value="����"  onclick="sub('false')">
					  
				<%-- 	<c:forEach items="${outcomelist }" var="item">
					   
	                   <input type="button"   value="${item }"  onclick="sub(this)">
	                   
	                 </c:forEach> --%>
	                 </td>
	                   </tr>
					</table>
					<table style="width:600px;border-spacing:1px;">
					 <tr bgcolor="#E6E6E6">
		                <td align="center" bgcolor="#EEEEEE">ʱ��</td>
		                <td align="left" bgcolor="#EEEEEE">������</td>
		                <td align="left" bgcolor="#EEEEEE">��׼��Ϣ</td>
	                 </tr>
	                 <tbody >
                      <c:forEach items="${comments }" var="item">
	                   <tr>
	                   <td>${item.time }</td><td>${item.username }</td><td> ${item.msg }</td>
	                   </tr>
	                   </c:forEach>
	                  </tbody>
                      </table>
					</div>
			</div>
<input id="outcome" name="outcome" type="hidden" value="">
<input id="btn_ok" type="hidden" onclick="ret()">
</form>
<script type="text/javascript">
function sub(val){

	
	$('#outcome').val(val);
	$('#formobj').submit();
	ret();
}
function ret(){
	 var api = frameElement.api;
	 (api.data)({code:"refresh"});
}
function openoffice(path){
	if(path==''||typeof(path)=='undefined'){
		$.dialog.alert('��ͬ�ļ�������');
	}else{
		var url = "officeonline/officeopen.jsp?filename="+path;
		$.dialog({
			id:'CLLHG1976D',
			content : 'url:' + url,
			lock : true,
			width : 1000,
			height : 600,
			title : '�ĵ��༭',
			zIndex :3000,
			opacity : 0.3,
			cache : false,
			ok : function() {
				return true;
			},
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
	}
}
</script>
</body>