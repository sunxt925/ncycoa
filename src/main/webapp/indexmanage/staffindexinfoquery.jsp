<%@page import="com.entity.index.AllMeritGroupMember"%>
<%@page import="com.entity.index.AllMeritGroup"%>
<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
                  request.getServerName()+":"+
		          request.getServerPort()+path+"/";
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
</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
String treepath="refindexjson.jsp?objectcode="+u.getStaffcode();
AllMeritGroup allMeritGroup=AllMeritGroupMember.getAllmeritMode(u.getStaffcode());

%>
<body class="easyui-layout">
	<div data-options="region:'west',split:false,title:'ָ����ϵ��'" style="width:400px;padding:10px;">
	  <table id="dg" class="easyui-treegrid"    data-options="url:'<%=treepath %>' ,method: 'get',rownumbers: true,idField: 'indexcode',treeField: 'indexname',fitColumns:true" >
		<thead>
			<tr>
				<th data-options="field:'indexname',width:100">ָ����ϵ����</th>
			</tr>
		</thead>
	</table> 
	</div>
	<div data-options="region:'center'">

		<div style="width: 90%;padding: 10px">
			<div style="width: 90%;padding: 20px">
				<div id="p" class="easyui-panel" title="�����ۺϼ�Чģ��"
					style="width:800px;height:300px;padding:10px;">
					<%if(allMeritGroup!=null) {%>
					      <label><span>ģ���ţ�&nbsp;&nbsp;&nbsp;&nbsp;<%=allMeritGroup.getGroupNo() %></span></label><br><br><br><br>
					    <label><span>ģ�����ƣ�&nbsp;&nbsp;&nbsp;&nbsp;<%=allMeritGroup.getGroupName() %></span></label><br><br><br><br>
					    <label><span>ģ�幹�ɣ�&nbsp;&nbsp;&nbsp;&nbsp;<%=allMeritGroup.getAllmeritfunc() %></span></label><br><br><br><br>
					    <label><span>ģ��˵����&nbsp;&nbsp;&nbsp;&nbsp;<%=allMeritGroup.getMemo() %></span></label><br><br><br><br>
					  <%}else{ %>
					  <label><span>����δ�����ۺϼ�Ч���飬��ѯ����������Ϣ������</span></label><br><br><br><br>
					  <%} %>
					  </div>
			</div>
			<div style="width: 100%;padding: 20px">
				<div id="p" class="easyui-panel" title="C"
					style="width:800px;height:300px;padding:10px;"></div>
			</div>
		</div>
	</div>
</body>
</html>
