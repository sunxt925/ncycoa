<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.workflow.service.*"%>
<%@page import="com.workflow.serviceimpl.*"%>
<%@page import="com.workflow.orm.*"%>
<%@page import="com.common.Format"%>
<%@ page import="org.jbpm.api.task.Task"%>
<%@ page import="java.util.zip.ZipInputStream"%>
<%@ page import="org.jbpm.api.*" %>
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
ProcessEngine pe = Configuration.getProcessEngine();
ExecutionService es = pe.getExecutionService();
List<ProcessInstance> lists = es.createProcessInstanceQuery().list();//流程实例列表
%>
<body class="mainbody" onLoad="this.focus()">
   <div style="text-align: center;position: relative;width: 70%;height:700px;overflow:auto;border:1px solid 1px #cccccc;" >
    <table id="dg" class="easyui-datagrid" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'instanceid',width:80">实例ID</th>
    <th data-options="field:'instancename',width:80">实例名称</th>
    <th data-options="field:'initstaffname',width:80">发起人</th>
    <th data-options="field:'initdate',width:80">发起时间</th>
    <th data-options="field:'initstate',width:80">状态</th>
     <th data-options="field:'op',width:80">操作</th>
    </tr>
    </thead>
    <tbody>
	<%for(ProcessInstance pi : lists){ %>										        
			<tr>
			    <td><%=pi.getId() %></td>
			    <%InstanceService instanceimpl=new InstanceServiceImpl();
			    InstanceInfo instance=instanceimpl.loadInstanceById(pi.getId());
			     %>
			    <td><%=instance.getInstancename() %></td>
			    <td><%=instance.getInitstaffname() %></td>
			    <td><%=instance.getInitdate()%></td>
			    <td><%=pi.getState() %></td>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="view('<%= pi.getId()%>')">查看实例图</a>
				                         </td>
			</tr>
			<%} %>
    </tbody>
    </table>
    </div>
 <script type="text/javascript">
      function view(pId)
{ 
    var url='jbmpworkflow/instancemanage/view.jsp?id='+pId;
    createwindowNoButton('流程跟踪图',url,'800px','600px');
}
 </script>
</body>
</html>
