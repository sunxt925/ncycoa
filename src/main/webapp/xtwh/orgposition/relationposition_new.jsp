<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String level=request.getParameter("level");
	String positioncode=request.getParameter("positioncode");
	String orgcode=request.getParameter("orgcode");
    String positionnames=request.getParameter("positionnames");
    String orgname=request.getParameter("orgname");
    //OrgPosition orgpositionname=new OrgPosition(positioncode,orgcode);
 
	//Position position=new Position();
   // DataTable dt=position.getAllPositionListDrawTable(name);
    OrgPosition orgposition=new OrgPosition(positioncode,orgcode);
    DataTable dt=orgposition.getAllOrgPositionListDrawTable(orgname,positionnames);
    Org og=new Org();
    String trackName="";
    //String trackName=og.getTrack(orgcode,"");
    // String orgname=og.getOrgName(orgcode).get(0).get(1).toString();
    
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script type="text/javascript" src="../../js/public/searchvalue.js"></script>
<script language="javascript">
function searchName()
{
	var orgname=document.getElementById("orgname").value;
	var positionnameing=document.getElementById("positionnameing").value;
	var rand=Math.floor(Math.random()*10000);
	window.open("relationposition_new.jsp?positionnames="+positionnameing+"&orgname="+orgname+"&level=heigher&positioncode=<%=positioncode%>&sid="+rand+"&orgcode=<%=orgcode%>","_self");
	
}



function F8()
{
      
		document.all("Submit").click();
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="main_table_topbg" height="31" COLSPAN=2> ��ǰ������<%=trackName %><%=orgposition.getPositionname()%>&gt;&gt;���������λ��Ϣ</td>
      </tr>
      <tr>
        <td class="main_table_topbg" height="31" align="right">����---��λ��Ϣ</td>
        <td class="main_table_topbg" height="31" align="right">
        �������ƣ�<input name="orgname" type="text" class="input1" id="orgname"  onfocus="cls()" onblur="res()"  value="������ؼ���" size="60" maxlength="300">    
        ��λ���ƣ�<input name="positionnameing" type="text" class="input1" id="positionnameing"  onfocus="cls()" onblur="res()"  value="������ؼ���" size="30" maxlength="30"><a href="#" onClick="searchName()" class="button4">��ѯ</a></td>
      </tr>
    </table>
     <div style="overflow-x: auto; overflow-y: auto; height: 600px;">
    <%
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
	   out.print(tableutil.DrawTable());
	%>
    </div>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%">��<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          <td align="right"><a href="#" onClick="F8()" class="button4">ȷ��</a>
       </td>
          
        </tr>
        
      </table>
      <%}%>
      <tr>
      <td>
         <input type="submit" name="Submit" value="�ύ" style="display:none">
         <input type="txt" name="positioncode" id="positioncode" value="<%=positioncode %>" style="display:none">
         <input type="txt" name="orgcode" id="orgcode" value="<%=orgcode %>" style="display:none">
         <input type="txt" name="level" id="level" value="<%=level %>" style="display:none">
         <input type="txt" name="positionname" id="positionname" value="<%=orgposition.getPositionname() %>" style="display:none">
          </td>
      </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="add"></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitPositionRelationAction"></td>
      <tr>
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>

</BODY>
</HTML>
