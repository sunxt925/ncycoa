<%@page import="com.business.BuyReportItem"%>
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
String reportno=request.getParameter("reportno");
BuyReportItem buyReportItem=new BuyReportItem(reportno);
ComponentUtil cu = new ComponentUtil();
CodeDictionary cd= new CodeDictionary();
%>
<body>
 <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
		
		<table cellpadding="5"  width="100%" align="left" >
	   <% if(buyReportItem.getFinalAuditFlag().equals("00")){ %>
       <% if(buyReportItem.getSecondAuditFlag().equals("21")){ %>
			<tr>
				<td><span>【初审意见】</span></td>
				<td>
					<%=buyReportItem.getFirstAuditOpinion()
					%>
				</td>
			</tr>
			<tr>
				<td><span>【二审意见】</span></td>
				<td>
					<%=buyReportItem.getSecondAudiOpiniont()
					%>
				</td>
			</tr>
			<tr>
				<td><span>审核意见：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","FINALAUDITOPINION",buyReportItem.getFinalAuditOpinion()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>审核结果：</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYREPORTITEM","FINALAUDITFLAG",buyReportItem.getFinalAuditFlag()));
					%>
					
				</td>
			</tr>
		  <%}else{%>
		  <tr>
				<td><span>无法审核</span></td>
		  </tr>
		  <%}}else{%>
		   <tr>
				<td><span>已审核</span></td>
		  </tr>
		   <%}%>
		</table>
		<input name="btn_ok" id="btn_ok" onclick="ret()" style="display:none">
		<input name="entity" id="entity" type="hidden" value="COM_BUYREPORTITEM"/>
        <input name="act" type="hidden" id="act" value="final_audit">
        <input name="COM_BUYREPORTITEM.FINALAUDITDATE" id="COM_BUYREPORTITEM.FINALAUDITDATE" type="hidden" value="<%=Format.getNowtime()%>"/>
        <input name="COM_BUYREPORTITEM.REPORTNO" id="COM_BUYREPORTITEM.REPORTNO" type="hidden" value="<%=reportno%>"/>
        <input name="old_REPORTNO" id="old_REPORTNO" type="hidden" value="<%=reportno%>"/>
        <input type="submit" name="Submit" value="提交" style="display:none">
        <input type="reset" name="reset" value="重置" style="display:none">
        <input name="action_class" type="hidden" id="action_class" value="com.action.buygoods.BuyReportAction"></td>
        </form>
		
	<script>
	  
	function ret(){
		var api = frameElement.api;
		var flag1="<%=buyReportItem.getFinalAuditFlag()%>"
		var flag2="<%=buyReportItem.getSecondAuditFlag()%>";
		if(flag1=="00"&&flag2=="21"){
			if(sumbit_check())
	   	    {
				document.all("Submit").click();
	   	    }
		}
		(api.data)({code:"refresh"});
	}
	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>