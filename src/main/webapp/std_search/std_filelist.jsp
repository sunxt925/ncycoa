<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.entity.std.*" errorPage="" %>
<%@page import="com.entity.std.DocMetaVersionInfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%

	String docno=request.getParameter("docNo");
	DocStoreFile docstorefile = new DocStoreFile();	
	//String doccode=docVersionInfo.getDocCode();
	//request.getSession().setAttribute("doccode",doccode);
	//System.out.println("doccode   :"+doccode);
	//String orgcode=(String)request.getSession().getAttribute("orgcode");
	//DocOrgPost docorgpost = new DocOrgPost();
	int page_no = Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_third();
	DocMetaVersionInfo versioninfo=new DocMetaVersionInfo(docno);
	String name=versioninfo.getDocVersionName();
	DataTable dt=docstorefile.getFileList(page_no,per_page,docno);
	DataTable dtcount=docstorefile.getAllFileList(docno);
/*	if(request.getSession().getAttribute("docversionname")==null){
		name=request.getParameter("docversionname");
		request.getSession().setAttribute("docversionname",name);
	}
	else{
		name=(String)request.getSession().getAttribute("docversionname");
	}*/
	//System.out.println("name:"+name);
		int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);

	
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
function F6(docno)
{
 	var newurl='std_filelist.jsp?docno='+docno;
  	window.open(newurl,"attachposlist");
}
function office(storefileno,filecontenttype)
{
   //alert(storefileno+'     '+filecontenttype);
   if(filecontenttype=="pdf"){
       var stdupnewurl='ftpopenpdf.jsp?storefileno='+storefileno;
       window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
       window.location.reload();
   }else{
            var stdupnewurl='std_officeopen.jsp?storefileno='+storefileno;
     //window.open(stdupnewurl,"stdlist");
     window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
     window.location.reload();
    }
}

function pdf(storefileno,filecontenttype)
{
  //alert(storefileno+'     '+filecontenttype);
  var url='std_localaspdf.jsp?storefileno='+storefileno;
  //window.open(stdupnewurl,"stdlist");
  //window.showModalDialog(url,window,"dialogWidth=1500px;dialogHeight=800px");
  //window.location.reload();
  //window.open(url);
  if(filecontenttype=="pdf"){
    		office(storefileno,filecontenttype);	
  }
  else{
  			//window.open(url);
			document.getElementById("iframe1").src =url;
  }
}

</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>当前文档：<%=name %></td>
      </tr>
    </table>
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
				TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%"></td>
          <td align="right">
          <%
          String para="docNo="+docno;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_filelist.jsp",para));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="del">
        <input name="docno" type="hidden" id="docno" value="<%=docno%>"></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdFileAction"></td>
      <tr>
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
  </table>
              <div style="width: 0px; height: 0px; overflow: hidden;">
        <iframe id="iframe1" name="iframe1" src="" height="0"></iframe>
    </div>
</form>
</BODY>
</HTML>
