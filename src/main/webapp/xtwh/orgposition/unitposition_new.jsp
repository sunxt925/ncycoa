<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<base target="_self">
<a id="reload" href="#" style="display:none"></a>
<base target="_self">
</HEAD>
<%
    String bm=Format.NullToBlank(request.getParameter("bm"));
    String name=request.getParameter("name");
	Position position=new Position();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half()+10;
    DataTable dt=position.getAllPositionListDrawTable(page_no,per_page,name);
    DataTable dtcount=position.getAllPositionList(name);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
    Org og=new Org();
    //String trackName=og.getTrack(bm,"");
    String trackName="";
    String orgname=og.getOrgName(bm).get(0).get(1).toString();
%>

<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script type="text/javascript" src="../../js/public/searchvalue.js"></script>
<script language="javascript">
function searchName()
{
	var name=document.getElementById("search").value;
	var rand=Math.floor(Math.random()*10000);
	var reload=document.getElementById("reload");
	window.location.href="unitposition_new.jsp?sid="+rand+"&bm="+"<%=bm%>"+"&name="+name;
	reload.click();
	
}



function F8()
{
		document.all("Submit").click();
		//this.window.close();
}
</script>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="main_table_topbg" height="31" COLSPAN=2> 当前操作：<%=trackName %>分配岗位信息</td>
      </tr>
      <tr>
        <td class="main_table_topbg" height="31" align="right"> 岗位信息</td>
        <td class="main_table_topbg" height="31" align="right"><input name="search" type="text" class="input1" id="search"  onfocus="cls()" onblur="res()"  value="请输入关键字" size="30" maxlength="30"><a href="#" onClick="searchName()" class="button4">查询</a></td>
      </tr>
    </table>
     
    <%
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
	   out.print(tableutil.DrawTable());
	%>
    
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
      <tr><td></td>
      <td align="right">
      
      <%
          String unitccmtemp="&bm="+bm+"&name="+name;
      	out.print(PageUtil.DividePage(page_no,pagecount,"unitposition_new.jsp",unitccmtemp));
       %>
      </td>
      </tr>
        <tr>
          <td width="50%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          <td align="right"><a href="#" id="F8" onClick="F8()" style="display:none"  class="button4">确定</a>
       </td>
           <input type="submit" name="Submit" value="提交" style="display:none">
         <input type="txt" name="orgname" id="orgname" value="<%=orgname %>" style="display:none">
         <input type="txt" name="orgcode" id="orgcode" value="<%=bm %>" style="display:none">
         <input name="act" type="hidden" id="act" value="add">
         <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitPositionAction">
        </tr>
        
      </table>
      <%}%>
     
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
  </td>
  </tr>
</form>
</table>

</BODY>
 
</HTML>
