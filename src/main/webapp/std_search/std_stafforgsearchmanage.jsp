<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE>南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
 <style type="text/css">
        #main_div{
         
        }
        #top_div{
        height: 1px;
         
        }
        #bottom_div{
         
          
        }
        iframe{
          border: 0;
          scrolling:no;
        }
    </style>
</HEAD>
<% 
    UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
	Staff staff = new Staff();
	DataTable dt = staff.getAllOrgByStaffCode(staffcode); 
	String pageheight=UserInfo.getAvalilheight();
	int pageHeight=(int)(Integer.parseInt(pageheight)*0.75);
	
%>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">

function F5()
{
	window.location.reload();
}

function changetopunit(unitccm)
{


	var newlisturl='std_orgposttab.jsp?unitccm='+unitccm;
	//var newtreeurl='../tree/unit_tree.jsp?pageurl=../std_allunitsearch/std_orgpostlist.jsp&pagetarget=postlist&unitccm='+unitccm;
	window.open(newlisturl,'postlist');
	//window.open(newtreeurl,'unittree')
}
</script>
<body>
  <div id="main_div">
   <div  id="top_div">
             <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="3%" class="main_table_topbg" height="31"><img src="<%=request.getContextPath() %>/images/table_lt.jpg" width="22" height="31"></td>
                    <td width="94%" valign="middle" class="main_table_topbg" height="31"> 首页 &gt;&gt; 标准化管理 &gt;&gt; 部门标准查询 </td>
                    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="<%=request.getContextPath() %>/images/table_rt.jpg" width="22" height="31"></td>
                 </tr>
            </table>

      </div>
      <div id="bottom_div">
            <table width="100%" height="94%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                  <td width="20%"> 
                 <iframe src="stafforg_left.jsp" width="100%" height="90%"></iframe>
                 </td>
               <td width="80%" valign="top" height="<%=pageHeight%>px">
               <iframe src="" name="postlist" id="postlist" width="99%" height="100%" scrolling="no" frameborder="0"></iframe></td>
                   </tr>
            </table> 
      </div>
        
  </div>
 
  </body>
</HTML>