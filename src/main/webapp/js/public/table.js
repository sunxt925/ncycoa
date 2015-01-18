/*
����JavaScript���򣺱����
*/


/*
���ܣ����Ʊ����ĳһ�е�������һ��
������TableObj  ����ı�����
      RowNum    Ҫ���Ƶ��к�
*/
function AddNewTr(TableObj,RowNum)
{
	var tbodyOnlineEdit=TableObj.getElementsByTagName("TBODY")[RowNum];
	var elm = tbodyOnlineEdit.firstChild.cloneNode(true);//���Ƶ�һ��
	
	//var elm = tbodyOnlineEdit.lastChild.cloneNode(true);//�������һ��
	elm.style.display="";
	
	tbodyOnlineEdit.insertBefore(elm);	
	return elm;
}

/*
���ܣ�ɾ��һ�����
������TableObj  Ҫ����ı���������
      RowNum    Ҫɾ�����к�
*/
function DeleteTr(TableObj,RowNum)
{
	TableObj.deleteRow(RowNum);
}

/*
���ܣ�����ɾ������е���
������TableObj Ҫɾ���еı�����
     StartNum ��ʼ��
     StopNum ������
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
���ܣ��õ���ǰ���ĳ�����к�
������TableTrObj  ���Tr����
���أ����ؾ�����кţ�-1��ʾδ�ɹ�
���ã�onmouseover��onmousedown�е��ã��磺onmouseover="aa=GetNuwRowNum(this)"
*/
function GetNowRowNum(TableTrObj)
{
	return TableTrObj.rowIndex;
}

/*
���ܣ�ȫѡ��ѡCheckBox
������CheckBoxName ��ѡ������ SelectFlag ѡ���־
���أ�True/False
*/
function SelectAllCheckBox(CheckBoxName,SelectFlag)
{
	var CkBoxDim;
	CkBoxDim=document.getElementsByName(CheckBoxName);
	for(var i=1;i<CkBoxDim.length;i++)
		CkBoxDim[i].checked=SelectFlag;
}

/*
���ܣ�����ѡ��һ��
������TableTrObj ���Tr����
     CheckBoxObj CheckBox����
*/
function LightTr(TableTrObj,CheckBoxObj)
{
	TableTrObj.style.backgroundColor='#D0E9ED';//���ø���Ϊѡ��״̬
	try{
	    if (CheckBoxObj!=null && CheckBoxObj!=undefined)
		CheckBoxObj.checked=true;
	}
	catch(e){
	}
}

/*
���ܣ�ȡ������ѡ��һ��
������TableTrObj ���Tr����
     CheckBoxObj CheckBox����
*/
function NoLightTr(TableTrObj,CheckBoxObj)
{
	TableTrObj.style.backgroundColor='';//���ø���Ϊѡ��״̬
	try{
	    if (CheckBoxObj!=null && CheckBoxObj!=undefined)
		CheckBoxObj.checked=false;
	}
	catch(e){
	}
}

/*
���ܣ�ɾ��ѡ����������
������TableObj ������  CheckBoxName ��ѡ������  DelFlag ɾ����־��0��ɾ���� 1�������У�
���أ�True/False
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
���ܣ���������ĳһ��������ʾ�����ĳ���е�ĳһ��
������TableTrObj ���Td����
     TdName ����
     Value Ҫ��ʾ��ֵ
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

���ܣ��������е�ֵ��ʾ������ĳһ����
������TableTrObj ���Tr����
     DataObj  ���ݶ���
     DataObjName ���ݶ�������
���أ�True/False

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


���ܣ��������е�ֵ��ֵ�������ĳһ���еĿؼ���
������TableTrObj ���Tr����
     DataObj ���ݶ���
     DataObjName ���ݶ�������
���أ�DataObj �Ѹ�ֵ�Ķ���

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

���ܣ��������ĳһ���пؼ�������д��������
������TableTrObj ���Tr����
	 DataObj ���ݶ���
     DataObjName  ���ݶ�������
���أ�True/False

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
���ܣ��������ĳһ�е�����д��������
������TableTrObj ���Tr����
	 DataObj ���ݶ���
     DataObjName  ���ݶ�������
���أ�True/False
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
���ܣ��������ĳһ�е�ֵ��ֵ��ĳ����������
������TableTrObj ���Td����
     TdName ����
     DataObj ���ݶ���
     DataObjName ���ݶ�������
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
���ܣ��������ĳһ���пؼ���innerText��ֵд�������
������TableTrObj ���Tr����
	 DataObj ���ݶ���
	 DataObjName ���ݶ�������
���أ��ɹ�����DataObj���󡡲��ɹ�����null
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

//������������ȡ�ö�������Ե�ֵ
function getPropValue(data,colname){
	var value;
		
	value = data[colname];
	if (typeof(value)=="function") return "";
	if (_typeof(value)=="Date")
	   value=_dateToStr(value);
	return value;
}
//�ж�javascript�������������
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
//������ת��Ϊ�ַ���
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
���ܣ��������е�ֵд�뵽�����ĳһ���пؼ���innerHTML��
������TableTrObj ���Tr����
	 DataObj ���ݶ���
	 DataObjName ���ݶ�������
���أ��ɹ�����true  ʧ�ܷ���false
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