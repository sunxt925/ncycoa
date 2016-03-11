<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body>
	<h:datagrid actionUrl="objresult.htm?totaldgdata" fit="true" fitColumns="true" queryMode="group" name="objresultlist">
		<h:dgColumn field="ID" title="id" hidden="true"></h:dgColumn>
     	<h:dgColumn field="archCode" title="体系编码" dictionary="tbm_objindex,index_code,index_name"></h:dgColumn>
		<h:dgColumn field="objectCode" title="对象编码" dictionary="tbm_objindexarchuser,object_code,uniindex_code"></h:dgColumn>
		<h:dgColumn field="totalScore" title="总得分"></h:dgColumn>
		<h:dgColumn field="year" title="年份"  query="true"></h:dgColumn>
		<h:dgColumn field="season" title="季度"  query="true"></h:dgColumn>
		<h:dgToolBar url="checkplan_management.htm?update" icon="icon-reload" funname="abc" title="导出"></h:dgToolBar>
	</h:datagrid>
</body>
<script type="text/javascript">

function abc(gname) {  
    //获取Datagride的列  
    var rows = $('#objresultlist').datagrid('getRows');  
    
    var columns = $("#objresultlist").datagrid("options").columns[0];  
    var oXL = new ActiveXObject("Excel.Application"); //创建AX对象excel   
    var oWB = oXL.Workbooks.Add; //获取workbook对象   
   // var oSheet = oWB.ActiveSheet; //激活当前sheet  
   var oSheet=oWB.Worksheets(1);
    //设置工作薄名称  
    oSheet.name = "导出Excel报表";  
    //设置表头  
    for (var i = 0; i < columns.length; i++) {  
        oSheet.Cells(1, i+1).value = columns[i].title;  
    }  
    //设置内容部分  
    for (var i = 0; i < rows.length; i++) {  
        //动态获取每一行每一列的数据值  
        for (var j = 0; j < columns.length; j++) {                 
            oSheet.Cells(i + 2, j+1).value = rows[i][columns[j].field];  
        }     
    }                
    oXL.Visible = true; //设置excel可见属性  
  //  oSheet.SaveAs(BrowseFolder()+"temp.xls");  
}  
function BrowseFolder() {
    try {
        var Message = "Please select the folder path.";  //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 0x0040, 0x11); //起始目录为：我的电脑
        //var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
        if (Folder != null) {
            Folder = Folder.items();  // 返回 FolderItems 对象
            Folder = Folder.item();  // 返回 Folderitem 对象
            Folder = Folder.Path;   // 返回路径
            if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
            return Folder;
        }
    } catch (e) {
        alert(e.message);
    }
}
	
	function add(title, actionUrl, gname, width, height) {
		gridname=gname;
		createwindow(title, actionUrl, 600, 500);
	}
	
	function myedit(title, actionUrl, gname, width, height) {
		gridname=gname;
		var rows;
		try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
		try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
		
		if (!rows || rows.length==0) {
			tip('请先选择一条记录');
			return;
		}
		if (rows.length > 1) {
			tip('不能同时对多条记录编辑，请勾选一条记录');
			return;
		}
	
		if(actionUrl.indexOf("?") >= 0) {
			actionUrl += '&id='+ rows[0].id;
		} else {
			actionUrl += '?id='+ rows[0].id;
		}
		createwindow(title, actionUrl, 600, 500);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>