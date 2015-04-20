<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<script type="text/javascript" src="timepicker-v0.2.1/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="timepicker-v0.2.1/js/jquery-ui-1.7.2.custom.min.js"></script>
</HEAD>
<%
    String  roomid=request.getParameter("bm");
   // System.out.println(roomid);
    MeetingInfo role=new MeetingInfo();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=10;
	DataTable dt=role.getRoomList(page_no,per_page,roomid);
	DataTable dtcount=role.getAllRoomList(roomid);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
%>
<% ComponentUtil cu=new ComponentUtil(); %>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">
/*var xmlHttp;  
    var flag;  
    function createXMLHttp(){  
        if (window.XMLHttpRequest){                     // 创建XMLHttpRequest核心对象  
            xmlHttp = new XMLHttpRequest()                  // 使用FireFox内核  
        }else {  
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");   // 使用IE内核的浏览器  
        }  
    }  
  
    function checkUserid(userid){       // 显示信息  
        createXMLHttp();    //  
        // 设置请求,通过地址重写方式将userid传递到JSP中  
  
        xmlHttp.open("POST","../servlet/CheckServlet?id="+userid, true);;  
        // 设置完请求后调用处理回调函数  
        xmlHttp.onreadystatechange = checkUseridCallback;  
        xmlHttp.send(null); // 发送请求，不设置参数  
        //document.getElementById("msg").innerHTML = "正在验证……";  
               // alert("*******");  
  
    }  
  
    function checkUseridCallback(){ // 回调函数  
        if (xmlHttp.readyState == 4){           // 数据返回完毕  
            if (xmlHttp.status == 200){         // HTTP操作正常   
 
                var text = xmlHttp.responseText;// 接收返回内容  
                out.println(text);
              
                    document.getElementById("div").innerHTML = "sdsadsadas";  
            }  
        }  
    }  
  
    function checkForm(){   // 对表单判断能否进行提交操作  
        return flag;  
    }  */
    
    /* $(function () {
        $.ajax({
            url: 'xxxxxxxx.do', //servlet的url，注意要组合成table的html然后返回就行了
            cache: false,
            dateType: 'html',
            success: function (data) { $('#dv').html(data); },
            error: function (xhr) { alert('servlet出错\n'+xhr.status+'\n'+xhr.responseText); }
        });
    });*/
   /* $(function () {
$('#sel').change(function () {
    var userid=$('#sel').val();
           $.ajax({
           type: "POST",
            url: '../servlet/CheckServlet', //servlet的url，注意要组合成table的html然后返回就行了
            data:{userid:userid},
            cache: false,
            dateType: 'html',
            success: function (data) { $('#dv').html(data); },
            error: function (xhr) { alert('servlet出错\n'+xhr.status+'\n'+xhr.responseText); }
        });

})
}) 
   
    */
    
  function checkUserid(){ 
  //alert(document.all("menu1").value);
  window.location.href="meetingroom.jsp?bm="+document.all("menu1").value;
    }
function F3()
{
	showModalDialogWin("meeting_new.jsp",490,500);
	window.location.reload();
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("删除角色，是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
          
}
function F5()
{
	window.location.reload();
}
function F1(rolecode){
showModalDialogWin("meeting_audit.jsp?bm="+rolecode,490,500);
	window.location.reload();

}
function F6(aa){
if (confirm("删除会议，是否继续？"))
		{
			document.getElementById("item").value=aa;
document.all("form1").submit();
		}
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <form name="form1" id="form1" method="post" action="../servlet/PageHandler">
   <tr>
      <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
      <td width="94%" valign="middle" class="main_table_topbg" height="31">首页&gt;&gt;会议管理  &gt;&gt;会议基本查询  </td>
      <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
    </tr>
    <tr><td colspan="3" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">会议室：
          <select  name="menu1"  onChange="checkUserid()"   >
          <option value="">==请选择==</option>
            <%
		  	    DBObject db=new DBObject();
				String sql_yhz="select * from COM_MEETINGROOM";
				DataTable dt_yhz=db.runSelectQuery(sql_yhz);
				if (dt_yhz!=null && dt_yhz.getRowsCount()>0)
				{
					for (int j=0;j<dt_yhz.getRowsCount();j++)
					{
						DataRow r=dt_yhz.get(j);
						//out.print("<option id='"+r.getString("role_id")+"' value='privillege.jsp?role_id="+r.getString("role_id")+"'>"+r.getString("role_name")+"</option>");
						out.print("<option  value='"+r.getString("ROOMID")+"'>"+r.getString("ROOMNAME")+"</option>");
					}
				}
			%>
          </select></td>
      </tr>
  </table>
  </td></tr>
	<tr>
	  
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <tr>
          </table>
   <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>=0) {
		TableUtil tu=new TableUtil();
    		tu.setDt(dt);
    		tu.setRowCode("审核情况","@审核情况@"+",mflag");
    		tu.setRowCode("会议开会标志","@会议开会标志@"+",Finishflag");
    		tu.setRowCode("开会地点","@开会地点@"+",mroom");
    	   tu.setRowCode("需求部门","@需求部门@"+",BASE_ORG,ORGCODE,ORGNAME");
    		
    	   out.print(tu.DrawTable());
	%>
    <%}%>
      <input name="act" type="hidden" id="act" value="del">
       <input name="item" type="hidden" id="item" value="">
       <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingAction">
        <input type="hidden" id="entity" name="entity" value="COM_MEETINGINFO"/>
        <table width="100%" border="0" cellpadding="3" cellspacing="0" >
         <tr>
           <td width="51%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
           <td width="49%" align="right"><%
          String unitccmtemp="bm="+roomid;
      	out.print(PageUtil.DividePage(page_no,pagecount,"meetingroom.jsp",unitccmtemp));
       %>           </td>
         </tr>
         <tr>
           <td valign="top" height="100%">&nbsp;</td>
         </tr>
       </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>

</BODY>
</HTML>
