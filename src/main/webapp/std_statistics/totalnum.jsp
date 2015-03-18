<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
//if(unitccm==null||unitccm=="")
//	unitccm=(String)request.getSession().getAttribute("orgcode");
//request.getSession().setAttribute("orgcode",unitccm);
if (unitccm.equals("")) unitccm="NC";
%>
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String type=request.getParameter("type");
	Org og=new Org();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=(((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full())-2;
	DataTable dt=og.getStdListStatistics(page_no,per_page,type);
	int dtcount=og.getAllStdListStatistics(type);
	//System.out.println("dtcount:::::"+dtcount);
	int pagecount=0;
	if(dtcount%per_page==0)
	    pagecount=dtcount/per_page;
	else
		pagecount=(dtcount/per_page)+1;
	//request.getSession().setAttribute("pageno",page_no);
	//var belongno= document.getElementById ('items').value;
%>
<script language=
                "javascript" type="text/javascript" src="../js/MyDatePicker/WdatePicker.js">  </script>
                
 <script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../jscomponent/tools/outwindow.js"></script>
                
<script language="javascript" src="../js/public/select.js"></script>

<script type="text/javascript" src="../js/tab/tab/tab.js"></script>


<script language="javascript">

</script>
<script type="text/javascript">

</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<div style="width: 100%">
<table width="100%" height="80%" border="0" cellpadding="0" cellspacing="0">

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("序号","10%");
		tableutil.setHeadWidth("部门","18%");
		tableutil.setHeadWidth("标准总数","18%");
		tableutil.setHeadWidth("工作标准","18%");
		tableutil.setHeadWidth("管理标准","18%");
		tableutil.setHeadWidth("技术标准","18%");
		/*
		tableutil.setRowCode("文档类名称","@文档类名称@"+",std_docclass");
		tableutil.setHeadWidth("文档版本状态","15%");
		tableutil.setRowCode("文档版本状态","@文档版本状态@"+",std_versionstatus");
		*/
		tableutil.setDisplayCol("no");
		out.print(tableutil.DrawTable());
	%>
      <%}%>
      </td>
            </tr>
  </table>
  </div>
     <div align="right">
   <input id="btnPrint" type="button" value="打印" onclick="javascript:window.print();"/>
  </div>
<!--        <div id="tab_menu" style="text-align: center;border:1px "></div>-->
<!--		<div id="page"  style="text-align: center;position: relative;  height:45%; width:100%; border:1px "></div>-->
<!--      <div id="tab_menu" style="text-align: center;position: absolute; top: 49.5%; height:28px; width:100%; border:1px "></div>-->
<!--<div id="page" style="text-align: center;position: absolute; top: 54%; height:45%; width:100%; border:1px "></div>-->
</form>
</BODY>
</HTML>