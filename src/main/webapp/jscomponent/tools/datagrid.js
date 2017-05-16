//ʱ���ʽ��
Date.prototype.format = function (format,value) {
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}
	if(value==''||value==null){
		return '';
	}
	var strdata=value.replace(/-/g,"/");
	var index=strdata.indexOf(".");
	if(index>0)
	{
		strdata=strdata.substr(0,index);
	}
	var date= new Date(Date.parse(strdata));
	var o = {
		"M+" : date.getMonth() + 1, // month
		"d+" : date.getDate(), // day
		"h+" : date.getHours(), // hour
		"m+" : date.getMinutes(), // minute
		"s+" : date.getSeconds(), // second
		"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
		"S" : date.getMilliseconds()
		// millisecond
	};
	
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, strdata.substr(4-RegExp.$1.length,RegExp.$1.length));
	}
	
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * ��ɾ�Ĺ�����
 */
window.onerror = function() {
	return true;
};
var iframe;     // iframe��������
var win;        //���ڶ���
var gridname="";//����datagrid��������
var windowapi = frameElement.api;
var W = windowapi.opener;//����ҳ�е��ô���ʵ������ӿ�
function upload(curform) {
	upload();
}

/**
 * ��ӵ��ú���
 */
function add(title, actionUrl, gname, width, height) {
	gridname=gname;
	createwindow(title, actionUrl, width, height);
}

/**
 * ɾ�����ú���
 */
function del(title, actionUrl, gname) {
	var rows = null;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('��ѡ��');
		return;
	}
	
	var ids = '';
	for(var i=0; i<rows.length; i++) {
		ids += rows[i].id + ',';
	}
	if(actionUrl.indexOf("?") == -1) {
		actionUrl += '?id='+ ids;
	} else {
		actionUrl += '&id='+ ids;
	}
	
	createdialog('ɾ��ȷ�� ', 'ȷ��ɾ���ü�¼�� ?', actionUrl, gname);
}

/**
 * ���µ��ú���
 */
function update(title, actionUrl, gname, width, height) {
	gridname=gname;
	var rows;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('��ѡ��༭��Ŀ');
		return;
	}
	if (rows.length > 1) {
		tip('��ѡ��һ����¼�ٱ༭');
		return;
	}
	
	if(actionUrl.indexOf("?") >= 0) {
		actionUrl += '&id='+ rows[0].id;
	} else {
		actionUrl += '?id='+ rows[0].id;
	}
	createwindow(title, actionUrl, width, height);
}

//��ͨѯ�ʲ������ú���
function confirm(actionUrl, content, name) {
	createdialog('��ʾ��Ϣ ', content, actionUrl, name);
}

/**
 * ����ѯ�ʴ���
 */
function createdialog(title, content, url, name) {
	$.dialog.confirm(
		content, 
		function(){
			doSubmit(url, name);
			rowid = '';
		}, 
		function(){
		
		}
	);	
}

function reloadTable(gname){
	if(gname) {
		try{$('#'+gname).datagrid('reload');}catch(ex){}
		try{$('#'+gname).treegrid('reload');}catch(ex){}
	} else {
		try{$('#'+gridname).datagrid('reload');}catch(ex){}
		try{$('#'+gridname).treegrid('reload');}catch(ex){}
	}
}



/**
 * ���б�����¼��򿪴���
 * @param title �༭�����
 * @param addurl//Ŀ��ҳ���ַ
 */
function addTreeNode(title,addurl,gname) {
	if (rowid != '') {
		addurl += '&id='+rowid;
	}
	gridname=gname;
	createwindow(title, addurl);
}

/**
 * ���ҳ������ϸ�鿴ҳ�棬��Ч�����б�Ԫ�أ�ֻ�ܽ��в鿴
 */
$(function(){
	if(location.href.indexOf("load=detail")!=-1){
		$(":input").attr("disabled","true");
		//$(":input").attr("style","border:0;border-bottom:1 solid black;background:white;");
	}
});

/**
 * �鿴��ϸ�¼��򿪴���
 * @param title �鿴�����
 * @param addurl//Ŀ��ҳ���ַ
 * @param id//�����ֶ�
 */
