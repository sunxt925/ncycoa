<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
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
<TITLE>�ϳ��̲�ר����</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
String sortkind=request.getParameter("sortkind");
if(sortkind==null) sortkind="";
String begin=request.getParameter("begin");
if(begin==null) 
	begin="";
String end=request.getParameter("end");
if(end==null) 
	end="";
String type=request.getParameter("type");
if(type==null) 
	type="";
	Org og=new Org();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=(((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-5);
	DataTable dtcount=og.getAllStdListSearch(begin,end,type);
	DataTable dt=og.getStdListSearch(page_no,per_page,begin,end,type,sortkind);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
%>
<script language=
                "javascript" type="text/javascript" src="../js/MyDatePicker/WdatePicker.js">  </script>
                
                
                 <script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../jscomponent/tools/outwindow.js"></script>
                
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript">
function doccodesort(){
	window.parent.document.getElementById("sorttype").value="code";
	window.parent.document.getElementById("sortbutton").click();
}
function datesort(){
	window.parent.document.getElementById("sorttype").value="date";
	window.parent.document.getElementById("sortbutton").click();
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<table width="100%" height="90%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">

    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("�к�","5%");
		tableutil.setHeadWidth("��׼���","10%");
		tableutil.setHeadWidth("��׼����","15%");
		tableutil.setHeadWidth("��������","10%");
		tableutil.setHeadWidth("��/�޸���","12%");
		/*
		tableutil.setRowCode("�ĵ�������","@�ĵ�������@"+",std_docclass");
		tableutil.setHeadWidth("�ĵ��汾״̬","15%");
		tableutil.setRowCode("�ĵ��汾״̬","@�ĵ��汾״̬@"+",std_versionstatus");
		*/
		tableutil.setHeadWidth("����","30%");
		tableutil.setHaveAttach("���޸���","@���޸���@");
		tableutil.setDisplayCol("no");
				tableutil.setSort1("��׼���");
		tableutil.setSortlink1("<a href='#' onClick='doccodesort()' >��׼���</a>");
		tableutil.setSort2("��������");
		tableutil.setSortlink2("<a href='#' onClick='datesort()' >��������</a>");
		out.print(tableutil.DrawTable());
	%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
         
          <td align="right">
          <%// <td width="50%">��<a href="#" onClick="F4()">ɾ��</a>����<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          String ccm="begin="+begin+"&end="+end+"&type="+type;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_list.jsp",ccm));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="searchdel">
                    <input name="url" type="hidden" id="url" value="">
          <input name="name" type="hidden" id="name" value="">
          <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input name="docno" type="hidden" id="docno" value=""></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdManageAction"></td>
      </tr>
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
<script language="javascript">

function F1(docno)
{
  var stdupnewurl='std_allsearch/std_detail.jsp?bm='+docno;
  createwindowNoButton('��׼����Ϣ',stdupnewurl,'400px','400px');
  //window.open(stdupnewurl,"stdlist");
 // window.showModalDialog(stdupnewurl,window,"dialogWidth=400px;dialogHeight=340px");
//  window.location.reload();
}
function dele(docno)
{
			document.getElementById("docno").value=docno;
			//document.form1.docno.value=docno;
		if (confirm("�Ƿ�Ҫɾ���ĵ���ˮ��Ϊ"+docno+"���ĵ���"))
		{
			document.all("Submit").click();
		}
		//window.parent.document.getElementById("search2").click();
			
}
</script>
</BODY>
</HTML>