<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.task.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充市烟草局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<style type="text/css">
<!--
.STYLE1 {color: #ff0000}
-->
</style>
</HEAD>
<base target="_self">
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript" src="../js/public/check.js"></script>
<script language="javascript" src="../js/DatePicker/WdatePicker.js"></script>
<script language="javascript">
function F5()
{
	window.location.reload();
}
function F3()
{
	document.all("reset").click();
}
function F8()
{
//alert(sumbit_check());
	if(sumbit_check()){
	
	document.all("Submit").click();
	window.returnValue="refresh";
	
	}
	else
	{
	alert(false);
	}
	
	
}

function uploadfile(taskno)
{
 	  var newurl='../file_upload/local_up.jsp?taskno='+taskno;
  	  //window.open(newurl,"stdlist");
  	  window.showModalDialog(newurl,window,"dialogWidth=300px;dialogHeight=150px");
      window.location.reload(); 
}

</script>
<%
	request.setCharacterEncoding("gb2312");
	String taskno = request.getParameter("taskno");
	Role.sysmng.login.User u=(Role.sysmng.login.User)request.getSession().getAttribute("USER");
	String participantcode = u.getZgdm();
	ComponentUtil cu=new ComponentUtil();
	Participant pt = new Participant(taskno,participantcode);
%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
 
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
   
   
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">实际完成时间：</div></td>
        <td width="80%">
        
        <%out.print(cu.print("MONTHTASKPARTICIPANT","FINISHEDDATE",pt.getFinisheddate())); %>
        <!-- 
        <input name="cxmc" type="text" class="input1" id="cxmc" onKeyDown="EnterKeyDo('')" size="50" maxlength="100">
         -->
        </td>
      </tr>

   
   
      <tr>
        <td width="20%"><div align="right">工作完成情况：</div></td>
        <td width="80%">
        
        <%out.print(cu.print("MONTHTASKPARTICIPANT","FINISHEDDESC",pt.getFinisheddesc())); %>
        <!-- 
        <input name="cxmc" type="text" class="input1" id="cxmc" onKeyDown="EnterKeyDo('')" size="50" maxlength="100">
         -->
        </td>
      </tr>
      
      <tr>
        <td width="20%"><div align="right">文件上传：</div></td>
        <td width="80%">
        
        <a href="#" onClick='uploadfile("<%=taskno %>")' class="button4">上传文件</a> (如果没有文件不用上传)
        <!-- 
        <input name="cxmc" type="text" class="input1" id="cxmc" onKeyDown="EnterKeyDo('')" size="50" maxlength="100">
         -->
        </td>
      </tr>
     
	
     
    

   
         <input type="hidden" name="entity" id="entity" value="MONTHTASKPARTICIPANT"/>
          <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
             <input type="hidden" name="MONTHTASKPARTICIPANT.TASKNO" id="MONTHTASKPARTICIPANT.TASKNO" value=<%=pt.getTaskno() %>>
            <input type="hidden" name="old_TASKNO" id="old_TASKNO" value=<%=pt.getTaskno() %>>
             <input type="hidden" name="MONTHTASKPARTICIPANT.PARTICIPANTCODE" id="MONTHTASKPARTICIPANT.PARTICIPANTCODE" value=<%=pt.getParticipantcode() %>>
            <input type="hidden" name="old_PARTICIPANTCODE" id="old_TPARTICIPANTCODE" value=<%=pt.getParticipantcode() %>>
            <input name="act" type="hidden" id="act" value="modify">
          
            
            
            <input name="action_class" id="action_class" type="hidden" value="com.action.task.TaskAction"/>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
 
</form>
</table>
</BODY>
</HTML>

 <%out.print(cu.getCheckstr());%>
