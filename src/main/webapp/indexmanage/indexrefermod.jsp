<%@page import="com.entity.index.ReferObjectIndex"%>
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
String recno=request.getParameter("recno");
ReferObjectIndex ref=new ReferObjectIndex(recno);
 ComponentUtil cu=new ComponentUtil();
%>
<body>
	<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
		<div id="p" style="padding: 10px">
			<!-- <a id="btn_save" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">保存</a>  -->
				<a id="btn_reset"
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true">重置</a> <a
				id="btn_ref" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">刷新</a>
		</div>
		<div >
		<table cellpadding="5"  width="100%" align="left" >
			
			<tr>
				<td><span>使用者：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","OBJECTCODE",ref.getObjectcode(),"readonly")); 
					%>
				</td>
			</tr>
			<tr>
				<td><span>使用类别：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","OBJECTTYPE",ref.getObjecttype()));
					%>
				</td>
			</tr>
			 
			
			<tr>
				<td><span>指标编码：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","INDEXCODE",ref.getIndexcode(), "readonly"));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>参数编码：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","PARACODE",ref.getParacode(), "readonly"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>被引用者：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","REFOBJECTCODE",ref.getRefobjectcode())); 
					%>
					<a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
				</td>
			</tr>
			<tr>
				<td><span>被引用者类别：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","REFOBJECTTYPE",ref.getRefobjecttype()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>被引用指标编码：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","REFINDEXCODE",ref.getRefindexcode()));
					%>
					<a id="btn_selectindex" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
				</td>
			</tr>
			<tr>
				<td><span>引用模式：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","REFMODE",ref.getRefmode()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>备注：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFEROBJECTINDEX","MEMO",ref.getMemo()));
					%>
				</td>
			</tr>
		</table>
		
		</div>
		<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		<!-- 隐藏字段 -->
		<input name="entity" id="entity" type="hidden" value="TBM_REFEROBJECTINDEX"/>
        <input name="act" type="hidden" id="act" value="modify">
        <input name="TBM_REFEROBJECTINDEX.RECNO" type="hidden" id="TBM_REFEROBJECTINDEX.RECNO" value="<%=recno%>" />
        <input name="old_RECNO" type="hidden" id="old_RECNO" value="<%=recno%>" />
        <input type="submit" name="Submit" value="提交" style="display:none">
        <input type="reset" name="reset" value="重置" style="display:none">
        <input name="action_class" type="hidden" id="action_class" value="com.action.index.ReferObjectAction">
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
	    $("#btn_selectobject").click(function(){
	    	var u="indexmanage/selectstaff.jsp";
	    	createwindow('选择对象',u,800,600,returnobjValue);
    	    });
	    $("#btn_selectindex").click(function(){
	    	var rand=Math.floor(Math.random()*10000);
	    	var objectcode=document.getElementById("TBM_REFEROBJECTINDEX.REFOBJECTCODE").value;
	    	if(objectcode==""){
	    	    alert("请先选择引用者信息！！！");
	    	}else{
	    	    var u="indexmanage/selectrefindex.jsp?objectcode="+objectcode;
	    		
	    		createwindow('选择指标体系',u,500,400,returnindexValue);
	    	}
    	    });
	    function returnindexValue(data){
	    	document.getElementById("TBM_REFEROBJECTINDEX.REFINDEXCODE").value=data.code;
		}
	    function returnobjValue(data){
	    	var objectarray=data.code;
	    	var objectcode="<%=ref.getObjectcode()%>";
	    	var refobjectcode=objectarray[0].staffcode;
	    	if(refobjectcode==objectcode){
	    		alert("被引用者不能是自己");
	    	}else{
	    		document.getElementById("TBM_REFEROBJECTINDEX.REFOBJECTCODE").value=refobjectcode;
	    	}
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
					id:'CLHG1976D',
					data:func,
					content : 'url:' + url,
					lock : true,
					width : width,
					height : height,
					title : title,
					opacity : 0.3,
					parent:api,
					cache : false,
					ok : function() {
						$('#btn_ok', this.iframe.contentWindow.document).click();
						return true;
					},
					cancelVal : '关闭',
					cancel : true/* 为true等价于function(){} */
				});
			}
		}

	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>