

//CheckAll
//  <input type="checkbox" name="chkall" value="on" onclick="CheckAll(this.form)" ><font color="red">ȫѡ</font>
function  CheckAll(form) {
	for (var i=0;i<form.elements.length;i++) {
		var e = form.elements[i];
		if (e.name != 'chkall' && e.type=='checkbox')
			e.checked = form.chkall.checked;
			
	}
}
