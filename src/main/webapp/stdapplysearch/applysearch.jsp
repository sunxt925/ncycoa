<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<%@page import="com.entity.stdapply.DocApplyPerson"%>
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
<TITLE>�ϳ��̲�ר����</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	DocApplyPerson applyer=new DocApplyPerson();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=(((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full())-2;
	DataTable dt=applyer.getApplyInfo(page_no,per_page);
	DataTable dtcount=applyer.getAllApplyInfo();
	//System.out.println("dtcount:::::"+dtcount);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
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
function OpenTable(applyid)
{
var url='stdapplysearch/applytable.jsp?applyid='+applyid;
createwindowNoButton('��ҵ��׼�޶������',url,'1000px','550px');
}
</script>
<script type="text/javascript">

</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("���","10%");
		tableutil.setHeadWidth("������","20%");
		tableutil.setHeadWidth("���벿��","20%");
		tableutil.setHeadWidth("�޶�����","20%");
		tableutil.setHeadWidth("��ҵ��׼�޶������","30%");
		/*
		tableutil.setRowCode("�ĵ�������","@�ĵ�������@"+",std_docclass");
		tableutil.setHeadWidth("�ĵ��汾״̬","15%");
		tableutil.setRowCode("�ĵ��汾״̬","@�ĵ��汾״̬@"+",std_versionstatus");
		*/
		tableutil.setDisplayCol("no");
		out.print(tableutil.DrawTable());
	%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
         
          <td align="right">
          <%// <td width="50%">��<a href="#" onClick="F4()">ɾ��</a>����<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          String ccm="";
      	out.print(PageUtil.DividePage(page_no,pagecount,"applysearch.jsp",ccm));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
      </td>
            </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
  </table>
<!--        <div id="tab_menu" style="text-align: center;border:1px "></div>-->
<!--		<div id="page"  style="text-align: center;position: relative;  height:45%; width:100%; border:1px "></div>-->
<!--      <div id="tab_menu" style="text-align: center;position: absolute; top: 49.5%; height:28px; width:100%; border:1px "></div>-->
<!--<div id="page" style="text-align: center;position: absolute; top: 54%; height:45%; width:100%; border:1px "></div>-->
</form>
</BODY>
</HTML>