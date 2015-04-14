function saveAsExcel(tableid){ 
	//整个表格拷贝到EXCEL中   
		   var jXls; 
		   try { 
		    jXls = new ActiveXObject('Excel.Application'); 
		   }catch (e) { 
		    alert("无法启动Excel!\n\n如果您确信您的电脑中已经安装了Excel，"+"那么请调整IE的安全级别。\n\n具体操作：\n\n"+"工具 → Internet选项 → 安全 → 自定义级别 → 对没有标记为安全的ActiveX进行初始化和脚本运行 → 启用"); 
		    return false; 
		   } 
	  var  curTbl = document.getElementById(tableid);  
	    
	  var  oXL = new  ActiveXObject("Excel.Application" );  
	    
	  //创建AX对象excel   
	    
	  var  oWB = oXL.Workbooks.Add();  
	    
	  //获取workbook对象   
	    
	  var  oSheet = oWB.ActiveSheet;  
	    
	  //激活当前sheet   
	    
	  var  sel = document.body.createTextRange();  
	    
	  sel.moveToElementText(curTbl);  
	    
	  //把表格中的内容移到TextRange中   
	    
// 	  sel.select();  
	    
	  //全选TextRange中内容   
	    
	  sel.execCommand("Copy" );  
	    
	  //复制TextRange中内容   
	    
	  oSheet.Paste();  
	    
	  //粘贴到活动的EXCEL中   
	    
	  oXL.Visible = true ;  
	    
	  //设置excel可见属性   
	    
	  }  