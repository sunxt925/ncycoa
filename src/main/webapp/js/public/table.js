/*
公共JavaScript程序：表格处理
*/


/*
功能：复制表格中某一行到表格最后一行
参数：TableObj  处理的表格对象
      RowNum    要复制的行号
*/
function AddNewTr(TableObj,RowNum)
{
	var tbodyOnlineEdit=TableObj.getElementsByTagName("TBODY")[RowNum];
	var elm = tbodyOnlineEdit.firstChild.cloneNode(true);//复制第一行
	
	//var elm = tbodyOnlineEdit.lastChild.cloneNode(true);//复制最后一行
	elm.style.display="";
	
	tbodyOnlineEdit.insertBefore(elm);	
	return elm;
}

/*
功能：删除一表格行
参数：TableObj  要处理的表格对象名称
      RowNum    要删除的行号
*/
function DeleteTr(TableObj,RowNum)
{
	TableObj.deleteRow(RowNum);
}

/*
功能：批量删除表格中的行
参数：TableObj 要删除行的表格对象
     StartNum 起始行
     StopNum 结束行
*/
function DeleteMutilTr(TableObj,StartNum,StopNum)
{
	if(TableObj==null || TableObj==undefined)return;
	if(StartNum<0 || StartNum==null || StartNum==undefined)return;
	if(StopNum<0 || StopNum==null || StopNum==undefined)return;
	if(StopNum==0)
		StopNum=TableObj.rows.length;
	else
		StopNum+=1;
	for(var i=(StopNum-1);i>=StartNum;i--)
		DeleteTr(TableObj,i);
}

/*
功能：得到当前鼠标某表格的行号
参数：TableTrObj  表格Tr对象
返回：返回具体的行号，-1表示未成功
调用：onmouseover、onmousedown中调用，如：onmouseover="aa=GetNuwRowNum(this)"
*/
function GetNowRowNum(TableTrObj)
{
	return TableTrObj.rowIndex;
}

/*
功能：全选或反选CheckBox
参数：CheckBoxName 复选框名称 SelectFlag 选择标志
返回：True/False
*/
function SelectAllCheckBox(CheckBoxName,SelectFlag)
{
	var CkBoxDim;
	CkBoxDim=document.getElementsByName(CheckBoxName);
	for(var i=1;i<CkBoxDim.length;i++)
		CkBoxDim[i].checked=SelectFlag;
}

/*
功能：高亮选择一行
参数：TableTrObj 表格Tr对象
     CheckBoxObj CheckBox对象
*/
function LightTr(TableTrObj,CheckBoxObj)
{
	TableTrObj.style.backgroundColor='#D0E9ED';//设置该行为选定状态
	try{
	    if (CheckBoxObj!=null && CheckBoxObj!=undefined)
		CheckBoxObj.checked=true;
	}
	catch(e){
	}
}

/*
功能：取消高亮选择一行
参数：TableTrObj 表格Tr对象
     CheckBoxObj CheckBox对象
*/
function NoLightTr(TableTrObj,CheckBoxObj)
{
	TableTrObj.style.backgroundColor='';//设置该行为选定状态
	try{
	    if (CheckBoxObj!=null && CheckBoxObj!=undefined)
		CheckBoxObj.checked=false;
	}
	catch(e){
	}
}

/*
功能：删除选定的所有行
参数：TableObj 表格对象  CheckBoxName 复选框名称  DelFlag 删除标志（0：删除行 1：隐藏行）
返回：True/False
*/
function DeleteSelectTr(TableObj,CheckBoxName,DelFlag)
{
	var CkBoxDim;
	try
	{
		CkBoxDim=document.getElementsByName(CheckBoxName);
		for(var i=1;i<CkBoxDim.length;i++)
		{
			if(CkBoxDim[i].checked)
			{
				if(DelFlag==0)
					DeleteTr(TableObj,i);
				else
					TableObj.rows[i].style.display="none";
			}
		}
		return true;
	}
	catch(e)
	{
		return false;
	}
}

