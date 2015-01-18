<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<%@page import="com.business.BuyGoodsItem"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<%
String buyno=Format.NullToBlank(request.getParameter("buyno"));

   BuyGoodsItem buyGoodsItem=new BuyGoodsItem(buyno); 
    int summitflag=buyGoodsItem.getSummitFlag();
   ComponentUtil cu=new ComponentUtil();
   CodeDictionary cd=new CodeDictionary();
 %>
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

	 if(sumbit_check())
	 {
	
	var goodnum=document.getElementById("COM_BUYGOODSITEM.GOODSNUMBER").value;
	var goodprice=document.getElementById("COM_BUYGOODSITEM.GOODSPRICE").value;
	if(goodnum>0&&goodprice>0){
	document.getElementById("COM_BUYGOODSITEM.TOTALCOST").value=goodnum*goodprice;
	 document.all("Submit").click();
     
		window.returnValue="refresh";
	}
	else
	{
	  alert("单价和采购数量不能为空！");
	}
	 }
	
	
}
</script>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
     <%if(summitflag==0){ %>
      <tr>
        <td width="20%"><div align="right">项目名称：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","PROJECTNAME",buyGoodsItem.getProjectName(),"readonly")); %>
      
        </td>
      </tr>
      <tr>
      <td><div align="right">品名：</div></td>
        <td>
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSNAME",buyGoodsItem.getGoodsName(),"readonly")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">规格型号：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSSTYLE",buyGoodsItem.getGoodsStyle())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">计量单位：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSUNIT",buyGoodsItem.getGoodsUnit())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">采购说明：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","BUYGOODSDESC",buyGoodsItem.getBuyGoodsDesc())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">单价：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSPRICE",String.valueOf(buyGoodsItem.getGoodsPrice())));
         %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">采购数量：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSNUMBER",String.valueOf(buyGoodsItem.getGoodsNumber()))); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">预算金额：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","TOTALCOST",String.valueOf(buyGoodsItem.getTotalCost()),"readonly")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">需求时间：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","NEEDMONTH",Format.dateToStr(buyGoodsItem.getNeedMonth()))); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">采购模式：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","BUYMODE",buyGoodsItem.getBuyMode())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">申请部门：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","BUYORGCODE",buyGoodsItem.getBuyOrgCode())); %>
        </td>
      </tr>
     <tr>
        <td width="20%"><div align="right">经办人：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","HANDLER",buyGoodsItem.getHandler())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">填写时间：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","INPUTDATE",Format.dateToStr(buyGoodsItem.getInputDate())));
       // System.out.println(cu.print("COM_BUYGOODSITEM","INPUTDATE"));
         %>
          </td>
      </tr>
      <%}else{ %>
      <tr>
      <td>
          采购已经上报，无法修改
      </td>
      
      </tr>
      <%} %>
       <tr>
       <input name="entity" id="entity" type="hidden" value="COM_BUYGOODSITEM"/>
        <input name="act" type="hidden" id="act" value="modify">
        <input name="sequence" id="sequence" type="hidden" value="BUYNO"/>
        <input name="COM_BUYGOODSITEM.BUYNO" id="COM_BUYGOODSITEM.BUYNO" type="hidden" value="<%=buyno%>"/>
       <input name="old_BUYNO" id="old_BUYNO" type="hidden" value="<%=buyno%>"/>
         <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
            <input name="action_class" type="hidden" id="action_class" value="com.action.buygoods.BuyGoodsInAction"></td>
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
</BODY>
</HTML>
<%
out.print(cu.getCheckstr());
%>