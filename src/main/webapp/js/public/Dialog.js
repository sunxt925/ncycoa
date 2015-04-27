/**
*
*  js消息框基类，用来显示增强的js消息框
*  目前的封装比较依赖于模板的形式，而且美工采用了table作显示，有待改进
*  详细使用例子见Demo/MessageBoxDemo.html
*  创建时间：2006-02-24
*　修改时间：
*　
**/
(function()
{
	Dialog = {
		showModelessDialog		: showWebDialog ,
		showModalDialog   : showBlockWebDialog
	};

	function showWebDialog(url, w, h, userArgs) {  //线程非中止方式，不会中断脚本
		
		if(!w) w = 503;
		if(!h) h = 385;
		
		var dwh = "dialogWidth:" + w + "px; dialogHeight:" + h + "px;";
		var ret =  showModelessDialog(url, {wnd:window,userArgs:userArgs}, 
			"status:no;center:yes;help:no;minimize:no;maximize:no;border:thin;" + dwh);
		return ret;
	};

	function showBlockWebDialog(url, w, h, userArgs){    //线程中止方式的对话框会中断脚本 
		
		if(!w) w = 503;
		if(!h) h = 385;
		var dwh = "dialogWidth:" + w + "px; dialogHeight:" + h + "px;";
		var ret = showModalDialog(url, {wnd:window,userArgs:userArgs}, 
			"status:no;center:yes;help:no;minimize:no;maximize:no;border:thin;" + dwh);
		return ret;
	};
})();
	