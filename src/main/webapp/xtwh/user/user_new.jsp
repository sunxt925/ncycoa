<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String positioncode=request.getParameter("positioncode");
String positionname=request.getParameter("positionname");
String orgcode=request.getParameter("orgcode");
String orgname=request.getParameter("orgname");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">



<HTML>
<HEAD>
<TITLE>��ҳ &gt;&gt; ϵͳά�� &gt;&gt; Ա��ά��  &gt;&gt; �½�Ա��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TITLE>
<base target="_self">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<script language=
                "javascript" type="text/javascript" src="../../js/MyDatePicker/WdatePicker.js">  </script>

<script language="javascript" src="../../js/public/check.js"></script>
<script language="javascript" src="../../js/public/select.js"></script>

 <script type="text/javascript" src="../../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../../jscomponent/tools/outwindow.js"></script>
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
 
 function nameExist()
 {
   createXMLHttpRequest();
   staffname = document.getElementById("staffname").value;
   
   var uri = "../../usernameexist?staffname="+staffname;
   xmlrequest.open("post",uri,true);
   xmlrequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   xmlrequest.onreadystatechange = nameExistResponse;
   xmlrequest.send(null);
 }
 
 function nameExistResponse()
 {
         if(xmlrequest.readyState == 4)
      {
        if(xmlrequest.status == 200)
        {
           var userinfo = xmlrequest.responseText;
           if(userinfo == "no")
           {
           
           }
           else 
           {
           
           var obj = eval ("(" + userinfo + ")");
           
           if(obj.onlyone == "true")
           {
           
           document.getElementById("idcard").value = obj.idcard;
           document.getElementById("staffcode").value = obj.staffcode;     
           document.getElementById("gender").value = obj.gender;
           
           var  gender= document.getElementsByName("gender");
           for(i=0;i<gender.length;i++)
           {
           if(gender[i].value == obj.gender)
              gender[i].checked = true;
           }
           
           document.getElementById("birthday").value = obj.brithday;
           document.getElementById("nationalitycode").value = obj.nationalitycode;
           document.getElementById("nationality").value = obj.nationality;
           document.getElementById("nativeplace").value = obj.nativeplace;
           document.getElementById("marriage").value = obj.marriage;
           document.getElementById("salarylevel").value = obj.salarylevel;
           document.getElementById("begincareerdate").value = obj.begincareerdate;
           document.getElementById("email").value = obj.email;
           document.getElementById("qq").value = obj.qq;
           document.getElementById("mobilephone").value = obj.mobilephone;
           document.getElementById("officephone").value = obj.officephone;
           document.getElementById("homephone").value = obj.homephone;
           document.getElementById("homeaddress").value = obj.homeaddress;
           document.getElementById("isexist").value = "true";
           }
           else if(obj.onlyone == "false")
           {
            //  alert(obj.onlyone);
             var staffname = document.getElementById("staffname").value;
             var url="xtwh/user/username_choose.jsp?orgcode="+"<%=orgcode%>"+"&orgname="+"<%=orgname%>"+"&positioncode="+"<%=positioncode%>"+"&positionname="+"<%=positionname%>"+"&staffname="+staffname;
           //  alert(url);
             createwindow('Ա��ѡ��',url,'800px','800px');
              
        //     frameElement.api.close();
             
           
                         
           }
              
           }

           
        } 
        else
         alert("�������");
      }
 }
 
     function processResponse()
    {
   
      if(xmlrequest.readyState == 4)
      {
        if(xmlrequest.status == 200)
        {
           var userinfo = xmlrequest.responseText;
           if(userinfo == "no")
           {
           
           }
           else
           {
           
           var obj = eval ("(" + userinfo + ")");
           document.getElementById("staffcode").value = obj.staffcode;
           document.getElementById("staffname").value = obj.staffname;
           document.getElementById("gender").value = obj.gender;
           
           var  gender= document.getElementsByName("gender");
           for(i=0;i<gender.length;i++)
           {
           if(gender[i].value == obj.gender)
              gender[i].checked = true;
           }
           
           document.getElementById("birthday").value = obj.brithday;
           document.getElementById("nationalitycode").value = obj.nationalitycode;
           document.getElementById("nationality").value = obj.nationality;
           document.getElementById("nativeplace").value = obj.nativeplace;
           document.getElementById("marriage").value = obj.marriage;
           document.getElementById("salarylevel").value = obj.salarylevel;
           document.getElementById("begincareerdate").value = obj.begincareerdate;
           document.getElementById("email").value = obj.email;
           document.getElementById("qq").value = obj.qq;
           document.getElementById("mobilephone").value = obj.mobilephone;
           document.getElementById("officephone").value = obj.officephone;
           document.getElementById("homephone").value = obj.homephone;
           document.getElementById("homeaddress").value = obj.homeaddress;
           document.getElementById("isexist").value = "true";
              
           }

           
        } 
        else
         alert("�������");
      }
    }
    
    function changeIsExist()
    {
    
    var isexist = document.getElementById("isexist").value;
    if(isexist == "true")
       document.getElementById("isexist").value = "false";
    
    }
    
    function getinfo()
    {
    
     document.getElementById("idcard0").value=idcard;
     document.all("Submit0").click();
    
      // var newmemberurl='../../servlet/PageHandler?act=get&action_class=com.action.system.StaffAction&idcard='+idcard;
     //  window.open(newmemberurl,"_self");
    }
