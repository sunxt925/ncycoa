<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,java.util.ArrayList,com.entity.system.*,com.dao.system.*"  errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<%
	//初始化表格内容
	TBM_AdminDept  tbm=new TBM_AdminDept();
	DataTable dt=tbm.getMemberList();
%>
<script type="text/javascript" src="../../js/jquery-2.0.3.min.js"></script></HEAD>

<script language="javascript" src="../../js/public/select.js"></script>



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

function validate(value,field) {
	//field.checked="checked";
	
	
	
	field.cells[0].childNodes[0].checked="checked";
	
	//field.cells[0].selected="selected";
	clearOrg(document.getElementById("OrgTable"));
	document.getElementById("staffcode").value =value;
	//alert(document.getElementById("staffcode").value);
	if (value!=null) {
		//创建Ajax核心对象XMLHttpRequest
		createXMLHttpRequest();
		//alert(id);
		var url = "staffcode_orgcode.jsp?staffcode="+value+"&time=" + new Date().getTime();
		
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
			initDepartment(str,1);
			
		}else {
			alert("请求失败，错误码=" + xmlHttp.status);
		}
	}
}
function validateDeletePerson(value) {
	//alert(value);
	 if (value!=null) {
		//创建Ajax核心对象XMLHttpRequest
		createXMLHttpRequest();
		//alert(id);
		var url = "clear_staff.jsp?staffcode="+value+"&time=" + new Date().getTime();
		//alert(url);
		//设置请求方式为GET，设置请求的URL，设置为异步提交
		xmlHttp.open("GET", url, true);
		
		//将方法地址复制给onreadystatechange属性
		//类似于电话号码
		xmlHttp.onreadystatechange=callbackdeletePerson;
		
		//将设置信息发送到Ajax引擎
		xmlHttp.send(null);
	} else {
		//document.getElementById("spanUserId").innerHTML = "";
	}	
}
function callbackdeletePerson() {
	var str;
	
	//Ajax引擎状态为成功
	if (xmlHttp.readyState == 4) {
		//HTTP协议状态为成功
		
		if (xmlHttp.status == 200) {
			str=xmlHttp.responseText;
			if(str=="true"){
				alert("删除成功");
				clearOrg();
				initOrgCodeValue();
				}
				
			else{
				alert("删除失败");
				clearOrg();
				initOrgCodeValue();
			}
				
			
		}else {
			alert("请求失败，错误码=" + xmlHttp.status);
		}
	}
}
function F5()
{
	window.location.reload();
}

function test(){

			alert(document.all("menu1").value);
}
function selectPerson(){
	var flag=0;
	//var result =window.showModalDialog("systemrole_member_new.jsp",window,"scroll=no;status=no;dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;");
	var results= showModalDialogWin("../PerformanceInputRights/systemrole_member_new.jsp",1000,600);
	var table=document.getElementById("personTable");
	var rowsNum=table.rows.length;//获取当前行数
	var result=results.split(";");
	
	for(var i=0;i<result.length-1;i++){
		flag=0;
		var staffcode=result[i].split(",");
		for(var j=1;j<rowsNum;j++){//判断是否重复，否则不进行table插入
			if(table.rows[j].cells[1].innerText==staffcode[0]){
				flag=1;
				break;
			}
		} 
		if(flag==1)
			continue;
		var newRow = table.insertRow(rowsNum); //创建新行
		newRow.onmouseover=function(){
			this.style.backgroundColor='#D0E9ED';
			this.style.cursor='hand';
		};
		newRow.onmouseout=function(){
			this.style.backgroundColor='';
		};
		
		 newRow.onclick=function(){
			//事件显示关联的权限
			validate(this.children[1].innerText,this);
		}; 
		//给每一行添加点击事件
		var newCell1 = newRow.insertCell(0); //创建新单元格
		newCell1.innerHTML="<input type='radio' name='personRadio' id='"+staffcode[0]+"' style='width:20px' />";
		var newCell2 = newRow.insertCell(1); //创建新单元格
		newCell2.innerText=staffcode[0];
		var newCell3 = newRow.insertCell(2); //创建新单元格
		newCell3.innerText = staffcode[1]; //单元格内的内容
		
		
		
		//newCell.setAttribute("align","left"); //设置位置
	}
	
	//document.getElementById("staffcode").value=staffcodes;
	//alert(document.getElementById("staffcode").value);
	

}
function initDepartment(value,first){
	
	//var result =window.showModalDialog("systemrole_member_new.jsp",window,"scroll=no;status=no;dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;");
	var results= value;
	var table=document.getElementById("OrgTable");
	var rowsNum=table.rows.length;//获取当前行数
	var flag=0;
	var result=results.split(";");
	
	for(var i=0;i<result.length-1;i++){
		flag=0;
		var orgcode=result[i].split(",");
		if(first!=1){//不是第一次进入，则进行判断
			for(var j=1;j<rowsNum;j++){//判断是否重复，否则不进行table插入
				if(table.rows[j].cells[1].innerText==orgcode[0]){
					flag=1;
					break;
				}
			} 
		}
		 
		if(flag==1)
			continue;
		var newRow = table.insertRow(rowsNum); //创建新行
		newRow.onmouseover=function(){
			this.style.backgroundColor='#D0E9ED';
			
		};
		newRow.onmouseout=function(){
			this.style.backgroundColor='';
		};
		
		 
		//给每一行添加点击事件
		var newCell1 = newRow.insertCell(0); //创建新单元格
		newCell1.innerHTML="<input type='radio' name='departmentRadio' id='"+orgcode[0]+"' style='width:20px' />";
		var newCell2 = newRow.insertCell(1); //创建新单元格
		newCell2.innerText=orgcode[0];
		var newCell3 = newRow.insertCell(2); //创建新单元格
		newCell3.innerText = orgcode[1]; //单元格内的内容
		
		
		//newCell.setAttribute("align","left"); //设置位置
	}
	initOrgCodeValue();
	//document.getElementById("orgcode").value+=orgcodes;
	//alert(document.getElementById("staffcode").value);
	

}
function selectDepartment(){//选择的时候，需要判断是否重复，重复的值自动过滤
	var results= showModalDialogWin("../PerformanceInputRights/unitmanage.jsp",1000,600);
	initDepartment(results,0);//参数0表示，是点击增加按钮进入的，此时应该判断是否重复
	
}
function showModalDialogWin(url,wh,hg) {
    var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
    return obj;
}

