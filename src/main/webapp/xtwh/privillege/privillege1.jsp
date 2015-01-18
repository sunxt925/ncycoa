<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,com.db.*,com.common.*,java.util.ArrayList,com.entity.system.*"  errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<script type="text/javascript" src="../../js/jquery-2.0.3.min.js"></script></HEAD>

<script type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">
    var xmlHttp;
	 
	function createXMLHttpRequest() {
		//表示当前浏览器不是ie,如ns,firefox
		if(window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function validate(field) {
		UnSelectAll('form1');
		//var id=document.getElementById("menu1").value;
		var id=field.value;
		//alert(field.value);
		if (id) {
			//创建Ajax核心对象XMLHttpRequest
			createXMLHttpRequest();
			//alert(id);
			var url = "privilege_validate.jsp?role_id="+id+"&time=" + new Date().getTime();
			
			//设置请求方式为GET，设置请求的URL，设置为异步提交
			xmlHttp.open("GET", url, true);
			
			//将方法地址复制给onreadystatechange属性
			//类似于电话号码
			xmlHttp.onreadystatechange=callback;
			
			//将设置信息发送到Ajax引擎
			xmlHttp.send(null);
		} else {
			//document.getElementById("spanUserId").innerHTML = "";
		}	
	}
	
	function callback() {
		var str;
		var str1;
		var ch = new Array;
        var cks =document.getElementsByName("items");
		//Ajax引擎状态为成功
		if (xmlHttp.readyState == 4) {
			//HTTP协议状态为成功
			
			if (xmlHttp.status == 200) {
			str=xmlHttp.responseText;
			
				if (str!= "") {
					
					ch =str.split(",");
					
					for(var i=0;i<ch.length;i++){
					 
						 for(var j=0;j<cks.length;j++){
					    
					     	if(ch[i]==cks[j].value)
					     	{
					     		
					     	    cks[j].checked=true;
					     	}
					     	
					     }
					}
				}else {
				
					//document.getElementById("spanUserId").innerHTML = "";
				}
			}else {
				alert("请求失败，错误码=" + xmlHttp.status);
			}
		}
	}
function F8()
{
	if (confirm("确定按选定值设置权限？"))
	{
		document.all("form1").submit();
	}
}
function F5()
{
	window.location.reload();
}
function change()
{

}
function test(){
	
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 权限设置 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a><a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center">
	<table width="100%" height="40" border="0" cellpadding="3" cellspacing="5" bgcolor="b2d5ff">
      <tr>
        <td width="11%" bgcolor="#FFFFFF">请选择用户组：
          <select  name="menu1" onChange="validate(this)" >
          <option value="">==请选择==</option>
            <%
		  	    DBObject db=new DBObject();
				String sql_yhz="select * from system_role";
				DataTable dt_yhz=db.runSelectQuery(sql_yhz);
				if (dt_yhz!=null && dt_yhz.getRowsCount()>0)
				{
					for (int j=0;j<dt_yhz.getRowsCount();j++)
					{
						DataRow r=dt_yhz.get(j);
						//out.print("<option id='"+r.getString("role_id")+"' value='privillege.jsp?role_id="+r.getString("role_id")+"'>"+r.getString("role_name")+"</option>");
						out.print("<option  value='"+r.getString("rolecode")+"'>"+r.getString("rolename")+"</option>");
					}
				}
			%>
          </select></td>
      </tr>
    </table>
	
	<table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">
      <tr>
        <td width="30%" align="center">模块名称</td>
		<td width="70%" align="center">路径</td>
        </tr>
	  <%
	  String sql1="select * from system_menu order by level_code ";
	DataTable dt1=db.runSelectQuery(sql1);
		 if (dt1!=null && dt1.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt1.getRowsCount();i++)
			{
				DataRow r1=dt1.get(i);
				
				String level_code=r1.getString("level_code");
				 SystemModule m=new SystemModule(level_code);
				
	  %>
      <tr onMouseOver="this.style.backgroundColor='#D0E9ED'" onMouseOut="this.style.backgroundColor=''">
       <td align="left"><input type="checkbox" id="items" name="items" value="<%=r1.getString("level_code")%>" onclick="selecttriger('form1',this.value,this.checked )"   class="checkbox1"><%=Coder.getCdfromccm(r1.getString("level_code"))%><%=r1.getString("menu_name")%></td>
       <td align="left"><%=r1.getString("menu_url")%></td>
       </tr>
	  <%
	  	}}
	  %>
    </table>
<%
/*  if (dt!=null && dt.getRowsCount()>0)
		 {
		   ArrayList List = new ArrayList();
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
				String level_code=r.getString("level_code");
		
				
	}

	}
	*/
 %>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick=" UnSelectAll('form1')">清空</a>】</td>
          <td width="50%" align="right">&nbsp;</td>
        </tr>
      </table>
      <input name="act" type="hidden" id="act" value="setprivillege"><input name="dao_class" type="hidden" id="dao_class" value="com.dao.user.UserDao"></td>
    
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