</script>

<BODY class="mainbody" onLoad="document.all('staffcode').focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler"><!--
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ &gt;&gt; ϵͳά�� &gt;&gt; Ա��ά��  &gt;&gt; �½�Ա��</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  -->
  
  <table cellpadding="5"  width="100%" align="left" >
				<tr>
					<td><a id="F8" style="display: none" href="#" onClick="F8()">����[F8]</a></td>
				</tr>
			
			<tr>
				<td><span>���֤�ţ�</span></td>
				<td>
					<input name="idcard" type="text" class="easyui-textbox" id="idcard"  onblur=exist() onKeyDown="EnterKeyDo('')" size="40" maxlength="40" >
				</td>
			</tr>
			<tr>
				<td><span>Ա�����룺</span></td>
				<td>
					<input name="staffcode" type="text" class="easyui-textbox" id="staffcode" onblur=changeIsExist() onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			 
			<tr>
				<td><span>Ա��������</span></td>
				<td>
					<input name="staffname" type="text" class="easyui-textbox" id="staffname" onblur=nameExist() onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>�Ա�</span></td>
				<td>
					<input name="gender" type="radio" class="easyui-textbox" id="gender" value="��" onKeyDown="EnterKeyDo('')" >��</input>
            <input name="gender" type="radio" class="easyui-textbox" id="gender" value="Ů" onKeyDown="EnterKeyDo('')"  >Ů</input>
				</td>
			</tr>
			
			<tr>
				<td><span>���գ�</span></td>
				<td>
					<input name="birthday" type="Wdate" class="easyui-textbox" id="birthday" onKeyDown="EnterKeyDo('')" size="40" maxlength="40"  onfocus="new WdatePicker(this,null,false,'whyGreen')"  >
				</td>
			</tr>
			<tr>
				<td><span>������룺</span></td>
				<td>
					<input name="nationalitycode" type="text" class="easyui-textbox" id="nationalitycode" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>���壺</span></td>
				<td>
					<input name="nationality" type="text" class="easyui-textbox" id="nationality" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>���᣺</span></td>
				<td>
					<input name="nativeplace" type="text" class="easyui-textbox" id="nativeplace" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>����״����</span></td>
				<td>
					<input name="nativeplace" type="text" class="easyui-textbox" id="nativeplace" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>нˮ�ȼ���</span></td>
				<td>
					<input name="salarylevel" type="text" class="easyui-textbox" id="salarylevel" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>�ι�ʱ�䣺</span></td>
				<td>
					<input name="begincareerdate" type="Wdate" class="easyui-textbox" id="begincareerdate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="30"  maxlength="30">
				</td>
			</tr>
			<tr>
				<td><span>E-mail��</span></td>
				<td>
					<input name="email" type="text" class="easyui-textbox" id="email" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>QQ��</span></td>
				<td>
                    <input name="qq" type="text" class="easyui-textbox" id="qq" onKeyDown="EnterKeyDo('')" size="40" maxlength="40"></td>				</td>
			</tr>
			<tr>
				<td><span>�ƶ��绰��</span></td>
				<td>
					<input name="mobilephone" type="text" class="easyui-textbox" id="mobilephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>�칫�绰��</span></td>
				<td>
					<input name="officephone" type="text" class="easyui-textbox" id="officephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>��ͥ�绰��</span></td>
				<td>
					<input name="homephone" type="text" class="easyui-textbox" id="homephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>��ͥסַ��</span></td>
				<td>
					<input name="homeaddress" type="text" class="easyui-textbox" id="homeaddress" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			
		</table>

<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
          
          <input name="act" type="hidden" id="act" value="add"/>
          <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode %>">
 <input name="orgname" type="hidden" id="orgname" value="<%=orgname %>">
 <input name="positioncode" type="hidden" id="positioncode" value="<%=positioncode %>">
 <input name="positionname" type="hidden" id="positionname" value="<%=positionname %>">
          <input type="reset" name="reset" value="����" style="display:none">
          <input name="action_class" type="hidden" id="dao_class" value="com.action.system.StaffAction"></td>
          <input name="isexist" type="hidden" id="isexist" value="false">
      </tr>
      <tr>
      <input type="submit" name="Submit" value="�ύ" style="display:none">
      </tr>
  
    </table></td>
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
