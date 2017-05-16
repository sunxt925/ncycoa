<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>

<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<BODY class="mainbody" onLoad="this.focus()">
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="3%" class="main_table_topbg" height="31"><img
				src="../images/table_lt.jpg" width="22" height="31"></td>
			<td width="94%" valign="middle" class="main_table_topbg" height="31"></td>
			<td width="3%" align="right" class="main_table_topbg" height="31"><img
				src="../images/table_rt.jpg" width="22" height="31"></td>
		</tr>

		<tr>
			<td colspan="3" valign="top" class="main_table_centerbg"
				align="center">
				<table width="100%" height="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
					<td>
					  <span>员工姓名：</span>
					  <input type="hidden" name="staffcode" id="staffcode" value="0">
          <input  id="staffname" id="staffname" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:100px">
          <a id="select_obj" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">选择员工</a>
           <a id="btn_search" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true">搜索</a>
					</td>
					</tr>
					<tr>
						
						<td valign="top" height="100%"><iframe
								src="goodsconfirmStore_list.jsp" name="goodsStorelist"
								id="goodsStorelist" width="100%" height="100%"
								frameborder="0"></iframe></td>

					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="5" class="main_table_bottombg"><img
				src="../images/table_lb.jpg" width="10" height="5"></td>
			<td height="5" class="main_table_bottombg"></td>
			<td height="5" align="right" class="main_table_bottombg"><img
				src="../images/table_rb.jpg" width="10" height="5"></td>
		</tr>
	</table>
	<script language="javascript">
		function F5() {
			window.location.reload();
		}
		$("#btn_search").click(function(){
			 document.getElementById("goodsStorelist").contentWindow.location.href="goodsconfirmStore_list.jsp?staffcode="+$('#staffcode').val(); 
             
		});
		
		$("#select_obj").click(function(){
			
			createwindow('选择人员','indexmanage/selectstaff.jsp',500,500,returnobjValue );
		    });
		function returnobjValue(data){

			var array = data.code;
			var staffcodes=array[0].staffcode;
			var staffnames=array[0].staffname;
			
			$('#staffname').val(staffnames);
			$('#staffcode').val(staffcodes);
			
		}
		function createwindow(title, url, width, height,func) {
			
			$.dialog({
					id:'CLHG1976D',
					data:func,
					content : 'url:' + url,
					lock : true,
					width : width,
					height : height,
					title : title,
					zIndex :2000,
					opacity : 0.3,
					cache : false,
					ok : function() {
						$('#btn_ok', this.iframe.contentWindow.document).click();
						return true;
					},
					cancelVal : '关闭',
					cancel : true/* 为true等价于function(){} */
				});
		}   
	</script>
</BODY>
</HTML>