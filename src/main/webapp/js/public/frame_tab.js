// JavaScript Documentvar
var frame_num=0;//��ǰ��������
var frame_active_num=0;//��ǰ���������
var active_id=-1;//��ǰ�����id
var title_each_width=100;//ÿ��ѡ��Ŀ��
var title_top_height=24;//��Ҫ��ȥ���Ѵ��ڵĸ߶�
var frame_active_id=new Array();//��ǰ����ڵ�ID����
var frame_url=new Array();//��ǰ���ڵĴ��ڵ�ַ
var frame_name=new Array();//��ǰ���ڴ��ڵ�����
var max_window_num=0;//������ʾ���ڵ��������
var current_first_view=0;//��ǰ��ʾ�ĵ�һ�����������п���ʾ���ڵ�λ�ã���0��ʼ����
//alert (frame_active_id.length);
////
function create_frame(f_num,url,frame_name)
{
	if (frame_num==500)
	{
		alert ("���ڵ��ε�¼ʹ��ʱ�������ѡ��ؼ��ﵽϵͳ���ƣ��뽫���δ�������Ϣ��������µ�¼ϵͳ��");
		return;
	}
	var title_total_width=document.all("title_top").clientWidth;
	max_window_num=parseInt(title_total_width/title_each_width);
	//��鵱ǰ���Ƿ�򿪣�����Ѵ򿪣���ֱ����ת���ô���
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
	//����ǰ�������Ϊ����ʾ
	if (!check_true)
	{
		if (active_id>-1)
		{
			var target_frame_tr_preame="frame_tr_"+active_id;
			document.all(target_frame_tr_preame).style.display="none";
		}
		//���±���
		frame_active_id[frame_active_num]=frame_num;
		frame_url[frame_num]=url;
		frame_name[frame_num]=frame_name;
		//alert (frame_name);
		//����FRAME��HTML
		var add_html=add_frame_html(f_num,url);
		var new_frame_td_name="frame_td_"+frame_num;
		var new_frame_tr_name="frame_tr_"+frame_num;
		document.all(new_frame_td_name).innerHTML=add_html;
		document.all(new_frame_tr_name).style.display="block";
		//���µ�������HTML
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
		//����һ����ǰ���ڵı����޸�
		if (active_id!=-1)
		{
			var target_title_name="title_"+active_id;
			//alert (document.all(target_title_name).innerHTML);
			document.all(target_title_name).background="images/title_bg2.gif";
		}		
		//�����������
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
		//����ǰ���ڵĸ߶ȼ���ַ
		var target_frame_name="opt_frame_"+active_id;
		var target_title_name="title_"+active_id;
		var target_tr_name="frame_tr_"+active_id;
		//alert (target_title_name);
		//document.all(target_frame_name).height=0;
		document.all(target_frame_name).src="null.html";
		//����˵���
		document.all(target_title_name).innerHTML="";
		document.all(target_title_name).style.display="none";
		document.all(target_tr_name).style.display="none";
		//������ر���
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
		//�����һ��������Ϊ�ɼ�
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
	//����һ����ǰ���ڵı����޸�
	if (active_id!=-1)
	{
		var target_title_name="title_"+active_id;
		//alert (document.all(target_title_name).innerHTML);
		document.all(target_title_name).background="images/title_bg2.gif";
	}
	for (var i=0;i<frame_active_num;i++)
	{
		//ȫ������
		var t_name="opt_frame_"+frame_active_id[i];
		var t_frame_tr_name="frame_tr_"+frame_active_id[i];
		document.all(t_frame_tr_name).style.display="none";
		//document.all(t_name).height=0;
	}
	//�򿪵�ǰ��
	t_name="opt_frame_"+frame_id;
	var t_frame_tr_name="frame_tr_"+frame_id;
	document.all(t_frame_tr_name).style.display="block";
	active_id=frame_id;
	//document.all(t_name).height=parseInt(document.body.clientHeight)-title_top_height;
	//�޸ĵ�ǰ���ڱ���ı���
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
	//���㵱ǰ�����Ƿ���
	if (frame_active_num<=max_window_num)
	{
		//ȫ����ʾ
		for (var i=0;i<frame_active_num;i++)
		{
			title_visible(frame_active_id[i]);
		}
		current_first_view=0;
	}
	if (frame_active_num>max_window_num)
	{
		//��鵱ǰ���������д򿪴��ڵ�λ��
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
		//�����ǰ�������������ʾ��Χ�ڣ��������Ĳ�����Ϊ����ʾ
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
		//�����ǰ����ڲ��ڻ��Χ�ڣ��Ƚ�ǰ�����Ĵ������أ��ټ���������Ĵ��ڣ���������
		if (act_>=max_window_num)
		{
			var hidde_title_num=act_-max_window_num+1;
			//����ǰ��Ĵ���
			//alert (hidde_title_num);
			for (var i=0;i<hidde_title_num;i++)
			{
				title_invisible(frame_active_id[i]);
			}
			//��ʾ��ǰ�Ĵ���
			for (var i=hidde_title_num;i<hidde_title_num+max_window_num;i++)
			{
				title_visible(frame_active_id[i]);	
			}
			//���غ���Ĵ���
			for (var i=hidde_title_num+max_window_num;i<frame_active_num;i++)
			{
				title_invisible(frame_active_id[i]);
			}
			//���µ�ǰ��һ������ʾ���������п���ʾ���ڵ�λ��
			current_first_view=hidde_title_num;
		}
	}
	//����ǰ���Ƿ���Ҫ����...��
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
		//������������������֮ǰ�����һ������
		if (frame_active_num>=max_window_num)
		{
			title_invisible(frame_active_id[current_first_view+max_window_num]);
		}
		//��������ϡ�...��
		document.all("right_").style.display="block";
	}
}
function titlebar_moveright()
{
	if (frame_active_num>max_window_num && current_first_view<(frame_active_num-max_window_num))
	{
		//�ѵ�һ������
		title_invisible(frame_active_id[current_first_view]);
		//��ʾ��һ��
		title_visible(frame_active_id[current_first_view+max_window_num]);
		//����Ѿ��������һ�����ѡ�...������
		if (current_first_view+max_window_num==frame_active_num-1)
		{
			document.all("right_").style.display="none";
		}
		//��ǰ����ϡ�...��
		document.all("left_").style.display="block";
		current_first_view=current_first_view+1;
	}
}
