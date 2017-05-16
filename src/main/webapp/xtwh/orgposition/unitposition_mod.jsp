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
  
 <table cellpadding="5"  width="100%" align="left" >
  <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
             <tr>
				<td><span>岗位编码：</span></td>
				<td>
					<input name="PositionCode" type="text" class="easyui-textbox" id="PositionCode" onKeyDown="EnterKeyDo('')" value="<%=orgposition.getPositioncode()%>" size="30" maxlength="30"  readonly="readonly">
				</td>
			</tr>
			 
			<tr>
				<td><span>岗位名称：</span></td>
				<td><input name="PositionName" type="text" class="easyui-textbox" id="PositionName" onKeyDown="EnterKeyDo('')" value="<%=orgposition.getPositionname()%>" size="40" maxlength="40" readonly="readonly"></td>
			</tr>
			<tr>
				<td><span>配置人数：</span></td>
				<td><input name="positionconfigcount" type="text" class="easyui-textbox" id="positionconfigcount" onKeyDown="EnterKeyDo('')" value="<%=orgposition.getPositionconfigcount()%>" size="30" maxlength="200"></td>
			</tr>
			<tr>
				<td><span>备注：</span></td>
				<td><input name="Memo" type="text" class="easyui-textbox" id="Memo" onKeyDown="EnterKeyDo('')" value="<%=orgposition.getMemo()%>" size="30" maxlength="30"></td>
			</tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table  width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1" style="display: none">    
	  <tr >
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
 
</form>
</table>
</BODY>
</HTML>