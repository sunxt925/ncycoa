<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE>�ϳ����̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
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
	DBObject db = new DBObject();
	String sql="select * from base_org where PARENTORGCODE='NC' order by orgcode";
	DataTable dt=db.runSelectQuery(sql);
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("unit_new.jsp?sid="+rand,"unitlist");
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

function changetopunit(orgcode)
{
	var newlisturl='postion_list.jsp?orgcode='+orgcode;
	var newtreeurl='../../tree/unit_tree.jsp?unitccm='+orgcode+'&pageurl=../xtwh/user/postion_list.jsp&pagetarget=postionlist';
	window.open(newlisturl,'postionlist');
	window.open(newtreeurl,'unittree');
	window.open("empty.jsp","memberlist");
}

function getmember(positioncode)
{
  var newmemberurl='member_list.jsp?positioncode='+positioncode;
  window.open(newmemberurl,"memberlist");
}
</script>


<body>
  <div id="main_div">
   <div  id="top_div">
             <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="3%" class="main_table_topbg" height="31"><img src="<%=request.getContextPath() %>/images/table_lt.jpg" width="22" height="31"></td>
                    <td width="94%" valign="middle" class="main_table_topbg" height="31"> ��ҳ &gt;&gt; ϵͳά�� &gt;&gt; Ա������</td>
                    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="<%=request.getContextPath() %>/images/table_rt.jpg" width="22" height="31"></td>
                 </tr>
            </table>
      </div>
      <div id="bottom_div">
            
            
               <table width="100%" height="94%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                  <td width="20%"> 
                 <iframe src="usermanage_left.jsp" height="90%"></iframe>
                 </td>
                  <td width="80%" >
               <iframe src="postion_list.jsp" name="postionlist" id="postionlist" height="45%"  width="95%"></iframe><br>
                 <iframe src="empty.jsp" name="memberlist" id="memberlist" height="45%"  width="95%" scrolling="no"></iframe> 
                 </td>
                   </tr>
            </table> 
      </div>
        
  </div>
 
  </body>



</HTML>