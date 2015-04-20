<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.workflow.service.*"%>
<%@page import="com.workflow.serviceimpl.*"%>
<%@page import="com.workflow.orm.*"%>
<%@page import="com.common.Format"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.runtime.*"%>
<%@page import="org.activiti.engine.task.*"%>
<%@page import="org.activiti.engine.form.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/outwindow.js"></script>
</head>
<%
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
// Get Activiti services
RepositoryService repositoryService = processEngine
	.getRepositoryService();
FormService formService=processEngine.getFormService();
RuntimeService runtimeService = processEngine
	.getRuntimeService();
TaskService taskService = processEngine
	.getTaskService();
%>
<body class="mainbody" onLoad="this.focus()">
   <div style="text-align: center;position: relative;width: 100%;height:700px;overflow:auto;border:1px solid 1px #cccccc;" >
    <table id="dg" class="easyui-datagrid" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'instanceid',width:100" align="center">实例ID</th>
    <th data-options="field:'instancename',width:100" align="center">实例名称</th>
    <th data-options="field:'initstaffname',width:100" align="center">发起人</th>
    <th data-options="field:'initdate',width:100" align="center">发起时间</th>
     <th data-options="field:'op',width:100" align="center">操作</th>
    </tr>
    </thead>
    <tbody>
<%
	for (ProcessInstance processInstance : runtimeService.createProcessInstanceQuery().list()) {
		pageContext.setAttribute("processInstance", processInstance);
%>							        
			<tr>
			    <td><%=processInstance.getId() %></td>
			    <%InstanceService instanceimpl=new InstanceServiceImpl();
			    InstanceInfo instance=instanceimpl.loadInstanceById(processInstance.getId());
			     %>
			    <td><%=instance.getInstancename() %></td>
			    <td><%=instance.getInitstaffname() %></td>
			    <td><%=instance.getInitdate()%></td>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" 
				        onclick="view('<%= processInstance.getId()%>')">查看实例图</a>
				                         </td>
			</tr>
			<%} %>
    </tbody>
    </table>
    </div>
 <script type="text/javascript">
      function view(pId)
{ 
    var url='workflow/instancemanage/view.jsp?processInstanceId='+pId;
    createwindowNoButton('流程跟踪图',url,'800px','600px');
}
 </script>
</body>
</html>
