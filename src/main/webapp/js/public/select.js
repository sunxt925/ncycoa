// JavaScript Document
function allitems_click()
{
	if (document.all("allitems").checked)
	{
		SelectAll("form1");
	}
	else
	{
		UnSelectAll("form1");
	}
}

function SelectAll(form_name) 
{ 
	for (var i=0;i<document.all(form_name).elements.length;i++) 
	{ 
		var e=document.all(form_name).elements[i]; 
		if (e.type=='checkbox' && e.id=='items' && e.disabled!=true) 
		{ 
			e.checked=true; 
		} 
	} 
}
function ChangeSelect(form_name) //ทดัก
{ 
	for (var i=0;i<document.all(form_name).elements.length;i++) 
	{ 
		var e=document.all(form_name).elements[i]; 
		if (e.type=='checkbox' && e.id=='items' && e.disabled!=true) 
		{ 
			if (e.checked==false)
			{
				e.checked=true;
			}
			else
			{
				e.checked=false;
			}
		} 
	} 
} 
function UnSelectAll(form_name) 
{ 
	for (var i=0;i<document.all(form_name).elements.length;i++) 
	{ 
		var e=document.all(form_name).elements[i]; 
		if (e.type=='checkbox' && e.id=='items' && e.disabled!=true) 
		{ 
			e.checked=false; 
		} 
	} 
} 
function CheckSelect(form_name)
{
	var res=0;
	for (var i=0;i<document.all(form_name).elements.length;i++) 
	{ 
		var e=document.all(form_name).elements[i]; 
		if (e.type=='checkbox' && e.id=='items') 
		{ 
			if (e.checked==true)
			{
				res++;
			}
		} 
	} 
	if (res==0)
	{
		return false;	
	}
	else
	{
		return true;	
	}
}
function selecttriger(form_name,val,val_boolean)
{
	for (var i=0;i<document.all(form_name).elements.length;i++) 
	{ 
		var e=document.all(form_name).elements[i]; 
		if (e.type=='checkbox' && e.id=='items' && e.disabled!=true) 
		{
			if (val_boolean==true)
			{
				if (e.value.indexOf(val)==0 || val.indexOf(e.value)==0)
				{
					e.checked=val_boolean;
				}
			}
			else
			{
				if (e.value.indexOf(val)==0)
				{
					e.checked=val_boolean;
				}
				unchecksamelevel(form_name,val);
			}
		} 
	} 
}
function unchecksamelevel(form_name,val)
{
	var val_len=val.length;	
	if (val_len>3)
	{
		var val_s=val.substring(0,val_len-3);
		var val_slen=val_s.length;
		var ck=false;
		for (var i=0;i<document.all(form_name).elements.length;i++) 
		{ 
			var e=document.all(form_name).elements[i]; 
			if (e.type=='checkbox' && e.id=='items' && e.disabled!=true) 
			{
				if (e.value.substring(0,val_slen)==val_s && e.value!=val && e.value.length==val_len)
				{
					if (e.checked)
					{
						ck=true;
					}
				}
			}
		}
		if (ck==false)
		{
			setcheck(form_name,val_s,false);
		}
	}
}
function setcheck(form_name,val,z)
{
	for (var i=0;i<document.all(form_name).elements.length;i++) 
	{ 
		var e=document.all(form_name).elements[i]; 
		if (e.type=='checkbox' && e.id=='items' && e.disabled!=true && e.value==val) 
		{
			e.checked=z;
			if (e.checked==false)
			{
				unchecksamelevel(form_name,val);
			}
		}
	}
}

function setowncheck(form_name,val,z){
	alert("yunxing");
	var ch = new Array;
    ch = val.split(",");
	for(var i=0;i<ch.length;i++){
		setcheck(form_name,ch[i],z)
	}
	
	
}