<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.entity.std.*" errorPage="" %>
<%@page import="com.entity.std.DocMetaVersionInfo"%>
<%@page import="com.entity.stdapply.DocReviseInifo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<HTML>
<HEAD>
<TITLE>�������̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%

	String docno=request.getParameter("docno");
	DocMetaVersionInfo docVersionInfo = new DocMetaVersionInfo(docno);	
	String doccode=docVersionInfo.getDocCode();
		String orgcode=request.getParameter("orgcode");
	if(doccode==null||doccode=="")
	{
		 DocReviseInifo docreviseinfo=new DocReviseInifo(docno);
	     doccode=docreviseinfo.getDocCode();
	     if(doccode!=null&&!(doccode.equals(""))){
				DocOrg docorg=new DocOrg();
				docorg.setDocCode(doccode);
				docorg.setOrgCode(orgcode);
				docorg.setRelation("ֱ��");
				docorg.insert();
		}
	}
	//System.out.println("doccode   :"+doccode);
	DocOrgPost docorgpost = new DocOrgPost();
	int page_no = Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=0;
	String s=request.getParameter("pagelength");
	if(s==null){
		per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-3;	
	}else{
		per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_third()-2;
	}
	String name="";
	DataTable dt=docorgpost.getStdPostList(page_no,per_page,orgcode,doccode);
	DataTable dtcount=docorgpost.getAllStdPostList(orgcode,doccode);
	//if(request.getSession().getAttribute("docversionname")==null){
	name=request.getParameter("docversionname");
	//	request.getSession().setAttribute("docversionname",name);
	//}
	//else{
	//	name=(String)request.getSession().getAttribute("docversionname");
	//}
	//System.out.println("name:"+name);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>

 <script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../jscomponent/tools/outwindow.js"></script>


<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);

	
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
	  	window.location.reload();
}
function F5()
{
	window.location.reload();
}
function F6(orgcode,doccode)
{
 	var newurl='/ncycoa/std/std_orgpost.jsp?orgcode='+orgcode+'&doccode='+doccode;
 	createwindow('���ڲ��Ÿ�λ�б�',newurl,'500px','400px');
  	//window.open(newurl,"attachposlist");
//  	window.showModalDialog(newurl,"dialogWidth=500px;dialogHeight=1000px");
//  	window.location.reload();
}
function F7(orgcode,doccode)
{
 	var newurl='/ncycoa/std/usermanage.jsp?orgcode='+orgcode+'&doccode='+doccode;
 	createwindowNoSave('���в��Ÿ�λ�б�',newurl,'1000px','600px');
  	//window.open(newurl,"attachposlist");
//  	window.showModalDialog(newurl,window,"dialogWidth=1000px;dialogHeight=600px");
 // 	window.location.reload();
}
function dele(recid)
{
			document.getElementById("recid").value=recid;
			//document.form1.docno.value=docno;
		if (confirm("�Ƿ�Ҫɾ�����ĵ�������ĸ�λ��"))
		{
			document.all("Submit").click();
		}
			//window.location.reload();
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white">

<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="table_td_jb_iframe">&nbsp;&nbsp; 
    <a href="#" onClick="F6('<%=orgcode %>','<%=doccode%>')">������λ����������</a>
    <a href="#" onClick="F7('<%=orgcode %>','<%=doccode%>')">������λ��ȫ�֣�</a>
</td>
</tr>
</table>

<table width="100%" height="90%" border="0"  class="main_table_centerbg" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" align="left">
   
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
        <!--
          <td width="50%">��<a href="#" onClick="F4()">ɾ��</a>����<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          --><!--
          <td width="50%" align="left">
                                ��ǰ�ĵ�---<%=name%>
        </td>
          --><td align="right">
          <%
          String para="docno="+docno;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_aboutpost.jsp",para));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="del">
        <input name="docno" type="hidden" id="docno" value="<%=docno%>">
        <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode%>">
        <input name="versionname" type="hidden" id="versionname" value="<%=name %>">
                          <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input name="recid" type="hidden" id="recid" value=""></div></td>
       
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdAboutPostAction"></td>
      </tr>
        <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
      </td>
  </tr>

</form>
</table>
</BODY>
</HTML>