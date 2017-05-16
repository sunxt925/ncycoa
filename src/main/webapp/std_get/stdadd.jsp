<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<%
String path = request.getContextPath();
%>
<html>
<head>
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="<%=path%>/jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=path%>/jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/datagrid.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/validform/js/Validform_Datatype.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/ajaxfileupload.js"></script>
<script language=
                "javascript" type="text/javascript" src="<%=path%>/js/MyDatePicker/WdatePicker.js">  </script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
<script type="text/javascript">
var count = 2;  
/** 
* 生成多附件上传框 
*/  
function createInput(parentId){  
    count++;  
    var str = '<div name="div" ><font style="font-size:12px;">附件</font>'+  
    '   '+ '<input type="file" contentEditable="false" id="uploads' + count + '' +  
    '" name="uploads'+ count +'" value="" style="width: 220px"/><input type="button"  value="删除" onclick="removeInput(event)" />'+'</div>';  
    document.getElementById(parentId).insertAdjacentHTML("beforeEnd",str);  
}  
/** 
* 删除多附件删除框 
*/  
function removeInput(evt, parentId){  
   var el = evt.target == null ? evt.srcElement : evt.target;  
   var div = el.parentNode;  
   var cont = document.getElementById('more');         
   if(cont.removeChild(div) == null){  
    return false;  
   }  
   return true;  
} 
function addOldFile(data){  
    var str = '<div name="div' + data.name + '" ><a href="#" style="text-decoration:none;font-size:12px;color:red;" onclick="removeOldFile(event,' + data.id + ')">删除</a>'+  
    '   ' + data.name +   
    '</div>';  
    document.getElementById('oldImg').innerHTML += str;  
}  
  
function removeOldFile(evt, id){  
    //前端隐藏域，用来确定哪些file被删除，这里需要前端有个隐藏域  
    $("#imgIds").val($("#imgIds").val()=="" ? id :($("#imgIds").val() + "," + id));  
    var el = evt.target == null ? evt.srcElement : evt.target;  
    var div = el.parentNode;  
    var cont = document.getElementById('oldImg');      
    if(cont.removeChild(div) == null){  
        return false;  
    }  
    return true;  
} 
function ajaxFileUploadImg(){  
    //获取file的全部id  
    var uplist = $("input[name^=uploads]");  
var arrId = [];  
for (var i=0; i< uplist.length; i++){  
    if(uplist[i].value){  
        arrId[i] = uplist[i].id;  
    }  
    }  
    ////////////////
    var id=document.getElementById("id").value;
    var gstStaffname=document.getElementById("gstStaffname").value;
    var gstStaffcode=document.getElementById("gstStaffcode").value;
    var gstFilename=document.getElementById("gstFilename").value;
    var gstFilecode=document.getElementById("gstFilecode").value;
    var gstFiletype=document.getElementById("gstFiletype").value;
    var gstFilelevel=document.getElementById("gstFilelevel").value;
    var gstPublictime=document.getElementById("gstPublictime").value;
    var gstStafforg=document.getElementById("gstStafforg").value;
    ////////////////
$.ajaxFileUpload({  
    url:'stdget.htm?upfile',  
    secureuri:false,  
    fileElementId: arrId,  //这里不在是以前的id了，要写成数组的形式哦！  
    dataType: 'plain',  
    data: {  
                 //需要传输的数据  
    	id:id,
    	gstStaffname:gstStaffname,gstFiletype:gstFiletype,gstFilelevel:gstFilelevel,gstPublictime:gstPublictime,
    	gstStaffcode:gstStaffcode,gstFilename:gstFilename,gstFilecode:gstFilecode,gstStafforg:gstStafforg,
            },  
    success: function (data){  
    	var win = frameElement.api.opener;
    	frameElement.api.close();
    	win.reloadTable();
    },  
    error: function(data){  
    }  
});  
}


</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="stdget.htm?save" enctype="multipart/form-data" method="post">
<input type="hidden" id="btn_sub" onClick="ajaxFileUploadImg()"/>
<input id="id" name="id" type="hidden" value="${getstd.id}">
<input id="gstStaffname" name="gstStaffname" type="hidden" value="${staffname}">
<input id="gstStaffcode" name="gstStaffcode" type="hidden" value="${staffcode}">
<table style="width:100%;border-spacing:1px;"  class="formtable">
	<tr>
		<td align="right" width="30%"><label class="Validform_label"> 采标文件名称 </label></td>
		<td class="value"  width="70%"><input class="inputxt" style="width:200px;" id="gstFilename" name="gstFilename" value="${getstd.gstFilename}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label"> 采标文件编号 </label></td>
		<td class="value"><input class="inputxt" style="width:200px;" id="gstFilecode" name="gstFilecode" value="${getstd.gstFilecode}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 采标文件类型 </label></td>
		<td class="value">
		<select class="inputxt" id="gstFiletype" name="gstFiletype" style="width:205px;">
			<option value="0">法律法规</option>
			<option value="1">规范性文件</option>
			<option value="2">外来标准</option>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label"> 采标文件层级 </label></td>
		<td class="value">
		<select class="inputxt" id="gstFilelevel" name="gstFilelevel" style="width:205px;">
			<option value="0">国家</option>
			<option value="1">地方</option>
			<option value="2">行业</option>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label"> 文件发布时间 </label></td>
		<td class="value"><input class="inputxt" style="width:200px;" class="Wdate" type="Wdate" id="gstPublictime" onfocus="new WdatePicker({lang:'zh-cn'})" name="gstPublictime" value="${getstd.gstPublictime}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label"> 管理部门 </label></td>
		<td class="value"><input class="inputxt" style="width:200px;" id="gstStafforg" name="gstStafforg" value="${orgname}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 上传文件 </label></td>
		<td class="value"><input type="file" id="uploads1" name="uploads1" style="width:205px;">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label"> <input type="button"  value="添加附件" onclick="createInput('more');" /></label></td>
		<td class="value"><div id="more"></div>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
</table>

</form>


</body>