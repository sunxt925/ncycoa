<%@ page language="java" contentType="text/html; charset=GB2312"%>
<%@ page import="com.performance.MenuHelper, com.entity.system.UserInfo"%>
<script type="text/javascript" src="jscomponent/tools/leftmenu.js"></script>

<div id="nav" style="display:none;" class="easyui-accordion" data-options="fit:true,border:false">
<%
	UserInfo user = (UserInfo)request.getSession().getAttribute("UserInfo");
	if(request.getParameter("rolecode") != null){
		user.setDefaultid(request.getParameter("rolecode"));
	}
	
	out.write(MenuHelper.getSystemMenu(user));
%>
</div>