function detail(title,url, id,width,height) {
	var rowsData = $('#'+id).datagrid('getSelections');
//	if (rowData.id == '') {
//		tip('��ѡ��鿴��Ŀ');
//		return;
//	}
	
	if (!rowsData || rowsData.length == 0) {
		tip('��ѡ��鿴��Ŀ');
		return;
	}
	if (rowsData.length > 1) {
		tip('��ѡ��һ����¼�ٲ鿴');
		return;
	}
    url += '&load=detail&id='+rowsData[0].id;
	createdetailwindow(title,url,width,height);
}

/**
 * ���¼����
 * @param title
 * @param url
 * @param gname
 * @return
 */
function deleteALLSelect(title,url,gname) {
	gridname=gname;
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.confirm('��ȷ������ɾ����������?', function(r) {
		   if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : url,
					type : 'post',
					data : {
						ids : ids.join(',')
					},
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							reloadTable();
							$("#"+gname).datagrid('unselectAll');
							ids='';
						}
					}
				});
			}
		});
	} else {
		tip("��ѡ����Ҫɾ��������");
	}
}

/**
 * �鿴ʱ�ĵ�������
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function createdetailwindow(title, addurl,width,height) {
	width = width?width:700;
	height = height?height:400;
	if(width=="100%" || height=="100%"){
		width = document.body.offsetWidth;
		height =document.body.offsetHeight-100;
	}
	if(typeof(windowapi) == 'undefined'){
		$.dialog({
			content: 'url:'+addurl,
			lock : true,
			width:width,
			height: height,
			title:title,
			opacity : 0.3,
			cache:false, 
		    cancelVal: '�ر�',
		    cancel: true /*Ϊtrue�ȼ���function(){}*/
		});
	}else{
		W.$.dialog({
			content: 'url:'+addurl,
			lock : true,
			width:width,
			height: height,
			parent:windowapi,
			title:title,
			opacity : 0.3,
			cache:false, 
		    cancelVal: '�ر�',
		    cancel: true /*Ϊtrue�ȼ���function(){}*/
		});
	}
	
}
/**
 * ȫ���༭
 * @param title �༭�����
 * @param addurl//Ŀ��ҳ���ַ
 * @param id//�����ֶ�
 */
function editfs(title,url) {
	var name=gridname;
	 if (rowid == '') {
		tip('��ѡ��༭��Ŀ');
		return;
	}
	url += '&id='+rowid;
	openwindow(title,url,name,800,500);
}

// ɾ�����ú���
function confuploadify(url, id) {
	$.dialog.confirm('ȷ��ɾ����', function(){
		deluploadify(url, id);
	}, function(){
	});
}
/**
 * ִ��ɾ������
 * 
 * @param url
 * @param index
 */
function deluploadify(url, id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// �����action·��
		error : function() {// ����ʧ�ܴ�����
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				$("#" + id).remove();// �Ƴ�SPAN
				m.remove(id);// �Ƴ�MAP�������ַ���
			}

		}
	});
}

/**
 * ��ʾ��Ϣ
 */
function tip_old(msg) {
	$.dialog.setting.zIndex = 1980;
	$.dialog.tips(msg, 1);
}
/**
 * ��ʾ��Ϣ
 */
function tip(msg) {
	$.dialog.setting.zIndex = 1980;
	$.messager.show({
		title : '��ʾ��Ϣ',
		msg : msg,
		timeout : 1000 * 6
	});
}
/**
 * ��ʾ��Ϣ��alertһ��
 */