/*
功能：将对像中某一个属性显示到表格某行中的某一列
参数：TableTrObj 表格Td对象
     TdName 列名
     Value 要显示的值
*/
function DataToTd(TableTrObj,TdName,Value)
{
	for(var i=0;i<TableTrObj.cells.length;i++)
	{
		if(TableTrObj.cells[i].id==TdName)
			TableTrObj.cells[i].innerText=Value;
	}
}


/*

功能：将对象中的值显示到表格的某一行中
参数：TableTrObj 表格Tr对象
     DataObj  数据对象
     DataObjName 数据对象名称
返回：True/False

function ObjDataToTrObj(TableTrObj,DataObj,DataObjName)
{
	var ObjStr="";
	try
	{
		if(TableTrObj==null || TableTrObj==undefined) return false;
		if(DataObj==null || DataObj==undefined) return false;
		if(DataObjName==null || DataObjName=="") return false;
		for(var i=0;i<TableTrObj.cells.length;i++)
		{
			ObjStr=DataObjName+"."+TrObj.cells[i].id;
			TableTrObj.cells[i].innerText=eval(ObjStr);
		}
		return true;
	}
	catch(e)
	{
		return false;
	}
}


功能：将对象中的值赋值到表格中某一行中的控件中
参数：TableTrObj 表格Tr对象
     DataObj 数据对象
     DataObjName 数据对象名称
返回：DataObj 已赋值的对象

function ObjDataToTrObj1(TableTrObj,DataObj,DataObjName)
{
	var ObjStr="";
	for(var i=0;i<TableTrObj.cells.length;i++)
	{
		ObjStr=DataObjName+"."+TrObj.cells[i].id;
		document.getElementsByName(DataObjName)[GetNowRowNum(TableTrObj)].value=eval(ObjStr);
	}
	return true;
}

功能：将表格中某一行中控件的数据写到对象中
参数：TableTrObj 表格Tr对象
	 DataObj 数据对象
     DataObjName  数据对象名称
返回：True/False

function TrObjToObjData1(TableTrObj,DataObj,DataObjName)
{
	var ObjStr="";
	for(var i=0;i<TableTrObj.cells.length;i++)
	{
		ObjStr=DataObjName+"."+TrObj.cells[i].id;
		eval(ObjStr)=document.getElementsByName(DataObjName)[GetNowRowNum(TableTrObj)].value;
	}
	return DataObj;
}
*/

/*
功能：将表格中某一行的数据写到对象中
参数：TableTrObj 表格Tr对象
	 DataObj 数据对象
     DataObjName  数据对象名称
返回：True/False
*/
function TrObjToObjData(TableTrObj,DataObj,DataObjName)
{
	var ObjStr="";
	try
	{
		if(TableTrObj==null || TableTrObj==undefined) return false;
		if(DataObj==null || DataObj==undefined) return false;
		if(DataObjName==null || DataObjName=="") return false;
		for(var i=0;i<TableTrObj.cells.length;i++)
		{
			ObjStr=DataObjName+"."+TrObj.cells[i].id;
			eval(ObjStr)=TableTrObj.cells[i].innerText;
		}
		return true;
	}
	catch(e)
	{
		return false;
	}
}

/*
功能：将表格中某一列的值赋值到某对象属性中
参数：TableTrObj 表格Td对象
     TdName 列名
     DataObj 数据对象
     DataObjName 数据对象名称
*/
function TdToData(TableTrObj,TdName,DataObj,DataObjName)
{
	var ObjStr="";
	for(var i=0;i<TableTrObj.cells.length;i++)
	{
		if(TableTrObj.cells[i].id==TdName)
		{
			ObjStr=DataObjName+"."+TdName;
			eval(ObjStr)=TableTrObj.cells[i].innerText;
		}
	}
}

