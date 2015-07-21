<%@ page language="java" contentType="text/html; charset=GB2312"%>
<%@ page import="com.performance.MenuHelper, com.entity.system.UserInfo"%>

<div id="nav" style="display:none;" class="easyui-accordion" data-options="fit:true,border:false">
<%
	UserInfo user = (UserInfo)request.getSession().getAttribute("UserInfo");
	if(request.getParameter("rolecode") != null){
		user.setDefaultid(request.getParameter("rolecode"));
	}
	
	out.write(MenuHelper.getSystemMenu(user));
// 	out.write(	"<script type=\"text/javascript\">" +
// 				"function initLeftMenu() {" +
// 				"	$(\"#nav\").show();"  +
// 				"	$('.easyui-accordion').accordion('resize');"+
// 				"	$('.easyui-accordion li div').click(function() {"+
// 				"		$('.easyui-accordion li div').removeClass('selected');" +
// 				"		$(this).parent().addClass('selected');" +
// 				"	}).hover(function() {"+
// 				"		$(this).parent().addClass('hover');"+
// 				"	}, function() {"+
// 				"		$(this).parent().removeClass('hover');"+
// 				"	});"+
// 				"	$('#nav .easyui-tree').tree({"+
// 				"		onClick : function(node) {openNode(node);}"+
// 				"	});"+
// 				"}"+
// 				"setTimeout(initLeftMenu, 1000);"+
// 				"</script>"
// 			);
	
	out.write(	"<script type=\"text/javascript\">" +
					"$(function(){setTimeout(InitLeftMenu, 1000);});"+
				"</script>"
			);
%>
</div>