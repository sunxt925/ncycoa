function check_primaryKey(item1,item2)
{ 
   var res="";
   
  if(item1.value=="y"){
    if(item2.value=="" || item2.value==undefined)
		res+="�ֶ�Ϊ����������Ϊ��!\n";
	}
   return res;
  
}
function check_strLength(item1,item2)
{ 
   var res="";
  if(item1.value<item2.value.length){
    
    res+="�ֶ������ַ������ȳ�������!\n";
    }
    return res;
}
function check_strType(item1,item2)
{
   var res="";
  if(item1.value=="number"){
     var myRegExp=/^[-\+]?\d+(\.\d+)?$/;
	  if(myRegExp.test(item2.value)==false)
	  {
	  res+="�ֶ������ַ���Ч��������������!\n";
	  }
  }
  return res;
}