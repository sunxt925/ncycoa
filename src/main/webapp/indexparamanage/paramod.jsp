<%@page import="com.entity.index.ReferPara"%>
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
String paracode=request.getParameter("paracode");
ReferPara ref=new ReferPara(paracode);
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
				<td><span>参数编码：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFERPARA","PARACODE",paracode,"readonly")); 
					%>
				</td>
			</tr>
			<tr>
				<td><span>参数名称：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFERPARA","PARANAME",ref.getParaname()));
					%>
				</td>
			</tr>
			 
			<tr>
				<td><span>参数周期</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFERPARA","PARAPERIOD",ref.getParaperiod()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>缺省值：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFERPARA","DEFAULTVALUE",String.valueOf(ref.getDefaultvalue())));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>采集函数：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFERPARA","GETPARAVALUEFUNC",ref.getGetparavaluefunc()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>使用标志：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFERPARA","USINGFLAG",String.valueOf(ref.getUsingflag())));
					%>
				</td>
			</tr>
			<tr>
				<td><span>参数排序：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFERPARA","PARAORDER",String.valueOf(ref.getParaorder())));
					%>
				</td>
			</tr>
			<tr>
				<td><span>备注：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_REFERPARA","MEMO",ref.getMemo()));
					%>
				</td>
			</tr>
			
		</table>
		
		</div>
		<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		<!-- 隐藏字段 -->
		 <input name="entity" id="entity" type="hidden" value="TBM_REFERPARA"/>
         <input name="old_PARACODE" id="old_PARACODE" type="hidden" value="<%=paracode%>"/>
         <input name="act" type="hidden" id="act" value="modify">
         <input type="submit" name="Submit" value="提交" style="display:none">
         <input type="reset" name="reset" value="重置" style="display:none">
         <input name="action_class" type="hidden" id="action_class" value="com.action.index.ReferParaAction">
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
	   

	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>