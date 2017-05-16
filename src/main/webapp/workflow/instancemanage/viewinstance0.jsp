<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.common.Format"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.history.*"%>
<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.entity.system.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>�Ĵ��ϳ��̲�ר��</title>
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
				        >ȫ������</a>
				        <a href="viewinstance.jsp?type=1" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        >δ��������</a>
				        <a href="viewinstance.jsp?type=2" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        >��ʷ����</a>
   <div style="text-align: center;position: relative;width: 100%;height:700px;overflow:auto;border:1px solid 1px #cccccc;" >
    <table id="dg" class="easyui-datagrid" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'instanceid',width:100" align="center">ʵ��ID</th>
    <th data-options="field:'instancename',width:100" align="center">��������</th>
    <th data-options="field:'initstaffname',width:100" align="center">������</th>
    <th data-options="field:'initdate',width:100" align="center">����ʱ��</th>
    <th data-options="field:'enddate',width:100" align="center">����ʱ��</th>
     <th data-options="field:'op',width:100" align="center">����</th>
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
		}
		ProcessDefinition processDefinition=repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
		flowname=processDefinition.getName();
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
			    <td>
			    <% if(endtime.equals("")){%><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" 
				        onclick="view('<%= processInstance.getId()%>')">�鿴ʵ��ͼ</a>
				        <%} %>
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
				        data-options="iconCls:'icon-add',plain:true" 
				        onclick="view('<%= processInstance.getId()%>')">�鿴ʵ��ͼ</a>
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
			    <td>
				                         </td>
			</tr>
			<%}
			}%>
    </tbody>
    </table>
    </div>
 <script type="text/javascript">
      function view(pId)
{ 
    var url='workflow/instancemanage/view.jsp?processInstanceId='+pId;
    createwindowNoButton('���̸���ͼ',url,'800px','600px');
}
 </script>
</body>
</html>
