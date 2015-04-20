// JavaScript Documentvar
var frame_num=0;//当前窗口数量
var frame_active_num=0;//当前活动窗口数量
var active_id=-1;//当前活动窗口id
var title_each_width=100;//每个选项卡的宽度
var title_top_height=24;//需要减去的已存在的高度
var frame_active_id=new Array();//当前活动窗口的ID数组
var frame_url=new Array();//当前存在的窗口地址
var frame_name=new Array();//当前存在窗口的名称
var max_window_num=0;//可以显示窗口的最大数量
var current_first_view=0;//当前显示的第一个窗口在所有可显示窗口的位置，从0开始计算
//alert (frame_active_id.length);
////
function create_frame(f_num,url,frame_name)
{
	if (frame_num==500)
	{
		alert ("由于单次登录使用时间过长，选项卡控件达到系统限制，请将相关未保存的信息保存后重新登录系统！");
		return;
	}
	var title_total_width=document.all("title_top").clientWidth;
	max_window_num=parseInt(title_total_width/title_each_width);
	//检查当前口是否打开，如果已打开，则直接跳转到该窗口
	var check_true=false;
	//alert (frame_active_num)
	for (var i=0;i<frame_active_num;i++)
	{
		var t_f_id=frame_active_id[i];
		if (frame_url[t_f_id]==url)
		{
			change_frame(t_f_id);
			check_true=true;;
			break;
		}
	}
	//将当前活动窗口设为不显示
	if (!check_true)
	{
		if (active_id>-1)
		{
			var target_frame_tr_preame="frame_tr_"+active_id;
			document.all(target_frame_tr_preame).style.display="none";
		}
		//更新变量
		frame_active_id[frame_active_num]=frame_num;
		frame_url[frame_num]=url;
		frame_name[frame_num]=frame_name;
		//alert (frame_name);
		//更新FRAME的HTML
		var add_html=add_frame_html(f_num,url);
		var new_frame_td_name="frame_td_"+frame_num;
		var new_frame_tr_name="frame_tr_"+frame_num;
		document.all(new_frame_td_name).innerHTML=add_html;
		document.all(new_frame_tr_name).style.display="block";
		//更新导航条的HTML
		var title_name="title_"+frame_num;
		var tr=document.getElementById("title_bar");
		var col=document.createElement("td");
		col.id=title_name;
		col.innerHTML=add_title_html(f_num,url,frame_name);
		tr.appendChild(col);
		document.all(title_name).align="left";
		document.all(title_name).valign="buttom";
		document.all(title_name).background="images/title_bg1.gif";
		document.all(title_name).width=title_each_width;
		document.all(title_name).nowrap="nowrap";
		//将上一个当前窗口的背景修改
		if (active_id!=-1)
		{
			var target_title_name="title_"+active_id;
			//alert (document.all(target_title_name).innerHTML);
			document.all(target_title_name).background="images/title_bg2.gif";
		}		
		//更新相关数字
		active_id=frame_num;
		frame_num=frame_num+1;
		frame_active_num=frame_active_num+1;
	}
	caculate_titlebar();
}

