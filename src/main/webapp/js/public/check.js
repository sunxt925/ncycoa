/*
����JavaScript����������
*/

/*
���ܣ�����null
������CkStr ������
���أ������null����""�����򷵻�ԭֵ
*/
function IsNullStr(CkStr)
{
	if(CkStr==null || CkStr==undefined)
		return "";
	else
		return CkStr;
}

/*
���ܣ����������ַ����Ƿ���0-9���������
������CkStr Ҫ�����ַ���
���أ�True ���ͨ�� False ���ʧ��
*/
function IsDigit(CkStr)
{
	var myRegExp=/^\d+$/;
	return myRegExp.test(CkStr);
}

/*
���ܣ���֤�ַ����Ƿ�������
������CkStr Ҫ�����ַ���
���أ�True ���ͨ�� False ���ʧ��
*/
function IsNumber(CkStr)
{
	var myRegExp=/^[-\+]?\d+(\.\d+)?$/;
	return myRegExp.test(CkStr);
}

/*
���ܣ��������������Ƿ���ȷ����ʽΪ��yyyy-mm-dd
������CkStr Ҫ�����ַ���
���أ�True ���ͨ�� False ���ʧ��
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
		top.MessageBox.Show(null,"������ʾ��\n\n�����밴 yyyy-mm-dd �ĸ�ʽ�������ڣ��磺2004-02-10����\t",null,"OK","Error"); 	
 		return false;
 	}
	
	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]
	var iaDate = new Array(3)
	var year, month, day

	iaDate = sDate.toString().split("-")
	if (iaDate.length != 3){
		top.MessageBox.Show(null,"�����˷Ƿ������ڣ�",null,"OK","Error"); 	
		return false
	}
	if (iaDate[1].length > 2 || iaDate[2].length > 2){
		top.MessageBox.Show(null,"�����˷Ƿ������ڣ�",null,"OK","Error"); 	
		return false
	}

	year = parseFloat(iaDate[0])
	month = parseFloat(iaDate[1])
	day=parseFloat(iaDate[2])

	if (year < 1900 || year > 2100){
		top.MessageBox.Show(null,"�����˷Ƿ������ڣ�",null,"OK","Error"); 	
		return false
	}
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12){
		top.MessageBox.Show(null,"�����˷Ƿ������ڣ�",null,"OK","Error"); 	
		return false
	}
	if (day < 1 || day > iaMonthDays[month - 1]){
		top.MessageBox.Show(null,"�����˷Ƿ������ڣ�",null,"OK","Error"); 	
		return false
	}
	return true
}
 
 /*
 ���ܣ�����һ���ַ�����ʵ�ʳ��ȣ����ĺ�ȫ��Ϊ2���ַ���Ӣ�ġ����ֺ������ַ���Ϊ1���ַ�
 ������CkStr Ҫ���㳤�ȵ��ַ���
 ���أ��ַ�������
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
���ܣ����������ַ����Ƿ�Ϊһ����ȷ��Email��ַ
������CkStr Ҫ�����ַ���
���أ�True ��һ����ȷ��Email��ַ  False Email��ַ��ʽ����
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
���ܣ������������ֵ�Ƿ�Ϊ��
������CkObj Ҫ���Ķ���  ErrInfo�����Ϊ����ʾ�Ĵ�����Ϣ
���أ����ͨ������ true ���򷵻� false
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
���ܣ������������ֵ�Ƿ񳬳�
������CkObj Ҫ���Ķ���  ErrInfo���������ʱ��ʾ�Ĵ�����Ϣ  StrMaxLen������ַ�������
���أ����ͨ������ true  ���򷵻� false
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
���ܣ�ȡ�����N���ַ�
������Str �ַ��� Num ����
���أ��ַ���
*/
function LeftStr(Str,Num)
{
	return Str.substr(0,Num);
}

/*
���ܣ�ȡ���ұ�N���ַ�
������Str �ַ��� Num ����
���أ��ַ���
*/
function RightStr(Str,Num)
{
	return Str.substr(Str.length-Num,Num);
}

/*
���ܣ����Form�и��ؼ���ֵ�Ƿ�仯
������FormObj ������
���أ�True/False
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
���ܣ����ñ��и��ؼ���ʼֵ
������FormObj ������
���أ�True �б仯 False û�б仯
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

//�ַ���ת��Ϊ����
function strToDate(str)
{
  var val=Date.parse(str);
  var newDate=new Date(val);
  return newDate;
} 

/*
���ܣ�����������ʾ
*/
var PopWidth = 202; //��Ϣ����
var PopHeight = 52; //��Ϣ��߶�
var PopBorder = 25; //����Ļ��Ե�ľ���
var PopShow = 500; //��Ϣ�����ʾʱ��
var PopTop =0;
var showtime, hidetime;
var oPopup = window.createPopup();
function popmsg(msgstr){
oPopup.document.body.innerHTML = '<div id="infodiv" style="width: 180; height: 50; left: 0; top: 0; position: absolute;background-color:#eeeeee;border-color:#000000;border-style:solid;border-width:1px"><table border="0"  cellpadding="0" cellspacing="0" width="200" height="50" style="font-size:9pt"><tr><td align="center">'+msgstr+'</td></tr><tr><td align="center"><img border="0" src="/shbxweb/images/loading.gif" width="94" height="17"></td></tr></table></div>';
//��Ϣ�����ʽ
popshow();
//oPopup.document.body.onclick = pophide; //������Ϣ��ʱ��ʼ����
}

function popshow(){
oPopup.show((screen.width - (PopWidth + PopBorder))/2, (screen.height - PopTop)/2, PopWidth, PopHeight);
}

function pophide(){
//oPopup.show((screen.width - (PopWidth + PopBorder))/2, (screen.height - PopTop)/2, PopWidth, PopHeight);
oPopup.hide(); //��ȫ������Ϣ��
}
