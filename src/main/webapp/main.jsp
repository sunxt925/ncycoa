<%@page import="com.entity.system.StaffInfo"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page contentType="text/html; charset=gb2312" language="java" errorPage=""%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
#nav .easyui-tree li a{ color:Black; text-decoration:none;}
#nav .easyui-tree li a:hover{ color:blue; text-decoration:none;}
.footer{text-align:center;color:#15428B; margin:0px; padding:0px;line-height:23px; font-weight:bold;}
.head a{color:White;text-decoration:none;}
#nav .easyui-tree {list-style-type:none;margin:0px; padding:0px;}
#nav .easyui-tree li{ padding:0px;}
#nav .easyui-tree li div{margin:2px 0px;height:24px;padding-left:10px;padding-top:2px;}
#nav .easyui-tree li div:hover{border:1px dashed #99BBE8; background:#E0ECFF;cursor:pointer;}
#nav .easyui-tree li div:hover a{color:#416AA3;text-decoration: none}
#nav .easyui-tree li div.selected{border:1px solid #99BBE8; background:#E0ECFF;cursor:default;}
#nav .easyui-tree li div.selected a{color:#416AA3;text-decoration: none; font-weight:bold;}
.icon{width:18px; line-height:18px; display:inline-block;}
.deafult{background:url('../images/folder.png') no-repeat;}
.easyui-tabs .panel-body{overflow:hidden;}
</style>

<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
    UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    StaffInfo staff=new StaffInfo(u.getStaffcode());
 %>
<body class="easyui-layout" style="overflow-y: hidden">
<!-- 顶部-->
<div data-options="region:'north',border:false" style="background: url(images/top_bg.jpg);height:70px;overflow:hidden;">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" style="vertical-align: text-bottom"><img src="images/logo.jpg"></td>
		<td align="right" valign="top" nowrap>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr style="height: 25px;" align="right">
				<td style="" colspan="2">
				<div style="float: right;">
				<div style="float: left; line-height: 25px; margin-left: 70px;"><span style="color: #386780">当前用户:</span>
				<span style="color: #FFFFFF">${sessionScope.UserInfo.username }<%=staff.getName()%></span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span style="color: #386780">当前角色:</span>
				<input id="curRole" class="easyui-combobox" style="width:90px;height:25px;" data-options="valueField:'rolecode',textField:'rolename',data:${sessionScope.UserInfo.roleJson},onSelect:onRoleChanged" />
				<script type="text/javascript">
				function onRoleChanged(record){
					$('#menu').panel('refresh','main_menu.jsp?rolecode=' + record.rolecode);		
				}
				function logout(){
			       $.dialog.confirm('确认退出吗',function(){
			           window.open("logindo.jsp?act=logout","_self");
	               });     
				}
				function userinfo(){
	                createwindow("个人信息", "userinfo.jsp");
				}
				function modpass(){
				    createwindow("修改密码", "passmod.jsp");
				}
				
				function createwindow(title, url,width,height) {
					width =  width ? width : 800;
					height = height ? height : 600;
					if(width=="100%" || height=="100%"){
						width = document.body.offsetWidth;
						height =document.body.offsetHeight-100;
					}
					
					if(typeof(windowapi) == 'undefined'){
						$.dialog({
							content: 'url:' + url,
							lock : true,
							width:width,
							height:height,
							title:title,
							opacity : 0.3,
							cache:false,
						    ok: function(){
						    	iframe = this.iframe.contentWindow;
								if(!saveObj()){
									return false;
								}
								return true;
						    },
						    cancelVal: '关闭',
						    cancel: true /*为true等价于function(){}*/
						});
					}else{
						W.$.dialog({
							content: 'url:'+url,
							lock : true,
							width:width,
							height:height,
							parent:windowapi,
							title:title,
							opacity : 0.3,
							cache:false,
						    ok: function(){
						    	iframe = this.iframe.contentWindow;
								if(!saveObj()){
									return false;
								}
								return true;
						    },
						    cancelVal: '关闭',
						    cancel: true /*为true等价于function(){}*/
						});
					}
					
				}
				</script>
				</div>
				<div style="float: left; margin-left: 18px;">
				<div style="right: 0px; bottom: 0px;">
				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-comturn" style="color: #FFFFFF">控制面板</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-exit" style="color: #FFFFFF">注销</a>
				</div>
				<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
					<div onclick="userinfo()">个人信息</div>
					<div class="menu-sep"></div>
					<div onclick="modpass()">修改密码</div>
				</div>
				<div id="layout_north_zxMenu" style="width: 100px; display: none;">
					<div onclick="logout()">退出系统</div>
				</div>	
				</div>
				</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>
<!-- 左侧-->
<script type="text/javascript" src="jscomponent/tools/leftmenu.js"></script>
<div id="menu" data-options="region:'west',split:true,href:'main_menu.jsp'" title="导航菜单" style="width: 280px;"></div>
<!-- 中间-->
<div id="mainPanle" data-options="region:'center'" style="overflow-y: hidden;">
<div id="maintabs" class="easyui-tabs" data-options="fit:true,border:false"></div>
</div>
<!-- 底部 -->
<div data-options="region:'south',border:false" style="height: 25px; overflow: hidden;"></div>
<!-- 菜单 -->
<div id="mm" class="easyui-menu" style="width: 150px;">
<div id="mm-tabupdate">刷新</div>
<div id="mm-tabclose">关闭</div>
<div id="mm-tabcloseall">全部关闭</div>
<div id="mm-tabcloseother">除此之外全部关闭</div>
<div class="menu-sep"></div>
<div id="mm-tabcloseright">当前页右侧全部关闭</div>
<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
</div>

</body>
</html>
