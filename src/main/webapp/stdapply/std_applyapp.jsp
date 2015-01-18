<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.entity.stdapply.*,com.emsflow.spi.model.AttributeInstance,org.wfmc.wapi.WMWorkflowException" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%

%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<%
	//System.out.println(bm);
		//UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	//	com.cms.model.sysmng.login.User user = (com.cms.model.sysmng.login.User)request.getSession().getAttribute("USER");
		//		com.emsflow.server.webflow.manage.WebflowServer server = new com.emsflow.server.webflow.manage.WebflowServer(user.getZgdm());
		//		String processInstanceId = server.createProcessInstance("stdApplicationFlow","my_process");
	//	AttributeInstance ApplyId =    server.getParentProcessInstanceAttributeValue(processInstanceId, "applyid");
	String ApplyId=request.getParameter("applyid");
		int applyid=Integer.parseInt(ApplyId.toString());
		DocApplyPerson applyperson=new DocApplyPerson(applyid);
	String staffcode=applyperson.getApplystaffcode();
	String staffname=applyperson.getApplyperson();
	String applyapart=applyperson.getApplyapart();
	String applydate=(applyperson.getApplydate()).substring(0,10);
	String applyreason=applyperson.getApplyreason();
	
	
/*	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_third();
	DataTable dt=orgposition.getOrgPositionListstd(page_no,per_page,bm);  
	DataTable dtcount=orgposition.getAllOrgPositionList(bm);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	String trackName=og.getTrack(bm,"");*/
	//System.out.println(trackName);
	//System.out.println(page_no);
	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language=
                "javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/MyDatePicker/WdatePicker.js">  </script>
<script language="javascript" src="<%=request.getContextPath()%>/js/public/select.js"></script> 


<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab/tab/tab.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../jscomponent/tools/outwindow.js"></script>
<style type="text/css">
@IMPORT url("<%=request.getContextPath()%>/js/tab/tab/tab.css");

h2 {
	background-color: #cccccc;
	padding: 4px;
	font-size: 18px;
	font-family: "黑体";
}
#tab_menu {
	padding: 0px;
	width: 100%;
	height: 30px;
	overflow: hidden;
}
#tab_menu2 {
	padding: 0px;
	width: 100%;
	height: 30px;
	overflow: hidden;
}
	#page {
	width: 100%;
	height: 250px;
	border: solid 1px #cccccc;
	}
		#page2 {
	width: 100%;
	height: 250px;
	border: solid 1px #cccccc;
	}
</style>
<script language="javascript">
var tab=null;
$( function() {
	  	tab = new TabView( {
		containerId :'tab_menu',
		pageid :'page',
		cid :'tab_po',
		position :"top"
	});
		  	tab2 = new TabView( {
		containerId :'tab_menu2',
		pageid :'page2',
		cid :'tab_po2',
		position :"top"
	});
});
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scrollbar=no;help: no;resizable:no;status:no;");
}

