<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.stdapply.*,com.entity.system.*" errorPage="" %>
<%@page import="com.entity.std.DocMetaVersionInfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%

	String docNo=request.getParameter("docNo");
	String name="";
	DocReviseInifo DocReviseInifo = new DocReviseInifo();
	int page_no = Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	
	//request.getSession().setAttribute("docno",docNo);
	//int page_no=Integer.parseInt(Format.NullToZero(request.getSession().getAttribute("pageno").toString()));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_third()-2;
	DataTable dt=DocReviseInifo.getAttachedStdList(page_no,per_page,docNo);
	DataTable dtcount=DocReviseInifo.getAllAttachedStdList(docNo);
	DataTable dtname=DocReviseInifo.getByDocNo(docNo);
	//name=Format.NullToBlank(dtname.get(0).get(2).toString());
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>
<script language="javascript" src="<%=request.getContextPath()%>/js/public/select.js"></script>


 <script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/tools/stdapplyoutwindow.js"></script>


<script language="javascript">
function F2(docno)
{
	  //var newurl='std_attachlocalup.jsp?docNo='+docno;
  	  //window.open(newurl,"attachposlist");
  	  	  var newurl='/ncycoa/stdapply/std_localup.jsp?docNo='+docno;
  	  	  createwindowUpFile('为附件上传文件',newurl,'300px','200px');
 // 	  window.showModalDialog(newurl,window,"dialogWidth=300px;dialogHeight=300px");
//      window.location.reload();
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
  var stdupnewurl='/ncycoa/stdapply/std_upNewAttached.jsp?docno='+docno;
  createwindow('新增附件信息',stdupnewurl,'500px','340px');
  //window.open(stdupnewurl,"attachposlist");
 //   window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=400px");
//    window.location.reload();
}

function F7(docno,docversionname)
{ 
  var newurl='/ncycoa/stdapply/std_attachedfile.jsp?docNo='+docno;
           var name='附件'+docversionname+'的附件:';
         
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("hidbutton").click();
  //window.open(newurl,"attachposlist");
}
function F9(docno,docversionname)
{
	  var newurl='/ncycoa/stdapply/std_filelist.jsp?docNo='+docno;
	  	  var name='附件'+docversionname+'的文件:';
	  window.parent.document.getElementById("url").value=newurl;
      window.parent.document.getElementById("name").value=name;
      window.parent.document.getElementById("flag").value="";
      window.parent.document.getElementById("hidbutton2").click();
  	  //window.open(newurl,"attachposlist");
}
function F1(docno)
{
  var stdupnewurl='/ncycoa/stdapply/std_modattach.jsp?bm='+docno;
  createwindow('修改附件信息',stdupnewurl,'500px','400px');
  //window.open(stdupnewurl,"stdlist");
//  window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=500px");
//  window.location.reload();
}
function dele(docno)
{
			document.getElementById("docno1").value=docno;
			//document.form1.docno.value=docno;
		if (confirm("是否要删除文档流水号为"+docno+"的附件？"))
		{
			document.all("Submit").click();
		}

}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" scroll="no">
<table width="100%" height="100%" border="0"  cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/servlet/PageHandler">
<!-- <tr><td colspan="3" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">　&gt;&gt;当前文档：<%=name %></td>
      </tr>
  </table>
  </td></tr> -->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td class="table_td_jb_iframe">&nbsp;&nbsp;
         <a href="#" onClick="F6('<%=docNo%>')" >增加附件</a>
      </td>
      </tr>
    </table>
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("选择","5%");
		tableutil.setHeadWidth("附件编号","20%");
		tableutil.setHeadWidth("附件名称","20%");
		tableutil.setHeadWidth("操作","55%");
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">

        <tr>
       <!--  <td width="50%">【<a href="#" onClick="F4()">删除</a>】【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          --> 
       
       <td align="right">
       <%
         String para="docNo="+docNo;
      	out.print(PageUtil.DividePage(page_no,pagecount,"/ncycoa/stdapply/std_attachedfile.jsp",para));
       %>
       </td>
          
        </tr>
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="del">
        <input name="docno" type="hidden" id="docno" value="<%=docNo%>">
                  <input type="submit" name="Submit" value="提交" style="display:none">
          <input name="docno1" type="hidden" id="docno1" value=""></div></td>
       
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.stdapply.StdAttachAction"></td>
      </tr>
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>