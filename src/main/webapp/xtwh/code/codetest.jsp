<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.dao.system.CodeTree" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE></TITLE>
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
<link rel="stylesheet" href="../../images/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../../js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../js/ztree/jquery.ztree.core-3.5.js"></script>
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
		<%
			out.print(CodeTree.getTreeNew(table_name,"code_list.jsp","codelist"));
		%>
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		//-->
	</SCRIPT>
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
          
          <div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
</div>
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
	<%}else{ %>
	 <td valign="top"><iframe src="code_list2.jsp?table_name=<%=table_name %>" name="codelist2" id="codelist2" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
	
	<%} %>
		  </tr>
		  
		</table>
		</td>
      </tr>
    </table></td>
     
 <!-- 
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">
      
      <tr>
      <td  width="20%" align="center">
      </td>
     <td width="10%" align="center">&nbsp;</td>
	    <td width="30%" align="center">编码</td>
        <td width="30%" align="center">编码说明</td>
		
        </tr>
	  <%
			 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
	  %>
      <tr onMouseOver="this.style.backgroundColor='#D0E9ED'" onMouseOut="this.style.backgroundColor=''">
	      
	     <td align="center"><label>
          <input type="checkbox" id="items" name="items" value="<%=r.getString("code_id")%>" class="checkbox1">
        </label></td>
	    <td align="left"><input name="code_id_<%=r.getString("CODE_ID") %>" type="text" id="code_id_<%=r.getString("CODE_ID") %>" value="<%=r.getString("CODE_ID") %>" style="width:100%"></td>
        <td align="left"><input name="code_name_<%=r.getString("CODE_ID")%>" type="text" id="code_name_<%=r.getString("CODE_ID")%>" value="<%=r.getString("code_name")%>" style="width:100%"></td>
		
        </tr>
         
	  <%
	  	}}
	  %>
    </table>
    -->
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
<flow:StepChoice table="1"/>
</form>
</table>
</BODY>
</HTML>
