<%@page import="com.common.PageUtil"%>
<%@page import="com.common.Format"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.history.*"%>
<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.entity.system.*" pageEncoding="gb2312"%>
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
String type=request.getParameter("type");
if(type==null)
	type="0";
UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
String staffcode=UserInfo.getStaffcode();
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
// Get Activiti services
HistoryService historyService=processEngine.getHistoryService();
RepositoryService repositoryService = processEngine
.getRepositoryService();
SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
%>
<body class="mainbody" onLoad="this.focus()">
   <a href="viewinstance.jsp?type=0" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        >全部流程</a>
				        <a href="viewinstance.jsp?type=1" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        >未结束流程</a>
				        <a href="viewinstance.jsp?type=2" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        >历史流程</a>
   <div style="text-align: center;position: relative;width: 100%;height:700px;overflow:auto;border:1px solid 1px #cccccc;" >

    <table id="dg" class="easyui-datagrid" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'instanceid',width:50" align="center">实例ID</th>
    <th data-options="field:'instancename',width:50" align="center">流程名称</th>
    <th data-options="field:'initstaffname',width:50" align="center">发起人</th>
    <th data-options="field:'initdate',width:50" align="center">发起时间</th>
    <th data-options="field:'enddate',width:50" align="center">结束时间</th>
     <th data-options="field:'op',width:50" align="center">操作</th>
    </tr>
    </thead>
    <tbody>
<%
for (HistoricProcessInstance processInstance : historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().desc().list()) {
	pageContext.setAttribute("processInstance", processInstance);
	String flowname="",staffname="";
	String startid=processInstance.getStartUserId();
	if(startid!=null){
		StaffInfo user=new StaffInfo(startid);
		staffname=user.getName();
		ProcessDefinition processDefinition=repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
		flowname=processDefinition.getName();
	
		if(staffcode.equals(startid)){
		String endtime="";
		if(processInstance.getEndTime()!=null){
			endtime=sdf.format(processInstance.getEndTime());
		}
		if(type.equals("0")){
%>							        
			<tr>
			    <td><%=processInstance.getId() %></td>

			    <td><%=flowname%></td>
			    <td><%=staffname%></td>
			    <td><%=sdf.format(processInstance.getStartTime())%></td>
			    <td><%=endtime%></td>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="view('<%= processInstance.getId()%>')">查看实例图</a>
				                         </td>
			</tr>
			<%}
		if(type.equals("1")&&endtime.equals("")){
			%>							        
			<tr>
			    <td><%=processInstance.getId() %></td>

			    <td><%=flowname%></td>
			    <td><%=staffname%></td>
			    <td><%=sdf.format(processInstance.getStartTime())%></td>
			    <td><%=endtime%></td>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="view('<%= processInstance.getId()%>')">查看实例图</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-remove',plain:true" 
				        onclick="deleteinstance('<%= processInstance.getId()%>')">结束流程实例</a>
				                         </td>
			</tr>
			<%}
		if(type.equals("2")&&!(endtime.equals(""))){
			%>							        
			<tr>
			    <td><%=processInstance.getId() %></td>

			    <td><%=flowname%></td>
			    <td><%=staffname%></td>
			    <td><%=sdf.format(processInstance.getStartTime())%></td>
			    <td><%=endtime%></td>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="view('<%= processInstance.getId()%>')">查看实例图</a>
				                         </td>
			</tr>
			<%}
		}
		}
	} %>
    </tbody>
    </table>
    </div>

      <script type="text/javascript">
      function view(pId)
   { 
       var url='workflow/instancemanage/view.jsp?processInstanceId='+pId;
       createwindowNoButton('流程跟踪图',url,'800px','600px');
   }
   function deleteinstance(pid){
   var path='<%=path%>';
   $.post(path+"/std_instance/deletedo.jsp",
           			    {
           			      pid:pid,
           			    },
           				 function(data,status){
           			    	result(data);
           			    	//window.location.reload();
           			    }); 
   }
   function result(data){
   window.location.reload();
       	  show(data);
       	  closemsgshow();
       	  
       	 // window.location.reload();
   }
    </script>
</body>
</html>

