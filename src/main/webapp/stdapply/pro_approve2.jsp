<%@ page contentType="text/html; charset=gb2312" language="java" import="java.util.*,java.sql.*,com.db.*,com.common.*,com.entity.stdapply.*,com.entity.system.*,com.workflow.serviceimpl.*" errorPage="" %>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.activiti.engine.*"%>

<%@page import="org.activiti.engine.task.Task"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String bm=Format.NullToBlank(request.getParameter("unitccm"));
if (bm.equals("")) bm="NC";
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
String sugstaffcode=UserInfo.getStaffcode();
		String taskId=request.getParameter("id");
	    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
		TaskService taskService = processEngine
				.getTaskService();
		String ApplyId=taskService.getVariable(taskId, "applyid").toString();
		int applyid=Integer.parseInt(ApplyId.toString());
		DocApplyPerson applyperson=new DocApplyPerson(applyid);
	String staffcode=applyperson.getApplystaffcode();
	String staffname=applyperson.getApplyperson();
	String applyapart=applyperson.getApplyapart();
	String applydate=(applyperson.getApplydate()).substring(0,10);
	String applyreason=applyperson.getApplyreason();
%>
<script language=
                "javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/MyDatePicker/WdatePicker.js">  </script>
                <script language="javascript" src="<%=request.getContextPath()%>/js/public/select.js"></script> 

                 <link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/tools/stdapplyoutwindow.js"></script>
<script language="javascript">
function F1()
{
	//if (CkEmptyStr(document.all("DocNo"),"层次码不能为空！"))
	//{
		//alert (document.all("act"));
		document.form1.result.value="1";
		document.all("form1").submit();
	//}
}
function F2()
{

		document.form1.result.value="2";
		document.all("form1").submit();
}
function appytablebutton()
{
var applyid=document.getElementById("applyid").value;
var applyorg=document.getElementById("applyapart").value;
var applydate=document.getElementById("applydate").value;
var applyreason=document.getElementById("applyreason").value;
var url='/ncycoa/stdapply/applytable2.jsp?applyid='+applyid+'&applyorg='+applyorg+'&applydate='+applydate+'&applyreason='+applyreason;
createwindowNoButton('企业标准修订申请表',url,'1000px','500px');
//window.open(url);
}
</script>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form class="mainbody" name="form1" id="form1" method="post" action="../servlet/PageHandler">
       <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" 
				        onclick="F1()">提交评审意见</a></td>
       </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center">
    <table width="100%" border="0" cellspacing="3" cellpadding="3" class="table_list1">
      <tr>
        <td width="30%"  align="center">申 请 人:</td>
        <td width="70%" align="left"><input name="applyperson" type="text" class="input1" id="applyperson" onKeyDown="EnterKeyDo('')" value="<%=staffname%>"   size="30" maxlength="30"  readonly="readonly"><input type="hidden" name="applyid" value="<%=applyid%>" id="applyid"></td>
      </tr>
		 <tr>
          <td width="30%"  align="center"> 申请时间:</td>
           <td width="70%" align="left"><input name="applydate" type="text" class="Wdate" id="applydate" onFocus="new WdatePicker(this,null,false,'whyGreen')"   value="<%=applydate %>" size="30" maxlength="30" readonly="readonly"></td>
        </tr>
                <tr>
          <td width="30%" align="center"> 申请部门:</td>
		  <td width="70%" align="left"><input type="text" name="applyapart" value="<%=applyapart %>" id="applyapart" size="30" maxlength="60" readonly="readonly"></td>
		  </tr>
		  		  <tr>
		   <td width="30%" align="center"> 新建标准:</td>
		  <td width="70%" align="left">
		  <label>
		    <textarea name="newstd" id="newstd" style="width:400px;height:100px" ><%=taskService.getVariable(taskId, "newstd") %> </textarea>
		   </label></td>
        </tr>
        <tr>
		   <td width="30%" align="center"> 修订标准:</td>
		  <td width="70% align="left"">
		  <label>
		    <textarea name="modstd" id="modstd" style="width:400px;height:100px" readonly="readonly"><%=taskService.getVariable(taskId, "modstd") %></textarea>
		   </label></td>
        </tr>
		  <tr>
		   <td width="30%" align="center"> 废除标准:</td>
		  <td width="70%" align="left">
		  <label>
		    <textarea name="delstd" id="delstd" style="width:400px;height:100px" readonly="readonly"><%=taskService.getVariable(taskId, "delstd") %></textarea>
		   </label></td>
        </tr>
 		  <tr>
		   <td width="30%" align="center"> 申请理由:</td>
		  <td width="70%" align="left">
		  <label>
		    <textarea name="applyreason" id="applyreason" style="width:400px;height:100px" readonly="readonly"><%=applyreason %></textarea>
		   </label></td>
        </tr>
         <tr>
         <td width="30%" align="center"> 申 请 表:</td>
		  <td width="70%" align="left">
				 <input type="button" name="button0" value="查看申请表" onClick="appytablebutton()" >
		 </td>
        </tr>
                <tr>
        <td width="30%" align="center"> 审核意见:</td>
		  <td width="70%" align="left">
		    <label>
		    <textarea name="suggest" id="suggest"   style="width:400px;height:100px"></textarea>
		    </label></td>
        </tr> 
      <tr>
        <td><input name="taskId" type="hidden" id="taskId" value="<%=taskId %>"></td>
        <input type='hidden' name='applystaffcode' id='applystaffcode'  value="<%=staffcode %>">
        <input type='hidden' name='sugstaffcode'  value="<%=sugstaffcode %>">
        <input name="result" type="hidden" id="result" value="">
          <input name="act" type="hidden" id="act" value="appro">
		  <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          <input name="action_class" type="hidden" id="action_class" value="com.action.stdapply.StdApplyAction"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</body>
</html>