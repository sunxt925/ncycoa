<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�ϳ��̲�ר����</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<%
	UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
	StaffInfo staffinfo=new StaffInfo(staffcode);
	OrgPosition orgPosition = new OrgPosition();
	DataTable dTable = orgPosition.getOrgPositionCode(staffcode);//���ظ�Ա����Ӧ�Ļ�������͸�λ���루����᷵�����������ϵļ�¼��
	String orgcode = dTable.get(0).getString("orgcode");
	Org org=new Org(orgcode);
	String orgname=org.getName();
	String srcurl="orgnum.jsp?type=cansee&orgcode="+orgcode;
%>
<script language=
                "javascript" type="text/javascript" src="../js/MyDatePicker/WdatePicker.js">  </script>
<script language="javascript" src="../js/public/select.js"></script> 

<script language="javascript">

function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scrollbar=no;help: no;resizable:no;status:no;");
}
function F6()
{
	var type='cansee';
	var orgcode=document.getElementById("orgcode").value;
	window.open('orgnum.jsp?type='+type+'&orgcode='+orgcode,'sumlist');
}
function F7()
{
	var type='mydraw';
	var orgcode=document.getElementById("orgcode").value;
	window.open('orgnum.jsp?type='+type+'&orgcode='+orgcode,'sumlist');
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;" >

<div id="tab_menu" style="text-align: center;position: absolute; top: 0%; height:5%; width:100%; border:1px ">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="table_td_jb">
      <tr>
          <td width="100%" align="center" >
          <a href="#" onClick="F6()" >�����ſɲ鿴�ı�׼����ͳ��</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="#" onClick="F7()" >�����ű��Ƶı�׼����ͳ��</a>
          </td>
      </tr>
  </table>
  </div>
  <div id="tab_menu" style="text-align: center;position: relative; top: 5%; height:100%; width:100%; border:3px ">
<iframe src="<%=srcurl %>" name="sumlist" id="sumlist" width="100%" height="100%" scrolling="no" frameborder="0"></iframe><
</div><input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode%>">
<div>
</div>
</BODY>
</HTML>
