<%@ page language="java" import="java.util.*,com.db.*,com.entity.ftp.*,com.ftp.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	DBObject db = new DBObject();
	String sql="select * from system_ftp where file_type=0 order by file_id";
	DataTable dt=db.runSelectQuery(sql);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FileMerge.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script language="javascript">
  function FileMerge()
  		{	var i
 			var s=""
 			var str = document.getElementById("MergeFileSubject").value;
 			
 			if (str == "请输入文档主题") {
		                alert("请输入文档名称");
						window.open("FileMerge.jsp","_self");
		    }else{
 				for(i=0;i<document.all.item("right").length;i++)
 				{
  					 s=s+document.all.item("right").options[i].value+","
  				}
  				s=s+str+","
   				window.open("Merge.jsp?sid="+s,"_self");
   			}
	}
  function moveOption(obj1, obj2)
		{
			 for(var i = obj1.options.length - 1 ; i >= 0 ; i--)
			 {
				 if(obj1.options[i].selected)
				 {
					var opt = new Option(obj1.options[i].text,obj1.options[i].value);
					opt.selected = true;
					obj2.options.add(opt);
					obj1.remove(i);
				}
			 }
		}
				function Cancel() {
		            window.close();
		        }
		
		        function getFocus() {
		            var str = document.getElementById("MergeFileSubject").value;
		            if (str == "请输入文档主题") {
		                document.getElementById("MergeFileSubject").value = "";
		            }
		        }
		        function lostFocus() {
		            var str = document.getElementById("MergeFileSubject").value;
		            if (str.length <= 0) {
		                document.getElementById("MergeFileSubject").value = "请输入文档主题";
		            }
		        }
</script>
  <body>
  <form id="form2" action="CreateWord.aspx">
			<div id="content">
				<div id="textcontent" style="width: auto; height: auto;">
					<div class="flow4">
						<span style="width: 100px;"> &nbsp; </span>
					</div>
						<div>
						文档主题：
						<input name="MergeFileSubject" id="MergeFileSubject" type="text"
							onfocus="getFocus()" onBlur="lostFocus()" class="boder"
							style="width: 180px;" value="请输入文档主题" />
						<input type="button" onClick="Cancel()" value="取消" />
					</div>
					<div>
						&nbsp;
					</div>
					<po:PageOfficeCtrl id="PageOfficeCtrl1" />
				</div>
			</div>
</form>
          <table border="0" width="400">
		 <tr>
		  <td><CENTER>待选项</CENTER></td>
		  <td> </td>
		  <td><CENTER>已选项</CENTER></td>
		 </tr>
      <tr>
	      <td width="40%">
			  <select  multiple name="left" id="left" size="8" style='width:200;height:150' ondblclick="moveOption(document.getElementById('left'), document.getElementById('right'))">
				     <%
		 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
				//r.getString("file_name")
				%>
				   <option value="<%=r.getString("file_id")%>"><%=r.getString("file_name")%></option>
	  <%
	  	}}
	  %>
			  </select>
	   	</td>
      <td width="20%" align="center">
		  <input type="button" value=" >> " onclick="moveOption(document.getElementById('left'),document.getElementById('right'))"><br><br>
		  <input type="button" value=" << " onclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
     </td>
      <td width="40%">
         <select  multiple name="right" id="right" size="8" style='width:200;height:150' ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))"></select>
      </td>
    </tr>
    <tr>
    <td align="center"><input type="button" value="合并"  onClick="FileMerge();"></td>
    </tr>
  </table>
  </body>
</html>
