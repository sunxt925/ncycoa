<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.entity.std.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String temppath=getServletContext().getRealPath("/")+"UploadTemp";
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
String type=request.getParameter("type");
	String orgcode=request.getParameter("orgcode");
	//String positioncode=request.getParameter("positioncode");
	//Position position=new Position(positioncode);
	//String positionname=position.getPositionName();
	DocOrgPost docorgpost=new DocOrgPost();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	DataTable dt=docorgpost.getStdModList(page_no,per_page,orgcode,type);
	DataTable dtcount=docorgpost.getAllStdModList(orgcode);
	int pagecount=(dtcount.getRowsCount()/per_page)+1;
	//request.getSession().setAttribute("pageno",page_no);
	//var belongno= document.getElementById ('items').value;
%>
<script language="javascript" src="<%=request.getContextPath()%>/js/public/select.js"></script>

<script language="javascript">

function F2(docno)
{
	  var newurl='std_localup.jsp?docNo='+docno;
  	  //window.open(newurl,"stdlist");
  	  window.showModalDialog(newurl,"dialogWidth=300px;dialogHeight=300px");
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

function F5()
{
	window.location.reload();
}

function F6(orgcode)
{
  var stdupnewurl='std_upnew.jsp?orgcode='+orgcode;
  //window.open(stdupnewurl,"stdlist");
  window.showModalDialog(stdupnewurl,"dialogWidth=500px;dialogHeight=1000px");
  window.location.reload();
}
function F7(docno,docversionname)
{ 
         var newurl='std_attachedfile.jsp?docNo='+docno;
         var name='文档'+docversionname+'的附件:';
         
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="std";
         window.parent.document.getElementById("hidbutton2").click();
 
  //window.open(newurl,"attachposlist");
}


function F9(docno,docversionname)
{
	  var newurl='std_filelist.jsp?docNo='+docno;
	  var name='文档'+docversionname+'的文件:';
	  window.parent.document.getElementById("url").value=newurl;
      window.parent.document.getElementById("name").value=name;
      window.parent.document.getElementById("hidbutton").click();
  	  //window.open(newurl,"attachposlist");
}

function F8(docno)
{
		document.getElementById("docno").value=docno;

			document.all("Submit").click();
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white">
<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/servlet/PageHandler">
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
				TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("选择","5%");
		tableutil.setHeadWidth("标准编号","15%");
		tableutil.setHeadWidth("标准名称","15%");
		tableutil.setHeadWidth("编制日期","15%");
		tableutil.setHeadWidth("有无附件","15%");
		tableutil.setHeadWidth("操作","20%");
		tableutil.setHaveAttach("有无附件","@有无附件@");
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%"></td>
          <td align="right">
          <%//String para="orgcode="+orgcode+"&positioncode="+positioncode;
          String para="orgcode="+orgcode;
      	out.print(PageUtil.DividePage(page_no,pagecount,"/ncycoa/stdapply/std_modlist.jsp",para));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="mod">
        <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode%>">

        <input name="docno" type="hidden" id="docno" value="">    
        <input name="temppath" type="hidden" id="temppath" value="<%=temppath %>"> 
        <input type="submit" name="Submit" value="提交" style="display:none"></div></td>
          
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.stdapply.StdModAction"></td>
      </tr>
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_rb.jpg" width="10" height="5"></td>
 <td><input name="type" type="hidden" id="type" value="<%=type %>"> </td>
  </tr>
  </table>
</form>
</BODY>
</HTML>