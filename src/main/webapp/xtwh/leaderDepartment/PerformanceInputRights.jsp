<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,java.util.ArrayList,com.entity.system.*,com.dao.system.*"  errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<%
	//��ʼ���������
	TBM_AdminDept  tbm=new TBM_AdminDept();
	DataTable dt=tbm.getMemberList();
%>
<script type="text/javascript" src="../../js/jquery-2.0.3.min.js"></script></HEAD>

<script language="javascript" src="../../js/public/select.js"></script>



<script language="javascript">

var xmlHttp;

function createXMLHttpRequest() {
	//��ʾ��ǰ���������ie,��ns,firefox
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
		//����Ajax���Ķ���XMLHttpRequest
		createXMLHttpRequest();
		//alert(id);
		var url = "staffcode_orgcode.jsp?staffcode="+value+"&time=" + new Date().getTime();
		
		//��������ʽΪGET�����������URL������Ϊ�첽�ύ
		xmlHttp.open("GET", url, true);
		
		//��������ַ���Ƹ�onreadystatechange����
		//�����ڵ绰����
		xmlHttp.onreadystatechange=callback;
		
		//��������Ϣ���͵�Ajax����
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
	//Ajax����״̬Ϊ�ɹ�
	if (xmlHttp.readyState == 4) {
		//HTTPЭ��״̬Ϊ�ɹ�
		
		if (xmlHttp.status == 200) {
			str=xmlHttp.responseText;
			initDepartment(str,1);
			
		}else {
			alert("����ʧ�ܣ�������=" + xmlHttp.status);
		}
	}
}
function validateDeletePerson(value) {
	//alert(value);
	 if (value!=null) {
		//����Ajax���Ķ���XMLHttpRequest
		createXMLHttpRequest();
		//alert(id);
		var url = "clear_staff.jsp?staffcode="+value+"&time=" + new Date().getTime();
		//alert(url);
		//��������ʽΪGET�����������URL������Ϊ�첽�ύ
		xmlHttp.open("GET", url, true);
		
		//��������ַ���Ƹ�onreadystatechange����
		//�����ڵ绰����
		xmlHttp.onreadystatechange=callbackdeletePerson;
		
		//��������Ϣ���͵�Ajax����
		xmlHttp.send(null);
	} else {
		//document.getElementById("spanUserId").innerHTML = "";
	}	
}
function callbackdeletePerson() {
	var str;
	
	//Ajax����״̬Ϊ�ɹ�
	if (xmlHttp.readyState == 4) {
		//HTTPЭ��״̬Ϊ�ɹ�
		
		if (xmlHttp.status == 200) {
			str=xmlHttp.responseText;
			if(str=="true"){
				alert("ɾ���ɹ�");
				clearOrg();
				initOrgCodeValue();
				}
				
			else{
				alert("ɾ��ʧ��");
				clearOrg();
				initOrgCodeValue();
			}
				
			
		}else {
			alert("����ʧ�ܣ�������=" + xmlHttp.status);
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
	var rowsNum=table.rows.length;//��ȡ��ǰ����
	var result=results.split(";");
	
	for(var i=0;i<result.length-1;i++){
		flag=0;
		var staffcode=result[i].split(",");
		for(var j=1;j<rowsNum;j++){//�ж��Ƿ��ظ������򲻽���table����
			if(table.rows[j].cells[1].innerText==staffcode[0]){
				flag=1;
				break;
			}
		} 
		if(flag==1)
			continue;
		var newRow = table.insertRow(rowsNum); //��������
		newRow.onmouseover=function(){
			this.style.backgroundColor='#D0E9ED';
			this.style.cursor='hand';
		};
		newRow.onmouseout=function(){
			this.style.backgroundColor='';
		};
		
		 newRow.onclick=function(){
			//�¼���ʾ������Ȩ��
			validate(this.children[1].innerText,this);
		}; 
		//��ÿһ����ӵ���¼�
		var newCell1 = newRow.insertCell(0); //�����µ�Ԫ��
		newCell1.innerHTML="<input type='radio' name='personRadio' id='"+staffcode[0]+"' style='width:20px' />";
		var newCell2 = newRow.insertCell(1); //�����µ�Ԫ��
		newCell2.innerText=staffcode[0];
		var newCell3 = newRow.insertCell(2); //�����µ�Ԫ��
		newCell3.innerText = staffcode[1]; //��Ԫ���ڵ�����
		
		
		
		//newCell.setAttribute("align","left"); //����λ��
	}
	
	//document.getElementById("staffcode").value=staffcodes;
	//alert(document.getElementById("staffcode").value);
	

}
function initDepartment(value,first){
	
	//var result =window.showModalDialog("systemrole_member_new.jsp",window,"scroll=no;status=no;dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;");
	var results= value;
	var table=document.getElementById("OrgTable");
	var rowsNum=table.rows.length;//��ȡ��ǰ����
	var flag=0;
	var result=results.split(";");
	
	for(var i=0;i<result.length-1;i++){
		flag=0;
		var orgcode=result[i].split(",");
		if(first!=1){//���ǵ�һ�ν��룬������ж�
			for(var j=1;j<rowsNum;j++){//�ж��Ƿ��ظ������򲻽���table����
				if(table.rows[j].cells[1].innerText==orgcode[0]){
					flag=1;
					break;
				}
			} 
		}
		 
		if(flag==1)
			continue;
		var newRow = table.insertRow(rowsNum); //��������
		newRow.onmouseover=function(){
			this.style.backgroundColor='#D0E9ED';
			
		};
		newRow.onmouseout=function(){
			this.style.backgroundColor='';
		};
		
		 
		//��ÿһ����ӵ���¼�
		var newCell1 = newRow.insertCell(0); //�����µ�Ԫ��
		newCell1.innerHTML="<input type='radio' name='departmentRadio' id='"+orgcode[0]+"' style='width:20px' />";
		var newCell2 = newRow.insertCell(1); //�����µ�Ԫ��
		newCell2.innerText=orgcode[0];
		var newCell3 = newRow.insertCell(2); //�����µ�Ԫ��
		newCell3.innerText = orgcode[1]; //��Ԫ���ڵ�����
		
		
		//newCell.setAttribute("align","left"); //����λ��
	}
	initOrgCodeValue();
	//document.getElementById("orgcode").value+=orgcodes;
	//alert(document.getElementById("staffcode").value);
	

}
function selectDepartment(){//ѡ���ʱ����Ҫ�ж��Ƿ��ظ����ظ���ֵ�Զ�����
	var results= showModalDialogWin("../PerformanceInputRights/unitmanage.jsp",1000,600);
	initDepartment(results,0);//����0��ʾ���ǵ�����Ӱ�ť����ģ���ʱӦ���ж��Ƿ��ظ�
	
}
function showModalDialogWin(url,wh,hg) {
    var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
    return obj;
}

function removeRowPerson(){//ɾ��ѡ�е���Ա�����Ұ����ݿ�����Ӧ������ɾ����
	if (confirm("ɾ������Ա��ɾ����Ա��������Ȩ�ޣ�ȷ��ɾ����")){
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
function removeRowOrg(){//ɾ��ѡ�еĲ��ţ����ù����ݿ��е�����
	
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
function clearOrg(tableTemp){//���ȫ������
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
	if (confirm("ȷ����ѡ��ֵ����Ȩ�ޣ�"))
	{
		 if(document.getElementById("staffcode").value==""||document.getElementById("orgcode").value=="")
		{
			alert("��ѡ��Ҫ����Ȩ�޵�Ա����Ȩ��");
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
    <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ &gt;&gt; ��Ч���� &gt;&gt; ¼����ԱȨ������</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr >
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a><a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  
 <tr height="60%">
<td colspan="3" valign="top" height="100%">
   <table width="100%" height="100%">
    <tr>
    <td width="50%" valign="top" align="left">
    <fieldset style="height:60%;width:60%; ">
    <legend>��Աѡ��</legend>
    <a href="#" onClick="selectPerson()" class="button4">����</a><a href="#" onClick="removeRowPerson()" class="button4">ɾ��</a></br></br>
	<div style="overflow-x: auto; overflow-y: auto; width:100%;  height: 90%;">
	<table id="personTable" class="gridtable">
	<th>���к�</th><th>Ա������</th><th>����</th>
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
    <legend>Ȩ��ѡ��</legend>
    <a href="#" onClick="selectDepartment()" class="button4">����</a><a href="#" onClick="removeRowOrg()" class="button4">ɾ��</a></br></br>
	<div style="overflow-x: auto; overflow-y: auto; width:100%;  height: 90%;">
	<table id="OrgTable" class="gridtable">
	<th>���к�</th><th>��������</th><th>����</th>
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
