//var windowapi = frameElement.api, W = windowapi.opener;// 内容页中调用窗口实例对象接口

function createwindow(title, url, width, height) {
	width = width ? width : 700;
	height = height ? height : 400;
	if (width == "100%" || height == "100%") {
		width = document.body.offsetWidth;
		height = document.body.offsetHeight - 100;
	}
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return false;
			},
			cancelVal : '关闭',
			cancel : true/* 为true等价于function(){} */
		});
	}
}

/**
 * 全屏编辑
 */
function editfs(title, url) {
	var name = gridname;
	if (rowid == '') {
		tip('请选择编辑项目');
		return;
	}
	url += '&id=' + rowid;
	openwindow(title, url, name, 800, 500);
}

// 删除调用函数
function deleteObj(url, name) {
	gridname = name;
	createdialog('删除确认 ', '确定删除该记录吗 ?', url, name);
}
// 删除调用函数
function confuploadify(url, id) {
	$.dialog.confirm('确定删除吗', function() {
		deluploadify(url, id);
	}, function() {
	});
}

// 普通询问操作调用函数
function confirm(url, content, name) {
	createdialog('提示信息 ', content, url, name);
}
/**
 * 提示信息
 */
function tip_old(msg) {
	$.dialog.setting.zIndex = 1980;
	$.dialog.tips(msg, 1);
}
/**
 * 提示信息
 */
function tip(msg) {
	if (typeof (windowapi) == 'undefined') {
		$.dialog.setting.zIndex = 1980;
		$.dialog.tips(msg,1.5,'tips.gif');
	} else {
		W.$.dialog.tips(msg,1.5,'tips.gif');
	}
}

/**
 * 提示信息像alert一样
 */
function alertTip(msg, title) {
	if (typeof (windowapi) == 'undefined') {
		$.dialog.setting.zIndex = 1980;
		title = title ? title : "提示信息";
		$.dialog({
			title : title,
			icon : 'tips.gif',
			content : msg
		});
	} else {
		title = title ? title : "提示信息";
		W.$.dialog({
			title : title,
			icon : 'tips.gif',
			content : msg
		});
	}
}

/**
 * 创建上传页面窗口
 */
function openuploadwin(title, url, name, width, height) {
	gridname = name;
	$.dialog({
		content : 'url:' + url,
		cache : false,
		button : [ {
			name : '开始上传',
			callback : function() {
				iframe = this.iframe.contentWindow;
				iframe.upload();
				return false;
			},
			focus : true
		}, {
			name : '取消上传',
			callback : function() {
				iframe = this.iframe.contentWindow;
				iframe.cancel();
			}
		}]
	});
}

/**
 * 创建不带按钮的窗口
 */
function openwindow(title, url, name, width, height) {
	gridname = name;
	if (typeof (width) == 'undefined' && typeof (height) != 'undefined') {
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				content : 'url:' + url,
				title : title,
				cache : false,
				lock : true,
				width : 'auto',
				height : height
			});
		} else {
			$.dialog({
				content : 'url:' + url,
				title : title,
				cache : false,
				parent : windowapi,
				lock : true,
				width : 'auto',
				height : height
			});
		}
	}
	if (typeof (height) == 'undefined' && typeof (width) != 'undefined') {
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				content : 'url:' + url,
				title : title,
				lock : true,
				width : width,
				cache : false,
				height : 'auto'
			});
		} else {
			$.dialog({
				content : 'url:' + url,
				title : title,
				lock : true,
				parent : windowapi,
				width : width,
				cache : false,
				height : 'auto'
			});
		}
	}
	if (typeof (width) == 'undefined' && typeof (height) == 'undefined') {
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				content : 'url:' + url,
				title : title,
				lock : true,
				width : 'auto',
				cache : false,
				height : 'auto'
			});
		} else {
			$.dialog({
				content : 'url:' + url,
				title : title,
				lock : true,
				parent : windowapi,
				width : 'auto',
				cache : false,
				height : 'auto'
			});
		}
	}
	if (typeof (width) != 'undefined' && typeof (height) != 'undefined') {
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				width : width,
				height : height,
				content : 'url:' + url,
				title : title,
				cache : false,
				lock : true
			});
		} else {
			$.dialog({
				width : width,
				height : height,
				content : 'url:' + url,
				parent : windowapi,
				title : title,
				cache : false,
				lock : true
			});
		}
	}
}