function alertTip(msg,title) {
	$.dialog.setting.zIndex = 1980;
	title = title?title:"��ʾ��Ϣ";
	$.dialog({
			title:title,
			icon:'tips.gif',
			content: msg
		});
}
/**
 * ������ӻ�༭����
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function createwindow(title, url,width,height) {
	width =  width ? width : 800;
	height = height ? height : 600;
	if(width=="100%" || height=="100%"){
		width = document.body.offsetWidth;
		height =document.body.offsetHeight-100;
	}
	
	if(typeof(windowapi) == 'undefined'){
		$.dialog({
			content: 'url:' + url,
			lock : true,
			width:width,
			height:height,
			title:title,
			opacity : 0.3,
			cache:false,
		    ok: function(){
		    	iframe = this.iframe.contentWindow;
				if(!saveObj()){
					return false;
				}
				return true;
		    },
		    cancelVal: '�ر�',
		    cancel: true /*Ϊtrue�ȼ���function(){}*/
		});
	}else{
		W.$.dialog({
			content: 'url:'+url,
			lock : true,
			width:width,
			height:height,
			parent:windowapi,
			title:title,
			opacity : 0.3,
			cache:false,
		    ok: function(){
		    	iframe = this.iframe.contentWindow;
				if(!saveObj()){
					return false;
				}
				return true;
		    },
		    cancelVal: '�ر�',
		    cancel: true /*Ϊtrue�ȼ���function(){}*/
		});
	}
	
}
/**
 * �����ϴ�ҳ�洰��
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function openuploadwin(title, url,name,width, height) {
	gridname=name;
	$.dialog({
	    content: 'url:'+url,
	    cache:false,
	    button: [
	        {
	            name: '��ʼ�ϴ�',
	            callback: function(){
	            	iframe = this.iframe.contentWindow;
					iframe.upload();
					return false;
	            },
	            focus: true
	        },
	        {
	            name: 'ȡ���ϴ�',
	            callback: function(){
	            	iframe = this.iframe.contentWindow;
					iframe.cancel();
	            }
	        }
	    ]
	});
}
/**
 * ������ѯҳ�洰��
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function opensearchdwin(title, url, width, height) {
	$.dialog({
		content: 'url:'+url,
		title : title,
		lock : true,
		height : height,
		cache:false,
		width : width,
		opacity : 0.3,
		button : [ {
			name : '��ѯ',
			callback : function() {
				iframe = this.iframe.contentWindow;
				iframe.searchs();
			},
			focus : true
		}, {
			name : 'ȡ��',
			callback : function() {

			}
		} ]
	});
}
/**
 * ����������ť�Ĵ���
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function openwindow(title, url,name, width, height) {
	gridname=name;
	if(typeof(windowapi) == 'undefined'){
		$.dialog({
			content: 'url:'+url,
			title : title,
			cache:false,
			lock : true,
			width: width ? width : 800,
		    height: height ? height : 600
		});
	}else{
		$.dialog({
			content: 'url:'+url,
			title : title,
			cache:false,
			parent:windowapi,
			lock : true,
			width: width ? width : 800,
			height: height ? height : 600
		});
	}
}


/**
 * ִ�б���
 */
function saveObj() {
	$('#btn_sub', iframe.document).click();
}

/**
 * ִ��AJAX�ύFORM
 * 
 * @param url
 * @param gridname
 */
function ajaxSubForm(url) {
	$('#myform', iframe.document).form('submit', {
		url : url,
		onSubmit : function() {
			iframe.editor.sync();
		},
		success : function(r) {
			tip('�����ɹ�');
			reloadTable();
		}
	});
}
/**
 * ִ�в�ѯ
 * 
 * @param url
 * @param gridname
 */
function search() {

	$('#btn_sub', iframe.document).click();
	iframe.search();
}

/**
 * ִ�в���
 * 
 * @param url
 * @param index
 */
function doSubmit(url, gname) {
	gridname=gname;
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// �����action·��
		error : function() {// ����ʧ�ܴ�����
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				var msg = d.msg;
				tip(msg);
				reloadTable(gname);
			}
		}
	});
}
/**
 * �˳�ȷ�Ͽ�
 * 
 * @param url
 * @param content
 * @param index
 */
function exit(url, content) {
	$.dialog.confirm(content, function(){
		window.location = url;
	}, function(){
	});
}
/**
 * ģ��ҳ��ajax�ύ
 * 
 * @param url
 * @param gridname
 */
function ajaxdoSub(url, formname) {
	$('#' + formname).form('submit', {
		url : url,
		onSubmit : function() {
			editor.sync();
		},
		success : function(r) {
			tip('�����ɹ�');
		}
	});
}
/**
 * ajax�ύFORM
 * 
 * @param url
 * @param gridname
 */
function ajaxdoForm(url, formname) {
	$('#' + formname).form('submit', {
		url : url,
		onSubmit : function() {
		},
		success : function(r) {
			tip('�����ɹ�');
		}
	});
}

