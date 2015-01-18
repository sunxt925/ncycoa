<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target=_self>

<script language="javascript" type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript" src="../../js/public/check.js"></script>
<script type="text/javascript" src="../../js/public/searchvalue.js"></script>
<script language="javascript" src="../../js/public/Calendar1.js"></script>
<%
String outno=request.getParameter("outno");
Goodsoutstoreitem gisi=new Goodsoutstoreitem(outno);//本次出库物品详细信息
GoodsStoreInfo gsi=new GoodsStoreInfo(gisi.getGOODSCODE());
//System.out.println(StoreEventNo);
	//Org org=new Org(request.getParameter("unitccm"));
int goodsnumber=Integer.parseInt(gsi.getAvailableNumber())+Integer.parseInt(gisi.getGOODSNUMBER());
	ComponentUtil cu=new ComponentUtil();
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
	
	//System.out.println(newcode);
	String username=orgmember.getStaffname();

 %>
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
function select()
{
	var newtreeurl='goodsclasscheckbox_tree.jsp';
	var str=showModalDialog(newtreeurl);
	if(str==null||str=="")
		return;
	var strs= new Array(); //定义一数组 
	
	strs=str.split(";"); //字符分割 
	/*if(strs==null||strs=="")
		return;*/
	document.getElementById("COM_OUTSTOREITEM.GOODSNAME").value=strs[0];
	document.getElementById("COM_OUTSTOREITEM.GOODSCODE").value=strs[1];
	//window.open (newtreeurl,"Sample","toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,scrollbars=yes, copyhistory=no,width=350,height=540,left=200,top=300");
}
function blured()
{
	
	var number=<%=goodsnumber%>;
	var numberinput=document.getElementById("COM_OUTSTOREITEM.GOODSNUMBER").value;
	if(parseInt(numberinput)>parseInt(number))
	{
		alert("库存为"+number+"请重新输入");
		return false;
	}
	else
		return true;
	
}
</script>

<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">

<tr>
<td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td><div align="right">物资品名：</div></td>
        <td>
        <input name="COM_OUTSTOREITEM.GOODSNAME" type="text" class="input1" id="COM_OUTSTOREITEM.GOODSNAME" onKeyDown="EnterKeyDo('')" value="<%=gisi.getGOODSNAME() %>" readonly size="48" maxlength="48">
        
        </td>
      </tr>
      <tr>
        <td><div align="right">物资品名代码：</div></td>
        <td>
        
        <input name="COM_OUTSTOREITEM.GOODSCODE" type="text" class="input1" id="COM_OUTSTOREITEM.GOODSCODE" onKeyDown="EnterKeyDo('')" value="<%=gisi.getGOODSCODE() %>" readonly size="48" maxlength="200">
        
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">说明：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","GOODSDESC",gisi.getGOODSDESC())); %>
        
        
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">规格型号：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","GOODSSTYLE",gisi.getGOODSSTYLE())); %>
        
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">数量：</div></td>
        <td width="80%">
        <input name="COM_OUTSTOREITEM.GOODSNUMBER" id="COM_OUTSTOREITEM.GOODSNUMBER" size="48" class="input1" type="text" value="<%=gisi.getGOODSNUMBER() %>" onfocus="cls()" onblur="res();blured()"/>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">计量单位：</div></td>
        <td width="80%">
        
        <%out.print(cu.print("COM_OUTSTOREITEM","MEASUREUNIT",gisi.getMEASUREUNIT())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">经办人：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","HANDLER",gisi.getHANDLER())); %>
        </td>
      </tr>
      <!--<tr>
        <td width="20%"><div align="right">出库日期：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","OUTDATE",gisi.getOUTDATE())); %>
        </td>
      </tr>
      --><tr>
        <td width="20%"><div align="right">验收部门：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","AUDITORGCODE",gisi.getAUDITORGCODE())); %>
        
        </td>
      </tr><!--
      <tr>
        <td width="20%"><div align="right">操作人员：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","AUDITORCODE",gisi.getAUDITORCODE())); %>
        </td>
      </tr>
      --><!--<tr>
        <td width="20%"><div align="right">录入日期：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","INPUTDATE",gisi.getINPUTDATE())); %>
        </td>
      </tr>
      --><tr>
        <td width="20%"><div align="right">领用部门：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","USINGORGCODE",gisi.getUSINGORGCODE())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">备注：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","MEMO",gisi.getMEMO())); %>
        
        </td>
      </tr>
      
      <tr>
      <td>
      <input type="hidden" id="entity" name="entity" value="COM_OUTSTOREITEM"/>
    
    
         <input type="submit" name="Submit" onclick="return blured()" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          </td>
      </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="modify"></div></td>
        
        <td><input name="old_OUTNO" type="hidden" id="old_OUTNO" value="<%=outno%>">
        <input name="COM_OUTSTOREITEM.OUTNO" type="hidden" id="COM_OUTSTOREITEM.OUTNO" value="<%=outno%>">
        <input name="COM_OUTSTOREITEM.OUTDATE" id="COM_OUTSTOREITEM.OUTDATE" type="hidden" value="<%=Format.getNowtime2()%>"/>
        <input name="COM_OUTSTOREITEM.INPUTDATE" id="COM_OUTSTOREITEM.INPUTDATE" type="hidden" value="<%=Format.getNowtime2()%>"/>
        <input name="COM_OUTSTOREITEM.AUDITORCODE" id="COM_OUTSTOREITEM.AUDITORCODE" type="hidden" value="<%=username%>"/>
        </td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformOUTAction"></td>
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