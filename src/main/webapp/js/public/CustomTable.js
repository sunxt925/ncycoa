//全局变量
var inputFocus;//该变量记录当前焦点的input
var bKeyDown=false;//记录键盘被按下的状态，当有键盘按下时其值为true

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

 //将所有未被选中的行取消高亮度现实
        var tableOnlineEdit=obj.parentElement;
        while(tableOnlineEdit.tagName!="TABLE")tableOnlineEdit=tableOnlineEdit.parentElement;
 var objsCheckBox=tableOnlineEdit.all("checkLine");
 for(var iCheckBox=1;iCheckBox<objsCheckBox.length;iCheckBox++)
  if(objsCheckBox[iCheckBox].checked==false) setRowClass(tableOnlineEdit.rows[iCheckBox+1],"tableData");

 //当前点击行高亮度显示
 setRowClass(obj,"tableDataHit");
}

//得到obj的上级元素TagName
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
 inputFocus=obj;//记录当前焦点input至全局变量
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

//将指定数据行的objRow的输入域strName设置为strValue
function setInputValue(objRow,strName,strValue){
 var objs=objRow.all;
 for(var i=0;i<objs.length;i++){
  if(objs[i].name==strName)objs[i].value=strValue;
 }
}

//添加一个实例数据行
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

//将全部复选框设为指定值
function setOnlineEditCheckBox(obj,value){
 var tbodyOnlineEdit=obj.getElementsByTagName("TBODY")[0];
 for (var i=tbodyOnlineEdit.children.length-1; i>=0 ; i-- )
  tbodyOnlineEdit.children[i].firstChild.firstChild.checked=value;
}

