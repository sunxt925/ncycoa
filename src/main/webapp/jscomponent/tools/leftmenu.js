//alert("This is in leftmenu.js");

$(function() {
	// easy ui�����ػ����ĵ�������ִ��,���Գ�ʼ���˵�Ҫ�ӳ�һ��
	//setTimeout(InitLeftMenu, 1000);
	
	tabCloseEven();
	// �ͷ��ڴ�
	$.fn.panel.defaults = $.extend({}, $.fn.panel.defaults, {
		onBeforeDestroy : function() {
			var frame = $('iframe', this);
			if (frame.length > 0) {
				frame[0].contentWindow.document.write('');
				frame[0].contentWindow.close();
				frame.remove();
			}
			if ($.browser.msie) {
				CollectGarbage();
			}
		}
	});
	
	$('.tabs-inner span').each(function(i, n) {
		var t = $(n).text();
		$('#maintabs').tabs('close', t);
	});
	
	$('#maintabs').tabs('add',{
		title : '�ҵĹ���',
		content : createFrame('pending-task.htm?dgview'),
		closable : false,
	});
	
	$('#maintabs').tabs('add',{
		title : '通知公告',
		content : createFrame('notice.htm?dgview_see'),
		closable : false,
	});

	$('#maintabs').tabs({
		onSelect : function(title) {
			rowid = "";
		}
	});
	tabClose();
});

var rowid = "";
// ��ʼ�����
function InitLeftMenu() {
	$("#nav").show();
	$('.easyui-accordion').accordion('resize');
	$('.easyui-accordion li div').click(function() {
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
	
	$('#nav .easyui-tree').tree({
		onClick : function(node) {
			openNode(node);
		}
	});
}

function openNode(node) {
	if (node.state == "open") {
		$('.easyui-tree').tree('collapse', node.target);
		return;
	}
	var children = $('#nav .easyui-tree').tree('getChildren', node.target);
	var pnode = null;
	try {
		pnode = $('#nav .easyui-tree').tree('getParent', node.target);
	} catch (e) {
	}
	if (pnode && children && children.length > 0) {
		$(pnode).each(function() {
			$('#nav .easyui-tree').tree('collapse', this);
		});
		$('#nav .easyui-tree').tree('expand', node.target);
	} else if (children && children.length > 0) {
		$('#nav .easyui-tree').tree('collapseAll');
		$('#nav .easyui-tree').tree('expand', node.target);
	}
	if (children = null || children.length == 0) {
		var fun = $(node.target).find('a').attr("onclick");
		var params = fun.substring(7, fun.length - 1).replaceAll("'", "")
				.split(",");
		addTab(params[0], params[1], params[2]);
	}
}

String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};
// ��ȡ��ർ����ͼ��
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		$.each(n.menus, function(j, o) {
			if (o.menuid == menuid) {
				icon += o.icon;
			}
		});
	});
	return icon;
}

function addTab(subtitle, url) {
	var progress = $("div.messager-progress");
	if (progress.length) {
		return;
	}
	rowid = "";
//	$.messager.progress({
//		text : 'ҳ�������....',
//		interval : 200
//	});
	if (!$('#maintabs').tabs('exists', subtitle)) {
		// �ж��Ƿ����iframe��ʽ��tab��Ĭ��Ϊhref��ʽ
		if (url.indexOf('isIframe') != -1) {
			$('#maintabs').tabs('add',{
					title : subtitle,
					content : createFrame(url),
					closable : true,
			});
		} else {
			$('#maintabs').tabs('add', {
				title : subtitle,
				href : url,
				closable : true,
			});
		}

	} else {
		$('#maintabs').tabs('select', subtitle);
		$.messager.progress('close');
	}
	// $('#maintabs').tabs('select',subtitle);
	tabClose();
}
function addmask() {
	$.messager.progress({
		text : 'ҳ�������....',
		interval : 100
	});
}
function createFrame(url) {
	var s = '<iframe name="tabiframe" id="tabiframe" scrolling="yes" frameborder="0" src="'+ url + 
			'" style="width:100%;height:99.5%;border:0px none;"></iframe>';
	return s;
}

function tabClose() {
	/* ˫���ر�TABѡ� */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* Ϊѡ����Ҽ� */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		var subtitle = $(this).children(".tabs-closable").text();
		$('#mm').data("currtab", subtitle);
		// $('#maintabs').tabs('select',subtitle);
		return false;
	});
}
// ���Ҽ��˵��¼�
function tabCloseEven() {
	// ˢ��
	$('#mm-tabupdate').click(function() {
		var currTab = $('#maintabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#maintabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	});
	// �رյ�ǰ
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#maintabs').tabs('close', currtab_title);
	});
	// ȫ���ر�
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if (t != '�ҵĹ���') {
				$('#maintabs').tabs('close', t);
			}
		});
	});
	// �رճ���ǰ֮���TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// �رյ�ǰ�Ҳ��TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('ϵͳ��ʾ','���û����~~','error');
			alert('���û����~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#maintabs').tabs('close', t);
		});
		return false;
	});
	// �رյ�ǰ����TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('��ͷ�ˣ�ǰ��û����~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if (t != '�ҵĹ���') {
				$('#maintabs').tabs('close', t);
			}
		});
		return false;
	});

	// �˳�
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}
