<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target=_self>

<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<script language="javascript" src="<%=path%>/js/public/key.js"></script>
<script language="javascript" src="<%=path%>/js/public/check.js"></script>
<script language="javascript" src="<%=path%>/js/public/Calendar1.js"></script>

<%
	Goods goods=new Goods(request.getParameter("goodscode"));
	String newcoding=AutoCoding.Coding(request.getParameter("goodscode"),"Com_GoodsClass","goodscode",2,".");
	String isparent="1";
	if(newcoding.length()==17)
		isparent="0";
	ComponentUtil cu=new ComponentUtil();

 %>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">物资编码：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_GOODSCLASS","GOODSCODE",newcoding,"readonly")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">物资名称：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_GOODSCLASS","GOODSNAME")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">说明：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_GOODSCLASS","GOODSDESC")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">归口部门：</div></td>
        <td width="80%">
        
        <%out.print(cu.print("COM_GOODSCLASS","AUDITORGCODE")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">上层物资编码：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_GOODSCLASS","PARENTGOODSCODE",request.getParameter("goodscode"),"readonly")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">有效标志：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_GOODSCLASS","VALIDFLAG","1"));  %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">备注：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_GOODSCLASS","MEMO")); %>
       </td>
      </tr>
      <tr>
      <td>
      <input type="hidden" id="entity" name="entity" value="COM_GOODSCLASS"/>
    
    
         <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          </td>
      </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="add"></div></td>
        <input id="btn_ok" style="display:none" onclick="ret()">
        <td><input name="COM_GOODSCLASS.ISPARENT" type="hidden" class="input1" id="COM_GOODSCLASS.ISPARENT" value="<%=isparent %>" size="30" maxlength="30"></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsAction"></td>
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
<script language="javascript">
function ret(){
	var api = frameElement.api;
	if (sumbit_check())
	{
		document.all("Submit").click();
		(api.data)({code:"refresh"});
	}
}
function select()
{
	var newtreeurl='unitcheckbox_tree.jsp';
	var str=showModalDialog(newtreeurl);
	if(str==null||str=="")
		return;
	var strs= new Array(); //定义一数组 
	strs=str.split(";"); //字符分割 
	document.getElementById("BASE_ORG.BLONGADMINORGNAME").value=strs[0];
	document.getElementById("BASE_ORG.BLONGADMINORGCODE").value=strs[1];
}
</script>
</BODY>
</HTML>
<% out.print(cu.getCheckstr());%>