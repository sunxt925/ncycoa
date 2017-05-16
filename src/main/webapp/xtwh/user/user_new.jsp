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
<TITLE>首页 &gt;&gt; 系统维护 &gt;&gt; 员工维护  &gt;&gt; 新建员工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TITLE>
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
             createwindow('员工选择',url,'800px','800px');
              
        //     frameElement.api.close();
             
           
                         
           }
              
           }

           
        } 
        else
         alert("网络故障");
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
         alert("网络故障");
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
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 员工维护  &gt;&gt; 新建员工</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  -->
  
  <table cellpadding="5"  width="100%" align="left" >
				<tr>
					<td><a id="F8" style="display: none" href="#" onClick="F8()">保存[F8]</a></td>
				</tr>
			
			<tr>
				<td><span>身份证号：</span></td>
				<td>
					<input name="idcard" type="text" class="easyui-textbox" id="idcard"  onblur=exist() onKeyDown="EnterKeyDo('')" size="40" maxlength="40" >
				</td>
			</tr>
			<tr>
				<td><span>员工编码：</span></td>
				<td>
					<input name="staffcode" type="text" class="easyui-textbox" id="staffcode" onblur=changeIsExist() onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			 
			<tr>
				<td><span>员工姓名：</span></td>
				<td>
					<input name="staffname" type="text" class="easyui-textbox" id="staffname" onblur=nameExist() onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>性别：</span></td>
				<td>
					<input name="gender" type="radio" class="easyui-textbox" id="gender" value="男" onKeyDown="EnterKeyDo('')" >男</input>
            <input name="gender" type="radio" class="easyui-textbox" id="gender" value="女" onKeyDown="EnterKeyDo('')"  >女</input>
				</td>
			</tr>
			
			<tr>
				<td><span>生日：</span></td>
				<td>
					<input name="birthday" type="Wdate" class="easyui-textbox" id="birthday" onKeyDown="EnterKeyDo('')" size="40" maxlength="40"  onfocus="new WdatePicker(this,null,false,'whyGreen')"  >
				</td>
			</tr>
			<tr>
				<td><span>民族编码：</span></td>
				<td>
					<input name="nationalitycode" type="text" class="easyui-textbox" id="nationalitycode" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>民族：</span></td>
				<td>
					<input name="nationality" type="text" class="easyui-textbox" id="nationality" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>籍贯：</span></td>
				<td>
					<input name="nativeplace" type="text" class="easyui-textbox" id="nativeplace" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>婚姻状况：</span></td>
				<td>
					<input name="nativeplace" type="text" class="easyui-textbox" id="nativeplace" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>薪水等级：</span></td>
				<td>
					<input name="salarylevel" type="text" class="easyui-textbox" id="salarylevel" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>参工时间：</span></td>
				<td>
					<input name="begincareerdate" type="Wdate" class="easyui-textbox" id="begincareerdate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="30"  maxlength="30">
				</td>
			</tr>
			<tr>
				<td><span>E-mail：</span></td>
				<td>
					<input name="email" type="text" class="easyui-textbox" id="email" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>QQ：</span></td>
				<td>
                    <input name="qq" type="text" class="easyui-textbox" id="qq" onKeyDown="EnterKeyDo('')" size="40" maxlength="40"></td>				</td>
			</tr>
			<tr>
				<td><span>移动电话：</span></td>
				<td>
					<input name="mobilephone" type="text" class="easyui-textbox" id="mobilephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>办公电话：</span></td>
				<td>
					<input name="officephone" type="text" class="easyui-textbox" id="officephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>家庭电话：</span></td>
				<td>
					<input name="homephone" type="text" class="easyui-textbox" id="homephone" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			<tr>
				<td><span>家庭住址：</span></td>
				<td>
					<input name="homeaddress" type="text" class="easyui-textbox" id="homeaddress" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			
		</table>

<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
          
          <input name="act" type="hidden" id="act" value="add"/>
          <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode %>">
 <input name="orgname" type="hidden" id="orgname" value="<%=orgname %>">
 <input name="positioncode" type="hidden" id="positioncode" value="<%=positioncode %>">
 <input name="positionname" type="hidden" id="positionname" value="<%=positionname %>">
          <input type="reset" name="reset" value="重置" style="display:none">
          <input name="action_class" type="hidden" id="dao_class" value="com.action.system.StaffAction"></td>
          <input name="isexist" type="hidden" id="isexist" value="false">
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
