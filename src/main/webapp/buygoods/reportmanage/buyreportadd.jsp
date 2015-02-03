<%@page import="com.common.CodeDictionary"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.common.Format"%>
<%@page import="com.common.ComponentUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script language="javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=path%>/js/public/check.js"></script>

<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

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

UserInfo user =(UserInfo)request.getSession().getAttribute("UserInfo");
ComponentUtil cu = new ComponentUtil();
CodeDictionary cd= new CodeDictionary();
%>
<body>
 <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
		
		<table cellpadding="5"  width="100%" align="left" >
			
			<tr>
				<td><span>项目代码：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","PROJECTCODE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>项目名称：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","PROJECTNAME"));
					%>
					<a id="btn_select" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
				</td>
			</tr>
			<tr>
				<td><span>实施方式：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","DEALMODE"));
					%>
					
				</td>
			</tr>
			<tr>
				<td><span>审议重点：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","CHECKCONTENTS"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>初审部门：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITORG"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>初审意见：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITOPINION"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>摘要：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITSUMMARY"));
					%>
				</td>
			</tr>
			
		</table>
		<input name="btn_ok" id="btn_ok" onclick="ret()" style="display: none">
		<input name="entity" id="entity" type="hidden" value="COM_BUYREPORTITEM"/>
        <input name="act" type="hidden" id="act" value="add">
        <input type="submit" name="Submit" value="提交" style="display:none">
        <input type="reset" name="reset" value="重置" style="display:none">
        <input type="hidden" name="COM_BUYREPORTITEM.SUMMITFLAG" id="COM_BUYREPORTITEM.SUMMITFLAG" value="0"/>
        <input name="action_class" type="hidden" id="action_class" value="com.action.buygoods.BuyReportItemAction"></td>
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
	$("#btn_select").click(function(){
		var path="<%=path%>";
    	var u=path+"/buygoods/buygoodsin/goodsclasscheckbox_tree.jsp";
    	createwindow('选择对象',u,500,600,returnValue);
	    });
	function createwindow(title, url, width, height) {
		var api = frameElement.api, W = api.opener;
		W.$.dialog({
			id:'CLHG1976D',
			data:returnValue,
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return true;
			}
		});
	
   }
   function returnValue(data){
       var str=data.code;
       var strs= new Array(); //定义一数组 
   	   strs=str.split(";"); //字符分割 
   	   document.getElementById("COM_BUYREPORTITEM.PROJECTCODE").value=strs[1];
	   document.getElementById("COM_BUYREPORTITEM.PROJECTNAME").value=strs[0];
   }
   function createalert(title){
	var api = frameElement.api, W = api.opener;
   	W.$.dialog({
   		id:'BLHG1976D',
   	    content: title,
   	    title :'提示',
   	    ok: function(){
   	    	
   	        this.title('提示').time(0.3);
   	        return false;
   	    },
   	    cancelVal: '关闭',
   	    cancel: true /*为true等价于function(){}*/
   	});
   }
	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>