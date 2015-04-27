//ȫ�ֱ���
var inputFocus;//�ñ�����¼��ǰ�����input
var bKeyDown=false;//��¼���̱����µ�״̬�����м��̰���ʱ��ֵΪtrue

function setRowClass(obj,className){
 obj.className=className;
 var oldClass,toClass;
 if(className=="tableData")  {oldClass="inputTableDataHit";toClass="inputTableData";}
 if(className=="tableDataHit") {oldClass="inputTableData";toClass="inputTableDataHit";}
 var objsInput=obj.all;
 for(var i=0;i<objsInput.length;i++)
  if(objsInput[i].tagName=="INPUT")if(objsInput[i].className==oldClass)objsInput[i].className=toClass;
}

function lightonRow(obj){
 if(obj.tagName!="TR")return;

 //������δ��ѡ�е���ȡ����������ʵ
        var tableOnlineEdit=obj.parentElement;
        while(tableOnlineEdit.tagName!="TABLE")tableOnlineEdit=tableOnlineEdit.parentElement;
 var objsCheckBox=tableOnlineEdit.all("checkLine");
 for(var iCheckBox=1;iCheckBox<objsCheckBox.length;iCheckBox++)
  if(objsCheckBox[iCheckBox].checked==false) setRowClass(tableOnlineEdit.rows[iCheckBox+1],"tableData");

 //��ǰ����и�������ʾ
 setRowClass(obj,"tableDataHit");
}

//�õ�obj���ϼ�Ԫ��TagName
function getUpperObj(obj,TagName){
 var strTagName=TagName.toUpperCase();
 var objUpper=obj.parentElement;
 while(objUpper){
  if(objUpper.tagName==strTagName) break;
  objUpper=objUpper.parentElement;
 }
 return objUpper;
}

function getPosition(obj,pos){
   var t=eval("obj."+pos);
   while(obj=obj.offsetParent){
      t+=eval("obj."+pos);
   }
   return t;
}
function showInputSelect(obj,objShow){
 inputFocus=obj;//��¼��ǰ����input��ȫ�ֱ���
 objShow.style.top =getPosition(obj,"offsetTop")+obj.offsetHeight+2;
 objShow.style.left=getPosition(obj,"offsetLeft");
 objShow.style.width=obj.offsetWidth;
 objShow.value=obj.value;
 objShow.style.display="block";
}

function setInputFromSelect(objTo,objShow){
 objTo.value=objShow.options[objShow.selectedIndex].value;
 //objShow.style.display="none";
}

function hideInput(obj){
 obj.style.display="none";
}

function clearRow(objTable){
  var tbodyOnlineEdit=objTable.getElementsByTagName("TBODY")[0];
  for (var i=tbodyOnlineEdit.children.length-1;i>=0;i--)
    tbodyOnlineEdit.deleteRow(i);
}

function deleteRow(objTable){
 var tbodyOnlineEdit=objTable.getElementsByTagName("TBODY")[0];
 for (var i=tbodyOnlineEdit.children.length-1; i>=0 ; i-- )
  if (tbodyOnlineEdit.children[i].firstChild.firstChild.checked)
   tbodyOnlineEdit.deleteRow(i);
}

function addRow(objTable){
 var tbodyOnlineEdit=objTable.getElementsByTagName("TBODY")[0];
 var theadOnlineEdit=objTable.getElementsByTagName("THEAD")[0];
 var elm = theadOnlineEdit.lastChild.cloneNode(true);
 elm.style.display="";
 tbodyOnlineEdit.insertBefore(elm);
 
 return elm;
}

//��ָ�������е�objRow��������strName����ΪstrValue
function setInputValue(objRow,strName,strValue){
 var objs=objRow.all;
 for(var i=0;i<objs.length;i++){
  if(objs[i].name==strName)objs[i].value=strValue;
 }
}

