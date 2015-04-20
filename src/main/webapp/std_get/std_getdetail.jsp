<%@ page language="java" pageEncoding="UTF-8" import="edu.cqu.ncycoa.domain.GetStd"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<%
String path = request.getContextPath();
%>
<html>
<head>
<title>计划管理</title>
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
function officeopen(filepath){
	  var url='std_get/std_officeopen.jsp?filename='+filepath;
	     window.open(url);
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
		<td align="right" width="30%"><label class="Validform_label"> 录入人员 </label></td>
		<td class="value"  width="70%"><input class="inputxt" style="width:200px;" id="gstStaffname" name="gstStaffname" value="${getstd.gstStaffname}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%
				GetStd stdget=((GetStd)(request.getAttribute("getstd")));
				String type="",level="";
				String Filetype=stdget.getGstFiletype();
				String Filelevel=stdget.getGstFilelevel();
				if(Filetype.equals("0")){type="法律法规";}else if(Filetype.equals("1")){type="规范性文件";}else if(Filetype.equals("2")){type="外来文件";}
				if(Filelevel.equals("0")){level="国家";}else if(Filelevel.equals("1")){level="地方";}else if(Filelevel.equals("2")){level="行业";}
	%>
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
		<td align="right"><label class="Validform_label"> 采标文件类型</label></td>
		<td class="value"><input class="inputxt" style="width:200px;" id="gstFiletype" name="gstFiletype" value="<%=type%>">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 采标文件层级</label></td>
		<td class="value"><input class="inputxt" style="width:200px;" id="gstFilelevel" name="gstFilelevel" value="<%=level%>">
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
		<td class="value"><input class="inputxt" style="width:200px;" id="gstStafforg" name="gstStafforg" value="${getstd.gstStafforg}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%
	String filepath=stdget.getGstFilepath();
	if(filepath!=null&&!(filepath.equals(""))){
	String[] filepaths=filepath.split(";");
	%>
	<tr>
		<td align="right"><label class="Validform_label"> 采标文件 </label></td>
		<td class="value"><input type="button"  value=" 查看采标文件内容   " onclick="officeopen('<%=filepaths[0]%>');" />
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label"> 附件列表</label></td>
		<td class="value"><div id="more">
		<%
		for(int i=1;i<filepaths.length;i++){
		%>
		<div name="div" ><font style="font-size:12px;">附件<%=i %></font>&nbsp;&nbsp;<input type="button"  value="查看附件内容" onclick="officeopen('<%=filepaths[i]%>');" /></div>
		<%} %>
		</div>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%} %>
</table>

</form>


</body>