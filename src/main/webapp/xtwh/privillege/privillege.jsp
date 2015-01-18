<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,com.db.*,com.common.*,java.util.ArrayList,com.entity.system.*,com.dao.system.*"  errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<script type="text/javascript" src="../../js/jquery-2.0.3.min.js"></script></HEAD>
<link rel="stylesheet" href="../../images/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../../js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../../js/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
<%
	String roid=request.getParameter("roid");

 %>
<script language="javascript" src="../../js/public/select.js"></script>

<script language="javascript">


function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		py = $("#py").attr("checked")? "p":"",
		sy = $("#sy").attr("checked")? "s":"",
		pn = $("#pn").attr("checked")? "p":"",
		sn = $("#sn").attr("checked")? "s":"",
		type = { "Y":"ps", "N":"ps"};
		zTree.setting.check.chkboxType = type;
		//showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	}
var zNodes=<%=UnitTreecheckbox.getTreeMenu(roid)%> ;
function change()
	{
		//alert(document.all("menu1").value);
		window.location.href="privillege.jsp?roid="+document.all("menu1").value;
	}
var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};

		$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
	});
function F8()
{
	if (confirm("确定按选定值设置权限？"))
	{
		if(document.all("menu1").value=="selection")
		{
			alert("请选择角色");
			return;
		}
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
 			var str="";
            var nodes = zTree.getCheckedNodes(true);
 			var orgname,orgcode;
 			//alert(nodes.length);
            for (var i = 0; i < nodes.length; i++) {
 				str+=nodes[i].id+";";       
			}
			str+=document.all("menu1").value;
		
		document.getElementById("result").value=str;
		document.all("form1").submit();
	}
}
function F5()
{
	window.location.reload();
}

function test(){
	
	
			alert(document.all("menu1").value);
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
          <select  name="menu1" id="menu1"  onChange="change()" >
          <option value="selection">==请选择==</option>
            <%
		  	    DBObject db=new DBObject();
				String sql_yhz="select * from system_role";
				DataTable dt_yhz=db.runSelectQuery(sql_yhz);
				if (dt_yhz!=null && dt_yhz.getRowsCount()>0)
				{
					for (int j=0;j<dt_yhz.getRowsCount();j++)
					{
						DataRow r=dt_yhz.get(j);
						String selected="";
						if(r.getString("rolecode").equals(roid))
							selected="selected";
						//out.print("<option id='"+r.getString("role_id")+"' value='privillege.jsp?role_id="+r.getString("role_id")+"'>"+r.getString("role_name")+"</option>");
						out.print("<option  value='"+r.getString("rolecode")+"' "+selected+">"+r.getString("rolename")+"</option>");
					}
				}
			%>
          </select></td>
      </tr>
    </table>
	
	<table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">
	<tr>
	<td style="width: 50%">
	
	 <div style="overflow-x: auto; overflow-y: auto; height: 750px;">
		<ul id="treeDemo" class="ztree"></ul>
	 </div>
	</td>
	
	</tr>
     

    </table>
      
      <input name="act" type="hidden" id="act" value="add">
      <input name="result" type="hidden" id="result" value="">
      <input name="action_class" type="hidden" id="action_class" value="com.action.system.SysMenuPrivilegeAction"></td>
    
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
