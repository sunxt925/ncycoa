<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target=_self>
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=path%>/js/public/key.js"></script>
<script language="javascript" src="<%=path%>/js/public/check.js"></script>
<script type="text/javascript" src="<%=path%>/js/public/searchvalue.js"></script>
<script language="javascript" src="<%=path%>/js/public/Calendar1.js"></script>
<%
String outno=request.getParameter("outno");
Goodsoutstoreitem gisi=new Goodsoutstoreitem(outno);//本次出库物品详细信息
GoodsStoreInfo gsi=new GoodsStoreInfo(gisi.getGOODSCODE());
int goodsnumber=Integer.parseInt(gsi.getAvailableNumber())+Integer.parseInt(gisi.getGOODSNUMBER());
	ComponentUtil cu=new ComponentUtil();
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());

	String username=orgmember.getStaffname();

 %>


<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td><div align="right">物资品名：</div></td>
        <td>
        <input name="COM_OUTSTOREITEM.GOODSNAME" type="text" class="input1" id="COM_OUTSTOREITEM.GOODSNAME" onKeyDown="EnterKeyDo('')" value="<%=gisi.getGOODSNAME() %>" readonly  maxlength="48">
        
        </td>
      </tr>
      <tr>
        <td><div align="right">物资品名代码：</div></td>
        <td>
        
        <input name="COM_OUTSTOREITEM.GOODSCODE" type="text" class="input1" id="COM_OUTSTOREITEM.GOODSCODE" onKeyDown="EnterKeyDo('')" value="<%=gisi.getGOODSCODE() %>" readonly  maxlength="200">
        
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">说明：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","GOODSDESC",gisi.getGOODSDESC())); %>
        
        
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">规格型号：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","GOODSSTYLE",gisi.getGOODSSTYLE())); %>
        
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">数量：</div></td>
        <td width="80%">
        <input name="COM_OUTSTOREITEM.GOODSNUMBER" id="COM_OUTSTOREITEM.GOODSNUMBER" class="input1" type="text" value="<%=gisi.getGOODSNUMBER() %>" onfocus="cls()" onblur="res();blured()"/>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">计量单位：</div></td>
        <td width="80%">
        
        <%out.print(cu.print("COM_OUTSTOREITEM","MEASUREUNIT",gisi.getMEASUREUNIT())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">领用人：</div></td>
        <td width="80%">
          <input type="hidden" name="COM_OUTSTOREITEM.HANDLER" id="COM_OUTSTOREITEM.HANDLER" value="<%=gisi.getHANDLER()%>">
        <input type="text" name="COM_OUTSTOREITEM.HANDLERNAME" id="COM_OUTSTOREITEM.HANDLERNAME" value="<%=CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", gisi.getHANDLER())%>">
        <%//out.print(cu.print("COM_OUTSTOREITEM","HANDLER",gisi.getHANDLER())); %>
        <a id="btn_selectobject2" href="#">选择</a>
        </td>
      </tr>
      <!--<tr>
        <td width="20%"><div align="right">出库日期：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","OUTDATE",gisi.getOUTDATE())); %>
        </td>
      </tr>
      --><tr>
        <td width="20%"><div align="right">验收部门：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","AUDITORGCODE",gisi.getAUDITORGCODE())); %>
        
        </td>
      </tr><!--
      <tr>
        <td width="20%"><div align="right">操作人员：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","AUDITORCODE",gisi.getAUDITORCODE())); %>
        </td>
      </tr>
      --><!--<tr>
        <td width="20%"><div align="right">录入日期：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","INPUTDATE",gisi.getINPUTDATE())); %>
        </td>
      </tr>
      --><tr>
        <td width="20%"><div align="right">领用部门：</div></td>
        <td width="80%">
        <input type="hidden" name="COM_OUTSTOREITEM.USINGORGCODE" id="COM_OUTSTOREITEM.USINGORGCODE"  value="<%=gisi.getUSINGORGCODE()%>">
        <input type="text" name="COM_OUTSTOREITEM.USINGORGNAME" id="COM_OUTSTOREITEM.USINGORGNAME" value="<%=CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname", gisi.getUSINGORGCODE())%>">
        <%//out.print(cu.print("COM_OUTSTOREITEM","USINGORGCODE",gisi.getUSINGORGCODE())); %>
        <a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">备注：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_OUTSTOREITEM","MEMO",gisi.getMEMO())); %>
        
        </td>
      </tr>
      
      <tr>
      <td>
      <input type="hidden" id="entity" name="entity" value="COM_OUTSTOREITEM"/>
    
    
         <input type="submit" name="Submit" onclick="return blured()" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          </td>
      </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="modify"></div></td>
        
        <td><input name="old_OUTNO" type="hidden" id="old_OUTNO" value="<%=outno%>">
        <input name="COM_OUTSTOREITEM.OUTNO" type="hidden" id="COM_OUTSTOREITEM.OUTNO" value="<%=outno%>">
        <input name="COM_OUTSTOREITEM.OUTDATE" id="COM_OUTSTOREITEM.OUTDATE" type="hidden" value="<%=Format.getNowtime2()%>"/>
        <input name="COM_OUTSTOREITEM.INPUTDATE" id="COM_OUTSTOREITEM.INPUTDATE" type="hidden" value="<%=Format.getNowtime2()%>"/>
        <input name="COM_OUTSTOREITEM.AUDITORCODE" id="COM_OUTSTOREITEM.AUDITORCODE" type="hidden" value="<%=username%>"/>
        <input id="btn_ok" type="hidden" onclick="ret()">
        </td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformOUTAction"></td>
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
<script language="javascript">
function ret(){
	var api = frameElement.api;
	if (sumbit_check())
	{
		document.all("Submit").click();
		(api.data)({code:"refresh2"});
	}
}
function select()
{
	var newtreeurl='goodsclasscheckbox_tree.jsp';
	var str=showModalDialog(newtreeurl);
	if(str==null||str=="")
		return;
	var strs= new Array(); //定义一数组 
	
	strs=str.split(";"); //字符分割 
	/*if(strs==null||strs=="")
		return;*/
	document.getElementById("COM_OUTSTOREITEM.GOODSNAME").value=strs[0];
	document.getElementById("COM_OUTSTOREITEM.GOODSCODE").value=strs[1];
	//window.open (newtreeurl,"Sample","toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,scrollbars=yes, copyhistory=no,width=350,height=540,left=200,top=300");
}
function blured()
{
	
	var number=<%=goodsnumber%>;
	var numberinput=document.getElementById("COM_OUTSTOREITEM.GOODSNUMBER").value;
	if(parseInt(numberinput)>parseInt(number))
	{
		alert("库存为"+number+"请重新输入");
		return false;
	}
	else
		return true;
	
}

