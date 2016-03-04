<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/demo/demo.css">
<style type="text/css">
*{font-size:12px; font-family:Î¢ÈíÑÅºÚ,ÐÂËÎÌå}
</style>
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<body style="width:100%;height:100%;margin:0 3px;padding:0;overflow:hidden">
<div >


	<table id="dg" class="easyui-datagrid" style="width:150px;float:left;
           url="get_users.php"
           toolbar="#toolbar" pagination="false"
           rownumbers="false" fitColumns="true" singleSelect="true">
       	<thead >
           	<tr >
           	<th data-options="field:'archcode',width:100" hidden="true">ÌåÏµ±àÂë</th>
               <th data-options="field:'archname',width:100">ÌåÏµÃû³Æ</th>
               </tr>
             </thead>
             <tbody>
              <c:forEach items="${archList}" var="item">   
             <tr> 
              <td hidden="true">${item.indexCode}		 </td>  
             <td>${item.indexName}		 </td>  
	    </tr>
	     </c:forEach>  
             </tbody>
              
         </table>
         
         <input type="button" id="btn_ok" style="display: none" onclick="ret()">
         <script type="text/javascript">
	   function ret(){
	    	var api = frameElement.api;
	    	var row = $('#dg').datagrid('getSelected');
	    	(api.data)({code:(row)});
	    	//alert(row.archcode);
	    }
	</script>
 </div>
 
	                
</body>
</html>