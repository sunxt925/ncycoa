<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bm=Format.NullToBlank(request.getParameter("unitccm"));
//System.out.println(bm+"dvxvxvxcvx");
if (bm.equals("")) bm="NC";

%>
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<%
	//System.out.println(bm);
	Org og=new Org();
	OrgPosition orgposition=new OrgPosition();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-4;
	DataTable dt=orgposition.getOrgPositionListstd(page_no,per_page,bm);  
	DataTable dtcount=orgposition.getAllOrgPositionList(bm);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	String trackName=og.getTrack(bm,"");
	//System.out.println(trackName);
	//System.out.println(page_no);
	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language="javascript" src="../js/public/select.js"></script> 
<script type="text/javascript" src="../js/tab/jquery.js"></script>
<script type="text/javascript" src="../js/tab/tab/tab.js"></script>

<script language="javascript">
var tab=null;

function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scrollbar=no;help: no;resizable:no;status:no;");
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
function F7(docno,docversionname)
{alert(docversionname);
}
var index1=1;
var index2=300;
function F8(positioncode,positionname,orgcode)
{
	/*	while(index1>1)
		{
			tab.close(index1);
			index1=index1-5;
		}
		while(index2>300)
		{
			tab2.close(index2);
			index2=index2-5;
		}*/
        var newurl='std_list.jsp?positioncode='+positioncode+'&orgcode='+orgcode;
         var name='标准：'+positionname+'的标准';

         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="";
         window.parent.document.getElementById("hidbutton").click();
	/*    var id=index1+5;
		tab.add( {
		id :id,
		title :name==""?("标签页面"+(index1+1)):positionname,
		url :newurl,
		isClosed :true
	});
	index1=index1+5;*/
  	//	 window.open(newurl,"stdlist");
       //  window.open('empty.jsp',"attachlist");
}

</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;" >

<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">　&gt;&gt;当前单位：<%=trackName %>岗位信息</td>
      </tr>
      </table>

  
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("选择","5%");
		tableutil.setHeadWidth("岗位编码","15%");
		tableutil.setHeadWidth("岗位名称","15%");
		tableutil.setHeadWidth("配置人数","15%");
		tableutil.setHeadWidth("备注","30%");
		tableutil.setHeadWidth("操作","20%");
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%"></td>
          <td align="right">
          <%
         String unitccmtemp="&unitccm="+bm;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_orgpostlist.jsp",unitccmtemp));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
      </td>
        <td><div align="right"><input name="act" type="hidden" id="act" value="del">
          <input name="orgcode" type="hidden" id="orgcode" value="<%=bm%>">
          <input name="url" type="hidden" id="url" value="">
          <input name="name" type="hidden" id="name" value="">
          <input name="flag" type="hidden" id="flag" value=""></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitPositionAction"></td>
  </tr>
    <tr>
    <td width="1%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="98%" height="5" class="main_table_bottombg"></td>
    <td width="1%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
 
  </table>


</form>
</BODY>
</HTML>