//���һ��ʵ��������
function addInstanceRow(objTable,Names,Values){
 var tbodyOnlineEdit=objTable.getElementsByTagName("TBODY")[0];
 var theadOnlineEdit=objTable.getElementsByTagName("THEAD")[0];
 var elm = theadOnlineEdit.lastChild.cloneNode(true)
 elm.style.display="";
        for(var i=0;i<Names.length;i++)
          setInputValue(elm,Names[i],Values[i]);
 tbodyOnlineEdit.insertBefore(elm);
 return elm;
}

//��ȫ����ѡ����Ϊָ��ֵ
function setOnlineEditCheckBox(obj,value){
 var tbodyOnlineEdit=obj.getElementsByTagName("TBODY")[0];
 for (var i=tbodyOnlineEdit.children.length-1; i>=0 ; i-- )
  tbodyOnlineEdit.children[i].firstChild.firstChild.checked=value;
}

//Ϊ��̬������Ӽ��̵�������,Ҫʹ�øù������ڱ�����������¼�����onKeyDown="navigateKeys()" onKeyUp="setKeyDown(false)"
//��һ������⣬������"->"��ת����һ������ʱ�������ʾ�ڵ�һ���ַ�֮��
//������Ȼʹ��Tab����ת
function navigateKeys(){
 if(bKeyDown) return;
 bKeyDown=true;
 var elm=event.srcElement;
 if(elm.tagName!="INPUT") return;//Ĭ��ֻ��INPUT���е������������趨

 var objTD=elm.parentElement;
 var objTR=objTD.parentElement;
 var objTBODY=objTR.parentElement.parentElement;
 var objTable=objTBODY.parentElement;

 var nRow=objTR.rowIndex;
 var nCell=objTD.cellIndex;

 var nKeyCode=event.keyCode;
 switch(nKeyCode){
  case 37://<-
   if(getCursorPosition(elm)>0)return;
   nCell--;
   if(nCell==0){
    nRow--;//��ת����һ��
    nCell=objTR.cells.length-1;//���һ��
   }
   break;
  case 38://^
   nRow--;
   break;
  case 39://->
   if(getCursorPosition(elm)<elm.value.length)return;
   nCell++;
   if(nCell==objTR.cells.length){    
    nRow++;//��ת����һ����λ��
    nCell=1;//��һ��
   }
   break;
  case 40://\|/
   nRow++;
   if(nRow==objTBODY.rows.length){    
    addRow(objTable);//����һ������
    nCell=1;//��ת����һ��
   }
   break;
  case 13://Enter
   nCell++;
   if(nCell==objTR.cells.length){    
    nRow++;//��ת����һ����λ��
    nCell=1;//��һ��
   }
   if(nRow==objTBODY.rows.length){    
    addRow(objTable);//����һ������
    nCell=1;//��ת����һ��
   }

   break;
  default://do nothing
   return;
 }
 if(nRow<2 || nRow>=objTBODY.rows.length || nCell<1 ||nCell>=objTR.cells.length) return;

 objTR=objTBODY.rows[nRow];
 objTD=objTR.cells[nCell];
 var objs=objTD.all;
 for(var i=0;i<objs.length;i++){
  //�˴�ʹ��ojbs[0],ʵ��ʹ��ʱ������Ҫ�����޸�,�������������
  try{
   lightonRow(objTR);
   objs[i].focus();//setCursorPosition(objs[i],-1);
   return;
  }catch(e){
   continue;
   //if error occur,continue to next element
  }
 }//end for objs.length
}


//���ü���״̬����bKeyDown��ֵ
function setKeyDown(status){
 bKeyDown=status;
}


//�õ�����λ��
function getCursorPosition(obj){
 var qswh="@#%#^&#*$"
 obj.focus();
 rng=document.selection.createRange();
 rng.text=qswh;
 var nPosition=obj.value.indexOf(qswh)
 rng.moveStart("character", -qswh.length)
 rng.text="";
 return nPosition;
}


//���ù��λ��
function setCursorPosition(obj,position){
 range=obj.createTextRange(); 
 range.collapse(true); 
 range.moveStart('character',position); 
 range.select();
}

