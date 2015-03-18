<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<%@page import="com.entity.std.DocMetaVersionInfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%

	String docNo=request.getParameter("docNo");
	String docversionname=request.getParameter("docversionname");
	String name="";
	DocMetaVersionInfo docVersionInfo = new DocMetaVersionInfo();
	int page_no = Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	
	//request.getSession().setAttribute("docno",docNo);
	//int page_no=Integer.parseInt(Format.NullToZero(request.getSession().getAttribute("pageno").toString()));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-4;
	DataTable dt=docVersionInfo.getAttachedStdList2(page_no,per_page,docNo);
	DataTable dtcount=docVersionInfo.getAllAttachedStdList(docNo);
	DataTable dtname=docVersionInfo.getByDocNo(docNo);
	name=Format.NullToBlank(dtname.get(0).get(2).toString());
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
function F2(docno)
{
	  //var newurl='std_attachlocalup.jsp?docNo='+docno;
  	  //window.open(newurl,"attachposlist");
  	  	  var newurl='std_localup.jsp?docNo='+docno;
  	  window.showModalDialog(newurl,"dialogWidth=300px;dialogHeight=300px");
      window.location.reload();
}
function F3()
{
	var rand=Math.floor(Math.random()*10000);

	
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("确认删除附件，是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
}
function F5()
{
	window.location.reload();
}
function F6(docno)
{
  var stdupnewurl='std_upNewAttached.jsp?docno='+docno;
  //window.open(stdupnewurl,"attachposlist");
    window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=600px");
    window.location.reload();
}

function F7(docno,docversionname)
{ 
         var newurl='std_attachedfile.jsp?docNo='+docno;
         var name='附件'+docversionname+'的附件:';
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="";
         window.parent.document.getElementById("hidbutton2").click();
}
function F9(docno,docversionname)
{
	  var newurl='std_filelist.jsp?docNo='+docno;
	  var name='附件'+docversionname+'的文件:';
	  window.parent.document.getElementById("url").value=newurl;
      window.parent.document.getElementById("name").value=name;
      window.parent.document.getElementById("flag").value="";
      window.parent.document.getElementById("hidbutton2").click();
}
function OpenFile(storefileno,filecontenttype)
{
    if(filecontenttype=="pdf"){
       var stdupnewurl='ftpopenpdf.jsp?storefileno='+storefileno;
       window.open(stdupnewurl);
      // window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
       //window.location.reload();
   }else if(filecontenttype=="swf"){
          var stdupnewurl='ftpopenswf.jsp?storefileno='+storefileno;
       window.open(stdupnewurl);
   }else{
            var stdupnewurl='std_officeopenCD.jsp?storefileno='+storefileno;
     window.open(stdupnewurl);
     //window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
     //window.location.reload();
    }
}
function F1(docno)
{
  var stdupnewurl='/ncycoa/std_search/std_attachdetail.jsp?bm='+docno;
    createwindowNoButton('附件的信息',stdupnewurl,'450px','300px');
  //window.open(stdupnewurl,"stdlist");
//  window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=600px");
//  window.location.reload();
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white">
<table width="100%" height="100%" border="0" class="main_table_centerbg"  cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	 <tr><td colspan="3" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">　&gt;&gt;文档：<%=docversionname %>的附件列表</td>
      </tr>
  </table>
  </td></tr> 
    </table>
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
				TableUtil tableutil=new TableUtil();
				tableutil.setDt(dt);
		tableutil.setHeadWidth("序号","5%");
		tableutil.setHeadWidth("附件编号","15%");
		tableutil.setHeadWidth("附件名称","20%");
		tableutil.setHeadWidth("编制日期","15%");
		tableutil.setHeadWidth("附件正文","25%");
		tableutil.setHeadWidth("操作","20%");
		tableutil.setRowreadLink("附件正文","@附件正文@");
		tableutil.setDisplayCol("no");
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%"></td>
          <td align="right">
          <%String para="docNo="+docNo;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_attachedfile.jsp",para));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>