/**
 * 创建询问窗口
 */
function createdialog(title, content, url, name) {
	$.dialog.confirm(content, function() {
		gridname = name;
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : url,// 请求的action路径
			error : function() {// 请求失败处理函数
			},
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					reloadTable();
				}
			}
		});
		rowid = '';
	}, function() {
	});
}
/**
 * 执行保存
 */
function saveObj() {
	$('#btn_sub', iframe.document).click();
}

/**
 * 执行AJAX提交FORM
 */
function ajaxSubForm(url) {
	$('#myform', iframe.document).form('submit', {
		url : url,
		onSubmit : function() {
			iframe.editor.sync();
		},
		success : function(r) {
			tip('操作成功');
			reloadTable();
		}
	});
}

/**
 * 退出确认框
 */
function exit(url, content) {
	$.dialog.confirm(content, function() {
		window.location = url;
	});
}
/**
 * 模板页面ajax提交
 */
function ajaxdoSub(url, formname) {
	$('#' + formname).form('submit', {
		url : url,
		onSubmit : function() {
			editor.sync();
		},
		success : function(r) {
			tip('操作成功');
		}
	});
}

/**
 * ajax提交FORM
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
			tip('操作成功');
		}
	});
}

function openauditwin(title, url, saveurl, okbutton, backbutton, closebutton) {
	$.dialog({
		content : 'url:' + url,
		title : title,
		lock : true,
		opacity : 0.3,
		button : [ {
			name : okbutton,
			callback : function() {
				iframe = this.iframe.contentWindow;
				win = $.dialog.open.origin;// 来源页面
				$('#btn_sub', iframe.document).click();
				return false;
			}
		}, {
			name : backbutton,
			callback : function() {
				iframe = this.iframe.contentWindow;
				win = frameElement.api.opener;// 来源页面
				$('#formobj', iframe.document).form('submit', {
					url : saveurl + "&code=exit",
					onSubmit : function() {
						$('#code').val('exit');
					},
					success : function(r) {
						$.dialog.tips('操作成功', 2);
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
// 添加标签
function addOneTab(subtitle, url, icon) {
	if (icon == '') {
		icon = 'icon folder';
	}
	window.top.$.messager.progress({
		text : '页面加载中....',
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
				href : url,
				closable : true,
				icon : icon
			}
		});
	} else {
		if (url.indexOf('isIframe') != -1) {
			window.top.$('#maintabs').tabs('add',{
				title : subtitle,
				content : '<iframe src="'
						+ url
						+ '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				closable : true,
				icon : icon
			});
		} else {
			window.top.$('#maintabs').tabs('add', {
				title : subtitle,
				href : url,
				closable : true,
				icon : icon
			});
		}
	}
}
// 关闭自身TAB刷新父TABgrid
function closetab(title) {
	// 暂时先不刷新
	// window.top.document.getElementById('tabiframe').contentWindow.reloadTable();
	// window.top.document.getElementById('maintabs').contentWindow.reloadTable();
	window.top.$('#maintabs').tabs('close', title);
	// tip("添加成功");
}

// popup
// object: this name:需要选择的列表的字段 code:动态报表的code
function inputClick(obj, name, code) {
	$.dialog.setting.zIndex = 2000;
	if (name == "" || code == "") {
		alert("popup参数配置不全");
		return;
	}
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : "url:cgReportController.do?popup&id=" + code,
			lock : true,
			title : "选择",
			width : 800,
			height : 400,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				var selected = iframe.getSelectRows();
				if (selected == '' || selected == null) {
					alert("请选择");
					return false;
				} else {
					var str = "";
					$.each(selected, function(i, n) {
						if (i == 0)
							str += n[name];
						else
							str += "," + n[name];
					});
					$(obj).val("");
					// $('#myText').searchbox('setValue', str);
					$(obj).val(str);
					return true;
				}

			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	} else {
		$.dialog({
			content : "url:cgReportController.do?popup&id=" + code,
			lock : true,
			title : "选择",
			width : 800,
			height : 400,
			parent : windowapi,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				var selected = iframe.getSelectRows();
				if (selected == '' || selected == null) {
					alert("请选择");
					return false;
				} else {
					var str = "";
					$.each(selected, function(i, n) {
						if (i == 0)
							str += n[name];
						else
							str += "," + n[name];
					});
					$(obj).val("");
					// $('#myText').searchbox('setValue', str);
					$(obj).val(str);
					return true;
				}

			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	}
}
/*
 * 自定义url的弹出 obj:要填充的控件,可以为多个，以逗号分隔 name:列表中对应的字段,可以为多个，以逗号分隔（与obj要对应）
 * url：弹出页面的Url
 */
