<%@ page language="java" pageEncoding="UTF-8" import="java.util.*,com.entity.system.*,java.text.SimpleDateFormat"%>
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

Date date=new Date();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
String datestr = format.format(date);
String path = request.getContextPath();
 String taskId=request.getParameter("id");
 ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	TaskService taskService = processEngine
			.getTaskService();
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
	//}
}

</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="/ncycoa/checkproject.htm?publicreport" enctype="multipart/form-data" method="post">
&nbsp;&nbsp;<a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" 
				        onclick="F1()">发布（下发）</a>&nbsp;&nbsp;<a href="/ncycoa/std_check/checkproject/deleteinstance.jsp?id=<%=taskId %>" class="easyui-linkbutton"
				        data-options="iconCls:'icon-remove',plain:true" >结束流程</a>
<input id="taskid" name="taskid" type="hidden" value="<%=taskId%>">
<input id="result" name="result" type="hidden">
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
	<%
	Object reportpathObject=taskService.getVariable(taskId, "reportpath");
	if(reportpathObject!=null){
		String reportpath=reportpathObject.toString();
	String[] reportpaths=reportpath.split(";");
	for(int i=0;i<reportpaths.length;i++){
	%>
	<tr>
		<td align="right"><label class="Validform_label"> 初评报告<%=i+1 %> </label></td>
		<td class="value"><input type="button"  value=" 初评报告<%=i+1 %>  " onclick="officeopen('<%=reportpaths[i]%>')" />
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%}} %>
			<%
	Object allreportObject=taskService.getVariable(taskId, "allreportpath");
	if(allreportObject!=null){
		String allreportpath=allreportObject.toString();
	String[] allreportpaths=allreportpath.split(";");
	%>
	<tr>
		<td align="right"><label class="Validform_label"> 全市评审报告</label></td>
		<td class="value"><input type="button"  value=" 全市评审报告  " onclick="officeopen('<%=allreportpaths[0]%>')" />
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%} %>
		<tr>
		<td align="right" width="30%"><label class="Validform_label"> 相关委员会</label></td>
		<td class="value"  width="70%"><input class="inputxt" style="width:200px;" id="CommitteeName" name="CommitteeName">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
			<tr>
		<td align="right"><label class="Validform_label"> 完成时限 </label></td>
		<td class="value"><input class="inputxt" style="width:200px;" class="Wdate" type="Wdate" id="endTime" onfocus="new WdatePicker({lang:'zh-cn'})" name="endTime" value="<%=datestr%>">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
</table>

</form>


</body>