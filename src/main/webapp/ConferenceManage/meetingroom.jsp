<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�����еط�˰���</TITLE>
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
        if (window.XMLHttpRequest){                     // ����XMLHttpRequest���Ķ���  
            xmlHttp = new XMLHttpRequest()                  // ʹ��FireFox�ں�  
        }else {  
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");   // ʹ��IE�ں˵������  
        }  
    }  
  
    function checkUserid(userid){       // ��ʾ��Ϣ  
        createXMLHttp();    //  
        // ��������,ͨ����ַ��д��ʽ��userid���ݵ�JSP��  
  
        xmlHttp.open("POST","../servlet/CheckServlet?id="+userid, true);;  
        // �������������ô���ص�����  
        xmlHttp.onreadystatechange = checkUseridCallback;  
        xmlHttp.send(null); // �������󣬲����ò���  
        //document.getElementById("msg").innerHTML = "������֤����";  
               // alert("*******");  
  
    }  
  
    function checkUseridCallback(){ // �ص�����  
        if (xmlHttp.readyState == 4){           // ���ݷ������  
            if (xmlHttp.status == 200){         // HTTP��������   
 
                var text = xmlHttp.responseText;// ���շ�������  
                out.println(text);
              
                    document.getElementById("div").innerHTML = "sdsadsadas";  
            }  
        }  
    }  
  
    function checkForm(){   // �Ա��ж��ܷ�����ύ����  
        return flag;  
    }  */
    
    /* $(function () {
        $.ajax({
            url: 'xxxxxxxx.do', //servlet��url��ע��Ҫ��ϳ�table��htmlȻ�󷵻ؾ�����
            cache: false,
            dateType: 'html',
            success: function (data) { $('#dv').html(data); },
            error: function (xhr) { alert('servlet����\n'+xhr.status+'\n'+xhr.responseText); }
        });
    });*/
   /* $(function () {
$('#sel').change(function () {
    var userid=$('#sel').val();
           $.ajax({
           type: "POST",
            url: '../servlet/CheckServlet', //servlet��url��ע��Ҫ��ϳ�table��htmlȻ�󷵻ؾ�����
            data:{userid:userid},
            cache: false,
            dateType: 'html',
            success: function (data) { $('#dv').html(data); },
            error: function (xhr) { alert('servlet����\n'+xhr.status+'\n'+xhr.responseText); }
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
		if (confirm("ɾ����ɫ���Ƿ������"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("��û��ѡ��Ҫɾ�������ݣ�");
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
if (confirm("ɾ�����飬�Ƿ������"))
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
      <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ&gt;&gt;�������  &gt;&gt;���������ѯ  </td>
      <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
    </tr>
    <tr><td colspan="3" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">�����ң�
          <select  name="menu1"  onChange="checkUserid()"   >
          <option value="">==��ѡ��==</option>
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
    		tu.setRowCode("������","@������@"+",mflag");
    		tu.setRowCode("���鿪���־","@���鿪���־@"+",Finishflag");
    		tu.setRowCode("����ص�","@����ص�@"+",mroom");
    	   tu.setRowCode("������","@������@"+",BASE_ORG,ORGCODE,ORGNAME");
    		
    	   out.print(tu.DrawTable());
	%>
    <%}%>
      <input name="act" type="hidden" id="act" value="del">
       <input name="item" type="hidden" id="item" value="">
       <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingAction">
        <input type="hidden" id="entity" name="entity" value="COM_MEETINGINFO"/>
        <table width="100%" border="0" cellpadding="3" cellspacing="0" >
         <tr>
           <td width="51%">��<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
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
