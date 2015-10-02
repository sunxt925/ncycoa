<%@ page language="java" pageEncoding="UTF-8" import="java.util.*,com.entity.system.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.ProcessDefinition"%>

<%@page import="org.activiti.engine.task.Task"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<%
String path = request.getContextPath();
 String taskId=request.getParameter("id");
 ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	TaskService taskService = processEngine
			.getTaskService();
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
		filepath='\\checkproject\\'+filepath;
	  var url='/ncycoa/std_check/std_officeopen.jsp?filename='+filepath;
	     window.open(url);
}
function F1()
{
	//if (CkEmptyStr(document.all("DocNo"),"层次码不能为空！"))
	//{
		//alert (document.all("act"));
		document.formobj.result.value="1";
		document.all("formobj").submit();
// 		   setTimeout(function(){
// 			   var api = frameElement.api;
// 			   api.close();
// 			   W = api.opener;
// 			   W.reloadTable();
// 			      return true;
// 			       },100);
// 	}
}
function F2()
{

		document.formobj.result.value="2";
		document.all("formobj").submit();
}
function F8()
{ 
		var s=document.formobj.result.value;
		if(s==null||s==''){
			alert("请选择操作！");
		}else{
		if(s=='3'){
		 	   $.post("/ncycoa/std_check/checkproject/deleteinstance.jsp?id=<%=taskId %>",
		  			    {

		  			    },
		  				 function(data,status){
		  			    	window.close();
		  			    }); 
			}else{
				document.all("Submit").click();
			}
		}
}
</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="/ncycoa/checkproject.htm?approve" enctype="multipart/form-data" method="post">
&nbsp;&nbsp;<a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" 
				        onclick="F1()">审核通过</a>&nbsp;&nbsp;<a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="F2()">驳回</a><a href="/ncycoa/std_check/checkproject/deleteinstance.jsp?id=<%=taskId%>" class="easyui-linkbutton"
				        data-options="iconCls:'icon-remove',plain:true" >结束流程</a>
<input id="taskid" name="taskid" type="hidden" value="<%=taskId%>">
<input id="result" name="result" type="hidden" >
<table style="width:100%;border-spacing:1px;"  class="formtable">
	<tr>
		<td align="right" width="30%"><label class="Validform_label"> 发起人</label></td>
		<td class="value"  width="70%"><input class="inputxt" style="width:200px;" id="staffName" name="staffName" value="<%=taskService.getVariable(taskId, "staffName") %>">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
			<tr>
		<td align="right"><label class="Validform_label"> 评审方案编码</label></td>
		<td class="value"><input class="inputxt" style="width:200px;" id="checkCode" name="checkCode" value="<%=taskService.getVariable(taskId, "checkCode") %>">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label"> 评审方案名称</label></td>
		<td class="value"><input class="inputxt" style="width:200px;" id="checkName" name="checkName" value="<%=taskService.getVariable(taskId, "checkName") %>">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<% String startdate=taskService.getVariable(taskId, "startdate").toString();
	System.out.println(startdate);
	%>
		<tr>
		<td align="right"><label class="Validform_label"> 发起时间 </label></td>
		<td class="value"><input class="inputxt" style="width:200px;" class="Wdate" type="Wdate" id="startTime" onfocus="new WdatePicker({lang:'zh-cn'})" name="startTime" value="<%=taskService.getVariable(taskId, "startdate") %>">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
			<%
			Object filepathObject=taskService.getVariable(taskId, "filepath");
			if(filepathObject!=null){
				String filepath=filepathObject.toString();
	String[] filepaths=filepath.split(";");
	%>
	<tr>
		<td align="right"><label class="Validform_label"> 评审方案 </label></td>
		<td class="value"><input type="button"  value=" 评审方案内容   " onclick="officeopen('<%=filepaths[0]%>')" />
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%} %>
		<tr>
	       <td align="right"><label class="Validform_label"> 审核意见 </label></td>
		  <td class="value">
		    <label>
		    <textarea name="suggest" id="suggest"   style="width:200px;height:50px"></textarea>
		    </label><span class="Validform_checktip"></span>
		</td>
        </tr> 
<!--         <tr> -->
<!-- 	       <td align="right"><a id="btn_sub" name="btn_sub" style="display:none" href="#" onClick="F8()">保存[F8]</a><input type="submit" name="Submit" value="提交" style="display:none"></td> -->
<!-- 		  <td class="value"> -->
<!-- 		  	<label><input name="result" type="radio" value="1" />审核通过 </label>  -->
<!-- 			<label><input name="result" type="radio" value="2" />驳回 </label>  -->
<!-- 			<label><input name="result" type="radio" value="3" />结束流程 </label>  -->
<!-- 		</td> -->
<!--         </tr>  -->
</table>

</form>


</body>