function removeRowPerson(){//删除选中的人员，并且把数据库中相应的内容删除掉
	if (confirm("删除该人员将删除人员及其所有权限，确定删除？")){
		var chkObj=document.getElementsByName("personRadio");

		   var tabObj=document.getElementById("personTable");

		   for(var k=0;k<chkObj.length;k++){

		    if(chkObj[k].checked){
		    	 
		    	 validateDeletePerson(chkObj[k].id);
		    	 tabObj.deleteRow(k+1);
		    	 break;
		    }
		   }
		   clearOrg(document.getElementById("OrgTable"));
	}
	   
}
function removeRowOrg(){//删除选中的部门，不用管数据库中的内容
	
	var chkObj=document.getElementsByName("departmentRadio");

	var tabObj=document.getElementById("OrgTable");
	
	for(var k=0;k<chkObj.length;k++){

		    if(chkObj[k].checked){
	
		     tabObj.deleteRow(k+1);
	
		     k=-1;
	
		    }

	}
	initOrgCodeValue();
	   
}
function initOrgCodeValue(){
	var Orgcode="";
	var chkObj=document.getElementsByName("departmentRadio");
	for(var k=0;k<chkObj.length;k++){
		   Orgcode+=chkObj[k].id+";";
	   }
	   document.getElementById("orgcode").value="";
	   document.getElementById("orgcode").value+=Orgcode;	
}
function clearOrg(tableTemp){//清除全部内容
	var table=tableTemp;
	var length=table.rows.length;
	for(var i=1;i<length;i++){
		table.deleteRow(i);
		length=length-1;
		i=i-1;

	}
	
}
function F8()
{
	if (confirm("确定按选定值设置权限？"))
	{
		 if(document.getElementById("staffcode").value==""||document.getElementById("orgcode").value=="")
		{
			alert("请选择要分配权限的员工和权限");
			return;
		} 
		
		alert(document.getElementById("orgcode").value);
		alert(document.getElementById("staffcode").value);
		document.all("form1").submit();
		
	}
}
function getmember(code){
	alert(code);
}
</script>
<BODY  onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr >
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 绩效考核 &gt;&gt; 录入人员权限设置</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr >
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a><a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  
 <tr height="60%">
<td colspan="3" valign="top" height="100%">
   <table width="100%" height="100%">
    <tr>
    <td width="50%" valign="top" align="left">
    <fieldset style="height:60%;width:60%; ">
    <legend>人员选择</legend>
    <a href="#" onClick="selectPerson()" class="button4">增加</a><a href="#" onClick="removeRowPerson()" class="button4">删除</a></br></br>
	<div style="overflow-x: auto; overflow-y: auto; width:100%;  height: 90%;">
	<table id="personTable" class="gridtable">
	<th>序列号</th><th>员工编码</th><th>姓名</th>
	<%
	if (dt!= null && dt.getRowsCount()>0)
	{
		for (int i=0;i<dt.getRowsCount();i++)
		{
	%>		
			<tr onMouseOver="this.style.backgroundColor='#D0E9ED';this.style.cursor='hand'" onMouseOut="this.style.backgroundColor=''" onClick="validate(this.children[1].innerText,this);" >
			<td><input type='radio' name='personRadio' id='<%=dt.get(i).getString("staffcode")%>'  style='width:20px' /></td>
			<td><%= dt.get(i).getString("staffcode")%></td>
			<td><%= dt.get(i).getString("staffname")%></td>
			
			</tr>
	<%
		}
	}
	%>
	</table>
	</div>
	</fieldset>
	</td>
	<td width="50%" valign="top" align="left">
	<fieldset style="height:60%;width:50%; ">
    <legend>权限选择</legend>
    <a href="#" onClick="selectDepartment()" class="button4">增加</a><a href="#" onClick="removeRowOrg()" class="button4">删除</a></br></br>
	<div style="overflow-x: auto; overflow-y: auto; width:100%;  height: 90%;">
	<table id="OrgTable" class="gridtable">
	<th>序列号</th><th>机构编码</th><th>名称</th>
	</table>
	</div>
	</fieldset>
	</td>
	</tr>
    </table>
      <input name="act" type="hidden" id="act" value="add">
     
      <input name="action_class" type="hidden" id="action_class" value="com.action.system.SysTBM_AdminDeptAction">
      <input name="staffcode" type="hidden" id="staffcode" value="">
      <input name="orgcode" type="hidden" id="orgcode" value="">
     
	</td>   
  </tr>
  <tr >
    <td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
