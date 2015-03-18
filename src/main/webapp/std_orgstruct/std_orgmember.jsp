<%@page import="com.common.PageUtil"%>
<%@page import="com.common.*,com.db.*"%>
<%@ page language="java" import="java.util.*,com.entity.system.*" pageEncoding="gb2312"%>
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
</head>
<%
String orgcode=request.getParameter("orgcode");
String positioncode=request.getParameter("positioncode");
String positioncodezhuren=request.getParameter("positioncodezhuren");
Org orginfo=new Org(orgcode);
Staff staff = new Staff();
DataTable stafflist=staff.getAllMemberList(positioncode,orgcode);
DataTable stafflist2=staff.getAllMemberList(positioncodezhuren,orgcode);
%>
<body class="mainbody" onLoad="this.focus()">

<table width="100%"  height="6" border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td class="table_td_jb_iframe"> 
        		机构职能:<%=orginfo.getOrgDesc() %>
    </td>
  </tr>
</table>
   <div style="text-align: center;position: relative;width: 100%;height:350px;overflow:auto;border:1px solid 1px #cccccc;" >
    <table id="dg" class="easyui-datagrid" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
 
    <tr>
    <th data-options="field:'staffcode',width:120">成员编码</th>
    <th data-options="field:'idcard',width:150">身份证号</th>
    <th data-options="field:'staffname',width:70">成员姓名</th>
    <th data-options="field:'gender',width:50">性别</th>
    <th data-options="field:'gender',width:50">职位</th>
    </tr>
    </thead>
    <tbody>
<%
	for (int i=0;i< stafflist.getRowsCount();i++) {
		DataRow row=stafflist.get(i);
%>							      
			<tr>
			    <td><%=row.get("staffcode") %></td>
			    <td><%=row.get("idcard") %></td>
			    <td><%=row.get("staffname") %></td>
			    <td><%=row.get("gender") %></td>
			    <td>成员</td>
			</tr>
			<%} %>
			<%
	for (int i=0;i< stafflist2.getRowsCount();i++) {
		DataRow row=stafflist2.get(i);
%>							      
			<tr>
			    <td><%=row.get("staffcode") %></td>
			    <td><%=row.get("idcard") %></td>
			    <td><%=row.get("staffname") %></td>
			    <td><%=row.get("gender") %></td>
			    <td>主任</td>
			</tr>
			<%} %>
    </tbody>
    </table>
    </div>

      <script type="text/javascript">
    </script>
</body>
</html>