$("#btn_selectobject2").click(function(){
	
	createwindow('选择部门','indexmanage/selectstaff.jsp',650,500,returnobjValue2 );
    });
$("#btn_selectobject").click(function(){
	
	createwindow('选择部门','indexmanage/selectunit.jsp',650,500,returnobjValue );
    });
function returnobjValue2(data){

	var array = data.code;
	var staffcodes=array[0].staffcode;
	var staffnames=array[0].staffname;
    document.getElementById("COM_OUTSTOREITEM.HANDLER").value=staffcodes;
	document.getElementById("COM_OUTSTOREITEM.HANDLERNAME").value=staffnames;
	
}
function returnobjValue(data){
	var org = data.code;
	if(org.length>1){
		alert("最多只能选择一个部门");
	}else{
		document.getElementById("COM_OUTSTOREITEM.USINGORGCODE").value=org[0].orgcode;
		document.getElementById("COM_OUTSTOREITEM.USINGORGNAME").value=org[0].orgname;
		
	}
	
	
}
function createwindow(title, url, width, height,func) {
	
	$.dialog({
			id:'CLHG1976D',
			data:func,
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			zIndex :2000,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return true;
			},
			cancelVal : '关闭',
			cancel : true/* 为true等价于function(){} */
		});
}
</script>
</BODY>
</HTML>
<% out.print(cu.getCheckstr());%>