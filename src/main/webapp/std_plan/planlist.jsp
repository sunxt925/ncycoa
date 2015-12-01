<%@page import="com.entity.std.DocPlan"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable,com.db.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>南充烟草专卖局</title>
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
  // String url="collectjson.jsp?year="+request.getParameter("year");
DocPlan plan=new DocPlan();
DataTable dt = plan.getAllPlan();
%>
<body>
   <div style="text-align: center;position: relative;width: 100%;overflow:auto;">
    <table id="dg" class="easyui-datagrid" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'plandate',width:80" align="center">年度</th>
    <th data-options="field:'planname',width:80" align="center">类别</th>
    <th data-options="field:'uptime',width:80" align="center">制定时间</th>
     <th data-options="field:'op',width:100" align="center">操作</th>
    </tr>
    </thead>
    <tbody>
	<%for(int i=0;i<dt.getRowsCount();i++){ 
	DataRow row=dt.get(i);
	%>										        
			<tr>
			    <td><%=row.getString("plandate") %></td>
			    <td><%=row.getString("planname") %></td>
			    <td><%=row.getString("uptime").substring(0, 10) %></td>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="openoffice('<%=row.getString("planpath")%>')">查看</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-remove',plain:true" 
				         onclick="deleteplan('<%=row.getString("id")%>','<%=row.getString("planpath")%>')">删除</a></td>
			</tr>
			<%} %>
    </tbody>
    </table>
    </div>
 <script type="text/javascript">
      function openoffice(policypath){
    	  var url='std_officeopen.jsp?policypath='+policypath;
    	     window.open(url);
      }
      function deleteplan(id,planpath){
    	   var path='<%=path%>';
    	   $.post(path+"/std_plan/deletedo.jsp",
    	           			    {
    	           			      id:id,
    	           			      planpath:planpath,
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
