<%@page import="com.entity.index.Collectentity"%>
<%@page import="com.entity.index.AllMeritCollection"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.entity.index.MeritQueryHelper"%>
<%@page import="com.entity.system.Staff"%>
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
</head>
<%
%>
<body>
  <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="queryWithoutIndexRelation()">��ѯû�й���ָ��Ա��</a>
  <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="queryMutilIndexRelation()">��ѯ�������ָ��Ա��</a>
  <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="queryMutilAllMeritRelation()">��ѯ��������ۺϼ�Ч����Ա��</a>
				        
 <script type="text/javascript">
   function queryWithoutIndexRelation(){
	   createwindow('����ָ��Ա��','meritquery/queryconfigerrorlist.jsp',600,600);
   }
   function queryMutilIndexRelation(){
	   createwindow('�������ָ��Ա��','meritquery/queryconfigerrorlist2.jsp',600,600);
   }
   function queryMutilAllMeritRelation(){
	   createwindow('��������ۺϼ�Ч����Ա��','meritquery/queryconfigerrorlist3.jsp',600,600);
   }
   function createwindow(title, url, width, height) {
		
		$.dialog({
			id:'choose01',
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
	
}
 </script>
</body>
</html>