function opensubwin(title, url, saveurl, okbutton, closebutton) {
	$.dialog({
		content: 'url:'+url,
		title : title,
		lock : true,
		opacity : 0.3,
		button : [ {
			name : okbutton,
			callback : function() {
				iframe = this.iframe.contentWindow;
				win = frameElement.api.opener;// ��Դҳ��
				$('#btn_sub', iframe.document).click();
				return false;
			}
		}, {
			name : closebutton,
			callback : function() {
			}
		} ]

	});
}

function openauditwin(title, url, saveurl, okbutton, backbutton, closebutton) {
	$.dialog({
		content: 'url:'+url,
		title : title,
		lock : true,
		opacity : 0.3,
		button : [ {
			name : okbutton,
			callback : function() {
				iframe = this.iframe.contentWindow;
				win = $.dialog.open.origin;// ��Դҳ��
				$('#btn_sub', iframe.document).click();
				return false;
			}
		}, {
			name : backbutton,
			callback : function() {
				iframe = this.iframe.contentWindow;
				win = frameElement.api.opener;// ��Դҳ��
				$('#formobj', iframe.document).form('submit', {
					url : saveurl + "&code=exit",
					onSubmit : function() {
						$('#code').val('exit');
					},
					success : function(r) {
						$.dialog.tips('�����ɹ�', 2);
						win.location.reload();
					}
				});

			}
		}, {
			name : closebutton,
			callback : function() {
			}
		} ]

	});
}
// ��ӱ�ǩ
function addOneTab(subtitle, url, icon) {
	if (icon == '') {
		icon = 'icon folder';
	}
	window.top.$.messager.progress({
		text : 'ҳ�������....',
		interval : 300
	});
	window.top.$('#maintabs').tabs({
		onClose : function(subtitle, index) {
			window.top.$.messager.progress('close');
		}
	});
	if (window.top.$('#maintabs').tabs('exists', subtitle)) {
		window.top.$('#maintabs').tabs('select', subtitle);
		window.top.$('#maintabs').tabs('update', {
			tab : window.top.$('#maintabs').tabs('getSelected'),
			options : {
				title : subtitle,
				href:url,
				//content : '<iframe name="tabiframe"  scrolling="no" frameborder="0"  src="' + url + '" style="width:100%;height:99%;"></iframe>',
				closable : true,
				icon : icon
			}
		});
	} else {
		if (url.indexOf('isIframe') != -1) {
			window.top.$('#maintabs').tabs('add', {
				title : subtitle,
				content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				closable : true,
				icon : icon
			});
		}else {
			window.top.$('#maintabs').tabs('add', {
				title : subtitle,
				href:url,
				closable : true,
				icon : icon
			});
		}
	}
}
// �ر�����TABˢ�¸�TABgrid
function closetab(title) {
	//��ʱ�Ȳ�ˢ��
	//window.top.document.getElementById('tabiframe').contentWindow.reloadTable();
	//window.top.document.getElementById('maintabs').contentWindow.reloadTable();
	window.top.$('#maintabs').tabs('close', title);
	//tip("��ӳɹ�");
}