//为动态表格增加键盘导航功能,要使用该功能请在表格定义中增加事件处理onKeyDown="navigateKeys()" onKeyUp="setKeyDown(false)"
//有一点点问题，当按下"->"跳转到下一输入域时，光标显示在第一个字符之后
//建议仍然使用Tab键跳转
function navigateKeys(){
 if(bKeyDown) return;
 bKeyDown=true;
 var elm=event.srcElement;
 if(elm.tagName!="INPUT") return;//默认只对INPUT进行导航，可自行设定

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
    nRow--;//跳转到上一行
    nCell=objTR.cells.length-1;//最后一列
   }
   break;
  case 38://^
   nRow--;
   break;
  case 39://->
   if(getCursorPosition(elm)<elm.value.length)return;
   nCell++;
   if(nCell==objTR.cells.length){    
    nRow++;//跳转到下一行首位置
    nCell=1;//第一列
   }
   break;
  case 40://\|/
   nRow++;
   if(nRow==objTBODY.rows.length){    
    addRow(objTable);//增加一个空行
    nCell=1;//跳转到第一列
   }
   break;
  case 13://Enter
   nCell++;
   if(nCell==objTR.cells.length){    
    nRow++;//跳转到下一行首位置
    nCell=1;//第一列
   }
   if(nRow==objTBODY.rows.length){    
    addRow(objTable);//增加一个空行
    nCell=1;//跳转到第一列
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
  //此处使用ojbs[0],实际使用时可能需要加以修改,或加入其他条件
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


//设置键盘状态，即bKeyDown的值
function setKeyDown(status){
 bKeyDown=status;
}


//得到光标的位置
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


//设置光标位置
function setCursorPosition(obj,position){
 range=obj.createTextRange(); 
 range.collapse(true); 
 range.moveStart('character',position); 
 range.select();
}

//根据列名找到表格中对应<TD>元素
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

//根据列名找到对应的元素
function findColByName(objRow,colName){
	if (objRow==null || objRow==undefined) return null;
	
	var curColTD=objRow.firstChild;
	while (curColTD!=null && curColTD!=undefined){
		curCol=curColTD.firstChild;

		//如果<TD>标签下面没有子元素(如<input><select>等
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

//根据属性名，取得对象该属性的值
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
		
		//如果<TD>标签下面没有子元素(如<input><select>等)，则直接检查其id属性
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
		
		//如果<TD>标签下面有子元素，则根据子元素的类型设置显示值
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
		
		//如果<TD>标签下面没有子元素(如<input><select>等)，则直接检查其id属性
		if (curCol==null || curCol==undefined ||
			curCol.name==undefined || curCol.name==null || curCol.name==""){
			if (curColTD.id!=null && curColTD.id!=undefined){
				if (findProperty(data,curColTD.id))
					data[curColTD.id]=curColTD.innerHTML;
			}
			curColTD=curColTD.nextSibling;			
			continue;			
		}
		
		//如果<TD>标签下面有子元素，则根据子元素的类型设置显示值
		if (curCol.name==undefined || curCol.name==null ||
			curCol.name==""){
			curColTD=curColTD.nextSibling;			
			continue;
		}	
		
		//如果在data对象中未找到curCol.name列
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
	
	//第一列(checkbox)不设置
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

//拷贝来自于DWRUtils的函数 
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
    //替换tomoney()中的“,”
    Num = Num.replace(" ","");
    //替换tomoney()中的空格
    Num = Num.replace("￥","");
   //替换掉可能出现的￥字符
   
// part = Num.toString(10).split(".");
	part =String(Num).split(".");
	newchar = "";
   //小数点前进行转化
	for (var i=part[0].length-1;i>=0;i--){
		if(part[0].length > 10) return "";
    	tmpnewchar = "";
    	perchar = part[0].charAt(i);
    	switch(perchar){
    		case "0": tmpnewchar="零" + tmpnewchar ;break;
    		case "1": tmpnewchar="壹" + tmpnewchar ;break;
    		case "2": tmpnewchar="贰" + tmpnewchar ;break;
    		case "3": tmpnewchar="叁" + tmpnewchar ;break;
    		case "4": tmpnewchar="肆" + tmpnewchar ;break;
    		case "5": tmpnewchar="伍" + tmpnewchar ;break;
    		case "6": tmpnewchar="陆" + tmpnewchar ;break;
    		case "7": tmpnewchar="柒" + tmpnewchar ;break;
    		case "8": tmpnewchar="捌" + tmpnewchar ;break;
    		case "9": tmpnewchar="玖" + tmpnewchar ;break;
    	}
    	switch(part[0].length-i-1){
    		case 0: tmpnewchar = tmpnewchar +"元" ;break;
    		case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
    		case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
    		case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
    		case 4: tmpnewchar= tmpnewchar +"万" ;break;
    		case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
    		case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
    		case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
    		case 8: tmpnewchar= tmpnewchar +"亿" ;break;
    		case 9: tmpnewchar= tmpnewchar +"拾" ;break;
    	}
    	newchar = tmpnewchar + newchar;
	}
   
   //小数点之后进行转化
	if (Num.indexOf(".")!=-1){
		if(part[1].length > 2) {
    		//小数点之后只能保留两位,系统将自动截段
    		part[1] = part[1].substr(0,2)
      	}
		for (var i=0;i<part[1].length;i++){
			tmpnewchar = ""
    		perchar = part[1].charAt(i)
    		switch(perchar){
    			case "0": tmpnewchar="零" + tmpnewchar ;break;
    			case "1": tmpnewchar="壹" + tmpnewchar ;break;
    			case "2": tmpnewchar="贰" + tmpnewchar ;break;
    			case "3": tmpnewchar="叁" + tmpnewchar ;break;
    			case "4": tmpnewchar="肆" + tmpnewchar ;break;
    			case "5": tmpnewchar="伍" + tmpnewchar ;break;
    			case "6": tmpnewchar="陆" + tmpnewchar ;break;
    			case "7": tmpnewchar="柒" + tmpnewchar ;break;
    			case "8": tmpnewchar="捌" + tmpnewchar ;break;
    			case "9": tmpnewchar="玖" + tmpnewchar ;break;
    		}
    		if (i==0)tmpnewchar =tmpnewchar + "角";
     		if (i==1)tmpnewchar = tmpnewchar + "分";
   			newchar = newchar + tmpnewchar;
   	  	}
	}

   //替换所有无用汉字
	while (newchar.search("零零") != -1)
	{
		newchar = newchar.replace("零零", "零");
	}

	newchar = newchar.replace("零亿", "亿");
	newchar = newchar.replace("亿万", "亿");
	newchar = newchar.replace("零万", "万");
	newchar = newchar.replace("零元", "元");
	newchar = newchar.replace("零角零分", "");
	newchar = newchar.replace("零角", "零");
	//newchar = newchar.replace("零角", "");
	newchar = newchar.replace("零分", "");

	if (newchar.substr(0,1)=="元") 
	{
		newchar="零"+newchar;
	}

	if (newchar.charAt(newchar.length-1) == "元" || newchar.charAt(newchar.length-1) == "角")
		newchar = newchar+"整"
	return newchar;
}