function F2()
{
	document.all("reset").click();
}
function F1()
{
	//if (CkEmptyStr(document.all("DocNo"),"层次码不能为空！"))
	//{
		//alert (document.all("act"));
		document.all("form1").submit();
	//}
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
var index1=1;
var index2=300;
function F8(positioncode,positionname,orgcode)
{
		while(index1>1)
		{
			tab.close(index1);
			index1=index1-5;
		}
		while(index2>300)
		{
			tab2.close(index2);
			index2=index2-5;
		}
        var newurl='std_list.jsp?positioncode='+positioncode+'&orgcode='+orgcode;
	    var id=index1+5;
		tab.add( {
		id :id,
		title :name==""?("标签页面"+(index1+1)):positionname,
		url :newurl,
		isClosed :true
	});
	index1=index1+5;
  //window.open(newurl,"attachposlist");
}
function page()
{
var url=document.form1.url.value;
var name=document.form1.name.value;
var id=index1+5;
		tab.add( {
		id :id,
		title :name==""?("标签页面"+(index1+1)):name,
		url :url,
		isClosed :true
	});
		index1=index1+5;
}
function page2()
{
var url=document.form1.url.value;
var name=document.form1.name.value;
var flag=document.form1.flag.value;
if(flag=='std')
{
		while(index2>300)
		{
			tab2.close(index2);
			index2=index2-5;
		}
}
var id=index2+5;
		tab2.add( {
		id :id,
		title :name==""?("标签页面"+(index2+1)):name,
		url :url,
		isClosed :true
	});
		index2=index2+5;
}
function checkradio()
{

	/*		while(index1>1)
		{
			tab.close(index1);
			index1=index1-5;
		}
		while(index2>300)
		{
			tab2.close(index2);
			index2=index2-5;
		}
		var applyid = document.getElementById('applyid').value;
        var newurl='/ncycoa/stdapply/std_list.jsp?applyid='+applyid;
	    var id=index1+5;
		tab.add( {
		id :id,
		title :name==""?("标签页面"+(index1+1)):"标准",
		url :newurl,
		isClosed :true
	});
	index1=index1+5;*/
	var applyid = document.getElementById('applyid').value;
	        var newurl='/ncycoa/stdapply/std_approlist.jsp?applyid='+applyid;
	        window.open(newurl,"stdlist");
        window.open('/ncycoa/std_search/empty.jsp',"attachlist");
}
function appytablebutton()
{
var applyid=document.getElementById("applyid").value;
var applyorg=document.getElementById("applyapart").value;
var applydate=document.getElementById("applydate").value;
var applyreason=document.getElementById("applyreason").value;
var url='/ncycoa/stdapply/applytable2.jsp?applyid='+applyid+'&applyorg='+applyorg+'&applydate='+applydate+'&applyreason='+applyreason;
createwindowNoButton('企业标准修订申请表',url,'1000px','500px');
//window.open(url);
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;" >

<form name="form1" id="form1" method="post" style="background-color:white" action="<%=request.getContextPath()%>/servlet/PageHandler">
<table width="100%" height="20%" border="0" cellpadding="0" cellspacing="0">
       <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;　<a href="#" onClick="F2()">重填[F2]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
       </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">     
      <table width="100%" border="0" cellpadding="3" cellspacing="0">

        <tr>
          <td width="23%" align="right"> 申请人</td>
		  <td width="27%" ><input type="text" name="applyperson" value="<%=staffname %>" id="applyperson" size="30" maxlength="30"><input type="hidden" name="applyid" value="<%=ApplyId %>" id="applyid"></td>
		            <td width="6%" align="right"> 申请理由</td>
		  <td width="44%">
		    <label>
		    <textarea name="applyreason" id="applyreason" value="<%=applyreason %>"  style="width:260px"><%=applyreason%></textarea>
		    </label></td>
        </tr>
        <tr>
          <td width="23%" align="right"> 申请部门</td>
		  <td width="27%"><input type="text" name="applyapart" id="applyapart" value="<%=applyapart %>" size="30" maxlength="60"></td>
        </tr>
		 <tr>
          <td width="23%" align="right"> 申请时间</td>
		  <td width="27%"><input name="applydate" type="text" class="Wdate" id="applydate" onFocus="new WdatePicker(this,null,false,'whyGreen')"  value="<%=applydate %>" size="30" maxlength="30"></td>
		            <td width="6%" align="right">查看标准</td>
		  <td width="5%">
				 <input type="button" name="button0" value="查看" onClick="checkradio()" >&nbsp;&nbsp;&nbsp;<input type="button" name="button0" value="查看申请表" onClick="appytablebutton()" >
		 </td>
        </tr>
      </table>
    <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="appro">
          <input name="url" type="hidden" id="url" value="">
          <input name="name" type="hidden" id="name" value="">
          <input name="flag" type="hidden" id="flag" value="">
          <input type='hidden' name='applystaffcode'  value="<%=staffcode %>">
          <input name="hidbutton" type="button" id="hidbutton" value="" onClick="page();" style="display:none">
          <input name="hidbutton2" type="button" id="hidbutton2" value="" onClick="page2();" style="display:none"></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.stdapply.StdApplyAction"></td>
    </tr>
       </td>
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_rb.jpg" width="10" height="5"></td>
  </tr>
 
  </table>
      		<table width="100%" height="80%" border="0" cellspacing="0" cellpadding="0">
		  <tr >
            <td ><iframe src="" name="stdlist" id="stdlist" width="100%" height="100%" scrolling="no" frameborder="2"></iframe></td>
		  </tr>
		  <tr >
            <td ><iframe src="" name="attachlist" id="attachlist" width="100%" height="100%" scrolling="no" frameborder="2"></iframe></td>
		  </tr>
		</table>
		<%@ include file="/page/controlHead.jsp"%>
<flow:StepChoice table="1" />

</form>
</BODY>
</HTML>


