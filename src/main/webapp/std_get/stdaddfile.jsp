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
var count = 1;  
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
    var staffnametest=document.getElementById("gstStaffname").value;
    alert(staffnametest);
    var uplist = $("input[name^=uploads]");  
var arrId = [];  
for (var i=0; i< uplist.length; i++){  
    if(uplist[i].value){  
        arrId[i] = uplist[i].id;  
    }  
    }  
$.ajaxFileUpload({  
    url:'stdget.htm?upfile',  
    secureuri:false,  
    fileElementId: arrId,  //这里不在是以前的id了，要写成数组的形式哦！  
    dataType: 'json',  
    data: {  
    	staffnametest:staffnametest,
                 //需要传输的数据  
            },  
    success: function (data){  
    },  
    error: function(data){  
    }  
});  
}


</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="stdget.htm?save" enctype="multipart/form-data" method="post">
<!-- <input type="hidden" id="btn_sub" class="btn_sub" />  -->
<input id="gstStaffname" name="gstStaffname" type="hidden" value="hello  world">
<input type="button"  value="添加附件" onclick="createInput('more');" />  <input type="hidden" id="btn_sub" onClick="ajaxFileUploadImg()"/>
<div id="more"></div> 


</form>


</body>