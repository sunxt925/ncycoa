<%@page import="com.entity.index.IndexitemPara"%>
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
<title>�Ĵ��ϳ��̲�ר��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
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
String indexcode=request.getParameter("indexcode");
Indexitem indexitem=new Indexitem(indexcode);
ComponentUtil cu=new ComponentUtil();
CodeDictionary cd=new CodeDictionary();
String parasource=CodeDictionary.getselectoption("parasource");
List<IndexitemPara> indexitemParas=Indexitem.getparainfo(indexcode);

%>
<body>
	<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
		<div id="p" style="padding: 10px">
			<!-- <a id="btn_save" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">����</a>  -->
				<a id="btn_reset"
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true">����</a> <a
				id="btn_ref" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
		</div>
		<div >
		<table cellpadding="5"  width="50%" align="left" >
		<!-- ******************************************* -->
			<tr>
				<td><span>�������ƣ�</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "INDEXNAME",indexitem.getIndexName()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>ָ��������</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "INDEXDESC",indexitem.getIndexDesc()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>�Ƿ�ָ�꣺</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "ISPARENT",indexitem.getIsParent()));
					%>
				</td>
			</tr>
		<!-- ******************************************* -->
		
			 <% if(indexcode.length()>3&&indexitem.getIsParent().equals("0")){ %>
			<tr>
				<td><span>ָ��ֵ�������ͣ�</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALUECOMPUTINGTYPE",indexitem.getValueComputingType()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>ָ��ֵ���㹫ʽ��</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALUEFUNC",indexitem.getValueFunc()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>ָ��ֵ������λ��</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALUEUNIT",indexitem.getValueUnit()));
					%>
				</td>
			</tr>
			<tr>
				<td><span>�Ʒֺ������</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCOREFUNCTYPE",indexitem.getSCoreFuncType()));
					%>
				</td>
			</tr>
		    <tr>
				<td><span>�Ʒֺ�����</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCOREFUNC",indexitem.getScoreFunc()));
					%>
				</td>
			</tr>
			<% }%>
			
			<!-- ************************************************ -->
			<tr>
				<td><span>������ֵ��</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCOREDEFAULT",String.valueOf(indexitem.getScoreDefault())));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>����ֵ��</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCORESUMMAX",String.valueOf(indexitem.getScoreSumMax())));
					%>
				</td>
			</tr>
			
            <tr>
				<td><span>��С��ֵ��</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCORESUMLOW",String.valueOf(indexitem.getScoreSumLow())));
					%>
				</td>
			</tr>
			<tr>
				<td><span>��׼��ֵ��</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "STANDARDSCORE",String.valueOf(indexitem.getStandardscore())));
					%>
				</td>
			</tr>
			<tr>
				<td><span>����Ȩ�أ�</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "UPPERSUMWEIGHT",String.valueOf(indexitem.getUpperSumWeight())));
					%>
				</td>
			</tr>
			<!-- ******************************************* -->
			
			<% if(indexcode.length()>3&&indexitem.getIsParent().equals("0")){ %>
			<tr>
				<td><span>�Ʒ����ڣ�</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "SCOREPERIOD",indexitem.getScorePeriod()));
					%>

				</td>
			</tr>
			<%} %>
			
			<!-- ********************* -->
			<% if(indexcode.length()==3){ %> 
			<tr>
				<td><span>��ʼ���ڣ�</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALIDBEGINDATE",Format.dateToStr(indexitem.getValidBeginDate())));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>�������ڣ�</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "VALIDENDDATE",Format.dateToStr(indexitem.getValidEndDate())));
					%>
				</td>
			</tr>
				<tr>
				<td><span>����</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "INDEXORDER",indexitem.getIndexorder()));
					%>
				</td>
			</tr>
			<%} %>
			<!-- ********************** -->
			
			<!-- *********************** -->
			<tr>
				<td><span>��Ч��־��</span></td>
				<td>
					<%
						out.print(cu.print("TBM_INDEXITEM", "ENABLED",String.valueOf(indexitem.getEnabled())));
					%>
				</td>
			</tr>
			<!-- ******************** -->
		</table>
			<%
				if (indexcode.length() > 3&&indexitem.getIsParent().equals("0")) {
			%>
			<table id="paratable" width="50%" align="right" class="table_list1">
				<tr >
				<%if(indexitem.getValueComputingType().equals("���㺯����")){ %>
					<td> <a id="addpara" href="#" class="easyui-linkbutton"
				                  data-options="iconCls:'icon-add',plain:true" style="display:">��Ӳ���</a>
					     <a id="selectpara" href="#" class="easyui-linkbutton"
				                  data-options="iconCls:'icon-search',plain:true" style="display:">ѡ�����</a></td>
				                  <%}else{ %>
				                  <td> <a id="addpara" href="#" class="easyui-linkbutton"
				                  data-options="iconCls:'icon-add',plain:true" style="display:none">��Ӳ���</a>
					     <a id="selectpara" href="#" class="easyui-linkbutton"
				                  data-options="iconCls:'icon-search',plain:true" style="display:none">ѡ�����</a></td>
				                  <td></td>
				                  <%} %>
					<td></td>
				</tr>
				<%for(IndexitemPara para:indexitemParas){ %>
				<tr>
					<td align='center'><span><%=para.getParaid() %>:</span> 
					<input type="text" size='13' id="<%=para.getParaid() %>"  value="<%=para.getParacode() %>" /> 
					<span>������Դ:</span> 
					<select id="<%=para.getParaid()%>_src">
					   <option value='<%=para.getParavaluemode() %>' selected="selected"><%=para.getParavaluemode() %></option>
					   <%=parasource%>
					</select>
					
					</td>
					<td><a href="javascript:Goto();"  style="text-decoration: none"  onclick='{deleteCurrentRow(this);}'>x</a></td>
				</tr>
				<%} %>
			</table>
			<%
				}
			%>
		</div>
		<!-- ***************** -->
		<% if (indexcode.length() ==3){%>
		    <input name="TBM_INDEXITEM.SCOREFUNC" id="TBM_INDEXITEM.SCOREFUNC" type="hidden" value="<%=indexitem.getScoreFunc()%>"/>
		    <input name="TBM_INDEXITEM.SCOREPERIOD" id="TBM_INDEXITEM.SCOREPERIOD" type="hidden" value="<%=indexitem.getScorePeriod()%>"/>
		    
		<%} %>
		<!-- ****************** -->
		<% if (indexcode.length() !=3){%>
		    <input name="TBM_INDEXITEM.VALIDBEGINDATE" id="TBM_INDEXITEM.VALIDBEGINDATE" type="hidden" value="<%=Format.dateToStr(indexitem.getValidBeginDate())%>"/>
		    <input name="TBM_INDEXITEM.VALIDENDDATE" id="TBM_INDEXITEM.VALIDENDDATE" type="hidden" value="<%=Format.dateToStr(indexitem.getValidEndDate())%>"/>
		    
		<%} %>
		<!-- ***************** -->
		<% if (indexitem.getIsParent().equals("1")){%>
		    <input name="TBM_INDEXITEM.VALUECOMPUTINGTYPE" id="TBM_INDEXITEM.VALUECOMPUTINGTYPE" type="hidden" value="<%=indexitem.getValueComputingType()%>"/>
		    <input name="TBM_INDEXITEM.VALUEFUNC" id="TBM_INDEXITEM.VALUEFUNC" type="hidden" value="<%=indexitem.getValueFunc()%>"/>
		    <input name="TBM_INDEXITEM.VALUEUNIT" id="TBM_INDEXITEM.VALUEUNIT" type="hidden" value="<%=indexitem.getValueUnit()%>"/>
		    <input name="TBM_INDEXITEM.SCOREFUNCTYPE" id="TBM_INDEXITEM.SCOREFUNCTYPE" type="hidden" value="<%=indexitem.getSCoreFuncType()%>"/>
		    <input name="TBM_INDEXITEM.SCOREFUNC" id="TBM_INDEXITEM.SCOREFUNC" type="hidden" value="<%=indexitem.getScoreFunc()%>"/>
		   
		<%} %>
		<!--******************** -->
		<%if (indexitem.getIsParent().equals("1")){ %>
		    <input name="TBM_INDEXITEM.SCOREPERIOD" id="TBM_INDEXITEM.SCOREPERIOD" type="hidden" value="<%=indexitem.getScorePeriod()%>"/>
		<%} %>
		<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		<!-- �����ֶ� -->
		<input name="TBM_INDEXITEM.INDEXCODE" id="TBM_INDEXITEM.INDEXCODE" type="hidden" value="<%=indexcode%>"/>
		<input name="old_INDEXCODE" id="old_INDEXCODE" type="hidden" value="<%=indexcode%>"/>
		<input type="hidden"  id="TBM_INDEXITEM.UNIINDEXCODE" name="TBM_INDEXITEM.UNIINDEXCODE" value="" >
		<input name="TBM_INDEXITEM.ISPARENT" id="TBM_INDEXITEM.ISPARENT" type="hidden" value="<%=indexitem.getIsParent()%>"/>
		<input type="hidden" id="TBM_INDEXITEM.NUMPARA" name="TBM_INDEXITEM.NUMPARA"  >
		<input name="entity" id="entity" type="hidden" value="TBM_INDEXITEM"/>
        <input name="act" type="hidden" id="act" value="modify">
        <input name="TBM_INDEXITEM.PARENTINDEXCODE" id="TBM_INDEXITEM.PARENTINDEXCODE" type="hidden" value="<%=indexitem.getParentIndexCode()%>"/>
        <input name="indexpara" id="indexpara" type="hidden" />
        <input name="indexparasrc" id="indexparasrc" type="hidden" />
        <input type="submit" name="Submit" value="�ύ" style="display:none">
        <input type="reset" name="reset" value="����" style="display:none">
        <input name="action_class" type="hidden" id="action_class" value="com.action.index.IndexItemAction">
	</form>
		
		
	<script>
	document.getElementById("TBM_INDEXITEM.NUMPARA").value="<%=indexitem.getNumPara()%>";
	    $("#btn_reset").click(function(){
	    	document.all("reset").click();
    	    });
	    function ret(){
			   var api = frameElement.api;
		    	
			   var flag="<%=indexcode.length()%>";
			   var isparent="<%=indexitem.getIsParent()%>";
		    	if(flag>3&&isparent=='0'){
		    		seareilze();
		    	}
		    	if(sumbit_check())
		   	    {
		    		
		    		if(document.getElementById("TBM_INDEXITEM.VALIDBEGINDATE").value!=""&&document.getElementById("TBM_INDEXITEM.VALIDENDDATE").value!=""&&document.getElementById("TBM_INDEXITEM.VALIDBEGINDATE").value<document.getElementById("TBM_INDEXITEM.VALIDENDDATE").value){
		    			 if(isparent=="1"){
		    				 document.getElementById("TBM_INDEXITEM.SCOREFUNC").value="x";
		    				 document.getElementById("TBM_INDEXITEM.SCOREFUNCTYPE").value="һ�㺯����";
		    				 document.getElementById("TBM_INDEXITEM.SCOREPERIOD").value="D00.M00";
		    				document.getElementById("TBM_INDEXITEM.VALUECOMPUTINGTYPE").value="���������";
		    			}
		    			
		    			 document.all("Submit").click();
		   	   	         (api.data)({code:"refresh"});
		    		}else{
		    			alert("�����ֶ�Ϊ�ջ�������");
		    		}
		   	      
		   	    }
		   }
	 
		function submitForm() {
			$('#ff').form('submit');
		}
		function clearForm() {
			$('#ff').form('clear');
		}
			
			$("#addpara").click(function(){
				x=document.getElementById("TBM_INDEXITEM.NUMPARA").value;
			    x=x/1+1;
			    if(x<=4){
			    var paraname="P"+x;
			    var parasource=paraname+"_src";
			    var tr="<tr >"+
			           "<td align='center'><span>"+paraname+": </span>"+
			           "<input type=\"text\" size='13' id=\""+paraname+"\"/> "+
			           "<span>������Դ: </span>"+
			           "<select id=\""+parasource+"\">"+"<%=parasource%>"+
			           "</select>"+
			           "</td>"+
			           "<td><a href=\"javascript:Goto();\"  style=\"text-decoration: none\"  onclick='{deleteCurrentRow(this);}'>x</a></td>"+
			           "</tr>"; 
			           $("#paratable").append(tr);
			         document.getElementById("TBM_INDEXITEM.NUMPARA").value=x;
			    }else{
		        	 alert("����������������");
		         }
			    });
			 function returnValue(data){
				 x=x/1+1;
		         var paraname="P"+x;
		         var parasource=paraname+"_src";
		         
		         var tr="<tr >"+
		                "<td align='center'><span>"+paraname+": </span>"+
		                "<input type=\"text\"  size=13 id=\""+paraname+"\" value=\""+data.code+"\"/> "+
		                "<span>������Դ: <span>"+
		                "<input type=\"text\"  size=11 id=\""+parasource+"\" value=\"ҵ������\"/>"+
		                "</td>"+
		                "<td><a href=\"javascript:Goto();\"  style=\"text-decoration: none\"  onclick='{deleteCurrentRow(this);}'>x</a></td>"+
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
								this.title('3����Զ��ر�').time(0.1);
								return false;
							},
							cancelVal : '�ر�',
							cancel : true/* Ϊtrue�ȼ���function(){} */
						});
					}
				}
				$("#selectpara").click(function(){
					x=document.getElementById("TBM_INDEXITEM.NUMPARA").value;
					if(x<4){
			    	   createwindow('ѡ�����', 'indexmanage/selectpara.jsp');
			    	   
					}else{
						 alert("����������������");
					}
			    });
			    $(document.getElementById("TBM_INDEXITEM.VALUECOMPUTINGTYPE")).change(function(){
			         var p1=$(this).children('option:selected').val();
			         if(p1=="���㺯����"){
			            document.getElementById("addpara").style.display="";
			            document.getElementById("selectpara").style.display="";
			          }else{
			        	  document.getElementById("addpara").style.display="none";
				            document.getElementById("selectpara").style.display="none";
			          }
			        
			   });
			    function Goto(){}
				   function deleteCurrentRow(obj){
				   var tr=obj.parentNode.parentNode;
				   var tbody=tr.parentNode;
				   tbody.removeChild(tr);
				   x=document.getElementById("TBM_INDEXITEM.NUMPARA").value;
				   x=x-1;
				   document.getElementById("TBM_INDEXITEM.NUMPARA").value=x;
				   }
			   
			  
			   //���л�����
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
		
	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>