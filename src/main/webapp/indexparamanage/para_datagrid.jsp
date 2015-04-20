<%@ page language="java" contentType="text/html; charset=GB2312"%>
<%@ page import="com.entity.system.UserInfo"%>
<%@ page import="com.entity.index.Indexitem"%>
<%@ page import="com.performance.*"%>
<%@ page language="java" import="java.util.*"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body style="width:100%;height:100%;margin:3px 0 0;;padding:0;overflow:hidden">
<%
try{
	String year = request.getParameter("year");
	String period = request.getParameter("periodcode");
	String indexcode = request.getParameter("indexcode");
	
	System.out.println(year+":"+ period+":" + indexcode);
	
	ReviewDate date = new ReviewDate(year, period);
	UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
	Map<String, HashMap<String, String>> data = (Map<String, HashMap<String, String>>)request.getSession().getAttribute("paradata");
	Review review = new Review(user);
	ReviewTask task = review.getReviewTask(date, indexcode);
	String content = ParaDataHelper.getDataGrid(task, data);
	out.write(content);
	if(data != null){
		data.clear();
		request.getSession().removeAttribute("paradata");
	}
%>
<form id="forDownloading" action="para_dg_export.jsp" method="post">
<input id="d" name="d" type="hidden"></input>
</form>


<div id="tb" style="padding:5px;height:auto">
<a id="btnDownload" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-put'" >导出</a>
<a id="btnUpload" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-putout'">导入</a>
<a id="btnSubmit" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a>
<a id="btnReload" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">重载</a>
</div>

<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>				
<script type="text/javascript">
		function tip(msg) {
			parent.$.dialog.setting.zIndex = 1980;
			parent.$.messager.show({
				title : '提示信息',
				msg : msg,
				timeout : 1000 * 6
			});
		}
		
		function reloadTable(){
			window.location.reload();
		}

		(function($){
			$("#btnDownload").click(function(){
				var data = getParaData();
				$('#d').val(JSON.stringify(data));
				var formObj = document.getElementById("forDownloading");
			    formObj.submit();
			});
			
			$("#btnUpload").click(function(){
				$.dialog({
				    content: 'url:xlsupload.jsp?uploader=' + encodeURIComponent('indexparamanage/para_dg_import.jsp?indexcode=${param.indexcode}&periodcode=${param.periodcode}'),
				    cache:false,
				    button: [{
					            name: '开始上传',
					            callback: function(){
					            	iframe = this.iframe.contentWindow;
									iframe.upload();
									return false;
					            },
					            focus: true
				        	  },{
					            name: '取消上传',
					            callback: function(){
					            	iframe = this.iframe.contentWindow;
									iframe.cancel();
				            }
				        }
				    ]
				});
			});
			
			$("#btnSubmit").click(function(){
				$.dialog.confirm("确定保存?", function() {
					onSubmit();
				}, function() {
				}, window);
			});
			
			$("#btnReload").click(function(){
				refresh();
			});
			
			function getParaData(){
				endEditing();
				
				var data = $('#para_tb').datagrid('getData');
				var results = {};
				for(var i = 0; i<data.rows.length; i++) {
					var row = data.rows[i];
					results[row.objcode] = {};
					for(var attr in row){
						if(attr.indexOf("para_") === 0){
							if(row[attr]){
								results[row.objcode][attr] = row[attr];	
							} else {
								results[row.objcode][attr] = "";
							}
						}
					}
				}
				
				results.indexcode='${param.indexcode}';
				results.relateyear='${param.year}';
				results.periodcode='${param.periodcode}';
				return results;
			}
			
			function onSubmit(){
				var results = getParaData();
				$.ajax({
					url: "parahandler.jsp",
					type: "POST",
					data: {d:JSON.stringify(results)},
					success: function(data, status){
						if(data.status == 0){
							alert("保存成功");
						}
						else if(data.status == 1){
							alert("获取数据库连接失败");
						}
						else if(data.status == 3){
							alert("插入数据失败");
						}
					},
					error: function(jqXHR, status, errThrown){
						alert("status:" + status);
						alert("errThrown:" + errThrown);
					}
				});
			};	
	
			
			function refresh(){
				window.location.reload();
			}
		})($);
					
       $('#para_tb').datagrid();
       var editIndex = undefined;
       function endEditing(){
           if (editIndex == undefined){return true;}
           if ($('#para_tb').datagrid('validateRow', editIndex)){
            $('#para_tb').datagrid('endEdit', editIndex);
               editIndex = undefined;
               return true;
           } else {
               return false;
           }
       }
       function onClickRow(index){
           if (editIndex != index){
               if (endEditing()){
                   $('#para_tb').datagrid('selectRow', index).datagrid('beginEdit', index);
                   editIndex = index;
               } else {
                   $('#para_tb').datagrid('selectRow', editIndex);
               }
           }
       }
       
       function reject(){
           $('#para_tb').datagrid('rejectChanges');
           editIndex = undefined;
       }
</script>
<%
	} catch (Exception e){
		if(out != null){
			out.write(e.getMessage());
		}
		e.printStackTrace();
	}
%>
</body>
</html>
