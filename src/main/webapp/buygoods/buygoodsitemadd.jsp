<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�ϳ��̲�ר����</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<%
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
	
	  if(document.getElementById("COM_BUYGOODSITEM.NEEDMONTH").value!=""&&document.getElementById("COM_BUYGOODSITEM.INPUTDATE").value!=""){
	   document.all("Submit").click();
     
		window.returnValue="refresh";
	  }
	else
	{
	alert("���ڲ���Ϊ�գ�");
	}
	}
	else
	{
	  alert("���ۺͲɹ���������Ϊ�գ�");
	}
	
	
	 }
	
	
}
function select()
{
var rand=Math.floor(Math.random()*10000);
	var newtreeurl='goodsclasscheckbox_tree.jsp?sid='+rand;
	
	var str=showModalDialog(newtreeurl);
	if(str==null||str=="")
		return;
	var strs= new Array(); //����һ���� 
	
	strs=str.split(";"); //�ַ��ָ� 
	/*if(strs==null||strs=="")
		return;*/
	document.getElementById("COM_BUYGOODSITEM.GOODSNAME").value=strs[0];
	document.getElementById("COM_BUYGOODSITEM.GOODSCODE").value=strs[1];
	document.getElementById("COM_BUYGOODSITEM.PROJECTCODE").value=strs[2];
	document.getElementById("COM_BUYGOODSITEM.PROJECTNAME").value=strs[3];
	
	
	//document.getElementById("COM_INSTOREITEM.GOODSCODE").value=strs[1];
	//window.open (newtreeurl,"Sample","toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,scrollbars=yes, copyhistory=no,width=350,height=540,left=200,top=300");
}



/*
var xmlHttp=null;

function showHint(str)
{
if (str.length==0)
  { 
  document.getElementById("txtHint").innerHTML="";
  return;
  }
try
  {// Firefox, Opera 8.0+, Safari, IE7
  xmlHttp=new XMLHttpRequest();
  }
catch(e)
  {// Old IE
  try
    {
    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  catch(e)
    {
    alert ("Your browser does not support XMLHTTP!");
    return;  
    }
  }
var url="test.jsp?q=" + str;
url=url+"&sid="+Math.random();
xmlHttp.open("GET",url,false);
xmlHttp.send(null);
document.getElementById("txtHint").innerHTML=xmlHttp.responseText;
}

function sss(value)
{
  var strs= new Array();
  strs=value.toString().split(",");
  //alert("0="+strs[0]+"1="+strs[1]);
  document.getElementById("COM_BUYGOODSITEM.PROJECTNAME").value=strs[0];
}*/

</script>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">��Ŀ���ƣ�</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","PROJECTNAME")); 
        
        %>
      
        </td>
      </tr>
      <tr>
      <td><div align="right">Ʒ����</div></td>
        <td> <!-- 
       <input type="text" id="COM_BUYGOODSITEM.GOODSNAME" name="COM_BUYGOODSITEM.GOODSNAME" onkeyup="showHint(this.value)">
        <span id="txtHint"></span>-->
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSNAME")); %>
       
        <a href="#" onClick="select()" class="button4">ѡ��</a> 
        </td>
      </tr>
      <input type="hidden" name="COM_BUYGOODSITEM.GOODSCODE" id="COM_BUYGOODSITEM.GOODSCODE"/>
      <input type="hidden" name="COM_BUYGOODSITEM.PROJECTCODE" id="COM_BUYGOODSITEM.PROJECTCODE"/>
      <tr>
        <td width="20%"><div align="right">����ͺţ�</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSSTYLE")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">������λ��</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSUNIT")); %>
        
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">�ɹ�˵����</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","BUYGOODSDESC")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">���ۣ�</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSPRICE"));
         %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">�ɹ�������</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","GOODSNUMBER")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">Ԥ���</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","TOTALCOST")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">����ʱ�䣺</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","NEEDMONTH")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">�ɹ�ģʽ��</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","BUYMODE")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">���벿�ţ�</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","BUYORGCODE")); %>
        </td>
      </tr>
     <tr>
        <td width="20%"><div align="right">�����ˣ�</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","HANDLER")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">��дʱ�䣺</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYGOODSITEM","INPUTDATE"));
       // System.out.println(cu.print("COM_BUYGOODSITEM","INPUTDATE"));
         %>
          </td>
      </tr>
       <tr>
       <input name="entity" id="entity" type="hidden" value="COM_BUYGOODSITEM"/>
        <input name="act" type="hidden" id="act" value="add">
       
       <input name="COM_BUYGOODSITEM.SUMMITFLAG" id="COM_BUYGOODSITEM.SUMMITFLAG" type="hidden" value="0"/>
         <input type="submit" name="Submit" value="�ύ" style="display:none">
            <input type="reset" name="reset" value="����" style="display:none">
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