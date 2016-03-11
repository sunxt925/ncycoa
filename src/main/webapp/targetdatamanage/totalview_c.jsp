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
*{font-size:12px; font-family:΢���ź�,������}
</style>
</head>
<body>
	<h:datagrid actionUrl="objresult.htm?totaldgdata" fit="true" fitColumns="true" queryMode="group" name="objresultlist">
		<h:dgColumn field="ID" title="id" hidden="true"></h:dgColumn>
     	<h:dgColumn field="archCode" title="��ϵ����" dictionary="tbm_objindex,index_code,index_name"></h:dgColumn>
		<h:dgColumn field="objectCode" title="�������" dictionary="tbm_objindexarchuser,object_code,uniindex_code"></h:dgColumn>
		<h:dgColumn field="totalScore" title="�ܵ÷�"></h:dgColumn>
		<h:dgColumn field="year" title="���"  query="true"></h:dgColumn>
		<h:dgColumn field="season" title="����"  query="true"></h:dgColumn>
		<h:dgToolBar url="checkplan_management.htm?update" icon="icon-reload" funname="abc" title="����"></h:dgToolBar>
	</h:datagrid>
</body>
<script type="text/javascript">

function abc(gname) {  
    //��ȡDatagride����  
    var rows = $('#objresultlist').datagrid('getRows');  
    
    var columns = $("#objresultlist").datagrid("options").columns[0];  
    var oXL = new ActiveXObject("Excel.Application"); //����AX����excel   
    var oWB = oXL.Workbooks.Add; //��ȡworkbook����   
   // var oSheet = oWB.ActiveSheet; //���ǰsheet  
   var oSheet=oWB.Worksheets(1);
    //���ù���������  
    oSheet.name = "����Excel����";  
    //���ñ�ͷ  
    for (var i = 0; i < columns.length; i++) {  
        oSheet.Cells(1, i+1).value = columns[i].title;  
    }  
    //�������ݲ���  
    for (var i = 0; i < rows.length; i++) {  
        //��̬��ȡÿһ��ÿһ�е�����ֵ  
        for (var j = 0; j < columns.length; j++) {                 
            oSheet.Cells(i + 2, j+1).value = rows[i][columns[j].field];  
        }     
    }                
    oXL.Visible = true; //����excel�ɼ�����  
  //  oSheet.SaveAs(BrowseFolder()+"temp.xls");  
}  
function BrowseFolder() {
    try {
        var Message = "Please select the folder path.";  //ѡ�����ʾ��Ϣ
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 0x0040, 0x11); //��ʼĿ¼Ϊ���ҵĵ���
        //var Folder = Shell.BrowseForFolder(0,Message,0); //��ʼĿ¼Ϊ������
        if (Folder != null) {
            Folder = Folder.items();  // ���� FolderItems ����
            Folder = Folder.item();  // ���� Folderitem ����
            Folder = Folder.Path;   // ����·��
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
			tip('����ѡ��һ����¼');
			return;
		}
		if (rows.length > 1) {
			tip('����ͬʱ�Զ�����¼�༭���빴ѡһ����¼');
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