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
if(staff.getGender().equals("男"))
     othersex="女";
else if(staff.getGender().equals("女"))
     othersex = "男";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">



<HTML>
<HEAD>
<base target="_self">
<TITLE>南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>

<script language=
                "javascript" type="text/javascript" src="../../js/MyDatePicker/WdatePicker.js">  </script>
<script language="javascript" src="../../js/public/key.js"></script>
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
	if (CkEmptyStr(document.all("staffname"),"用户名不能为空！") && CkEmptyStr(document.all("staffcode"),"员工编码不能为空！"))
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
             if(confirm("身份证号为"+idcard+"的员工已经存在。是否直接添加？"))
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
         alert("网络故障");
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
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 员工维护  &gt;&gt; 新建员工</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
          <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td><div align="right">身份证号：</div></td>
        <td><input name="idcard" type="text" class="input1" id="idcard"  onblur=exist() onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getIdcard() %>"></td>
      </tr>
      <tr>
        <td> <div align="right">员工编码：</div></td>
        <td ><input name="staffcode" type="text" class="input1" id="staffcode" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getCode()%>"></td>
        <td><div align="right">员工姓名：</div></td>
        <td><input name="staffname" type="text" class="input1" id="staffname" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getName()%>"></td>
      </tr>
      <tr>
        
      </tr>
        <td><div align="right">性别：</div></td>
        <td><input name="gender" type="radio" class="input1" id="gender"  onKeyDown="EnterKeyDo('')" size="40" value="<%=staff.getGender()%>" checked><%=staff.getGender() %></input>
            <input name="gender" type="radio" class="input1" id="gender"  onKeyDown="EnterKeyDo('')" size="40" value="<%=othersex %>" ><%=othersex %></input></td>
        <td><div align="right">生日：</div></td>
        <td><input name="birthday" type="Wdate" class="input1" id="birthday" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" onfocus="new WdatePicker(this,null,false,'whyGreen')" value="<%=staff.getBirthday() %>"></td>
      
      <tr>
         <td><div align="right">民族编码：</div></td>
         <td><input name="nationalitycode" type="text" class="input1" id="nationalitycode" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getNationalitycode() %>"></td>
         <td><div align="right">民族：</div></td>
         <td><input name="nationality" type="text" class="input1" id="nationality" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getNationality() %>"></td>
      </tr>
      
      <tr>
         <td><div align="right">籍贯：</div></td>
         <td><input name="nativeplace" type="text" class="input1" id="nativeplace" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getNativeplace() %>"></td>
         <td><div align="right">婚姻状况：</div></td>
         <td><input name="marriage" type="text" class="input1" id="marriage" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getMarriage() %>"></td>
      </tr>
      
      <tr>
         <td><div align="right">薪水等级：</div></td>
         <td><input name="salarylevel" type="text" class="input1" id="salarylevel" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getSalarylevel() %>"></td>
         <td><div align="right">参工时间：</div></td>
         <td><input name="begincareerdate" type="Wdate" class="input1" id="begincareerdate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=staff.getBegincareerdate() %>" size="30"  maxlength="30"></td>
      </tr>
      
       <tr>
         <td><div align="right">E-mail：<div></td>
         <td><input name="email" type="text" class="input1" id="email" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getEmail() %>"></td>
         <td><div align="right">QQ：</div></td>
         <td><input name="qq" type="text" class="input1" id="qq" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getQq() %>"></td>
      </tr>
       
       <tr>
         <td><div align="right">移动电话：</div></td>
         <td><input name="mobilephone" type="text" class="input1" id="mobilephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getMobilephone() %>"></td>
         <td><div align="right">办公电话：</div></td>
         <td><input name="officephone" type="text" class="input1" id="officephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getOfficephone() %>"></td>
      </tr>
      
      <tr>
         <td><div align="right">家庭电话：</div></td>
         <td><input name="homephone" type="text" class="input1" id="homephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40" value="<%=staff.getHomephone() %>"></td>
         <td><div align="right">家庭住址：</div></td>
         <td><input name="homeaddress" type="text" class="input1" id="homeaddress" onKeyDown="EnterKeyDo('')" size="40" maxlength="40"  value="<%=staff.getHomeaddress() %>"></td>
          
          <input name="act" type="hidden" id="act" value="modify"/>
          
          <input type="reset" name="reset" value="重置" style="display:none">
          <input name="action_class" type="hidden" id="dao_class" value="com.action.system.StaffInfoAction"></td>
      </tr>
      <tr>
      <input type="submit" name="Submit" value="提交" style="display:none">
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
