<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.StaffInfo" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String positioncode=(String)session.getAttribute("positioncode");
String positionname=(String)session.getAttribute("positionname");
String orgcode=(String)session.getAttribute("orgcode");
String orgname=(String)session.getAttribute("orgname");



String staffcode=Format.NullToBlank(request.getParameter("staffcode"));
StaffInfo staff=new StaffInfo(staffcode);
String othersex=null;
if(staff.getGender().equals("��"))
     othersex="Ů";
else if(staff.getGender().equals("Ů"))
     othersex = "��";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">



<HTML>
<HEAD>
<base target="_self">
<TITLE>�ϳ����̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>

<script language=
                "javascript" type="text/javascript" src="../../js/MyDatePicker/WdatePicker.js">  </script>

<script language="javascript" src="../../js/public/check.js"></script>
<script language="javascript">

var xmlrequest;
var idcard;
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
	if (CkEmptyStr(document.all("staffname"),"�û�������Ϊ�գ�") && CkEmptyStr(document.all("staffcode"),"Ա�����벻��Ϊ�գ�"))
	{
		//alert ("aaa");
		document.all("Submit").click();
	}
}

function myidcard()
{ 
  
   idcard = document.getElementById("idcard").value;
   if(confirm(idcard))
   {
      alert("yes");
   }
}


 function createXMLHttpRequest()
    {
        if(window.XMLHttpRequest)
        {
           xmlrequest = new XMLHttpRequest();
        }
        else if(window.ActiveXObject)
        {
           try
           {
              xmlrequest = new AcitveXObject("Msxml2.XMLHTTP");
           }
           catch(e)
           {
             try
             {
                xmlrequest = new ActiveXObject("Microsoft.XMLHTTP");
             }
             catch(e)
             {}
           }
        }
    }
    
 function exist()
 {
   createXMLHttpRequest();
   idcard = document.getElementById("idcard").value;
   
   var uri = "../../userexist?idcard="+idcard;
   xmlrequest.open("post",uri,true);
   xmlrequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   xmlrequest.onreadystatechange = processResponse;
   xmlrequest.send(null);
   
 }
 
     function processResponse()
    {
    
      if(xmlrequest.readyState == 4)
      {
        if(xmlrequest.status == 200)
        {
           var res = xmlrequest.responseText;
           if(res == "yes")
            {
             if(confirm("���֤��Ϊ"+idcard+"��Ա���Ѿ����ڡ��Ƿ�ֱ����ӣ�"))
             {
               getinfo();
             }
            }
           else if(res == "no")
           {
             alert("no");
           }
           
        } 
        else
         alert("�������");
      }
    }
    
    function getinfo()
    {
    
       var newmemberurl='../../servlet/PageHandler?act=get&action_class=com.action.system.StaffAction&idcard='+idcard;
       window.open(newmemberurl,"_self");
    }
