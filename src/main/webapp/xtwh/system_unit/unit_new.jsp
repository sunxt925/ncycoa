<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<script type="text/javascript" src="../../js/MyDatePicker/WdatePicker.js"></script></HEAD>
<base target=_self>
<% 

/* String unitccm=request.getParameter("unitccm");
 Org og=new Org();
 DataTable dt=og.getByParentcode(unitccm);
 String newcoding="";
 if(dt!=null && dt.getRowsCount()>0){
 String code =dt.get(dt.getRowsCount()-1).getString("orgcode");
 //System.out.println(code);
 String temp=code.substring(code.length()-4,code.length());
 
 int newcode=Integer.parseInt(temp)+1; 
  String s = String.valueOf(newcode);
  String res="";
  for(int i=s.length();i<4;i++)
  {
  	res+="0";
  }
 res+=s;
 newcoding=code.substring(0,code.length()-4)+res;

 }
 else{
 newcoding=unitccm+"0001";
  
 }*/

%>
<script language="javascript" type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript" src="../../js/public/check.js"></script>
<script language="javascript" src="../../js/public/Calendar1.js"></script>
<script language="javascript">
function F5()
{
	window.location.reload();
}
function F3()
{
	document.all("reset").click();
}
function F8()
{
	if (sumbit_check())
	{
		//alert ("aaa");
		document.all("Submit").click();
		//window.close();
	}
}
function select()
{
	var newtreeurl='unitcheckbox_tree.jsp';
	var str=showModalDialog(newtreeurl);
	if(str==null||str=="")
		return;
	var strs= new Array(); //定义一数组 
	
	strs=str.split(";"); //字符分割 
	/*if(strs==null||strs=="")
		return;*/
	document.getElementById("BASE_ORG.BLONGADMINORGNAME").value=strs[0];
	document.getElementById("BASE_ORG.BLONGADMINORGCODE").value=strs[1];
	//window.open (newtreeurl,"Sample","toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,scrollbars=yes, copyhistory=no,width=350,height=540,left=200,top=300");
}
</script>
<%
	Org org=new Org(request.getParameter("unitccm"));
	ComponentUtil cu=new ComponentUtil();

 %>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
<table cellpadding="5"  width="100%" align="left" >
				<tr>
					<td><a id="F8" style="display: none" href="#" onClick="F8()">保存[F8]</a></td>
				</tr>
			
			<tr>
				<td><span>机构编码：</span></td>
				<td>
				<%
        	out.print(cu.print("BASE_ORG","ORGCODE",AutoCoding.Coding(request.getParameter("unitccm"),"base_org","orgcode",2,"."),"readonly"));
        %>				
        </td>
			</tr>
			<tr>
				<td><span>机构全称：</span></td>
				<td>
					 <%
        	out.print(cu.print("BASE_ORG","ORGNAME"));
        %>
				</td>
			</tr>
			 
			<tr>
				<td><span>机构简称：</span></td>
				<td>
					 <%
        	out.print(cu.print("BASE_ORG","ORGSIMPLENAME"));
        %>
				</td>
			</tr>
			<tr>
				<td><span>机构职能：</span></td>
				<td>
				 <%
                	out.print(cu.print("BASE_ORG","ORGDESC"));
                %>
				</td>
			</tr>
			
			<tr>
				<td><span>成员数：</span></td>
				<td>
					  <%
        	out.print(cu.print("BASE_ORG","MEMBERCOUNT"));
        %>
				</td>
			</tr>
			<tr>
				<td><span>岗位数：</span></td>
				<td>
					 <%
        	out.print(cu.print("BASE_ORG","POSITIONCOUNT"));
        %>
				</td>
			</tr>
			<tr>
				<td><span>办公室地址：</span></td>
				<td>
					 <%
        	out.print(cu.print("BASE_ORG","OFFCIEADDRESS"));
        %>
				</td>
			</tr>
			<tr>
				<td><span>邮政编码：</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","POSTCODE"));
        %>
				</td>
			</tr>
			<tr>
				<td><span>联系方式：</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","CONTACTINFO"));
        %>
				</td>
			</tr>
			<tr>
				<td><span>机构类别：</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","ORGCLASS",org.getOrgClass()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>创建日期：</span></td>
				<td>
					 <%
        	out.print(cu.print("BASE_ORG","CREATEDATE"));
        %>
				</td>
			</tr>
			<tr>
				<td><span>上级部门编码：</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","PARENTORGCODE",request.getParameter("unitccm"),"readonly"));
        %>
				</td>
			</tr>
			<tr>
				<td><span>上级部门名称：</span></td>
				<td>
                    <%
        	out.print(cu.print("BASE_ORG","PARENTORGNAME",org.getName(),"readonly"));
        %>
                    </td>		
			</tr>
			<tr>
				<td><span>挂靠行政机构：</span></td>
				<td>
					<input name="BASE_ORG.BLONGADMINORGCODE" type="text" class="input1" id="BASE_ORG.BLONGADMINORGCODE" onKeyDown="EnterKeyDo('')" value="" readonly size="48" maxlength="48"><a href="#" onClick="select()" class="button4">选择</a>
				</td>
			</tr>
			<tr>
				<td><span>挂靠行政机构名：</span></td>
				<td>
					<input name="BASE_ORG.BLONGADMINORGNAME" type="text" class="input1" id="BASE_ORG.BLONGADMINORGNAME" onKeyDown="EnterKeyDo('')" value="" readonly size="48" maxlength="200">
				</td>
			</tr>
			<tr>
				<td><span>备注：</span></td>
				<td>
					<%out.print(cu.print("BASE_ORG","MEMO")); %>
				</td>
			</tr>
			
		</table>

<!--<tr>-->
<!--<td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      
      <tr>
      <td>
      <input type="hidden" id="entity" name="entity" value="BASE_ORG"/>
    
    
         <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          </td>
      </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="add"></div></td>
        
        <td><input name="BASE_ORG.NONLEAFFLAG" type="hidden" class="input1" id="BASE_ORG.NONLEAFFLAG" value="0" size="30" maxlength="30"></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitAction"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
<% out.print(cu.getCheckstr());%>