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
<TITLE>南充市烟草局</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String applyid=request.getParameter("applyid");
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_third()-2;
	DocReviseInifo docreviseinfo=new DocReviseInifo();
	DataTable dt=docreviseinfo.getStdListapp(page_no,per_page,applyid);
	DataTable dtcount=docreviseinfo.getAllStdList(applyid);
	int pagecount=(dtcount.getRowsCount()/per_page)+1;
	DocApplyPerson applyperson=new DocApplyPerson(Integer.valueOf(applyid));
	String orgcode=applyperson.getApplyorgcode();
	//request.getSession().setAttribute("pageno",page_no);
	//var belongno= document.getElementById ('items').value;
%>
<script language="javascript" src="<%=request.getContextPath()%>/js/public/select.js"></script>

 <script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../jscomponent/tools/outwindow.js"></script>

<script language="javascript">
function F2(docno)
{
	  var newurl='/ncycoa/stdapply/std_localup.jsp?docNo='+docno;
  	  //window.open(newurl,"stdlist");
  	  window.showModalDialog(newurl,window,"dialogWidth=300px;dialogHeight=150px");
      window.location.reload();
}

function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("父级菜单的删除将级联删除子菜单，是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
}
function F9(docno,docversionname)
{
	  var newurl='/ncycoa/stdapply/std_filelist.jsp?docNo='+docno;
	  var name='文档'+docversionname+'的文件:';
	  window.parent.document.getElementById("url").value=newurl;
      window.parent.document.getElementById("name").value=name;
      window.parent.document.getElementById("hidbutton").click();
  	  //window.open(newurl,"attachposlist");
}
function F5()
{
	window.location.reload();
}

function F6(applyid)
{ 
  var stdupnewurl='/ncycoa/stdapply/std_upnew.jsp?applyid='+applyid;
  //window.open(stdupnewurl,"stdlist");
  window.showModalDialog(stdupnewurl,"dialogWidth=500px;dialogHeight=1000px");
  window.location.reload();
}
function F7(docno,docversionname)
{ 
         var newurl='/ncycoa/stdapply/std_appattachfile.jsp?docNo='+docno+'&docversionname='+docversionname;
     /*    var name='文档'+docversionname+'的附件:';
         
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="std";
         window.parent.document.getElementById("hidbutton2").click();*/
 
  window.open(newurl,"attachlist");
}
function aboutpost(docno,docversionname)
{
		var orgcode=document.getElementById("orgcode").value;
		var height='three';
		var newurl='/ncycoa/std/std_aboutpost.jsp?docno='+docno+'&docversionname='+docversionname+'&orgcode='+orgcode+'&pagelength='+height;
		window.open(newurl,"attachlist");
}
function F1(docno)
{
	var applyid=document.getElementById("applyid").value;
  var stdupnewurl='/ncycoa/stdapply/std_mod.jsp?bm='+docno+'&applyid='+applyid;
    createwindow('修改标准信息',stdupnewurl,'500px','400px');
  //window.open(stdupnewurl,"stdlist");
 // window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=500px");
 // window.location.reload();
}
function dele(docno)
{
			document.getElementById("docno").value=docno;
			//document.form1.docno.value=docno;
		if (confirm("是否要删除文档流水号为"+docno+"的文档？"))
		{
			document.all("Submit").click();
		}
			
}
function OpenFile(storefileno,filecontenttype)
{
    if(filecontenttype=="pdf"){
       var stdupnewurl='/ncycoa/stdapply/localopenpdf.jsp?storefileno='+storefileno;
       window.open(stdupnewurl);
      // window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
       //window.location.reload();
   }else{
            var stdupnewurl='/ncycoa/stdapply/std_officeopen.jsp?storefileno='+storefileno;
     window.open(stdupnewurl);
     //window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
     //window.location.reload();
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
          <td class="table_td_jb">　&gt;&gt;标准列表</td>
      </tr>
</table>

    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("选择","5%");
		tableutil.setHeadWidth("标准编号","15%");
		tableutil.setHeadWidth("标准名称","15%");
		tableutil.setHeadWidth("流程类型","15%");
		tableutil.setHeadWidth("标准正文","15%");
		tableutil.setHeadWidth("操作","35%");
		tableutil.setRowreadLink("标准正文","@标准正文@");
		out.print(tableutil.DrawTable());
	%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
         
          <td align="right">
          <%// <td width="50%">【<a href="#" onClick="F4()">删除</a>】【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          String ccm="applyid="+applyid;
      	out.print(PageUtil.DividePage(page_no,pagecount,"/ncycoa/stdapply/std_list.jsp",ccm));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="appdel">
        <input name="applyid" type="hidden" id="applyid" value="<%=applyid%>">
        <input name="docno" type="hidden" id="docno" value="">
        <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode %>">
          <input type="submit" name="Submit" value="提交" style="display:none"></div></td>
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