function popClick(obj, name, url) {
	$.dialog.setting.zIndex = 2001;
	var names = name.split(",");
	var objs = obj.split(",");
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : "url:" + url,
			lock : true,
			title : "选择",
			width : 700,
			height : 400,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				var selected = iframe.getSelectRows();
				if (selected == '' || selected == null) {
					alert("请选择");
					return false;
				} else {
					for ( var i1 = 0; i1 < names.length; i1++) {
						var str = "";
						$.each(selected, function(i, n) {
							if (i == 0)
								str += n[names[i1]];
							else {
								str += ",";
								str += n[names[i1]];
							}
						});
						if ($("#" + objs[i1]).length >= 1) {
							$("#" + objs[i1]).val("");
							$("#" + objs[i1]).val(str);
						} else {
							$("input[name='" + objs[i1] + "']").val("");
							$("input[name='" + objs[i1] + "']").val(str);
						}
					}
					return true;
				}

			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	} else {
		$.dialog({
			content : "url:" + url,
			lock : true,
			title : "选择",
			width : 700,
			height : 400,
			parent : windowapi,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				var selected = iframe.getSelectRows();
				if (selected == '' || selected == null) {
					alert("请选择");
					return false;
				} else {
					for ( var i1 = 0; i1 < names.length; i1++) {
						var str = "";
						$.each(selected, function(i, n) {
							if (i == 0)
								str += n[names[i1]];
							else {
								str += ",";
								str += n[names[i1]];
							}
						});
						if ($("#" + objs[i1]).length >= 1) {
							$("#" + objs[i1]).val("");
							$("#" + objs[i1]).val(str);
						} else {
							$("[name='" + objs[i1] + "']").val("");
							$("[name='" + objs[i1] + "']").val(str);
						}
					}
					return true;
				}

			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	}
}
/**
 * Excel 导出 代入查询条件
 */
function JeecgExcelExport(url, datagridId) {
	var queryParams = $('#' + datagridId).datagrid('options').queryParams;
	$('#' + datagridId + 'tb').find('*').each(function() {
		queryParams[$(this).attr('name')] = $(this).val();
	});
	var params = '&';
	$.each(queryParams, function(key, val) {
		params += '&' + key + '=' + val;
	});
	var fields = '&field=';
	$.each($('#' + datagridId).datagrid('options').columns[0],
			function(i, val) {
				if (val.field != 'opt') {
					fields += val.field + ',';
				}
			});
	window.location.href = url + encodeURI(fields + params);
}
/**
 * 自动完成的解析函数
 * 
 * @param data
 * @returns {Array}
 */
function jeecgAutoParse(data) {
	var parsed = [];
	$.each(data.rows, function(index, row) {
		parsed.push({
			data : row,
			result : row,
			value : row.id
		});
	});
	return parsed;
}
