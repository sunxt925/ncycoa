<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.OrgPosition" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String orgcode=request.getParameter("unitccm");
String doccode=(String)request.getSession().getAttribute("doccode");
String orgname = request.getParameter("orgname");
String orgclass = request.getParameter("orgclass");


%>
<HTML>
<HEAD>
<TITLE>�ϳ��̲�ר����</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	
	//DBObject db = new DBObject();
	//String sql="select * from base_orgposition where orgcode='"+ orgcode+"' order by orgcode";
	//DataTable dt=db.runSelectQuery(sql);
    OrgPosition op = new OrgPosition();
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=10;
	DataTable dt=op.getPositionList2(page_no,per_page,orgcode);
	DataTable dtcount=op.getAllOrgPositionList(orgcode);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript">
function F8()
{

	if (CheckSelect("form1"))
	{

			document.all("form1").submit();
	}
	else
	{
		alert ("��û��ѡ��Ҫ��ӵĸ�λ��");
	}
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
function F5()
{
	window.location.reload();
}
function getmember(positioncode,positionname)
{
    var rand=Math.floor(Math.random()*10000);
	var newmemberurl='member_list.jsp?positioncode='+positioncode + '&positionname='+positionname+'&orgcode='+'<%=orgcode%>'+'&orgname='+'<%=orgname%>'+'&orgclass='+'<%=orgclass%>';
	window.open(newmemberurl,"memberlist");
}
</script>
<BODY class="mainbody" onLoad="this.focus()">

<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td colspan="3" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">��&gt;&gt;��λ��Ϣ</td>
      </tr>
      <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" id="F8" onClick="F8()">����[F8]</a>����<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  </table>
  </td></tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">

    </table>
      <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>=0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("��λ����","20%");
		tableutil.setHeadWidth("��λ����","20%");
		tableutil.setHeadWidth("��������","10%");
		tableutil.setHeadWidth("��λ˵��","50%");
		out.print(tableutil.DrawTable());
	%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
                 
          <td align="left">��<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          
          <td align="right">
          <%
         String position = "unitccm="+orgcode+"&orgname="+orgname+"&orgclass="+orgclass;
      	out.print(PageUtil.DividePage(page_no,pagecount,"postion_list.jsp",position));
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
          		<tr>
    	<td>
          <input name="act" type="hidden" id="act" value="add"><input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode%>"/>
          <input name="all" type="hidden" id="all" value="all"/>
          <input name="doccode" type="hidden" id="doccode" value="<%=doccode%>"/><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdAboutPostAction"></td>
          </tr>
  </table>
</form>

</BODY>
</HTML>