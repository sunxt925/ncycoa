<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.common.*,com.entity.system.*,com.dao.system.*,com.db.*" errorPage="" %>
<%
request.setCharacterEncoding("gb2312");
//String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
//String pageurl=Format.NullToBlank(request.getParameter("pageurl"));
//String pagetarget=Format.NullToBlank(request.getParameter("pagetarget"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB18030">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="../images/ztree/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../js/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<SCRIPT type="text/javascript">
/*function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		py = $("#py").attr("checked")? "p":"",
		sy = $("#sy").attr("checked")? "s":"",
		pn = $("#pn").attr("checked")? "p":"",
		sn = $("#sn").attr("checked")? "s":"",
		type = { "N":py + sy, "Y":pn + sn};
		zTree.setting.check.chkboxType = type;
		//showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	}*/
		var zNodes =<%=UnitTreecheckbox.getTreeNew("com_goodsclass","goodscode","goodsname","parentgoodscode","WF")%>;
var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//setCheck();
	});
function select()
{
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
 
            var nodes = zTree.getCheckedNodes(true);
 			var orgname,orgcode,pid,pname;
            /*if(nodes.length!=1)
            {
            	alert("只能选择一项");
            	return;
            }*/
 
 
 
            for (var i = 0; i < nodes.length; i++) {
	                orgname = nodes[i].name;
	 				orgcode=nodes[i].id;
	 			pid=nodes[i].pId;
	 			pname=nodes[i].pname;
            }        
 
            //alert(orgname+orgcode);
            var return_value=orgname+";"+orgcode+";"+pid+";"+pname;
            if(orgname==undefined)
            {
            var strs= new Array();
          
         
         //  var sel=document.getElementsByName("goodsinfo")[0];
         //  var selvalue= sel.options[sel.options.selectedIndex].value
         var selvalue=document.getElementsByName("goods_info").value;
           strs=selvalue.toString().split(",");
           
            return_value=strs[1]+";"+strs[0]+";"+strs[2]+";"+strs[3];
            }
            window.returnValue = return_value;
            
           
 			window.close();
	
}	


var xmlHttp=null;

function showHint(str)
{
if (str.length==0)
  { 
  document.getElementById("txtHint").innerHTML="";
  return;
  }
try
  {// Firefox, Opera 8.0+, Safari, IE7
  xmlHttp=new XMLHttpRequest();
  }
catch(e)
  {// Old IE
  try
    {
    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  catch(e)
    {
    alert ("Your browser does not support XMLHTTP!");
    return;  
    }
  }
var url="goodschoose.jsp?q=" + str;
url=url+"&sid="+Math.random();
xmlHttp.open("GET",url,false);
xmlHttp.send(null);
document.getElementById("txtHint").innerHTML=xmlHttp.responseText;

}
function s(para)
{

document.getElementsByName("goods_info").value=para;
 var sss= new Array();
 sss=para.toString().split(",");
document.getElementById("txt1").value=sss[1];
document.getElementById("hello").style.display="none";
}
</SCRIPT>
<TITLE>这是一颗树</TITLE>
</HEAD>
<BODY bgcolor="#DBECFE" leftmargin="0" rightmargin="0" topmargin="0" bottommargin="0">
<div style="overflow-x: auto; overflow-y: auto;  height: 480px;">
<div style=" overflow-x: auto; overflow-y: auto;  width:50%; height:480px;float:left;">
<ul id="treeDemo" class="ztree"></ul>
</div>
<div style=" width:50%; height:480px;float:left;">
物资查询:

<input type="text" id="txt1" style=width:200px;
onkeyup="showHint(this.value)">
<br/>
<span id="txtHint"></span> 

</div>
</div>

<div >

	<a href="#" onClick="select()" class="button4">请选择物品</a>
</div>
<input name="goods_info" id="goods_info" type="hidden" />
</BODY>
</HTML>
