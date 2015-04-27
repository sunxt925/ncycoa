<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
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
	if (CkEmptyStr(document.all("positionconfigcount"),"配置人数至少为0"))
	{
		//alert (document.all("act"));
		document.all("Submit").click();
	}
}
</script>
<%

	request.setCharacterEncoding("gb2312");
	String bm=Format.NullToBlank(request.getParameter("bm"));
	String positioncode=Format.NullToBlank(request.getParameter("positioncode"));
  	//Org mo=new Org(bm);
  	Org og=new Org();
  	OrgPosition orgposition=new OrgPosition(positioncode,bm);
  	
  	//Position position=new Position(positioncode);
  	//String trackName=og.getTrack(bm,"");
  	String trackName="";
%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">当前单位：<%=trackName %>岗位信息修改</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
      <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">岗位编码：</div></td>
        <td width="80%"><input name="PositionCode" type="text" class="input1" id="PositionCode" onKeyDown="EnterKeyDo('')" value="<%=orgposition.getPositioncode()%>" size="30" maxlength="30"  readonly="readonly"></td>
      </tr>
      <tr>
        <td><div align="right">岗位名称：</div></td>
        <td><input name="PositionName" type="text" class="input1" id="PositionName" onKeyDown="EnterKeyDo('')" value="<%=orgposition.getPositionname()%>" size="40" maxlength="40" readonly="readonly"></td>
      </tr>
      <tr>
        <td><div align="right">配置人数：</div></td>
        <td><input name="positionconfigcount" type="text" class="input1" id="positionconfigcount" onKeyDown="EnterKeyDo('')" value="<%=orgposition.getPositionconfigcount()%>" size="30" maxlength="200"></td>
      </tr>
        
      <tr>  
	   <td width="20%"><div align="right">备注：</div></td>
        <td width="80%"><input name="Memo" type="text" class="input1" id="Memo" onKeyDown="EnterKeyDo('')" value="<%=orgposition.getMemo()%>" size="30" maxlength="30"></td> 
        </tr>    
	  <tr>
      <td>
         <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          </td>
      </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="modify"></div></td>
        <input name="OrgCode" type="hidden" id="OrgCode" value="<%=bm%>">
        <input name="OrgName" type="hidden" id="OrgName" value="<%=orgposition.getOrgname()%>">
        <input name="orgpositionid" type="hidden" id="orgpositionid" value="<%=orgposition.getOrgpositionid()%>">
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitPositionAction"></td>
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