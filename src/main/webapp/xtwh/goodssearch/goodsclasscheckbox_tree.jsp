<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.common.*,com.entity.system.*,com.dao.system.*,com.db.*" errorPage="" %>
<%
request.setCharacterEncoding("gb2312");
String StoreEventNo=Format.NullToBlank(request.getParameter("StoreEventNo"));
//String pageurl=Format.NullToBlank(request.getParameter("pageurl"));
//String pagetarget=Format.NullToBlank(request.getParameter("pagetarget"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<base target=_self>
<HEAD>

<META http-equiv="Content-Type" content="text/html; charset=GB18030">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="../../images/ztree/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<script type="text/javascript" src="../../js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../../js/ztree/jquery.ztree.excheck-3.5.min.js"></script>

<SCRIPT type="text/javascript"> 
function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		py = $("#py").attr("checked")? "p":"",
		sy = $("#sy").attr("checked")? "s":"",
		pn = $("#pn").attr("checked")? "p":"",
		sn = $("#sn").attr("checked")? "s":"",
		type = { "Y":py + sy, "N":pn + sn};
		//type = { "N":"ps", "Y":"ps"};
		zTree.setting.check.chkboxType = type;
		//showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	}
		var zNodes =<%=UnitTreecheckbox.getTreeNew("com_goodsclass","goodscode","goodsname","parentgoodscode","WF")%>;
var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
		$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();
	});
function select()
{
	//parent.frames('goodsStorelist').location.href='goodsStore_list.jsp'; 
	        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
 
            var nodes = zTree.getCheckedNodes(true);
 			var orgname,orgcode="";
            //alert(nodes.length);
 			var styleIn="OUT";
 			var styleOut="IN";
 
 
            for (var i = 0; i < nodes.length; i++) {
	                //orgname = nodes[i].name;
	 				orgcode+=nodes[i].id+";";
            }        
            
            var chkIn = document.getElementById("0"); 

			if(chkIn.checked)
				 styleIn="IN";
			else
				 styleIn="OUT";
			var chkOUT = document.getElementById("1"); 

			if(chkOUT.checked)
			     styleOut="OUT";
			else
				 styleOut="IN";
			//alert("styleIn:"+styleIn+"styleOut:"+styleOut);
            var department=document.getElementById("department").value;
           var startdate=document.getElementById("startdate").value;
            var enddate=document.getElementById("enddate").value;
            
            parent.document.getElementById("goodsStorelist").contentWindow.location.href="goodsStore_list.jsp?styleIn="+styleIn+"&styleOut="+styleOut+"&department="+department+"&startdate="+startdate+"&enddate="+enddate+"&goodscode="+orgcode+""; 
           // window.open("goodsStore_list.jsp?styleIn="+styleIn+"&styleOut="+styleOut+"&department="+department+"&startdate="+startdate+"&enddate="+enddate+"&goodscode="+orgcode+"","goodsStorelist");
	
}		
</SCRIPT>
<TITLE>这是一颗树</TITLE>
<!--<script type="text/javascript" src="../../js/DatePicker/WdatePicker.js"></script>
-->

<script type="text/javascript" src="../../js/MyDatePicker/WdatePicker.js"></script></HEAD>
<BODY bgcolor="#DBECFE" leftmargin="0" rightmargin="0" topmargin="0" bottommargin="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
<div>

<fieldset>
    <legend>查询条件</legend>
    <table>
    <tr>
    <td><input type="checkbox" value="0" id="0" name="0">入库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" value="1" id="1" name="1" >领用</td>
    </tr>
    
    <tr><td> 部门名称：<input name="department" type="text" class="input1" id="department"  value=""  ></td></tr>
    <tr><td> 起始日期：
    <input name="startdate" type="Wdate" class="input1" id="startdate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="30"  maxlength="30"><!--
     
    <input name="startdate" type='Wdate'  onfocus="new WdatePicker(this,null,false,'whyGreen')"   class="input1" id="startdate" value=""  >-->
    </td></tr>
    <tr><td> 截止日期：
    <!--<input name="enddate" type='Wdate' onfocus="new WdatePicker(this,null,false,'whyGreen')"  class="input1" id="enddate"  value=""  >
    --><input name="enddate" type="Wdate" class="input1" id="enddate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="30"  maxlength="30">
    </td></tr>
    
     </table>
   
  </fieldset>

</div>
<div style="overflow-x: auto; overflow-y: auto; height: 480px;">
	<ul id="treeDemo" class="ztree"></ul>
</div>
<div>
	<a href="#" onClick="select()" class="button4">查询</a>
</div>
<input type="hidden" id="entity" name="entity" value="COM_INSTOREITEM"/>
<input type="submit" name="Submit" value="提交" style="display:none">
         <input type="txt" name="StoreEventNo" id="StoreEventNo" value="<%=StoreEventNo %>" style="display:none">
         <input type="txt" name="goodscode" id="goodscode" value="" style="display:none">
         <input name="act" type="hidden" id="act" value="addDul">
         <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformINAction">
</form>
</BODY>
</HTML>
