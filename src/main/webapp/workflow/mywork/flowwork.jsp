<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.runtime.*"%>
<%@page import="org.activiti.engine.task.*"%>
<%@ page language="java" import="java.util.*,com.entity.system.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%@page import="org.activiti.engine.form.*"%>
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
<%-- <script type="text/javascript" src="<%=path%>/jscomponent/tools/outwindow.js"></script> --%>
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
UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
%>
<body class="mainbody" onLoad="this.focus()">

    <table id="dg" class="easyui-datagrid" width="100%" height="100%" border="1"  cellpadding="0" cellspacing="0" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'instanceid',width:100" align="center">任务ID</th>
    <th data-options="field:'instancename',width:200" align="center">任务名称</th>
    <th data-options="field:'staffname',width:150" align="center">发起人</th>
     <th data-options="field:'op',width:200" align="center">操作</th>
    </tr>
    </thead>
    <tbody>
<%
	for (Task task : taskService.createTaskQuery().taskAssignee(staffcode).orderByTaskCreateTime().desc().list()) {
		pageContext.setAttribute("task", task);
		TaskFormData formData = formService.getTaskFormData(task.getId());
		String formKey = formData.getFormKey();
		String ownercode=taskService.getVariable(task.getId(), "owner").toString();
		StaffInfo staffinfo=new StaffInfo(ownercode);
		String ownername=staffinfo.getName();
%>									        
			<tr><%
//String url=taskurl+"?id="+task.getId()
%>
			    <td>${task.id}</td>
			    <td>${task.name}</td>
			    <td><%=ownername %></td>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" 
				        onclick="view('<%=formKey%>','<%=task.getId()%>')">查看任务</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" 
				        onclick="picture('workflow/instancemanage/view.jsp','<%=task.getId()%>')">流程跟踪</a>
				                         </td>
			</tr>
			<%} %>
    </tbody>
      
  </table>
 <script type="text/javascript">
      function view(taskurl,id)
{ 
var url=taskurl+'?id='+id;
createwindowNoButton('流程处理',url,'1200px','630px');
}
      function picture(taskurl,id)
      { 
      var url=taskurl+'?taskId='+id;
      createwindowNoButton('流程处理',url,'1000px','500px');
      }
      function createwindowNoButton(title, url, width, height) {
  		width = width ? width : 700;
  		height = height ? height : 400;
  		if (width == "100%" || height == "100%") {
  			width = document.body.offsetWidth;
  			height = document.body.offsetHeight - 100;
  		}
  		if (typeof (windowapi) == 'undefined') {
  			$.dialog({
  				//data:returnValue,
  				modal:true, 
  				content : 'url:' + url,
  				lock : false,
  				width : width,
  				height : height,
  				title : title,
  				opacity : 0.3,
  				cache : false,
  				close:function(){
  				window.location.reload();
  				return true;
  				}
  				
  			});
  		}
  	}
 </script>
</body>
</html>
