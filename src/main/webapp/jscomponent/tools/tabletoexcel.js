function saveAsExcel(tableid){ 
	//������񿽱���EXCEL��   
		   var jXls; 
		   try { 
		    jXls = new ActiveXObject('Excel.Application'); 
		   }catch (e) { 
		    alert("�޷�����Excel!\n\n�����ȷ�����ĵ������Ѿ���װ��Excel��"+"��ô�����IE�İ�ȫ����\n\n���������\n\n"+"���� �� Internetѡ�� �� ��ȫ �� �Զ��弶�� �� ��û�б��Ϊ��ȫ��ActiveX���г�ʼ���ͽű����� �� ����"); 
		    return false; 
		   } 
	  var  curTbl = document.getElementById(tableid);  
	    
	  var  oXL = new  ActiveXObject("Excel.Application" );  
	    
	  //����AX����excel   
	    
	  var  oWB = oXL.Workbooks.Add();  
	    
	  //��ȡworkbook����   
	    
	  var  oSheet = oWB.ActiveSheet;  
	    
	  //���ǰsheet   
	    
	  var  sel = document.body.createTextRange();  
	    
	  sel.moveToElementText(curTbl);  
	    
	  //�ѱ���е������Ƶ�TextRange��   
	    
// 	  sel.select();  
	    
	  //ȫѡTextRange������   
	    
	  sel.execCommand("Copy" );  
	    
	  //����TextRange������   
	    
	  oSheet.Paste();  
	    
	  //ճ�������EXCEL��   
	    
	  oXL.Visible = true ;  
	    
	  //����excel�ɼ�����   
	    
	  }  