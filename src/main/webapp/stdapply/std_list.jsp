<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.entity.stdapply.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//if(unitccm==null||unitccm=="")
//	unitccm=(String)request.getSession().getAttribute("orgcode");
//request.getSession().setAttribute("orgcode",unitccm);
%>
<HTML>
<HEAD>
<TITLE>�ϳ����̲ݾ�</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String applyid=request.getParameter("applyid");
	String orgcode=request.getParameter("orgcode");
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_third()-2;
	DocReviseInifo docreviseinfo=new DocReviseInifo();
	DataTable dt=docreviseinfo.getStdList(page_no,per_page,applyid);
	DataTable dtcount=docreviseinfo.getAllStdList(applyid);
	int pagecount=(dtcount.getRowsCount()/per_page)+1;
	//request.getSession().setAttribute("pageno",page_no);
	//var belongno= document.getElementById ('items').value;
%>
<script language="javascript" src="<%=request.getContextPath()%>/js/public/select.js"></script>


 <script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/tools/outwindow.js"></script>


<script language="javascript">
function F2(docno)
{
	  var newurl='/ncycoa/stdapply/std_localup.jsp?docNo='+docno;
	  createwindowUpFile('Ϊ��׼�ϴ��ļ�',newurl,'300px','200px');
  	  //window.open(newurl,"stdlist");
//  	  window.showModalDialog(newurl,window,"dialogWidth=300px;dialogHeight=150px");
//      window.location.reload();
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
}
function F9(docno,docversionname)
{
	  var newurl='/ncycoa/stdapply/std_filelist.jsp?docNo='+docno;
	  var name='�ĵ�'+docversionname+'���ļ�:';
	  window.parent.document.getElementById("url").value=newurl;
      window.parent.document.getElementById("name").value=name;
      window.parent.document.getElementById("hidbutton").click();
  	  //window.open(newurl,"attachposlist");
}
function aboutpost(docno,docversionname)//�漰��λ
{
		var orgcode=document.getElementById("orgcode").value;
		var height='three';
		var newurl='/ncycoa/std/std_aboutpost.jsp?docno='+docno+'&docversionname='+docversionname+'&orgcode='+orgcode+'&pagelength='+height;
	  var name='�ĵ�'+docversionname+'�Ĺ�����λ:';
	  window.parent.document.getElementById("url").value=newurl;
      window.parent.document.getElementById("name").value=name;
      window.parent.document.getElementById("hidbutton").click();
}
function F5()
{
	window.location.reload();
}

function F6(applyid)
{ 
	var orgcode=document.form1.orgcode.value;
  var stdupnewurl='/ncycoa/stdapply/std_upnew.jsp?applyid='+applyid+'&orgcode='+orgcode;
   createwindow('�±�׼��Ϣ',stdupnewurl,'450px','360px');
  //window.open(stdupnewurl,"stdlist");
//  window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=400px");
//  window.location.reload();
}
function F7(docno,docversionname)
{ 
         var newurl='/ncycoa/stdapply/std_attachedfile.jsp?docNo='+docno;
         var name='�ĵ�'+docversionname+'�ĸ���:';
         
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="std";
         window.parent.document.getElementById("hidbutton2").click();
 
  //window.open(newurl,"attachposlist");
}

function F1(docno)
{
	var applyid=document.getElementById("applyid").value;
  var stdupnewurl='/ncycoa/stdapply/std_mod.jsp?bm='+docno+'&applyid='+applyid;
  createwindow('�޸ı�׼��Ϣ',stdupnewurl,'500px','400px');
  //window.open(stdupnewurl,"stdlist");
//  window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=500px");
//  window.location.reload();
}
function dele(docno)
{
			document.getElementById("docno").value=docno;
			//document.form1.docno.value=docno;
		if (confirm("�Ƿ�Ҫɾ���ñ�׼��"))
		{
			document.all("Submit").click();
		}
			
}

</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" >
<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/servlet/PageHandler">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
<table width="100%" height="6" border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td class="table_td_jb_iframe">&nbsp;&nbsp; 
       <a href="#" onClick="F6('<%=applyid %>')" >�ϴ��±�׼</a>
    </td>
  </tr>
</table>

    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("ѡ��","5%");
		tableutil.setHeadWidth("��׼���","15%");
		tableutil.setHeadWidth("��׼����","15%");
		tableutil.setHeadWidth("��������","15%");
		tableutil.setHeadWidth("����","50%");
		out.print(tableutil.DrawTable());
	%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
         
          <td align="right">
          <%// <td width="50%">��<a href="#" onClick="F4()">ɾ��</a>����<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          String ccm="applyid="+applyid;
      	out.print(PageUtil.DividePage(page_no,pagecount,"/ncycoa/stdapply/std_list.jsp",ccm));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="del">
        <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode%>">
        <input name="applyid" type="hidden" id="applyid" value="<%=applyid%>">
        <input name="docno" type="hidden" id="docno" value="">
          <input type="submit" name="Submit" value="�ύ" style="display:none"></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.stdapply.StdManageAction"></td>
      </tr>
      </td>
            </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_rb.jpg" width="10" height="5"></td>
  </tr>
  </table>
</form>
</BODY>

</HTML>