/*
功能：将表格中某一行中控件或innerText的值写入对象中
参数：TableTrObj 表格Tr对象
	 DataObj 数据对象
	 DataObjName 数据对象名称
返回：成功返回DataObj对象　不成功返回null
*/
function TrObjToDataObj2(TableTrObj,DataObj)
{
	var ObjStr="";
	var InnerHtmlStr="";
	try
	{
		if(TableTrObj==null || TableTrObj==undefined) return null;
		if(DataObj==null || DataObj==undefined) return null;
		for(var i=0;i<TableTrObj.cells.length;i++)
		{
			ObjStr="DataObj."+TableTrObj.cells[i].id;
			InnerHtmlStr=TableTrObj.cells[i].innerHTML;
			if(InnerHtmlStr.indexOf("<input ")==-1 && InnerHtmlStr.indexOf("<select ")==-1)
				eval(ObjStr)=TableTrObj.cells[i].innerText;
			else
				eval(ObjStr)=document.getElementsByName(TableTrObj.cells[i].id+"a")[GetNowRowNum(TableTrObj)].value
		}
		return DataObj;
	}
	catch(e)
	{
		return null;
	}
}

//根据属性名，取得对象该属性的值
function getPropValue(data,colname){
	var value;
		
	value = data[colname];
	if (typeof(value)=="function") return "";
	if (_typeof(value)=="Date")
	   value=_dateToStr(value);
	return value;
}
//判断javascript对象的数据类型
function _typeof(objClass) 
{ 
    if ( objClass != undefined && objClass.constructor ) 
    { 
        var strFun = objClass.constructor.toString(); 
        var className = strFun.substr(0, strFun.indexOf('(')); 
        className = className.replace('function', ''); 
        return className.replace(/(^\s*)|(\s*$)/ig, ''); 
    } 
    return typeof(objClass); 
}
//将日期转换为字符串
function _dateToStr(dateObj){
    if (_typeof(dateObj)!="Date") return;
	var s ="-";
	
	var mon =dateObj.getMonth()+1;
	var day =dateObj.getDate();
 	mon = (mon<10?"0":"")+mon;
 	day = (day<10?"0":"")+day;

 	var dtStr = dateObj.getFullYear()+s+mon+s+day;
 	return dtStr;
}

/*
功能：将对象中的值写入到表格中某一行中控件或innerHTML中
参数：TableTrObj 表格Tr对象
	 DataObj 数据对象
	 DataObjName 数据对象名称
返回：成功返回true  失败返回false
*/
function DataObjToTrObj2(TableTrObj,DataObj)
{
	var ObjStr="";
	var InnerHtmlStr="";
	try
	{
		if(TableTrObj==null || TableTrObj==undefined) return false;
		if(DataObj==null || DataObj==undefined) return false;
		for(var ii=0;ii<TableTrObj.cells.length;ii++)
		{
 			if(getPropValue(DataObj,TableTrObj.cells[ii].id)==undefined || getPropValue(DataObj,TableTrObj.cells[ii].id)==null)
 				continue;
			//ObjStr="DataObj."+TableTrObj.cells[ii].id;
			InnerHtmlStr=TableTrObj.cells[ii].innerHTML;
			if(InnerHtmlStr.indexOf("<INPUT ")==-1 && InnerHtmlStr.indexOf("<SELECT ")==-1){
//				TableTrObj.cells[ii].innerText=eval(ObjStr);
				TableTrObj.cells[ii].innerText=getPropValue(DataObj,TableTrObj.cells[ii].id);
			//else if(InnerHtmlStr.indexOf("<input type=\"hidden\"")!=-1)
			//	TableTrObj.cells[i].innerHTML="<input type=\"hidden\" name=\""+TrObj.cells[i].id+"\" value=\""+eval(ObjStr)+"\">"+eval(ObjStr);
			}
			else
			{
				try
				{
///				alert(getPropValue(DataObj,TableTrObj.cells[ii].id));
//					if(getPropValue(DataObj,TableTrObj.cells[ii].id)!=undefined)
						document.getElementsByName(TableTrObj.cells[ii].id+"a")[TableTrObj.rowIndex].value=getPropValue(DataObj,TableTrObj.cells[ii].id);
				}
				catch(e){}
			}
		}
		return true;
	}
	catch(e)
	{
		return false;
	}
}