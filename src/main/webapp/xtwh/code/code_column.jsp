<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<%
	String table_name=Format.NullToBlank(request.getParameter("table_name"));
	String code_class=Format.NullToBlank(request.getParameter("code_class"));
    Code code=new Code();
	
	DataTable dt=code.getTableCodeColumn(table_name);
	
	
	
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">
function F8()
{
	document.all("act").value="modifycolumn";
	
	document.all("form1").submit();
	
}
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	
	window.open("../../xtwh/code/code_column_new.jsp?table_name="
						+ document.all("table_name").value + "&sid="+rand,"_self");
	//window.open("../xtwh/code/code_column_new.jsp?table_name="+table_name+"&sid="+rand,"_self");
}
function F4()
{
	document.all("act").value="deletecolumn";
	
	if (CheckSelect("form1"))
	{
		if (confirm("确定要删除选中的记录？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
	
}
function F5()
{
	window.location.reload();
}


</script>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
<!--  
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 系统配置 &gt;&gt; <a href="codemanage.jsp">编码管理</a>：<%=table_name%></td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  -->
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;
    <!-- 
    <a href="#" onClick="F8()">保存[F8]</a>   <a href="#" onClick="F4()">删除[F4]</a>  <a href="#" onClick="F3()">新增[F3]</a> <a href="#" onClick="F5()">刷新[F5]</a>
     -->
    </td>
  </tr> 
  
  <tr>
 
 
 
   <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="82%" border="0" cellspacing="0" cellpadding="0">
      <tr>
       <% if(code_class.equals("00020001")){%>
        <td width="200" valign="top" ><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#DBEAFD">
          
            </td>
          </tr>
         
          <tr>
            <td valign="top" height="100%"><iframe src="../../xtwh/code/code_tree.jsp?table_name=<%=table_name %>&pageurl=code_list.jsp&pagetarget=codelist" name="codetree" id="codetree" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
          </tr>
          
        </table></td><%} %>
		<td>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr valign="top">
		  <!--  
            <td valign="top"><iframe src="code_list.jsp?level_id=&table_name=" name="codelist" id="codelist" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
		 -->
		 <% if(code_class.equals("00020001")){%>
		  <td valign="top"><iframe src="code_list.jsp?code_id=<%=dt.get(0).getString("code_id") %>&table_name=<%=table_name %>" name="codelist" id="codelist" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
	<%}else if(code_class.equals("00020002")){ %>
	 <td valign="top"><iframe src="code_list2.jsp?table_name=<%=table_name %>" name="codelist2" id="codelist2" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
	
	<%}else if(code_class.equals("00020003")){%>
	 <td valign="top"><iframe src="code_list3.jsp?table_name=<%=table_name %>" name="codelist3" id="codelist3" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
	
	<%} %>
		  </tr>
		  
		</table>
		</td>
      </tr>
    </table></td>
     
 
     <!--  
    <input name="act" type="hidden" id="act" value="deletecolumn">-->
     <input name="act" type="hidden" id="act">
      <input type="submit" name="Submit" value="提交" style="display:none">
            
      <input name="table_name" type="hidden" id="table_name" value="<%=table_name%>">
      <input name="action_class" type="hidden" id="action_class" value="com.action.system.CodeAction"></td>
     
  </tr>
  <tr>
  <!-- 
   <td width="50%" align="right">
           <%
      	//out.print(PageUtil.DividePage(page_no,pagecount,"codemanage.jsp",""));
       %></td> -->
    <td height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
