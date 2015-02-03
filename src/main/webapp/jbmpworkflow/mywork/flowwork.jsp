<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.common.Format"%>
<%@ page import="org.jbpm.api.task.Task"%>
<%@ page import="java.util.zip.ZipInputStream"%>
<%@ page import="org.jbpm.api.*" %>
<%@ page language="java" import="java.util.*,com.entity.system.*" pageEncoding="gb2312"%>
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
ProcessEngine pe = Configuration.getProcessEngine();
TaskService ts = pe.getTaskService();
UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
List<Task> lists = ts.findPersonalTasks(staffcode); // 代办任务列表
%>
<body class="mainbody" onLoad="this.focus()">

    <table id="dg" class="easyui-datagrid" width="100%" height="100%" border="1"  cellpadding="0" cellspacing="0" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'instanceid',width:80">任务ID</th>
    <th data-options="field:'instancename',width:80">任务名称</th>
     <th data-options="field:'op',width:80">操作</th>
    </tr>
    </thead>
    <tbody>
	<%for(Task task : lists){ %>										        
			<tr><%
String taskname=task.getName();
String taskurl=task.getFormResourceName();
if(taskurl==null){
	taskurl=task.getDescription();
	String parenttaskid=taskname;
	Task parentTask = ts.getTask(parenttaskid);
	taskname=parentTask.getName();
}
taskurl=path+"/"+taskurl;
//String url=taskurl+"?id="+task.getId()
%>
			    <td><%=task.getId()%></td>
			    <td><%=taskname%></td>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="view('<%=taskurl%>','<%=task.getId()%>')">查看任务</a>
				                         </td>
			</tr>
			<%} %>
    </tbody>
      
  </table>
 <script type="text/javascript">
      function view(taskurl,id)
{ 
var url=taskurl+'?id='+id;
    window.open(url);
}
 </script>
</body>
</html>
