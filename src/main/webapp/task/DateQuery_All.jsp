<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
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
var orgcode="";
function query()
{
    var date = document.getElementById("date").value;
 	var newmemberurl='TaskAllDate.jsp?date='+date+'&orgcode='+orgcode ;
 	//alert("date="+date+"orgcode"+orgcode);
	window.open(newmemberurl,"taskalllist");
}

function select()
{
   var newtreeurl='unitcheckbox_tree.jsp';
	var str=showModalDialog(newtreeurl);
	if(str==null||str=="")
		return;
	var strs= new Array(); //定义一数组 
	
	strs=str.split(";"); //字符分割 
	/*if(strs==null||strs=="")
		return;*/
	document.getElementById("orgname").value=strs[0];
	orgcode = strs[1];
	//document.getElementById("BASE_ORG.BLONGADMINORGCODE").value=strs[1];
}


</script>

<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
 

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">年月：</div></td>
        <td width="30%">
        
        <input type="text" id="date" onFocus="new WdatePicker(this,'%Y-%M',false,'whyGreen')"/>
        
        <!-- 
        <input name="cxmc" type="text" class="input1" id="cxmc" onKeyDown="EnterKeyDo('')" size="50" maxlength="100">
         -->
        </td>
        
        <td width="20%"><div align="right">部门：</div></td>
        <td width="30%">
        
        <input type="text" id="orgname" name="orgname">
        <a href="#" onClick=select() class="button4">选择</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      
        <a href="#" onClick=query() class="button4">查询</a>
        
        <!-- 
        <input name="cxmc" type="text" class="input1" id="cxmc" onKeyDown="EnterKeyDo('')" size="50" maxlength="100">
         -->
        </td>
      </tr>
     
	
    
         <input type="hidden" name="entity" id="entity" value="MONTHTASK"/>
          <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
            <input name="act" type="hidden" id="act" value="add">
          
            
            
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

