function check_primaryKey(item1,item2)
{ 
   var res="";
   
  if(item1.value=="y"){
    if(item2.value=="" || item2.value==undefined)
		res+="字段为主键，不能为空!\n";
	}
   return res;
  
}
function check_strLength(item1,item2)
{ 
   var res="";
  if(item1.value<item2.value.length){
    
    res+="字段输入字符串长度超过限制!\n";
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
	  res+="字段输入字符无效，必须输入数字!\n";
	  }
  }
  return res;
}