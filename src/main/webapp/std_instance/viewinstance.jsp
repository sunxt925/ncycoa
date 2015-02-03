<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.workflow.service.*"%>
<%@page import="com.workflow.serviceimpl.*"%>
<%@page import="com.workflow.orm.*"%>
<%@page import="com.common.Format"%>
<%@ page import="org.jbpm.api.task.Task"%>
<%@ page import="java.util.zip.ZipInputStream"%>
<%@ page import="org.jbpm.api.*" %>
<%@ page language="java" import="java.util.*,com.db.*,com.common.*,com.entity.system.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">

<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/outwindow.js"></script>
<script language="javascript" src="../js/public/select.js"></script>
</head>
<%
//ProcessEngine pe = Configuration.getProcessEngine();
//ExecutionService es = pe.getExecutionService();
//List<ProcessInstance> lists = es.createProcessInstanceQuery().list();//流程实例列表
	UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
	InstanceServiceImpl instance=new InstanceServiceImpl();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-4;
	DataTable dt=instance.getInstanceList(page_no,per_page,staffcode,"stdflow.png"); 
	DataTable dtcount=instance.getAllInstanceList(staffcode,"stdflow.png");
	int pagecount=(dtcount.getRowsCount()/per_page)+1;
%>
 <script type="text/javascript">
   function view(pId)
{ 
    var url='jbmpworkflow/instancemanage/view.jsp?id='+pId;
    createwindowNoButton('流程跟踪图',url,'800px','600px');
}
function deleteinstance(pid){
var path='<%=path%>';
$.post(path+"/std_instance/deletedo.jsp",
        			    {
        			      pid:pid,
        			    },
        				 function(data,status){
        			    	result(data);
        			    	//window.location.reload();
        			    }); 
}
function result(data){
window.location.reload();
    	  show(data);
    	  closemsgshow();
    	  
    	 // window.location.reload();
}
 </script>
<BODY class="" onLoad="this.focus()"  style="background-color:white" style="height:100%;">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<table width="100%" height="100%" class="maintable">
<tr>
<td colspan="3" valign="top" class="main_table_centerbgtebie" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="30px">
    <tr>
		    <td width="3%" class="main_table_topbg" height="31"><img src="<%=path%>/images/table_lt.jpg" ></td>
		    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt;标准化制修订 &gt;&gt;个人修订查询 </td>
		    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="<%=path%>/images/table_rt.jpg" ></td>
		    </tr>
	</table>
	</td>
  
</tr>
   
    
  
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
      	out.print(PageUtil.DividePage(page_no,pagecount,"viewinstance.jsp",""));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
      </td>
  </tr>

  <!-- <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr> -->

<!--   <div id="tab_menu" style="text-align: center; border:1px "></div>-->
<!--<div id="page" style="text-align: center;position: relative;  height:31%; width:100%; border:1px "></div> -->
<!--	<div id="tab_menu2" style="text-align: center;position: relative; border:1px "></div>-->
<!--	<div id="page2" style="text-align: center;position: relative;  height:31%; width:100%; border:1px "></div> -->


  </table>
  </form>
</BODY>
</html>