function close_frame()
{
	if (frame_active_num>0)
	{
		var title_total_width=document.all("title_top").clientWidth;
		max_window_num=parseInt(title_total_width/title_each_width);
		//处理当前窗口的高度及地址
		var target_frame_name="opt_frame_"+active_id;
		var target_title_name="title_"+active_id;
		var target_tr_name="frame_tr_"+active_id;
		//alert (target_title_name);
		//document.all(target_frame_name).height=0;
		document.all(target_frame_name).src="null.html";
		//处理菜单条
		document.all(target_title_name).innerHTML="";
		document.all(target_title_name).style.display="none";
		document.all(target_tr_name).style.display="none";
		//更新相关变量
		var frame_active_id_temp=new Array();
		var v_t=0;
		for (var i=0;i<frame_active_num;i++)
		{
			//alert (frame_active_id[i]);
			if (frame_active_id[i]!=active_id)
			{
				frame_active_id_temp[v_t]=frame_active_id[i];
				v_t=v_t+1;
			}
		}
		frame_active_num=frame_active_num-1;
		frame_active_id=new Array();
		for (var x=0;x<v_t;x++)
		{
			frame_active_id[x]=frame_active_id_temp[x];
		}
		//alert(v_t);
		//alert (frame_active_id[v_t]);
		//将最后一个窗口置为可见
		var v_t_1=v_t-1;
		//alert (v_t_1);
		if (frame_active_num>0)
		{
			change_frame(frame_active_id[v_t_1]);
			active_id=frame_active_id[v_t_1];
		}
		//alert (active_id);
		caculate_titlebar();
	}
}
function change_frame(frame_id)
{
	//var title_total_width=document.all("title_top").clientWidth;
	//max_window_num=parseInt(title_total_width/title_each_width);
	//将上一个当前窗口的背景修改
	if (active_id!=-1)
	{
		var target_title_name="title_"+active_id;
		//alert (document.all(target_title_name).innerHTML);
		document.all(target_title_name).background="images/title_bg2.gif";
	}
	for (var i=0;i<frame_active_num;i++)
	{
		//全部隐藏
		var t_name="opt_frame_"+frame_active_id[i];
		var t_frame_tr_name="frame_tr_"+frame_active_id[i];
		document.all(t_frame_tr_name).style.display="none";
		//document.all(t_name).height=0;
	}
	//打开当前的
	t_name="opt_frame_"+frame_id;
	var t_frame_tr_name="frame_tr_"+frame_id;
	document.all(t_frame_tr_name).style.display="block";
	active_id=frame_id;
	//document.all(t_name).height=parseInt(document.body.clientHeight)-title_top_height;
	//修改当前窗口标题的背景
	var target_title_name_cur="title_"+active_id;
	document.all(target_title_name_cur).background="images/title_bg1.gif";
	//caculate_titlebar();
}
function add_frame_html(f_num,url)
{
	var frame_height=parseInt(document.body.clientHeight)-title_top_height;
	var frame_width=document.body.clientWidth;
	return "<iframe name='opt_frame_"+f_num+"' src='"+url+"' width='100%' height='"+frame_height+"' scrolling='yes' allowtransparency='true'  frameborder='0' marginHeight='0' marginWidth='0'></iframe>";
}
function add_title_html(f_num,url,f_name)
{
	var f_name_show=f_name;
	if (f_name.length>6)
	{
		f_name_show=f_name.substr(0,6)+"...";
	}
	return "<a href='#' onclick='change_frame("+f_num+")' title='"+f_name+"'>&nbsp;"+f_name_show+"</a>";
}
function openwin()
{
	var url=document.all("url").value;
	var frame_name=document.all("f_name").value;
	//alert (frame_name);
	create_frame(frame_num,url,frame_name);
}
function title_invisible(title_id)
{
	var target_title_name="title_"+title_id;
	document.all(target_title_name).style.display="none";
}
function title_visible(title_id)
{
	var target_title_name="title_"+title_id;
	document.all(target_title_name).style.display="block";
}
function window_resize()
{
	var title_total_width=document.all("title_top").clientWidth;
	max_window_num=parseInt(title_total_width/title_each_width);
	caculate_titlebar();
}
function caculate_titlebar()
{
	//计算当前窗口是否超限
	if (frame_active_num<=max_window_num)
	{
		//全部显示
		for (var i=0;i<frame_active_num;i++)
		{
			title_visible(frame_active_id[i]);
		}
		current_first_view=0;
	}
	if (frame_active_num>max_window_num)
	{
		//检查当前窗口在所有打开窗口的位置
		var act_=-1;
		for (var x=0;x<frame_active_num;x++)
		{
			if (frame_active_id[x]==active_id)
			{
				act_=x;
				break;
			}
		}
		//act_=act_-current_first_view;
		//alert (act_);
		//如果当前活动窗口在允许显示范围内，将超出的部分置为不显示
		if (act_<max_window_num)
		{
			for (var i=0;i<max_window_num;i++)
			{
				title_visible(frame_active_id[i]);
			}
			for (var i=max_window_num;i<frame_active_num;i++)
			{				
				title_invisible(frame_active_id[i]);
			}
			current_first_view=0;
		}
		//如果当前活动窗口不在活动范围内，先将前面多余的窗口隐藏，再计算后面多余的窗口，将其隐藏
		if (act_>=max_window_num)
		{
			var hidde_title_num=act_-max_window_num+1;
			//隐藏前面的窗口
			//alert (hidde_title_num);
			for (var i=0;i<hidde_title_num;i++)
			{
				title_invisible(frame_active_id[i]);
			}
			//显示当前的窗口
			for (var i=hidde_title_num;i<hidde_title_num+max_window_num;i++)
			{
				title_visible(frame_active_id[i]);	
			}
			//隐藏后面的窗口
			for (var i=hidde_title_num+max_window_num;i<frame_active_num;i++)
			{
				title_invisible(frame_active_id[i]);
			}
			//更新当前第一个可显示窗口在所有可显示窗口的位置
			current_first_view=hidde_title_num;
		}
	}
	//验算前后是否需要量“...”
	//alert (current_first_view);
	if (current_first_view>0)
	{
		document.all("left_").style.display="block";
	}
	else
	{
		document.all("left_").style.display="none";
	}
	if (frame_active_num>max_window_num && current_first_view<(frame_active_num-max_window_num))
	{
		document.all("right_").style.display="block";
	}
	else
	{
		document.all("right_").style.display="none";
	}
}
function titlebar_moveleft()
{
	//alert (current_first_view);
	if (current_first_view>0)
	{
		title_visible(frame_active_id[current_first_view-1]);
		current_first_view=current_first_view-1;
		//alert (current_first_view);
		if (current_first_view==0)
		{
			document.all("left_").style.display="none";
		}
		//如果超出了最大数，将之前的最后一个隐掉
		if (frame_active_num>=max_window_num)
		{
			title_invisible(frame_active_id[current_first_view+max_window_num]);
		}
		//给后面打上“...”
		document.all("right_").style.display="block";
	}
}
function titlebar_moveright()
{
	if (frame_active_num>max_window_num && current_first_view<(frame_active_num-max_window_num))
	{
		//把第一个隐掉
		title_invisible(frame_active_id[current_first_view]);
		//显示下一个
		title_visible(frame_active_id[current_first_view+max_window_num]);
		//如果已经到了最后一个，把“...”隐掉
		if (current_first_view+max_window_num==frame_active_num-1)
		{
			document.all("right_").style.display="none";
		}
		//给前面打上“...”
		document.all("left_").style.display="block";
		current_first_view=current_first_view+1;
	}
}
