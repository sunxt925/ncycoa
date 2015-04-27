<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<base target=_self>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript" src="../../js/public/check.js"></script>
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
	if (sumbit_check())
	{
		//alert ("aaa");
		document.all("Submit").click();
		//window.close();
	}
}
</script>

<% ComponentUtil cu=new ComponentUtil(); %>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  
     
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a></td>-->
<!--  </tr>-->

<table cellpadding="5"  width="100%" align="left" >		
 <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
			<tr>
				<td><span>岗位代码：</span></td>
				<td>
					<%out.print(cu.print("BASE_POSITION","POSITIONCODE")); %>
				</td>
			</tr>
			<tr>
				<td><span>岗位名称：</span></td>
				<td>
					<%out.print(cu.print("BASE_POSITION","POSITIONNAME")); %>
				</td>
			</tr>
			 
			<tr>
				<td><span>岗位说明：</span></td>
				<td>
					<%out.print(cu.print("BASE_POSITION","POSITIONDESC")); %>
				</td>
			</tr>			
</table>
  <tr>
        <input name="act" type="hidden" id="act" value="add">
        <input type="hidden" id="entity" name="entity" value="BASE_POSITION"/>
    
    <input name="action_class" type="hidden" id="action_class" value="com.action.query.QueryAction"/>
        <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          <!--  
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemPositionAction">--></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
<% out.print(cu.getCheckstr());%>