//popup  
//object: this  name:��Ҫѡ����б���ֶ�  code:��̬�����code
function inputClick(obj,name,code) {
	 $.dialog.setting.zIndex = 2000;
	 if(name==""||code==""){
		 alert("popup�������ò�ȫ");
		 return;
	 }
	 if(typeof(windowapi) == 'undefined'){
		 $.dialog({
				content: "url:cgReportController.do?popup&id="+code,
				lock : true,
				title:"ѡ��",
				width:800,
				height: 400,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	var selected = iframe.getSelectRows();
			    	if (selected == '' || selected == null ){
				    	alert("��ѡ��");
			    		return false;
				    }else {
					    var str = "";
				    	$.each( selected, function(i, n){
					    	if (i==0)
					    	str+= n[name];
					    	else
				    		str+= ","+n[name];
				    	});
				    	$(obj).val("");
				    	//$('#myText').searchbox('setValue', str);
					    $(obj).val(str);
				    	return true;
				    }
					
			    },
			    cancelVal: '�ر�',
			    cancel: true /*Ϊtrue�ȼ���function(){}*/
			});
		}else{
			$.dialog({
				content: "url:cgReportController.do?popup&id="+code,
				lock : true,
				title:"ѡ��",
				width:800,
				height: 400,
				parent:windowapi,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	var selected = iframe.getSelectRows();
			    	if (selected == '' || selected == null ){
				    	alert("��ѡ��");
			    		return false;
				    }else {
					    var str = "";
				    	$.each( selected, function(i, n){
					    	if (i==0)
					    	str+= n[name];
					    	else
				    		str+= ","+n[name];
				    	});
				    	$(obj).val("");
				    	//$('#myText').searchbox('setValue', str);
					    $(obj).val(str);
				    	return true;
				    }
					
			    },
			    cancelVal: '�ر�',
			    cancel: true /*Ϊtrue�ȼ���function(){}*/
			});
		}
}
/*
	�Զ���url�ĵ���
	obj:Ҫ���Ŀؼ�,����Ϊ������Զ��ŷָ�
	name:�б��ж�Ӧ���ֶ�,����Ϊ������Զ��ŷָ�����objҪ��Ӧ��
	url������ҳ���Url
*/
function popClick(obj,name,url) {
	 $.dialog.setting.zIndex = 2001;
	var names = name.split(",");
	var objs = obj.split(",");
	 if(typeof(windowapi) == 'undefined'){
		 $.dialog({
				content: "url:"+url,
				lock : true,
				title:"ѡ��",
				width:700,
				height: 400,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	var selected = iframe.getSelectRows();
			    	if (selected == '' || selected == null ){
				    	alert("��ѡ��");
			    		return false;
				    }else {
				    	for(var i1=0;i1<names.length;i1++){
						    var str = "";
					    	$.each( selected, function(i, n){
						    	if (i==0)
						    	str+= n[names[i1]];
						    	else{
									str+= ",";
									str+=n[names[i1]];
								}
					    	});
							if($("#"+objs[i1]).length>=1){
								$("#"+objs[i1]).val("");
								$("#"+objs[i1]).val(str);
							}else{
								$("input[name='"+objs[i1]+"']").val("");
								$("input[name='"+objs[i1]+"']").val(str);
							}
						 }
				    	return true;
				    }
					 
			    },
			    cancelVal: '�ر�',
			    cancel: true /*Ϊtrue�ȼ���function(){}*/
			});
		}else{
			$.dialog({
				content: "url:"+url,
				lock : true,
				title:"ѡ��",
				width:700,
				height: 400,
				parent:windowapi,
				cache:false,
			     ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	var selected = iframe.getSelectRows();
			    	if (selected == '' || selected == null ){
				    	alert("��ѡ��");
			    		return false;
				    }else {
				    	for(var i1=0;i1<names.length;i1++){
						    var str = "";
					    	$.each( selected, function(i, n){
						    	if (i==0)
						    	str+= n[names[i1]];
						    	else{
									str+= ",";
									str+=n[names[i1]];
								}
					    	});
					    	if($("#"+objs[i1]).length>=1){
								$("#"+objs[i1]).val("");
								$("#"+objs[i1]).val(str);
							}else{
								$("[name='"+objs[i1]+"']").val("");
								$("[name='"+objs[i1]+"']").val(str);
							}
						 }
				    	return true;
				    }
					
			    },
			    cancelVal: '�ر�',
			    cancel: true /*Ϊtrue�ȼ���function(){}*/
			});
		}
}
/**
 * Jeecg Excel ����
 * �����ѯ����
 */
function JeecgExcelExport(url,datagridId){
	var queryParams = $('#'+datagridId).datagrid('options').queryParams;
	$('#'+datagridId+'tb').find('*').each(function() {
	    queryParams[$(this).attr('name')] = $(this).val();
	});
	var params = '&';
	$.each(queryParams, function(key, val){
		params+='&'+key+'='+val;
	}); 
	var fields = '&field=';
	$.each($('#'+ datagridId).datagrid('options').columns[0], function(i, val){
		if(val.field != 'opt'){
			fields+=val.field+',';
		}
	}); 
	window.location.href = url+ encodeURI(fields+params);
}
/**
 * �Զ���ɵĽ�������
 * @param data
 * @returns {Array}
 */
function jeecgAutoParse(data){
	var parsed = [];
    	$.each(data.rows,function(index,row){
    		parsed.push({data:row,result:row,value:row.id});
    	});
			return parsed;
}
