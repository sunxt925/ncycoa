<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
//System.out.println(unitccm+"dvxvxvxcvx");
%>
<HTML>
<HEAD>
<TITLE>�����еط�˰���</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<link rel="stylesheet" type="text/css" href="../../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="../../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../../jscomponent/easyui/jquery.easyui.min.js"></script>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	//System.out.println(request.getParameter("unitccm"));
	SystemModule og=new SystemModule();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=20;
	DataTable dt=og.getMenuList(page_no,per_page,unitccm);
	DataTable dtcount=og.getAllOrgList(unitccm);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	//String res=og.getTrack(unitccm,"");
	//System.out.println(name);
	//System.out.println(page_no);
	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language="javascript" src="../../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
    showModalDialogWin("menu_new.jsp?unitccm="+"<%=unitccm%>",1000,600);
    window.location.reload();
    parent.menutree.location.reload();

}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
          
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
function F1(level_code){
    showModalDialogWin("menu_mod.jsp?bm="+level_code,490,500);
  window.location.reload();
    parent.menutree.location.reload();
}
function F6(aa){
if (confirm("ȷ��Ҫɾ����"))
		{
	
document.getElementById("item").value=aa;
document.all("form1").submit();
}
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      </tr>
      <tr>
      <td class="table_td_jb_iframe"><a href="#" onClick="F3()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">&nbsp;&nbsp;���ӱ���˵�[F3]</a><!--��<a href="#" onClick="F4()">ɾ��[F4]</a></td>-->
      </tr>
    </table>
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>=0) {
		TableUtil tu=new TableUtil();
		tu.setDt(dt);
		tu.setCheckBoxName("ѡ��");
								tu.setCheckBoxValue("�����");
								tu.setHeadWidth("ѡ��", "10");
								//tu.setHeadAlign("ʵ�����","left");
								//tu.setLineAction("onclick=\"alert ('@ʵ�����@====@ʵ������@')\"");
								tu.setRowColor("�����", "FF0000");
			  					tu.setColumnAlign("�����", "left");
								tu.setRowLink("�����", "aa.jsp?a=@�����@====@�˵�����@");
								tu
										.setRowValue(
												"����",
												"<a href=\"#\" onclick=\"F1('@level_code@')\" class=\"button4\">�� ��</a><a href=\"#\" onclick=\"F6('@level_code@')\" class=\"button4\">ɾ��</a>");
								tu.setDisplayCol("level_code,rn");
	   out.print(tu.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%"></td>
          <td align="right">
          <%
          String unitccmtemp="&unitccm="+unitccm;
      	  out.print(PageUtil.DividePage(page_no,pagecount,"unit_list.jsp",unitccmtemp));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"><input name="act" type="hidden" id="act" value="del"> <input name="ParentOrgCode" type="hidden" id="ParentOrgCode" value="<%=unitccm%>"> <input name="item" type="hidden" id="item" value=""><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemModuleAction"> <input type="hidden" id="entity" name="entity" value="SYSTEM_MENU"/></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>