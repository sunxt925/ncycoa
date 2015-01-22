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
%>
<body>
 <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
		
		<table cellpadding="5"  width="100%" align="left" >
			
			<tr>
				<td><span>项目名称：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","PROJECTNAME"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>品名：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSNAME"));
					%>
					<a id="btn_selectgoods" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
				</td>
			</tr>
			<tr>
				<td><span>规格型号：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSSTYLE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>计量单位：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSUNIT"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>采购说明：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","BUYGOODSDESC"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>单价：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSPRICE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>采购数量：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSNUMBER"));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>需求时间：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","NEEDMONTH"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>采购模式：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","BUYMODE"));
					%>
				</td>
			</tr>
		</table>
			
		
		<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		<input name="entity" id="entity" type="hidden" value="COM_BUYGOODSITEM"/>
        <input name="act" type="hidden" id="act" value="add">
        <input name="COM_BUYGOODSITEM.SUMMITFLAG" id="COM_BUYGOODSITEM.SUMMITFLAG" type="hidden" value="0"/>
        <input name="COM_BUYGOODSITEM.INPUTDATE" id="COM_BUYGOODSITEM.INPUTDATE" type="hidden" value="<%=Format.getNowtime2()%>"/>
        <input name="COM_BUYGOODSITEM.HANDLER" id="COM_BUYGOODSITEM.HANDLER" type="hidden" value="<%=user.getStaffcode()%>"/>
        <input name="COM_BUYGOODSITEM.TOTALCOST" id="COM_BUYGOODSITEM.TOTALCOST" type="hidden" value=""/>
        <input type="hidden" name="COM_BUYGOODSITEM.GOODSCODE" id="COM_BUYGOODSITEM.GOODSCODE"/>
        <input type="hidden" name="COM_BUYGOODSITEM.PROJECTCODE" id="COM_BUYGOODSITEM.PROJECTCODE"/>
        <input type="submit" name="Submit" value="提交" style="display:none">
        <input type="reset" name="reset" value="重置" style="display:none">
        <input name="action_class" type="hidden" id="action_class" value="com.action.buygoods.BuyGoodsInAction">
	</form>
		
	<script>
	  
	function ret(){
		var api = frameElement.api;
		
				  
		if(sumbit_check())
   	    {
			var goodnum=document.getElementById("COM_BUYGOODSITEM.GOODSNUMBER").value;
			var goodprice=document.getElementById("COM_BUYGOODSITEM.GOODSPRICE").value;
			if(goodnum>0&&goodprice>0){
				document.getElementById("COM_BUYGOODSITEM.TOTALCOST").value=goodnum*goodprice;
				if(document.getElementById("COM_BUYGOODSITEM.NEEDMONTH").value!=""){
					if(document.getElementById("COM_BUYGOODSITEM.BUYMODE").value!=""){
						 document.all("Submit").click();
	 	                 (api.data)({code:"refresh"});
					}else{
						createalert("请选择采购模式！");
					}
		            
				}else{
				     createalert("日期不能为空！");
			    }
   	        }else{
   	    	     createalert("单价和采购数量不能为空！");
   	        }
   	    }
	}
	$("#btn_selectgoods").click(function(){
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
   	   document.getElementById("COM_BUYGOODSITEM.GOODSNAME").value=strs[0];
   	   document.getElementById("COM_BUYGOODSITEM.GOODSCODE").value=strs[1];
   	   document.getElementById("COM_BUYGOODSITEM.PROJECTCODE").value=strs[2];
   	   document.getElementById("COM_BUYGOODSITEM.PROJECTNAME").value=strs[3];
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