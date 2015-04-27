/*
公共JavaScript程序：输入检查
*/

/*
功能：过滤null
参数：CkStr 检查对象
返回：如果是null返回""，否则返回原值
*/
function IsNullStr(CkStr)
{
	if(CkStr==null || CkStr==undefined)
		return "";
	else
		return CkStr;
}

/*
功能：检查输入的字符串是否由0-9的数字组成
参数：CkStr 要检查的字符串
返回：True 检查通过 False 检查失败
*/
function IsDigit(CkStr)
{
	var myRegExp=/^\d+$/;
	return myRegExp.test(CkStr);
}

/*
功能：验证字符串是否是数字
参数：CkStr 要检查的字符串
返回：True 检查通过 False 检查失败
*/
function IsNumber(CkStr)
{
	var myRegExp=/^[-\+]?\d+(\.\d+)?$/;
	return myRegExp.test(CkStr);
}

/*
功能：检查输入的日期是否正确，格式为：yyyy-mm-dd
参数：CkStr 要检查的字符串
返回：True 检查通过 False 检查失败
*/
function isDate(sDate){
	if (sDate.length==0) return true;

 	var pattern=/^\d{4}-\d{2}-\d{2}$/;
 	if (!pattern.test(sDate)) return false;

	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]
	var iaDate = new Array(3)
	var year, month, day
	
	iaDate = sDate.toString().split("-")
	if (iaDate.length != 3) return false
	if (iaDate[1].length > 2 || iaDate[2].length > 2) return false
	
	year = parseFloat(iaDate[0])
	month = parseFloat(iaDate[1])
	day=parseFloat(iaDate[2])
	
	if (year < 1900 || year > 2100) return false
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12) return false
	if (day < 1 || day > iaMonthDays[month - 1]) return false
	return true
}

function ChkDate(sDate)
{ 
	if (sDate.length==0) return true;
 	var pattern=/^\d{4}-\d{2}-\d{2}$/;
 	if (!pattern.test(sDate)){
		top.MessageBox.Show(null,"输入提示：\n\n　　请按 yyyy-mm-dd 的格式输入日期（如：2004-02-10）。\t",null,"OK","Error"); 	
 		return false;
 	}
	
	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]
	var iaDate = new Array(3)
	var year, month, day

	iaDate = sDate.toString().split("-")
	if (iaDate.length != 3){
		top.MessageBox.Show(null,"输入了非法的日期！",null,"OK","Error"); 	
		return false
	}
	if (iaDate[1].length > 2 || iaDate[2].length > 2){
		top.MessageBox.Show(null,"输入了非法的日期！",null,"OK","Error"); 	
		return false
	}

	year = parseFloat(iaDate[0])
	month = parseFloat(iaDate[1])
	day=parseFloat(iaDate[2])

	if (year < 1900 || year > 2100){
		top.MessageBox.Show(null,"输入了非法的日期！",null,"OK","Error"); 	
		return false
	}
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12){
		top.MessageBox.Show(null,"输入了非法的日期！",null,"OK","Error"); 	
		return false
	}
	if (day < 1 || day > iaMonthDays[month - 1]){
		top.MessageBox.Show(null,"输入了非法的日期！",null,"OK","Error"); 	
		return false
	}
	return true
}
 
 /*
 功能：返回一个字符串的实际长度，中文和全角为2个字符，英文、数字和特殊字符等为1个字符
 参数：CkStr 要计算长度的字符串
 返回：字符串长度
 */
function StrLenthByByte(CkStr) 
{ 
	var len; 
	var i;
	len = 0; 
	for (i=0;i<CkStr.length;i++) 
	{ 
		if(CkStr.charCodeAt(i)>255)
			len+=2;
		else
			len++; 
	} 
	return len; 
} 

/*
功能：检查输入的字符串是否为一个正确的Email地址
参数：CkStr 要检查的字符串
返回：True 是一个正确的Email地址  False Email地址格式错误
*/
function IsEmailAddress(CkStr) 
{ 
	var pattern=/^[a-zA-Z0-9\-]+@[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3})$/; 
	if(pattern.test(obj)) 
		return true; 
	else 
		return false;
}

/*
功能：检查输入对象的值是否为空
参数：CkObj 要检查的对象  ErrInfo：如果为空显示的错误信息
返回：检查通过返回 true 否则返回 false
*/
function CkEmptyStr(CkObj,ErrInfo)
{
	if(CkObj.value=="")
	{
		//alert(ErrInfo);
		top.MessageBox.Show(null,ErrInfo,null,"OK","Error");
		try
		{
			CkObj.focus();
		}
		catch(e){}
		return false;
	}
	return true;
}

/*
功能：检查输入对象的值是否超长
参数：CkObj 要检查的对象  ErrInfo：如果超长时显示的错误信息  StrMaxLen：最大字符串长度
返回：检查通过返回 true  否则返回 false
*/
function CkMaxLenStr(CkObj,ErrInfo,StrMaxLen)
{
	if(StrLenthByByte(CkObj.value)>StrMaxLen)
	{
		//alert(ErrInfo);
		top.MessageBox.Show(null,ErrInfo,null,"OK","Error");
		try
		{
			CkObj.focus();
		}
		catch(e){}
		return false;
	}
	return true;
}

