<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));

if (unitccm.equals("")) unitccm="NC";
%>
<HTML>
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
String doccode=(String)request.getSession().getAttribute("doccode");
	Org og=new Org();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=200;
	DataTable dt=og.getOrgList2(page_no,per_page,unitccm);
	DataTable dtcount=og.getAllOrgList(unitccm);
	//System.out.println(dtcount.getRowsCount()+"nihaoasdjfhkjasdhfjkh");
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	//String res=og.getTrack(unitccm,"");
	String res="";

	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language="javascript" src="<%=path%>/js/public/select.js"></script>


 <script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>



<script language="javascript">


function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("�������������Ҳ������������������ĸ�λ���Ƿ������"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("��û��ѡ��Ҫ��ӵĻ�����");
	}
}

function dele(orgcode)
{
//alert(orgcode);
document.getElementById("org").value=orgcode
			//document.getElementsByName(orgcode).checked="checked";
			//document.form1.docno.value=docno;
			if (confirm("ȷ��Ҫɾ����"))
			{
				document.all("Submit").click();
			}
			//mainbody
			//window.location.reload();
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table class="maintable"  width="100%" height="100%">
<form name="form1" id="form1" method="post" action="<%=path%>/servlet/PageHandler">
 <tr>
 <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">��ǰ��λ��<%=res %></td>
      </tr>
      <tr>
      <td><a href="#" onClick="F4()" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" >���ӱ������</a></td>
      </tr>
    </table>
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		/*TableUtil tu = new TableUtil();
								//DBObject db2=new DBObject();
								tu.setDt(dt);
								tu.setCheckBoxName("ѡ��");
								tu.setCheckBoxValue("��������");
								
					
								tu.setRowValue("����","<a href=\"#\" onclick=F1('@��������@') class=\"button4\">�� ��</a><a href=\"#\" onClick=dele('@��������@') class=\"button4\">ɾ��</a>'");
								//tu.setRowValue("��ѯ���","<%=db2.getClob(\"query_baseinfo\", \"querysql\" ,\"queryid=\'@queryid@'\")"+"%"+">");
								out.print(tu.DrawTable()); */
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
	   out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
         <td>��<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          
          <td align="right">
          <%
          String unitccmtemp="&unitccm="+unitccm;
      	out.print(PageUtil.DividePage(page_no,pagecount,"unit_list.jsp",unitccmtemp));
       %>
       </td>
       <td>
       <input type="submit" name="Submit" value="�ύ" style="display:none">
           <input name="act" type="hidden" id="act" value="add">
          <input name="all" type="hidden" id="all" value="allorg"/>
          <input name="doccode" type="hidden" id="doccode" value="<%=doccode%>"/><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdAboutPostAction">
          </td>
        </tr>
        
      </table>
      <%}%>
     
         
      
</td>
  </tr>
</form>
</table>
</BODY>
</HTML>