//���������ҵ�����ж�Ӧ<TD>Ԫ��
function findTdById(objRow,idName){
	if (objRow==null || objRow==undefined) return null;
	
	var curColTD=objRow.firstChild;
	while (curColTD!=null && curColTD!=undefined){
		if (curColTD.id!=null && curColTD.id!=undefined 
			&& curColTD.id==idName)
			return curColTD;
		curColTD=curColTD.nextSibling;			
	}		 

	return null;
}

//���������ҵ���Ӧ��Ԫ��
function findColByName(objRow,colName){
	if (objRow==null || objRow==undefined) return null;
	
	var curColTD=objRow.firstChild;
	while (curColTD!=null && curColTD!=undefined){
		curCol=curColTD.firstChild;

		//���<TD>��ǩ����û����Ԫ��(��<input><select>��
		if (curCol==null || curCol==undefined){
			curColTD=curColTD.nextSibling;			
			continue;			
		}
		
		if (curCol.name==undefined || curCol.name==null ||
			curCol.name==""){
			curColTD=curColTD.nextSibling;			
			continue;
		}	
		if (curCol.name==colName) return curCol;
		curColTD=curColTD.nextSibling;
	}	

	return null;
}

function setValueByRowNo(objTable,irow,map){
	var tbodyOnlineEdit=objTable.getElementsByTagName("TBODY")[0];
 	if  (irow<=tbodyOnlineEdit.rows.length-1 && irow>=0 ){
 		setRowValues(tbodyOnlineEdit.children[irow],map);
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

function setRowValues(objRow,data){	
	if (objRow==null || objRow==undefined) return;
	if (data == null || data==undefined) return;
	
	var value;
	var curCol;
	var curColTD=objRow.firstChild;
	while (curColTD!=null && curColTD!=undefined){
		curCol=curColTD.firstChild;
		
		//���<TD>��ǩ����û����Ԫ��(��<input><select>��)����ֱ�Ӽ����id����
		if (curCol==null || curCol==undefined ||
			curCol.name==undefined || curCol.name==null || curCol.name==""){
			if (curColTD.id!=null && curColTD.id!=undefined){
	    		value=getPropValue(data,curColTD.id);
	    		if (value!=null) {
	    			curColTD.innerHTML=value;
	    		}	    				
			}
			curColTD=curColTD.nextSibling;			
			continue;			
		}
		
		//���<TD>��ǩ��������Ԫ�أ��������Ԫ�ص�����������ʾֵ
		if (curCol.name==undefined || curCol.name==null ||
			curCol.name==""){
			curColTD=curColTD.nextSibling;			
			continue;
		}	
	    value=getPropValue(data,curCol.name);
	    if (value==null) {
	    	curColTD=curColTD.nextSibling;
	    	continue;
	    }	    
		
		if (_isHTMLElement(curCol, "select")){
			var found  = false;
			var i;
			for (i = 0; i < curCol.options.length; i++){
				if (curCol.options[i].value == value){
					curCol.options[i].selected = true;
					found = true;
				}
				else{
					curCol.options[i].selected = false;
				}
			}
			if (found){
				curColTD=curColTD.nextSibling;			
				continue;
			}

			for (i = 0; i < curCol.options.length; i++){
				if (curCol.options[i].text == value){
					curCol.options[i].selected = true;
					break;
				}
			}
			curColTD=curColTD.nextSibling;			
			continue;
		}
		
		if (_isHTMLElement(curCol, "input")){
			switch (curCol.type){
			case "checkbox":
			case "check-box":
			case "radio":
				curCol.checked = (value == true);
				
				curColTD=curColTD.nextSibling;			
				continue;
			default:
				curCol.value = value;
				
				curColTD=curColTD.nextSibling;			
				continue;
			}
		}

		if (_isHTMLElement(curCol, "textarea")){
			curCol.value = value;
			
			curColTD=curColTD.nextSibling;			
			continue;
		}
		if (val.nodeType){
			if (value.nodeType == 9  ){
				value = value.documentElement;
			}
			value = _importNode(curCol.ownerDocument, value, true);
			curCol.appendChild(value);
			
			curColTD=curColTD.nextSibling;			
			continue;
		}	
		
		curColTD=curColTD.nextSibling;
	}	
}

function findProperty(data,prop){
	var value;
	for (var property in data){
//		if (typeof(property)=="function") continue;
		
		if (property==prop) return true;
	}
	return false;
}

function getRowValues(objRow,data){	
	if (objRow==null || objRow==undefined) return;
	if (data == null || data==undefined) return;
	
	var value;
	var curCol;
	var curColTD=objRow.firstChild;
	while (curColTD!=null && curColTD!=undefined){
		curCol=curColTD.firstChild;
		
		//���<TD>��ǩ����û����Ԫ��(��<input><select>��)����ֱ�Ӽ����id����
		if (curCol==null || curCol==undefined ||
			curCol.name==undefined || curCol.name==null || curCol.name==""){
			if (curColTD.id!=null && curColTD.id!=undefined){
				if (findProperty(data,curColTD.id))
					data[curColTD.id]=curColTD.innerHTML;
			}
			curColTD=curColTD.nextSibling;			
			continue;			
		}
		
		//���<TD>��ǩ��������Ԫ�أ��������Ԫ�ص�����������ʾֵ
		if (curCol.name==undefined || curCol.name==null ||
			curCol.name==""){
			curColTD=curColTD.nextSibling;			
			continue;
		}	
		
		//�����data������δ�ҵ�curCol.name��
		if (!findProperty(data,curCol.name)) {
	    	curColTD=curColTD.nextSibling;
	    	continue;
	    }	    
		
		if (_isHTMLElement(curCol, "select")){
			data[curCol.name]=curCol.value;
		}
		
		if (_isHTMLElement(curCol, "input")){
			switch (curCol.type){
			case "checkbox":
			case "check-box":
			case "radio":
				if (curCol.checked) 
					data[curCol.name]=1;
				else
					data[curCol.name]=0;;
			default:
				data[curCol.name]=curCol.value;
			}
		}

		if (_isHTMLElement(curCol, "textarea")){
			data[curCol.name]=curCol.value;
		}
	
		curColTD=curColTD.nextSibling;
	}	
}

function getColValue(objRow,property){
	if (objRow==null || objRow==undefined) return;
	
	var curColTD=objRow.firstChild;
	while (curColTD!=null && curColTD!=undefined){
		curCol=curColTD.firstChild;
		
		if (curCol==null || curCol==undefined ||
			curCol.name==undefined || curCol.name==null || curCol.name==""){
			if (curColTD.id!=null && curColTD.id!=undefined){
				if (curColTD.id==property)
					return curColTD.innerHTML;
			}
			curColTD=curColTD.nextSibling;			
			continue;			
		}
		
		if (curCol.name==undefined || curCol.name==null ||
			curCol.name==""){
			curColTD=curColTD.nextSibling;			
			continue;
		}	
		if (curCol.name!=property){
			curColTD=curColTD.nextSibling;			
			continue;
		}
		
		if (_isHTMLElement(curCol, "select")){
			return curCol.value;
		}
		
		if (_isHTMLElement(curCol, "input")){
			switch (curCol.type){
			case "checkbox":
			case "check-box":
			case "radio":
				if (curCol.checked) 
				   return 1;
				else   
				   return 0;
			default:
				return curCol.value;
			}
		}

		if (_isHTMLElement(curCol, "textarea")){
			return curCol.value;
		}
		return null;
	}
}

function setColValue(trObj,property,data){
	if (trObj==null || trObj==undefined) return;
	var mycol=findColByName(trObj,property);
	if (mycol!=null && mycol!=undefined){ 
		mycol.value=data;
	}	
	else{
		mycol=findTdById(trObj,property);
		if (mycol!=null && mycol!=undefined){ 
			mycol.innerHTML=data;
		}	
	}		
}

function setRowDisabled(objRow,value){
	var curColTD=objRow.firstChild;
	var curCol;
	
	//��һ��(checkbox)������
	if (curColTD!=null && curColTD!=undefined){
		curColTD=curColTD.nextSibling;			
	}
	
	while (curColTD!=null && curColTD!=undefined){		
		curCol=curColTD.firstChild;
		if (curCol!=null && curCol!=undefined){
			curCol.disabled=value;
//			if (curCol.tagName=="INPUT")
//				curCol.readonly=value;
//			else
//				curCol.disabled=value;
		}	
			
		curColTD=curColTD.nextSibling;			
	}
}

//����������DWRUtils�ĺ��� 
function $(){
	var elements = new Array();
	for (var i = 0; i < arguments.length; i++){
		var element = arguments[i];
		if (typeof element == 'string'){
			if (document.getElementById){
				element = document.getElementById(element);
			}
			else if (document.all){
				element = document.all[element];
			}
		}

		if (arguments.length == 1){
			return element;
		}
		elements.push(element);
	}
	return elements;
}

function _isHTMLElement(ele, nodeName){
	if (ele == null || typeof ele != "object" || ele.nodeName == null){
		return false;
	}

	if (nodeName != null){
		var test = ele.nodeName.toLowerCase();
		if (typeof nodeName == "string"){
			return test == nodeName.toLowerCase();
		}

		if (_isArray(nodeName)){
			var match = false;
			for (var i = 0; i < nodeName.length && !match; i++){
				if (test == nodeName[i].toLowerCase()){
					match =  true;
				}
			}

			return match;
		}

		alert("_isHTMLElement was passed test node name that is neither a string or array of strings");
		return false;
	}
	return true;
}

function _importNode(doc, importedNode, deep){
	var newNode;
	if (importedNode.nodeType == 1  ){
		newNode = doc.createElement(importedNode.nodeName);
		for (var i = 0; i < importedNode.attributes.length; i++){
			var attr = importedNode.attributes[i];
			if (attr.nodeValue != null && attr.nodeValue != ''){
				newNode.setAttribute(attr.name, attr.nodeValue);
			}
		}

		if (typeof importedNode.style != "undefined"){
			newNode.style.cssText = importedNode.style.cssText;
		}
	}
	else if (importedNode.nodeType == 3  ){
		newNode = doc.createTextNode(importedNode.nodeValue);
	}

	if (deep && importedNode.hasChildNodes()){
		for (i = 0; i < importedNode.childNodes.length; i++){
			newNode.appendChild(_importNode(doc, importedNode.childNodes[i], true));
		}
	}
	return newNode;
}

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
 
function _strToDate(str) {
	var retDate;
 	var pattern=/^\d{4}-\d{2}-\d{2}$/;
 	if (!pattern.test(str)){
 		return null;
 	}
	var year = 0;
	var mon = 0;
	var day = 0;
	var a = str.split(/\W+/);
	year=a[0];
	mon=a[1];
	day=a[2];
    mon=mon-1;
	retDate=new Date(year,mon,day);	
	return retDate;
}

function getValues(map){
    var avalue;
	for (var property in map){
		var ele = $(property);
		if (ele != null){
			avalue=getValue(property);
/*			if (_typeof(map[property])=="Date"){
				map[property]=_strToDate(avalue);	
			}*/
			map[property]=avalue;
		}
	}
}
 
function getValue(ele){
	var orig = ele;
	ele = $(ele);
	if (ele == null){
		alert("getValue() can't find an element with id: " + orig + ".");
		return "";
	}

	if (_isHTMLElement(ele, "select")){
		var sel = ele.selectedIndex;
		if (sel != -1){
			var reply = ele.options[sel].value;
			if (reply == null || reply == ""){
				reply = ele.options[sel].text;
			}
			return reply;
		}
		else{
			return "";
		}
	}

	if (_isHTMLElement(ele, "input")){
	switch (ele.type){
		case "checkbox":
		case "check-box":
		case "radio":
			return ele.checked;

		default:
			return ele.value;
		}
	}

	if (_isHTMLElement(ele, "textarea")){
		return ele.value;
	}

	return ele.innerHTML;
}

function setValues(map){
	for (var property in map){
		if (typeof(property)=="function") continue;
		var value = map[property];
		if (typeof(value)=="function") continue;
		var ele = $(property);
		if (ele != null){
			if (_typeof(value)=="Date"){
		   		var avalue=_dateToStr(value);
				setValue(property, avalue);		   		
		   	}
		   	else	
				setValue(property, value);
		}
	}
}

function setValue(ele, val){
	if (val == null){
		val = "";
	}

	var orig = ele;
	ele = $(ele);
	if (ele == null){
		alert("setValue() can't find an element with id: " + orig + ".");
		return;
	}

	if (_isHTMLElement(ele, "select")){
		var found  = false;
		var i;
		for (i = 0; i < ele.options.length; i++){
			if (ele.options[i].value == val){
				ele.options[i].selected = true;
				found = true;
			}
			else{
				ele.options[i].selected = false;
			}
		}

		if (found){
			return;
		}

		for (i = 0; i < ele.options.length; i++){
			if (ele.options[i].text == val){
				ele.options[i].selected = true;
				break;
			}
		}
		return;
	}

	if (_isHTMLElement(ele, "input")){
		switch (ele.type){
			case "checkbox":
			case "check-box":
			case "radio":
				ele.checked = (val == true);
				return;
			default:
				ele.value = val;
				return;
		}
	}

	if (_isHTMLElement(ele, "textarea")){
		ele.value = val;
		return;
	}

	if (val.nodeType){
		if (val.nodeType == 9  ){
			val = val.documentElement;
		}

		val = _importNode(ele.ownerDocument, val, true);
		ele.appendChild(val);
		return;
	}

	ele.innerHTML = val;
}

ROW_NORMALCOLOR=0xFFFFFF;
ROW_OVERCOLOR = 0x8ec4cf;
ROW_SELECTED = '#D0E9ED';
ROW_ODDCOLOR = 0xFFFFFF;
ROW_EVENCOLOR = 0xE3E3E3;

function grid_row_over ( row ){
	if (row!=null && row!=undefined){
		if (row.style.backgroundColor!=ROW_SELECTED)
	 		row.style.backgroundColor = ROW_OVERCOLOR;
	 }
}

function grid_row_out ( row ){
	if (row!=null && row!=undefined){
		if (row.style.backgroundColor!=ROW_SELECTED)
	 		row.style.backgroundColor = ROW_NORMALCOLOR;
	} 	
}

function grid_row_select( row ){
 	if (row.tagName!="TR") return;
//	if (row.style.backgroundColor==ROW_SELECTED) return;	
	
    var tableBody=row.parentElement;
    while (tableBody.tagName!="TBODY") tableBody=tableBody.parentElement;
	for (var i=0;i<tableBody.children.length;i++)
	{
  		tableBody.children[i].style.backgroundColor='';
  	}	
	row.style.backgroundColor = ROW_SELECTED;
}	

/*
Object.prototype.clone = function(){
    var objClone;
    if ( this.constructor == Object ) objClone = new this.constructor(); 
    else objClone = new this.constructor(this.valueOf()); 
    for ( var key in this )
    {
        if ( objClone[key] != this[key] )
        { 
            if ( typeof(this[key]) == 'object' )
            { 
                objClone[key] = this[key].Clone();
            }
            else
            {
                objClone[key] = this[key];
            }
        }
    }
    objClone.toString = this.toString;
    objClone.valueOf = this.valueOf;
    return objClone; 
}    
*/
function Arabia2Chinese(Num){
	if(isNaN(Num)) return ""
	Num=String(Num);
    Num = Num.replace(",","");
    //�滻tomoney()�еġ�,��
    Num = Num.replace(" ","");
    //�滻tomoney()�еĿո�
    Num = Num.replace("��","");
   //�滻�����ܳ��ֵģ��ַ�
   
// part = Num.toString(10).split(".");
	part =String(Num).split(".");
	newchar = "";
   //С����ǰ����ת��
	for (var i=part[0].length-1;i>=0;i--){
		if(part[0].length > 10) return "";
    	tmpnewchar = "";
    	perchar = part[0].charAt(i);
    	switch(perchar){
    		case "0": tmpnewchar="��" + tmpnewchar ;break;
    		case "1": tmpnewchar="Ҽ" + tmpnewchar ;break;
    		case "2": tmpnewchar="��" + tmpnewchar ;break;
    		case "3": tmpnewchar="��" + tmpnewchar ;break;
    		case "4": tmpnewchar="��" + tmpnewchar ;break;
    		case "5": tmpnewchar="��" + tmpnewchar ;break;
    		case "6": tmpnewchar="½" + tmpnewchar ;break;
    		case "7": tmpnewchar="��" + tmpnewchar ;break;
    		case "8": tmpnewchar="��" + tmpnewchar ;break;
    		case "9": tmpnewchar="��" + tmpnewchar ;break;
    	}
    	switch(part[0].length-i-1){
    		case 0: tmpnewchar = tmpnewchar +"Ԫ" ;break;
    		case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"ʰ" ;break;
    		case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"��" ;break;
    		case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"Ǫ" ;break;
    		case 4: tmpnewchar= tmpnewchar +"��" ;break;
    		case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"ʰ" ;break;
    		case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"��" ;break;
    		case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"Ǫ" ;break;
    		case 8: tmpnewchar= tmpnewchar +"��" ;break;
    		case 9: tmpnewchar= tmpnewchar +"ʰ" ;break;
    	}
    	newchar = tmpnewchar + newchar;
	}
   
   //С����֮�����ת��
	if (Num.indexOf(".")!=-1){
		if(part[1].length > 2) {
    		//С����֮��ֻ�ܱ�����λ,ϵͳ���Զ��ض�
    		part[1] = part[1].substr(0,2)
      	}
		for (var i=0;i<part[1].length;i++){
			tmpnewchar = ""
    		perchar = part[1].charAt(i)
    		switch(perchar){
    			case "0": tmpnewchar="��" + tmpnewchar ;break;
    			case "1": tmpnewchar="Ҽ" + tmpnewchar ;break;
    			case "2": tmpnewchar="��" + tmpnewchar ;break;
    			case "3": tmpnewchar="��" + tmpnewchar ;break;
    			case "4": tmpnewchar="��" + tmpnewchar ;break;
    			case "5": tmpnewchar="��" + tmpnewchar ;break;
    			case "6": tmpnewchar="½" + tmpnewchar ;break;
    			case "7": tmpnewchar="��" + tmpnewchar ;break;
    			case "8": tmpnewchar="��" + tmpnewchar ;break;
    			case "9": tmpnewchar="��" + tmpnewchar ;break;
    		}
    		if (i==0)tmpnewchar =tmpnewchar + "��";
     		if (i==1)tmpnewchar = tmpnewchar + "��";
   			newchar = newchar + tmpnewchar;
   	  	}
	}

   //�滻�������ú���
	while (newchar.search("����") != -1)
	{
		newchar = newchar.replace("����", "��");
	}

	newchar = newchar.replace("����", "��");
	newchar = newchar.replace("����", "��");
	newchar = newchar.replace("����", "��");
	newchar = newchar.replace("��Ԫ", "Ԫ");
	newchar = newchar.replace("������", "");
	newchar = newchar.replace("���", "��");
	//newchar = newchar.replace("���", "");
	newchar = newchar.replace("���", "");

	if (newchar.substr(0,1)=="Ԫ") 
	{
		newchar="��"+newchar;
	}

	if (newchar.charAt(newchar.length-1) == "Ԫ" || newchar.charAt(newchar.length-1) == "��")
		newchar = newchar+"��"
	return newchar;
}
