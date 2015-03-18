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
<script language=
                "javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/MyDatePicker/WdatePicker.js">  </script>
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
		document.all("form1").submit();
	//}
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
}</script>
<%
	//System.out.println(bm);
	         Calendar c = Calendar.getInstance();
   		 String year = "" + c.get(c.YEAR);
		 String month = "" + (c.get(c.MONTH) + 1);
		 String day = "" + c.get(c.DATE);
		 String date="";
		UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
		String staffcode=UserInfo.getStaffcode();
		StaffInfo staffinfo=new StaffInfo(staffcode);
		String staffname=staffinfo.getName();
		OrgPosition orgPosition = new OrgPosition();
		DataTable dTable = orgPosition.getOrgPositionCode(staffcode);//返回该员工对应的机构编码和岗位编码（这个会返回两条及以上的记录）
		String orgcode = dTable.get(0).getString("orgcode");
		Org org=new Org(orgcode);
		String orgname=org.getName();
		//////////////////////////////////////////////////////
		String taskId=request.getParameter("id");
	    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
		TaskService ts = processEngine
				.getTaskService();
		String applyid="";
		Object applyobject=ts.getVariable(taskId, "applyid");
		if(applyobject==null){
			SequenceUtil seq=new SequenceUtil();
			applyid=String.valueOf(seq.getSequence("标准类"));
			date=year+"-"+month+"-"+day;
		}else{
			applyid=applyobject.toString();
			DocApplyPerson person=new DocApplyPerson(Integer.parseInt(applyid));
			date=person.getApplydate();
		}
%>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form class="mainbody" name="form1" id="form1" method="post" action="../servlet/PageHandler">
       <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F1()" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" >提交[F1]</a>　<a href="deleteinstance.jsp?id=<%=taskId %>" class="easyui-linkbutton"
				        data-options="iconCls:'icon-remove',plain:true" >删除实例</a>　</td>
       </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center">
    <table width="100%" border="0" cellspacing="3" cellpadding="3" class="table_list1">
      <tr>
        <td width="30%" align="center">申 请 人:</td>
        <td width="70%" align="left"><input name="applyperson" type="text" class="input1" id="applyperson" onKeyDown="EnterKeyDo('')" value="<%=staffname%>"   size="30" maxlength="30"  readonly="readonly"><input type="hidden" name="applyid" value="<%=applyid%>" id="applyid"></td>
      </tr>
		 <tr>
          <td width="30%" align="center"> 申请时间:</td>
           <td width="70%" align="left"><input name="applydate" type="text" class="Wdate" id="applydate" onFocus="new WdatePicker(this,null,false,'whyGreen')"   value="<%=date %>" size="30" maxlength="30" readonly="readonly"></td>
        </tr>
                <tr>
          <td width="30%" align="center"> 申请部门:</td>
		  <td width="70%" align="left"><input type="text" name="applyapart" value="<%=orgname %>" id="applyapart" size="30" maxlength="60"></td>
		  </tr>
		  		  <tr>
		   <td width="30%" align="center"> 新建标准:</td>
		  <td width="70%" align="left">
		  <label>
		    <textarea name="newstd" id="newstd"  style="width:200px;height:50px"></textarea>
		   </label></td>
        </tr>
        <tr>
		   <td width="30%" align="center"> 修订标准:</td>
		  <td width="70%" align="left">
		  <label>
		    <textarea name="modstd" id="modstd"  style="width:200px;height:50px"></textarea>
		   </label></td>
        </tr>
		  <tr>
		   <td width="30%" align="center"> 废除标准:</td>
		  <td width="70%" align="left">
		  <label>
		    <textarea name="delstd" id="delstd"  style="width:200px;height:100px"></textarea>
		   </label></td>
        </tr>
 		  <tr>
		   <td width="30%" align="center"> 申请理由:</td>
		  <td width="70%" align="left">
		  <label>
		    <textarea name="applyreason" id="applyreason"  style="width:200px;height:150px"></textarea>
		   </label></td>
        </tr>
<!--          <tr> -->
<!--          <td width="30%" align="center"> 申请表:</td> -->
<!-- 		  <td width="70%" align="left"> -->
<!-- 				 <input type="button" name="button0" value="查看申请表" onClick="appytablebutton()" > -->
<!-- 		 </td> -->
<!--         </tr> -->
      <tr>
        <td><input name="taskId" type="hidden" id="taskId" value="<%=taskId %>"></td>
        <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode%>">
        <input type='hidden' name='applystaffcode' id='applystaffcode'  value="<%=staffcode %>">
          <input name="act" type="hidden" id="act" value="add">
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