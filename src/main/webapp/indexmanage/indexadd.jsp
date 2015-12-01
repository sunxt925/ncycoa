<%@page import="com.common.Format"%>
<%@page import="com.entity.index.Indexitem"%>
<%@page import="com.common.ComponentUtil"%>
<%@page import="com.common.CodeDictionary"%>
<%@page import="com.common.IndexCode"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=path%>/js/MyDatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=path%>/js/public/check.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<style type="text/css">
    span{
        font-size: 12px;
    }
    table{
        border: 1px solid #f2f2f2;
    }
</style>
</head>
<%
String pIndexcode=request.getParameter("pIndexcode");
String index_class=request.getParameter("index_class");
String newindexcode=IndexCode.getIndexRootcode(index_class,pIndexcode);
String isparent="";
if(newindexcode.toCharArray().length<=7){
   isparent="1";
}else
{
   isparent="0";
}
String archcode="";
        if(pIndexcode.equals("-1")){
         archcode=index_class.toUpperCase()+"00";
         }
String parasource=CodeDictionary.getselectoption("parasource");
ComponentUtil cu=new ComponentUtil();
%>
<body>
	<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
		<div id="p" style="padding: 10px">
			<!-- <a id="btn_save" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" >保存</a> -->
				 <a id="btn_reset"
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true">重置</a> <a
				id="btn_ref" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">刷新</a>
		</div>
		<div >
		<table cellpadding="5"  width="50%" align="left" >
			
			<tr>
				<td><span>编码名称：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "INDEXNAME"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>指标描述：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "INDEXDESC"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>是否父指标：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "ISPARENT",isparent));
					%>
				</td>
			</tr>
			<!-- ******************************** -->
			 <% if(newindexcode.length()>3&&isparent.equals("0")){ %>
			<tr>
				<td><span>指标值计算类型：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALUECOMPUTINGTYPE"));
					%>
					
				</td>
			</tr>
			<tr>
				<td><span>指标值计算公式：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALUEFUNC"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>指标值度量单位：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALUEUNIT"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>计分函数类别：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCOREFUNCTYPE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>计分函数：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCOREFUNC"));
					%>
				</td>
			</tr>
			<% }%>
			<!-- ***************************** -->
			<tr>
				<td><span>基础分值：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCOREDEFAULT"));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>最大分值：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCORESUMMAX"));
					%>
				</td>
			</tr>
			
            <tr>
				<td><span>最小分值：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCORESUMLOW"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>标准分值：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "STANDARDSCORE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>汇总权重：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "UPPERSUMWEIGHT"));
					%>
				</td>
			</tr>
			
			<% if(newindexcode.length()==3){ %>
			    
			<tr>
				<td><span>开始日期：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALIDBEGINDATE"));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>结束日期：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALIDENDDATE"));
					%>
				</td>
			</tr>
				<tr>
				<td><span>排序：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "INDEXORDER"));
					%>
				</td>
			</tr>
			<%}else{ 
			    Indexitem index=new Indexitem(newindexcode.substring(0,3));
			%>
			     
			    <input type="hidden" name="TBM_INDEXITEM.VALIDBEGINDATE" id="TBM_INDEXITEM.VALIDBEGINDATE" value="<%=Format.dateToStr(index.getValidBeginDate())%>">
			    <input type="hidden" name="TBM_INDEXITEM.VALIDENDDATE" id="TBM_INDEXITEM.VALIDENDDATE" value="<%=Format.dateToStr(index.getValidEndDate())%>">
			
			<%} %>
			<% if(!isparent.equals("0")){%>
			<input type="hidden" name="TBM_INDEXITEM.VALUECOMPUTINGTYPE" id="TBM_INDEXITEM.VALUECOMPUTINGTYPE" value="子项汇总型">
			<input type="hidden" name="TBM_INDEXITEM.SCOREFUNCTYPE" id="TBM_INDEXITEM.SCOREFUNCTYPE" value="一般函数型">
			<input type="hidden" name="TBM_INDEXITEM.SCOREFUNC" id="TBM_INDEXITEM.SCOREFUNC" value="x">
			<input type="hidden" name="TBM_INDEXITEM.SCOREPERIOD" id="TBM_INDEXITEM.SCOREPERIOD" value="D00.M00">
			
			<%} %>
			 <% if(newindexcode.length()>3){ %>
			
			<% if(isparent.equals("0")){ %>
            <tr>
				<td><span>计分周期：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCOREPERIOD"));
					%>

				</td>
			</tr>
			<%}%>
			
			<tr>
				<td><span>有效标志：</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "ENABLED","1"));
					%>
				</td>
			</tr>
			
			<%} %>
			
		</table>
			<%
				if (newindexcode.length() > 3&&isparent.equals("0")) {
			%>
			<table id="paratable" width="50%" align="right" class="table_list1">
				<tr >
					<td> <a id="addpara" href="#" class="easyui-linkbutton"
				                  data-options="iconCls:'icon-add',plain:true" style="display:none">添加参数</a>
					     <a id="selectpara" href="#" class="easyui-linkbutton"
				                  data-options="iconCls:'icon-search',plain:true" style="display:none">选择参数</a></td>
					<td></td>
				</tr>

			</table>
			<%
				}
			%>
		</div>
		<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		<!-- 隐藏字段 -->
		<input type="hidden" id="TBM_INDEXITEM.INDEXCODE"  name="TBM_INDEXITEM.INDEXCODE" value="<%=newindexcode %>" >
		<input type="hidden" id="TBM_INDEXITEM.UNIINDEXCODE"   name="TBM_INDEXITEM.UNIINDEXCODE" value="" >
		<input type="hidden"  id="TBM_INDEXITEM.NUMPARA" name="TBM_INDEXITEM.NUMPARA" >
		<input name="entity" id="entity" type="hidden" value="TBM_INDEXITEM"/>
        <input name="act" type="hidden" id="act" value="add">
        <input name="TBM_INDEXITEM.PARENTINDEXCODE" id="TBM_INDEXITEM.PARENTINDEXCODE" type="hidden" value="<%=pIndexcode%>"/>
        <input name="TBM_INDEXITEM.ARCHCODE" id="TBM_INDEXITEM.ARCHCODE" type="hidden" value="<%=archcode%>">
        <input name="indexpara" id="indexpara" type="hidden" />
        <input name="indexparasrc" id="indexparasrc" type="hidden" />
        <input type="submit" name="Submit" value="提交" style="display:none">
        <input type="reset" name="reset" value="重置" style="display:none">
        <input name="action_class" type="hidden" id="action_class" value="com.action.index.IndexItemAction">
	</form>
		
		
	<script>
	   function ret(){
		   var api = frameElement.api;
	    	
		   var flag="<%=newindexcode.length()%>";
		   var isparent="<%=isparent%>";
	    	if(flag>3&&isparent=='0'){
	    		seareilze();
	    	}
	    	
	    	if(sumbit_check())
	   	    {
	    		if(document.getElementById("TBM_INDEXITEM.VALIDBEGINDATE").value!=""&&document.getElementById("TBM_INDEXITEM.VALIDENDDATE").value!=""&&document.getElementById("TBM_INDEXITEM.VALIDBEGINDATE").value<document.getElementById("TBM_INDEXITEM.VALIDENDDATE").value){
	    			
	    			 document.all("Submit").click();
	    			
	    			 
	   	   	         (api.data)({code:"refresh"});
	    		}else{
	    			alert("日期字段为空或者有误");
	    		}
	   	      
	   	    }
	   }
	    $("#btn_reset").click(function(){
	    	document.all("reset").click();
    	    });
	   
	    document.getElementById("TBM_INDEXITEM.NUMPARA").value="0";
	    var x=document.getElementById("TBM_INDEXITEM.NUMPARA").value;
			$("#addpara").click(function(){
				x=document.getElementById("TBM_INDEXITEM.NUMPARA").value
				
				    x=x/1+1;
				
				    if(x<=4){
					    var paraname="P"+x;
					    var parasource=paraname+"_src";
					    var tr="<tr >"+
					           "<td align='center'><span>"+paraname+":</span>"+
					           "<input type=\"text\" width='100px' id=\""+paraname+"\"/>"+
					           "<span>参数来源:</span>"+
					           "<select id=\""+parasource+"\">"+"<%=parasource%>"+
					           "</select>"+
					           "</td>"+
					           "<td><a href=\"javascript:Goto();\"  style=\"text-decoration: none\"  onclick='{deleteCurrentRow(this);}'>x</a></td>"+
					           "</tr>"; 
						$("#paratable").append(tr);
					    document.getElementById("TBM_INDEXITEM.NUMPARA").value=x;
			         }else{
			        	 alert("参数个数超多限制");
			         }
			    });
			    
			    function returnValue(data){
			    	 x=x/1+1;
			         var paraname="P"+x;
			         var parasource=paraname+"_src";
			         
			         var tr="<tr >"+
			                "<td align='center'><span>"+paraname+":</span>"+
			                "<input type=\"text\"  width='100px' id=\""+paraname+"\" value=\""+data.code+"\"/>"+
			                "<span>参数来源:<span>"+
			                "<input type=\"text\"  size=11 id=\""+parasource+"\" value=\"业务数据\"/>"+
			                "</td>"+
			                "<td><a href=\"javascript:Goto();\" style=\"text-decoration: none\"  onclick='{deleteCurrentRow(this);}'>x</a></td>"+
			                "</tr>";
			        $("#paratable").append(tr);
			        document.getElementById("TBM_INDEXITEM.NUMPARA").value=x;
				}
				
				function createwindow(title, url, width, height) {
					var api = frameElement.api, W = api.opener;
					width = width ? width : 700;
					height = height ? height : 400;
					if (width == "100%" || height == "100%") {
						width = document.body.offsetWidth;
						height = document.body.offsetHeight - 100;
					}
					if (typeof (windowapi) == 'undefined') {
						W.$.dialog({
							id:'CLHG1976D',
							data:returnValue,
							content : 'url:' + url,
							lock : true,
							width : width,
							height : height,
							title : title,
							opacity : 0.3,
							parent:api,
							cache : false,
							ok : function() {
								$('#btn_ok', this.iframe.contentWindow.document).click();
								this.title('3秒后自动关闭').time(0.1);
								return false;
							},
							cancelVal : '关闭',
							cancel : true/* 为true等价于function(){} */
						});
					}
				}
				$("#selectpara").click(function(){
					x=document.getElementById("TBM_INDEXITEM.NUMPARA").value;
					
					if(x<4){
			    	   createwindow('选择参数', 'indexmanage/selectpara.jsp');
			    	   
					}else{
						 alert("参数个数超多限制");
					}
			    });
			    $(document.getElementById("TBM_INDEXITEM.VALUECOMPUTINGTYPE")).change(function(){
			    	   var p1=$(this).children('option:selected').val();
			    	     if(p1=="计算函数型"){
			    	    	 document.getElementById("addpara").style.display="";
			    	         document.getElementById("selectpara").style.display="";
			    	         document.getElementById("TBM_INDEXITEM.VALUEFUNC").value="";
			    	         document.getElementById("TBM_INDEXITEM.SCOREFUNCTYPE").value="";
			    	         document.getElementById("TBM_INDEXITEM.SCOREFUNC").value="";
			    	         document.getElementById("TBM_INDEXITEM.UPPERSUMWEIGHT").value="1.0";
			    	         document.getElementById("TBM_INDEXITEM.SCOREPERIOD").value="D00.M00";
			    	     }else{
			    	    	 document.getElementById("addpara").style.display="none";
			    	         document.getElementById("selectpara").style.display="none";
			    	         document.getElementById("TBM_INDEXITEM.VALUEFUNC").value="()=()";
			    	         document.getElementById("TBM_INDEXITEM.SCOREFUNCTYPE").value="一般函数型";
			    	         document.getElementById("TBM_INDEXITEM.SCOREFUNC").value="x";
			    	         document.getElementById("TBM_INDEXITEM.UPPERSUMWEIGHT").value="1.0";
			    	         document.getElementById("TBM_INDEXITEM.SCOREPERIOD").value="D00.M00";
			    	         
			    	     }
			    });
			  
			   //序列化参数
			   function seareilze(){
			
			      var table=document.getElementById("paratable");
			      var rownum=table.rows.length;
			      var para="";
			      var parasrc="";
			      for(var i=1;i<rownum;i++){
			         if(i==rownum-1){
			             para=para+document.getElementById("P"+i).value;
			             parasrc=parasrc+document.getElementById("P"+i+"_src").value;
			         }else{
			             para=para+document.getElementById("P"+i).value+",";
			             parasrc=parasrc+document.getElementById("P"+i+"_src").value+",";
			         }
			       }
			       document.getElementById("indexpara").value=para;
			       document.getElementById("indexparasrc").value=parasrc;
			   }
			   
			   function Goto(){}
			   function deleteCurrentRow(obj){
			   var tr=obj.parentNode.parentNode;
			   var tbody=tr.parentNode;
			   tbody.removeChild(tr);
			   x=document.getElementById("TBM_INDEXITEM.NUMPARA").value;
			   x=x-1;
			   document.getElementById("TBM_INDEXITEM.NUMPARA").value=x;
			   }
	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>