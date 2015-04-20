<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.common.Format"%>
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
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
String year=request.getParameter("year");
String start_month=request.getParameter("start_month");
String end_month=request.getParameter("end_month");
String dataurl="meritjson.jsp?class=individual&year="+year+"&start_month="+start_month+"&end_month="+end_month;
%>
<body>
   <div style="width: 100%">
   <a id="btn_detail" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true">��ϸ</a>
    <table id="dg" class="easyui-datagrid"
    data-options="url:'<%=dataurl %>',fitColumns:true,singleSelect:true,collapsible:true">
    <thead>
    <tr>
     <!--    <th data-options="field:'company',width:100">��˾</th>
	    <th data-options="field:'depart',width:100">����</th>
	    <th data-options="field:'stafforg',width:100">Ƭ��</th>
	    <th data-options="field:'position',width:100">��λ</th>
	    <th data-options="field:'name',width:100">����</th> -->
	    <th data-options="field:'mon',width:100">�·�</th>
	    <th data-options="field:'keyindexscore',width:100">�ؼ���Ч�÷�</th>
	    <th data-options="field:'baseindexscore',width:100">��������/�����÷�</th>
	    <th data-options="field:'commindexscore',width:100">ͨ��ָ��</th>
	    <th data-options="field:'companymerit',width:100">��˾��Ч</th>
	    <th data-options="field:'departmerit',width:100">���ż�Ч</th>
	    <th data-options="field:'othermerit',width:100">����</th>
	    <th data-options="field:'addscore',width:100">ֱ�ӼӼ���</th>
	    <th data-options="field:'staffallmerit',width:100">�ۺϵ÷�</th>
    </tr>
    </thead>
    </table>
   
    </div>
     <div align="right">
   <input id="btnPrint" type="button" value="��ӡ" onclick="javascript:window.print();"/>
  </div>
 <script type="text/javascript">
    $("#btn_detail").click(function(){
		var row = $('#dg').datagrid('getSelected');
		createwindow("��ϸ","meritquery/meritdetaillist.jsp?staffcode="+"<%=u.getStaffcode()%>"+"&relateyear="+"<%=year%>"+"&periodcode="+row.mon,700,500);
		    });
	function createwindow(title, url, width, height) {
		$.dialog({
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				return true;
			},
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});}
 </script>
</body>
</html>