</script>
<BODY class="mainbody" onLoad="document.all('staffcode').focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>-->
<!--  </tr>-->
<table cellpadding="5"  width="100%" align="left" >
				<tr>
					<td><a id="F8" style="display: none" href="#" onClick="F8()">����[F8]</a></td>
				</tr>
			
			<tr>
				<td><span>���֤�ţ�</span></td>
				<td>
					<input name="idcard" type="text" class="easyui-textbox" id="idcard"  onblur=exist() onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getIdcard() %>">
				</td>
			</tr>
			<tr>
				<td><span>Ա�����룺</span></td>
				<td>
					<input name="staffcode" type="text" class="easyui-textbox" id="staffcode" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getCode()%>">
				</td>
			</tr>
			 
			<tr>
				<td><span>Ա��������</span></td>
				<td>
					<input name="staffname" type="text" class="easyui-textbox" id="staffname" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getName()%>">
				</td>
			</tr>
			<tr>
				<td><span>�Ա�</span></td>
				<td>
					<input name="gender" type="radio" class="easyui-textbox" id="gender"  onKeyDown="EnterKeyDo('')" size="40" value="<%=staff.getGender()%>" checked><%=staff.getGender() %></input>
            <input name="gender" type="radio" class="easyui-textbox" id="gender"  onKeyDown="EnterKeyDo('')" size="40" value="<%=othersex %>" ><%=othersex %></input>
				</td>
			</tr>
			
			<tr>
				<td><span>���գ�</span></td>
				<td>
					<input name="birthday" type="Wdate" class="easyui-textbox" id="birthday" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" onfocus="new WdatePicker(this,null,false,'whyGreen')" value="<%=staff.getBirthday() %>">
				</td>
			</tr>
			<tr>
				<td><span>������룺</span></td>
				<td>
					<input name="nationalitycode" type="text" class="easyui-textbox" id="nationalitycode" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getNationalitycode() %>">
				</td>
			</tr>
			<tr>
				<td><span>���壺</span></td>
				<td>
					<input name="nationality" type="text" class="easyui-textbox" id="nationality" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getNationality() %>">
				</td>
			</tr>
			<tr>
				<td><span>���᣺</span></td>
				<td>
					<input name="nativeplace" type="text" class="easyui-textbox" id="nativeplace" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getNativeplace() %>">
				</td>
			</tr>
			<tr>
				<td><span>����״����</span></td>
				<td><input name="marriage" type="text" class="easyui-textbox" id="marriage" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getMarriage() %>"></td>
			</tr>
			<tr>
				<td><span>нˮ�ȼ���</span></td>
			         <td><input name="salarylevel" type="text" class="easyui-textbox" id="salarylevel" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getSalarylevel() %>"></td>

			</tr>
			<tr>
				<td><span>�ι�ʱ�䣺</span></td>
			         <td><input name="begincareerdate" type="Wdate" class="easyui-textbox" id="begincareerdate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=staff.getBegincareerdate() %>" size="30"  maxlength="30"></td>

			</tr>
			<tr>
				<td><span>E-mail��</span></td>
			         <td><input name="email" type="text" class="easyui-textbox" id="email" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getEmail() %>"></td>

			</tr>
			<tr>
				<td><span>QQ��</span></td>
				         <td><input name="qq" type="text" class="easyui-textbox" id="qq" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getQq() %>"></td>
			</tr>
			<tr>
				<td><span>�ƶ��绰��</span></td>
				         <td><input name="mobilephone" type="text" class="easyui-textbox" id="mobilephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getMobilephone() %>"></td>

			</tr>
			<tr>
				<td><span>�칫�绰��</span></td>
				         <td><input name="officephone" type="text" class="easyui-textbox" id="officephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getOfficephone() %>"></td>
			</tr>
			<tr>
				<td><span>��ͥ�绰��</span></td>
				         <td><input name="homephone" type="text" class="easyui-textbox" id="homephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getHomephone() %>"></td>
			</tr>
			<tr>
				<td><span>��ͥסַ��</span></td>
				         <td><input name="homeaddress" type="text" class="easyui-textbox" id="homeaddress" onKeyDown="EnterKeyDo('')" size="40" maxlength="40"  value="<%=staff.getHomeaddress() %>"></td>
			</tr>
			
		</table>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      
          <input name="act" type="hidden" id="act" value="modify"/>
          
          <input type="reset" name="reset" value="����" style="display:none">
          <input name="action_class" type="hidden" id="dao_class" value="com.action.system.StaffInfoAction"></td>
      </tr>
      <tr>
      <input type="submit" name="Submit" value="�ύ" style="display:none">
      </tr>
  <input type="button" id="btn_ok" style="display: none" onclick="ret()">
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
<script type="text/javascript">
function ret(){
	var api = frameElement.api;
 	document.all("Submit").click();
 	(api.data)({code:"refresh"});
}
</script>
</BODY>
</HTML>