/*
功能：取最左边N个字符
参数：Str 字符串 Num 长度
返回：字符串
*/
function LeftStr(Str,Num)
{
	return Str.substr(0,Num);
}

/*
功能：取最右边N个字符
参数：Str 字符串 Num 长度
返回：字符串
*/
function RightStr(Str,Num)
{
	return Str.substr(Str.length-Num,Num);
}

/*
功能：检查Form中各控件的值是否变化
参数：FormObj 表单对象
返回：True/False
*/
function FormChange(FormObj)
{
	var ObjDim=FormObj.elements;
	try
	{
		for(var i=0;i<ObjDim.length;i++)
		{
			switch(ObjDim[i].type)
			{
				case "checkbox":
					if(ObjDim[i].defaultChecked!=ObjDim[i].checked)
						return true;
					break;
				case "password":
					if(ObjDim[i].defaultChecked!=ObjDim[i].checked)
						return true;
					break;
				case "radio":
					if(ObjDim[i].defaultChecked!=ObjDim[i].checked)
						return true;
					break;
				case "text":
					if(ObjDim[i].defaultValue!=ObjDim[i].value)
						return true;
					break;
				case "hidden":
					if(ObjDim[i].defaultValue!=ObjDim[i].value)
						return true;
					break;				
				case "file":
					if(ObjDim[i].defaultValue!=ObjDim[i].value)
						return true;
					break;				
				case "select-one":
					var OptObj=ObjDim[i].options;
					for(var j=0;j<OptObj.length;j++)
					{
						if(OptObj[j].defaultSelected!=OptObj[j].selected)
							return true;
					}
					break;
				case "select-multiple":
					var OptObj=ObjDim[i].options;
					for(var j=0;j<OptObj.length;j++)
					{
						if(OptObj[j].defaultSelected!=OptObj[j].selected)
							return true;
					}
					break;
			}			
		}
	}
	catch(e)
	{
		return false;
	}
	return false;
}

/*
功能：重置表单中各控件初始值
参数：FormObj 表单对象
返回：True 有变化 False 没有变化
*/
function ResetFormValue(FormObj)
{
	var ObjDim=FormObj.elements;
	try
	{
		for(var i=0;i<ObjDim.length;i++)
		{
			switch(ObjDim[i].type)
			{
				case "checkbox":
					ObjDim[i].defaultChecked=ObjDim[i].checked;
					break;
				case "password":
					ObjDim[i].defaultChecked=ObjDim[i].checked;
					break;
				case "radio":
					ObjDim[i].defaultChecked=ObjDim[i].checked;
					break;
				case "text":
					ObjDim[i].defaultValue=ObjDim[i].value;
					break;
				case "file":
					ObjDim[i].defaultValue=ObjDim[i].value;
					break;				
				case "select-one":
					var OptObj=ObjDim[i].options;
					for(var j=0;j<OptObj.length;j++)
						OptObj[j].defaultSelected=OptObj[j].selected;
					break;
				case "select-multiple":
					var OptObj=ObjDim[i].options;
					for(var j=0;j<OptObj.length;j++)
						OptObj[j].defaultSelected=OptObj[j].selected;
					break;
			}			
		}
		return true;
	}
	catch(e)
	{
		return false;
	}
}

//字符串转换为日期
function strToDate(str)
{
  var val=Date.parse(str);
  var newDate=new Date(val);
  return newDate;
} 

/*
功能：加载数据提示
*/
var PopWidth = 202; //信息框宽度
var PopHeight = 52; //信息框高度
var PopBorder = 25; //距屏幕边缘的距离
var PopShow = 500; //信息框的显示时间
var PopTop =0;
var showtime, hidetime;
var oPopup = window.createPopup();
function popmsg(msgstr){
oPopup.document.body.innerHTML = '<div id="infodiv" style="width: 180; height: 50; left: 0; top: 0; position: absolute;background-color:#eeeeee;border-color:#000000;border-style:solid;border-width:1px"><table border="0"  cellpadding="0" cellspacing="0" width="200" height="50" style="font-size:9pt"><tr><td align="center">'+msgstr+'</td></tr><tr><td align="center"><img border="0" src="/shbxweb/images/loading.gif" width="94" height="17"></td></tr></table></div>';
//信息框的样式
popshow();
//oPopup.document.body.onclick = pophide; //单击信息框时开始隐藏
}

function popshow(){
oPopup.show((screen.width - (PopWidth + PopBorder))/2, (screen.height - PopTop)/2, PopWidth, PopHeight);
}

function pophide(){
//oPopup.show((screen.width - (PopWidth + PopBorder))/2, (screen.height - PopTop)/2, PopWidth, PopHeight);
oPopup.hide(); //完全隐藏信息框
}
