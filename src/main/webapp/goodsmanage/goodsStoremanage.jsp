<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<HTML>
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>


<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("unit_new.jsp?sid="+rand,"unittemp");
	//window.open ("unit_new.jsp","Sample","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, copyhistory=no,width=350,height=140,left=200,top=300");        
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("�����˵���ɾ��������ɾ���Ӳ˵����Ƿ������"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("��û��ѡ��Ҫɾ�������ݣ�");
	}
}
function F5()
{
	window.location.reload();
}

function changetopunit(unitccm)
{
	var newlisturl='goodsStore_list.jsp?unitccm='+unitccm;
	var newtreeurl='../tree/goods_tree.jsp?pageurl=../xtwh/goodsmanage/goodsStore_list.jsp&pagetarget=goodsStorelist&unitccm='+unitccm;
	document.getElementById("goodsStorelist").src=newlisturl;
	//window.open(newlisturl,'unitlist');
	window.open(newtreeurl,'goodstree');
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%" valign="top">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          
          <tr>
            <td valign="top" height="100%"><iframe src="../tree/goods_tree.jsp?pageurl=../goodsmanage/goodsStore_list.jsp&pagetarget=goodsStorelist" name="goodstree" id="goodstree" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
          </tr>
        </table>
		
		</td>
		<td valign="top" width="80%" height="100%">
			<iframe src="goodsStore_list.jsp" name="goodsStorelist" id="goodsStorelist" width="100%" height="100%"  frameborder="0"></iframe>
			
        </td>
		
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