<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.common.*,com.entity.system.*,com.dao.system.*" errorPage="" %>
<%
request.setCharacterEncoding("gb2312");
String pageurl=Format.NullToBlank(request.getParameter("pageurl"));
String pagetarget=Format.NullToBlank(request.getParameter("pagetarget"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB18030">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="../images/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/ztree/jquery.ztree.core-3.5.js"></script>
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
		<%
			out.print(menuTree.getTreeNew(pageurl,pagetarget));
		%>
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		//-->
	</SCRIPT>
<TITLE>这是一颗树</TITLE>
</HEAD>
<BODY bgcolor="#DBECFE" leftmargin="0" rightmargin="0" topmargin="0" bottommargin="0">
<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
</div>
</BODY>
</HTML>
