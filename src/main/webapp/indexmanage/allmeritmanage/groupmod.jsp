<%@page import="com.entity.index.AllMeritGroup"%>
<%@page import="com.common.ComponentUtil"%>
<%@page import="com.common.CodeDictionary"%>
<%@page import="com.common.IndexCode"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script language="javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=path%>/js/public/check.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<style type="text/css">
    span{
        font-size: 12px;
    }
    table{
        border: 1px solid #f2f2f2;
    }
</style>
</head>
<%
String groupno=request.getParameter("groupno");
AllMeritGroup allMeritGroup=new AllMeritGroup(groupno);
ComponentUtil cu=new ComponentUtil();
%>
<body>
	<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
		<div id="p" style="padding: 10px">
			<!-- <a id="btn_save" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">����</a>  -->
				<a id="btn_reset"
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true">����</a> <a
				id="btn_ref" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
		</div>
		<div >
		<table cellpadding="5"  width="100%" align="left" >
			<tr>
				<td><span>���:</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUP","GROUPNO",allMeritGroup.getGroupNo(),"readonly")); 
					%>
				</td>
			</tr>
			<tr>
				<td><span>������</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUP","GROUPNAME",allMeritGroup.getGroupName()));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>��Ч�����б�</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUP","ALLMERITFUNC",allMeritGroup.getAllmeritfunc()));
					%>
					<a id="btn_addlist" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-add',plain:true">���</a>
				</td>
			</tr>
			<tr>
				<td><span>��ע��</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUP","MEMO",allMeritGroup.getMemo()));
					%>
				</td>
			</tr>
			
			
		</table>
		
		</div>
		<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		<!-- �����ֶ� -->
		<input name="entity" id="entity" type="hidden" value="TBM_ALLMERITGROUP"/>
        <input name="act" type="hidden" id="act" value="modify">
        <input type="submit" name="Submit" value="�ύ" style="display:none">
         <input type="reset" name="reset" value="����" style="display:none">
         <input name="old_GROUPNO" id="old_GROUPNO" type="hidden" value="<%=groupno%>"/>
         <input name="action_class" type="hidden" id="action_class" value="com.action.index.AllMeritGroupAction">
	</form>
	<script>
	 function ret(){
		   var api = frameElement.api;
	    
	    	if(sumbit_check())
	   	    {
	    		
	   	       document.all("Submit").click();
	   	      (api.data)({code:"refresh"});
	   	    }
	   }
	    $("#btn_reset").click(function(){
	    	document.all("reset").click();
    	    });
	   
	    $("#btn_ref").click(function(){
	    	window.location.reload();
    	    });
	    
	    function returnValue(data){
	    	document.getElementById("TBM_ALLMERITGROUP.ALLMERITFUNC").value=data.code;
		}
		function createwindow(title, url, width, height,func) {
			var api = frameElement.api, W = api.opener;
			width = width ? width : 700;
			height = height ? height : 400;
			if (width == "100%" || height == "100%") {
				width = document.body.offsetWidth;
				height = document.body.offsetHeight - 100;
			}
			if (typeof (windowapi) == 'undefined') {
				W.$.dialog({
					data:func,
					id:'CLHG1976D',
					content : 'url:' + url,
					lock : true,
					width : width,
					height : height,
					title : title,
					opacity : 0.3,
					cache : false,
					parent:api,
					ok : function() {
						$('#btn_ok', this.iframe.contentWindow.document).click();
						return true;
					},
					cancelVal : '�ر�',
					cancel : true/* Ϊtrue�ȼ���function(){} */
				});
			}
		}
	    
	    $("#btn_addlist").click(function(){
	    	var url="indexmanage/allmeritmanage/getallmeritlist.jsp";
	 	   	createwindow('�����б�',url,500,400,returnValue);
    	    });
	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>